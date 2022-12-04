package com.example.twotteur.services;

import com.example.twotteur.models.Retwot;
import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import com.example.twotteur.repositories.RetwotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetwotService {
    @Autowired
    private RetwotRepository retwotRepository;


    public int getRetwotCount(Twot twot){
        return retwotRepository.countRetwotsByTwot(twot);
    }

    public void RetwotWithText(Twot twot, User user, String text){
        retwotRepository.save(new Retwot(user,text,twot));
    }
    public void RetwotWithoutText(Twot twot,User user){
        retwotRepository.save(new Retwot(user,twot));
    }
    public void deleteRetwot(Retwot retwot){
        retwotRepository.delete(retwot);
    }
    public List<Retwot> getByUser(User user){
        return retwotRepository.findRetwotsByUser(user);
    }
    public Optional<Retwot> getById(long id){
        return retwotRepository.findById(id);
    }
}
