package entity;

public class Store {
    private int store_id;
    private String name;
    private String address;
    private String phone;

    public Store() {}

    public Store(String address, String name, String phone, int store_id) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.store_id = store_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Store{" +
                "store_id=" + store_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone +
                '}';
    }
}
