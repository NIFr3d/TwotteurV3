package com.example.twotteur.services;

import com.example.twotteur.models.Retwot;
import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import com.example.twotteur.repositories.RetwotRepository;
import com.example.twotteur.repositories.TwotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TwotRetwotService {
    @Autowired
    private TwotRepository twotRepository;
    @Autowired
    private RetwotRepository retwotRepository;

    public Object getPreviousFromUser(User user,Object last) {
        Object result;
        if(last==null){
            Twot lastTwot=twotRepository.findFirstByUserOrderByCreatedatDesc(user);
            Retwot lastRetwot=retwotRepository.findFirstByUserOrderByCreatedatDesc(user);
            if(lastTwot==null && lastRetwot!=null){
                result=lastRetwot.getId();
            }else if(lastRetwot==null && lastTwot!=null){
                result=lastTwot;
            }else if(lastTwot.getDate().after(lastRetwot.getDate())) {
                result=lastTwot;
            }else {
                result=lastRetwot;
            }
        }else{
            Date lastDate;
            try{
                lastDate=((Twot) last).getDate();
            }catch (Exception e){
                lastDate=((Retwot) last).getDate();
            }
            Retwot lastRetwot=retwotRepository.findFirstByUserAndCreatedatBeforeOrderByCreatedatDesc(user,lastDate);
            Twot lastTwot=twotRepository.findFirstByUserAndCreatedatBeforeOrderByCreatedatDesc(user,lastDate);
            if(lastTwot==null && lastRetwot!=null){
                result=lastRetwot.getId();
            }else if(lastRetwot==null && lastTwot!=null){
                result=lastTwot;
            }
            else if(lastTwot.getDate().after(lastRetwot.getDate())) {
                result=lastTwot;
            }else {
                result=lastRetwot;
            }
        }
        return result;
    }

}
