package nam.tran.baigiangprm391.recyclerview;

import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nam.tran.baigiangprm391.R;

public class RecyclerViewActivity extends AppCompatActivity {

    int typeLayout = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.rv);
        Button btLayout = findViewById(R.id.bt_layout_rv);
        EditText inputSpan = findViewById(R.id.edt);
        CheckBox isItemAnimator = findViewById(R.id.isItemAnimator);
        CheckBox isHeader = findViewById(R.id.isHeader);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(VERTICAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(linearLayoutManager);

        CountryAdapter adapter = new CountryAdapter(seed());
        recyclerView.setAdapter(adapter);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(RecyclerViewActivity.this, R.anim.layout_animation_fall_down);

        btLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (typeLayout) {
                    case 1:
                        typeLayout = 2;
                        btLayout.setText("Switch Linear Layout");
                        inputSpan.setVisibility(View.GONE);
                        int spanCount = 2;
                        try {
                            spanCount = Integer.parseInt(inputSpan.getText().toString());
                        } catch (Exception ignored) {
                        }

                        gridLayoutManager.setSpanCount(spanCount == 0 ? 2 : spanCount);
                        int finalSpanCount = spanCount;
                        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
                            @Override
                            public int getSpanSize(int position) {
                                if (adapter.typeHeader && position == 0){
                                    return finalSpanCount;
                                }
                                return 1;
                            }
                        });
                        recyclerView.setLayoutManager(gridLayoutManager);
                        break;
                    case 2:
                        typeLayout = 1;
                        btLayout.setText("Switch Grid Layout");
                        inputSpan.setVisibility(View.VISIBLE);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        break;
                }
                adapter.notifyDataSetChanged();
                if (isItemAnimator.isChecked()){
                    recyclerView.startLayoutAnimation();
                }
            }
        });

        isItemAnimator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recyclerView.setLayoutAnimation(animation);
                    recyclerView.startLayoutAnimation();
                } else {
                    recyclerView.clearAnimation();
                }
                adapter.notifyDataSetChanged();
            }
        });

        isHeader.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.typeHeader = isChecked;
                adapter.notifyDataSetChanged();
            }
        });
    }

    public List<Country> seed() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Brazil", R.drawable.brazil));
        countries.add(new Country("Canada", R.drawable.canada));
        countries.add(new Country("China", R.drawable.china));
        countries.add(new Country("Dominican Republic", R.drawable.dominican_republic));
        countries.add(new Country("Germany", R.drawable.germany));
        countries.add(new Country("India", R.drawable.india));
        countries.add(new Country("Netherlands", R.drawable.netherlands));
        countries.add(new Country("Norway", R.drawable.norway));
        countries.add(new Country("Peru", R.drawable.peru));
        countries.add(new Country("Philippines", R.drawable.philipines));
        countries.add(new Country("Poland", R.drawable.poland));
        countries.add(new Country("Romania", R.drawable.romania));
        countries.add(new Country("South Africa", R.drawable.south_africa));
        countries.add(new Country("Spain", R.drawable.spain));
        countries.add(new Country("Sweden", R.drawable.sweden));
        countries.add(new Country("United Kingdom", R.drawable.united_kingdom));
        countries.add(new Country("United States", R.drawable.united_states));

        Collections.sort(countries, (country1, country2) -> country1.getName().compareToIgnoreCase(country2.getName()));

        return countries;
    }
}
