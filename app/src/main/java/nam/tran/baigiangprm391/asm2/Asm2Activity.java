package nam.tran.baigiangprm391.asm2;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import nam.tran.baigiangprm391.R;

public class Asm2Activity extends AppCompatActivity {

    private static final String TAG = Asm2Activity.class.getName();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asm2);

        initViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initViews() {
        getSupportFragmentManager().beginTransaction().replace(
            R.id.ln_main, new MenuFragment(), null)
            .addToBackStack(null).commit();
    }

    public void gotoDetailFragment(String animalType, List<Animal> listAnimal, Animal animal) {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setData(listAnimal, animalType, animal);

        Log.i(TAG, "gotoDetailFragment: pass");
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.ln_main, detailFragment, null)
            .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.fade_out,android.R.anim.fade_in,android.R.anim.slide_out_right)
            .addToBackStack(null)
            .commit();


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