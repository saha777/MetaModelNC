package controller;

import entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String getAll(Model model, HttpServletRequest httpServletRequest) {
        Integer grant = MainController.getGrant(httpServletRequest);
        model.addAttribute("employees", employeeService.findAll(grant));
        return "employeeList";
    }

    @GetMapping("/emp/{empId}")
    public String getEmpPage(Model model, @PathVariable Integer empId, HttpServletRequest httpServletRequest) {
        Integer grant = MainController.getGrant(httpServletRequest);
        Employee employee = employeeService.findByObjectId(empId, grant);

        if(employee == null)
            return "redirect:/";

        model.addAttribute("employee", employee);
        return "employee";
    }

    @GetMapping("/department/{departmentId}")
    public String getByDepartment(Model model, @PathVariable Integer departmentId, HttpServletRequest httpServletRequest) {
        Integer grant = MainController.getGrant(httpServletRequest);
        model.addAttribute("department", departmentId);
        model.addAttribute("employees", employeeService.findByDepartmentId(departmentId, grant));
        return "employeeList";
    }

    @GetMapping("/update/{employeeId}")
    public String getUpdatePage(
            Model model,
            @PathVariable Integer employeeId,
            HttpServletRequest httpServletRequest){
        Integer grant = MainController.getGrant(httpServletRequest);

        if(grant < 5)
            return "redirect:/";

        Employee employee = employeeService.findByObjectId(employeeId, grant);

        model.addAttribute("grant", grant);
        model.addAttribute("employee", employee);
        return "updateEmp";
    }

    @PostMapping("/update")
    public String setUpdateEmp(@ModelAttribute Employee employee, HttpServletRequest httpServletRequest) {
        Integer grant = MainController.getGrant(httpServletRequest);

        if(grant < 5)
            return "redirect:/";

        employeeService.update(employee, grant);
        return "redirect:/";
    }
}
