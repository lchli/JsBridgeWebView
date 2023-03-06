package com.lchli.lotfilter.filters;



import java.util.ArrayList;
import java.util.List;

public class ZZZ implements FilterCondition {
  @Override
 public void doFilter(List<String> source) {
    List<String> toremove=new ArrayList<>();

    for(String element:source){
      if(!isInclude(element)){
        toremove.add(element);
      }
    }

    source.removeAll(toremove);

  }

  @Override
  public FilterCondition reverseCondition() {
    return new _ZZZReverse();
  }

  boolean isInclude(String element) {
      String a=element.substring(0,1);
      String b=element.substring(1,2);
      String c=element.substring(2,3);

    return a.equals(b)&&b.equals(c);
  }


  public   static class _ZZZReverse extends ZZZ {
    @Override
    boolean isInclude(String element) {
      return !super.isInclude(element);
    }

    @Override
    public FilterCondition reverseCondition() {
      return new ZZZ();
    }
  }
}
