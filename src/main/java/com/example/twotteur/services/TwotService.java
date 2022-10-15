package com.example.twotteur.services;

import com.example.twotteur.models.LikeAsso;
import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import com.example.twotteur.repositories.LikeRepository;
import com.example.twotteur.repositories.TwotRepository;
import com.example.twotteur.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;


    public List<Twot> getTwots(User user){
        List<Twot> twots=new ArrayList<>();
        twotRepository.findTwotsByUser(user).forEach(twots::add);
        return twots;
    }
    public List<Long> getTwotsId(User user){
        List<Long> ids=new ArrayList<>();
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

    public Twot getTwotById(long id){
        return twotRepository.getReferenceById(id);
    }
    public List<Twot> getAnswersByTwotId(long id) {
        return twotRepository.findTwotsByOriginaltwot(getTwotById(id));
    }
    public Optional<User> getUserByTwotId(long id){
        Optional<User> user=Optional.empty();
        if(twotRepository.findFirstById(id).isPresent()) user=Optional.of(twotRepository.findFirstById(id).get().getUser());
        return user;
    }

    public int countAnswers(long id) {
        int count=0;
        if(twotRepository.findFirstById(id).isPresent()) count=twotRepository.countByOriginaltwot(twotRepository.findFirstById(id).get());
        return count;
    }

    public int countLikes(long id) {
        int count=0;
        if(twotRepository.findFirstById(id).isPresent()) count=likeRepository.countByTwot(twotRepository.findFirstById(id).get());
        return count;
    }

    public boolean addLike(long twotid, long userid) {
        if(twotRepository.findFirstById(twotid).isPresent()){
            if(userRepository.findById(userid).isPresent()){
                likeRepository.save(new LikeAsso(twotRepository.findFirstById(twotid).get(),userRepository.findById(userid).get()));
                return true;
            }
        }
        return false;
    }
    public boolean removeLike(long twotid,long userid) {
        if(twotRepository.findFirstById(twotid).isPresent()) {
            if (userRepository.findById(userid).isPresent()) {
                if (likeRepository.findByTwotAndUser(twotRepository.findFirstById(twotid).get(), userRepository.findById(userid).get()).isPresent()) {
                    likeRepository.delete(likeRepository.findByTwotAndUser(twotRepository.findFirstById(twotid).get(), userRepository.findById(userid).get()).get());
                    return true;
                }
            }
        }
        return false;
    }
    public boolean userAlreadyLiked(long twotid,long userid) {
        if (twotRepository.findFirstById(twotid).isPresent()) {
            if (userRepository.findById(userid).isPresent()) {
                if (likeRepository.findByTwotAndUser(twotRepository.findFirstById(twotid).get(), userRepository.findById(userid).get()).isPresent()) {
                    return true;
                }
            }
        }
        return false;
    }
}
