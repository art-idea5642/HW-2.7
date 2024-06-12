package com.coursework.EmployeeBook.service;

import com.coursework.EmployeeBook.dto.Employee;
import com.coursework.EmployeeBook.exceptions.EmployeeAlreadyAddedException;
import com.coursework.EmployeeBook.exceptions.EmployeeNotFoundException;
import com.coursework.EmployeeBook.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    private final List<Employee> employees;
    private static final int MAX_EMPLOYEES = 100;

    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
        for (Employee value : employees) {
            if (value.getFirstName().equals(firstName) &&
                    value.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        return employee;
    }

    public Employee deleteEmployee(String firstName, String lastName) {
        Employee result = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) &&
                    employees.get(i).getLastName().equals(lastName)) {
                result = employees.get(i);
                employees.remove(i);
                return result;
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
