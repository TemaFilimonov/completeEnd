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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

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
                user.setUser_photo_url(twitter.userOperations().getUserProfile().getProfileImageUrl());
                userRepository.save(user);

                model.addAttribute("user", user.getUserUrl());
                model.addAttribute("name", user.getName());
                model.addAttribute("role", user.getRole());
                model.addAttribute("id", user.getId());
                httpSession.setAttribute("name", user.getName());
                httpSession.setAttribute("role", user.getRole());
                httpSession.setAttribute("id", user.getId());
                httpSession.setAttribute("img", twitter.userOperations().getUserProfile().getProfileImageUrl());
                model.addAttribute("img", twitter.userOperations().getUserProfile().getProfileImageUrl());
            }
            else{
                user = userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl());
                model.addAttribute("user",user.getUserUrl());
                model.addAttribute("name",user.getName());
                model.addAttribute("role",user.getRole());
                model.addAttribute("id", user.getId());
                httpSession.setAttribute("name", user.getName());
                httpSession.setAttribute("role", user.getRole());
                httpSession.setAttribute("id", user.getId());
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
                    user.setUser_photo_url("http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                    userRepository.save(user);

                    model.addAttribute("user",user.getUserUrl());
                    model.addAttribute("name", user.getName());
                    model.addAttribute("role", user.getRole());
                    model.addAttribute("id", user.getId());
                    httpSession.setAttribute("name", user.getName());
                    httpSession.setAttribute("role", user.getRole());
                    httpSession.setAttribute("id", user.getId());
                    httpSession.setAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                    model.addAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                }
                else{
                    user = userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink());
                    model.addAttribute("user",user.getUserUrl());
                    model.addAttribute("name",user.getName());
                    model.addAttribute("role",user.getRole());
                    model.addAttribute("id", user.getId());
                    httpSession.setAttribute("name", user.getName());
                    httpSession.setAttribute("role",user.getRole());
                    httpSession.setAttribute("id",user.getId());
                    httpSession.setAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                    model.addAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                }
            }
        }

        return "index";
    }

    @RequestMapping(value = "/disconnect")
    public String disconnect(HttpSession httpSession){
        if(connectionRepository.findPrimaryConnection(Twitter.class) != null){
            connectionRepository.removeConnection(connectionRepository.getPrimaryConnection(Twitter.class).getKey());
        }

        if (connectionRepository.findPrimaryConnection(Facebook.class) != null){
            connectionRepository.removeConnection(connectionRepository.getPrimaryConnection(Facebook.class).getKey());
        }
        httpSession = new HttpSession() {
            @Override
            public long getCreationTime() {
                return 0;
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public long getLastAccessedTime() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public void setMaxInactiveInterval(int i) {

            }

            @Override
            public int getMaxInactiveInterval() {
                return 0;
            }

            @Override
            public HttpSessionContext getSessionContext() {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String[] getValueNames() {
                return new String[0];
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void putValue(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public void removeValue(String s) {

            }

            @Override
            public void invalidate() {

            }

            @Override
            public boolean isNew() {
                return false;
            }
        };
        return "index";
    }

}
