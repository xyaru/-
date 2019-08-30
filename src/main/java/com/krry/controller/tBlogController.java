package com.krry.controller;

import com.krry.entity.User;
import com.krry.entity.result;
import com.krry.entity.tBlog;
import com.krry.repository.tBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class tBlogController {
    @Autowired
    private tBlogRepository tBlogRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @CrossOrigin
    @RequestMapping(value = "/write")
    @ResponseBody
    public result WriteSthNew(@RequestBody tBlog tblog){
//        tBlog tt = new tBlog<>();
//        String title = tblog.getTitle();
//        title = HtmlUtils.htmlEscape(title);
//        tt.setContext(tblog.getContent());
//        //System.out.println("content: "+tblog.getContent());
        tBlogRepository.save(tblog);
        return new result(200,"success! you've published it",null);

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/findAllB")
    public result find(){
        return new result(200,"success",mongoTemplate.findAll(tBlog.class));
    }


}
