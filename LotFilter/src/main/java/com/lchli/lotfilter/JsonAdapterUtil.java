package com.lchli.lotfilter;

import android.util.Log;

import com.lchli.lotfilter.filters.DingDanMa;
import com.lchli.lotfilter.filters.DingErMa;
import com.lchli.lotfilter.filters.DingHewei;
import com.lchli.lotfilter.filters.DingKuadu;
import com.lchli.lotfilter.filters.Duanzu;
import com.lchli.lotfilter.filters.NMa;
import com.lchli.lotfilter.filters.ShaDanMa;
import com.lchli.lotfilter.filters.ShaErMa;
import com.lchli.lotfilter.filters.ZZZ;
import com.lchli.lotfilter.filters.Zu3;
import com.lchli.lotfilter.filters.Zu6;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonAdapterUtil {

    public static JSONObject filter(JSONObject options) {
        Log.e("sss", "filter:" + options);

        JSONObject data = new JSONObject();
        FilterManager filterManager = new FilterManager();

        if (options == null) {
            return null;
        }


        try {
            JSONArray numbers = options.optJSONArray("danMa");
            if (numbers != null && numbers.length() > 0) {
                filterManager.addFilter(new DingDanMa(arrToList(numbers)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONArray numbers = options.optJSONArray("shaDanMa");
            if (numbers != null && numbers.length() > 0) {
                filterManager.addFilter(new ShaDanMa(arrToList(numbers)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONArray numbers = options.optJSONArray("heWei");
            if (numbers != null && numbers.length() > 0) {
                filterManager.addFilter(new DingHewei(arrToList(numbers)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray numbers = options.optJSONArray("kuaDu");
            if (numbers != null && numbers.length() > 0) {
                filterManager.addFilter(new DingKuadu(arrToList(numbers)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONArray numbers = options.optJSONArray("erMa");
            if (numbers != null && numbers.length() > 0) {
                filterManager.addFilter(new DingErMa(arrToList(numbers)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray numbers = options.optJSONArray("shaErMa");
            if (numbers != null && numbers.length() > 0) {
                filterManager.addFilter(new ShaErMa(arrToList(numbers)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONObject duanZu = options.optJSONObject("duanZu");
            if (duanZu != null && duanZu.length() > 0) {
                JSONArray d1 = duanZu.getJSONArray("d1");
                JSONArray d2 = duanZu.getJSONArray("d2");
                JSONArray d3 = duanZu.getJSONArray("d3");
                if ((d1 != null && d1.length() > 0) && (d2 != null && d2.length() > 0) && (d3 != null && d3.length() > 0)) {
                    filterManager.addFilter(new Duanzu(arrToList(d1), arrToList(d2), arrToList(d3)));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject nMa = options.optJSONObject("nMa");
            if (nMa != null && nMa.length() > 0) {
                JSONArray source = nMa.getJSONArray("source");
                JSONArray count = nMa.getJSONArray("count");

                if ((source != null && source.length() > 0) && (count != null && count.length() > 0)) {
                    filterManager.addFilter(new NMa(arrToList(source), arrToList(count)));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> rongCuo = null;
        try {
            JSONArray numbers = options.optJSONArray("rongCuo");
            if (numbers != null && numbers.length() > 0) {
                rongCuo = arrToListInt(numbers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> res;

        if (rongCuo != null && !rongCuo.isEmpty()) {
            res = filterManager.runRongCuoFilter(DanMaSource.getZuXuanSource(), rongCuo);///rongcuo
        } else {
            res = filterManager.runFilter(DanMaSource.getZuXuanSource());
        }


        try {
            filterManager = new FilterManager();

            JSONArray numbers = options.optJSONArray("type");
            Log.e("chromium","type numbers:"+numbers.length());
            if (numbers != null && numbers.length() > 0) {
                List<String> list = arrToList(numbers);
                if (!list.contains("1")) {
                    filterManager.addFilter(new Zu6._Zu6Reverse());
                }
                if (!list.contains("2")) {
                    filterManager.addFilter(new Zu3._ZuSanReverse());
                }
                if (!list.contains("3")) {
                    filterManager.addFilter(new ZZZ._ZZZReverse());
                }

            } else {
                Log.e("chromium","no type===");
            }

            res = filterManager.runFilter(res);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray jarr = new JSONArray();

        if (res != null && !res.isEmpty()) {
            for (String e : res) {
                jarr.put(e);
            }


        }

        try {
            data.put("data", jarr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("sss", "data:" + data);

        return data;
    }

    private static List<String> arrToList(JSONArray arr) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.optString(i));
        }
        return list;
    }

    private static List<Integer> arrToListInt(JSONArray arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(Integer.parseInt(arr.optString(i)));
        }
        return list;
    }
}
