package nam.tran.baigiangprm391.asm2;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import nam.tran.baigiangprm391.R;

public class DetailAnimalAdapter extends PagerAdapter {

    private static String TAG = DetailAnimalAdapter.class.getName();
    private Context mContext;
    private List<Animal> listAnimal;

    public DetailAnimalAdapter(List<Animal> listAnimal, Context mContext) {
        this.listAnimal = listAnimal;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_animal, container, false);
        Animal item = listAnimal.get(position);

        ImageView iv_bg = view.findViewById(R.id.iv_animal_dtl_background);
        TextView tv_name = view.findViewById(R.id.tv_animal_dtl_name);
        TextView tv_description = view.findViewById(R.id.tv_animal_dtl_text);
        ImageView iv_fav = view.findViewById(R.id.iv_fav);

        if (!item.isFav()) {
            iv_fav.setImageLevel(0);
        } else if (item.isFav()) {
            iv_fav.setImageLevel(1);
        }
        iv_fav.setOnClickListener(view1 -> {
            if (!item.isFav()) {
                iv_fav.setImageLevel(1);
                item.setFav(true);
            } else if (item.isFav()) {
                iv_fav.setImageLevel(0);
                item.setFav(false);
            }
        });

        iv_bg.setImageBitmap(item.getPhotoBg());
        tv_name.setText(item.getName());
        tv_description.setText(item.getContent());

        container.addView(view);
        return view;
    }

    ;


    @Override
    public int getCount() {
        return listAnimal.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
