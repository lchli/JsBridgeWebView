package com.lchli.lotfilter.filters;



import java.util.List;

public class DingKuadu extends DingDanMa {


  public DingKuadu(List<String> mdanMa) {
    super(mdanMa);
  }

  boolean isInclude(String item, List<String> dmList) {
    if (dmList.contains(getItemKuadu(item))) {
      return true;
    }
    return false;
  }

  String getItemKuadu(String item) {
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

  @Override
  public FilterCondition reverseCondition() {
    return  new DingKuaduReverse(mdanMa);
  }



  public static class DingKuaduReverse extends DingKuadu{


    public DingKuaduReverse(List<String> mdanMa) {
      super(mdanMa);
    }

    @Override
    public boolean isInclude(String item, List<String> dmList) {
      return !super.isInclude(item, dmList);
    }

    @Override
    public  FilterCondition reverseCondition() {
      return new DingKuadu(mdanMa);
    }
  }

}
