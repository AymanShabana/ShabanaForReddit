package com.example.shabanaforreddit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class UriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uri);
        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String code= uri.getQueryParameter("code");
            String state= uri.getQueryParameter("state");
            String error = uri.getQueryParameter("error");
            //Log.i("MyTesting",code);
            SharedPreferences sharedPref = this.getSharedPreferences("MyShared",0);
            String randomString = sharedPref.getString("randomString", "NotFound");
            //Log.i("MyTesting",randomString);
            //Log.i("MyTesting",state);
            if(error == null)Log.i("MyTesting","NoErrors");
            else Log.i("MyTesting",error);
            // Ensure that the state variable you sent matches the value returned here.
            //RequestQueue queue = Volley.newRequestQueue(this);

        }

    }
}