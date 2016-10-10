package course.controller;

import course.dao.SiteRepository;
import course.domain.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nox on 06.10.2016.
 */
@Controller
@RequestMapping("/")
public class EditController {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    public EditController(SiteRepository siteRepository){
        this.siteRepository = siteRepository;
    }

    @RequestMapping(value = "/edit/source/{id}", method = RequestMethod.POST)
    public void Source(Model model, HttpSession httpSession, @RequestBody String source, @PathVariable long id) {
        Site site = siteRepository.findById(id);
        if (site.getOwnerId()==(long)httpSession.getAttribute("id")) {
            site.setSource(source);
            Date data = Calendar.getInstance().getTime();
            site.setEditDate(data.toString());
            siteRepository.save(site);
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String ViewEditRole(Model model, HttpSession httpSession){
        model.addAttribute("role", httpSession.getAttribute("role"));
        model.addAttribute("name", httpSession.getAttribute("name"));
        model.addAttribute("id", httpSession.getAttribute("id"));
        model.addAttribute("img", httpSession.getAttribute("img"));
        return "edit";
    }
}
