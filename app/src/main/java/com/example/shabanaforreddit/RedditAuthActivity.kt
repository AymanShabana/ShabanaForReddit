package com.example.shabanaforreddit

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.shabanaforreddit.databinding.ActivityRedditAuthBinding


class RedditAuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRedditAuthBinding
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_auth)
        binding = ActivityRedditAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val randomString = (1..15)
                .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("");

//        binding.webview.setWebViewClient(object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
//                view.loadUrl("https://www.reddit.com/api/v1/authorize.compact?client_id=v9KZtNQ8vwgxyA&response_type=code&state="+randomString+"&redirect_uri=https://github.com/AymanShabana/ShabanaForReddit&duration=permanent&scope=identity edit flair history mysubreddits privatemessages read report save submit subscribe vote")
//                return false
//            }
//        })
        binding.webview.setWebViewClient(WebViewClient())
        var webSettings = binding.webview.settings
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(0);
            binding.webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            binding.webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            binding.webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        if (binding != null) {
            binding.webview.loadUrl("https://www.reddit.com/api/v1/authorize.compact?client_id=v9KZtNQ8vwgxyA&response_type=code&state="+randomString+"&redirect_uri=https://github.com/AymanShabana/ShabanaForReddit&duration=permanent&scope=identity edit flair history mysubreddits privatemessages read report save submit subscribe vote")
        }
    }
}