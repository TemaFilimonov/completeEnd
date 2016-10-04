package course.controller;

import course.dao.UserRepository;
import course.domain.User;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by Артем Константинович on 30.09.2016.
 */
//@RestController
@Controller
@RequestMapping("/")

public class SocialController {

    private Twitter twitter;
    private Facebook facebook;
    private ConnectionRepository connectionRepository;
    private UserRepository userRepository;



    @Inject
    public SocialController(ConnectionRepository connectionRepository, UserRepository userRepository, Twitter twitter, Facebook facebook) {
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
        this.twitter = twitter;
        this.facebook = facebook;
    }

/*
    @RequestMapping(value = "/connect/twitter", method = RequestMethod.GET)
    public ModelAndView connectTwitter(){
        return new ModelAndView("redirect:/profiles");
    }

    @RequestMapping(value = "/connect/facebook", method = RequestMethod.GET)
    public ModelAndView connectFacebook(){
        return new ModelAndView("redirect:/profiles");
    }
*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String logging(Model model) {
        User user = new User();

        if ((connectionRepository.findPrimaryConnection(Twitter.class) != null)) {
            if (userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl())==null){
                user.setName(twitter.userOperations().getScreenName());
                user.setUserUrl(twitter.userOperations().getUserProfile().getProfileUrl());
                user.setRole("user");
                userRepository.save(user);

                model.addAttribute("user", user.getUserUrl());
                model.addAttribute("name", user.getName());
                model.addAttribute("role", user.getRole());
                model.addAttribute("img", twitter.userOperations().getUserProfile().getProfileImageUrl());
            }
            else{
                //return userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl());
                model.addAttribute("user",userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl()).getUserUrl());
                model.addAttribute("name",userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl()).getName());
                model.addAttribute("role",userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl()).getRole());
                model.addAttribute("img", twitter.userOperations().getUserProfile().getProfileImageUrl());
            }
        }
        else {
            if ((connectionRepository.findPrimaryConnection(Facebook.class) != null)) {
                if ((userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()))==null) {
                    user.setName(facebook.userOperations().getUserProfile().getName());
                    user.setUserUrl(facebook.userOperations().getUserProfile().getLink());
                    user.setRole("user");
                    userRepository.save(user);
                    //return user;
                    model.addAttribute("user",user.getUserUrl());
                    model.addAttribute("name", user.getName());
                    model.addAttribute("role", user.getRole());
                    model.addAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                }
                else{
                    //return userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink());
                    model.addAttribute("user",userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()).getUserUrl());
                    model.addAttribute("name",userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()).getName());
                    model.addAttribute("role",userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()).getRole());
                    model.addAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                }
            }
        }
        return "index";
    }

}
