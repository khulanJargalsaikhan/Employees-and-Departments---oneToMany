package com.example.employees_and_departments;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "index";
    }

    @GetMapping("/addDepartment")
    public String addDepartment(Model model){
        model.addAttribute("department", new Department());
        return "departmentform";
    }

    @RequestMapping("/updateDepartment/{id}")
    public String updateDepartment(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "departmentform";
    }

    @RequestMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id") long id){
        departmentRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/processDepartment")
    public String processDepartment(@ModelAttribute Department department){
        departmentRepository.save(department);
        return "redirect:/";
    }



    @GetMapping("/addEmployee")
    public String addEmployee(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employeeform";
    }


    @RequestMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employeeform";
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/processEmployee")
    public String processEmployee(@ModelAttribute Employee employee, @RequestParam("file") MultipartFile file){
        if(file.isEmpty() && (employee.getPhoto() == null || employee.getPhoto().isEmpty())){
            employee.setPhoto("https://res.cloudinary.com/dqejdpdau/image/upload/v1628789991/d20vhr0ywftubc4dcex7.jpg");   //set up default photo
        }else if(!file.isEmpty()) {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                employee.setPhoto(uploadResult.get("url").toString());

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/processEmployee";
            }
        }
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @RequestMapping("/moreInfo/{id}")
    public String moreInfo(@PathVariable("id") long id, Model model){
        model.addAttribute("employees", employeeRepository.findById(id).get());
        return "detail";
    }


    @RequestMapping("/allDepartments")
    public String showAllDepartments(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "allDepartments";
    }

    @RequestMapping("/allEmployees")
    public String showAllEmployees(Model model){
        model.addAttribute("employee", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        return "allEmployees";
    }

    @GetMapping("/search")
    public String searchEmployee(Model model){
        return "index";
    }

    @PostMapping("/search")
    public String searchEmployee(Model model, @RequestParam(name="name") String name){
        ArrayList<Employee> results = (ArrayList<Employee>) employeeRepository.findAllByName(name);
        model.addAttribute("results", results);
        model.addAttribute("departments", departmentRepository.findAll());
        return "result";
    }




}
