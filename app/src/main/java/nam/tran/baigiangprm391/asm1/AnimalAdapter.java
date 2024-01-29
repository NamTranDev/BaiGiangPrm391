package nam.tran.baigiangprm391.asm1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import nam.tran.baigiangprm391.R;

public class AnimalAdapter extends BaseAdapter {  // set up nhung con vat duoc lap lai nhu nao tu layout detail cho tat ca cac con vat
    // => bo dem match vs list_animal=> khai bao ve list view
    private Context context;
    private int layout;

    public AnimalAdapter(Context context, int layout, List<Animal> animalList) {
        this.context = context;
        this.layout = layout;
        this.animalList = animalList;
    }

    private List<Animal> animalList;  // luu danh sach con vat
    @Override
    public int getCount() { // return so phan tu co trong danh sach list view
        return animalList.size();
    }
    // tra ve vi tri cua icon con vat ma ta click
    @Override
    public Object getItem(int i) {
        return null;
    }
    //tra ve mot ID lien quan den phan tu o vi tri position
    @Override
    public long getItemId(int i) {
        return 0;
    }
    // anh xa tu list_animals => anh xa 2 yeu to imgView va icon(tim) => tra ra gia tri view tuong ung o getView ben duoi
    private class ViewHolder{
        ImageView imageView, icon;
    }
    // gan du lieu vi tri position -> View => return View
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){  // neu view con vat null => set layout cho no
            viewHolder = new ViewHolder();
            // khoi tao layout => lay ra du lieu hien thi thong tin con vat tuong ung
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);

            // anh xa view thong qua viewHolder
            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
            viewHolder.icon = (ImageView) view.findViewById(R.id.imageIcon);

            view.setTag(viewHolder); /// set cac gia tri va setTag khi chua co gia tri view
        }else{  // neu co giu lieu view roi  ==> gan gia tri getTag cho viewHolder
            viewHolder = (ViewHolder) view.getTag();
        }
        // gan gia tri theo position tuong ung
        Animal animal = animalList.get(position);
        viewHolder.imageView.setImageResource(animal.getImg()); // gan gia tri imageView
        viewHolder.icon.setImageResource(animal.getIc());  // get icon trai tim tuong ung

        //gan animation mo cho icon o man hinh 1
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_alpha); // hieu ung lam mo
        view.startAnimation(animation);

        return view;
    }
}
