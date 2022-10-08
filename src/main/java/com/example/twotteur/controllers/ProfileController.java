package com.example.twotteur.controllers;

import com.example.twotteur.models.Twot;
import com.example.twotteur.services.TwotService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private TwotService twotService;

    @GetMapping(value="/user/{nickname}")
    public String userProfile(@PathVariable String nickname, Model model){
        if(userService.getUserByNickname(nickname)!=null){
            List<Twot> twots=twotService.getTwots(userService.getUserByNickname(nickname));
            model.addAttribute("user",userService.getUserByNickname(nickname));
            model.addAttribute("twots",twots);
            return "user";
        }
        return "error";
    }

    @GetMapping(value="/profile")
    public RedirectView profile(HttpSession session){
        int id=(int)session.getAttribute("userid");
        String nickname=userService.getUserById(id).get().getNickname();
        return new RedirectView("/user/"+nickname);
    }
}
