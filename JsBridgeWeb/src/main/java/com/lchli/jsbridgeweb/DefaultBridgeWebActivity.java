package com.lchli.jsbridgeweb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者：simpleli on 2023/3/3 18:27
 * 邮箱：lchli@mexc.com
 */
public class DefaultBridgeWebActivity extends AppCompatActivity {

    public static void start(Context context, String url) {
        Intent it = new Intent(context, DefaultBridgeWebActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        it.putExtra("url", url);
        context.startActivity(it);
    }

    protected JsBridgeWebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lch_jsb_def_web_act);
        mWebView = findViewById(R.id.webView);
        initLoad(getIntent().getStringExtra("url"));
    }

    protected void initLoad(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }
}
