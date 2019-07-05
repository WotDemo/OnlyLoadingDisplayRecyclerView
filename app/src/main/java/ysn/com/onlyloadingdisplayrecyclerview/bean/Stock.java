package ysn.com.onlyloadingdisplayrecyclerview.bean;

/**
 * @Author yangsanning
 * @ClassName Stock
 * @Description 一句话概括作用
 * @Date 2019/7/5
 * @History 2019/7/5 author: description:
 */
public class Stock {

    private String name;
    private double price;
    private double percent;

    public Stock(String name, double price, double percent) {
        this.name = name;
        this.price = price;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
