package com.example.shabanaforreddit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.shabanaforreddit.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
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
}