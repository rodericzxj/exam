package com.online.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class New {
    private Integer newsId;

    private String Time;

    private String newsType;

    private String newsTitle;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date newsTime;

    private String  newsAuthor;

    private String  newsValue;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getNewTitle() {
        return newsTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newsTitle = newTitle;
    }

    public Date getNewsTime() throws ParseException {
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

    public String getNewsValue() {
        return newsValue;
    }

    public void setNewsValue(String newsValue) {
        this.newsValue = newsValue;
    }

    public String getTime() {
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.ENGLISH);
        return new SimpleDateFormat("yyyy-MM-dd").format(this.newsTime);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", newsId=").append(newsId);
        sb.append(", newsType=").append(newsType);
        sb.append(", newsTitle=").append(newsTitle);
        sb.append(", newsTime=").append(newsTime);
        sb.append(", newsAuthor=").append(newsAuthor);
        sb.append(", newsValue=").append(newsValue);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}