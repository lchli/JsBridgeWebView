package com.lchli.lotfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanMaSource {
  static List<List<String>> x012=new ArrayList<>();

  static {
    x012.add(Arrays.asList("1","4","7"));
    x012.add(Arrays.asList("2","5","8"));
    x012.add(Arrays.asList("0","3","6","9"));
  }

  static List<String> _makeDanXuan() {
    List<String> list =new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 10; k++) {
          list.add("$i$j$k");
        }
      }
    }

    return list;
  }

  static List<String> _makeZuXuan() {
    List<String> list = new ArrayList<>();
    Map<String, Boolean> map = new HashMap<>();

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 10; k++) {
          String ijk = _getSortedIJK(i, j, k);
          if (map.get(ijk)== null) {
            list.add(ijk);
            map.put(ijk,true);
          }
        }
      }
    }

    return list;
  }

  static List<String> _makeErMa() {
    List<String> list = new ArrayList<>();
    Map<String, Boolean> map = new HashMap<>();

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        String ij = _getSortedIJ(i, j);
        if (map.get(ij)== null ) {
          list.add(ij);
          map.put(ij,true);
        }
      }
    }

    return list;
  }

  static String _getSortedIJK(int i, int j, int k) {
    List<Integer> list = Arrays.asList(i,j,k);
    Collections.sort(list);

    String ret = "";
    for(int element:list){
      ret += element+"";
    }

    return ret;
  }

  static String _getSortedIJ(int i, int j) {
    int mx = Math.max(i, j);
    int mi = Math.min(i, j);
    return mi+""+mx;
  }

  static List<String> _sources;
  static List<String> _sourcesZX;
  static List<String> _sourcesErMa;

  static List<String> getDanXuanSource() {
    if (_sources == null) {
      _sources = _makeDanXuan();
    }

      return new ArrayList<>(_sources);
  }

  public static List<String> getZuXuanSource() {
    if (_sourcesZX == null) {
      _sourcesZX = _makeZuXuan();
    }

    return  new ArrayList<>(_sourcesZX);
  }

  static List<String> getErMaSource() {
    if (_sourcesErMa == null) {
      _sourcesErMa = _makeErMa();
    }

    return _sourcesErMa;
  }

  static List<String> getDanMaSource() {
    return  Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
  }

  static List<Integer> getDanMaSourceInt() {
    return  Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
  }

}