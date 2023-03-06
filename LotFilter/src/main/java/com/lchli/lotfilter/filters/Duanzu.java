package com.lchli.lotfilter.filters;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Duanzu implements FilterCondition {
  final List<String> condition1;
  final List<String> condition2;
  final List<String> condition3;

  public Duanzu(List<String> condition1, List<String> condition2, List<String> condition3) {
    this.condition1 = condition1;
    this.condition2 = condition2;
    this.condition3 = condition3;
  }

  @Override
  public FilterCondition reverseCondition() {
    return new _DuanzuReverse(condition1,condition2,condition3);
  }

  @Override
  public void doFilter(List<String> source) {
   // print(this.condition1.toString()+"-"+this.condition2.toString()+"-"+this.condition3.toString());

    List<String> duanzuDi = _makeZuXuan();

    List<String> toremove=new ArrayList<>();

    for(String item:source){
      if(isInclude(item, duanzuDi)){
        toremove.add(item);
      }
    }

    source.removeAll(toremove);
  }

  boolean isInclude(String item, List<String> condition) {
    return condition.contains(item);
  }

  List<String> _makeZuXuan() {
    List<String> list = new ArrayList<>();
    Map<String, Boolean> map = new HashMap<>();

    for (int i = 0; i < condition1.size(); i++) {
      for (int j = 0; j < condition2.size(); j++) {
        for (int k = 0; k < condition3.size(); k++) {
          String ijk =
              _getSortedIJK(condition1.get(i), condition2.get(j), condition3.get(k));
          if (map.get(ijk) == null) {
            list.add(ijk);
            map.put(ijk,true);
          }
        }
      }
    }

    return list;
  }

  String _getSortedIJK(String i, String j, String k) {
    List<String> list = Arrays.asList(i, j, k);
    Collections.sort(list);

    String ret = "";
    for(String element:list){
      ret += element;
    }

    return ret;
  }


  private static class _DuanzuReverse extends Duanzu {


    public _DuanzuReverse(List<String> condition1, List<String> condition2, List<String> condition3) {
      super(condition1, condition2, condition3);
    }

    @Override
    boolean isInclude(String item, List<String> condition) {
      return !super.isInclude(item, condition);
    }

    @Override
    public FilterCondition reverseCondition() {
      return new Duanzu(condition1,condition2,condition3);
    }
  }
}
