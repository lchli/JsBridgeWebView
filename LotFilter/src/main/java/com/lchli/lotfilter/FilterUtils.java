package com.lchli.lotfilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterUtils {
  public static String getItemHeWei(String item) {
    int ret = 0;
    int len = item.length();
    for (int i = 0; i < len; i++) {
      ret += Integer.parseInt(item.substring(i, i + 1));
    }
    ret %= 10;
    return ret+"";
  }

  public static String getItemHezhi(String item) {
    int ret = 0;
    int len = item.length();
    for (int i = 0; i < len; i++) {
      ret += Integer.parseInt(item.substring(i, i + 1));
    }

    return ret+"";
  }

  public static String getItemKuadu(String item) {
    int len = item.length();
    int max = -1;
    int min = 10;
    for (int i = 0; i < len; i++) {
      int temp = Integer.parseInt(item.substring(i, i + 1));
      if (temp > max) max = temp;
      if (temp < min) min = temp;
    }
    int ret = max - min;
    ret %= 10;
    return ret+"";
  }

  public static List<String> danmaToList(String item) {
    List<String> list =new ArrayList<>();
    int len = item.length();
    for (int i = 0; i < len; i++) {
      list.add(item.charAt(i)+"");
    }

    return list;
  }

  public static List<Integer> danmaToListInt(String item) {
    List<Integer> list = new ArrayList<>();
    int len = item.length();
    for (int i = 0; i < len; i++) {
      list.add(Integer.parseInt(item.charAt(i)+""));
    }

    return list;
  }

  public static String getSortedDanMa(String item) {
    List<String> list = danmaToList(item);
    Collections.sort(list);
    String ret = "";

    for(String element:list){
      ret += element;
    }

    return ret;
  }
}