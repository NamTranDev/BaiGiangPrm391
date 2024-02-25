package nam.tran.baigiangprm391.exercise4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.Toast;

import nam.tran.baigiangprm391.R;


public class ServiceReceiver extends BroadcastReceiver {

    private static final String TAG = ServiceReceiver.class.getName();

    @Override

    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener() {

            @Override

            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                showEmoij(context, state);
            }

        }, PhoneStateListener.LISTEN_CALL_STATE);
    }



    private void showEmoij(Context context, int state) {
        int drawable = R.drawable.ic_incoming;
        if (state == TelephonyManager.CALL_STATE_IDLE) {
            drawable = R.drawable.ic_offhook;
        }
        Toast toast = new Toast(context);
        ImageView ivEmoij = new ImageView(context);
        ivEmoij.setMaxWidth(200);
        ivEmoij.setImageResource(drawable);
        toast.setView(ivEmoij);
        toast.show();
    }

}
