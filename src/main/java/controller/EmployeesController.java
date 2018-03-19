package controller;

import entities.Employee;
import metamodel.dao.GrantsDao;
import metamodel.dao.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private Service<Employee> employeesService;

    @Autowired
    private GrantsDao grantsDao;

    @GetMapping("/")
    public String getAll(Model model, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("employees", employeesService.getAll(role));
        return "employeeList";
    }

    @GetMapping("/{empId}")
    public String getEmpPage(Model model, @PathVariable Integer empId, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        Employee employee = employeesService.get(role, empId);

        if (employee == null)
            return "redirect:/";

        boolean isUpdatable =  grantsDao.isWritableObj(role, empId);

        model.addAttribute("employee", employee);
        model.addAttribute("updatable", isUpdatable);
        return "employee";
    }

    @GetMapping("/department/{departmentId}")
    public String getByDepartment(Model model, @PathVariable Integer departmentId, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        List<Employee> employeeList = employeesService.getByParentId(role, departmentId);
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

        Employee employee = employeesService.getForUpdate(role, employeeId);

        model.addAttribute("grant", role);
        model.addAttribute("employee", employee);

        return "updateEmp";
    }

    @PostMapping("/update")
    public String setUpdateEmp(HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        Map<String, String[]> stringMap = httpServletRequest.getParameterMap();
        employeesService.update(role, Integer.parseInt(stringMap.get("objectId")[0]), stringMap);
        return "redirect:/";
    }
}
