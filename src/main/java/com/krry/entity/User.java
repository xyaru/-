package com.krry.entity;

import com.krry.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体类，类名对应的是MongoDB的集合名（表名），若没有，则自动创建
 * @author asusaad
 *
 */
public class User {

	@Autowired
	private BlogRepository blgRepository;

    private String _id;
	private String username;
	private String nickname;
    private String password;
	private int followNumber;
	private int fansNumber;
	private List<String> flwList;
	private List<String> fansList;
	private List<String> myBlogs;
	private List<String> ClBlogs;
	
	public User(){
		
	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String cid) {
		this._id = cid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void addOneBlog(Blog blg){
		this.myBlogs.add(blg.get_id());
	}
	public int getFollowNumber() {
		return followNumber;
	}
	public void setFollowNumber(int followNumber) {
		this.followNumber = followNumber;
	}
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

	public List<String> getFlwList() {
		return flwList;
	}

	public void setFlwList(List<String> flwList) {
		this.flwList = flwList;
	}

	public List<Blog> getClBlogs() {
		List<Blog> blgs = new ArrayList<>();
		for(int i = 0 ; i<ClBlogs.size();i++){
			blgs.add(blgRepository.findBlogBy_id(myBlogs.get(i)));
		}
		return blgs;
	}

	public void setClBlogs(List<String> clBlogs) {
		ClBlogs = clBlogs;
	}

	public List<Blog> getMyBlogs() {
		List<Blog> blgs = new ArrayList<>();
		for(int i = 0 ; i<myBlogs.size();i++){
			blgs.add(blgRepository.findBlogBy_id(myBlogs.get(i)));
		}
		return blgs;
	}

	public void setMyBlogs(List<String> myBlogs) {
		this.myBlogs = myBlogs;
	}

	public List<String> getFansList() {
		return fansList;
	}

	public void setFansList(List<String> fansList) {
		this.fansList = fansList;
	}

	public int getFansNumber() {
		return fansNumber;
	}

	public void setFansNumber(int fansNumber) {
		this.fansNumber = fansNumber;
	}

	public void collectOneBlog(Blog blg){
		this.ClBlogs.add(blg.get_id());
	}
	public void dcollectOneBlog(Blog blg){
		this.ClBlogs.remove(blg.get_id());
	}

	public void followOne(User usr){
		this.flwList.add(usr.getUsername());
		this.setFollowNumber(this.getFollowNumber()+1);
	}
	public void addFans(User usr){
		this.fansList.add(usr.getUsername());
		this.setFansNumber(this.getFansNumber()+1);
		System.out.println(" fans "+this.getFansNumber());

	}
	
	
}
