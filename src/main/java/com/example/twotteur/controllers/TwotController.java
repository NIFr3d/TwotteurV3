package com.example.twotteur.controllers;

import com.example.twotteur.models.Twot;
import com.example.twotteur.services.TwotService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TwotController {
    @Autowired
    private TwotService twotService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/twot")
    public RedirectView newTwot(@RequestParam("id") long id,@RequestParam("text") String text){
        twotService.newTweet(userService.getUserById(id).get(),text);
        return new RedirectView("/home"); //TODO: redirect sur la page d'où on vient
    }


    @PostMapping(value="/answer")
    public RedirectView answer(@RequestParam("userid") String userid, @RequestParam("originalid") long originalid, @RequestParam("text") String text){
        if(userService.getUserByusername(userid).isPresent() && twotService.getTwotById(originalid).isPresent()) {
            twotService.newAnswer(userService.getUserByusername(userid).get(), text, twotService.getTwotById(originalid).get());
        }
        return new RedirectView("/home"); //TODO: redirect sur la page d'où on vient
    }

    @GetMapping(value="/twot/{id}")
    public ModelAndView fullTwot(@PathVariable long id, Model model){
        if(twotService.getTwotById(id).isPresent()) {
            model.addAttribute("twot", twotService.getTwotById(id).get());
            List<Long> twots = new ArrayList<>();
            for (Twot twot : twotService.getAnswersByTwotId(id)) {
                twots.add(twot.getId());
            }
            model.addAttribute("twots", twots);
            if (twotService.getUserByTwotId(id).isPresent()) {
                model.addAttribute("user", twotService.getUserByTwotId(id).get());
            }
            return new ModelAndView("twotfull");
        }
        return new ModelAndView("redirect:/error?e=404");
    }

    @GetMapping(value="/smalltwot/{id}")
    public ModelAndView smallTwot(@PathVariable long id,Model model){
        if(twotService.getTwotById(id).isPresent()) {
            model.addAttribute("twot", twotService.getTwotById(id).get());
            if (twotService.getUserByTwotId(id).isPresent()) {
                model.addAttribute("user", twotService.getUserByTwotId(id).get());
            }
            return new ModelAndView("smalltwot");
        }
        return new ModelAndView("redirect:/error?e=404");
    }
}
