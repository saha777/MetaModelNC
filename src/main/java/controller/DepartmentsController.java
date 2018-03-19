package controller;

import entities.Department;
import metamodel.dao.GrantsDao;
import metamodel.dao.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Service;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {
    @Autowired
    private Service<Department> service;

    @Autowired
    private GrantsDao grantsDao;

    public DepartmentsController() {}

    @GetMapping("/{officeId}")
    public String getOffice(Model model, @PathVariable Integer officeId, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("departments", service.getByParentId(role, officeId));
        return "departmentList";
    }

    @GetMapping("/")
    public String getOffice(Model model, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("departments", service.getAll(role));
        return "departmentList";
    }
}
