package com.example.twotteur.services;

import com.example.twotteur.models.Retwot;
import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import com.example.twotteur.repositories.RetwotRepository;
import com.example.twotteur.repositories.TwotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
            result=compare(lastTwot,lastRetwot);
        }else{
            Date lastDate;
            try{
                lastDate=((Twot) last).getDate();
            }catch (Exception e){
                lastDate=((Retwot) last).getDate();
            }
            Retwot lastRetwot=retwotRepository.findFirstByUserAndCreatedatBeforeOrderByCreatedatDesc(user,lastDate);
            Twot lastTwot=twotRepository.findFirstByUserAndCreatedatBeforeOrderByCreatedatDesc(user,lastDate);
            result=compare(lastTwot,lastRetwot);
        }
        return result;
    }

    public Object getPreviousFromUsers(List<User> users, Object last) {
        Object result;
        if (last == null) {
            Twot lastTwot = twotRepository.findFirstByUserIsInOrderByCreatedatDesc(users);
            Retwot lastRetwot = retwotRepository.findFirstByUserIsInOrderByCreatedatDesc(users);
            result = compare(lastTwot, lastRetwot);
        } else{
            Date lastDate;
            try{
                lastDate=((Twot) last).getDate();
            }catch (Exception e){
                lastDate=((Retwot) last).getDate();
            }
            Retwot lastRetwot=retwotRepository.findFirstByUserIsInAndCreatedatBeforeOrderByCreatedatDesc(users,lastDate);
            Twot lastTwot=twotRepository.findFirstByUserIsInAndCreatedatBeforeOrderByCreatedatDesc(users,lastDate);
            result=compare(lastTwot,lastRetwot);
        }
        return result;
    }
    private Object compare(Twot lastTwot,Retwot lastRetwot){
        Object result;
        if(lastTwot==null && lastRetwot!=null){
            result=lastRetwot.getId();
        }else if(lastRetwot==null && lastTwot!=null){
            result=lastTwot;
        }else if(lastRetwot==null && lastTwot==null){
            result=null;
        }else if(lastTwot.getDate().after(lastRetwot.getDate())) {
            result=lastTwot;
        }else {
            result=lastRetwot;
        }
        return result;
    }
}
