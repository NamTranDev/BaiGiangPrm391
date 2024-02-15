package nam.tran.baigiangprm391.recyclerview;

public class Country {
    private String name;
    private int flagImageResource;

    public Country(String name, int flagImageResource) {
        this.name = name;
        this.flagImageResource = flagImageResource;
    }

    public String getName() {
        return name;
    }

    public int getFlagImageResource() {
        return flagImageResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlagImageResource(int flagImageResource) {
        this.flagImageResource = flagImageResource;
    }
}

