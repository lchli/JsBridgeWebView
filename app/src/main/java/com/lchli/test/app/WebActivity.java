package com.lchli.test.app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.lchli.jsbridgeweb.DefaultBridgeWebActivity;
import com.lchli.jsbridgeweb.JsBridgeWebView;
import com.lchli.lotfilter.JsonAdapterUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebActivity extends DefaultBridgeWebActivity {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

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
                if (event.equals("filter")) {
                    Log.e("chromium","param:"+param);
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject res = JsonAdapterUtil.filter(new JSONObject(param));
                                callBack.onResult(res.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                                callBack.onResult(null);
                            }
                        }
                    });

                }
            }
        });
        mWebView.loadUrl(url);
    }
}