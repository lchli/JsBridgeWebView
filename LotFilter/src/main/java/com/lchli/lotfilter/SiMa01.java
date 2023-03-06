package com.lchli.lotfilter;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiMa01 {
    static Map<String, String> _map = new HashMap<>();

    static {
        _map.put("0156", "1567");
        _map.put("0257", "2579");
        _map.put("0358", "1358");
        _map.put("0459", "3459");
        _map.put("1267", "3789");
        _map.put("1368", "1479");
        _map.put("1469", "0357");
        _map.put("2378", "0159");
        _map.put("2479", "1369");
        _map.put("3489", "1237");
    }

    static  List<String> _list = Arrays.asList("18","29","34","05","67");

    static String getByDuiMa(String kaijianghao) {
        List<Integer> ints = FilterUtils.danmaToListInt(kaijianghao);
        if (ints.get(0).equals(ints.get(1)) && ints.get(1).equals(ints.get(2))) {
            int v1 = ints.get(0);
            int v2 = (v1 + 5) % 10;
            int minx = min(v1, v2);
            if (minx == 0) {
                return v1+""+v2+"16";
            }

            return v1+""+v2+"05";
        }
        //909,949,945,917
        List<Integer> ret = new ArrayList<>();

        int v1 = (ints.get(0) + 5) % 10;
        int v2 = (ints.get(1) + 5) % 10;
        int v3 = (ints.get(2) + 5) % 10;

        if (!ret.contains(ints.get(0))) {
            ret.add(ints.get(0));
        }
        if (!ret.contains(ints.get(1))) {
            ret.add(ints.get(1));
        }
        if (!ret.contains(ints.get(2))) {
            ret.add(ints.get(2));
        }
        if (!ret.contains(v1)) {
            ret.add(v1);
        }
        if (!ret.contains(v2)) {
            ret.add(v2);
        }
        if (!ret.contains(v3)) {
            ret.add(v3);
        }

        if (ret.size() == 4) {
            return ret.get(0) +""+ ret.get(1) +""+ ret.get(2) +""+ ret.get(3);

        }

        if (ret.size() == 2) {
            int minx = min(ret.get(0), ret.get(1));
            if (minx == 0) {
                return ret.get(0) +""+ ret.get(1) +"16";
            }

            return ret.get(0) +""+ ret.get(1) +"05";
        }

        if (ret.size() == 6) {
            List<Integer> left = new ArrayList<>();
            for(int element: DanMaSource.getDanMaSourceInt()){
                if (!ret.contains(element)) {
                    left.add(element);
                }
            }

            return left.get(0) +""+ left.get(1) +""+ left.get(2) +""+ left.get(3);
        }

        return "";
    }

    static String getByDivide0618(String kaijianghao) {
        int ints = Integer.parseInt(kaijianghao);
        double dr = ints / 0.618;
        String drstr = dr+"";


        drstr = drstr.replaceAll("\\.", "");

        List<String> left = new ArrayList<>();

        for (int i = 0; i < drstr.length(); i++) {
            if (!left.contains(drstr.charAt(i)+"")) {
                left.add(drstr.charAt(i)+"");
                if (left.size() >= 4) {
                    break;
                }
            }
        }

        String ret = "";

        for(String element:left){
            ret += element;
        }

        return ret;
    }

    static String getByXueYinDuanzu(String kaijianghao) {
        List<Integer> ints = FilterUtils.danmaToListInt(kaijianghao);
        int v1 = Math.abs(ints.get(1) - ints.get(2));
        int v2;
        if (v1 != ints.get(2)) {
            v2 = ints.get(2);
        } else {
            v2 = (v1 + 1) % 10;
            v1 = v1 == 0 ? 9 : v1 - 1;
        }

        int v3 = (v1 + 5) % 10;
        int v4 = (v2 + 5) % 10;

        if (min(v3, v4) == min(v1, v2) && max(v3, v4) == max(v1, v2)) {
            v3 = (v1 + 9) % 10;
            v4 = (v2 + 9) % 10;
        }

        return v1+""+v2+""+v3+""+v4;

    }

    static List<String> getByGaoYuanLangDuanzu(String kaijianghao) {
        List<String> ret =new ArrayList<>();

        List<Integer> norepeat =new ArrayList<>();

        if (!norepeat.contains(Integer.parseInt(kaijianghao.charAt(0)+""))) {
            norepeat.add(Integer.parseInt(kaijianghao.charAt(0)+""));
        }
        if (!norepeat.contains(Integer.parseInt(kaijianghao.charAt(1)+""))) {
            norepeat.add(Integer.parseInt(kaijianghao.charAt(1)+""));
        }
        if (!norepeat.contains(Integer.parseInt(kaijianghao.charAt(2)+""))) {
            norepeat.add(Integer.parseInt(kaijianghao.charAt(2)+""));
        }

        String left = DanPreUtils.getLeftNumber(kaijianghao);
        List<Integer> leftlist = FilterUtils.danmaToListInt(left);
        Collections.sort(leftlist);

        norepeat.addAll(leftlist);

        ret.add(norepeat.get(0) +""+ norepeat.get(1) +""+ norepeat.get(2));
        ret.add(norepeat.get(3) +""+ norepeat.get(4) +""+ norepeat.get(5));
        ret.add(norepeat.get(6) +""+ norepeat.get(7) +""+ norepeat.get(8)+""+ norepeat.get(9));


        return ret;

    }


    static String get4HeadNumber(String drstr, int count) {


        drstr = drstr.replaceAll("\\.", "");

        List<String> left = new ArrayList<>();

        for (int i = 0; i < drstr.length(); i++) {
            if (!left.contains(drstr.charAt(i)+"")) {
                left.add(drstr.charAt(i)+"");
                if (left.size() >= count) {
                    break;
                }
            }
        }

        String ret = "";

        for(String element:left){
            ret += element;
        }


        return ret;
    }
}