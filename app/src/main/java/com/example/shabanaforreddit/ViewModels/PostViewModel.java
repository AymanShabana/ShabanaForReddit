package com.example.shabanaforreddit.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.shabanaforreddit.Models.Post;
import com.example.shabanaforreddit.Models.PostRepo;
import com.example.shabanaforreddit.Views.HomeFragment;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostRepo mRepository;
    private List<Post> mAllPosts;
    public PostViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PostRepo(application);
//        Log.i("LOG_RESPONSE_VM",mAllPosts.get(0).toString());

    }
    public List<Post> getAllPosts(HomeFragment.VolleyCallback log_response_hf) {
        mAllPosts = mRepository.getAllPosts(log_response_hf);
        if(mAllPosts!= null)
            Log.i("LOG_RESPONSE_VM",mAllPosts.get(0).toString());
        else
            Log.i("LOG_RESPONSE_VM","NULL");
        return mAllPosts; }
}
