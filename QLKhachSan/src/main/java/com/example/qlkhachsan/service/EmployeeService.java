package com.example.qlkhachsan.service;

import com.example.qlkhachsan.repository.EmployeeRepository;
import com.example.qlkhachsan.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.*;
import java.util.*;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public String formatSalary(double salary){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        //xác định các ký hiệu (symbols) như dấu phân cách hàng nghìn, dấu phân cách thập phân
        //Locale.getDefault() được sử dụng để lấy Locale mặc định của hệ thống, chẳng hạn như "vi_VN" cho tiếng Việt.

        symbols.setDecimalSeparator(','); //Đặt dấu phân cách thập phân là dấu phẩy (',').
        symbols.setGroupingSeparator('.'); //Đặt dấu phân cách hàng nghìn là dấu chấm ('.').

        DecimalFormat formatter = new DecimalFormat("#,##0",symbols);
        //"#,##0" là mẫu định dạng số để định dạng salary theo kiểu hàng nghìn, không có số thập phân.

        return formatter.format(salary)+" VNĐ";
    }

    public List<Employee> showListEmployee(){
        return employeeRepository.findAll();
    }

    public List<Employee> searchEmployee(String keyword){
        List<Employee> allEmployees = employeeRepository.findAll();
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
        String inputDateStr = employee.getBirth();
        String outputDateFormat = "dd/MM/yyyy";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputDateFormatObj = new SimpleDateFormat(outputDateFormat);
        try {
            Date inputDate = inputDateFormat.parse(inputDateStr);
            String outputDateStr = outputDateFormatObj.format(inputDate);
            employee.setBirth(outputDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        employeeRepository.save(employee);
    }

    public Employee showEditEmployee(Long id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            String birthDateStr = employee.getBirth();
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                //parse de doi String thanh Date
                //format de doi Date thanh String
                Date birthDate = inputDateFormat.parse(birthDateStr);
                String formattedBirthDate = outputDateFormat.format(birthDate);
                employee.setBirth(formattedBirthDate);
            }catch (ParseException e){
                throw new RuntimeException(e);
            }
        }
        return optionalEmployee.orElse(null);
    }

    public void editEmployee(Employee employee){
        String inputDateStr = employee.getBirth();
        String outputDateFormat = "dd/MM/yyyy";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputDateFormatObj = new SimpleDateFormat(outputDateFormat);
        try {
            Date inputDate = inputDateFormat.parse(inputDateStr);
            String outputDateStr = outputDateFormatObj.format(inputDate);
            employee.setBirth(outputDateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}
