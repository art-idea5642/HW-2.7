package com.coursework.EmployeeBook.controller;

import com.coursework.EmployeeBook.dto.Employee;
import com.coursework.EmployeeBook.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Добавление сотрудника
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return ResponseEntity.ok("Сотрудник добавлен.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Удаление сотрудника
    @DeleteMapping
    public ResponseEntity<String> removeEmployee(@RequestParam String name, @RequestParam String surname) {
        try {
            employeeService.removeEmployee(name, surname);
            return ResponseEntity.ok("Сотрудник удален.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Поиск сотрудника
    @GetMapping
    public ResponseEntity<Employee> findEmployee(@RequestParam String name, @RequestParam String surname) {
        try {
            Employee employee = employeeService.findEmployee(name, surname);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Получение всех сотрудников
    @GetMapping("/all")
    public ResponseEntity<Map<String, Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}


