package controller;

import dao.LocationsDao;
import dao.OfficeDao;
import metamodel.dao.GrantsDao;
import metamodel.dao.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LocationsController {
    @Autowired
    private LocationsDao locationsDao;

    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private GrantsDao grantsDao;

    public LocationsController() {}

    @GetMapping("/locations")
    public String getLocations(Model model, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("locations", locationsDao.findAll(role));
        return "locationList";
    }

    @GetMapping("/location/{locationId}")
    public String getLocation(@PathVariable Integer locationId, Model model, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("offices", officeDao.findByParentId(locationId, role));
        return "officeList";
    }
}
