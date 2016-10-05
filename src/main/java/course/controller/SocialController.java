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
import javax.servlet.http.HttpSession;

/**
 * Created by Артем Константинович on 30.09.2016.
 */
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String logging(Model model, HttpSession httpSession) {

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
                httpSession.setAttribute("role", user.getRole());
                httpSession.setAttribute("id", user.getId());
                httpSession.setAttribute("img", twitter.userOperations().getUserProfile().getProfileImageUrl());
                model.addAttribute("img", twitter.userOperations().getUserProfile().getProfileImageUrl());
            }
            else{
                model.addAttribute("user",userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl()).getUserUrl());
                model.addAttribute("name",userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl()).getName());
                model.addAttribute("role",userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl()).getRole());
                httpSession.setAttribute("role", userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl()).getRole());
                httpSession.setAttribute("id", userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl()).getId());
                httpSession.setAttribute("img", twitter.userOperations().getUserProfile().getProfileImageUrl());
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
                    model.addAttribute("user",user.getUserUrl());
                    model.addAttribute("name", user.getName());
                    model.addAttribute("role", user.getRole());
                    httpSession.setAttribute("role", user.getRole());
                    httpSession.setAttribute("id", user.getId());
                    httpSession.setAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                    model.addAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                }
                else{
                    model.addAttribute("user",userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()).getUserUrl());
                    model.addAttribute("name",userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()).getName());
                    model.addAttribute("role",userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()).getRole());
                    httpSession.setAttribute("role",userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()).getRole());
                    httpSession.setAttribute("id", userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()).getId());
                    httpSession.setAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                    model.addAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                }
            }
        }

        return "index";
    }


}
