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
    public int countLikes(@PathVariable int id, HttpSession session){
        return twotService.countLikes(id);
    }

}
