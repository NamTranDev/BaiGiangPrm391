package nam.tran.baigiangprm391.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import nam.tran.baigiangprm391.Constain;
import nam.tran.baigiangprm391.R;

public class StaticExample1Fragment extends Fragment {

    private EditText edtName, edtAge;
    private Button btnSend;

    private CommunityFragmentAndActivity listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(Constain.TAG, getClass().getName() + " - " + "onAttach");
        if (context instanceof FragmentStaticDefinitionActivity)
            this.listener = (CommunityFragmentAndActivity) context;
        else
            throw new RuntimeException(context + "must implement onViewSelected!");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constain.TAG, getClass().getName() + " - " + "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(Constain.TAG, getClass().getName() + " - " + "onCreateView");
        View view = inflater.inflate(R.layout.fragment_static_example_1, container, false);
        View text = view.findViewById(R.id.tv_example);
        edtName = view.findViewById(R.id.edtName);
        edtAge = view.findViewById(R.id.edtAge);
        btnSend = view.findViewById(R.id.btnSend);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setVisibility(View.GONE);
                view.findViewById(R.id.contain_community_with_activity).setVisibility(View.VISIBLE);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String age = edtAge.getText().toString().trim();

                if (!name.equals("") && !age.equals("")) {
                    listener.sendData(name, age);
                    final InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                } else if (name.equals("")) {
                    edtName.requestFocus();
                } else {
                    edtAge.requestFocus();
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(Constain.TAG, getClass().getName() + " - " + "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(Constain.TAG, getClass().getName() + " - " + "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constain.TAG, getClass().getName() + " - " + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(Constain.TAG, getClass().getName() + " - " + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(Constain.TAG, getClass().getName() + " - " + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Constain.TAG, getClass().getName() + " - " + "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(Constain.TAG, getClass().getName() + " - " + "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constain.TAG, getClass().getName() + " - " + "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(Constain.TAG, getClass().getName() + " - " + "onDetach");
    }

    interface CommunityFragmentAndActivity {
        void sendData(String name, String age);
    }
}
