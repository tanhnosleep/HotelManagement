package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.model.Employee;
import com.example.qlkhachsan.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/quanlynhanvien")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @GetMapping
    public String showListEmployee(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<Employee> le = employeeService.showListEmployee();
        model.addAttribute("Employees",le);
        return "quanlynhanvien";
    }

    @GetMapping("/search")
    public String searchEmployee(@RequestParam("keyword") String keyword, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        keyword = keyword.trim();
        model.addAttribute("Employees",employeeService.searchEmployee(keyword));
        return "quanlynhanvien";
    }

    @GetMapping("/add")
    public  String showAddEmployee(Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        model.addAttribute("Employee", new Employee() );
        return "themnhanvien";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee){
        employeeService.addEmployee(employee);
        return "redirect:/quanlynhanvien";
    }

    @GetMapping("/edit/{id}")
    public String showEditEmployee(@PathVariable(name="id") Long id, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        model.addAttribute("Employee",employeeService.showEditEmployee(id));
        return "suanhanvien";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(Employee employee){
        employeeService.editEmployee(employee);
        return "redirect:/quanlynhanvien";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/quanlynhanvien";
    }

}
