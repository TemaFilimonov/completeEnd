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

    @RequestMapping(value = "/curentUser/info", method = RequestMethod.GET)
    public @ResponseBody UserProfile ViewProfile(HttpSession httpSession){
        User user = new User();
        user = userRepository.findById(Integer.parseInt(httpSession.getAttribute("id").toString()));
        return new UserProfile(user.getName(),httpSession.getAttribute("img").toString(),user.getUserUrl());
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String ViewProfileRole(Model model, HttpSession httpSession){
        model.addAttribute("role", userRepository.findById(Integer.parseInt(httpSession.getAttribute("id").toString())).getRole());
        return "/profile";
    }
}
