package nam.tran.baigiangprm391;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AnimationCodeActivity extends AppCompatActivity {

    AnimatorSet animatorSet;

    int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_code);

        ImageView example = findViewById(R.id.iv_example);
        Button start = findViewById(R.id.bt_start);
        Button stop = findViewById(R.id.bt_stop);

        RadioGroup select = findViewById(R.id.rg_select);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = select.getCheckedRadioButtonId();
                if (selectedId == R.id.rb_view_animation){
                    viewAnimation(example);
                } else if (selectedId == R.id.rb_property_animation) {
                    propertyAnimation(example);
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = select.getCheckedRadioButtonId();
                if (selectedId == R.id.rb_view_animation){
                    example.clearAnimation();
                } else if (selectedId == R.id.rb_property_animation) {
                    if (animatorSet != null){
                        animatorSet.cancel();
                    }
                }
            }
        });
    }

    void viewAnimation(View view){
        // Create Alpha Animation
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setDuration(2000); // 1 second

//        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                view.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                view.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

        // Apply Animation to View
        view.startAnimation(alphaAnimation);
    }

    void propertyAnimation(View view) {
        // Create ObjectAnimator for translationX property
        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, 200f);
        translationXAnimator.setDuration(1000); // 1 second

        // Create ObjectAnimator for rotation property
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        rotationAnimator.setDuration(1000); // 1 second

        // Create AnimatorSet to play both animations together
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationXAnimator, rotationAnimator);

        // Start the AnimatorSet
        animatorSet.start();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }
}
