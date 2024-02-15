package nam.tran.baigiangprm391.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.R;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        findViewById(R.id.bt_pager_adapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerActivity.this,ViewPagerExampleActivity.class);
                intent.putExtra("type",0);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_fragment_pager_adapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerActivity.this,ViewPagerExampleActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_fragment_state_pager_adapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPagerActivity.this,ViewPagerExampleActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
            }
        });
    }
}
