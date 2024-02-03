package nam.tran.baigiangprm391.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import nam.tran.baigiangprm391.Constain;
import nam.tran.baigiangprm391.R;

public class FragmentDynamicDefinitionActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dynamic_definition);

        Log.d(Constain.TAG,getClass().getName() + " - " + "onCreate");



        findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeFragment(false);
            }
        });

        findViewById(R.id.bt_replace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeFragment(true);
            }
        });
    }

    private void makeFragment(boolean isReplace) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        DynamicExampleFragment fragment = new DynamicExampleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constain.ARGUMENT,String.valueOf(count));
        bundle.putBoolean(Constain.ARGUMENT_BOOLEAN,isReplace);
        fragment.setArguments(bundle);
        if (isReplace){
            fragmentTransaction.replace(R.id.container, fragment);
        }else {
            fragmentTransaction.add(R.id.container, fragment);
        }
        if (count > 0){
            fragmentTransaction.addToBackStack(String.valueOf(count));
        }
        fragmentTransaction.commit();
        count++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onDestroy");
    }
}
