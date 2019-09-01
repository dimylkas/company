
public class Employee {
    private String name;
    private String email;
    private String phone;
    private long salary;

    public Employee(String name) {
        this(name, "");
    }

    public Employee(String name, String phone) {
        this(name, phone, 0L);
    }

    public Employee(String name, String phone, long salary) {
        this.name = name;
        this.phone = phone;
        this.salary = salary;
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

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
