package controller;

import dao.DepartmentDao;
import dao.EmployeesDao;
import metamodel.dao.GrantsDao;
import metamodel.dao.models.Grants;
import metamodel.dao.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {
    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private GrantsDao grantsDao;
    public DepartmentsController() {}

    @GetMapping("/{officeId}")
    public String getOffice(Model model, @PathVariable Integer officeId, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("departments", departmentDao.findByParentId(officeId, role));
        return "departmentList";
    }

    @GetMapping("/")
    public String getOffice(Model model, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("departments", departmentDao.findAll(role));
        return "departmentList";
    }
}
