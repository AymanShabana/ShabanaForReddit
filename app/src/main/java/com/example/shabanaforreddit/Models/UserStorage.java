package com.example.shabanaforreddit.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shabanaforreddit.API;
import com.example.shabanaforreddit.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static UserStorage INSTANCE;
    private static Context mContext;
    private static SharedPreferences sharedPref;
    public static UserStorage getInstance(final Context context) {
        sharedPref = context.getSharedPreferences("MyShared",0);
        mContext = context;
        if (INSTANCE == null) {
            synchronized (PostAPIHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserStorage();
                }
            }
        }
        return INSTANCE;
    }
    public static User initUser(){
        RequestQueue queue = MySingleton.getInstance(mContext).getRequestQueue();
        String url = API.id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("LOG_RESPONSE_US_46", response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String icon = ((jsonResponse.getString("icon_img")).split(".png"))[0]+".png";
                            Log.i("LOG_RESPONSE_US_50", (((jsonResponse.getString("icon_img")).split(".png"))[0]+".png"));
                            String username = jsonResponse.getString("name");
                            Log.i("LOG_RESPONSE_Main_105", username);
                            int linkKarma = jsonResponse.getInt("link_karma");
                            Log.i("LOG_RESPONSE_Main_105", linkKarma+"");
                            int commentKarma = jsonResponse.getInt("comment_karma");
                            Log.i("LOG_RESPONSE_Main_105", commentKarma+"");
                            int totalKarma = jsonResponse.getInt("total_karma");
                            Log.i("LOG_RESPONSE_Main_105", totalKarma+"");
                            long created = jsonResponse.getLong("created");
                            Log.i("LOG_RESPONSE_Main_105", created+"");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG_RESPONSE_Main_114", error.toString());
            }

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Basic Authentication
                //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);

                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        queue.add(stringRequest);

    }

}
