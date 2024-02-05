import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class BasicPayrollSystem{
    // ADMINISTRATOR LOGIN CREDENTIALS
    private static String username = "admin";
    private static String password = "1234";

    // initializes fundamental global values
    private static boolean appRunning = true;
    private static boolean validInt = false;
    private static boolean validDepartment = false;
    private static Scanner sc = new Scanner(System.in);
    private static final String[] departments = {"Management", "Marketing", "Human Resource", "Finance", "IT"};
    private static ArrayList<String[]> employeeList = new ArrayList<>();

    // initializes data holders for new employee entrees
    private static String lastName;
    private static String firstName;
    private static String role;
    private static String department;
    private static String salary;
    
    // creates a breakline in the CLI
    private static void space() {
        System.out.println("");
    }

    // prompts user input to continue program
    private static void inputToContinue() {
        space();
        System.out.print("Press any key to continue...");
        sc.nextLine();
    }

    // clears console interface
    private static void clear() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // prompts app header
    private static void header() {
        System.out.println("Basic Payroll System");
        System.out.println("developed by Dash Adriano");
        System.out.println("-------------------------------------------------------");
    }

    // configures app banner for main app interfaces
    private static void banner() {
        clear();
        space();
        header();
        space();
    }
    
    // shows employees
    private static void showEmployees() {
        if (employeeList.isEmpty()) {
            banner();
            System.out.println("There are currently no employees in the system.");
            space();
            inputToContinue();
        } else {
            banner();
            System.out.println("Current number of employees: " + employeeList.size());
            space();
            int ID = 0;
            for (String[] employee : employeeList) {
                ID++;
                System.out.println("-------------------------------------------------------");
                space();
                System.out.println("ID: " + ID);
                System.out.println("Last name: " + employee[0]);
                System.out.println("First name: " + employee[1]);
                System.out.println("Position: " + employee[2]);
                System.out.println("Department: " + employee[3]);
                System.out.println("Salary: " + employee[4]);
                space();
                System.out.println("-------------------------------------------------------");
            }
            space();
            inputToContinue();
        }
    }

    // instantiates an employee instance, adds employee instance to `employeeList`
    private static void addEmployee(String newLastName, String newfirstName, String newRole, String newDepartment, String newSalary) {
        String[] employee = new String[5];
        employee[0] = newLastName;
        employee[1] = newfirstName;
        employee[2] = newRole;
        employee[3] = newDepartment;
        employee[4] = newSalary;
        employeeList.add(employee);
    }

    // removes an employee instance from `employeeList`
    private static void removeEmployee(int ID) {
        if (ID >= 0 || ID == employeeList.size()) {
            banner();
            String[] removedEmployee = employeeList.remove(ID);
            System.out.println("Employee removed.");
            System.out.println("Removed employee data:");
            space();
            System.out.println("Last name: " + removedEmployee[0]);
            System.out.println("First name: " + removedEmployee[1]);
            System.out.println("Position: " + removedEmployee[2]);
            System.out.println("Department: " + removedEmployee[3]);
            System.out.println("Salary: " + removedEmployee[4]);
            space();
            System.out.println("-------------------------------------------------------");
            space();
            System.out.println("If this was not the intended employee to be removed, \nplease write down the employee details of the recently \nremoved employee and re-add the employeeto the system.");
            space();
            System.out.println(" -> Consider using Option 1 [SHOW EMPLOYEES] to check \n    employee IDs before removal.");
            space();
            sc.nextLine();
        } else {
            System.out.println("No current employees exist with that ID.");
            space();
            sc.nextLine();
        }
    }

    private static int authenticate(String user, String pass) {
        if (user.equals(username) && pass.equals(password)) {
            return 0;
        } else {
            return 1;
        }

    }

    private static boolean validateInt(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean validateDepartment(String newDepartment) {
        for (String dept : departments) {
            if (newDepartment.toLowerCase().equals(dept.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private static int showOptions() {
        banner();
        int menuChoice;
        System.out.println("Menu Options");
        System.out.println(" 1 - Show employees ");
        System.out.println(" 2 - Add an employee ");
        System.out.println(" 3 - Remove an employee");
        System.out.println(" 4 - Exit program");
        space();

        try {
            System.out.println("What would you like to do?");
            System.out.print("> ");    
            menuChoice = sc.nextInt();
            sc.nextLine(); // resolves nextInt() input issues
            return menuChoice;
        } catch (InputMismatchException e) {
            space();
            System.out.println("Please input a valid option.");
            space();
            inputToContinue();
            sc.nextLine(); // resolves nextInt() input issues
            return 0;
        }
    }

    // main app instance
    private static void app() {
        // restarts global control variables
        validInt = false;
        validDepartment = false;

        banner();
        switch (showOptions()) {
            case 0:
                break;
            case 1:
                showEmployees();
                break;
            case 2:
                banner();
                System.out.println("NEW EMPLOYEE DATA FORM");
                System.out.print("Last name: ");
                lastName = sc.nextLine();
                System.out.print("First name: ");
                firstName = sc.nextLine();
                System.out.print("Position: ");
                role = sc.nextLine();

                while (!validDepartment) {
                    System.out.print("Department: ");
                    department = sc.nextLine(); 
                    validDepartment = validateDepartment(department);
                    if (!validDepartment) {
                        space();
                        System.out.println("Department does not exist.\nInput a valid department to continue.");
                        space();
                        inputToContinue();
                        banner();
                        System.out.println("continuing [NEW EMPLOYEE DATA FORM] process...");
                        space();
                    }
                }
                    
                while (!validInt) {
                    System.out.print("Monthly Salary: ");
                    salary = sc.nextLine();
                    validInt = validateInt(salary);
                    if (!validInt) {
                        space();
                        System.out.println("Please input a valid number.");
                        space();
                        inputToContinue();
                        banner();
                        System.out.println("continuing [NEW EMPLOYEE DATA FORM] process...");
                        space();
                    }
                }

                addEmployee(lastName, firstName, role, department, salary);
                banner();
                System.out.println("Employee added to database.");
                space();
                inputToContinue();
                break;
                
            case 3:
            if (employeeList.isEmpty()) {
                banner();
                System.out.println("There are currently no employees in the system.");
                space();
                inputToContinue();
                break;
            } else {
                banner();
                System.out.println("REMOVING EMPLOYEE");
                System.out.print("Enter employee ID: ");
                int removedEmployeeID = sc.nextInt() - 1;

                while (!validInt) {
                    try {
                        removeEmployee(removedEmployeeID);
                        space();
                        inputToContinue();
                        validInt = true;
                        break;
                    } catch (InputMismatchException e) {
                        banner();
                        System.out.println("Input invalid.");
                        space();
                        inputToContinue();
                        banner();
                        System.out.println("continuing [REMOVING EMPLOYEE] process...");
                        space();
                    }
                }
            }
            break;

            case 4:
                banner();
                System.out.println("Program terminated.");
                space();
                appRunning = false;
                break;

            default:
                space();
                System.out.println("Please input a valid option.");
                space();
                inputToContinue();
        }
        return;
    }
    
    // app logic stack
    public static void main(String[] args) {
        boolean authenticated = false;
        String user;
        String pass;

        while (!authenticated) {
            banner();
            System.out.println("Administrator Login");
            System.out.print("username: ");
            user = sc.nextLine();
            System.out.print("password: ");
            pass = sc.nextLine();

            switch (authenticate(user, pass)) {
                case 0:
                    authenticated = true;
                    break;
                case 1:
                    space();
                    System.out.println("Invalid username or password! Enter valid credentials to continue.");
                    inputToContinue();
                    break;
            }
        }

        while (appRunning) {
            app();
        }
    }
}
