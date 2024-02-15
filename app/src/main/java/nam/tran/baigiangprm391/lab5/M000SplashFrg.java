package nam.tran.baigiangprm391.lab5;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import nam.tran.baigiangprm391.R;

public class M000SplashFrg extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initViews();
        // Inflate activity_main layout for m000_frg_splash to show M000Screen (m000_frg_splash.xml)
        return inflater.inflate(R.layout.m000_frg_splash, container, false);
    }

    private void initViews() {
        //Delay 2 seconds to show M001Screen (m001_frg_topic.xml)
        new Handler().postDelayed(this::gotoM001Screen,
                2000);
    }

    // show m001screen by a way call gotoM001Screen method in MainActivity
    private void gotoM001Screen() {
        ((Lab5Activity) getActivity()).gotoM001Screen();
    }
}