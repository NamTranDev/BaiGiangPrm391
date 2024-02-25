package nam.tran.baigiangprm391.permission;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.R;

public class NormalPermissionActivity extends AppCompatActivity {

    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_permission);

        // Khởi tạo PowerManager
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

        // Tạo WakeLock với quyền PARTIAL_WAKE_LOCK
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "MyWakeLockTag");

        // Bật WakeLock
        if (wakeLock != null && !wakeLock.isHeld()) {
            wakeLock.acquire(10  * 60 * 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Giải phóng WakeLock khi ứng dụng bị hủy
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
    }
}
