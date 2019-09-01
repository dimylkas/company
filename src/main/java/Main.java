import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    private static Company[] systemCompanies = new Company[0];

    public static void main(String[] args) throws IOException {

        Company GloLo = new Company("GloLo", "Some address", "044");

        Employee newEmp = new Employee("A B", "001", 100);
        Employee newEmp2 = new Employee("C D", "002", 200);
        Employee newEmp3 = new Employee("E F", "003", 250);
        Employee newEmp4 = new Employee("G H", "004", 150);
        Employee newEmp5 = new Employee("I J", "005", 500);

        GloLo.addEmployee(newEmp5);
        GloLo.addEmployee(newEmp);
        GloLo.addEmployee(newEmp2);
        GloLo.addEmployee(newEmp3);
        GloLo.addEmployee(newEmp3);


        Main.printCompanyData(GloLo);

        GloLo.fireAnEmployee(newEmp);
        GloLo.fireAnEmployee(newEmp4);
        GloLo.fireAnEmployee(newEmp5);

        Main.printCompanyData(GloLo);

        Main.startConversation();
    }


    public static void printCompanyData(Company company) {
        String name = company.getCompanyName();
        String address = company.getAddress();
        String phone = company.getPhone();
        long minSalary = company.getMinSalary();

        Employee[] employees = company.getEmployees();

        System.out.println("Company Name - " + name);
        System.out.println("Company Address - " + address);
        System.out.println("Company Phone - " + phone);
        System.out.println("Company MinSalary - " + minSalary);
        System.out.println("Company Average Salary - " + company.getAverageSalary());
        System.out.println("Company overpaid guy - " + company.findEmployeeWithMaxSalary().getName());

        System.out.println("Company Employees: ");
        for (Employee e : employees) {
            System.out.println("Name: " + e.getName() + "; Phone: " + e.getPhone() + "; Salary: " + e.getSalary() + " |");
        }
    }

    public static void startConversation() throws IOException {
        System.out.println("------------------------------------------------------");
        System.out.println("Hello, please select option what would you like to do:");
        System.out.println("To add the new company - Enter 1");
        System.out.println("To show company list - Enter 2");
        System.out.println("To add an Employee to Company - Enter 3");

        String answer = bf.readLine();

        System.out.println(answer);

        if (answer.contains("1")) {
            Main.addCompany();
        }

        if (answer.contains("2")) {
            Main.showCompanyList();
        }

        if (answer.contains("3")) {
            Main.AddEmployeeToCompany();
        }

        Main.startConversation();
    }

    public static void addCompany() throws IOException {
        System.out.println("------------------------------------------------------");
        System.out.println("Please enter company name: ");

        String name = bf.readLine();

        System.out.println("Please enter company address: ");
        String address = bf.readLine();
        System.out.println("Please enter company phone: ");
        String phone = bf.readLine();
        System.out.println("Please enter company minSalary: ");

        long minSalary = 0L;
        try {
            minSalary = Long.parseLong(bf.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid text, should be a number!");
        }

        Company co = new Company(name, address, phone, minSalary);

        Main.addCompanyToList(co);
    }

    public static void AddEmployeeToCompany() throws IOException {
        System.out.println("------------------------------------------------------");
        System.out.println("Please enter name: ");
        String name = bf.readLine();

        System.out.println("Please enter phone: ");
        String phone = bf.readLine();

        System.out.println("Please enter salary: ");
        long salary = 0L;
        try {
            salary = Long.parseLong(bf.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid text, should be a number!");
        }

        Employee em = new Employee(name, phone, salary);
        int companyIndex = Main.SelectCompanyIndex();

        Main.addEmployeeToCompany(systemCompanies[companyIndex - 1], em);
    }

    private static int SelectCompanyIndex() throws IOException {
        System.out.println("Please select company: ");
        Main.showCompanyList();
        int companyIndex = 0;
        try {
            companyIndex = Integer.parseInt(bf.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid text, should be a number!");
        }

        if (companyIndex < systemCompanies.length || companyIndex > systemCompanies.length) {
            System.out.println("No such company. Would you like to repeat your choice (y/n)?");
            String answer = bf.readLine();
            if (answer.equals("y")) {
                return Main.SelectCompanyIndex();
            }
        }

        return companyIndex;
    }

    private static boolean addEmployeeToCompany(Company co, Employee em) {
        co.addEmployee(em);

        printCompanyData(co);
        return co.addEmployee(em);
    }

    private static boolean addCompanyToList(Company co) {
        Company[] newCo = new Company[systemCompanies.length + 1];

        for (int i = 0; i < systemCompanies.length; i++) {
            newCo[i] = systemCompanies[i];
        }

        newCo[systemCompanies.length] = co;

        systemCompanies = newCo;

        return true;
    }

    private static void showCompanyList() {
        if (systemCompanies.length <= 0) {
            System.out.println("No companies found. Please add a new company first.");
            return;
        }

        for (int i = 0; i < systemCompanies.length; i++) {
            System.out.println(i +1 + ". " + systemCompanies[i].getCompanyName());
        }
    }
}
