package nam.tran.baigiangprm391.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.Constain;
import nam.tran.baigiangprm391.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        findViewById(R.id.bt_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constain.KEY_LAB_2,"Day la bai lab 2");
                intent.putExtra(Constain.KEY_LAB_2,bundle);
                startActivity(intent);
            }
        });
    }
}
