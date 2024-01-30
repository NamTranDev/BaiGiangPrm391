package nam.tran.baigiangprm391.toast;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.R;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        findViewById(R.id.bt_toast_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToastActivity.this,"Đây là toast simple từ Android",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.bt_toast_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = ToastActivity.this.getLayoutInflater();
                // lấy layout từ thư mục res/layout/item_toast.xml ra môi trường code.
                View layout = inflater.inflate(R.layout.item_toast, null);
                // Ánh xạ các đối tượng giao diện con ra để đổi dữ liệu
                TextView textView = (TextView) layout.findViewById(R.id.textView);
                textView.setText("Đây là toast custom");
                textView.setTextColor(Color.BLUE);

                // Tạo một Toast tùy biến
                Toast toast = new Toast(ToastActivity.this);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });
    }
}
