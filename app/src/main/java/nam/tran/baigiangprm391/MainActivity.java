package nam.tran.baigiangprm391;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nam.tran.baigiangprm391.dialog.DialogActivity;
import nam.tran.baigiangprm391.exercise.Exercise3Activity;
import nam.tran.baigiangprm391.fragment.FragmentDynamicDefinitionActivity;
import nam.tran.baigiangprm391.fragment.FragmentStaticDefinitionActivity;
import nam.tran.baigiangprm391.lab.Lab4_1Activity;
import nam.tran.baigiangprm391.lab.Lab4_2_3Activity;
import nam.tran.baigiangprm391.toast.ToastActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(ToastActivity.class);
            }
        });

        findViewById(R.id.bt_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(DialogActivity.class);
            }
        });

        findViewById(R.id.bt_fragment_static_definition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(FragmentStaticDefinitionActivity.class);
            }
        });

        findViewById(R.id.bt_fragment_dynamic_definition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(FragmentDynamicDefinitionActivity.class);
            }
        });

        findViewById(R.id.bt_exercise_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Exercise3Activity.class);
            }
        });

        findViewById(R.id.bt_lab_4_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Lab4_1Activity.class);
            }
        });

        findViewById(R.id.bt_lab_4_2_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen(Lab4_2_3Activity.class);
            }
        });
    }

    void openScreen(Class screen) {
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }
}