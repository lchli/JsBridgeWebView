package com.lchli.lotfilter.filters;



import java.util.ArrayList;
import java.util.List;

public class FuShi implements FilterCondition {
  //n码复试=3
  final List<String> mdanMa;

  public FuShi(List<String> mdanMa) {
    this.mdanMa = mdanMa;
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
    return new FuShiReverse(mdanMa);
  }

  boolean isInclude(String item, List<String> dmList) {
    //123   1234567
    for (int i = 0; i < item.length(); i++) {
      if (!dmList.contains(item.charAt(i)+"")) {
        return false;
      }
    }

    return true;
  }


 private static class FuShiReverse extends FuShi {


   public FuShiReverse(List<String> mdanMa) {
     super(mdanMa);
   }

   @Override
    boolean isInclude(String item, List<String> dmList) {
      return !super.isInclude(item, dmList);
    }

    @Override
    public FilterCondition reverseCondition() {
      return new FuShi(mdanMa);
    }
  }
}
