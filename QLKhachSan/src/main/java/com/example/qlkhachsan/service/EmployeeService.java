package com.example.qlkhachsan.service;

import com.example.qlkhachsan.Repository.EmployeeRepository;
import com.example.qlkhachsan.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public List<Employee> showListEmployee(){
        return employeeRepository.showListEmployee();
    }

    public List<Employee> searchEmployee(String keyword){
        List<Employee> allEmployees = employeeRepository.showListEmployee();
        List<Employee> result = new ArrayList<Employee>();
        for (Employee e : allEmployees){
            if(e.getEmployeeId().toString().contains(keyword) ||
                    e.getEmployeeName().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getBirth().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getAddress().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getEmail().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getGender().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getPhoneNumber().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getSalary().toString().contains(keyword.toLowerCase())) {
                result.add(e);
            }
        }
        return result;
    }
    public void addEmployee (Employee employee){
        employeeRepository.addEmployee(employee.getEmployeeId(),employee.getEmployeeName(),
                employee.getBirth(),employee.getGender(),employee.getAddress(),employee.getEmail(),
                employee.getPhoneNumber(),employee.getSalary());
    }

    public Employee showEditEmployee(Long id){
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByID(id).stream().findFirst();
        return optionalEmployee.orElse(null);
    }

    public void editEmployee(Employee employee){
            employeeRepository.editEmployee(employee.getEmployeeId(),employee.getEmployeeName(),
                    employee.getBirth(), employee.getGender(), employee.getAddress(),
                    employee.getEmail(), employee.getPhoneNumber(),employee.getSalary());
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteEmployee(id);
    }
}
