package com.lchli.lotfilter.filters;



import java.util.ArrayList;
import java.util.List;

public class Zu3 implements FilterCondition {
  @Override
  public void doFilter(List<String> source) {
      
      List<String> toremove=new ArrayList<>();

      for(String item:source){
          if(!isInclude(item)){
              toremove.add(item);
          }
      }

      source.removeAll(toremove);
  }

  @Override
  public FilterCondition reverseCondition() {
    return new _ZuSanReverse();
  }

  boolean isInclude(String element) {
      String a=element.substring(0,1);
      String b=element.substring(1,2);
      String c=element.substring(2,3);
    //002
    if (a.equals(b) && b.equals(c)) {
      return false;
    }

    return a.equals(b)||b.equals(c)||a.equals(c);
  }


 public static class _ZuSanReverse extends Zu3 {
    @Override
    boolean isInclude(String element) {
      return !super.isInclude(element);
    }

    @Override
    public FilterCondition reverseCondition() {
      return new Zu3();
    }
  }
}
