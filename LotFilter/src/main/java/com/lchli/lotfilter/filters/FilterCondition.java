package com.lchli.lotfilter.filters;

import java.util.List;

public interface FilterCondition {
    public void doFilter(List<String> source);

    public FilterCondition reverseCondition();
}
