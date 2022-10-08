package com.example.twotteur.services;

import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import com.example.twotteur.repositories.LikeRepository;
import com.example.twotteur.repositories.TwotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TwotService {

    @Autowired
    private TwotRepository twotRepository;

    @Autowired
    private LikeRepository likeRepository;

    public List<Twot> getTwots(User user){
        List<Twot> twots=new ArrayList<>();
        twotRepository.findTwotsByUser(user).forEach(twots::add);
        return twots;
    }
    public List<Integer> getTwotsId(User user){
        List<Integer> ids=new ArrayList<>();
        List<Twot> twots=twotRepository.findTwotsByUser(user);
        for(int i=0;i< twots.size();i++) {
            ids.add(twots.get(i).getId());
        }
        return ids;
    }

    public void newTweet(User user, String text) {
        twotRepository.save(new Twot(user,text));
    }
    public void newAnswer(User user,String text,Twot twot){
        twotRepository.save(new Twot(user,text,twot));
    }

    public Twot getTwotById(int id){
        return twotRepository.getReferenceById(id);
    }
    public List<Twot> getAnswersByTwotId(int id) {
        return twotRepository.findTwotsByOriginaltwot(getTwotById(id));
    }
    public Optional<User> getUserByTwotId(int id){
        Optional<User> user=Optional.empty();
        if(twotRepository.findFirstById(id).isPresent()) user.of(twotRepository.findFirstById(id).get().getUser());
        return user;
    }

    public int countAnswers(int id) {
        int count=0;
        if(twotRepository.findFirstById(id).isPresent()) count=twotRepository.countByOriginaltwot(twotRepository.findFirstById(id).get());
        return count;
    }

    public int countLikes(int id) {
        int count=0;
        if(twotRepository.findFirstById(id).isPresent()) count=likeRepository.countByTwot(twotRepository.findFirstById(id).get());
        return count;
    }
}
