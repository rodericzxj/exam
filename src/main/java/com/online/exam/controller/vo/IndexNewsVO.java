package com.online.exam.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class IndexNewsVO implements Serializable {

    private Integer newsId;

    private String newsType;


    private String newsTitle;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date newsTime;

    private String  newsAuthor;

    private String  newsValue;

    private String newTime="";

    public String getNewTime() {
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.ENGLISH);
        return new SimpleDateFormat("yyyy/MM/dd").format(this.newsTime);
    }

    public void setNewTime() {
        this.newTime = "";
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }


    public Date getNewsTime() {

        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsValue() {
        return newsValue;
    }

    public void setNewsValue(String newsValue) {
        this.newsValue = newsValue;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

}

