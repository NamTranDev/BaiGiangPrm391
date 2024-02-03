package nam.tran.baigiangprm391.lab;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import nam.tran.baigiangprm391.R;

public class Lab4_2_3Activity extends AppCompatActivity implements View.OnClickListener {

    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_4_2_3);
        initView();
    }

    private void initView() {

        findViewById(R.id.fr_mom).setOnClickListener(this);
        findViewById(R.id.fr_dad).setOnClickListener(this);
        findViewById(R.id.fr_crush).setOnClickListener(this);
        findViewById(R.id.fr_best_friend).setOnClickListener(this);
        findViewById(R.id.ivdialer).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_popup_enter));
        if (v instanceof FrameLayout) {
            // getTag() Returns this view's tag.
            // In XML layout file, The "android:tag" element is used to indicate a tag
            processCall((String) v.getTag());
            return;
        }
        //Hiển thị dialog thông báo
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Thông báo");
        alert.setMessage("Mở màn hình quay số?");
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gotoDialPad();
            }
        });
        gotoDialPad();
    }

    private void gotoDialPad() {

        // Câu lệnh để mở màn hình quay số của ứng dụng Phone
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }

    private void processCall(String phone) {
        this.phone = phone;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                            101);
            Toast.makeText(this, "Hãy thực hiện lại sau khi cấp quyền!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Câu lệnh để mở ứng dụng Phone nhằm thực hiện cuộc gọi
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }
}
