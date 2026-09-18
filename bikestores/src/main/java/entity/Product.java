package entity;

public class Product {
    private int product_id;
    private String name;
    private int category_id;
    private int brand_id;
    private double price;
    private String desc;

    public Product(){}

    public Product(int brand_id, int category_id, String name, double price, int product_id, String desc) {
        this.brand_id = brand_id;
        this.category_id = category_id;
        this.name = name;
        this.price = price;
        this.product_id = product_id;
        this.desc = desc;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand_id=" + brand_id +
                ", product_id=" + product_id +
                ", name='" + name + '\'' +
                ", category_id=" + category_id +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                '}';
    }
}

