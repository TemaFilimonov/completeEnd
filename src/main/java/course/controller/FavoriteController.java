package course.controller;

import course.dao.FavoriteRepository;
import course.dao.SiteRepository;
import course.domain.Favorite;
import course.domain.Site;
import course.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Nox on 06.10.2016.
 */
@Controller
@RequestMapping("/")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    public FavoriteController(FavoriteRepository favoriteRepository, SiteRepository siteRepository){
        this.favoriteRepository = favoriteRepository;
        this.siteRepository = siteRepository;
    }

    @RequestMapping(value = "/user/favorite/{id}", method = RequestMethod.GET)
    public @ResponseBody List<Site> ViewFavorite(HttpSession httpSession, @PathVariable("id") long id) {
        List<Site> sites = null;
        List<Favorite> favorite = favoriteRepository.findByUserId(id);
        for(int i = favorite.size(); i>=0; i--){
            sites.add(siteRepository.findById(favorite.get(i).getSiteId()));
        }
        return sites;
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public String ViewFavoriteRole(Model model, HttpSession httpSession){
        model.addAttribute("role", httpSession.getAttribute("role"));
        model.addAttribute("name", httpSession.getAttribute("name"));
        model.addAttribute("id", httpSession.getAttribute("id"));
        model.addAttribute("img", httpSession.getAttribute("img"));
        return "/favorite";
    }

}
