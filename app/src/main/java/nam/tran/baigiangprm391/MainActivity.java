package nam.tran.baigiangprm391;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import nam.tran.baigiangprm391.asm3.Asm3Activity;
import nam.tran.baigiangprm391.lab7_download.Lab7DownloadActivity;
import nam.tran.baigiangprm391.lab7_login.Lab7LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_lab7_download).setOnClickListener(v -> openScreen(Lab7DownloadActivity.class));
        findViewById(R.id.bt_lab7_login).setOnClickListener(v -> openScreen(Lab7LoginActivity.class));
        findViewById(R.id.bt_asm3).setOnClickListener(v -> openScreen(Asm3Activity.class));
    }

    void openScreen(Class screen) {
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }
}