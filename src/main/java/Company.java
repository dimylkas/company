
import com.sun.tools.javac.util.ArrayUtils;

import java.util.Arrays;

public class Company {

    private String companyName;
    private String address = "Unknown address";
    private String phone = "Unknown phone";
    private long minSalary = 0L;
    private Employee[] employees = new Employee[0];

    enum SearchEmployeeFields {
        Name,
        Email,
        All,
    }

    public Company(String companyName) {
        this(companyName, "");
    }

    public Company(String companyName, String address) {
        this(companyName, address, "");
    }

    public Company(String companyName, String address, String phone) {
        this(companyName, address, phone, 0L);
    }

    public Company(String companyName, String address, String phone, long minSalary) {

        if (companyName.isEmpty()) {
            throw new Error("Company name is required");
        }

        this.companyName = companyName;

        if (!address.isEmpty()) {
            this.address = address;
        }

        if (!phone.isEmpty()) {
            this.phone = phone;
        }

        if (minSalary < 0) {
            throw new Error("Minimum salary should be greater than 0");
        }

        this.minSalary = minSalary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(long minSalary) {
        this.minSalary = minSalary;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public boolean addEmployee(Employee newEmployee) {
        if (newEmployee.getSalary() < 0) {
            System.out.println("Salary could't be less than 0");
            return false;
        }

        if (newEmployee.getSalary() < this.minSalary) {
            System.out.println("Salary could't be less than minimum salary");
            return false;
        }

        if (this.isEmployeeExist(newEmployee)) {
            System.out.println("Already working at the company");
            return false;
        }

        Employee[] newEmployees = new Employee[this.employees.length + 1];

        newEmployee.setEmail(this.generateEmployeeEmail(newEmployee));

        for (int i = 0; i < this.employees.length; i++) {
            newEmployees[i] = this.employees[i];
        }

        newEmployees[this.employees.length] = newEmployee;

        this.employees = newEmployees;

        return true;
    }

    private String generateEmployeeEmail(Employee e) {
        return e.getName() + "@" + companyName.substring(0, 3) + "com";
    }

    private String generateEmployeeEmail(Employee e, int number) {
        return e.getName() + number + "@" + companyName.substring(0, 3) + "com";
    }

    private boolean isEmployeesEquals(Employee firstE, Employee secondE) {
        // && firstE.getEmail().equals(secondE.getEmail())
        return firstE.getName().equals(secondE.getName());
    }

    private boolean isEmployeeExist(Employee employeeToFire) {
        for (Employee e : this.employees) {
            if (this.isEmployeesEquals(e, employeeToFire)) {
                return true;
            }
        }

        return false;
    }

    public Employee findEmployeeByName(String name) {
        return this.findEmployeeByField(SearchEmployeeFields.Name, name);
    }

    public Employee findEmployeeByEmail(String email) {
        return this.findEmployeeByField(SearchEmployeeFields.Email, email);
    }

    private Employee findEmployeeByField(SearchEmployeeFields searchField, String searchText) {
        for (Employee e : this.employees) {
            if (searchField.equals(searchField.Name)) {
                if (e.getName().equals(searchText)) {
                    return e;
                }
            }

            if (searchField.equals(searchField.Email)) {
                if (e.getEmail().equals(searchText)) {
                    return e;
                }
            }
        }

        System.out.println("Couldn't find the employee");
        return null;
    }

    public boolean fireAnEmployee(Employee employeeToFire) {
        if (!(this.employees.length > 0)) {
            System.out.println("No employees found");
            return false;
        }

        if (!this.isEmployeeExist(employeeToFire)) {
            System.out.println(employeeToFire.getName() + " isn't working t the company");
            return false;
        }

        Employee[] newEmployees = new Employee[this.employees.length - 1];

        for (int i = 0, j = 0; i < this.employees.length; i++) {
            if (!this.isEmployeesEquals(this.employees[i], employeeToFire)) {
                newEmployees[j] = this.employees[i];
                j++;
            }
        }

        this.employees = newEmployees;

        return true;
    }

    public Employee findEmployeeWithMaxSalary() {
        if (!(this.employees.length > 0)) {
            System.out.println("No employees at this company");
            return null;
        }

        Employee overpaidEmployee = this.employees[0];
        for (Employee e : this.employees) {
            if (e.getSalary() > overpaidEmployee.getSalary()) {
                overpaidEmployee = e;
            }
        }

        return overpaidEmployee;
    }

    public long getAverageSalary() {
        if (this.employees.length == 0) {
            return 0;
        }

        long salarySum = 0L;

        for (Employee e : this.employees) {
            salarySum += e.getSalary();
        }

        return salarySum / this.employees.length;
    }
}
