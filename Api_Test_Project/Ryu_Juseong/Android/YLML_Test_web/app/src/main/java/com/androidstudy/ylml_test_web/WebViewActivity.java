package com.androidstudy.ylml_test_web;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
    Intent intent;
    TextView tv_webView_urlBox;
    WebView wb_webView;
    Button btn_back;

    WebSettings we_webViewSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        intent = getIntent();
        setLayout();
        setListener();
        if(!intent.hasExtra("url_text")){return;}

        String url=intent.getStringExtra("url_text");
        Toast.makeText(getApplication(), url,Toast.LENGTH_SHORT).show();
        tv_webView_urlBox.setText(url);
        loadUrl(url);
    }

    private void setListener() {
        btn_back.setOnClickListener(view -> finish());
    }

    private void setLayout() {
        tv_webView_urlBox=findViewById(R.id.tv_webView_urlBox);
        wb_webView=findViewById(R.id.wb_webView);
        btn_back=findViewById(R.id.btn_back);

        tv_webView_urlBox.setText("null");
        wb_webView.setWebViewClient(new WebViewClient());
        we_webViewSetting = wb_webView.getSettings();
        we_webViewSetting.setJavaScriptEnabled(true);
    }


    private void loadUrl(String url){
        wb_webView.loadUrl(url);
    }
}