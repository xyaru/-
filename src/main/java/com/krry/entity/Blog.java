package com.krry.entity;

import com.krry.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Blog implements Comparable<Blog>{
    @Autowired
    private ReviewRepository revRepository;

    @Id
    private String _id;
    private String uid;
    private String username;
    private String title;
    private Object content;
    private String type;
    private int collectedTimes;

    public Blog() {
    }

    public Blog(String title, Object content) {
        this.title = title;
        this.content = content;
    }
    public Blog(String username,String title, Object content) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.collectedTimes = 0;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public int getCollectedTimes() {
        return collectedTimes;
    }

//    public List<Review> Retreviews() {
//        List<Review> revs = new ArrayList<>();
//        System.out.println("hr");
//        revs = revRepository.findReviewsByBid(this._id);
//        return revs;
//    }


    public void setCollectedTimes(int collectedTimes) {
        this.collectedTimes = collectedTimes;
    }
    public void addCollectedTimes() {
        this.collectedTimes += 1;
    }
    public void redCollectedTimes() {
        this.collectedTimes += 1 ;
    }

    @Override
    public int compareTo(Blog blg){
        int i = this.getCollectedTimes() - blg.getCollectedTimes();
        return -i;
    }

    /*
    public void addReview(Review review){
        this.reviews.add(review.get_id());
    }
    public void minsReview(Review review){
        this.reviews.remove(review);
    }*/

}
