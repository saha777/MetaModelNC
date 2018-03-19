package controller;

import entities.Location;
import entities.Office;
import metamodel.dao.GrantsDao;
import metamodel.dao.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import service.Service;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LocationsController {
    @Autowired
    private Service<Location> locationsService;

    @Autowired
    private Service<Office> officesService;

    @Autowired
    private GrantsDao grantsDao;

    @GetMapping("/locations")
    public String getLocations(Model model, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("locations", locationsService.getAll(role));
        return "locationList";
    }

    @GetMapping("/location/{locationId}")
    public String getLocation(@PathVariable Integer locationId, Model model, HttpServletRequest httpServletRequest) {
        Role role = MainController.getGrant(grantsDao, httpServletRequest);
        model.addAttribute("offices", officesService.getByParentId(role, locationId));
        return "officeList";
    }
}
