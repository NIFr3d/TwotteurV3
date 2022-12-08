package com.example.twotteur.controllers;

import com.example.twotteur.models.Twot;
import com.example.twotteur.models.Retwot;
import com.example.twotteur.services.RetwotService;
import com.example.twotteur.services.TwotRetwotService;
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

    @Autowired
    private RetwotService retwotService;

    @Autowired
    private TwotRetwotService trtService;

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

    @GetMapping("/simpleretwot/{id}")
    public int simpleretwot(@PathVariable long id,HttpSession session) {
        boolean isLogged = false;
        if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");
        if (isLogged) {
            long userid = (long) session.getAttribute("userid");
            if (retwotService.userAlreadyRT(id, userid)) {
                if (retwotService.removeRT(id, userid)) return 2;
            } else {
                if (retwotService.addRT(id, userid)) return 1;
            }
        }
        return 0;
    }

    @GetMapping("/didirt/{id}")
    public int didirt(@PathVariable long id,HttpSession session) {
        boolean isLogged = false;
        if (session.getAttribute("isLogged") != null) isLogged = (boolean) session.getAttribute("isLogged");
        if (isLogged) {
            long userid = (long) session.getAttribute("userid");
            if (retwotService.userAlreadyRT(id, userid)) return 1;
        }
        return 0;
    }

    @GetMapping("/countretwots/{id}")
    public int countRetwots(@PathVariable long id){
        if(twotService.getTwotById(id).isPresent()){
            return retwotService.getRetwotCount(twotService.getTwotById(id).get());
        }
        return 0;
    }

    @GetMapping("/getprevious/{user}/{type}/{id}")
    public String getPrevious(@PathVariable long user,@PathVariable String type,@PathVariable long id) {
        String result = "";
        if (userService.getUserById(user).isPresent()){
            if (type.equals("twot")) {
                if (twotService.getTwotById(id).isPresent()) {
                    Object rsl=trtService.getPreviousFromUser(userService.getUserById(user).get(),twotService.getTwotById(id).get());
                    result=convert(rsl);
                }
            } else if (type.equals("retwot")) {
                if (retwotService.getById(id).isPresent()) {
                    Object rsl=trtService.getPreviousFromUser(userService.getUserById(user).get(),retwotService.getById(id).get());
                    result=convert(rsl);
                }
            } else if(type.equals("last")){
                Object rsl=trtService.getPreviousFromUser(userService.getUserById(user).get(),null);
                result=convert(rsl);
            }
        }
        return result;
    }


    private String convert(Object obj){
        String result="";
        try{
            Twot twot=(Twot)obj;
            result="{\"Type\":\"twot\",\"Id\":"+twot.getId()+"}";
        }catch(Exception e){
            try{
                Retwot retwot=(Retwot)obj;
                result="{\"Type\":\"retwot\",\"Id\":"+retwot.getId()+"}";
            }catch(Exception e2){
            }
        }
        return result;
    }
}
