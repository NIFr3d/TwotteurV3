package com.example.twotteur.controllers;

import com.example.twotteur.models.Twot;
import com.example.twotteur.services.RetwotService;
import com.example.twotteur.services.TwotService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TwotController {
    @Autowired
    private TwotService twotService;
    @Autowired
    private UserService userService;
    @Autowired
    private RetwotService retwotService;

    @PostMapping(value = "/twot")
    public RedirectView newTwot(@RequestParam("id") long id,@RequestParam("text") String text, HttpServletRequest request){
        twotService.newTweet(userService.getUserById(id).get(),text);
        return new RedirectView(request.getHeader("Referer"));
    }


    @PostMapping(value="/answer")
    public RedirectView answer(@RequestParam("originalid") long originalid, @RequestParam("text") String text, HttpServletRequest request, HttpSession session){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged && twotService.getTwotById(originalid).isPresent()){
            long userid=(long)session.getAttribute("userid");
            twotService.newAnswer(userService.getUserById(userid).get(),text,twotService.getTwotById(originalid).get());
        }
        return new RedirectView(request.getHeader("referer"));
    }

    @GetMapping(value="/twot/{id}")
    public ModelAndView fullTwot(@PathVariable long id){
        ModelAndView mav=new ModelAndView();
        if(twotService.getTwotById(id).isPresent()) {
            mav.addObject("twot", twotService.getTwotById(id).get());
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
            mav.addObject("isLastRT",true);
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
    @GetMapping(value="/smalltwotnort/{id}")
    public ModelAndView smallTwotNoRT(@PathVariable long id){
        ModelAndView mav=new ModelAndView();
        if(twotService.getTwotById(id).isPresent()) {
            mav.addObject("twot", twotService.getTwotById(id).get());
            mav.addObject("isLastRT",false);
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
    @GetMapping(value="retwot/{id}")
    public ModelAndView retwot(@PathVariable long id){
        ModelAndView mav=new ModelAndView();
        if(retwotService.getById(id).isPresent()){
            mav.addObject("retwot",retwotService.getById(id).get());
            mav.setViewName("retwot");
            mav.addObject("twot",retwotService.getById(id).get().getTwot());
            if(twotService.getUserByTwotId(retwotService.getById(id).get().getTwot().getId()).isPresent()){
                mav.addObject("user",twotService.getUserByTwotId(retwotService.getById(id).get().getTwot().getId()).get());
            }
        }else{
            mav.addObject("status",404);
            mav.setViewName("error");
        }
        return mav;
    }
}
