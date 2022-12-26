package com.example.twotteur.services;

import com.example.twotteur.models.*;
import com.example.twotteur.repositories.*;
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
    @Autowired
    private AnswerAssoRepository answerAssoRepository;
    @Autowired
    private RetwotRepository retwotRepository;
    @Autowired
    private RetwotAssoRepository retwotAssoRepository;

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
        twotRepository.save(new Twot(user,text,false));
    }
    public void newAnswer(User user,String text,Twot twot){
        Twot newtwot=new Twot(user,text,true);
        twotRepository.save(newtwot);
        answerAssoRepository.save(new AnswerAsso(twot,newtwot));
    }
    public void newQuoteTweet(User user,String text,Twot twot){
        Twot newtwot=new Twot(true,user,text);
        twotRepository.save(newtwot);
        retwotAssoRepository.save(new RetwotAsso(twot,newtwot));
    }

    public Optional<Twot> getTwotById(long id){

        return twotRepository.findById(id);
    }
    public Twot getPreviousAnswerFromTwot(Twot original,Twot previousAnswer){
        Twot result;
        if(previousAnswer==null){
            AnswerAsso rslt=answerAssoRepository.findFirstByOriginaltwotOrderByCreatedatDesc(original);
            if(rslt!=null) result=rslt.getanswer();
            else result=null;
        }else{
            AnswerAsso rslt=answerAssoRepository.findFirstByOriginaltwotAndCreatedatBeforeOrderByCreatedatDesc(original,previousAnswer.getDate());
            if(rslt!=null )result=rslt.getanswer();
            else result=null;
        }
        return result;
    }

    public Optional<User> getUserByTwotId(long id){
        Optional<User> user=Optional.empty();
        if(twotRepository.findById(id).isPresent()) user=Optional.of(twotRepository.findById(id).get().getUser());
        return user;
    }

    public Optional<Twot> getOriginalByAnswer(long id){
        Optional<Twot> result=Optional.empty();
        if(twotRepository.findById(id).isPresent()){
            if(answerAssoRepository.findByAnswer(twotRepository.findById(id).get()).isPresent()){
                result=Optional.of(answerAssoRepository.findByAnswer(twotRepository.findById(id).get()).get().getoriginal());
            }
        }

        return result;
    }
    public Optional<Twot> getOriginalByRetwot(long id){
        Optional<Twot> result=Optional.empty();
        if(twotRepository.findById(id).isPresent()){
            if(retwotAssoRepository.findByRetwot(twotRepository.findById(id).get()).isPresent()){
                result=Optional.of(retwotAssoRepository.findByRetwot(twotRepository.findById(id).get()).get().getoriginal());
            }
        }
        return result;
    }

    public int countAnswers(long id) {
        int count=0;
        if(twotRepository.findById(id).isPresent()) count=answerAssoRepository.countByOriginaltwot(twotRepository.findById(id).get());
        return count;
    }

    public int countLikes(long id) {
        int count=0;
        if(twotRepository.findById(id).isPresent()) count=likeRepository.countByTwot(twotRepository.findById(id).get());
        return count;
    }

    public boolean addLike(long twotid, long userid) {
        if(twotRepository.findById(twotid).isPresent()){
            if(userRepository.findById(userid).isPresent()){
                likeRepository.save(new LikeAsso(twotRepository.findById(twotid).get(),userRepository.findById(userid).get()));
                return true;
            }
        }
        return false;
    }
    public boolean removeLike(long twotid,long userid) {
        if(twotRepository.findById(twotid).isPresent()) {
            if (userRepository.findById(userid).isPresent()) {
                if (likeRepository.findByTwotAndUser(twotRepository.findById(twotid).get(), userRepository.findById(userid).get()).isPresent()) {
                    likeRepository.delete(likeRepository.findByTwotAndUser(twotRepository.findById(twotid).get(), userRepository.findById(userid).get()).get());
                    return true;
                }
            }
        }
        return false;
    }
    public boolean userAlreadyLiked(long twotid,long userid) {
        if (twotRepository.findById(twotid).isPresent()) {
            if (userRepository.findById(userid).isPresent()) {
                if (likeRepository.findByTwotAndUser(twotRepository.findById(twotid).get(), userRepository.findById(userid).get()).isPresent()) {
                    return true;
                }
            }
        }
        return false;
    }


    public void deleteTwotById(long id) {
        if(getTwotById(id).isPresent()) {
            Twot twot = getTwotById(id).get();
            List<Twot> toDelete = new ArrayList<>();
            toDelete.add(twot);
            List<AnswerAsso> toTreat = new ArrayList<>();
            for (AnswerAsso answerAsso : answerAssoRepository.findByOriginaltwot(twot)) {
                toTreat.add(answerAsso);
            }
            while (toTreat.size() > 0) {
                List<AnswerAsso> newtreat = new ArrayList<>();
                for (AnswerAsso answerAsso : toTreat) {
                    newtreat.addAll(answerAssoRepository.findByOriginaltwot(answerAsso.getanswer()));
                    toDelete.add(answerAsso.getanswer());
                }
                toTreat = newtreat;
            }
            List<RetwotAsso> toTreat2 = new ArrayList<>();
            for (RetwotAsso retwotAsso : retwotAssoRepository.getRetwotAssosByOriginaltwot(twot)){
                toTreat2.add(retwotAsso);
            }
            while (toTreat2.size() > 0) {
                List<RetwotAsso> newtreat = new ArrayList<>();
                for (RetwotAsso retwotAsso : toTreat2) {
                    newtreat.addAll(retwotAssoRepository.getRetwotAssosByOriginaltwot(retwotAsso.getretwot()));
                    toDelete.add(retwotAsso.getretwot());
                }
                toTreat2 = newtreat;
            }
            for (int i = 1; i <= toDelete.size(); i++) {
                Twot deletetwot = toDelete.get(toDelete.size() - i);
                if (answerAssoRepository.findByAnswer(deletetwot).isPresent())
                    answerAssoRepository.delete(answerAssoRepository.findByAnswer(deletetwot).get());
                if (retwotAssoRepository.findByRetwot(deletetwot).isPresent())
                    retwotAssoRepository.delete(retwotAssoRepository.findByRetwot(deletetwot).get());
                List<LikeAsso> deletelikes = likeRepository.getByTwot(deletetwot);
                for (LikeAsso like : deletelikes) {
                    likeRepository.delete(like);
                }
                List<Retwot> deleteretwots = retwotRepository.findByTwot(deletetwot);
                for (Retwot retwot : deleteretwots) {
                    retwotRepository.delete(retwot);
                }
                twotRepository.delete(deletetwot);
            }
        }
    }

    public boolean isLastRT(Twot twot) {
        return(!retwotAssoRepository.findByOriginaltwot(twot).isPresent());
    }
}
