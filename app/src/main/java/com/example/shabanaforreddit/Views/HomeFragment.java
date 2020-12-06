package com.example.shabanaforreddit.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shabanaforreddit.API;
import com.example.shabanaforreddit.Models.Post;
import com.example.shabanaforreddit.MySingleton;
import com.example.shabanaforreddit.R;
import com.example.shabanaforreddit.ViewModels.PostViewModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PostViewModel mPostViewModel;
    private PostAdapter adapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPostViewModel.getAllPosts(new VolleyCallback(){
            @Override
            public void onSuccess(List<Post> result){
                Log.i("LOG_RESPONSE_HF",result.get(0).toString());
                adapter.setPosts(result);
                adapter.notifyDataSetChanged();
            }
        });
        String token = ((MainActivity)getActivity()).sharedPref.getString("access_token","");
        if(!token.isEmpty()){
            RequestQueue queue = MySingleton.getInstance(getActivity().getApplicationContext()).getRequestQueue();
            String url = API.id;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Log.i("LOG_RESPONSE", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_RESPONSE", error.toString());
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getActivity()));
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new PostAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPostViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        mPostViewModel.getAllPosts(new VolleyCallback(){
            @Override
            public void onSuccess(List<Post> result){
                Log.i("LOG_RESPONSE_HF",result.get(0).toString());
                adapter.setPosts(result);
                adapter.notifyDataSetChanged();
            }
        });
//        Log.i("LOG_RESPONSE_HF",mPostViewModel.getAllPosts().get(0).toString());
        return view;
    }
    public interface VolleyCallback{
        void onSuccess(List<Post> result);
    }

}