package com.example.bugssolveinc.bangou.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.bugssolveinc.bangou.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mxi on 3/1/18.
 */

public class HIntActivity extends Activity {

    int[] intArray = {2, 4, 3, 10, 14, 3, 7, 2,4,8,25,2,4,11,3,1,7,6,8,9,13,6,9,17,9,5,8};

    List<Integer> list;
    List<Integer> list2;

    TextView tv_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_hint = (TextView)findViewById(R.id.tv_hint);

        list = new ArrayList<>();
        list2 = new ArrayList<>();

        fourSum(intArray,10);



        tv_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.size() == 0){

                    Log.e("FinalListArrayMul",list2.toString());

                }else if (list2.size() == 0){

                    Log.e("FinalListArraySum",list.toString());

                }else {

                }
            }
        });
    }

    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> listSet = new ArrayList<List<Integer>>();
        if (num.length < 3) return listSet;

        Arrays.sort(num);


        sumA(num,target,listSet);



        return listSet;
    }



    private void sumA(int[] num, int target, List<List<Integer>> listSet) {


        for (int i = 0; i < num.length - 3; i = increment(num, i)) {
            for (int j = i + 1; j < num.length - 2; j = increment(num, j)) {
                int a = num[i], b = num[j], lo = j + 1, hi = num.length - 1;
                while (lo < hi) {
                    int c = num[lo], d = num[hi];
                    int sum = a + b ;


                    //Sum
                    if (sum == target) {
                        list = Arrays.asList(a, b);
                        listSet.add(list);
                        lo = increment(num, lo);
                        hi = decrement(num, hi);

                        Log.e("@@FINAL",list.toString());

                    } else if (sum < target) {
                        lo = increment(num, lo);

                       // mulA(num,target,listSet);
                        Log.e("Stop","........1");

                    } else {
                        hi = decrement(num, hi);

                        Log.e("Stop","........2");

                        /*if (list.size() == 0){

                            mulA(num,target,listSet);
                        }*/

                    }


                }
            }
        }


    }

   /* private void mulA(int[] num, int target, List<List<Integer>> listSet) {

        //MUL
        for (int i = 0; i < num.length - 3; i = increment3(num, i)) {
            for (int j = i + 1; j < num.length - 2; j = increment3(num, j)) {
                int a = num[i], b = num[j], lo = j + 1, hi = num.length - 1;
                while (lo < hi) {
                    int c = num[lo], d = num[hi];

                    int mul = a * b * c * d;


                    //Sum
                    if (mul == target) {
                        list2 = Arrays.asList(a, b, c, d);
                        listSet.add(list2);
                        lo = increment3(num, lo);
                        hi = decrement3(num, hi);

                        Log.e("@@FINALMUL",list2.toString());

                    } else if (mul < target) {
                        lo = increment3(num, lo);

                        Log.e("Stop","........3");

                    } else {
                        hi = decrement3(num, hi);

                        Log.e("Stop","........4");

                       *//* if (list2.size() == 0){

                            sumA(num,target,listSet);
                        }*//*
                    }


                }
            }
        }



    }*/

    private int increment(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrement(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }

    private int increment2(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrement2(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }

    private int increment3(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrement3(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }

    private int increment4(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrement4(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }

}


