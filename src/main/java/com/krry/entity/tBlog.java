package com.krry.entity;

import org.springframework.data.annotation.Id;

public class tBlog<T> {

    private String _id;
    private String title;
    private Object content;

    public tBlog(){}
    public tBlog(String title,T context){
        this.title = title;
        this.content = context;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContext(T content) {
        this.content = content;
    }
}
