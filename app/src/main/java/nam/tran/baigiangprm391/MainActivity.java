package nam.tran.baigiangprm391;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import nam.tran.baigiangprm391.exercise4.Exercise4Activity;
import nam.tran.baigiangprm391.lab6.Lab6Activity;
import nam.tran.baigiangprm391.permission.NormalPermissionActivity;
import nam.tran.baigiangprm391.permission.RuntimePermissionActivity;
import nam.tran.baigiangprm391.videoview.VideoViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_normal_permission).setOnClickListener(v -> openScreen(NormalPermissionActivity.class));
        findViewById(R.id.bt_runtime_permission).setOnClickListener(v -> openScreen(RuntimePermissionActivity.class));
        findViewById(R.id.bt_lab_6).setOnClickListener(v -> openScreen(Lab6Activity.class));
        findViewById(R.id.bt_exercise_4).setOnClickListener(v -> openScreen(Exercise4Activity.class));
        findViewById(R.id.bt_video_view).setOnClickListener(v -> openScreen(VideoViewActivity.class));
    }

    void openScreen(Class screen) {
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }
}