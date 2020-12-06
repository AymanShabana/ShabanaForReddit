package com.example.shabanaforreddit.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;

@Entity(tableName = "post_table")
public class Post {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "subreddit")
    private String subreddit;

    @ColumnInfo(name = "subreddit_icon")
    private String subredditIcon;

    @ColumnInfo(name = "post_img")
    private String postImg;

    @NonNull
    @ColumnInfo(name = "upvotes")
    private int upvotes;

    @NonNull
    @ColumnInfo(name = "comments")
    private int comments;

    @NonNull
    @ColumnInfo(name = "awards")
    private int awards;

    @NonNull
    @ColumnInfo(name = "created")
    private long created;

    @NonNull
    @ColumnInfo(name = "link")
    private String link;

    @NonNull
    @ColumnInfo(name = "author")
    private String author;

    public Post(@NonNull String title, @NonNull String subreddit, String subredditIcon, String postImg, int upvotes, int comments, int awards, long created, String link, String author) {
        this.title = title;
        this.subreddit = subreddit;
        this.subredditIcon = subredditIcon;
        this.postImg = postImg;
        this.upvotes = upvotes;
        this.comments = comments;
        this.awards = awards;
        this.created = created;
        this.link = link;
        this.author = author;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getSubreddit() {
        return subreddit;
    }

    public String getSubredditIcon() {
        return subredditIcon;
    }

    public String getPostImg() {
        return postImg;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getComments() {
        return comments;
    }

    public int getAwards() {
        return awards;
    }
    public String getLink() {
        return link;
    }
    public String getAuthor() {
        return author;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subreddit='" + subreddit + '\'' +
                ", subredditIcon='" + subredditIcon + '\'' +
                ", postImg='" + postImg + '\'' +
                ", upvotes=" + upvotes +
                ", comments=" + comments +
                ", awards=" + awards +
                ", created=" + created +
                ", link='" + link + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}