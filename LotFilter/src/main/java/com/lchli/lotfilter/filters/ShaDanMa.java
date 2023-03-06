package com.lchli.lotfilter.filters;



import java.util.List;

public class ShaDanMa extends DingDanMa {


  public ShaDanMa(List<String> mdanMa) {
    super(mdanMa);
  }

  @Override
  public FilterCondition reverseCondition() {
    return new DingDanMa(mdanMa);
  }

  boolean isInclude(String item, List<String> dmList) {
    return !super.isInclude(item, dmList);
  }
}