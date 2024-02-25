package nam.tran.baigiangprm391.exercise4;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import nam.tran.baigiangprm391.R;
import nam.tran.baigiangprm391.lab6.Lab6Activity;

public class Exercise4Activity extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_4);
        initViews();
        text = findViewById(R.id.permission_phone);
        text.setOnClickListener(v -> checkPermissionAndInit());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        text.setText("Đã có thể lắng nghe cuộc gọi");
    }

    private void checkPermissionAndInit(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
                return;
            }
        }
        text.setText("Đã có thể lắng nghe cuộc gọi");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            text.setText("Đã có thể lắng nghe cuộc gọi");
        } else {
            Toast.makeText(this, R.string.txt_alert, Toast.LENGTH_SHORT).show();
            gotoSettings();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111){
            text.setText("Đã có thể lắng nghe cuộc gọi");
        }
    }

    private void initViews() {
        try {
            // khai báo intent-filer trong file Manifest
            // READ MORE: https://developer.android.com/guide/topics/manifest/intent-filter-element
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);

            /*Note: Apps can receive broadcasts in two ways: through manifest-declared receivers and context-registered receivers.
            * Read more: https://developer.android.com/guide/components/broadcasts#receiving-broadcasts
            * =============================================================================================*
            * Dưới đây là đoạn code đăng ký BroadcastReceiver với context
            * READ MORE: https://developer.android.com/guide/components/broadcasts#context-registered-receivers*/
            BroadcastReceiver receiver = new BroadcastReceiver() {
                // khi nhận được tín hiệu từ biến receiver phương thức onReceiver() sẽ thực thi
                @Override
                public void onReceive(Context context, Intent intent) {
                    doScreenTalk(intent.getAction());
                }
            };
            // Đây là câu lệnh đăng ký receiver cùng intent-filter
            /* Với câu lệnh này thì khi xảy ra 1 action đã đăng ký trong biến filter
            * thì BroadcastReceiver sẽ nhận được tín hiệu thông qua biến receiver và chuyển flow control
            * thực thi phương thức onReceiver()*/
            registerReceiver(receiver, filter);

        }catch (Exception ignored){

        }
    }

    private void gotoSettings() {
        new AlertDialog.Builder(this)
                .setTitle("Need Permission")
                .setMessage("Accept permission in setting")
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",
                                Exercise4Activity.this.getPackageName(), null);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivityForResult(intent,111);
                    } catch (Exception e) {
                        Toast.makeText(Exercise4Activity.this, "failed to open Settings\n" + e, Toast.LENGTH_LONG).show();
                        Log.d("error", e.toString());
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> finish())
                .create().show();
    }

    /*trong phương thức dưới đây thực thi hành động
    * khi screen on sẽ gọi phương thức sayHello()
    * khi screen off sẽ gọi phương thức sayGoodbye()*/
    private void doScreenTalk(String action) {
        if(action.equals(Intent.ACTION_SCREEN_ON)){
            sayHello();
        }else if(action.equals(Intent.ACTION_SCREEN_OFF)){
            sayGoodbye();
        }
    }

    //Phương thức thực thi khởi chạy file âm thanh bye_bye.mp3
    private void sayGoodbye() {
        MediaPlayer.create(this, R.raw.bye_bye).start();
    }

    //Phương thức thực thi khởi chạy file âm thanh hello.mp3
    private void sayHello() {
        MediaPlayer.create(this, R.raw.hello).start();
    }
}