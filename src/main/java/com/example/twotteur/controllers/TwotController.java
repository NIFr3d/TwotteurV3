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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
    public RedirectView newTwot(@RequestParam("id") long id,@RequestParam("text") String text, HttpServletRequest request){
        twotService.newTweet(userService.getUserById(id).get(),text);
        return new RedirectView(request.getHeader("Referer"));
    }


    @PostMapping(value="/answer")
    public RedirectView answer(@RequestParam("userid") String userid, @RequestParam("originalid") long originalid, @RequestParam("text") String text, HttpServletRequest request){
        if(userService.getUserByusername(userid).isPresent() && twotService.getTwotById(originalid).isPresent()) {
            twotService.newAnswer(userService.getUserByusername(userid).get(), text, twotService.getTwotById(originalid).get());
        }
        return new RedirectView(request.getHeader("referer"));
    }

    @GetMapping(value="/twot/{id}")
    public ModelAndView fullTwot(@PathVariable long id){
        ModelAndView mav=new ModelAndView();
        if(twotService.getTwotById(id).isPresent()) {
            mav.addObject("twot", twotService.getTwotById(id).get());
            List<Long> twots = new ArrayList<>();
            for (Twot twot : twotService.getAnswersByTwotId(id)) {
                twots.add(twot.getId());
            }
            mav.addObject("twots", twots);
            if (twotService.getUserByTwotId(id).isPresent()) {
                mav.addObject("user", twotService.getUserByTwotId(id).get());
            }
            mav.setViewName("twotfull");
        }else{
            mav.addObject("status",404);
            mav.setViewName("error");
        }
        return mav;
    }

    @GetMapping(value="/smalltwot/{id}")
    public ModelAndView smallTwot(@PathVariable long id){
        ModelAndView mav=new ModelAndView();
        if(twotService.getTwotById(id).isPresent()) {
            mav.addObject("twot", twotService.getTwotById(id).get());
            if (twotService.getUserByTwotId(id).isPresent()) {
                mav.addObject("user", twotService.getUserByTwotId(id).get());
            }
            mav.setViewName("smalltwot");
        }else{
            mav.addObject("status",404);
            mav.setViewName("error");
        }
        return mav;
    }
}
