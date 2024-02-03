package nam.tran.baigiangprm391.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import nam.tran.baigiangprm391.Constain;
import nam.tran.baigiangprm391.R;

public class FragmentStaticDefinitionActivity  extends AppCompatActivity implements StaticExample1Fragment.CommunityFragmentAndActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_static_definition);

        Log.d(Constain.TAG,getClass().getName() + " - " + "onCreate");
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

    @Override
    public void sendData(String name, String age) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_static_example_2);
        if (fragment instanceof StaticExample2Fragment){
            ((StaticExample2Fragment) fragment).showInfor(name,age);
        }else {
            Toast.makeText(getApplicationContext(),"???",Toast.LENGTH_LONG).show();
        }
    }
}
