package nam.tran.baigiangprm391.asm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import nam.tran.baigiangprm391.R;

public class Asm1Activity extends AppCompatActivity {
    Toolbar toolbarMain;
    GridView gridView;
    String name,text;
    ArrayList<Animal> arrayListAnimals;
    AnimalAdapter animalAdapter;
    ImageView imageView;
    int type, position;
    boolean fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asm1);

        toolbarMain = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbarMain);

        gridView = (GridView) findViewById(R.id.gridViewAnimal);
        arrayListAnimals = new ArrayList<>();

        addAnimal();

        animalAdapter = new AnimalAdapter(this,R.layout.list_animals,arrayListAnimals);
        gridView.setAdapter(animalAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int vitri, long id) {
                // lam mo khi click vao con vat
                view.startAnimation(AnimationUtils.loadAnimation(Asm1Activity.this,androidx.appcompat.R.anim.abc_fade_in));
                Animal animal = arrayListAnimals.get(vitri);
                switch(vitri){
                    case 0: {
                        type = R.drawable.bg_elephant;
                        fav = animal.isSelected();
                        name = getString(R.string.elephant);
                        text = getString(R.string.txt_elephant);
                        showDetails(type,fav,name,text);
                    }
                    break;
                    case 1: {
                        type = R.drawable.bg_dragonfly;
                        fav = animal.isSelected();
                        name = getString(R.string.dragonfly);
                        text = getString(R.string.txt_dragonfly);
                        showDetails(type,fav,name,text);
                    }
                    break;
                    case 2: {
                        type = R.drawable.bg_dolphin;
                        fav = animal.isSelected();
                        name = getString(R.string.dolphin);
                        text = getString(R.string.txt_dolphin);
                        showDetails(type,fav,name,text);
                    }
                    break;
                    case 3: {
                        type = R.drawable.bg_dog;
                        fav = animal.isSelected();
                        name = getString(R.string.dog);
                        text = getString(R.string.txt_dog);
                        showDetails(type,fav,name,text);
                    }
                    break;
                    case 4: {
                        type = R.drawable.bg_pig;
                        fav = animal.isSelected();
                        name = getString(R.string.pig);
                        text = getString(R.string.txt_pig);
                        showDetails(type,fav,name,text);
                    }
                    break;
                    case 5: {
                        type = R.drawable.bg_goose;
                        fav = animal.isSelected();
                        name = getString(R.string.goose);
                        text = getString(R.string.txt_goose);
                        showDetails(type,fav,name,text);
                    }
                    break;
                    case 6: {
                        type = R.drawable.bg_ladybug;
                        fav = animal.isSelected();
                        name = getString(R.string.ladybug);
                        text = getString(R.string.txt_ladybug);
                        showDetails(type,fav,name,text);
                    }
                    break;
                    case 7: {
                        type = R.drawable.bg_turtle;
                        fav = animal.isSelected();
                        name = getString(R.string.turtle);
                        text = getString(R.string.txt_turtle);
                        showDetails(type,fav,name,text);
                    }
                    break;
                    case 8: {
                        type = R.drawable.bg_penguin;
                        fav = animal.isSelected();
                        name = getString(R.string.penguin);
                        text = getString(R.string.txt_penguin);
                        showDetails(type,fav,name,text);
                    }
                    break;
                }
                position = vitri;

                //hieu ung animation
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
            }
        });
    }

    // khi click vao vi tri con vat => chuyen sang man hinh detail tuong ung
    private void showDetails(int type,boolean fav,String name,String text){
        Intent intent = new Intent(Asm1Activity.this,DetailActivity.class);  // chuyen du lieu tu main -> detail
        // dong goi 1 bundle -> cho vo intent -> chuyen du lieu sang man hinh detail
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE",type);
        bundle.putBoolean("FAV",fav);
        bundle.putString("NAME",name);
        bundle.putString("TEXT",text);
        intent.putExtras(bundle);
        startActivityForResult(intent,101); // co phan hoi tu activity duoc goi
    }

    // xu li xu kien da click tim hay chua neu click tim roi => hien trai tim do con khong thi set gia tri 0(ko hien thi
    // (icon do)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data == null){ // neu du lieu null => tra ve (ket thuc) ham luon
            return;
        }
        Animal animal = arrayListAnimals.get(position);
        animal.setSelected(data.getBooleanExtra("isOn",false));
        if(animal != null){
            if(animal.isSelected()){   // neu nhu da duoc click => set trang thai tim
                animal.setIc(R.drawable.ic_favorite2);
            }else{   // con neu chua duoc click => cho no gia tri 0
                animal.setIc(0);
            }
        }

        arrayListAnimals.set(position,animal);  // set up con vat vao vi tri tuong ung
        animalAdapter.notifyDataSetChanged();  /// khi noi dung bi thay doi => goi notifyData => update lai dât
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void addAnimal(){
        arrayListAnimals.add(new Animal(R.drawable.ic_elephant,0));  // ban dau ic:0 => trai tim an vi chua bam
        arrayListAnimals.add(new Animal(R.drawable.ic_dragonfly,0));
        arrayListAnimals.add(new Animal(R.drawable.ic_dolphin,0));
        arrayListAnimals.add(new Animal(R.drawable.ic_dog,0));
        arrayListAnimals.add(new Animal(R.drawable.ic_pig,0));
        arrayListAnimals.add(new Animal(R.drawable.ic_goose,0));
        arrayListAnimals.add(new Animal(R.drawable.ic_bug,0));
        arrayListAnimals.add(new Animal(R.drawable.ic_turtle,0));
        arrayListAnimals.add(new Animal(R.drawable.ic_penguin,0));
    }
}