package nam.tran.baigiangprm391;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailIntentActivity extends AppCompatActivity {

    private EditText input;
    private boolean hasResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_intent);

        input = findViewById(R.id.input);

        Intent intent = getIntent();
        if (intent != null){
            String text = intent.getStringExtra(Constain.KEY_TEXT_INPUT);
            input.setText(text);
            hasResult = intent.getBooleanExtra(Constain.KEY_HAS_RESULT,false);
            input.setEnabled(hasResult);
        }

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!hasResult)
                    return;
                Intent intent = new Intent();
                intent.putExtra(Constain.KEY_TEXT_INPUT_DETAIL,input.getText().toString());
                setResult(RESULT_OK,intent);
            }
        });
    }
}
