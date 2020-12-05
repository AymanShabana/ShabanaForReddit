package com.example.shabanaforreddit.Models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PostRepo {
    private LiveData<List<Post>> mAllContacts;
    public PostRepo(Application application) {
    }

}
