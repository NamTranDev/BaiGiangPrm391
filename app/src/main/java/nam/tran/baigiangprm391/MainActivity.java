package nam.tran.baigiangprm391;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nam.tran.baigiangprm391.cardview.CardViewActivity;
import nam.tran.baigiangprm391.lab5.Lab5Activity;
import nam.tran.baigiangprm391.notification.NotificationActivity;
import nam.tran.baigiangprm391.recyclerview.RecyclerViewActivity;
import nam.tran.baigiangprm391.viewpager.ViewPagerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_notification).setOnClickListener(v -> openScreen(NotificationActivity.class));
        findViewById(R.id.bt_card_view).setOnClickListener(v -> openScreen(CardViewActivity.class));
        findViewById(R.id.bt_recycler_view).setOnClickListener(v -> openScreen(RecyclerViewActivity.class));
        findViewById(R.id.bt_view_pager).setOnClickListener(v -> openScreen(ViewPagerActivity.class));
        findViewById(R.id.bt_lab_5).setOnClickListener(v -> openScreen(Lab5Activity.class));
    }

    void openScreen(Class screen) {
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }
}