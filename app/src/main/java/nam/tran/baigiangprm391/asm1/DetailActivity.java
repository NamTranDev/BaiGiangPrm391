package nam.tran.baigiangprm391.asm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import nam.tran.baigiangprm391.R;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageViewDetail,imgHeart;
    TextView textName,textInfo;
    boolean fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // de toolbar co CN nhu actionBar
        toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // tim lai id
        imageViewDetail = (ImageView) findViewById(R.id.imageViewDetail);
        imgHeart = (ImageView) findViewById(R.id.imageHeart);
        textName = (TextView) findViewById(R.id.textViewName);
        textInfo = (TextView) findViewById(R.id.textViewInfo);

        //nhan intent tu MainActivity.class
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){  // neu ma du lieu gui sang != null => set thong tin
            int img = bundle.getInt("TYPE");
            String name = bundle.getString("NAME");
            String info = bundle.getString("TEXT");

            fav = getIntent().getBooleanExtra("FAV",false); // vi ban dau chua bam => default = false
            imageViewDetail .setImageResource(img);
            textName.setText(name);
            textInfo.setText(info);
        }
        setHeart();

        if(fav){  // neu ma trai tim duoc click => set icon: trai tim dam
            imgHeart.setImageResource(R.drawable.ic_favorite2);
        }else{   // neu khong thi de source trai tim nhu ban dau
            imgHeart.setImageResource(R.drawable.ic_favorite1);
        }

    }
    private void setHeart(){
        imgHeart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // animation
                view.startAnimation(AnimationUtils.loadAnimation(DetailActivity.this,androidx.appcompat.R.anim.abc_fade_in));

                if(fav){   // neu click vo trai tim da tim roi => chua duoc tim va chuyen fav = false
                    imgHeart.setImageResource(R.drawable.ic_favorite1);
                    fav = false;
                } else{   // con neu chua duoc click => chuyen sang tim da tim va fav = true
                    imgHeart.setImageResource(R.drawable.ic_favorite2);
                    fav = true;
                }
                Intent intent = new Intent(DetailActivity.this, Asm1Activity.class);
                intent.putExtra("isOn",fav);  // chuyen du lieu fav sang de biet tim da duoc tim hay chua
                setResult(RESULT_OK,intent);
            }
        });
    }
    /// trang thai back ve
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                //animation
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}