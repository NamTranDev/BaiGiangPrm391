package nam.tran.baigiangprm391.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.R;

public class ServiceActivity extends AppCompatActivity {

    RadioButton startService,boundService;
    EditText inputA,inputB;
    TextView text;
    private CalculatorService calculatorService;
    private boolean isBound = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        startService = findViewById(R.id.rb_start_service);
        boundService = findViewById(R.id.rb_bound_service);
        inputA = findViewById(R.id.input_a);
        inputB = findViewById(R.id.input_b);
        text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumbers();
            }
        });
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputA.setVisibility(View.GONE);
                inputB.setVisibility(View.GONE);
                text.setText("Đây là Start Service");
            }
        });
        boundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputA.setVisibility(View.VISIBLE);
                inputB.setVisibility(View.VISIBLE);
                text.setText("Tinh toan ket qua");
            }
        });
        findViewById(R.id.startButton).setOnClickListener(view -> {
            if (startService.isChecked()){
                startService(new Intent( this, RingToneService.class ) );
            }else if (boundService.isChecked()){
                connectToService();
            }
        });
        findViewById(R.id.stopButton).setOnClickListener(view -> {
            if (startService.isChecked()){
                stopService(new Intent( this, RingToneService.class ) );
            }else if (boundService.isChecked()){
                disconnectFromService();
            }
        });
    }

    public void connectToService() {
        Intent intent = new Intent(this, CalculatorService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    // Khi người dùng nhấn nút "Add Numbers"
    public void addNumbers() {
        if (isBound && calculatorService != null) {

            String textA = inputA.getText().toString();
            String textB = inputB.getText().toString();

            if (textA.isEmpty() || textB.isEmpty()){
                Toast.makeText(this,"Nhap so can tinh vao input",Toast.LENGTH_SHORT).show();
                return;
            }

            int a = Integer.parseInt(textA);
            int b = Integer.parseInt(textB);

            int result = calculatorService.addNumbers(a, b);
            text.setText("Kết quả : " + result);
        }else{
            Toast.makeText(this,"Bạn phải start bound service",Toast.LENGTH_SHORT).show();
        }
    }

    // Khi người dùng nhấn nút "Disconnect from Service"
    public void disconnectFromService() {
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    // Service Connection để quản lý kết nối với Service
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CalculatorService.CalculatorBinder binder = (CalculatorService.CalculatorBinder) iBinder;
            calculatorService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}
