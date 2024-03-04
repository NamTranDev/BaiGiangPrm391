package nam.tran.baigiangprm391.internal;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import nam.tran.baigiangprm391.R;

public class InternalStorageActivity extends AppCompatActivity {

    private final String simpleFileName = "note.txt";
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        Button saveButton = this.findViewById(R.id.button_save);
        Button readButton = this.findViewById(R.id.button_read);
        this.textView = this.findViewById(R.id.textView);
        this.editText = this.findViewById(R.id.editText);

        saveButton.setOnClickListener(v -> saveData());
        readButton.setOnClickListener(v -> readData());
    }

    private void saveData() {
        String data = this.editText.getText().toString();
        try {
            FileOutputStream out = this.openFileOutput(simpleFileName, MODE_PRIVATE);
            // Ghi dữ liệu.
            out.write(data.getBytes());
            out.close();
            Toast.makeText(this, "File saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void readData() {
        try {
            FileInputStream in = this.openFileInput(simpleFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
            this.textView.setText(sb.toString());
        } catch (Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
