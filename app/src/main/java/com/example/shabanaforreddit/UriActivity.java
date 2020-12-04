package com.example.shabanaforreddit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class UriActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
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
            Log.i("LOG_RESPONSE_URI_40",code);
            sharedPref = this.getSharedPreferences("MyShared",0);
            String randomString = sharedPref.getString("randomString", "NotFound");
            //Log.i("MyTesting",randomString);
            //Log.i("MyTesting",state);
            if(error == null)Log.i("LOG_RESPONSE_URI_45","NoErrors");
            else Log.i("LOG_RESPONSE_URI_46",error);
            // Ensure that the state variable you sent matches the value returned here.
            RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
            final String mRequestBody = "grant_type=authorization_code&code="+code+"&redirect_uri=shabanaforredditapp://OAuth/callback";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.reddit.com/api/v1/access_token",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Log.i("LOG_RESPONSE_URI_56", response.toString());
                            try {
                                JSONObject JSONresponse = new JSONObject(response);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("access_token", JSONresponse.getString("access_token"));
                                editor.putString("refresh_token", JSONresponse.getString("refresh_token"));
                                Log.i("LOG_RESPONSE_URI_62", JSONresponse.getString("access_token"));
                                Log.i("LOG_RESPONSE_URI_63", JSONresponse.getString("refresh_token"));
                                editor.apply();
                                finish();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, (Response.ErrorListener) error1 -> Log.e("LOG_RESPONSE_URI_71", error1.toString())){
                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }

                @Override
                public byte[] getBody()  {
                    try {
                        Log.i("LOG_RESPONSE_URI_80", mRequestBody);
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<String, String>();
                    String creds = String.format("%s:%s","v9KZtNQ8vwgxyA","");
                    String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                    params.put("Authorization", auth);
                    return params;
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                    }
                    try {
                        return Response.success((new JSONObject(new String(response.data))).toString(), HttpHeaderParser.parseCacheHeaders(response));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

            };
            MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }

    }
}