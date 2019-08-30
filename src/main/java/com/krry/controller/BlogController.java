package com.krry.controller;


import com.krry.entity.*;
import com.krry.repository.BlogRepository;
import com.krry.repository.ReviewRepository;
import com.krry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import org.unbescape.html.HtmlEscape;

import java.util.*;

@Controller
@RequestMapping(value = "/api")
public class BlogController  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blgRepository;
    @Autowired
    private ReviewRepository revRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    //要求 content string
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/searchContent")
    public result searchForSomeC(@RequestBody Blog blg){
        String _content = HtmlUtils.htmlEscape(blg.getTitle());
        List<Blog> blgs = blgRepository.findByContentLike(_content);
        List<Blog> blgt = blgRepository.findByTitleLike(_content);
        for(int i = 0;i < blgt.size();i++){
            int j;
            for(j = 0;j < blgs.size(); j++){
                if(blgs.get(j).get_id().equals(blgt.get(i).get_id()))
                    break;
            }
            if(j == blgs.size()){
                blgs.add(blgt.get(i));
            }
        }
        System.out.println("search it");
        return  new result(200,"search it",blgs);
    }

    // 返回blog的所有评论
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/blogReview")
    public result showReview(@RequestBody Blog tblg){

        List<Review> reviews = revRepository.findReviewsByBid(tblg.get_id());
        return  new result(400,"return reviews",reviews);
    }

    //发布评论
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/postReview")
    //username content bid
    public result postReview(@RequestBody newReview nr){
        String bid = HtmlUtils.htmlEscape(nr.getBid());
        Blog blg = blgRepository.findBlogBy_id(bid);
        String username = HtmlUtils.htmlEscape(nr.getUsername());
        String content = HtmlUtils.htmlEscape(nr.getContent());
        Review Rev = new Review(content,username,bid);
        revRepository.save(Rev);
        System.out.println("bid "+bid);
        revRepository.findReviewsByBid(bid);
        //System.out.println(revRepository.findReviewsByBid(bid));
        //System.out.println(blg.getreviews());
        return  new result(200,"return reviews",revRepository.findReviewsByBid(bid));
    }
    //发布
    //要求  username；content；title；
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/post")
    public result postBlog(@RequestBody Blog blg){
        String _username = blg.getUsername();
        _username = HtmlUtils.htmlEscape(_username);
        Object _content = blg.getContent();
        String _title = blg.getTitle();
        _title = HtmlUtils.htmlEscape(_title);
       // String _type = blg.getType();
       // _type = HtmlUtils.htmlEscape(_type);
        System.out.println("dd");
        Blog _blg = new Blog(_username,_title,_content);
        System.out.println("gg");
        User user = userRepository.findByUsername(_username);
        blg.setUid(user.get_id());
        blgRepository.save(_blg);
        user.addOneBlog(_blg);
        return  new result(200,"post it",null);
    }

    //撤回
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/dpost")
    public result dpostBlog(@RequestBody Blog blg){
        String _uid = blg.getUid();
        Blog _blg = blgRepository.findBlogBy_id(HtmlUtils.htmlEscape(blg.get_id()));
        if (_blg.getUid().equals(_uid)){
            blgRepository.removeBy_id(_blg.get_id());
            return new result(200,"success",null);
        }
        return new result(400,"failure",null);
    }

    //找到一篇
    //要求 _id;
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/forOne")
    public result oneBlog(@RequestBody Blog blg){
        String _bid = HtmlUtils.htmlEscape(blg.get_id());
        Blog _blg = blgRepository.findBlogBy_id(_bid);
        if(_blg != null)
        return new result(200,"get one",_blg);
        else
            return new result(400,"no such one",null);
    }

    //返回一类
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/forTypeOne")
    public result oneTypeBlog(@RequestBody String type){
        String _type = HtmlUtils.htmlEscape(type);
        List<Blog> _blgs = blgRepository.findBlogsByType(_type);
        if(_blgs != null)
            return new result(200,"get one",_blgs);
        else
            return new result(400,"no such one",null);
    }

    //返回某人的收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/ClBlogs")
    public result myClBlogs(@RequestBody User user){
        User _user = userRepository.findByUsername(HtmlUtils.htmlEscape(user.getUsername()));
        List<Blog> blgs = _user.getMyBlogs();
        return new result(200,"ClBlogs",blgs);

    }
    //返回某人的发布
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/myBlogs")
    public result myPost(@RequestBody User user){
        User _user = userRepository.findByUsername(HtmlUtils.htmlEscape(user.getUsername()));
        List<Blog> blgs = _user.getClBlogs();
        return new result(200,"ClBlogs",blgs);

    }


    //模糊查找api；
    //要求 title；
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/searchTitle")
    public result searchForSome(@RequestBody String title){
        String _title = HtmlUtils.htmlEscape(title);
        List<Blog> blgs = blgRepository.findByTitleLike(_title);
        return  new result(200+blgs.size(),"search it",blgs);
    }




    //查找一类；
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/searchType")
    public result searchForOneType(@RequestBody String title){
        String _title = HtmlUtils.htmlEscape(title);
        List<Blog> blgs = blgRepository.findByType(_title);
        return  new result(200+blgs.size(),"search it",blgs);
    }

    //修改文章；
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/changeIt")
    public result changeOne(@RequestBody Blog blg){
        Object _content = blg.getContent();
        blg = blgRepository.findBlogBy_id(blg.get_id());
        blg.setContent(_content);
        blgRepository.save(blg);
        return new result(200,"success you changed it",null);
    }


    //返回六篇


    //返回收藏数最高的六篇
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/recommend")
    public result reC(){
        List<Blog> blgs = blgRepository.findAll();
        List<Blog> _blgs;
        Collections.sort(blgs);
        if(blgs.size() - 6 > 0){
            _blgs = blgs.subList(0,6);
            return new result(200,"success",_blgs);
        }
        else
            return new result(201,"success!",blgs);

    }

    //收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/collect")
    public result collectBlog(@RequestBody Blog blg){
        String _username = HtmlUtils.htmlEscape(blg.getUsername());
        User user = userRepository.findByUsername(_username);
        String _uid = user.get_id();
        Blog _blg;
        _blg = blgRepository.findBlogBy_id(HtmlUtils.htmlEscape(blg.get_id()));
        user.collectOneBlog(_blg);
        _blg.addCollectedTimes();
        return  new result(200,"get it",null);
    }

    //取消收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/dcollect")
    public result dcollectBlog(@RequestBody Blog blg){
        String _username = HtmlUtils.htmlEscape(blg.getUsername());
        User user = userRepository.findByUsername(_username);
        String _uid = user.get_id();
        Blog _blg;
        _blg = blgRepository.findBlogBy_id(HtmlUtils.htmlEscape(blg.get_id()));
        user.dcollectOneBlog(_blg);
        _blg.redCollectedTimes();
        return  new result(200,"get it",null);
    }

    //返回list 所有博文；
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/findAllBlog")
    public result find(){
        return new result(200,"success",mongoTemplate.findAll(Blog.class));
    }



    /*public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println( " remove duplicate " + list);
    }*/


}
