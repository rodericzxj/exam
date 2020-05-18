package com.online.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Credential {
    private Integer credentialsId;

    private String Time;

    private String infoTime;

    private String credentialsType;

    private String credentialsTitle;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date credentialsTime;

    private String  credentialsAuthor;

    private String  credentialsValue;

    public Integer getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(Integer credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsTitle() {
        return credentialsTitle;
    }

    public void setCredentialsTitle(String credentialsTitle) {
        this.credentialsTitle = credentialsTitle;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCredentialsTime() {
        return credentialsTime;
    }

    public void setCredentialsTime(Date credentialsTime) {
        this.credentialsTime = credentialsTime;
    }

    public String getCredentialsAuthor() {
        return credentialsAuthor;
    }

    public void setCredentialsAuthor(String credentialsAuthor) {
        this.credentialsAuthor = credentialsAuthor;
    }

    public String getCredentialsValue() {
        return credentialsValue;
    }

    public void setCredentialsValue(String credentialsValue) {
        this.credentialsValue = credentialsValue;
    }

    public String getTime() {
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.ENGLISH);
        return new SimpleDateFormat("MM-dd").format(this.credentialsTime);
    }

    public String getInfoTime() {
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.ENGLISH);
        return new SimpleDateFormat("yyyy-MM-dd").format(this.credentialsTime);    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", credentialsId=").append(credentialsId);
        sb.append(", credentialsType=").append(credentialsType);
        sb.append(", credentialsTitle=").append(credentialsTitle);
        sb.append(", credentialsTime=").append(credentialsTime);
        sb.append(", credentialsAuthor=").append(credentialsAuthor);
        sb.append(", credentialsValue=").append(credentialsValue);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}