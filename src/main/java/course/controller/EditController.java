package course.controller;

import course.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Nox on 06.10.2016.
 */
@Controller
@RequestMapping("/")
public class EditController {

    private final SiteService siteService;

    @Autowired
    public EditController(SiteService siteService) {
        this.siteService = siteService;
    }


    @RequestMapping(value = "/edit/source/{id}", method = RequestMethod.POST)
    public void Source(HttpSession httpSession, @RequestBody String source, @PathVariable long id) {
        siteService.saveExistSite(httpSession, source, id);
    }
}
