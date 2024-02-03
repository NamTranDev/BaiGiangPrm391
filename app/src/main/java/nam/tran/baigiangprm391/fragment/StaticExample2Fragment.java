package nam.tran.baigiangprm391.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import nam.tran.baigiangprm391.Constain;
import nam.tran.baigiangprm391.R;

public class StaticExample2Fragment extends Fragment {

    private TextView tvExample;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(Constain.TAG,getClass().getName() + " - " + "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constain.TAG,getClass().getName() + " - " + "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(Constain.TAG,getClass().getName() + " - " + "onCreateView");
        View view = inflater.inflate(R.layout.fragment_static_example_2,container,false);
        tvExample = view.findViewById(R.id.tv_example_2);
        return view;
    }

    public void showInfor(String name, String age){
        tvExample.setText(name + "\n" + age);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(Constain.TAG,getClass().getName() + " - " + "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(Constain.TAG,getClass().getName() + " - " + "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(Constain.TAG,getClass().getName() + " - " + "onDetach");
    }
}
