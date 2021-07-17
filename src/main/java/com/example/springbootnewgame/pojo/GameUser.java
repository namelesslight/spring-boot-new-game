package com.example.springbootnewgame.pojo;

import com.baomidou.mybatisplus.annotation.TableId;

public class GameUser {
    @TableId("user_name")
    private String userName;
    private String userPassword;
    private Integer userBestLevel;
    private Double userBestAverage;

    @Override
    public String toString() {
        return "GameUser{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userBestLevel=" + userBestLevel +
                ", userBestAverage=" + userBestAverage +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserBestLevel() {
        return userBestLevel;
    }

    public void setUserBestLevel(Integer userBestLevel) {
        this.userBestLevel = userBestLevel;
    }

    public Double getUserBestAverage() {
        return userBestAverage;
    }

    public void setUserBestAverage(Double userBestAverage) {
        this.userBestAverage = userBestAverage;
    }
}
