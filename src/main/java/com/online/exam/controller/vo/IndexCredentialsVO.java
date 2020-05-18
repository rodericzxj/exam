package com.online.exam.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class IndexCredentialsVO implements Serializable {

    private Integer credentialsId;

    private String credentialsType;


    private String credentialsTitle="";

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date credentialsTime;

    private String  credentialsAuthor;

    private String  credentialsValue;

    private String Time="";

    public String getTime() {
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.ENGLISH);
        return new SimpleDateFormat("MM-dd").format(this.credentialsTime);
    }

    public void setTime() {
        this.Time = "";
    }

    public String getcredentialsType() {
        return credentialsType;
    }

    public void setcredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getcredentialsTitle() {
        return credentialsTitle;
    }

    public void setcredentialsTitle(String credentialsTitle) {
        this.credentialsTitle = credentialsTitle;
    }


    public Date getcredentialsTime() {

        return credentialsTime;
    }

    public void setcredentialsTime(Date credentialsTime) {
        this.credentialsTime = credentialsTime;
    }

    public String getcredentialsAuthor() {
        return credentialsAuthor;
    }

    public void setcredentialsAuthor(String credentialsAuthor) {
        this.credentialsAuthor = credentialsAuthor;
    }

    public String getcredentialsValue() {
        return credentialsValue;
    }

    public void setcredentialsValue(String credentialsValue) {
        this.credentialsValue = credentialsValue;
    }

    public Integer getcredentialsId() {
        return credentialsId;
    }

    public void setcredentialsId(Integer credentialsId) {
        this.credentialsId = credentialsId;
    }

}

