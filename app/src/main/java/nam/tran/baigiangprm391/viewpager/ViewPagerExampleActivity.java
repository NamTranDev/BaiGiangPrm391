package nam.tran.baigiangprm391.viewpager;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import nam.tran.baigiangprm391.R;

public class ViewPagerExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_example);
        CheckBox isTab = findViewById(R.id.isTab);
        ViewPager viewPager = findViewById(R.id.vp);
        TabLayout tab = findViewById(R.id.tab);
        tab.setVisibility(View.GONE);

        int type = getIntent().getIntExtra("type",0);
        PagerAdapter adapter;
        switch (type){
            case 1:
                adapter = new FragmentPagerAdapterExample(getSupportFragmentManager());
                break;
            case 2:
                adapter = new FragmentStatePagerAdapterExample(getSupportFragmentManager());
                break;
            default:
                isTab.setVisibility(View.GONE);
                adapter = new PagerAdapterExample(this);
                break;
        }
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

        isTab.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tab.setVisibility(View.VISIBLE);
            } else {
                tab.setVisibility(View.GONE);
            }
        });
    }
}
