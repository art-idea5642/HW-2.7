package com.coursework.EmployeeBook.service;

import com.coursework.EmployeeBook.dto.Employee;
import com.coursework.EmployeeBook.exceptions.EmployeeAlreadyAddedException;
import com.coursework.EmployeeBook.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private final Map<String, Employee> employeeMap = new HashMap<>();


    private String generateKey(Employee employee) {
        return employee.getFirstName() + " " + employee.getSurname();
    }


    public void addEmployee(Employee employee) {
        String key = generateKey(employee);
        if (employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник с таким ФИО уже существует.");
        }
        employeeMap.put(key, employee);
    }


    public void removeEmployee(String name, String surname) {
        String key = name + " " + surname;
        if (!employeeMap.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник с таким ФИО не найден.");
        }
        employeeMap.remove(key);
    }


    public Employee findEmployee(String name, String surname) {
        String key = name + " " + surname;
        Employee employee = employeeMap.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник с таким ФИО не найден.");
        }
        return employee;
    }


    public Map<String, Employee> getAllEmployees() {
        return employeeMap;
    }
}

