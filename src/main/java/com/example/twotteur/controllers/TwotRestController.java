package com.example.twotteur.controllers;

import com.example.twotteur.services.TwotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class TwotRestController {
    @Autowired
    private TwotService twotService;

    @GetMapping(value="/countanswers/{id}")
    public int countAnswer(@PathVariable long id){
        return twotService.countAnswers(id);
    }
    @GetMapping(value="/countlikes/{id}")
    public int countLikes(@PathVariable long id){
        return twotService.countLikes(id);
    }

    @GetMapping(value="/like/{id}")
    public int like(@PathVariable int id, HttpSession session){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged){
            long userid=(long)session.getAttribute("userid");
            if(twotService.userAlreadyLiked(id,userid)){
                if(twotService.removeLike(id,userid)) return 2;
            }else{
                if(twotService.addLike(id,userid)) return 1;
            }
        }
        return 0;
    }

    @DeleteMapping(value="/twot")
    public int deleteTwot(@RequestParam("id") long id, HttpSession session){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            if (twotService.getTwotById(id).getUser().getId() == (long) session.getAttribute("userid")) {
                twotService.deleteTwotById(id);
                return 1;
            }
        }
        return 0;
    }

    @GetMapping(value="/didilike/{id}")
    public int didilike(@PathVariable long id,HttpSession session){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long userid = (long) session.getAttribute("userid");
            if(twotService.userAlreadyLiked(id,userid)) return 1;
        }
        return 0;
    }
}
