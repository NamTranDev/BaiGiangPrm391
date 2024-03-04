package nam.tran.baigiangprm391.external;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import nam.tran.baigiangprm391.R;

public class ExternalStorageActivity extends AppCompatActivity {

    private final String simpleFileName = "note.txt";
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        Button saveButton = this.findViewById(R.id.button_save);
        Button readButton = this.findViewById(R.id.button_read);
        this.textView = this.findViewById(R.id.textView);
        this.editText = this.findViewById(R.id.editText);

        saveButton.setOnClickListener(v -> saveData());
        readButton.setOnClickListener(v -> readData());
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                writeDataValue();
            } else {
                Toast.makeText(this,"Không cấp quyền nên không thể thực hiện thao tác này",Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 102){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readDataValue();
            } else {
                Toast.makeText(this,"Không cấp quyền nên không thể thực hiện thao tác này",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveData() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 101);
            return;
        }
        writeDataValue();
    }

    private void writeDataValue(){
        String data = this.editText.getText().toString();
        try {
//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), simpleFileName);
//            try (FileWriter fileWriter = new FileWriter(file)){
//                fileWriter.append(data);
//                Toast.makeText(this, "File saved!", Toast.LENGTH_SHORT).show();
//            }catch (Exception e){
//                e.printStackTrace();
//                Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }

            //Trỏ đến vùng nhớ EXTERNAL
            String path = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath();
            FileOutputStream out = new FileOutputStream(new File(path + "/" + simpleFileName));
            // Ghi dữ liệu.
            out.write(data.getBytes());
            out.close();
            Toast.makeText(this, "File saved!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void readData() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 102);
            return;
        }
        readDataValue();
    }

    private void readDataValue() {
        try {
            FileInputStream in = new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), simpleFileName));
//            //Trở đến vùng nhớ EXTERNAL
//            String path = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath();
//            FileInputStream in = new FileInputStream(new File(path + "/" + simpleFileName));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
            this.textView.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
