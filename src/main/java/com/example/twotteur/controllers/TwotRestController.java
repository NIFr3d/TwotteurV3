package com.example.twotteur.controllers;

import com.example.twotteur.services.TwotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class TwotRestController {
    @Autowired
    private TwotService twotService;

    @GetMapping(value="/countanswers/{id}")
    public int countAnswer(@PathVariable int id){
        return twotService.countAnswers(id);
    }
    @GetMapping(value="/countlikes/{id}")
    public int countLikes(@PathVariable int id){
        return twotService.countLikes(id);
    }

    @GetMapping(value="/like/{id}")
    public int like(@PathVariable int id, HttpSession session){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged){
            int userid=(int)session.getAttribute("userid");
            if(twotService.userAlreadyLiked(id,userid)){
                if(twotService.removeLike(id,userid)) return 2;
            }else{
                if(twotService.addLike(id,userid)) return 1;
            }
        }
        return 0;
    }

    @GetMapping(value="/didilike/{id}")
    public int didilike(@PathVariable int id,HttpSession session){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            int userid = (int) session.getAttribute("userid");
            if(twotService.userAlreadyLiked(id,userid)) return 1;
        }
        return 0;
    }
}
