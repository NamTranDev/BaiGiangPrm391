package nam.tran.baigiangprm391.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.R;

public class SharePreferenceActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private CheckBox loggedInCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);

        usernameEditText = findViewById(R.id.editTextUsername);
        loggedInCheckBox = findViewById(R.id.checkBoxLoggedIn);

        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(v -> saveData());

        loadData();  // Load saved data when the app starts
    }

    private void saveData() {
        String username = usernameEditText.getText().toString();
        boolean isLoggedIn = loggedInCheckBox.isChecked();

        // Lưu dữ liệu vào SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putBoolean("is_logged_in", isLoggedIn);
        editor.apply();
    }

    private void loadData() {
        // Đọc dữ liệu từ SharedPreferences và hiển thị lên UI
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

        usernameEditText.setText(username);
        loggedInCheckBox.setChecked(isLoggedIn);
    }
}
