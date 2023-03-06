package com.lchli.lotfilter;


import com.lchli.lotfilter.filters.FilterCondition;

import java.util.ArrayList;
import java.util.List;

public class FilterManager {
    private final List<FilterCondition> _filters = new ArrayList<>();
    private List<String> _out;
    private int _mLevel;

    public int getFilterSize() {
        return _filters.size();
    }


    /// @param dataSource
    /// @return the same list has been filtered.
    public List<String> runFilter(List<String> dataSource) {
        if (_filters.isEmpty()) {
            return dataSource;
        }

        for (FilterCondition condition : _filters) {
            condition.doFilter(dataSource);
        }

        return dataSource;
    }

    public void addFilter(FilterCondition filter) {
        _filters.add(filter);
    }


    private List<String> _rongCuoFilterN(List<String> dataSource, int level) {
        final int filterSize = _filters.size();
        if (level == 0)
            return runFilter(dataSource);

        _out = null;
        _mLevel = level;

        _rongCuo(level, filterSize, 0, dataSource);
        return _out;
    }

    /**
     * @param dataSource
     * @param levels
     * @return a new list.
     */
    public List<String> runRongCuoFilter(List<String> dataSource,
                                         List<Integer> levels) {
        List<String> ret = null;

        for (int level : levels) {
            List<String> temp = _rongCuoFilterN(
                    new ArrayList<>(dataSource), level);
            if (ret == null)
                ret = temp;
            else { // union.
                for (String e : temp) {
                    if (!ret.contains(e)) {
                        ret.add(e);
                    }
                }//for

            }


        }//for

        return ret;
    }

    private void _rongCuo(int level, final int filterSize, int start,
                          final List<String> dataSource) {
        for (int i = start; i < filterSize; i++) {
            //filters.get(i).setUnFilter(true);
            _filters.set(i, _filters.get(i).reverseCondition());
            if (level > 0)
                level--;
            if (level > 0) {
                _rongCuo(level, filterSize, i + 1, dataSource);
                // filters.get(i).setUnFilter(false);
                _filters.set(i, _filters.get(i).reverseCondition()); //restore
                level = _mLevel;
            } else {
                List<String> temp = runFilter(new ArrayList<>(dataSource));
                //filters.get(i).setUnFilter(false);
                _filters.set(i, _filters.get(i).reverseCondition()); //restore
                if (_out == null)
                    _out = temp;
                else {
                    // out.removeAll(temp);
                    for (String e : temp) {
                        if (!_out.contains(e)) {
                            _out.add(e);
                        }
                    }//for

                }
            }
        }
    }
}
