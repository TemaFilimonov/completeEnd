package course.controller;

import course.dao.FavoriteRepository;
import course.dao.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Nox on 06.10.2016.
 */
@Controller
@RequestMapping("/")
public class CreateController {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    public CreateController(SiteRepository siteRepository){
        this.siteRepository = siteRepository;
    }

    @RequestMapping(value = "/create/view", method = RequestMethod.GET)
    public String ViewCreate(Model model) {
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String ViewCreateRole(Model model, HttpSession httpSession){
        model.addAttribute("role", httpSession.getAttribute("role"));
        model.addAttribute("name", httpSession.getAttribute("name"));
        model.addAttribute("id", httpSession.getAttribute("id"));
        model.addAttribute("img", httpSession.getAttribute("img"));
        return "/create";
    }
}
