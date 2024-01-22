package nam.tran.baigiangprm391;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DemoIntentActivity extends AppCompatActivity {

    private static final int REQUEST_DETAIL = 123;

    EditText input;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK){
                updateInput(result.getData());
            }
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_intent);

        input = findViewById(R.id.input);
        findViewById(R.id.bt_intent_tuong_minh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openDetail();
                openDetailForResult();
//                openDetailForResultNew();
            }
        });

        findViewById(R.id.bt_intent_khong_tuong_minh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openCall();
//                openSms();
//                openSearchGoogle();
            }
        });
    }

    private void openDetail() {
        Intent intent = new Intent(this, DetailIntentActivity.class);
        intent.putExtra(Constain.KEY_TEXT_INPUT, input.getText().toString());
        intent.putExtra(Constain.KEY_HAS_RESULT, false);
        startActivity(intent);
    }

    private void openDetailForResult() {
        Intent intent = new Intent(this, DetailIntentActivity.class);
        intent.putExtra(Constain.KEY_TEXT_INPUT, input.getText().toString());
        intent.putExtra(Constain.KEY_HAS_RESULT, true);
        startActivityForResult(intent, REQUEST_DETAIL);
    }

    private void openDetailForResultNew() {
        Intent intent = new Intent(this, DetailIntentActivity.class);
        intent.putExtra(Constain.KEY_TEXT_INPUT, input.getText().toString());
        intent.putExtra(Constain.KEY_HAS_RESULT, true);
        activityResultLauncher.launch(intent);
    }

    private void openCall() {
//                Sử dụng Intent để hiển thị màn hình quay số:
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0123456789"));
        startActivity(intent);
    }

    private void openSms() {
//        Sử dụng Intent để gửi một tin nhắn đi
        Uri uri = Uri.parse("smsto:12346556");
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", "Here you can set the SMS text to be sent");
        startActivity(it);
    }

    private void openSearchGoogle() {
//                Sử dụng Intent để nhập một chuỗi trong ô tìm kiếm của Google:
        String escapedQuery = null;
        try {
            escapedQuery = URLEncoder.encode("Đại học Funix", "UTF-8");
            Uri uri = Uri.parse(" http://www.google.com/search?q=" + escapedQuery);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DETAIL) {
            if (resultCode == RESULT_OK) {
                updateInput(data);
            }
        }
    }

    private void updateInput(Intent data) {
        if (data != null) {
            String text = data.getStringExtra(Constain.KEY_TEXT_INPUT_DETAIL);
            input.setText(text);
            input.setSelection(input.getText().toString().length());
        }
    }
}
