package com.example.shabanaforreddit.Models;

import java.util.Date;

public class User {
    private String username;
    private int linkKarma;
    private int commentKarma;
    private int totalKarma;
    private String icon;
    private long created;
    private String authToken;
    private String refreshToken;

    public User(String username, int linkKarma, int commentKarma, int totalKarma, String icon, long created, String authToken, String refreshToken) {
        this.username = username;
        this.linkKarma = linkKarma;
        this.commentKarma = commentKarma;
        this.totalKarma = totalKarma;
        this.icon = icon;
        this.created = created;
        this.authToken = authToken;
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public int getLinkKarma() {
        return linkKarma;
    }

    public int getCommentKarma() {
        return commentKarma;
    }

    public int getTotalKarma() {
        return totalKarma;
    }

    public String getIcon() {
        return icon;
    }

    public String getTimeSinceCreation() {
        long s = (long) ((new Date().getTime() - created*1000)/1000.0f);//seconds
        String res = s+"s";
        if(s>=60) {
            s /= 60.0f;//minutes
            res = s+"m";
            if(s>=60) {
                s /= 60.0f;//hours
                res = s+"h";
                if(s>=24) {
                    s /= 24.0f;//days
                    res = s+"d";
                    if(s>=7){
                        s/= 7.0f;//weeks
                        res = s+"w";
                        if(s>=4){
                            s/=4.0f;//months
                            res = s+"m";
                            if(s>=12){
                                s/=12.0f;//years
                                res = s+"y";
                            }
                        }
                    }
                }
            }
        }
        return res;

    }


    public String getAuthToken() {
        return authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
