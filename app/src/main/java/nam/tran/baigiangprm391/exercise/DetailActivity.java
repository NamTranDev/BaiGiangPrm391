package nam.tran.baigiangprm391.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nam.tran.baigiangprm391.R;

public class DetailActivity extends AppCompatActivity {

    private static final int BOCAP = 1;
    private static final int XUNU = 2;
    private static final int THIENBINH = 3;
    private static final int BAOBINH = 4;
    private static final int KIMNGUU = 5;
    private static final int MAKET = 6;
    private static final int NHANMA = 7;
    private static final int SONGNGU = 8;
    private static final int BACHDUONG = 9;
    private static final int CUGIAI = 10;
    private static final int SONGTU = 11;
    private static final int SUTU = 12;

    private static final int TEXT_REQUEST = 1;

    Button btnback;
    ImageView ivAvatar;
    TextView tvContent,tvTitle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnback=findViewById(R.id.btnback);
        ivAvatar = findViewById(R.id.ivAvatar);
        tvContent = findViewById(R.id.tvContent);
        tvTitle1 = findViewById(R.id.tvTitle1);

        Intent intent = getIntent();
        int message = intent.getIntExtra("main",0);
        writeDetail(message);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }

    public void writeDetail(int signal){
        switch (signal){
            case BOCAP:
                ivAvatar.setImageResource(R.drawable.ic_bocap);
                tvTitle1.setText(R.string.bo_cap_title1);
                tvContent.setText(R.string.bo_cap_text);
                break;
            case XUNU:
                ivAvatar.setImageResource(R.drawable.ic_xu_nu);
                tvTitle1.setText(R.string.xu_nu_title1);
                tvContent.setText(R.string.xu_nu_text);
                break;
            case THIENBINH:
                ivAvatar.setImageResource(R.drawable.ic_thien_binh);
                tvTitle1.setText(R.string.thien_binh_title1);
                tvContent.setText(R.string.thien_binh_text);
                break;
            case BAOBINH:
                ivAvatar.setImageResource(R.drawable.ic_bao_binh);
                tvTitle1.setText(R.string.bao_binh_title1);
                tvContent.setText(R.string.bao_binh_text);
                break;
            case KIMNGUU:
                ivAvatar.setImageResource(R.drawable.ic_kim_nguu);
                tvTitle1.setText(R.string.kim_nguu_title1);
                tvContent.setText(R.string.kim_nguu_text);
                break;
            case MAKET:
                ivAvatar.setImageResource(R.drawable.ic_ma_ket);
                tvTitle1.setText(R.string.ma_ket_title1);
                tvContent.setText(R.string.ma_ket_text);
                break;
            case NHANMA:
                ivAvatar.setImageResource(R.drawable.ic_nhan_ma);
                tvTitle1.setText(R.string.nhan_ma_title1);
                tvContent.setText(R.string.nhan_ma_text);
                break;
            case SONGNGU:
                ivAvatar.setImageResource(R.drawable.ic_song_ngu);
                tvTitle1.setText(R.string.song_ngu_title1);
                tvContent.setText(R.string.song_ngu_text);
                break;
            case BACHDUONG:
                ivAvatar.setImageResource(R.drawable.ic_bach_duong);
                tvTitle1.setText(R.string.bach_duong_title1);
                tvContent.setText(R.string.bach_duong_text);
                break;
            case CUGIAI:
                ivAvatar.setImageResource(R.drawable.ic_cu_giai);
                tvTitle1.setText(R.string.cu_giai_title1);
                tvContent.setText(R.string.cu_giai_text);
                break;
            case SONGTU:
                ivAvatar.setImageResource(R.drawable.ic_song_tu);
                tvTitle1.setText(R.string.song_tu_title1);
                tvContent.setText(R.string.song_tu_text);
                break;
            case SUTU:
                ivAvatar.setImageResource(R.drawable.ic_su_tu);
                tvTitle1.setText(R.string.su_tu_title1);
                tvContent.setText(R.string.su_tu_text);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==TEXT_REQUEST){
            if(requestCode==RESULT_OK){
                int signal =data.getIntExtra(Exercise3Activity.EXTRA_DATA_CODE,0);
                writeDetail(signal);
            }
        }
    }
}