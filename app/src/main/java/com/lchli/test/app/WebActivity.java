package com.lchli.test.app;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.lchli.jsbridgeweb.DefaultBridgeWebActivity;
import com.lchli.jsbridgeweb.JsBridgeWebView;

public class WebActivity extends DefaultBridgeWebActivity {

    public static void start(Context context, String url) {
        Intent it = new Intent(context, WebActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        it.putExtra("url", url);
        context.startActivity(it);
    }

    @Override
    protected void initLoad(String url) {
       mWebView.setJsEventListener(new JsBridgeWebView.JsEventListener() {
           @Override
           public void onJsEvent(String event, String param, JsBridgeWebView.JsEventListenerCallBack callBack) {
               Toast.makeText(getApplicationContext(),event,1).show();
           }
       });
        mWebView.loadUrl(url);
    }
}