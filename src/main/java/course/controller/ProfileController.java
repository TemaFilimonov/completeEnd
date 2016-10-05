package course.controller;

import course.dao.UserRepository;
import course.domain.User;
import course.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 * Created by Nox on 05.10.2016.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ProfileController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody UserProfile ViewProfile(@PathVariable long id, HttpSession httpSession){
        User user = new User();
        user = userRepository.findById(id);
        return new UserProfile(user.getName(),httpSession.getAttribute("img").toString(),user.getUserUrl());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String ViewProfileRole(@PathVariable long id, Model model){
        model.addAttribute("id", userRepository.findById(id).getRole());
        return "/profile/{id}";
    }
}
