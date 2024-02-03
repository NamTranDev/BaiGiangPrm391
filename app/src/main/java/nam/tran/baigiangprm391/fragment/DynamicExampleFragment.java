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

public class DynamicExampleFragment extends Fragment {

    private boolean isReplace = false;
    private String count;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(Constain.TAG,getClass().getName() + " - " + "onAttach");
    }

    private String log(){
        return (isReplace ? "Đây là fragment Replace :" : "Đây là fragment Add :") + count;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null){
            count = bundle.getString(Constain.ARGUMENT);
            isReplace = bundle.getBoolean(Constain.ARGUMENT_BOOLEAN,false);
        }

        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onCreateView");
        View view = inflater.inflate(R.layout.fragment_dynamic_example,container,false);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(log());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(Constain.TAG,getClass().getName() + " - " + log() + " - " + "onDetach");
    }
}
