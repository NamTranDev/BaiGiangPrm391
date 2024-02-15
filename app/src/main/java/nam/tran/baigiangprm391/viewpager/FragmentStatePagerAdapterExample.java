package nam.tran.baigiangprm391.viewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentStatePagerAdapterExample extends FragmentStatePagerAdapter {
    public FragmentStatePagerAdapterExample(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ExampleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("argument",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab-" + (position + 1);
    }
}
