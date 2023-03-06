package com.lchli.lotfilter.filters;


import java.util.ArrayList;
import java.util.List;

public class NMa implements FilterCondition {
    List<String> mdanMa;
    List<String> counts;

    public NMa(List<String> mdanMa, List<String> counts) {
        this.mdanMa = mdanMa;
        this.counts = counts;
    }


    private boolean isInclude(String item, int count) {
        int sum=0;
        if (mdanMa.contains(item.substring(0, 1))){
            sum++;
        }
        if (mdanMa.contains(item.substring(1, 2))){
            sum++;
        }
        if (mdanMa.contains(item.substring(2, 3))){
            sum++;
        }

        return sum==count;
    }


    @Override
    public void doFilter(List<String> source) {
        List<String> toremove = new ArrayList<>();

        for (String item : source) {
            if (!isInclude(item)) {
                toremove.add(item);
            }
        }

        source.removeAll(toremove);
    }

    @Override
    public FilterCondition reverseCondition() {
        return new ShaDanMa(mdanMa);
    }

    boolean isInclude(String item) {
        if (mdanMa == null || mdanMa.isEmpty()) {
            return true;
        }
        if (counts == null || counts.isEmpty()) {
            return true;
        }

        boolean isInclude = false;

        for (String cnt : counts) {
            isInclude = isInclude || isInclude(item, Integer.parseInt(cnt));
        }

        return isInclude;
    }

    public static class _NMaReverse extends NMa{

        public _NMaReverse(List<String> mdanMa, List<String> counts) {
            super(mdanMa, counts);
        }

        @Override
        boolean isInclude(String item) {
            return !super.isInclude(item);
        }
    }
}

