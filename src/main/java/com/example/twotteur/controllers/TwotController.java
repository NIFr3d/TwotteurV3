package com.example.twotteur.controllers;

import com.example.twotteur.services.TwotService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TwotController {
    @Autowired
    private TwotService twotService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/twot")
    public RedirectView newTwot(@RequestParam("id") int id,@RequestParam("text") String text){
        twotService.newTweet(userService.getUserById(id).get(),text);
        return new RedirectView("/home");
    }
}
