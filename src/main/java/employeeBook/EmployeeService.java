package employeeBook;

import java.util.List;

public class EmployeeService {
    private final List<Employee> employees;
    private static final int MAX_EMPLOYEES = 100;

    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Много сотрудников");
        }
        for (Employee value : employees) {
            if (value.getFirstName().equals(firstName) &&
                    value.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен!");
            }
        }
        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        return employee;
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) &&
                    employees.get(i).getLastName().equals(lastName)) {
                employees.remove(i);
                return employees.get(i);
            }
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) &&
                    employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getAll (){
        return employees;
    }
}
