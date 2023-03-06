package com.lchli.lotfilter;

import android.util.Log;


import com.lchli.lotfilter.filters.DingErMa;
import com.lchli.lotfilter.filters.DingHewei;
import com.lchli.lotfilter.filters.DingKuadu;
import com.lchli.lotfilter.filters.Duanzu;
import com.lchli.lotfilter.filters.NMa;
import com.lchli.lotfilter.filters.ZZZ;
import com.lchli.lotfilter.filters.Zu3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanPreUtils {


    public static String siMa01Pre(String preKaiJiangHao) {
        List<String> data = siMa01Result(preKaiJiangHao);
        return _getCountText(data);
    }

    public static String siMa01PreHewei(String preKaiJiangHao) {
        List<String> data = siMa01Result(preKaiJiangHao);
        return _getCountTextHeWei(data);
    }

    public static String siMa01PreKuadu(String preKaiJiangHao) {
        Log.e("sss","preKaiJiangHao:"+preKaiJiangHao);

        List<String> data = siMa01Result(preKaiJiangHao);
        return _getCountTextKuadu(data);
    }

    public static String duanZuPre(String preKaiJiangHao) {
        List<String> data = duanZuResult(preKaiJiangHao);
        return _getCountText(data);
    }

    public static String bsgePre(String preKaiJiangHao) {
        try {
            int sum = Integer.parseInt(preKaiJiangHao.substring(0, 1)) * 4 +
                    Integer.parseInt(preKaiJiangHao.substring(1, 2)) * 9 +
                    Integer.parseInt(preKaiJiangHao.substring(2, 3)) * 9 +
                    3;
            sum = sum % 10;

            int sum2 = Integer.parseInt(preKaiJiangHao.substring(0, 1)) * 5 +
                    Integer.parseInt(preKaiJiangHao.substring(1, 2)) * 8 +
                    7;
            sum2 = sum2 % 10;

            if (sum2 == sum) {
                sum2 = (sum + sum)%10;
            }

            return sum + "" + sum2;
        } catch (Exception e) {
            return "-1-1";
        }
    }

    static List<String> sima01Number(String preKaiJiangHao) {
        List<String> sima01 = new ArrayList<>();
        int ints = Integer.parseInt(preKaiJiangHao);

        sima01.add(SiMa01.get4HeadNumber((ints / 3.1415926) + "", 4)); //
        sima01.add(SiMa01.getByDivide0618(preKaiJiangHao));
        sima01.add(SiMa01.getByXueYinDuanzu(preKaiJiangHao));
        return sima01;
    }

    static String getLeftNumber(String input) {
        String left = "";
        for (int i = 0; i < DanMaSource.getDanMaSource().size(); i++) {
            if (!input.contains(DanMaSource.getDanMaSource().get(i))) {
                left += DanMaSource.getDanMaSource().get(i);
            }
        }

        return left;
    }


    static List<String> siMa01Result(String preKaiJiangHao) {
        FilterManager mFilterManager = new FilterManager();

        List<String> sima01 = sima01Number(preKaiJiangHao);

        List<String> sima01Reverse = new ArrayList<>();
        for (String element : sima01) {
            String e = "";
            for (String d : DanMaSource.getDanMaSource()) {
                if (!element.contains(d)) {
                    e += d;
                }
            }

            sima01Reverse.add(e);
        }

        //4码=01
        for (String element : sima01) {
            mFilterManager.addFilter(new DingErMa(_getErMa(element)));
        }


        List<String> data = mFilterManager.runRongCuoFilter(
                DanMaSource.getZuXuanSource(),
                Arrays.asList(0, 1)); //[2, 3]

        ///

        // mFilterManager=new FilterManager();

//      sima01Reverse.forEach((element) {
//              mFilterManager.addFilter(new DingDanMa(Utils.danmaToList(element)));
//      });
//      data = mFilterManager.runFilter(data);


        return data;

    }

    static List<String> _getErMa(String numberstr) {
        List<String> shaermaCondition = new ArrayList<>(); //4567/4567

        for (int i = 0; i < numberstr.length(); i++) {
            for (int j = 0; j < numberstr.length(); j++) {
                shaermaCondition.add(numberstr.charAt(i) + "" + numberstr.charAt(j));
            }
        }

        return shaermaCondition;
    }

   public static String _getCountText(List<String> data) {
        List<SortHelper> countMap = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            countMap.add(new SortHelper(i, _count(data, i + "")));
        }
        Collections.sort(countMap, new Comparator<SortHelper>() {
            @Override
            public int compare(SortHelper l, SortHelper r) {
                if (l.value > r.value) {
                    return -1;
                }
                if (l.value < r.value) {
                    return 1;
                }
                return 0;
            }
        });

        String ret = "";
        for (SortHelper element : countMap) {
            ret += element.key + "";
        }


        return ret;

    }

    private static class SortHelper {
        public int key;
        public int value;

        public SortHelper(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "SortHelper{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    static int _count(List<String> filteredSource, String num) {
        int sum = 0;
        for (String element : filteredSource) {
            if (element.contains(num)) {
                sum += 1;
            }
        }


        return sum;

    }

    static List<String> duanZuResult(String preKaiJiangHao) {
        FilterManager mFilterManager = new FilterManager();

        List<String> danMaList = FilterUtils.danmaToList(preKaiJiangHao);
        List<String> d2 = new ArrayList<>();
        for (String e : danMaList) {
            d2.add((Integer.parseInt(e) + 1) + "");
        }


        List<String> d3 = new ArrayList<>();

        for (String e : DanMaSource.getDanMaSource()) {
            if (!danMaList.contains(e) && !d2.contains(e)) {
                d3.add(e);
            }
        }

        mFilterManager.addFilter(new Duanzu(danMaList, d2, d3));

        String hewei = FilterUtils.getItemHeWei(preKaiJiangHao);
        for (List<String> e : DanMaSource.x012) {
            if (e.contains(hewei)) {
                mFilterManager.addFilter(new DingHewei.DingHeweiReverse(e));
                break;
            }
        }

        String kuadu = FilterUtils.getItemKuadu(preKaiJiangHao);
        for (List<String> e : DanMaSource.x012) {
            if (e.contains(kuadu)) {
                mFilterManager.addFilter(new DingKuadu.DingKuaduReverse(e));
                break;
            }
        }

        final List<Integer> rongcuo = new ArrayList<>();
        rongcuo.add(0);
        rongcuo.add(1);

        List<String> data = mFilterManager.runRongCuoFilter(
                DanMaSource.getZuXuanSource(), rongcuo);

        return data;

    }

    static String _getCountTextHeWei(List<String> data) {

        Map<Integer, Integer> hezhiMap = new HashMap<>();

        for (String element : data) {
            int sum = Integer.parseInt(FilterUtils.getItemHeWei(element));

            if (hezhiMap.get(sum) != null) {
                hezhiMap.put(sum, hezhiMap.get(sum) + 1);
            } else {
                hezhiMap.put(sum, 1);
            }
        }

        List<SortHelper> countMap = new ArrayList<>();

        for (Map.Entry<Integer, Integer> e : hezhiMap.entrySet()) {
            countMap.add(new SortHelper(e.getKey(), e.getValue()));
        }

        Collections.sort(countMap, new Comparator<SortHelper>() {
            @Override
            public int compare(SortHelper l, SortHelper r) {
                if (l.value > r.value) {
                    return -1;
                }
                if (l.value < r.value) {
                    return 1;
                }
                return 0;
            }
        });

        String ret = "";
        for (SortHelper element : countMap) {
            ret += element.key + "";
        }


        return ret;

    }

    static String _getCountTextKuadu(List<String> data) {

        Map<Integer, Integer> hezhiMap = new HashMap<>();

        for (String element : data) {
            int sum = Integer.parseInt(FilterUtils.getItemKuadu(element));

            if (hezhiMap.get(sum) != null) {
                hezhiMap.put(sum, hezhiMap.get(sum) + 1);
            } else {
                hezhiMap.put(sum, 1);
            }
        }

        List<SortHelper> countMap = new ArrayList<>();

        for (Map.Entry<Integer, Integer> e : hezhiMap.entrySet()) {
            countMap.add(new SortHelper(e.getKey(), e.getValue()));
        }

        Collections.sort(countMap, new Comparator<SortHelper>() {
            @Override
            public int compare(SortHelper l, SortHelper r) {
                if (l.value > r.value) {
                    return -1;
                }
                if (l.value < r.value) {
                    return 1;
                }
                return 0;
            }
        });


        Log.e("kk","countMap:"+countMap);

        String ret = "";
        for (SortHelper element : countMap) {

            ret += element.key + "";
        }


        return ret;

    }


    public static List<String> getDadiPreLast(String preKaiJiangHao,String cond1) {
        FilterManager mFilterManager = new FilterManager();

       // String dm01= DanPreUtils.siMa01Pre(preKaiJiangHao);
       // String dmbs = DanPreUtils.bsgePre(preKaiJiangHao);
        String dmdz = DanPreUtils.duanZuPre(preKaiJiangHao);
       // String hw= DanPreUtils.siMa01PreHewei(preKaiJiangHao);
       // String kd= DanPreUtils.siMa01PreKuadu(preKaiJiangHao);
        mFilterManager.addFilter(new NMa(FilterUtils.danmaToList(dmdz.substring(0,2)),
                Arrays.asList("1")));

//        if(cond1.equals("1")){
//            mFilterManager.addFilter(new DingDanMa(FilterUtils.danmaToList(dmbs.substring(0,2))));
//        }else if(cond1.equals("2")){
//            mFilterManager.addFilter(new DingDanMa(FilterUtils.danmaToList(dm01.substring(0,2))));
//        }else{
//            mFilterManager.addFilter(new DingDanMa(FilterUtils.danmaToList(dmdz.substring(0,2))));
//        }
//

       // mFilterManager.addFilter(new DingHewei(FilterUtils.danmaToList(hw.substring(0,5))));
       // mFilterManager.addFilter(new DingKuadu(FilterUtils.danmaToList(kd.substring(0,5))));


        List<String> result = mFilterManager.runFilter(DanMaSource.getZuXuanSource());



        ///去掉组三豹子。
        mFilterManager=new FilterManager();
        mFilterManager.addFilter(new Zu3().reverseCondition());
        result = mFilterManager.runFilter(result);


        mFilterManager=new FilterManager();
        mFilterManager.addFilter(new ZZZ().reverseCondition());
        result = mFilterManager.runFilter(result);


        return result;

    }
}