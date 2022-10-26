package com.example.twotteur.controllers;

import com.example.twotteur.models.Twot;
import com.example.twotteur.services.TwotService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpRequest;

@Controller
public class TwotController {
    @Autowired
    private TwotService twotService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/twot")
    public RedirectView newTwot(@RequestParam("id") long id,@RequestParam("text") String text){
        twotService.newTweet(userService.getUserById(id).get(),text);
        return new RedirectView("/home");
    }

    @PostMapping(value="/answer")
    public RedirectView answer(@RequestParam("userid") String userid, @RequestParam("originalid") long originalid, @RequestParam("text") String text){
        if(userService.getUserByusername(userid).isPresent()) {
            twotService.newAnswer(userService.getUserByusername(userid).get(), text, twotService.getTwotById(originalid));
        }
        return new RedirectView("/home");
    }

    @GetMapping(value="/twot/{id}")
    public String fullTwot(@PathVariable long id,Model model){
        model.addAttribute("twot",twotService.getTwotById(id));
        model.addAttribute("twots",twotService.getAnswersByTwotId(id));
        if(twotService.getUserByTwotId(id).isPresent()) {
            model.addAttribute("user", twotService.getUserByTwotId(id).get());
        }
        return "twotfull";
    }
}
