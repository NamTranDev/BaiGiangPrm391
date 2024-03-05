package nam.tran.baigiangprm391;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import nam.tran.baigiangprm391.asm2.Asm2Activity;
import nam.tran.baigiangprm391.database.DatabaseSqlActivity;
import nam.tran.baigiangprm391.external.ExternalStorageActivity;
import nam.tran.baigiangprm391.internal.InternalStorageActivity;
import nam.tran.baigiangprm391.sharepreference.SharePreferenceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_asm2).setOnClickListener(v -> openScreen(Asm2Activity.class));
        findViewById(R.id.bt_internal_storage).setOnClickListener(v -> openScreen(InternalStorageActivity.class));
        findViewById(R.id.bt_external_storage).setOnClickListener(v -> openScreen(ExternalStorageActivity.class));
        findViewById(R.id.bt_share_reference).setOnClickListener(v -> openScreen(SharePreferenceActivity.class));
        findViewById(R.id.bt_database).setOnClickListener(v -> openScreen(DatabaseSqlActivity.class));
    }

    void openScreen(Class screen) {
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }
}