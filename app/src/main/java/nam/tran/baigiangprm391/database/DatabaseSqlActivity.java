package nam.tran.baigiangprm391.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.R;

public class DatabaseSqlActivity extends AppCompatActivity {
    private EditText nameEditText, ageEditText;
    private LinearLayout userListLayout;

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_sql);

        // Khởi tạo UI components
        nameEditText = findViewById(R.id.editTextName);
        ageEditText = findViewById(R.id.editTextAge);
        userListLayout = findViewById(R.id.userListLayout);

        // Khởi tạo cơ sở dữ liệu
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        // Load danh sách người dùng khi ứng dụng khởi động
        loadUserList();

        // Xử lý sự kiện khi người dùng nhấn nút "Add User"
        Button addButton = findViewById(R.id.buttonAddUser);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser() {
        String name = nameEditText.getText().toString().trim();
        String ageString = ageEditText.getText().toString().trim();

        if (name.isEmpty() || ageString.isEmpty()) {
            Toast.makeText(this,"Please input name and age",Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageString);

        // Thêm người dùng vào cơ sở dữ liệu
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UserEntry.COLUMN_NAME, name);
        values.put(DatabaseContract.UserEntry.COLUMN_AGE, age);

        long newRowId = database.insert(DatabaseContract.UserEntry.TABLE_NAME, null, values);

        // Hiển thị thông báo khi thêm người dùng thành công
        if (newRowId != -1) {
            loadUserList();  // Cập nhật danh sách người dùng
            nameEditText.getText().clear();
            ageEditText.getText().clear();
        }
    }

    private void loadUserList() {
        userListLayout.removeAllViews();

        // Truy vấn tất cả dữ liệu từ cơ sở dữ liệu
        Cursor cursor = database.query(
                DatabaseContract.UserEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        // Hiển thị danh sách người dùng
        while (cursor.moveToNext()) {
            int userId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UserEntry._ID));
            String userName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserEntry.COLUMN_NAME));
            int userAge = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UserEntry.COLUMN_AGE));

            TextView userTextView = new TextView(this);
            userTextView.setText("ID: " + userId + ", Name: " + userName + ", Age: " + userAge);

            userListLayout.addView(userTextView);
        }

        cursor.close();
    }
}
