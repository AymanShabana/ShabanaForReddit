package com.example.shabanaforreddit.Models;

import android.app.Application;
import android.util.Log;

import com.example.shabanaforreddit.Views.HomeFragment;

import java.util.List;

public class PostRepo {
    private PostAPIHandler mPostHandler;
    private List<Post> mAllPosts;
    public PostRepo(Application application) {
        mPostHandler  = PostAPIHandler.getInstance(application);
    }
    public List<Post> getAllPosts(HomeFragment.VolleyCallback log_response_hf) {
        mPostHandler.getAllPosts(log_response_hf);
        if(mAllPosts!= null)
            Log.i("LOG_RESPONSE_PR_25",mAllPosts.get(0).toString());
        else
            Log.i("LOG_RESPONSE_PR","NULL");

        return mAllPosts;
    }

}
