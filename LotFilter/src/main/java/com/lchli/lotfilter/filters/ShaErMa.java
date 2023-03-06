package com.lchli.lotfilter.filters;



import java.util.List;

public class ShaErMa extends DingErMa {


  public ShaErMa(List<String> condition) {
    super(condition);
  }

  @Override
  boolean isInclude(String item, List<String> condition) {
    return !super.isInclude(item, condition);
  }

  @Override
  public FilterCondition reverseCondition() {
    return new DingErMa(condition);
  }

}