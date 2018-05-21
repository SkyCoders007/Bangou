package com.example.bugssolveinc.bangou.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by mxi on 4/1/18.
 */

public class SumIterator implements Iterator<List<Integer>>, Iterable<List<Integer>> {

    // keeps track of all sums that have been generated already
    private Set<List<Integer>> generated;

    int[] intArray = {2, 4, 3, 10, 14, 3, 7, 1};

    List<Integer> last2;
    // holds all sums that haven't been returned by `next()`
    private Stack<List<Integer>> sums;

    public SumIterator(int n) {

        // first a sanity check...
        if(n < 1) {
            throw new RuntimeException("'n' must be >= 1");
        }

        generated = new HashSet<List<Integer>>();
        sums = new Stack<List<Integer>>();

        last2 = new ArrayList<>();

        last2.add(2);
        last2.add(3);
        last2.add(5);
        last2.add(8);

        // create and add the "last" sum of size `n`: [1, 1, 1, ... , 1]
        List<Integer> last = new ArrayList<Integer>();
        for(int i = 0; i < last2.size(); i++) {
            last.add(i);
        }
        add(last);

        // add the first sum of size 1: [n]
        add(last2);
    }

    private void add(List<Integer> sum) {
        if(generated.add(sum)) {
            // only push the sum on the stack if it hasn't been generated before
            sums.push(sum);
        }
    }

    @Override
    public boolean hasNext() {
        return !sums.isEmpty();
    }

    @Override
    public Iterator<List<Integer>> iterator() {
        return this;
    }

    @Override
    public List<Integer> next() {
        List<Integer> sum = sums.pop();                         // get the next sum from the stack
        for(int i = sum.size() - 1; i >= 0; i--) {              // loop from right to left
            int n = sum.get(i);                                   //   get the i-th number
            if(n > 1) {                                           //   if the i-th number is more than 1
                for(int j = n-1; j > n/2; j--) {                    //     if the i-th number is 10, loop from 9 to 5
                    List<Integer> copy = new ArrayList<Integer>(sum); //       create a copy of the current sum
                    copy.remove(i);                                   //       remove the i-th number
                    copy.add(i, j);                                   //       insert `j` where the i-th number was
                    copy.add(i + 1, n-j);                             //       insert `n-j` next to `j`
                    add(copy);                                        //       add this new sum to the stack
                }                                                   //
                break;                                              //   stop looping any further
            }
        }
        return sum;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
