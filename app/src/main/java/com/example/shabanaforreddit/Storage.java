package com.example.shabanaforreddit;

import android.content.Context;
import android.content.SharedPreferences;

public class Storage {
    private Context context;
    private SharedPreferences sharedPref;

    public Storage(Context activityContext) {
        context = activityContext;
        sharedPref = context.getSharedPreferences("MyShared", Context.MODE_PRIVATE);
    }

}
