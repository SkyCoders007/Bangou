package com.example.bugssolveinc.bangou.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.bugssolveinc.bangou.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mxi on 4/1/18.
 */

public class DemoHint extends AppCompatActivity {

    TextView tv_hint;

    int n = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] pointSet = { 1, 2, 3};
        int number = 20;

        /*
         * For input 5
         * Total combination count is : 13
         * Combinations are as below:
         * 1 1 1 1 1
         * 1 1 1 2
         * 1 1 2 1
         * 1 1 3
         * 1 2 1 1
         * 1 2 2
         * 1 3 1
         * 2 1 1 1
         * 2 1 2
         * 2 2 1
         * 2 3
         * 3 1 1
         * 3 2
         */


       /* long startTime = System.nanoTime();

        long endTime = System.nanoTime();



        HashMap<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
        startTime = System.nanoTime();

        endTime = System.nanoTime();*/

        ArrayList<Integer> inputArray = new ArrayList<Integer>();
        printCombinationCount(pointSet, number, inputArray);

    }

    public static int totalCombinationCountRecursive(int[] pointSet, int number) {
        int sum = 0;
        for (int i = 0; i < pointSet.length; i++) {
            if (number > pointSet[i]) {
                sum += totalCombinationCountRecursive(pointSet, number - pointSet[i]);
            } else if (number == pointSet[i]) {
                sum += 1;
            }
        }
        return sum;
    }

    public static int totalCombinationCountDynamic(int[] pointSet, int number, HashMap<Integer, Integer> resultMap) {
        int sum = 0;
        for (int i = 0; i < pointSet.length; i++) {
            if (number > pointSet[i]) {
                if (resultMap.containsKey(number - pointSet[i])) {
                    sum += resultMap.get(number - pointSet[i]);
                } else {
                    sum += totalCombinationCountDynamic(pointSet, number - pointSet[i], resultMap);
                }
            } else if (number == pointSet[i]) {
                sum += 1;
            }
        }
        resultMap.put(number, sum);
        return sum;
    }

    public static void printCombinationCount(int[] pointSet, int number, ArrayList<Integer> inputArray) {
        for (int i = 0; i < pointSet.length; i++) {
            if (number > pointSet[i]) {
                ArrayList<Integer> newInputArr = new ArrayList<>(inputArray);
                newInputArr.add(pointSet[i]);
                printCombinationCount(pointSet, number - pointSet[i], newInputArr);
            } else if (number == pointSet[i]) {
                for (Integer integer : inputArray) {
                    System.out.print(integer + " ");
                }
                System.out.println(pointSet[i]);
            }
        }
    }

}
