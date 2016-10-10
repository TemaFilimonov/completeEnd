package course.controller;

import course.dao.SiteRepository;
import course.domain.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * Created by Nox on 09.10.2016.
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

    @RequestMapping(value = "save/site", method = RequestMethod.POST)
    public String CreateSite(HttpSession httpSession, @RequestBody Site site) {
            siteRepository.save(new Site(site.getName(), (long) httpSession.getAttribute("id"), Calendar.getInstance().getTime().toString(), Calendar.getInstance().getTime().toString(), site.getTags()));
            return "redirect:/profile?id="+httpSession.getAttribute("id").toString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String ViewCreateRole(Model model, HttpSession httpSession){
        model.addAttribute("role", httpSession.getAttribute("role"));
        model.addAttribute("name", httpSession.getAttribute("name"));
        model.addAttribute("id", httpSession.getAttribute("id"));
        model.addAttribute("img", httpSession.getAttribute("img"));
        return "create";
    }
}
