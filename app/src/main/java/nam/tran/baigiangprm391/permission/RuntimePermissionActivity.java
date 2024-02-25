package nam.tran.baigiangprm391.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import nam.tran.baigiangprm391.R;

public class RuntimePermissionActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);
        findViewById(R.id.bt_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(RuntimePermissionActivity.this, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    // Quyền đã được cấp phép, gọi hàm để thực hiện cuộc gọi
                    makeEmergencyCall();
                } else {
                    // Quyền chưa được cấp phép, yêu cầu người dùng cấp quyền
                    ActivityCompat.requestPermissions(
                            RuntimePermissionActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_CALL_PHONE_PERMISSION
                    );
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp phép, gọi hàm để thực hiện cuộc gọi
                makeEmergencyCall();
            } else {
                // Người dùng từ chối cấp quyền, bạn có thể thông báo hoặc xử lý khác tùy ý
                Toast.makeText(this,"Không cấp quyền nên không thể thực hiện cuộc gọi từ ứng dụng",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makeEmergencyCall() {
        // Gọi đến số 911
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:113"));

        // Kiểm tra xem thiết bị có khả năng thực hiện cuộc gọi không
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(callIntent);
        }
    }
}
