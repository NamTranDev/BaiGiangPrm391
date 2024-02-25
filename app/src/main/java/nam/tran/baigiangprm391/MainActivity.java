package nam.tran.baigiangprm391;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import nam.tran.baigiangprm391.permission.NormalPermissionActivity;
import nam.tran.baigiangprm391.permission.RuntimePermissionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_normal_permission).setOnClickListener(v -> openScreen(NormalPermissionActivity.class));
        findViewById(R.id.bt_runtime_permission).setOnClickListener(v -> openScreen(RuntimePermissionActivity.class));
    }

    void openScreen(Class screen) {
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }
}