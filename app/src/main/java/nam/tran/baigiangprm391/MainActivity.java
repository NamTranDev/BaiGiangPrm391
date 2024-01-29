package nam.tran.baigiangprm391;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

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
    }

    void openScreen(Class screen){
        Intent intent = new Intent(this,screen);
        startActivity(intent);
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
    }
}