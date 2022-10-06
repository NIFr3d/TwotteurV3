package com.example.twotteur.services;

import com.example.twotteur.models.TwotModel;
import com.example.twotteur.models.UserModel;
import com.example.twotteur.repositories.LikeRepository;
import com.example.twotteur.repositories.TwotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwotService {

    @Autowired
    private TwotRepository twotRepository;

    @Autowired
    private LikeRepository likeRepository;

    public List<TwotModel> getTwots(UserModel user){
        List<TwotModel> twots=new ArrayList<>();
        twotRepository.findTwotModelsByUser(user).forEach(twots::add);
        return twots;
    }

    public void newTweet(UserModel user, String text) {
        twotRepository.save(new TwotModel(user,text));
    }
    public void newAnswer(UserModel user,String text,TwotModel twot){
        twotRepository.save(new TwotModel(user,text,twot));
    }

    public TwotModel getTwotById(int id){
        return twotRepository.getReferenceById(id);
    }
    public List<TwotModel> getAnswersByTwotId(int id) {
        return twotRepository.findTwotModelsByOriginaltwot(getTwotById(id));
    }
    public UserModel getUserByTwotId(int id){
        return twotRepository.findFirstById(id).getUser();
    }

    public Integer countAnswers(int id) {
        TwotModel twot=getTwotById(id);
        return twotRepository.countByOriginaltwot(twot);
    }
    public Integer countLikes(int id){
        TwotModel twot=getTwotById(id);
        return likeRepository.countByTwot(twot);
    }
}
