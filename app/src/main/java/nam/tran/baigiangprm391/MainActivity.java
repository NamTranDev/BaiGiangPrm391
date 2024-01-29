package nam.tran.baigiangprm391;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.exercise2.Exercise2Activity;
import nam.tran.baigiangprm391.lab.Lab3_1Activity;
import nam.tran.baigiangprm391.lab.Lab3_2Activity;
import nam.tran.baigiangprm391.lab.Lab3_3Activity;
import nam.tran.baigiangprm391.lab.Lab3_6Activity;
import nam.tran.baigiangprm391.lab.Lab3_7Activity;
import nam.tran.baigiangprm391.lab.Lab3_8Activity;
import nam.tran.baigiangprm391.lab.Lab3_9Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_animation_xml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(AnimationXmlActivity.class);
            }
        });

        findViewById(R.id.bt_animation_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(AnimationCodeActivity.class);
            }
        });

        findViewById(R.id.bt_exercise2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Exercise2Activity.class);
            }
        });

        findViewById(R.id.bt_lab_3_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Lab3_1Activity.class);
            }
        });

        findViewById(R.id.bt_lab_3_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Lab3_2Activity.class);
            }
        });

        findViewById(R.id.bt_lab_3_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Lab3_3Activity.class);
            }
        });

        findViewById(R.id.bt_lab_3_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Lab3_6Activity.class);
            }
        });

        findViewById(R.id.bt_lab_3_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Lab3_7Activity.class);
            }
        });

        findViewById(R.id.bt_lab_3_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Lab3_8Activity.class);
            }
        });

        findViewById(R.id.bt_lab_3_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Lab3_9Activity.class);
            }
        });
    }

    void openScreen(Class screen) {
        Intent intent = new Intent(this, screen);
        startActivity(intent);
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
    }
}