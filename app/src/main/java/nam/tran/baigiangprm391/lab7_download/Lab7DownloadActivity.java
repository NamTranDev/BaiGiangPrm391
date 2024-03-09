package nam.tran.baigiangprm391.lab7_download;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import nam.tran.baigiangprm391.R;

public class Lab7DownloadActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtLink;
    private ProgressBar progressBar;
    private int size = 0;
    private Button btOpen;
    private String savePath;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab7_download);
        initViews();
    }

    private void initViews() {

        progressBar = findViewById(R.id.progress_bar);

        edtLink = findViewById(R.id.edt_link);

        findViewById(R.id.bt_download).setOnClickListener(this);

        btOpen = findViewById(R.id.bt_open);
        btOpen.setEnabled(false);
        btOpen.setOnClickListener(this);
    }


    // phương thức xử lý click on button
    @Override

    public void onClick(View v) {
        count = 0;
        if (!checkPermission()) return;

        if (v.getId() == R.id.bt_download) {

            downloadFile(edtLink.getText().toString());
        } else if (v.getId() == R.id.bt_open) {
            openFile();
        }
    }


    // phương thức để kiểm tra quyền người dùng
    private boolean checkPermission() {
        boolean isAllow = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if (!isAllow) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 101);
        }
        return isAllow;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                downloadFile(edtLink.getText().toString());
            }else {
                if (count == 0){
                    count+= 1;
                    Toast.makeText(this, R.string.txt_alert, Toast.LENGTH_SHORT).show();
                    gotoSettings();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111){
            if (checkPermission()){
                downloadFile(edtLink.getText().toString());
            }
        }
    }

    private void gotoSettings() {
        new AlertDialog.Builder(this)
                .setTitle("Need Permission")
                .setMessage("Accept permission in setting")
                .setCancelable(false)
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    try {
                        dialogInterface.dismiss();
                        Intent myAppSettings = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                        myAppSettings.setData(Uri.parse("package:" + getPackageName()));
                        startActivityForResult(myAppSettings, 111);
                    } catch (Exception e) {
                        Toast.makeText(Lab7DownloadActivity.this, "failed to open Settings\n" + e, Toast.LENGTH_LONG).show();
                        Log.d("error", e.toString());
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create().show();
    }

    // Phương thức để open file sau download
    private void openFile() {
        if (savePath == null || savePath.isEmpty()) return;
        File file = new File(savePath);
        Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
        String mime = getContentResolver().getType(uri);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, mime);
        //FRAG_GRANT_READ_URI_PERMISSION là để khai báo thêm cờ báo hiệu đã được sự đồng ý để
        //đọc dữ liệu của đường dẫn uri
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }


    // phương thức để download file
    private void downloadFile(String link) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URLConnection conn = new URL(link).openConnection();
                    InputStream in = conn.getInputStream();
                    savePath = getExternalFilesDir(null).getPath() + "/" + new File(link).getName();
                    FileOutputStream out = new FileOutputStream(new File(savePath));
                    byte[] buff = new byte[1024];
                    int len = in.read(buff);
                    runOnUiThread(() -> {
                        progressBar.setMax(conn.getContentLength());
                        progressBar.setProgress(0);
                        btOpen.setEnabled(false);
                    });
                    size = 0;
                    while (len > 0) {
                        out.write(buff, 0, len);
                        size += len;
                        runOnUiThread(() -> progressBar.setProgress(size));
                        len = in.read(buff);
                    }
                    out.close();
                    in.close();

                    runOnUiThread(() -> btOpen.setEnabled(true));
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(Lab7DownloadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                }
            }
        }.start();
    }
}