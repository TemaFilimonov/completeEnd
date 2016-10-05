package course.controller;

import course.dao.UserRepository;
import course.domain.User;
import course.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Nox on 05.10.2016.
 */
@Controller
@RequestMapping("/")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ProfileController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/user/info/{id}", method = RequestMethod.GET)
    public @ResponseBody UserProfile ViewProfile(HttpSession httpSession, @PathVariable("id") String id){
        User user = new User();
        user = userRepository.findById(Integer.parseInt(id));
        return new UserProfile(user.getName(),user.getUser_photo_url(),user.getUserUrl());
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String ViewProfileRole(Model model, HttpSession httpSession){
        model.addAttribute("role", httpSession.getAttribute("role"));
        model.addAttribute("name", httpSession.getAttribute("name"));
        model.addAttribute("id", httpSession.getAttribute("id"));
        model.addAttribute("img", httpSession.getAttribute("img"));
        return "/profile";
    }

}
