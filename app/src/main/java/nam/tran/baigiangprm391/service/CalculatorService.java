package nam.tran.baigiangprm391.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalculatorService extends Service {

    private final IBinder binder = new CalculatorBinder();

    public class CalculatorBinder extends Binder {
        CalculatorService getService() {
            return CalculatorService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int addNumbers(int a, int b) {
        return a + b;
    }
}
