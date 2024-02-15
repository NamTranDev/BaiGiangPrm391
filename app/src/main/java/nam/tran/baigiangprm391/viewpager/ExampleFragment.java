package nam.tran.baigiangprm391.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import nam.tran.baigiangprm391.R;

public class ExampleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            int position = bundle.getInt("argument");
            TextView text = view.findViewById(R.id.tv);
            text.setText("Đây là fragment " + (position + 1) + " trong view pager");
        }
        return view;
    }
}
