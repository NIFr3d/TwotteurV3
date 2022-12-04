package com.example.twotteur.controllers;

import com.example.twotteur.models.Twot;
import com.example.twotteur.services.TwotService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
public class TwotRestController {
    @Autowired
    private TwotService twotService;
    @Autowired
    private UserService userService;

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
        if(twotService.getTwotById(id).isPresent()) {
            boolean isLogged = false;
            if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");
            if (isLogged) {
                if (twotService.getTwotById(id).get().getUser().getId() == (long) session.getAttribute("userid")) {
                    twotService.deleteTwotById(id);
                    return 1;
                }
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
    @PostMapping(value = "/resttwot")
    public int newTwot(@RequestParam("id") long id, @RequestParam("text") String text){
        twotService.newTweet(userService.getUserById(id).get(),text);
        return 1;
    }

    @GetMapping("/original/{id}")
    public String originalByAnswerId(@PathVariable long id){
        String result="";
        if(twotService.getOriginalByAnswer(id).isPresent()){
            Twot twot=twotService.getOriginalByAnswer(id).get();
            result="{\"user\":\""+twot.getUser().getusername()+"\",\"original\":\""+twot.getId()+"\"}";
        }
        return result;
    }
}
