package controller;

import metamodel.dao.GrantsDao;
import metamodel.dao.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private GrantsDao grantsDao;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/changeGrant")
    public String changeGrant(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("roles", grantsDao.findAll());
        return "changeGrant";
    }

    @PostMapping("/changeGrant")
    public String changeGrant(@ModelAttribute("role") String roleName,
                              HttpServletRequest httpServletRequest) {
        Role role = grantsDao.findByName(roleName);
        httpServletRequest.getSession().setAttribute("role", role);
        return "redirect:/";
    }



    public static Role getGrant(GrantsDao grantsDao, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Role role = (Role) session.getAttribute("role");

        if (role == null) {
            role = grantsDao.findByName("user");
            session.setAttribute("grant", role);
        }

        return role;
    }

}