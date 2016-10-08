package course.controller;

import course.dao.SiteRepository;
import course.domain.Site;
import course.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = "/create/site/{id}", method = RequestMethod.GET)
    public void CreateSite(Model model, @RequestBody String name, @RequestBody String source, @RequestBody String stringTags, @PathVariable long id) {
        List<Tag> tag = new ArrayList<Tag>();
        for (String retval : stringTags.split(" ")) {
            tag.add(new Tag(retval));
        }
        siteRepository.save(new Site(name, id, Calendar.getInstance().getTime().toString(), Calendar.getInstance().getTime().toString(), source, tag));
    }
}
