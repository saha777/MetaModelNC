package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.DepartmentService;
import service.EmployeeService;
import service.LocationService;
import service.OfficeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/changeGrant")
    public String changeGrant(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("grant", getGrant(httpServletRequest));
        return "changeGrant";
    }

    @PostMapping("/changeGrant")
    public String changeGrant(@ModelAttribute("grant") Integer grant, HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().setAttribute("grant", grant);
        return "redirect:/";
    }

    @GetMapping("/locations")
    public String getLocations(Model model, HttpServletRequest httpServletRequest) {
        Integer grant = MainController.getGrant(httpServletRequest);
        model.addAttribute("locations", locationService.findAll(grant));
        return "locationList";
    }

    @GetMapping("/location/{locationId}")
    public String getLocation(@PathVariable Integer locationId, Model model, HttpServletRequest httpServletRequest) {
        Integer grant = MainController.getGrant(httpServletRequest);
        model.addAttribute("offices", officeService.findByLocationId(locationId, grant));
        return "officeList";
    }

    @GetMapping("/office/{officeId}")
    public String getOffice(@PathVariable Integer officeId, Model model, HttpServletRequest httpServletRequest) {
        Integer grant = MainController.getGrant(httpServletRequest);
        model.addAttribute("departments", departmentService.findByOfficeId(officeId, grant));
        return "departmentList";
    }

    @GetMapping("/department/{departmentId}")
    public String getDepartment(@PathVariable Integer departmentId, Model model, HttpServletRequest httpServletRequest) {
        Integer grant = MainController.getGrant(httpServletRequest);
        model.addAttribute("employees", employeeService.findByDepartmentId(departmentId, grant));
        return "employeeList";
    }

    public static Integer getGrant(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Integer grant = (Integer) session.getAttribute("grant");

        if (grant == null) {
            grant = 1;
            session.setAttribute("grant", grant);
        }

        return grant;
    }

}