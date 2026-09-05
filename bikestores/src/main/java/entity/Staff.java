package entity;

public class Staff {
    private int staff_id;
    private String name;
    private String role;
    private String email;
    private String phone;
    private int store_id;

    public Staff(){};

    public Staff(int id, String email, String name, String phone, String role, int store_id) {
        this.staff_id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.store_id = store_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Staff{" +
                ", staff_id=" + staff_id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", store_id=" + store_id +
                '}';
    }
}
