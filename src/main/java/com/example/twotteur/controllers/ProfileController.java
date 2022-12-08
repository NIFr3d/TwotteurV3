package com.example.twotteur.controllers;

import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import com.example.twotteur.services.FollowService;
import com.example.twotteur.services.TwotRetwotService;
import com.example.twotteur.services.TwotService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private TwotService twotService;
    @Autowired
    private TwotRetwotService trtService;

    @Autowired
    private FollowService followService;

    @GetMapping(value="/user/{username}")
    public ModelAndView userProfile(@PathVariable String username){
        ModelAndView mav=new ModelAndView();
        if(userService.getUserByusername(username).isPresent()){
            List<Long> twots=new ArrayList<>();
            mav.addObject("user",userService.getUserByusername(username).get());
            mav.addObject("followers",followService.getfollowers(userService.getUserByusername(username).get()));
            mav.addObject("followeds",followService.getfollowed(userService.getUserByusername(username).get()));
            mav.setViewName("user");
        }else{
            mav.addObject("status",404);
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping(value="/profile")
    public ModelAndView profile(HttpSession session){
        ModelAndView mav=new ModelAndView();
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long id = (long) session.getAttribute("userid");
            String username = userService.getUserById(id).get().getusername();
            mav= new ModelAndView("redirect:/user/"+username);
        }else{
            mav.addObject("status",403);
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping(value="/editprofile")
    public ModelAndView editprofile(HttpSession session){
        ModelAndView mav=new ModelAndView();
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long id = (long) session.getAttribute("userid");
            if (userService.getUserById(id).isPresent()) {
                mav.addObject("user", userService.getUserById(id).get());
                mav.setViewName("editprofile");
            }
        }else{
            mav.addObject("status",403);
            mav.setViewName("error");
        }
        return mav;
    }

    @PostMapping(value="/editprofile")
    public RedirectView editprofilepost(@RequestParam("nickname") String nickname, @RequestParam("biography") String biography,
                                        @RequestParam("base64") String base64,HttpSession session) throws IOException {
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long id = (long) session.getAttribute("userid");
            if (userService.getUserById(id).isPresent()) {
                User user = userService.getUserById(id).get();
                user.setbiography(biography);
                user.setnickname(nickname);
                if(base64.length()>0){
                    String base64data = base64.split(",")[1];
                    byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64data);
                    user.setpicture("../images/"+user.getusername()+".png");
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                    BufferedImage image = ImageIO.read(bis);
                    bis.close();
                    File outputfile = new File("profilepictures/"+user.getusername()+".png");
                    ImageIO.write(image, "png", outputfile);
                }
                userService.update(user);
            }
        }
        return new RedirectView("/profile");
    }
}
