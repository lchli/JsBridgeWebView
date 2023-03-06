package com.lchli.lotfilter.filters;



import java.util.List;

public class DingHewei extends DingDanMa {


  public DingHewei(List<String> mdanMa) {
    super(mdanMa);
  }

  boolean isInclude(String item, List<String> dmList) {
    if (dmList.contains(getItemHeWei(item))) {
      return true;
    }

    return false;
  }

  @Override
  public FilterCondition reverseCondition() {
    return new DingHeweiReverse(mdanMa);
  }

  String getItemHeWei(String item) {
    int ret = 0;
    int len = item.length();
    for (int i = 0; i < len; i++) {
      ret += Integer.parseInt(item.substring(i, i + 1));
    }
    ret %= 10;
    return ret+"";
  }


  public static  class DingHeweiReverse extends DingHewei{


    public DingHeweiReverse(List<String> mdanMa) {
      super(mdanMa);
    }

    @Override
    public boolean isInclude(String item, List<String> dmList) {
      return !super.isInclude(item, dmList);
    }

    @Override
    public FilterCondition reverseCondition() {
      return new DingHewei(mdanMa);
    }
  }
}
