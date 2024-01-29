package nam.tran.baigiangprm391.exercise2;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import nam.tran.baigiangprm391.R;

public class Exercise2Activity extends AppCompatActivity {

//    https://www.almanac.com/predict-temperature-cricket-chirps

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);
        EditText input = findViewById(R.id.edt_input_thermometer);
        Button calculate = findViewById(R.id.bt_calculate);
        TextView result = findViewById(R.id.tv_result);

        DecimalFormat format = new DecimalFormat("#0.00");

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValue = input.getText().toString();
                if (TextUtils.isEmpty(inputValue)){
                    Toast.makeText(Exercise2Activity.this,"Please Input Field",Toast.LENGTH_SHORT).show();
                }else {
                    hideKeyboard(Exercise2Activity.this);
                    try {
                        int chirps = Integer.parseInt(inputValue);
                        double temp = (chirps / 3.0) + 4;
                        result.setVisibility(View.VISIBLE);
                        result.setText("The approximate temperature outside is " + format.format(temp) + " degrees Celcius");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
