package nam.tran.baigiangprm391.asm1;

/**
 * co 3 bien so : (icon) con vat , (icon) trai tim va trang thai (icon) trai tim da duoc clicked chua
 *
 */
public class Animal {  /// thiet ke doi tuong the hien 2 yeu to: (icon) con vat + (icon) trai tim
    private int img;
    private int ic;
    private boolean isSelected = false; // ban dau chua chon => cho gia tri tim ben canh icon ko hien
    public Animal(int img,int ic){
        this.img = img;
        this.ic = ic;
    }
    public int getImg() {
        return img;
    }
    public void setImg(int img) {
        this.img = img;
    }
    public int getIc() {
        return ic;
    }
    public void setIc(int ic) {
        this.ic = ic;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
