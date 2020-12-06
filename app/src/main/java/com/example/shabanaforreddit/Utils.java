package com.example.shabanaforreddit;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.example.shabanaforreddit.Views.PostAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(Resources.getSystem(), x);
    }
    public static class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {
        PostAdapter.PostViewHolder holder;
        public DownloadImageTask(PostAdapter.PostViewHolder holder) {
            this.holder = holder;
        }

        protected Bitmap doInBackground(String... urls) {
            URL url = null;
            try {
                url = new URL(urls[0]);
                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return image;
            }  catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Bitmap result) {
            holder.post_img.setImageBitmap(result);
        }
    }

}
