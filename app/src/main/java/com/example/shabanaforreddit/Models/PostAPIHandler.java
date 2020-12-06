package com.example.shabanaforreddit.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shabanaforreddit.API;
import com.example.shabanaforreddit.MySingleton;
import com.example.shabanaforreddit.Views.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostAPIHandler {
    private static PostAPIHandler INSTANCE;
    private static Context mContext;
    private static SharedPreferences sharedPref;

    public static PostAPIHandler getInstance(final Context context) {
        sharedPref = context.getSharedPreferences("MyShared",0);
        mContext = context;
        if (INSTANCE == null) {
            synchronized (PostAPIHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PostAPIHandler();
                }
            }
        }
        return INSTANCE;

    }
    void getAllPosts(HomeFragment.VolleyCallback volleyCallback){
        String url = API.home;
        final String[] token = {sharedPref.getString("access_token", "")};
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("LOG_RESPONSE_PAH_44",response.getString("data"));
                            String posts = new JSONObject(response.getString("data")).getString("children");
                            Log.i("LOG_RESPONSE_PAH_45",posts);
                            JSONArray jsonArr = new JSONArray(posts);
                            List<Post> dataList = new ArrayList<Post>();
                            for (int i = 0; i < jsonArr.length(); i++) {
                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                JSONObject data = new JSONObject(jsonObj.getString("data"));
                                String title = data.getString("title");
                                Log.i("LOG_RESPONSE_POST",title);
                                String subreddit = "r/"+data.getString("subreddit");
                                //Log.i("LOG_RESPONSE_PAH_45",subreddit);
                                String subreddit_img = "";
                                String post_img = data.getString("thumbnail");
                                //Log.i("LOG_RESPONSE_PAH_45",post_img);
                                int upvotes = data.getInt("score");
                                //Log.i("LOG_RESPONSE_PAH_45",upvotes+"");
                                int comments = data.getInt("num_comments");
                                //Log.i("LOG_RESPONSE_PAH_45",comments+"");
                                int awards = data.getInt("total_awards_received");
                                //Log.i("LOG_RESPONSE_PAH_45",awards+"");
                                long created = data.getLong("created_utc");
                                //Log.i("RESPONSE_LOG_POST",created+"");
                                String link="";
                                try {
                                    link = data.getString("url_overridden_by_dest");
                                }
                                catch (Exception e){
                                    link = data.getString("url");
                                }
                                //Log.i("RESPONSE_LOG_POST",link);
                                String author = data.getString("author");
                                //Log.i("RESPONSE_LOG_POST",author);
                                Post post = new Post(title,subreddit,subreddit_img,post_img,upvotes,comments,awards,created,link,author);
                                Log.i("LOG_RESPONSE_POST",post.toString());
                                dataList.add(post);
                            }

                            volleyCallback.onSuccess(dataList);
                        } catch (JSONException e) {
                            Log.i("LOG_RESPONSE_PAH_44",e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("LOG_RESPONSE_PAH_56",error.toString());

                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Basic Authentication
                //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);
                token[0] = sharedPref.getString("access_token","");
                Log.i("LOG_RESPONSE_PAH_79",token[0]);
                headers.put("Authorization", "Bearer " + token[0]);
                return headers;
            }
        };

        MySingleton.getInstance(mContext).addToRequestQueue(jsonObjectRequest);

    };

}
