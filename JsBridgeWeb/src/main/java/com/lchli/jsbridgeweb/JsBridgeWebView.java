package com.lchli.jsbridgeweb;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 作者：simpleli on 2023/3/3 16:34
 * 邮箱：lchli@mexc.com
 */
public class JsBridgeWebView extends WebView {

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private JsEventListener mJsEventListener;
    private WebViewClient mWebViewClient;

    public JsBridgeWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public JsBridgeWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JsBridgeWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public JsBridgeWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public JsBridgeWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init();
    }


    private void init() {
        getSettings().setJavaScriptEnabled(true);
        addJavascriptInterface(new NativeApi(), "LchNativeApi");
        setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                evaluateJavascript(LoadedWebViewBridgeJs.getBridgeJs(view.getContext()), value -> {
                });
                if (mWebViewClient != null) {
                    mWebViewClient.onPageFinished(view, url);
                }
            }
        });
    }


    public void callJsMethod(String method, String param) {
        mHandler.post(() -> {
            String js = String.format("javascript:window.LchJsApi.onNativeEvent('%s','%s')", method, param);
            evaluateJavascript(js, value -> {
            });
        });

    }

    public void setJsEventListener(JsEventListener jsEventListener) {
        this.mJsEventListener = jsEventListener;
    }

    public void setMyWebViewClientDelegate(WebViewClient client) {
        this.mWebViewClient = client;
    }

    public interface JsEventListener {
        void onJsEvent(String event, String param, JsEventListenerCallBack callBack);
    }

    public interface JsEventListenerCallBack {
        void onResult(String result);
    }


    private class NativeApi {

        @JavascriptInterface
        public void onJsEvent(String event, String param) {
            if (mJsEventListener != null) {
                mHandler.post(() -> mJsEventListener.onJsEvent(event, param, result -> callJsMethod(event, result)));
            }
        }

    }
}
