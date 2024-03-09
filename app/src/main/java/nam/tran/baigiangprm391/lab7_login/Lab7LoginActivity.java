package nam.tran.baigiangprm391.lab7_login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.R;

public class Lab7LoginActivity extends AppCompatActivity {

    public static final String SAVE_PREF = "save_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab7_login);
        gotoLoginScreen();
    }

    public void gotoRegisterScreen() {
        getSupportFragmentManager().beginTransaction().add(R.id.ln_main, new M001RegisterFragment()).addToBackStack("Register").commit();
    }

    public void gotoLoginScreen() {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, new M000LoginFragment()).addToBackStack("Login").commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }else {
            super.onBackPressed();
        }
    }
}