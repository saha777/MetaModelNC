package controller;

import dao.AbstractDao;
import dao.EmployeesDao;
import entities.Employee;
import metamodel.dao.GrantsDao;
import metamodel.dao.models.Grants;
import metamodel.dao.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeesDao employeesDao;

    @Autowired
    private GrantsDao grantsDao;

    public EmployeesController() {
    }

    @GetMapping("/")
    public String getAll(Model model, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("employees", employeesDao.findAll(role));
        return "employeeList";
    }

    @GetMapping("/{empId}")
    public String getEmpPage(Model model, @PathVariable Integer empId, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        Employee employee = employeesDao.findById(empId, role);

        if (employee == null)
            return "redirect:/";

        model.addAttribute("employee", employee);
        return "employee";
    }

    @GetMapping("/department/{departmentId}")
    public String getByDepartment(Model model, @PathVariable Integer departmentId, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        List<Employee> employeeList = employeesDao.findByParentId(departmentId, role);
        model.addAttribute("department", departmentId);
        model.addAttribute("employees", employeeList);
        return "employeeList";
    }

    @GetMapping("/update/{employeeId}")
    public String getUpdatePage(
            Model model,
            @PathVariable Integer employeeId,
            HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);

        if(!grantsDao.isWritableObj(role, employeeId)) return "redirect:/" + employeeId;

        Employee employee = employeesDao.findById(employeeId, role);

        model.addAttribute("grant", role);
        model.addAttribute("employee", employee);



        return "updateEmp";
    }

    @PostMapping("/update")
    public String setUpdateEmp(@ModelAttribute Employee employee, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        employeesDao.update(employee, role);
        return "redirect:/";
    }
}
