package com.lchli.lotfilter.filters;



import java.util.ArrayList;
import java.util.List;

public class DingErMa implements FilterCondition {
   List<String> condition;

  public DingErMa(List<String> condition) {
    this.condition=condition;
  }
  @Override
  public void doFilter(List<String> source) {
    List<String> toremove=new ArrayList<>();

    for(String item:source){
      if(!isInclude(item, condition)){
        toremove.add(item);
      }
    }

    source.removeAll(toremove);

  }

  @Override
  public FilterCondition reverseCondition() {
    return new ShaErMa(condition);
  }

  boolean isInclude(String item, List<String> condition) {//456

    if (condition.contains(item.charAt(0)+""+item.charAt(1)) ||
        condition.contains(item.charAt(1)+""+item.charAt(0)) ||
        condition.contains(item.charAt(2)+""+item.charAt(0)) ||
        condition.contains(item.charAt(0)+""+item.charAt(2)) ||
        condition.contains(item.charAt(1)+""+item.charAt(2)) ||
        condition.contains(item.charAt(2)+""+item.charAt(1))) {
      return true;
    }

    return false;
  }
}