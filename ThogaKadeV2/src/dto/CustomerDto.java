package dto;

public class CustomerDto {
    private String id;
    private String name;
    private String address;

    public CustomerDto() {
    }

    public CustomerDto(String id, String name, String address, Double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        Salary = salary;
    }

    private Double Salary;

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
}
