package com.example.shabanaforreddit.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shabanaforreddit.API;
import com.example.shabanaforreddit.MySingleton;
import com.example.shabanaforreddit.R;
import com.example.shabanaforreddit.RedditAuthActivity;
import com.example.shabanaforreddit.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Fragment selectorFragment;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
        sharedPref = this.getSharedPreferences("MyShared",0);
        binding.navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    //openFragment(HomeFragment.newInstance("", ""));
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    selectorFragment = new HomeFragment();
                    break;
                case R.id.action_subs:
                    //openFragment(HomeFragment.newInstance("", ""));
                    Toast.makeText(MainActivity.this, "Subs", Toast.LENGTH_SHORT).show();
                    selectorFragment = new SubsFragment();
                    break;
                case R.id.action_post:
                    //openFragment(HomeFragment.newInstance("", ""));
                    Toast.makeText(MainActivity.this, "Post", Toast.LENGTH_SHORT).show();
                    selectorFragment = new PostFragment();
                    break;
                case R.id.action_notif:
                    //openFragment(HomeFragment.newInstance("", ""));
                    Toast.makeText(MainActivity.this, "notif", Toast.LENGTH_SHORT).show();
                    selectorFragment = new NotifFragment();
                    break;
                case R.id.action_chat:
                    //openFragment(HomeFragment.newInstance("", ""));
                    Toast.makeText(MainActivity.this, "chat", Toast.LENGTH_SHORT).show();
                    selectorFragment = new ChatFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectorFragment).commit();
            return true;
        });


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RedditAuthActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        String token = sharedPref.getString("access_token","");
        if(!token.isEmpty()){
            RequestQueue queue = MySingleton.getInstance(getApplicationContext()).getRequestQueue();
            String url = API.id;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("LOG_RESPONSE_Main_100", response);
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                binding.profileImage.setImageBitmap(BitmapFactory.decodeFile(((jsonResponse.getString("icon_img")).split(".png"))[0]+".png"));
                                Log.i("LOG_RESPONSE_Main_104", (((jsonResponse.getString("icon_img")).split(".png"))[0]+".png"));
                                String username = (new JSONObject(jsonResponse.getString("subreddit"))).getString("display_name_prefixed");
                                Log.i("LOG_RESPONSE_Main_105", username);
                                binding.username.setText(username);

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
}