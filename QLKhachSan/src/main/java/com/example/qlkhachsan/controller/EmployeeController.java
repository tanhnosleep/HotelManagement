package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.model.Employee;
import com.example.qlkhachsan.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
@Controller
@RequestMapping("/quanlynhanvien")
public class EmployeeController {

//    @Autowired
//    private EmployeeRepository repo;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

//    @GetMapping
//    public String showListEmployee(Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        List<Employee> le = (List<Employee>) repo.findAll();
//        model.addAttribute("Employees",le);
//        return "quanlynhanvien";
//    }
    @GetMapping
    public String showListEmployee(Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        List<Employee> le = employeeService.showListEmployee();
        model.addAttribute("Employees",le);
        return "quanlynhanvien";
    }

//    @GetMapping("/search")
//    public String searchEmployee(@Param("keyword") String keyword, Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        keyword = keyword.trim();
//        List<Employee> le = repo.findAll();
//        List<Employee> result  = new ArrayList<Employee>();
//        for (Employee e : le) {
//            if(e.getEmployeeId().toString().contains(keyword) ||
//                    e.getEmployeeName().toLowerCase().contains(keyword.toLowerCase()) ||
//                    e.getBirth().toLowerCase().contains(keyword.toLowerCase())||
//                    e.getAddress().toLowerCase().contains(keyword.toLowerCase())||
//                    e.getEmail().toLowerCase().contains(keyword.toLowerCase())||
//                    e.getGender().toLowerCase().contains(keyword.toLowerCase())||
//                    e.getPhoneNumber().toLowerCase().contains(keyword.toLowerCase())||
//                    e.getSalary().toString().contains(keyword.toLowerCase())) {
//                result.add(e);
//            }
//        }
//        model.addAttribute("Employees",result);
//        return "quanlynhanvien";
//    }
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


//    @PostMapping("/add")
//    public  String addEmployee(Employee employee) {
//        repo.save(employee);
//        return "redirect:/quanlynhanvien";
//    }
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee){
        employeeService.addEmployee(employee);
        return "redirect:/quanlynhanvien";
    }


//    @GetMapping("/edit/{id}")
//    public  String showEditEmployee(@PathVariable(name = "id") Long id, Model model, Principal principal) {
//        String message = principal.getName() ;
//        model.addAttribute("message1", message);
//        Employee employee = new Employee();
//        Optional<Employee> optEmployee = repo.findById(id);
//        if(optEmployee.isPresent()){
//            employee = optEmployee.get();
//        }
//        model.addAttribute("Employee", employee );
//        return "suanhanvien";
//    }
    @GetMapping("/edit/{id}")
    public String showEditEmployee(@PathVariable(name="id") Long id, Model model, Principal principal){
        String message = principal.getName();
        model.addAttribute("message1",message);
        model.addAttribute("Employee",employeeService.showEditEmployee(id));
        return "suanhanvien";
    }


//    @PostMapping("/edit/{id}")
//    public  String editEmployee(@PathVariable(name = "id") Long id,Employee employee) {
//        Optional<Employee> optEmployee = repo.findById(id);
//        if(optEmployee.isPresent()){
//            Employee employeeExist = optEmployee.get();
//            employeeExist.setEmployeeName(employee.getEmployeeName());
//            employeeExist.setEmail(employee.getEmail());
//            employeeExist.setAddress(employee.getAddress());
//            employeeExist.setBirth(employee.getBirth());
//            employeeExist.setGender(employee.getGender());
//            employeeExist.setSalary((employee.getSalary()));
//            employeeExist.setPhoneNumber(employee.getPhoneNumber());
//
//            repo.save(employeeExist);
//        }
//        return "redirect:/quanlynhanvien";
//    }
    @PostMapping("/edit/{id}")
    public String editEmployee(Employee employee){
        employeeService.editEmployee(employee);
        return "redirect:/quanlynhanvien";
    }

//    @GetMapping("/delete/{id}")
//    public  String deleteEmployee(@PathVariable(name = "id") Long id) {
//        repo.deleteById(id);
//        return "redirect:/quanlynhanvien";
//    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/quanlynhanvien";
    }

}
