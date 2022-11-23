package com.example.twotteur.controllers;

import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import com.example.twotteur.services.FileUploadUtil;
import com.example.twotteur.services.FollowService;
import com.example.twotteur.services.TwotService;
import com.example.twotteur.services.UserService;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private TwotService twotService;

    @Autowired
    private FollowService followService;

    @GetMapping(value="/user/{username}")
    public String userProfile(@PathVariable String username, Model model){
        if(userService.getUserByusername(username).isPresent()){
            List<Twot> twots=twotService.getTwots(userService.getUserByusername(username).get());
            model.addAttribute("user",userService.getUserByusername(username).get());
            model.addAttribute("followers",followService.getfollowers(userService.getUserByusername(username).get()));
            model.addAttribute("followeds",followService.getfollowed(userService.getUserByusername(username).get()));
            model.addAttribute("twots",twots);
            return "user";
        }
        return "error";
    }

    @GetMapping(value="/profile")
    public RedirectView profile(HttpSession session){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long id = (long) session.getAttribute("userid");
            String username = userService.getUserById(id).get().getusername();
            return new RedirectView("/user/" + username);
        }
        return new RedirectView("/error");
    }

    @GetMapping(value="/editprofile")
    public String editprofile(HttpSession session,Model model){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long id = (long) session.getAttribute("userid");
            if (userService.getUserById(id).isPresent()) {
                model.addAttribute("user", userService.getUserById(id).get());
                return "editprofile";
            }
        }
        return "error";
    }

    @PostMapping(value="/editprofile")
    public RedirectView editprofilepost(@RequestParam("nickname") String nickname, @RequestParam("biography") String biography,
                                        @RequestParam("profilePic")MultipartFile[] files, HttpSession session) throws IOException {
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long id = (long) session.getAttribute("userid");
            if (userService.getUserById(id).isPresent()) {
                User user = userService.getUserById(id).get();
                user.setbiography(biography);
                user.setnickname(nickname);
                files[0].getOriginalFilename();
                if (!files[0].getOriginalFilename().equals("")) {
                    user.setpicture("../img/profilepics/"+user.getusername()+".png");
                    FileUploadUtil.saveFile("profilepictures", user.getusername()+".png", files[0]);

                }
                userService.update(user);
            }
        }
        return new RedirectView("/profile");
    }
}
