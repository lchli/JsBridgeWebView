package com.lchli.lotfilter.filters;


import java.util.ArrayList;
import java.util.List;

public class DingDanMa implements FilterCondition {
   List<String> mdanMa;

  public DingDanMa(List<String> mdanMa) {
    this.mdanMa=mdanMa;
  }



  @Override
  public void doFilter(List<String> source) {
    List<String> toremove=new ArrayList<>();

    for(String item:source){
      if(!isInclude(item, mdanMa)){
        toremove.add(item);
      }
    }

    source.removeAll(toremove);
  }

  @Override
  public FilterCondition reverseCondition() {
    return new ShaDanMa(mdanMa);
  }

  boolean isInclude(String item, List<String> dmList) {
    for (String e : dmList) {
      if (item.contains(e)) {
        return true;
      }
    }

    return false;
  }
}

