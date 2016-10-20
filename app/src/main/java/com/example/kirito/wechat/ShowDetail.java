package com.example.kirito.wechat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kirito on 2016.10.20.
 */

public class ShowDetail extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView wv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("url");
        //设置打开的网页在webview里显示而不是调用手机浏览器显示
        wv.setWebViewClient(new WebViewClient());
        //若不设置为true，则webview无法加载图片
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.loadUrl(url);
    }
}
