package com.example.shabanaforreddit.Models;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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

    public Post(@NonNull String title, @NonNull String subreddit, String subredditIcon, String postImg, int upvotes, int comments, int awards) {
        this.title = title;
        this.subreddit = subreddit;
        this.subredditIcon = subredditIcon;
        this.postImg = postImg;
        this.upvotes = upvotes;
        this.comments = comments;
        this.awards = awards;
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
}