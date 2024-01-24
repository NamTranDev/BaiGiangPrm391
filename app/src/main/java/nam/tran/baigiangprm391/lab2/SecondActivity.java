package nam.tran.baigiangprm391.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.Constain;
import nam.tran.baigiangprm391.R;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getBundleExtra(Constain.KEY_LAB_2);
            if (bundle != null){
                String textArgument = bundle.getString(Constain.KEY_LAB_2);
                TextView text = findViewById(R.id.tv_argument);
                text.setText(textArgument);
                Toast.makeText(SecondActivity.this,textArgument,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
