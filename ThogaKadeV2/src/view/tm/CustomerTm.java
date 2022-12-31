package view.tm;

import javafx.scene.control.Button;

public class CustomerTm {
    private String id;
    private String name;
    private String address;
    private Double Salary;

    public CustomerTm() {
    }

    public CustomerTm(String id, String name, String address, Double salary, Button btn) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        setSalary(salary);
        this.setBtn(btn);
    }

    private Button btn;

    public CustomerTm(String id, String name, String address, Double salary) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        setSalary(salary);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
