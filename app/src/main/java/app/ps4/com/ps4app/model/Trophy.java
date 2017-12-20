package app.ps4.com.ps4app.model;



public class Trophy {
    private String name;
    private String image;
    private int bronze;
    private int silver;
    private int gold;
    private int total;

    public Trophy() {
    }

    public Trophy(String name, String image, int bronze, int silver, int gold, int total) {
        this.name = name;
        this.image = image;
        this.bronze = bronze;
        this.silver = silver;
        this.gold = gold;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
