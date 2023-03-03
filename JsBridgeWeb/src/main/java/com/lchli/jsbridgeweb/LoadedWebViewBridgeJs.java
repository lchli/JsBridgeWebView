package com.lchli.jsbridgeweb;

import android.content.Context;
import android.content.res.AssetManager;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 作者：simpleli on 2023/3/3 17:42
 * 邮箱：lchli@mexc.com
 */
public final class LoadedWebViewBridgeJs {

    private static String cachedJs;

    private LoadedWebViewBridgeJs() {
    }

    public static String getBridgeJs(Context context) {
        if (cachedJs != null) {
            return cachedJs;
        }
        cachedJs = fromAssets(context);
        return cachedJs;
    }

    private static String fromAssets(Context context) {
        try {
            AssetManager asm = context.getAssets();
            InputStream ins = asm.open("webview-bridge.js");
            String js = IOUtils.toString(ins, StandardCharsets.UTF_8);

            return "javascript:" + js;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

}
