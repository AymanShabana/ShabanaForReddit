package com.example.shabanaforreddit.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shabanaforreddit.Models.Post;
import com.example.shabanaforreddit.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private final LayoutInflater mInflater;
    private List<Post> mPosts; // Cached copy of posts

    public PostAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context); ;
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        if (mPosts != null) {
            Post current = mPosts.get(position);
            //holder.nameItemView.setText(current.getName());
            //holder.numberItemView.setText(current.getNumber());
        } else {
            // Covers the case of data not being ready yet.
            //holder.nameItemView.setText("No Contacts");
            //holder.numberItemView.setText("");
        }

    }
    public void setPosts(List<Post> posts){
        mPosts = posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mPosts != null)
            return mPosts.size();
        else return 0;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView subreddit_img;
        private final TextView subreddit_name;
        private final TextView post_time;
        private final ImageButton more_btn;
        private final TextView awards_lbl;
        private final TextView post_title;
        private final ImageButton upvote_btn;
        private final TextView upvotes_lbl;
        private final ImageButton downvote_btn;
        private final ImageView comments_btn;
        private final TextView comments_lbl;
        private final TextView share_btn;
        private final ImageView gift_btn;

        private PostViewHolder(View itemView) {
            super(itemView);
            subreddit_img = itemView.findViewById(R.id.subreddit_img);
            subreddit_name = itemView.findViewById(R.id.subreddit_name);
            post_time = itemView.findViewById(R.id.post_time);
            more_btn = itemView.findViewById(R.id.more_btn);
            awards_lbl = itemView.findViewById(R.id.awards_lbl);
            post_title = itemView.findViewById(R.id.post_title);
            upvote_btn = itemView.findViewById(R.id.subreddit_name);
            upvotes_lbl = itemView.findViewById(R.id.upvotes_lbl);
            downvote_btn = itemView.findViewById(R.id.downvote_btn);
            comments_btn = itemView.findViewById(R.id.comments_btn);
            comments_lbl = itemView.findViewById(R.id.comments_lbl);
            share_btn = itemView.findViewById(R.id.share_btn);
            gift_btn = itemView.findViewById(R.id.gift_btn);
        }

    }
}
