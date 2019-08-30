package com.krry.entity;

public class result<T> {
    int code;
    String messeage;
    private  T data;



    public result(int code, String messeage) {
        this.code = code;
        this.messeage = messeage;
        this.data = null;
    }
    public result(int code, String messeage, T data){
        this.data = data;
        this.code = code;
        this.messeage = messeage;
    }

    public int getCode() {
        return code;
    }

    public String getMesseage() {
        return messeage;
    }

    public void setMesseage(String messeage) {
        this.messeage = messeage;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
