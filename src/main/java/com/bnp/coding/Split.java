package com.bnp.coding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA.
 * User: Alim.SLIMANI
 * Date: 30/1/20
 */

public class Split {

    public static <E, F> List<F> split(List<E> list, int size, Function<List<E>, F> func) {
        List<F> result = new ArrayList<F>(list.size() / size + 1);
        List<E> sublist = null;
        for (int i = 0; i < list.size(); i++) {
            if (i % size == 0) {
                if (sublist != null)
                    result.add(func.apply(sublist));
                sublist = new ArrayList<E>(size);
            }
            sublist.add(list.get(i));
        }
        result.add(func.apply(sublist));
        return result;
    }
}
