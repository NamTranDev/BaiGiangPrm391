package nam.tran.baigiangprm391.asm3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import nam.tran.baigiangprm391.R;
import nam.tran.baigiangprm391.lab7_download.Lab7DownloadActivity;

public class Asm3Activity extends AppCompatActivity {

    private static final String TAG = Asm3Activity.class.getName();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asm3);

        initViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initViews() {
        getSupportFragmentManager().beginTransaction().replace(
                R.id.ln_main, new MenuFragment(), null)
                .addToBackStack(null).commit();

        checkPermission();
    }

    private boolean checkPermission(){
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CALL_LOG
            }, 101);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else {
                Toast.makeText(this, R.string.txt_alert, Toast.LENGTH_SHORT).show();
                gotoSettings();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111){
            if (checkPermission()){

            }
        }
    }

    private void gotoSettings() {
        new AlertDialog.Builder(this)
                .setTitle("Need Permission")
                .setMessage("Accept permission in setting")
                .setCancelable(false)
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    try {
                        Intent myAppSettings = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                        myAppSettings.setData(Uri.parse("package:" + getPackageName()));
                        startActivityForResult(myAppSettings, 111);
                    } catch (Exception e) {
                        Toast.makeText(Asm3Activity.this, "failed to open Settings\n" + e, Toast.LENGTH_LONG).show();
                        Log.d("error", e.toString());
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> finish())
                .create().show();
    }

    public void gotoDetailFragment(String animalType, List<Animal> listAnimal, Animal animal) {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setData(listAnimal, animalType, animal);

        Log.i(TAG, "gotoDetailFragment: pass");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ln_main, detailFragment, null)
                .addToBackStack(null)
                .commit();


    }

    public void backtoMenuFragment(String animalType, List<Animal> listAnimal) {
        getSupportFragmentManager().popBackStackImmediate();

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }else {
            super.onBackPressed();
        }
    }
}