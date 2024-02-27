package nam.tran.baigiangprm391.sms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import nam.tran.baigiangprm391.R;
import nam.tran.baigiangprm391.lab6.Lab6Activity;

public class SmsActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST_CODE_SEND_SMS = 1;
    private static final String TAG = SmsActivity.class.getName();

    private EditText editTextPhoneNumber;
    private EditText editTextMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        this.editTextPhoneNumber = this.findViewById(R.id.editText_phoneNumber);
        this.editTextMessage = this.findViewById(R.id.editText_message);
        Button buttonSend = this.findViewById(R.id.button_send);

        //Kiểm tra quyền người dùng
        buttonSend.setOnClickListener(v -> askPermissionAndSendSMS());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendSMSBySmsManager();
            }else {
                Toast.makeText(this, R.string.txt_alert, Toast.LENGTH_SHORT).show();
                gotoSettings();
            }
        }
    }

    private void gotoSettings() {
        new AlertDialog.Builder(this)
                .setTitle("Need Permission")
                .setMessage("Accept permission in setting")
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    try {
                        Intent myAppSettings = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                        myAppSettings.setData(Uri.parse("package:" + getPackageName()));
                        startActivityForResult(myAppSettings, 111);
                    } catch (Exception e) {
                        Toast.makeText(SmsActivity.this, "failed to open Settings\n" + e, Toast.LENGTH_LONG).show();
                        Log.d("error", e.toString());
                    }
                })
                .create().show();
    }

    private void askPermissionAndSendSMS() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String permission = Manifest.permission.SEND_SMS;
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permission}, 101);
                return;
            }
        }
        this.sendSMSBySmsManager();
    }

    private void sendSMSBySmsManager() {
        String phoneNumber = this.editTextPhoneNumber.getText().toString();
        String message = this.editTextMessage.getText().toString();
        try {
            // Tạo đối tượng SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,
                    null,
                    message,
                    null,
                    null);
            Log.i(TAG, "Tin nhắn đã được gửi đi");
            Toast.makeText(getApplicationContext(), "Tin nhắn đã được gửi đi",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Log.e(TAG, "Gửi tin nhắn thất bại", ex);
            Toast.makeText(getApplicationContext(), "Gửi tin nhắn thất bại... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
