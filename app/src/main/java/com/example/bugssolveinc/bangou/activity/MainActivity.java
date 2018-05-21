package com.example.bugssolveinc.bangou.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.LoginFilter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bugssolveinc.bangou.R;
import com.example.bugssolveinc.bangou.comman.CommanClass;
import com.example.bugssolveinc.bangou.gameanimation.MyBounceInterpolator;
import com.example.bugssolveinc.bangou.helper.DatabaseHelper;
import com.example.bugssolveinc.bangou.model.PointTable;
import com.example.bugssolveinc.bangou.model.UndoData;
import com.example.bugssolveinc.bangou.service.BackgroundMediaService;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.example.bugssolveinc.bangou.R.string.fourty;
import static com.example.bugssolveinc.bangou.R.string.one;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout ln_1, ln_2, ln_3, ln_4, ln_5, ln_6, ln_7, ln_8, ln_9, ln_10,

    ln_11, ln_12, ln_13, ln_14, ln_15, ln_16, ln_17, ln_18, ln_19, ln_20,

    ln_21, ln_22, ln_23, ln_24, ln_25, ln_26, ln_27, ln_28;

    TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_10,

    tv_11, tv_12, tv_13, tv_14, tv_15, tv_16, tv_17, tv_18, tv_19, tv_20,

    tv_21, tv_22, tv_23, tv_24, tv_25, tv_26, tv_27, tv_28;

    ImageView tv_op_1, tv_op_2, tv_op_3, tv_op_4, tv_op_5;

    TextView tv_timer, tv_points,tv_points_head;
    TextView tv_undo,tv_best_time_main,tv_screen_final_equation;

    LinearLayout ln_start_game;
    LinearLayout ln_easy,ln_besttime_dis;

    LinearLayout ln_operation_1, ln_operation_2, ln_operation_3, ln_operation_4, ln_operation_5;

    boolean num_1 = false, num_2 = false, num_3 = false, num_4 = false, num_5 = false, num_6 = false, num_7 = false, num_8 = false, num_9 = false, num_10 = false,

    num_11 = false, num_12 = false, num_13 = false, num_14 = false, num_15 = false, num_16 = false, num_17 = false, num_18 = false, num_19 = false, num_20 = false,

    num_21 = false, num_22 = false, num_23 = false, num_24 = false, num_25 = false, num_26 = false, num_27 = false, num_28 = false;

   boolean rearrangeNumber_1,rearrangeNumber_2,rearrangeNumber_3,rearrangeNumber_4,rearrangeNumber_5,rearrangeNumber_6,rearrangeNumber_7,rearrangeNumber_8,rearrangeNumber_9,rearrangeNumber_10
           ,rearrangeNumber_11,rearrangeNumber_12,rearrangeNumber_13,rearrangeNumber_14,rearrangeNumber_15,rearrangeNumber_16,rearrangeNumber_17,rearrangeNumber_18,rearrangeNumber_19,rearrangeNumber_20
           ,rearrangeNumber_21,rearrangeNumber_22,rearrangeNumber_23,rearrangeNumber_24,rearrangeNumber_25,rearrangeNumber_26,rearrangeNumber_27,rearrangeNumber_28;

    boolean rearrangeNumber_s1,rearrangeNumber_s2,rearrangeNumber_s3,rearrangeNumber_s4,rearrangeNumber_s5,rearrangeNumber_s6,rearrangeNumber_s7,rearrangeNumber_s8,rearrangeNumber_s9,rearrangeNumber_s10
            ,rearrangeNumber_s11,rearrangeNumber_s12,rearrangeNumber_s13,rearrangeNumber_s14,rearrangeNumber_s15,rearrangeNumber_s16,rearrangeNumber_s17,rearrangeNumber_s18,rearrangeNumber_s19,rearrangeNumber_s20
            ,rearrangeNumber_s21,rearrangeNumber_s22,rearrangeNumber_s23,rearrangeNumber_s24,rearrangeNumber_s25,rearrangeNumber_s26,rearrangeNumber_s27,rearrangeNumber_s28;


    int uniq_number1,uniq_number2,uniq_number3,uniq_number4,uniq_number5,uniq_number6,uniq_number7,uniq_number8,uniq_number9,uniq_number10,
            uniq_number11,uniq_number12,uniq_number13,uniq_number14,uniq_number15,uniq_number16,uniq_number17,uniq_number18,uniq_number19,uniq_number20,
            uniq_number21,uniq_number22,uniq_number23,uniq_number24,uniq_number25,uniq_number26,uniq_number27,uniq_number28;

    private boolean lastNumeric;
    private boolean stateError;
    private TextView txtScreen, tv_hint;

    boolean selectOne = false, finalAnswerValidation, undoAll = false;
    boolean selectOneOperation;
    boolean selectFinal = false;
    boolean equalNotDisplayRhs = false;
    boolean notWin,divideError = false,hintError = false;

    boolean lhsValue = false, rhsValue = true;

    boolean startHint = false;
    boolean autoNumberSeven = false;
    boolean autoNumberFourteen = false;
    boolean autoNumberTwentyOne = false;

    CommanClass cc;


    String LHS, RHS;

    int[] array_set_up_text;
    Random rand_set_up_text;
    int rand_text_set_up;


    /*count down timer*/

    private long timeCountInMilliSeconds = 1 * 60000;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    private TimerStatus timerStatus = TimerStatus.STOPPED;
    CountDownTimer countDownTimer;

    /*Level easy and hard*/
    Boolean easy;
    Boolean hard;

    /*counter for number calculating*/
    int firstCount = 0;
    int secondCount = 0;

    /*ArrayList for adding number*/
    ArrayList<Integer> lhsNumberList;
    ArrayList<Integer> rhsNumberList;
    ArrayList<Integer> finalNumberList;
    ArrayList<Integer> undoNumberOne;
    ArrayList<Integer> undoNumberTwo;
    ArrayList<Integer> undoNumberFirstArrange;
    ArrayList<Integer> automaticNumberList;
    ArrayList<UndoData> undoNumberSecondArrange;
    ArrayList al = new ArrayList();

    /* Get Value*/

    boolean getvalue1 = false;

    /*dialog start new game*/
    Dialog dialog, dialog2;

    /*database helper for point*/
    DatabaseHelper db;


    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;

    Handler handler;

    int Seconds, Minutes, MilliSeconds;

    int scoreTeamA = 0;
    private static final String SCORE_TEAM_A_KEY = "score A";
    private static final String UNDO_A_KEY = "undo A";
    boolean hintnumber = false;

    ArrayList<Integer> hintCountList;
    int hintFirstCount = 0;

    Animation clickAnim;

    int undoCount = 0;

    String fm;

    StringBuffer buffer;


    //Hint
    ArrayList<Integer> result;
    int counter = 0;
    int[] hint_first_array;
    int[] hint_second_intarray;
    ArrayList<Integer> hint_second_arraylist;

    int targetNumber = 72;

    List<Integer> listHintFinal;
    List<Integer> listHintFinalCopied;

    String popUpNumber,bestTime;

    String finalLhs,finalRhs;

    boolean stopLoop = false;

    Handler hintHandler;

    Dialog  dialog1;

    int totalEarningHighScore = 0;


    private boolean startedSeven = false;
    private boolean startedFourteen = false;
    private boolean startedTwentyOne = false;
    private Handler handlerSeven = new Handler();
    private Handler handlerFourteen = new Handler();
    private Handler handlerTwentyOne = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());

        cc = new CommanClass(this);

        clickAnim = AnimationUtils.loadAnimation(this, R.anim.click_anim);

        hintCountList = new ArrayList<>();


        rand_set_up_text = new Random();
        array_set_up_text = new int[]{one, R.string.two, R.string.three, R.string.four, R.string.five, R.string.six, R.string.seven,
                R.string.eight, R.string.nine, R.string.ten, R.string.elevan, R.string.twel, R.string.thirty, R.string.fourty, R.string.fifty, R.string.sixty,
                R.string.seventy, R.string.eighty, R.string.ninty, R.string.twenty, R.string.twentyone, R.string.twentytwo, R.string.twentythree, R.string.twentyfour, R.string.twentyfive,
                R.string.twentysix, R.string.twentyseven, R.string.twentyeight};

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);

        undoNumberFirstArrange = new ArrayList<>();
        undoNumberSecondArrange = new ArrayList<>();
        hint_second_arraylist = new ArrayList<>();



        listHintFinal = new ArrayList<>();
        listHintFinalCopied = new ArrayList<>();

        hintHandler = new Handler();


        initLinearLayoutNumber();
        initTextViewNumber();
        initTextViewOperation();
        initLinearLayoutOperation();

        startStop();

        easy = cc.loadPrefBoolean("Easy");

        hard = cc.loadPrefBoolean("Hard");

        if (easy == true) {

            Log.e("@LevelChoos", "Easy");

            cc.savePrefBoolean("Easy", true);

        } else {

            ln_easy.setVisibility(View.GONE);
            ln_besttime_dis.setVisibility(View.INVISIBLE);

            Log.e("@LevelChoos", "Hard");

        }

        lhsNumberList = new ArrayList<>();
        rhsNumberList = new ArrayList<>();
        finalNumberList = new ArrayList<>();
        undoNumberOne = new ArrayList<>();
        undoNumberTwo = new ArrayList<>();
        automaticNumberList = new ArrayList<>();


        al = new ArrayList<>();

        int pick;
        for (int j = 0; j < 29; j++) {

            pick = rand_set_up_text.nextInt(29);
            al.add(pick);
            System.out.println("Contents of al: " + al);


        }
        handler = new Handler();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initTextViewOperation() {


        txtScreen = (TextView) findViewById(R.id.tv_screen);

        tv_op_1 = (ImageView) findViewById(R.id.tv_op_1);
        tv_op_2 = (ImageView) findViewById(R.id.tv_op_2);
        tv_op_3 = (ImageView) findViewById(R.id.tv_op_3);
        tv_op_4 = (ImageView) findViewById(R.id.tv_op_4);
        tv_op_5 = (ImageView) findViewById(R.id.tv_op_5);

        tv_hint = (TextView) findViewById(R.id.tv_hint);

        tv_op_1.setOnClickListener(this);
        tv_op_2.setOnClickListener(this);
        tv_op_3.setOnClickListener(this);
        tv_op_4.setOnClickListener(this);
        tv_op_5.setOnClickListener(this);

    }

    private void initLinearLayoutNumber() {

        /*start new game*/
        ln_start_game = (LinearLayout) findViewById(R.id.ln_start_game);


        ln_easy = (LinearLayout) findViewById(R.id.ln_easy);
        ln_besttime_dis = (LinearLayout) findViewById(R.id.ln_besttime_dis);

        //Undo number textview//
        tv_undo = (TextView) findViewById(R.id.tv_undo);

        tv_best_time_main = (TextView) findViewById(R.id.tv_best_time_main);

        if (cc.loadPrefString("best_time2").isEmpty()){

            tv_best_time_main.setText("00:00");

        }else {

            tv_best_time_main.setText(cc.loadPrefString("best_time2"));

        }

        ln_1 = (LinearLayout) findViewById(R.id.ln_1);
        ln_2 = (LinearLayout) findViewById(R.id.ln_2);
        ln_3 = (LinearLayout) findViewById(R.id.ln_3);
        ln_4 = (LinearLayout) findViewById(R.id.ln_4);
        ln_5 = (LinearLayout) findViewById(R.id.ln_5);
        ln_6 = (LinearLayout) findViewById(R.id.ln_6);
        ln_7 = (LinearLayout) findViewById(R.id.ln_7);
        ln_8 = (LinearLayout) findViewById(R.id.ln_8);
        ln_9 = (LinearLayout) findViewById(R.id.ln_9);
        ln_10 = (LinearLayout) findViewById(R.id.ln_10);
        ln_11 = (LinearLayout) findViewById(R.id.ln_11);
        ln_12 = (LinearLayout) findViewById(R.id.ln_12);
        ln_13 = (LinearLayout) findViewById(R.id.ln_13);
        ln_14 = (LinearLayout) findViewById(R.id.ln_14);
        ln_15 = (LinearLayout) findViewById(R.id.ln_15);
        ln_16 = (LinearLayout) findViewById(R.id.ln_16);
        ln_17 = (LinearLayout) findViewById(R.id.ln_17);
        ln_18 = (LinearLayout) findViewById(R.id.ln_18);
        ln_19 = (LinearLayout) findViewById(R.id.ln_19);
        ln_20 = (LinearLayout) findViewById(R.id.ln_20);
        ln_21 = (LinearLayout) findViewById(R.id.ln_21);
        ln_22 = (LinearLayout) findViewById(R.id.ln_22);
        ln_23 = (LinearLayout) findViewById(R.id.ln_23);
        ln_24 = (LinearLayout) findViewById(R.id.ln_24);
        ln_25 = (LinearLayout) findViewById(R.id.ln_25);
        ln_26 = (LinearLayout) findViewById(R.id.ln_26);
        ln_27 = (LinearLayout) findViewById(R.id.ln_27);
        ln_28 = (LinearLayout) findViewById(R.id.ln_28);


        ln_1.setOnClickListener(this);
        ln_2.setOnClickListener(this);
        ln_3.setOnClickListener(this);
        ln_4.setOnClickListener(this);
        ln_5.setOnClickListener(this);
        ln_6.setOnClickListener(this);
        ln_7.setOnClickListener(this);
        ln_8.setOnClickListener(this);
        ln_9.setOnClickListener(this);
        ln_10.setOnClickListener(this);
        ln_11.setOnClickListener(this);
        ln_12.setOnClickListener(this);
        ln_13.setOnClickListener(this);
        ln_14.setOnClickListener(this);
        ln_15.setOnClickListener(this);
        ln_16.setOnClickListener(this);
        ln_17.setOnClickListener(this);
        ln_18.setOnClickListener(this);
        ln_19.setOnClickListener(this);
        ln_20.setOnClickListener(this);
        ln_21.setOnClickListener(this);
        ln_22.setOnClickListener(this);
        ln_23.setOnClickListener(this);
        ln_24.setOnClickListener(this);
        ln_25.setOnClickListener(this);
        ln_26.setOnClickListener(this);
        ln_27.setOnClickListener(this);
        ln_28.setOnClickListener(this);

        /*start new game*/
        ln_start_game.setOnClickListener(this);
    }

    private void initTextViewNumber() {

        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_4 = (TextView) findViewById(R.id.tv_4);
        tv_5 = (TextView) findViewById(R.id.tv_5);
        tv_6 = (TextView) findViewById(R.id.tv_6);
        tv_7 = (TextView) findViewById(R.id.tv_7);
        tv_8 = (TextView) findViewById(R.id.tv_8);
        tv_9 = (TextView) findViewById(R.id.tv_9);
        tv_10 = (TextView) findViewById(R.id.tv_10);
        tv_11 = (TextView) findViewById(R.id.tv_11);
        tv_12 = (TextView) findViewById(R.id.tv_12);
        tv_13 = (TextView) findViewById(R.id.tv_13);
        tv_14 = (TextView) findViewById(R.id.tv_14);
        tv_15 = (TextView) findViewById(R.id.tv_15);
        tv_16 = (TextView) findViewById(R.id.tv_16);
        tv_17 = (TextView) findViewById(R.id.tv_17);
        tv_18 = (TextView) findViewById(R.id.tv_18);
        tv_19 = (TextView) findViewById(R.id.tv_19);
        tv_20 = (TextView) findViewById(R.id.tv_20);
        tv_21 = (TextView) findViewById(R.id.tv_21);
        tv_22 = (TextView) findViewById(R.id.tv_22);
        tv_23 = (TextView) findViewById(R.id.tv_23);
        tv_24 = (TextView) findViewById(R.id.tv_24);
        tv_25 = (TextView) findViewById(R.id.tv_25);
        tv_26 = (TextView) findViewById(R.id.tv_26);
        tv_27 = (TextView) findViewById(R.id.tv_27);
        tv_28 = (TextView) findViewById(R.id.tv_28);

        tv_screen_final_equation = (TextView) findViewById(R.id.tv_screen_final_equation);


        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_1.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));


        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_2.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));


        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_3.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_4.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_5.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_6.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_7.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_8.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_9.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_10.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_11.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_12.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_13.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_14.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_15.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_16.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_17.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_18.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_19.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_20.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_21.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_22.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_23.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_24.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_25.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_26.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_27.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
        tv_28.setText(array_set_up_text[rand_text_set_up]);

        Log.e("number", String.valueOf(rand_text_set_up + 1));

        /*timer*/
        tv_timer = (TextView) findViewById(R.id.tv_timer);

        /*points*/
        tv_points = (TextView) findViewById(R.id.tv_points);
        tv_points_head = (TextView) findViewById(R.id.tv_points_head);

        tv_1.setTextColor(Color.parseColor("#000000"));
        tv_2.setTextColor(Color.parseColor("#000000"));
        tv_3.setTextColor(Color.parseColor("#000000"));
        tv_4.setTextColor(Color.parseColor("#000000"));
        tv_5.setTextColor(Color.parseColor("#000000"));
        tv_6.setTextColor(Color.parseColor("#000000"));
        tv_7.setTextColor(Color.parseColor("#000000"));
        tv_8.setTextColor(Color.parseColor("#000000"));
        tv_9.setTextColor(Color.parseColor("#000000"));
        tv_10.setTextColor(Color.parseColor("#000000"));
        tv_11.setTextColor(Color.parseColor("#000000"));
        tv_12.setTextColor(Color.parseColor("#000000"));
        tv_13.setTextColor(Color.parseColor("#000000"));
        tv_14.setTextColor(Color.parseColor("#000000"));
        tv_15.setTextColor(Color.parseColor("#000000"));
        tv_16.setTextColor(Color.parseColor("#000000"));
        tv_17.setTextColor(Color.parseColor("#000000"));
        tv_18.setTextColor(Color.parseColor("#000000"));
        tv_19.setTextColor(Color.parseColor("#000000"));
        tv_20.setTextColor(Color.parseColor("#000000"));
        tv_21.setTextColor(Color.parseColor("#000000"));
        tv_22.setTextColor(Color.parseColor("#000000"));
        tv_23.setTextColor(Color.parseColor("#000000"));
        tv_24.setTextColor(Color.parseColor("#000000"));
        tv_25.setTextColor(Color.parseColor("#000000"));
        tv_26.setTextColor(Color.parseColor("#000000"));
        tv_27.setTextColor(Color.parseColor("#000000"));
        tv_28.setTextColor(Color.parseColor("#000000"));

        arrangeUndoNumberListFirst(tv_1);
        arrangeUndoNumberListFirst(tv_2);
        arrangeUndoNumberListFirst(tv_3);
        arrangeUndoNumberListFirst(tv_4);
        arrangeUndoNumberListFirst(tv_5);
        arrangeUndoNumberListFirst(tv_6);
        arrangeUndoNumberListFirst(tv_7);
        arrangeUndoNumberListFirst(tv_8);
        arrangeUndoNumberListFirst(tv_9);
        arrangeUndoNumberListFirst(tv_10);
        arrangeUndoNumberListFirst(tv_11);
        arrangeUndoNumberListFirst(tv_12);
        arrangeUndoNumberListFirst(tv_13);
        arrangeUndoNumberListFirst(tv_14);
        arrangeUndoNumberListFirst(tv_15);
        arrangeUndoNumberListFirst(tv_16);
        arrangeUndoNumberListFirst(tv_17);
        arrangeUndoNumberListFirst(tv_18);
        arrangeUndoNumberListFirst(tv_19);
        arrangeUndoNumberListFirst(tv_20);
        arrangeUndoNumberListFirst(tv_21);
        arrangeUndoNumberListFirst(tv_22);
        arrangeUndoNumberListFirst(tv_23);
        arrangeUndoNumberListFirst(tv_24);
        arrangeUndoNumberListFirst(tv_25);
        arrangeUndoNumberListFirst(tv_26);
        arrangeUndoNumberListFirst(tv_27);
        arrangeUndoNumberListFirst(tv_28);


    }

    private void initLinearLayoutOperation() {

        ln_operation_1 = (LinearLayout) findViewById(R.id.ln_operation_1);
        ln_operation_2 = (LinearLayout) findViewById(R.id.ln_operation_2);
        ln_operation_3 = (LinearLayout) findViewById(R.id.ln_operation_3);
        ln_operation_4 = (LinearLayout) findViewById(R.id.ln_operation_4);
        ln_operation_5 = (LinearLayout) findViewById(R.id.ln_operation_5);

        ln_operation_1.setOnClickListener(this);
        ln_operation_2.setOnClickListener(this);
        ln_operation_3.setOnClickListener(this);
        ln_operation_4.setOnClickListener(this);
        ln_operation_5.setOnClickListener(this);

        tv_undo.setOnClickListener(this);
        tv_hint.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.tv_hint:


                if (hint_second_arraylist.size() == 0){

                    fourSum(hint_first_array,targetNumber);

                }else {

                    fourSum(hint_second_intarray,targetNumber);

                    Log.e("@@hint_second_intarray",Arrays.toString(hint_second_intarray));

                }



                if (listHintFinal == null || startHint == false){

                    cc.showToast("Sorry, There is no hint");

                }else {


                    if (hintCountList.size() == 2) {


                        additionalHintDialog();

                    } else {

                        tv_hint.startAnimation(clickAnim);

                        hintCountList.add(hintFirstCount + 1);

                        for (int i = 0; i <listHintFinal.size() ; i++) {

                            popUpNumber = String.valueOf(listHintFinal.get(i));
                            Log.e("popUpHint",popUpNumber);

                            pop_up_number(popUpNumber);
                        }

                        Log.e("listHintFinalCopied",listHintFinal.toString());

                        listHintFinal = null;

                        startHint = false;

                    }


                }

                break;

            case R.id.tv_undo:


                equalNotDisplayRhs = false;
                tv_screen_final_equation.setText("");

                /*Pending.......................................*/

               if (undoAll == false){


                   if (num_1 == true) {


                       tv_1.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_1.setBackground(getResources().getDrawable(R.drawable.right_bottom_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_1 = false;

                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;



                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;

                       addUndoNumberToListOne(tv_1);


                       Log.e("RightClick",".......1");

                   }

                   if (num_2 == true) {



                       tv_2.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_2.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_2 = false;



                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();
                       undoAll = true;

                       addUndoNumberToListOne(tv_2);
                       Log.e("RightClick",".......2");

                   }

                   if (num_3 == true) {



                       tv_3.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_3.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);


                       num_3 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;

                       addUndoNumberToListOne(tv_3);

                       Log.e("RightClick",".......3");

                   }

                   if (num_4 == true) {



                       tv_4.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_4.setBackground(getResources().getDrawable(R.drawable.left_bottom));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_4 = false;



                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();
                       undoAll = true;

                       addUndoNumberToListOne(tv_4);
                       Log.e("RightClick",".......4");
                   }

                   if (num_5 == true) {



                       tv_5.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_5.setBackground(getResources().getDrawable(R.drawable.right_top_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_5 = false;



                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_5);
                       Log.e("RightClick",".......5");

                   }

                   if (num_6 == true) {



                       tv_6.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_6.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_6 = false;



                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_6);
                       Log.e("RightClick",".......6");

                   }
                   if (num_7 == true) {



                       tv_7.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_7.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_7 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_7);
                       Log.e("RightClick",".......7");

                   }

                   if (num_8 == true) {



                       tv_8.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_8.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_8 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_8);
                       Log.e("RightClick",".......8");

                   }


                   if (num_9 == true) {



                       tv_9.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_9.setBackground(getResources().getDrawable(R.drawable.right_top_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_9 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_9);
                       Log.e("RightClick",".......9");

                   }

                   if (num_10 == true) {



                       tv_10.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_10.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_10 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_10);
                       Log.e("RightClick",".......10");
                   }

                   if (num_11 == true) {



                       tv_11.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_11.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_11 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_11);
                       Log.e("RightClick",".......11");

                   }

                   if (num_12 == true) {



                       tv_12.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_12.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_12 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_12);
                       Log.e("RightClick",".......12");
                   }

                   if (num_13 == true) {


                       tv_13.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_13.setBackground(getResources().getDrawable(R.drawable.right_top_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_13 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_13);

                       Log.e("RightClick",".......13");
                   }


                   if (num_14 == true) {



                       tv_14.setTextColor(Color.parseColor("#000000"));
                       txtScreen.setText("");
                       ln_14.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_14 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_14);
                       Log.e("RightClick",".......14");

                   }

                   if (num_15 == true) {



                       tv_15.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_15.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_15 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_15);
                       Log.e("RightClick",".......15");
                   }

                   if (num_16 == true) {



                       tv_16.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_16.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_16 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_16);
                       Log.e("RightClick",".......16");
                   }


                   if (num_17 == true) {



                       tv_17.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_17.setBackground(getResources().getDrawable(R.drawable.right_top_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_17 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_17);
                       Log.e("RightClick",".......17");
                   }


                   if (num_18 == true) {


                       tv_18.setTextColor(Color.parseColor("#000000"));


                       ln_18.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_18 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_18);
                       Log.e("RightClick",".......18");
                   }

                   if (num_19 == true) {



                       tv_19.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_19.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_19 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_19);
                       Log.e("RightClick",".......19");
                   }


                   if (num_20 == true) {



                       tv_20.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_20.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_20 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_20);
                       Log.e("RightClick",".......20");
                   }

                   if (num_21 == true) {



                       tv_21.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_21.setBackground(getResources().getDrawable(R.drawable.right_top_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_21 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_21);
                       Log.e("RightClick",".......21");
                   }

                   if (num_22 == true) {


                       tv_22.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_22.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_22 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_22);
                       Log.e("RightClick",".......22");
                   }

                   if (num_23 == true) {



                       tv_23.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_23.setBackground(getResources().getDrawable(R.drawable.main_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_23 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_23);
                       Log.e("RightClick",".......23");
                   }


                   if (num_24 == true) {



                       tv_24.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_24.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_24 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_24);
                       Log.e("RightClick",".......24");
                   }

                   if (num_25 == true) {



                       tv_25.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_25.setBackground(getResources().getDrawable(R.drawable.right_top_border));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_25 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_25);
                       Log.e("RightClick",".......25");
                   }

                   if (num_26 == true) {



                       tv_26.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_26.setBackground(getResources().getDrawable(R.drawable.left_right_top));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_26 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_26);
                       Log.e("RightClick",".......26");
                   }

                   if (num_27 == true) {



                       tv_27.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_27.setBackground(getResources().getDrawable(R.drawable.left_right_top));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_27 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_27);
                       Log.e("RightClick",".......27");
                   }


                   if (num_28 == true) {



                       tv_28.setTextColor(Color.parseColor("#000000"));

                       txtScreen.setText("");
                       ln_28.setBackground(getResources().getDrawable(R.drawable.left_top));

                       tv_op_1.setImageResource(R.drawable.add_operation);
                       tv_op_2.setImageResource(R.drawable.minus_operation);
                       tv_op_3.setImageResource(R.drawable.multiply_operation);
                       tv_op_4.setImageResource(R.drawable.divide_operation);
                       tv_op_5.setImageResource(R.drawable.equal_operation);

                       num_28 = false;


                       tv_undo.startAnimation(clickAnim);
                       selectOneOperation = true;
                       selectOne = false;
                       hintnumber = false;
                       selectFinal = false;

                       lhsNumberList.clear();
                       rhsNumberList.clear();
                       finalNumberList.clear();

                       undoAll = true;
                       addUndoNumberToListOne(tv_28);
                       Log.e("RightClick",".......28");
                   }


               }else {

                   removeElementOneByOne();
                   Log.e("WrongClick",".......all");
                   makeFalseAllRearangeNumber();
                   //rearrangeNumberUndo();


               }

/*Pending...................................................................................*/



                break;


            case R.id.ln_1:

                if (selectOne == false && num_1 == false) {

                    if (stateError) {

                        txtScreen.setText(tv_1.getText());
                        tv_screen_final_equation.setText(tv_1.getText());


                        stateError = false;

                    } else {

                        txtScreen.append(tv_1.getText());
                        tv_screen_final_equation.setText(tv_1.getText());
                    }

                    addNumberToList();

                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_1 = true;


                    tv_1.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();

                    undoAll = false;

                    Log.e("ButtonClick", "false....make true");

                } else if (selectOne == true && num_1 == true) {

                    Toast.makeText(this, "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");

                }

                break;

            case R.id.ln_2:

                if (selectOne == false && num_2 == false) {

                    if (stateError) {

                        txtScreen.setText(tv_2.getText());
                        tv_screen_final_equation.setText(tv_2.getText());
                        stateError = false;

                    } else {

                        txtScreen.append(tv_2.getText());
                        tv_screen_final_equation.setText(tv_2.getText());

                    }




                    addNumberToList();

                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_2 = true;


                    tv_2.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();

                    Log.e("ButtonClick", "false.....make true");
                    undoAll = false;
                } else if (selectOne == true && num_2 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true.....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;


            case R.id.ln_3:

                if (selectOne == false && num_3 == false) {

                    if (stateError) {
                        txtScreen.setText(tv_3.getText());
                        tv_screen_final_equation.setText(tv_3.getText());


                        stateError = false;
                    } else {

                        txtScreen.append(tv_3.getText());
                        tv_screen_final_equation.append(tv_3.getText());
                    }

                    addNumberToList();
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_3 = true;


                    tv_3.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_3 == true) {


                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_4:

                if (selectOne == false && num_4 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_4.getText());
                        tv_screen_final_equation.setText(tv_4.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_4.getText());
                        tv_screen_final_equation.append(tv_4.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_4 = true;

                    tv_4.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_4 == true) {


                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_5:

                if (selectOne == false && num_5 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_5.getText());
                        tv_screen_final_equation.setText(tv_5.getText());


                        stateError = false;
                    } else {

                        txtScreen.append(tv_5.getText());
                        tv_screen_final_equation.append(tv_5.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_5 = true;


                    tv_5.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_5 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_6:

                if (selectOne == false && num_6 == false) {

                    if (stateError) {
                        txtScreen.setText(tv_6.getText());
                        tv_screen_final_equation.setText(tv_6.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_6.getText());
                        tv_screen_final_equation.append(tv_6.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_6 = true;


                    tv_6.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_6 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_7:

                if (selectOne == false && num_7 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_7.getText());
                        tv_screen_final_equation.setText(tv_7.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_7.getText());
                        tv_screen_final_equation.append(tv_7.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_7 = true;

                    tv_7.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_7 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_8:

                if (selectOne == false && num_8 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_8.getText());
                        tv_screen_final_equation.setText(tv_8.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_8.getText());
                        tv_screen_final_equation.append(tv_8.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_8 = true;

                    tv_8.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_8 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_9:

                if (selectOne == false && num_9 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_9.getText());
                        tv_screen_final_equation.setText(tv_9.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_9.getText());
                        tv_screen_final_equation.append(tv_9.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_9 = true;

                    tv_9.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_9 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_10:

                if (selectOne == false && num_10 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_10.getText());
                        tv_screen_final_equation.setText(tv_10.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_10.getText());
                        tv_screen_final_equation.append(tv_10.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_10 = true;

                    tv_10.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_10 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_11:

                if (selectOne == false && num_11 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_11.getText());
                        tv_screen_final_equation.setText(tv_11.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_11.getText());
                        tv_screen_final_equation.append(tv_11.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_11 = true;

                    tv_11.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_11 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_12:

                if (selectOne == false && num_12 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_12.getText());
                        tv_screen_final_equation.setText(tv_12.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_12.getText());
                        tv_screen_final_equation.append(tv_12.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_12 = true;

                    tv_12.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_12 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_13:

                if (selectOne == false && num_13 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_13.getText());
                        tv_screen_final_equation.setText(tv_13.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_13.getText());
                        tv_screen_final_equation.append(tv_13.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_13 = true;

                    tv_13.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_13 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_14:

                if (selectOne == false && num_14 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_14.getText());
                        tv_screen_final_equation.setText(tv_14.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_14.getText());
                        tv_screen_final_equation.append(tv_14.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_14 = true;

                    tv_14.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_14 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_15:

                if (selectOne == false && num_15 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_15.getText());
                        tv_screen_final_equation.setText(tv_15.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_15.getText());
                        tv_screen_final_equation.append(tv_15.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_15 = true;

                    tv_15.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_15 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_16:

                if (selectOne == false && num_16 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_16.getText());
                        tv_screen_final_equation.setText(tv_16.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_16.getText());
                        tv_screen_final_equation.append(tv_16.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_16 = true;

                    tv_16.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_16 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_17:

                if (selectOne == false && num_17 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_17.getText());
                        tv_screen_final_equation.setText(tv_17.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_17.getText());
                        tv_screen_final_equation.append(tv_17.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_17 = true;

                    tv_17.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_17 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_18:

                if (selectOne == false && num_18 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_18.getText());
                        tv_screen_final_equation.setText(tv_18.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_18.getText());
                        tv_screen_final_equation.append(tv_18.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_18 = true;

                    tv_18.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_18 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_19:

                if (selectOne == false && num_19 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_19.getText());
                        tv_screen_final_equation.setText(tv_19.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_19.getText());
                        tv_screen_final_equation.append(tv_19.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_19 = true;

                    tv_19.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_19 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_20:

                if (selectOne == false && num_20 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_20.getText());
                        tv_screen_final_equation.setText(tv_20.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_20.getText());
                        tv_screen_final_equation.append(tv_20.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_20 = true;

                    tv_20.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_20 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_21:

                if (selectOne == false && num_21 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_21.getText());
                        tv_screen_final_equation.setText(tv_21.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_21.getText());
                        tv_screen_final_equation.append(tv_21.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_21 = true;

                    tv_21.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_21 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_22:

                if (selectOne == false && num_22 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_22.getText());
                        tv_screen_final_equation.setText(tv_22.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_22.getText());
                        tv_screen_final_equation.append(tv_22.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_22 = true;

                    tv_22.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_22 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_23:

                if (selectOne == false && num_23 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_23.getText());
                        tv_screen_final_equation.setText(tv_23.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_23.getText());
                        tv_screen_final_equation.append(tv_23.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_23 = true;

                    tv_23.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_23 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_24:

                if (selectOne == false && num_24 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_24.getText());
                        tv_screen_final_equation.setText(tv_24.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_24.getText());
                        tv_screen_final_equation.append(tv_24.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_24 = true;

                    tv_24.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_24 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_25:

                if (selectOne == false && num_25 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_25.getText());
                        tv_screen_final_equation.setText(tv_25.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_25.getText());
                        tv_screen_final_equation.append(tv_25.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_25 = true;

                    tv_25.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_25 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_26:

                if (selectOne == false && num_26 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message
                        txtScreen.setText(tv_26.getText());
                        tv_screen_final_equation.setText(tv_26.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_26.getText());
                        tv_screen_final_equation.append(tv_26.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_26 = true;

                    tv_26.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_26 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            case R.id.ln_27:

                if (selectOne == false && num_27 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message

                        txtScreen.setText(tv_27.getText());
                        tv_screen_final_equation.setText(tv_27.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_27.getText());
                        tv_screen_final_equation.append(tv_27.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_27 = true;

                    tv_27.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_27 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;
            case R.id.ln_28:

                if (selectOne == false && num_28 == false) {

                    if (stateError) {
                        // If current state is Error, replace the error message

                        txtScreen.setText(tv_28.getText());
                        tv_screen_final_equation.setText(tv_28.getText());

                        stateError = false;
                    } else {

                        txtScreen.append(tv_28.getText());
                        tv_screen_final_equation.append(tv_28.getText());
                    }
                    addNumberToList();
                    // Set the flag
                    lastNumeric = true;
                    selectOne = true;
                    selectOneOperation = false;
                    num_28 = true;

                    tv_28.setTextColor(Color.parseColor("#ffffff"));
                    changeBackNumber(view);
                    changeBackOperation();
                    Log.e("ButtonClick", "false....make true");
                    undoAll = false;
                } else if (selectOne == true && num_28 == true) {

                    Toast.makeText(getApplicationContext(), "Please select operation", Toast.LENGTH_LONG);
                    Log.e("ButtonClick", "true....make false");

                } else {

                    Log.e("ButtonClick", "elseMain");
                }

                break;

            /*Operation click listner*/

            case R.id.tv_op_1:

                if (selectOneOperation == false) {


                    if (lastNumeric && !stateError) {

                        String add = "+";

                        txtScreen.append(add);
                        tv_screen_final_equation.append(add);


                        lastNumeric = false;

                        tv_op_1.setImageResource(R.drawable.add_clicked);

                        //changeBackOperation(view);


                        selectOne = false;
                        selectOneOperation = true;


                    } else {


                    }

                    Log.e("ButtonClickOperation", "false....make true");

                } else if (selectOneOperation == true) {

                    Log.e("ButtonClickOperation", "true....make false");

                } else {

                    Log.e("ButtonClickOperation", "elseMain");
                }


                break;

            case R.id.tv_op_2:

                if (selectOneOperation == false) {


                    if (lastNumeric && !stateError) {


                        String add = "-";


                        txtScreen.append(add);
                        tv_screen_final_equation.append(add);


//                        txtScreen.append(tv_op_2.getText());

                        lastNumeric = false;

                        tv_op_2.setImageResource(R.drawable.minus_clicked);

                        // changeBackOperation(view);


                        selectOne = false;
                        selectOneOperation = true;


                    } else {


                    }

                    Log.e("ButtonClickOperation", "false....make true");

                } else if (selectOneOperation == true) {

                    Log.e("ButtonClickOperation", "true....make false");

                } else {

                    Log.e("ButtonClickOperation", "elseMain");
                }


                break;

            case R.id.tv_op_3:

                if (selectOneOperation == false) {


                    if (lastNumeric && !stateError) {


                        String add = "*";

                        txtScreen.append(add);
                        tv_screen_final_equation.append(add);

//                        txtScreen.append(tv_op_3.getText());

                        lastNumeric = false;

                        tv_op_3.setImageResource(R.drawable.multiply_clicked);

                        //changeBackOperation(view);


                        selectOne = false;
                        selectOneOperation = true;


                    } else {


                    }

                    Log.e("ButtonClickOperation", "false....make true");

                } else if (selectOneOperation == true) {

                    Log.e("ButtonClickOperation", "true....make false");

                } else {

                    Log.e("ButtonClickOperation", "elseMain");
                }


                break;

            case R.id.tv_op_4:

                if (selectOneOperation == false) {


                    if (lastNumeric && !stateError) {


                        String add = "/";

                        txtScreen.append(add);
                        tv_screen_final_equation.append(add);

//                        txtScreen.append(tv_op_4.getText());

                        lastNumeric = false;

                        tv_op_4.setImageResource(R.drawable.divide_clicked);

                        //changeBackOperation(view);


                        selectOne = false;
                        selectOneOperation = true;


                    } else {


                    }

                    Log.e("ButtonClickOperation", "false....make true");

                } else if (selectOneOperation == true) {

                    Log.e("ButtonClickOperation", "true....make false");

                } else {

                    Log.e("ButtonClickOperation", "elseMain");
                }


                break;

            case R.id.tv_op_5:


                if (selectOneOperation == false) {



                    if (lastNumeric && !stateError) {

                        if(equalNotDisplayRhs == false){

                            String add = "=";

                            tv_screen_final_equation.append(add);

                            equalNotDisplayRhs = true;

                        }else {

                            equalNotDisplayRhs = false;

                        }

                        String txt1 = txtScreen.getText().toString();

                        if (txt1 == null) {

                            Log.e("Expression empty", "........");

                        } else {

                            Expression expression = new ExpressionBuilder(txt1).build();

                            double result = expression.evaluate();

                            /**/

                            if (divideError == true){

                                divideErrorDialog(result);
                            }
                            else
                            {
                                onEqual();
                                lastNumeric = false;

                                tv_op_5.setImageResource(R.drawable.equal_clicked);

                                //changeBackOperation(view);

                                selectOne = false;
                                selectOneOperation = true;
                                Log.e("222222222", "if....make true");

                                System.out.println("decimal value is not there");
                            }

                            /**/

                        }


                    } else {

                        Log.e("22222222222", "false....make true");

                    }

                    Log.e("ButtonClickOperation", "false....make true");

                } else if (selectOneOperation == true) {

                    Log.e("ButtonClickOperation", "true....make false");

                } else {

                    Log.e("ButtonClickOperation", "elseMain");
                }


                break;

            case R.id.ln_start_game:

                dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.level_select_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;
                dialog.getWindow().setLayout((6 * width) / 7, ActionBar.LayoutParams.WRAP_CONTENT);


                TextView tv_dialog_easy = (TextView) dialog.findViewById(R.id.tv_dialog_easy);
                TextView tv_dialog_hard = (TextView) dialog.findViewById(R.id.tv_dialog_hard);
                ImageView close_dialog = (ImageView) dialog.findViewById(R.id.close_dialog);

                final Animation myAnim;
                myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);

                tv_dialog_easy.startAnimation(myAnim);
                tv_dialog_hard.startAnimation(myAnim);

                tv_dialog_easy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                        cc.savePrefBoolean("Easy", true);
                        cc.savePrefBoolean("Hard", true);
                        startActivity(refresh);
                        finish();

                        dialog.dismiss();
                    }
                });

                tv_dialog_hard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                        cc.savePrefBoolean("Hard", false);
                        cc.savePrefBoolean("Easy", false);
                        startActivity(refresh);
                        finish();

                        dialog.dismiss();
                    }
                });

                close_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dialog.dismiss();
                    }
                });

               // pauseTimer();



                dialog.show();

                break;
        }


    }

    private void pop_up_number(String popUpNumber) {

        Log.e("pop_up_number",popUpNumber);

        if (popUpNumber.equals(tv_1.getText().toString().trim()) && tv_1.getVisibility() == View.VISIBLE && num_1 == false){


            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_1.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_1.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));

                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_1.setTextColor(Color.parseColor("#000000"));
                    ln_1.setBackground(getResources().getDrawable(R.drawable.right_bottom_border));

                }
            }, 4000);



        }else if (popUpNumber.equals(tv_2.getText().toString().trim()) && tv_2.getVisibility() == View.VISIBLE && num_2 == false){


            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_2.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_2.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));

                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_2.setTextColor(Color.parseColor("#000000"));
                    ln_2.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));

                }
            }, 4000);




        }else if (popUpNumber.equals(tv_3.getText().toString().trim()) && tv_3.getVisibility() == View.VISIBLE && num_3 == false){


            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_3.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_3.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));

                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_3.setTextColor(Color.parseColor("#000000"));
                    ln_3.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));

                }
            }, 4000);




        }else  if (popUpNumber.equals(tv_4.getText().toString().trim()) && tv_4.getVisibility() == View.VISIBLE && num_4 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_4.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_4.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));

                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_4.setTextColor(Color.parseColor("#000000"));
                    ln_4.setBackground(getResources().getDrawable(R.drawable.left_bottom));

                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_5.getText().toString().trim()) && tv_5.getVisibility() == View.VISIBLE && num_5 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_5.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_5.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));

                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_5.setTextColor(Color.parseColor("#000000"));
                    ln_5.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_6.getText().toString().trim()) && tv_6.getVisibility() == View.VISIBLE && num_6 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_6.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_6.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));

                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_6.setTextColor(Color.parseColor("#000000"));
                    ln_6.setBackground(getResources().getDrawable(R.drawable.main_border));

                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_7.getText().toString().trim()) && tv_7.getVisibility() == View.VISIBLE && num_7 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_7.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_7.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));

                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_7.setTextColor(Color.parseColor("#000000"));
                    ln_7.setBackground(getResources().getDrawable(R.drawable.main_border));

                }
            }, 4000);



        }else   if (popUpNumber.equals(tv_8.getText().toString().trim()) && tv_8.getVisibility() == View.VISIBLE && num_8 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_8.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_8.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_8.setTextColor(Color.parseColor("#000000"));
                    ln_8.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_9.getText().toString().trim()) && tv_9.getVisibility() == View.VISIBLE && num_9 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_9.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_9.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_9.setTextColor(Color.parseColor("#000000"));
                    ln_9.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_10.getText().toString().trim()) && tv_10.getVisibility() == View.VISIBLE && num_10 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_10.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_10.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_10.setTextColor(Color.parseColor("#000000"));
                    ln_10.setBackground(getResources().getDrawable(R.drawable.main_border));
                }
            }, 4000);

        }else  if (popUpNumber.equals(tv_11.getText().toString().trim()) && tv_11.getVisibility() == View.VISIBLE && num_11 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_11.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_11.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_11.setTextColor(Color.parseColor("#000000"));
                    ln_11.setBackground(getResources().getDrawable(R.drawable.main_border));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_12.getText().toString().trim()) && tv_12.getVisibility() == View.VISIBLE && num_12 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_12.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_12.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_12.setTextColor(Color.parseColor("#000000"));
                    ln_12.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_13.getText().toString().trim()) && tv_13.getVisibility() == View.VISIBLE && num_13 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_13.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_13.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_13.setTextColor(Color.parseColor("#000000"));
                    ln_13.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_14.getText().toString().trim()) && tv_14.getVisibility() == View.VISIBLE && num_14 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_14.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_14.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_14.setTextColor(Color.parseColor("#000000"));
                    ln_14.setBackground(getResources().getDrawable(R.drawable.main_border));
                }
            }, 4000);


        }else  if (popUpNumber.equals(tv_15.getText().toString().trim()) && tv_15.getVisibility() == View.VISIBLE && num_15 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_15.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_15.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_15.setTextColor(Color.parseColor("#000000"));
                    ln_15.setBackground(getResources().getDrawable(R.drawable.main_border));
                }
            }, 4000);



        }else if (popUpNumber.equals(tv_16.getText().toString().trim()) && tv_16.getVisibility() == View.VISIBLE && num_16 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_16.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_16.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_16.setTextColor(Color.parseColor("#000000"));
                    ln_16.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_17.getText().toString().trim()) && tv_17.getVisibility() == View.VISIBLE && num_17 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_17.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_17.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_17.setTextColor(Color.parseColor("#000000"));
                    ln_17.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));
                }
            }, 4000);


        }else if (popUpNumber.equals(tv_18.getText().toString().trim()) && tv_18.getVisibility() == View.VISIBLE && num_18 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_18.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_18.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_18.setTextColor(Color.parseColor("#000000"));
                    ln_18.setBackground(getResources().getDrawable(R.drawable.main_border));
                }
            }, 4000);



        }else if (popUpNumber.equals(tv_19.getText().toString().trim()) && tv_19.getVisibility() == View.VISIBLE && num_19 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_19.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_19.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_19.setTextColor(Color.parseColor("#000000"));
                    ln_19.setBackground(getResources().getDrawable(R.drawable.main_border));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_20.getText().toString().trim()) && tv_20.getVisibility() == View.VISIBLE && num_20 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_20.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_20.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_20.setTextColor(Color.parseColor("#000000"));
                    ln_20.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
                }
            }, 4000);



        }else   if (popUpNumber.equals(tv_21.getText().toString().trim()) && tv_21.getVisibility() == View.VISIBLE && num_21 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_21.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_21.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_21.setTextColor(Color.parseColor("#000000"));
                    ln_21.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));
                }
            }, 4000);



        }else   if (popUpNumber.equals(tv_22.getText().toString().trim()) && tv_22.getVisibility() == View.VISIBLE && num_22 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_22.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_22.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_22.setTextColor(Color.parseColor("#000000"));
                    ln_22.setBackground(getResources().getDrawable(R.drawable.main_border));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_23.getText().toString().trim()) && tv_23.getVisibility() == View.VISIBLE && num_23 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_23.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_23.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_23.setTextColor(Color.parseColor("#000000"));
                    ln_23.setBackground(getResources().getDrawable(R.drawable.main_border));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_24.getText().toString().trim()) && tv_24.getVisibility() == View.VISIBLE && num_24 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_24.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_24.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_24.setTextColor(Color.parseColor("#000000"));
                    ln_24.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_25.getText().toString().trim()) && tv_25.getVisibility() == View.VISIBLE && num_25 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_25.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_25.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_25.setTextColor(Color.parseColor("#000000"));
                    ln_25.setBackground(getResources().getDrawable(R.drawable.right_top_border));
                }
            }, 4000);



        }else    if (popUpNumber.equals(tv_26.getText().toString().trim()) && tv_26.getVisibility() == View.VISIBLE && num_26 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_26.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_26.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_26.setTextColor(Color.parseColor("#000000"));
                    ln_26.setBackground(getResources().getDrawable(R.drawable.left_right_top));
                }
            }, 4000);



        }else if (popUpNumber.equals(tv_27.getText().toString().trim()) && tv_27.getVisibility() == View.VISIBLE && num_27 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_27.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_27.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_27.setTextColor(Color.parseColor("#000000"));
                    ln_27.setBackground(getResources().getDrawable(R.drawable.left_right_top));
                }
            }, 4000);



        }else  if (popUpNumber.equals(tv_28.getText().toString().trim()) && tv_28.getVisibility() == View.VISIBLE && num_28 == false){

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_28.setTextColor(Color.parseColor("#FFFFFF"));
                    ln_28.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));
                }
            }, 2000);

            hintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    tv_28.setTextColor(Color.parseColor("#000000"));
                    ln_28.setBackground(getResources().getDrawable(R.drawable.left_top));
                }
            }, 4000);



        }else {

            Log.e("NOTHINTPOPUP","//////////");
        }
    }

    private void removeElementOneByOne() {



            if (undoCount >= undoNumberSecondArrange.size() ){

                Log.e("UndoData","Bound");

            }else {


                UndoData ann = undoNumberSecondArrange.get(undoCount);

                Log.e("UndoDataCounter", String.valueOf(undoCount));
                undoCount++;


                rearrangeNumberUndo(ann.getNumber_undo());


                undoNumberTwo.remove(undoNumberTwo.size() - 1);

                Log.i("RemoveListData1", String.valueOf(undoNumberTwo.size()));
                Log.i("RemoveListData2", String.valueOf(undoNumberSecondArrange.size()));

                Log.e("UndoData","Not....Bound");
            }

    }

    private void rearrangeNumberUndo(String fm) {

//hello

        if (fm.equals(tv_1.getText().toString().trim()) && tv_1.getVisibility() == View.INVISIBLE){

            tv_1.setVisibility(View.VISIBLE);
            tv_1.setTextColor(Color.parseColor("#000000"));
            ln_1.setClickable(true);

            rearrangeNumber_s1 = true;

            addHintNumberToArraylist(tv_1);


        }else if (fm.equals(tv_2.getText().toString().trim()) && tv_2.getVisibility() == View.INVISIBLE){

            tv_2.setVisibility(View.VISIBLE);
            tv_2.setTextColor(Color.parseColor("#000000"));
            ln_2.setClickable(true);

            rearrangeNumber_s2 = true;

            addHintNumberToArraylist(tv_2);

        }else if (fm.equals(tv_3.getText().toString().trim()) && tv_3.getVisibility() == View.INVISIBLE){

            tv_3.setVisibility(View.VISIBLE);
            tv_3.setTextColor(Color.parseColor("#000000"));
            ln_3.setClickable(true);

            rearrangeNumber_s3 = true;

            addHintNumberToArraylist(tv_3);

        }else  if (fm.equals(tv_4.getText().toString().trim()) && tv_4.getVisibility() == View.INVISIBLE){

            tv_4.setVisibility(View.VISIBLE);
            tv_4.setTextColor(Color.parseColor("#000000"));
            ln_4.setClickable(true);

            rearrangeNumber_s4 = true;

            addHintNumberToArraylist(tv_4);

        }else  if (fm.equals(tv_5.getText().toString().trim()) && tv_5.getVisibility() == View.INVISIBLE){

            tv_5.setVisibility(View.VISIBLE);
            tv_5.setTextColor(Color.parseColor("#000000"));
            ln_5.setClickable(true);

            rearrangeNumber_s5 = true;

            addHintNumberToArraylist(tv_5);

        }else  if (fm.equals(tv_6.getText().toString().trim()) && tv_6.getVisibility() == View.INVISIBLE){

            tv_6.setVisibility(View.VISIBLE);
            tv_6.setTextColor(Color.parseColor("#000000"));
            ln_6.setClickable(true);

            rearrangeNumber_s6 = true;

            addHintNumberToArraylist(tv_6);

        }else  if (fm.equals(tv_7.getText().toString().trim()) && tv_7.getVisibility() == View.INVISIBLE){

            tv_7.setVisibility(View.VISIBLE);
            tv_7.setTextColor(Color.parseColor("#000000"));
            ln_7.setClickable(true);

            rearrangeNumber_s7 = true;

            addHintNumberToArraylist(tv_7);

        }else   if (fm.equals(tv_8.getText().toString().trim()) && tv_8.getVisibility() == View.INVISIBLE){

            tv_8.setVisibility(View.VISIBLE);
            tv_8.setTextColor(Color.parseColor("#000000"));
            ln_8.setClickable(true);

            rearrangeNumber_s8 = true;

            addHintNumberToArraylist(tv_8);

        }else  if (fm.equals(tv_9.getText().toString().trim()) && tv_9.getVisibility() == View.INVISIBLE){

            tv_9.setVisibility(View.VISIBLE);
            tv_9.setTextColor(Color.parseColor("#000000"));
            ln_9.setClickable(true);

            rearrangeNumber_s9 = true;

            addHintNumberToArraylist(tv_9);

        }else  if (fm.equals(tv_10.getText().toString().trim()) && tv_10.getVisibility() == View.INVISIBLE){

            tv_10.setVisibility(View.VISIBLE);
            tv_10.setTextColor(Color.parseColor("#000000"));
            ln_10.setClickable(true);

            rearrangeNumber_s10 = true;

            addHintNumberToArraylist(tv_10);

        }else  if (fm.equals(tv_11.getText().toString().trim()) && tv_11.getVisibility() == View.INVISIBLE){

            tv_11.setVisibility(View.VISIBLE);
            tv_11.setTextColor(Color.parseColor("#000000"));
            ln_11.setClickable(true);

            rearrangeNumber_s11 = true;

            addHintNumberToArraylist(tv_11);

        }else  if (fm.equals(tv_12.getText().toString().trim()) && tv_12.getVisibility() == View.INVISIBLE){

            tv_12.setVisibility(View.VISIBLE);
            tv_12.setTextColor(Color.parseColor("#000000"));
            ln_12.setClickable(true);

            rearrangeNumber_s12 = true;

            addHintNumberToArraylist(tv_12);

        }else  if (fm.equals(tv_13.getText().toString().trim()) && tv_13.getVisibility() == View.INVISIBLE){

            tv_13.setVisibility(View.VISIBLE);
            tv_13.setTextColor(Color.parseColor("#000000"));
            ln_13.setClickable(true);

            rearrangeNumber_s13 = true;

            addHintNumberToArraylist(tv_13);

        }else  if (fm.equals(tv_14.getText().toString().trim()) && tv_14.getVisibility() == View.INVISIBLE){

            tv_14.setVisibility(View.VISIBLE);
            tv_14.setTextColor(Color.parseColor("#000000"));
            ln_14.setClickable(true);

            rearrangeNumber_s14 = true;

            addHintNumberToArraylist(tv_14);

        }else  if (fm.equals(tv_15.getText().toString().trim()) && tv_15.getVisibility() == View.INVISIBLE){

            tv_15.setVisibility(View.VISIBLE);
            tv_15.setTextColor(Color.parseColor("#000000"));
            ln_15.setClickable(true);

            rearrangeNumber_s15 = true;

            addHintNumberToArraylist(tv_15);

        }else if (fm.equals(tv_16.getText().toString().trim()) && tv_16.getVisibility() == View.INVISIBLE){

            tv_16.setVisibility(View.VISIBLE);
            tv_16.setTextColor(Color.parseColor("#000000"));
            ln_16.setClickable(true);

            rearrangeNumber_s16 = true;

            addHintNumberToArraylist(tv_16);

        }else  if (fm.equals(tv_17.getText().toString().trim()) && tv_17.getVisibility() == View.INVISIBLE){

            tv_17.setVisibility(View.VISIBLE);
            tv_17.setTextColor(Color.parseColor("#000000"));
            ln_17.setClickable(true);

            rearrangeNumber_s17 = true;

            addHintNumberToArraylist(tv_17);

        }else if (fm.equals(tv_18.getText().toString().trim()) && tv_18.getVisibility() == View.INVISIBLE){

            tv_18.setVisibility(View.VISIBLE);
            tv_18.setTextColor(Color.parseColor("#000000"));
            ln_18.setClickable(true);

            rearrangeNumber_s18 = true;

            addHintNumberToArraylist(tv_18);

        }else if (fm.equals(tv_19.getText().toString().trim()) && tv_19.getVisibility() == View.INVISIBLE){

            tv_19.setVisibility(View.VISIBLE);
            tv_19.setTextColor(Color.parseColor("#000000"));
            ln_19.setClickable(true);

            rearrangeNumber_s19 = true;

            addHintNumberToArraylist(tv_19);

        }else  if (fm.equals(tv_20.getText().toString().trim()) && tv_20.getVisibility() == View.INVISIBLE){

            tv_20.setVisibility(View.VISIBLE);
            tv_20.setTextColor(Color.parseColor("#000000"));
            ln_20.setClickable(true);

            rearrangeNumber_s20 = true;

            addHintNumberToArraylist(tv_20);

        }else   if (fm.equals(tv_21.getText().toString().trim()) && tv_21.getVisibility() == View.INVISIBLE){

            tv_21.setVisibility(View.VISIBLE);
            tv_21.setTextColor(Color.parseColor("#000000"));
            ln_21.setClickable(true);

            rearrangeNumber_s21 = true;

            addHintNumberToArraylist(tv_21);

        }else   if (fm.equals(tv_22.getText().toString().trim()) && tv_22.getVisibility() == View.INVISIBLE){

            tv_22.setVisibility(View.VISIBLE);
            tv_22.setTextColor(Color.parseColor("#000000"));
            ln_22.setClickable(true);

            rearrangeNumber_s22 = true;

            addHintNumberToArraylist(tv_22);

        }else  if (fm.equals(tv_23.getText().toString().trim()) && tv_23.getVisibility() == View.INVISIBLE){

            tv_23.setVisibility(View.VISIBLE);
            tv_23.setTextColor(Color.parseColor("#000000"));
            ln_23.setClickable(true);

            rearrangeNumber_s23 = true;

            addHintNumberToArraylist(tv_23);

        }else  if (fm.equals(tv_24.getText().toString().trim()) && tv_24.getVisibility() == View.INVISIBLE){

            tv_24.setVisibility(View.VISIBLE);
            tv_24.setTextColor(Color.parseColor("#000000"));
            ln_24.setClickable(true);

            rearrangeNumber_s24 = true;

            addHintNumberToArraylist(tv_24);

        }else  if (fm.equals(tv_25.getText().toString().trim()) && tv_25.getVisibility() == View.INVISIBLE){

            tv_25.setVisibility(View.VISIBLE);
            tv_25.setTextColor(Color.parseColor("#000000"));
            ln_25.setClickable(true);


            rearrangeNumber_s25 = true;

            addHintNumberToArraylist(tv_25);

        }else    if (fm.equals(tv_26.getText().toString().trim()) && tv_26.getVisibility() == View.INVISIBLE){

            tv_26.setVisibility(View.VISIBLE);
            tv_26.setTextColor(Color.parseColor("#000000"));
            ln_26.setClickable(true);

            rearrangeNumber_s26 = true;

            addHintNumberToArraylist(tv_26);

        }else if (fm.equals(tv_27.getText().toString().trim()) && tv_27.getVisibility() == View.INVISIBLE){

            tv_27.setVisibility(View.VISIBLE);
            tv_27.setTextColor(Color.parseColor("#000000"));
            ln_27.setClickable(true);

            rearrangeNumber_s27 = true;

            addHintNumberToArraylist(tv_27);

        }else  if (fm.equals(tv_28.getText().toString().trim()) && tv_28.getVisibility() == View.INVISIBLE){

            tv_28.setVisibility(View.VISIBLE);
            tv_28.setTextColor(Color.parseColor("#000000"));
            ln_28.setClickable(true);

            rearrangeNumber_s28 = true;

            addHintNumberToArraylist(tv_28);

        }else {

            Log.e("NOTUNDOO","//////////");
        }

    }



    public void makeFalseAllRearangeNumber(){

        if (rearrangeNumber_s1 == true){

            rearrangeNumber_s1 = false;
        }

        if (rearrangeNumber_s2 == true){

            rearrangeNumber_s2 = false;
        }

        if (rearrangeNumber_s3 == true){

            rearrangeNumber_s3 = false;
        }

        if (rearrangeNumber_s4 == true){

            rearrangeNumber_s4 = false;
        }

        if (rearrangeNumber_s5 == true){

            rearrangeNumber_s5 = false;
        }

        if (rearrangeNumber_s6 == true){

            rearrangeNumber_s6 = false;
        }

        if (rearrangeNumber_s7 == true){

            rearrangeNumber_s7 = false;
        }

        if (rearrangeNumber_s8 == true){

            rearrangeNumber_s8 = false;
        }

        if (rearrangeNumber_s9 == true){

            rearrangeNumber_s9 = false;
        }

        if (rearrangeNumber_s10 == true){

            rearrangeNumber_s10 = false;
        }

        if (rearrangeNumber_s11 == true){

            rearrangeNumber_s11 = false;
        }

        if (rearrangeNumber_s12 == true){

            rearrangeNumber_s12 = false;
        }

        if (rearrangeNumber_s13 == true){

            rearrangeNumber_s13 = false;
        }

        if (rearrangeNumber_s14 == true){

            rearrangeNumber_s14 = false;
        }

        if (rearrangeNumber_s15 == true){

            rearrangeNumber_s15 = false;
        }

        if (rearrangeNumber_s16 == true){

            rearrangeNumber_s16 = false;
        }

        if (rearrangeNumber_s17 == true){

            rearrangeNumber_s17 = false;
        }

        if (rearrangeNumber_s18 == true){

            rearrangeNumber_s18 = false;
        }

        if (rearrangeNumber_s19 == true){

            rearrangeNumber_s19 = false;
        }

        if (rearrangeNumber_s20 == true){

            rearrangeNumber_s20 = false;
        }

        if (rearrangeNumber_s21 == true){

            rearrangeNumber_s21 = false;
        }

        if (rearrangeNumber_s22 == true){

            rearrangeNumber_s22 = false;
        }

        if (rearrangeNumber_s23 == true){

            rearrangeNumber_s23 = false;
        }

        if (rearrangeNumber_s24 == true){

            rearrangeNumber_s24 = false;
        }

        if (rearrangeNumber_s25 == true){

            rearrangeNumber_s25 = false;
        }

        if (rearrangeNumber_s26 == true){

            rearrangeNumber_s26 = false;
        }

        if (rearrangeNumber_s27 == true){

            rearrangeNumber_s27 = false;
        }

        if (rearrangeNumber_s28 == true){

            rearrangeNumber_s28 = false;
        }


    }

    private void divideErrorDialog(double result) {

        if ((result-(int)result)!=0){

            System.out.println("decimal value is there");

            removeSelectedForDivideError();
        }
        else
        {
           /* onEqual();
            lastNumeric = false;

            tv_op_5.setImageResource(R.drawable.equal_clicked);

            //changeBackOperation(view);

            selectOne = false;
            selectOneOperation = true;
            Log.e("222222222", "if....make true");

            System.out.println("decimal value is not there");*/
        }
    }

    private void additionalHintDialog() {

        dialog2 = new Dialog(MainActivity.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setCancelable(false);
        dialog2.setContentView(R.layout.additionalhint_dialog);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog2.getWindow().setLayout((6 * width) / 7, ActionBar.LayoutParams.WRAP_CONTENT);


        ImageView close_dialog = (ImageView)dialog2.findViewById(R.id.iv_close);
        TextView tv_hint_ok = (TextView) dialog2.findViewById(R.id.tv_hint_ok);
        TextView tv_addi_message = (TextView) dialog2.findViewById(R.id.tv_addi_message);

        tv_addi_message.setText("Your free hint is finish,Please watch ads for more hint");

        tv_hint_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog2.dismiss();

                hintError = false;

            }
        });

        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog2.dismiss();

                hintError = false;
            }
        });



        dialog2.show();

    }

    private void hintpop() {

        final Dialog dialog1 = new Dialog(MainActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(false);
        dialog1.setContentView(R.layout.additionalhint_dialog);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog1.getWindow().setLayout((6 * width) / 7, ActionBar.LayoutParams.WRAP_CONTENT);

        TextView tv_addi_message = (TextView) dialog1.findViewById(R.id.tv_addi_message);
        TextView hint_congra = (TextView) dialog1.findViewById(R.id.hint_congra);
        TextView tv_hint_ok = (TextView) dialog1.findViewById(R.id.tv_hint_ok);
        ImageView iv_close = (ImageView) dialog1.findViewById(R.id.iv_close);

        if (hintCountList.size() == 1){

            hint_congra.setText("Remaining Hint is 1");

        }else if (hintCountList.size() == 2){

            hint_congra.setText("Remaining Hint is 0");

        }else {

            hint_congra.setText("");
        }

        String hint = cc.loadPrefString("LHS");


        tv_addi_message.setText("Your Hint is :" + " " + hint + " " + "");


        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //totalPointDisplayToTextview();
                startStop();

                hintError = false;

                dialog1.dismiss();
            }
        });

        tv_hint_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //totalPointDisplayToTextview();
                startStop();

                hintError = false;

                dialog1.dismiss();
            }
        });

        pauseTimer();

        dialog1.show();


    }

    public void addUndoNumberToListOne(TextView tv){

        undoNumberOne.add(Integer.valueOf(tv.getText().toString()));

        Log.e("addUndoNumberToListOne", String.valueOf(undoNumberOne.size()));
        System.out.println(undoNumberOne);

    }

    public void addUndoNumberToListTwo(String tv){

        undoNumberTwo.add(Integer.valueOf(tv));

        Log.e("addUndoNumberToListTwo", String.valueOf(undoNumberTwo.size()));
        System.out.println(undoNumberTwo+"..........");

        setUndoListDemo();

    }

    private void setUndoListDemo() {


        for (int i = 0; i < undoNumberTwo.size(); i++) {

            Log.e("Data_"+i,undoNumberFirstArrange.get(undoNumberTwo.get(i))+"");

            fm = String.valueOf(undoNumberFirstArrange.get(undoNumberTwo.get(i)));

            buffer = new StringBuffer(fm);
            buffer.reverse();

        }

        UndoData un = new UndoData();

        un.setNumber_undo(String.valueOf(fm));

        undoNumberSecondArrange.add(un);
        // undoNumberTwo.remove(undoNumberTwo.size() - 1);
        // undoNumberTwo.remove(undoNumberTwo.size() - 1);

        System.out.println(undoNumberSecondArrange+"range");
    }

    public void arrangeUndoNumberListFirst(TextView tv){

        undoNumberFirstArrange.add(Integer.valueOf(tv.getText().toString()));

        Log.e("undoNumberFirstArrange", String.valueOf(undoNumberFirstArrange.size()));

        hint_first_array = new int[undoNumberFirstArrange.size()];


        for (int i=0; i < hint_first_array.length; i++)
        {
            hint_first_array[i] = undoNumberFirstArrange.get(i).intValue();
        }

    }

    public List<List<Integer>> fourSum(int[] num, int target) {

        List<List<Integer>> listSet = new ArrayList<List<Integer>>();

        Arrays.sort(num);

        if (target == 1 || target == 2 || target == 3 || target == 4 || target == 5 || target == 6 || target == 7 || target == 8
                || target == 9 || target == 10){

            cc.showToast("Hint not display");

        }else {

            sumTwoCombination(num,target,listSet);
        }


        return listSet;
    }

    /*Combination method start*/

    private void sumTwoCombination(int[] num, int target, List<List<Integer>> listSet) {


        for (int i = 0; i < num.length - 3; i = incrementSumTwo(num, i)) {

            if (stopLoop == true){

                stopLoop = false;

                break;
            }

            for (int j = i + 1; j < num.length - 2; j = incrementSumTwo(num, j)) {
                int a = num[i], b = num[j], lo = j + 1, hi = num.length - 1;

                if (stopLoop == true){


                    break;
                }

                while (lo < hi) {
                    int c = num[lo], d = num[hi];
                    int sum = a + b;


                    //Sum
                    if (sum == target) {
                        listHintFinal = Arrays.asList(a, b);
                        listSet.add(listHintFinal);
                        lo = incrementSumTwo(num, lo);
                        hi = decrementSumTwo(num, hi);

                        Log.e("@@FINALSUMTWO",listHintFinal.toString());

                        stopLoop = true;


                        break;

                    } else if (sum < target) {
                        lo = incrementSumTwo(num, lo);



                        Log.e("StopSumTwo","........1");

                    } else {
                        hi = decrementSumTwo(num, hi);

                        mulTwoCombination(num,target,listSet);
                        stopLoop = true;
                        Log.e("StopSumTwo","........2");

                    }


                }
            }
        }


    }

    private void mulTwoCombination(int[] num, int target, List<List<Integer>> listSet) {


        for (int i = 0; i < num.length - 3; i = incrementMulTwo(num, i)) {

            if (stopLoop == true){

                stopLoop = false;

                break;
            }

            for (int j = i + 1; j < num.length - 2; j = incrementMulTwo(num, j)) {
                int a = num[i], b = num[j], lo = j + 1, hi = num.length - 1;

                if (stopLoop == true){


                    break;
                }

                while (lo < hi) {
                    int c = num[lo], d = num[hi];
                    int sum = a * b;


                    //Sum
                    if (sum == target) {
                        listHintFinal = Arrays.asList(a, b);
                        listSet.add(listHintFinal);
                        lo = incrementMulTwo(num, lo);
                        hi = decrementMulTwo(num, hi);

                        Log.e("@@FINALMULTWO",listHintFinal.toString());

                        stopLoop = true;


                        break;

                    } else if (sum < target) {
                        lo = incrementMulTwo(num, lo);



                        Log.e("StopMulTwo","........1");

                    } else {
                        hi = decrementMulTwo(num, hi);

                        sumThreeCombination(num,target,listSet);
                        stopLoop = true;
                        Log.e("StopMulTwo","........2");

                    }


                }
            }
        }


    }

    private void sumThreeCombination(int[] num, int target, List<List<Integer>> listSet) {


        for (int i = 0; i < num.length - 3; i = incrementSumThree(num, i)) {

            if (stopLoop == true){

                stopLoop = false;

                break;
            }

            for (int j = i + 1; j < num.length - 2; j = incrementSumThree(num, j)) {
                int a = num[i], b = num[j], lo = j + 1, hi = num.length - 1;

                if (stopLoop == true){


                    break;
                }

                while (lo < hi) {
                    int c = num[lo], d = num[hi];
                    int sum = a + b + c;


                    //Sum
                    if (sum == target) {
                        listHintFinal = Arrays.asList(a, b, c);
                        listSet.add(listHintFinal);
                        lo = incrementSumThree(num, lo);
                        hi = decrementSumThree(num, hi);

                        Log.e("@@FINALSUMTHREE",listHintFinal.toString());

                        stopLoop = true;


                        break;

                    } else if (sum < target) {
                        lo = incrementSumThree(num, lo);



                        Log.e("StopSumThree","........1");

                    } else {
                        hi = decrementSumThree(num, hi);

                        mulThreeCombination(num,target,listSet);

                        stopLoop = true;
                        Log.e("StopSumThree","........2");

                    }


                }
            }
        }


    }

    private void mulThreeCombination(int[] num, int target, List<List<Integer>> listSet) {


        for (int i = 0; i < num.length - 3; i = incrementMulThree(num, i)) {

            if (stopLoop == true){

                stopLoop = false;

                break;
            }

            for (int j = i + 1; j < num.length - 2; j = incrementMulThree(num, j)) {
                int a = num[i], b = num[j], lo = j + 1, hi = num.length - 1;

                if (stopLoop == true){



                    break;
                }

                while (lo < hi) {
                    int c = num[lo], d = num[hi];
                    int sum = a * b * c;


                    //Sum
                    if (sum == target) {
                        listHintFinal = Arrays.asList(a, b, c);
                        listSet.add(listHintFinal);
                        lo = incrementMulThree(num, lo);
                        hi = decrementMulThree(num, hi);

                        Log.e("@@FINALMULTHREE",listHintFinal.toString());

                        stopLoop = true;



                        break;

                    } else if (sum < target) {
                        lo = incrementMulThree(num, lo);


                        Log.e("StopMulThree","........1");

                    } else {
                        hi = decrementMulThree(num, hi);

                        sumFourCombination(num,target,listSet);

                        stopLoop = true;

                        Log.e("StopMulThree","........2");

                    }


                }
            }
        }


    }

    private void sumFourCombination(int[] num, int target, List<List<Integer>> listSet) {


        for (int i = 0; i < num.length - 3; i = incrementSumFour(num, i)) {


            if (stopLoop == true){

                stopLoop = false;

                break;
            }

            for (int j = i + 1; j < num.length - 2; j = incrementSumFour(num, j)) {
                int a = num[i], b = num[j], lo = j + 1, hi = num.length - 1;


                if (stopLoop == true){


                    break;
                }

                while (lo < hi) {
                    int c = num[lo], d = num[hi];
                    int sum = a + b + c + d;


                    //Sum
                    if (sum == target) {
                        listHintFinal = Arrays.asList(a, b, c, d);
                        listSet.add(listHintFinal);
                        lo = incrementSumFour(num, lo);
                        hi = decrementSumFour(num, hi);

                        Log.e("@@FINALSUMFOUR",listHintFinal.toString());

                        stopLoop = true;


                        break;

                    } else if (sum < target) {
                        lo = incrementSumFour(num, lo);


                        Log.e("StopSumFour","........1");

                    } else {
                        hi = decrementSumFour(num, hi);


                        mulFourCombination(num,target,listSet);

                        stopLoop = true;

                        Log.e("StopSumFour","........2");


                    }

                }
            }
        }


    }

    private void mulFourCombination(int[] num, int target, List<List<Integer>> listSet) {

        //MUL
        for (int i = 0; i < num.length - 3; i = incrementMulFour(num, i)) {


            if (stopLoop == true){

                stopLoop = false;

                break;
            }


            for (int j = i + 1; j < num.length - 2; j = incrementMulFour(num, j)) {
                int a = num[i], b = num[j], lo = j + 1, hi = num.length - 1;


                if (stopLoop == true){

                    break;
                }

                while (lo < hi) {
                    int c = num[lo], d = num[hi];

                    int mul = a * b * c * d;


                    //Sum
                    if (mul == target) {
                        listHintFinal = Arrays.asList(a, b, c, d);
                        listSet.add(listHintFinal);
                        lo = incrementMulFour(num, lo);
                        hi = decrementMulFour(num, hi);

                        Log.e("@@FINALMUL",listHintFinal.toString());
                        stopLoop = true;


                        break;

                    } else if (mul < target) {
                        lo = decrementMulFour(num, lo);



                        Log.e("StopMulFour","........1");

                    } else {
                        hi = decrementMulFour(num, hi);
                        stopLoop = true;

                        Log.e("StopMulFour","........2");

                    }


                }
            }
        }

    }


    /*Combination method end*/

    /*Combination looping start*/

    private int incrementSumFour(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrementSumFour(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }

    private int incrementMulFour(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrementMulFour(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }

    private int incrementSumThree(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrementSumThree(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }

    private int incrementMulThree(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrementMulThree(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }


    private int incrementSumTwo(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrementSumTwo(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }

    private int incrementMulTwo(int[] num, int lo) {
        while (lo < num.length - 1 && num[lo] == num[++lo]) {}
        return lo;
    }

    private int decrementMulTwo(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[--hi]) {}
        return hi;
    }

    /*Combination looping end*/

    private void addNumberToList() {


        if (lhsValue == false) {

            lhsNumberList.add(firstCount + 1);
            Log.e("lhsValue", "......true");

        } else if (rhsValue == false) {

            rhsNumberList.add(secondCount + 1);
            Log.e("rhsValue", "......true");

        } else {

            Log.e("novalueadded", "......oops");
        }


    }

    private void onEqual() {


        if (selectFinal == false && hintnumber == false) {

            if (lastNumeric && !stateError) {

                String txt = txtScreen.getText().toString();

                if (txt == null) {

                    Log.e("Expression empty", "........");

                    /*ln_operation_1.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_2.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_3.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_4.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_5.setBackground(getResources().getDrawable(R.drawable.operation_click_two));*/

                    tv_op_1.setImageResource(R.drawable.add_operation);
                    tv_op_2.setImageResource(R.drawable.minus_operation);
                    tv_op_3.setImageResource(R.drawable.multiply_operation);
                    tv_op_4.setImageResource(R.drawable.divide_operation);
                    tv_op_5.setImageResource(R.drawable.equal_operation);


                } else {

                    finalLhs = txt;

                    Expression expression = new ExpressionBuilder(txt).build();
                    try {

//                        double result = expression.evaluate();

                        double result = expression.evaluate();

                        Log.e("result", Double.toString(result));

                        txtScreen.setText("");

                        cc.savePrefString("LHS", Double.toString(result));

                        LHS = cc.loadPrefString("LHS");



                        String[] parts = LHS.split("\\."); // escape .
                        targetNumber = Integer.parseInt(parts[0]);
                        String part2 = parts[1];

                        Log.e("LHS", LHS);
                        Log.e("LHSINTEGER", String.valueOf(targetNumber));

                        startHint = true;

                        selectFinal = true;
                        selectOne = false;
                        lhsValue = true;
                        hintError = true;
                        rhsValue = false;
                        hintnumber = true;

                        finalAnswerValidation = false;

                        addHintToIntArrayWhenNumberSelected();

                    } catch (ArithmeticException ex) {

                        txtScreen.setText("Error");
                        stateError = true;
                        lastNumeric = false;
                    }

                    /*ln_operation_1.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_2.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_3.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_4.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_5.setBackground(getResources().getDrawable(R.drawable.operation_click_two));*/


                    Log.e("Expression Not empty", "........");

                }


            }

            notWin = false;

        } else if (selectFinal == true && hintnumber == true) {

            if (lastNumeric && !stateError) {

                String txt = txtScreen.getText().toString();

                if (txt == null) {

                    Log.e("Expression empty", "........");

                    /*ln_operation_1.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_2.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_3.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_4.setBackground(getResources().getDrawable(R.drawable.operation_click_two));
                    ln_operation_5.setBackground(getResources().getDrawable(R.drawable.operation_click_two));*/

                    tv_op_1.setImageResource(R.drawable.add_operation);
                    tv_op_2.setImageResource(R.drawable.minus_operation);
                    tv_op_3.setImageResource(R.drawable.multiply_operation);
                    tv_op_4.setImageResource(R.drawable.divide_operation);
                    tv_op_5.setImageResource(R.drawable.equal_operation);

                } else {

                    finalRhs = txt;

                    displayFinalEquation(finalLhs,finalRhs);

                    Expression expression = new ExpressionBuilder(txt).build();
                    try {

                        double result = expression.evaluate();

                        Log.e("result", Double.toString(result));

                        txtScreen.setText("");

                        cc.savePrefString("RHS", Double.toString(result));

                        RHS = cc.loadPrefString("RHS");


                        Log.e("RHS", RHS);

                        selectFinal = false;
                        selectOne = false;
                        hintnumber = false;

                        finalAnswerValidation = true;

                        equalNotDisplayRhs = true;

                        /*tv_op_1.setImageResource(R.drawable.add_operation);
                        tv_op_2.setImageResource(R.drawable.minus_operation);
                        tv_op_3.setImageResource(R.drawable.multiply_operation);
                        tv_op_4.setImageResource(R.drawable.divide_operation);
                        tv_op_5.setImageResource(R.drawable.equal_operation);*/

                    } catch (ArithmeticException ex) {

                        txtScreen.setText("Error");
                        stateError = true;
                        lastNumeric = false;
                    }


                    Log.e("Expression Not empty", "........");

                }


            }

            notWin = true;

        } else {

            Log.e("Nothing", ".........");
        }

        dialogPopup();

    }

    private void displayFinalEquation(String finalLhs, String finalRhs) {

       /* tv_screen_final_equation.setVisibility(View.VISIBLE);

        tv_screen_final_equation.setText(finalLhs + " = " + finalRhs);*/

    }

    private void addHintToIntArrayWhenNumberSelected() {

        hint_second_arraylist.clear();

        if (num_1 == false){

            addHintNumberToArraylist(tv_1);
        }

        if (num_2 == false){

            addHintNumberToArraylist(tv_2);
        }

        if (num_3 == false){

            addHintNumberToArraylist(tv_3);
        }

        if (num_4 == false){

            addHintNumberToArraylist(tv_4);
        }

        if (num_5 == false){

            addHintNumberToArraylist(tv_5);
        }

        if (num_6 == false){

            addHintNumberToArraylist(tv_6);
        }

        if (num_7 == false){

            addHintNumberToArraylist(tv_7);
        }

        if (num_8 == false){

            addHintNumberToArraylist(tv_8);
        }

        if (num_9 == false){

            addHintNumberToArraylist(tv_9);
        }

        if (num_10 == false){

            addHintNumberToArraylist(tv_10);
        }

        if (num_11 == false){

            addHintNumberToArraylist(tv_11);
        }

        if (num_12 == false){

            addHintNumberToArraylist(tv_12);
        }

        if (num_13 == false){

            addHintNumberToArraylist(tv_13);
        }

        if (num_14 == false){

            addHintNumberToArraylist(tv_14);
        }

        if (num_15 == false){

            addHintNumberToArraylist(tv_15);
        }

        if (num_16 == false){

            addHintNumberToArraylist(tv_16);
        }

        if (num_17 == false){

            addHintNumberToArraylist(tv_17);
        }

        if (num_18 == false){

            addHintNumberToArraylist(tv_18);
        }

        if (num_19 == false){

            addHintNumberToArraylist(tv_19);
        }

        if (num_20 == false){

            addHintNumberToArraylist(tv_20);
        }

        if (num_21 == false){

            addHintNumberToArraylist(tv_21);
        }

        if (num_22 == false){

            addHintNumberToArraylist(tv_22);
        }

        if (num_23 == false){

            addHintNumberToArraylist(tv_23);
        }

        if (num_24 == false){

            addHintNumberToArraylist(tv_24);
        }

        if (num_25 == false){

            addHintNumberToArraylist(tv_25);
        }

        if (num_26 == false){

            addHintNumberToArraylist(tv_26);
        }

        if (num_27 == false){

            addHintNumberToArraylist(tv_27);
        }

        if (num_28 == false){

            addHintNumberToArraylist(tv_28);
        }

    }

    private void dialogPopup() {

        if (LHS == null && RHS == null) {

            Log.e("LHS and RHS", "......null");

        } else {

            if (LHS.equals(RHS) && finalAnswerValidation == true) {

                removeSelected();

            } else if (!LHS.equals(RHS) && notWin == true) {

                removeSelectedNotWin();
                Log.e("Not winn", "......");



            } else {


            }

            Log.e("LHS and RHS", "......notnull");

        }

    }

    private void removeSelectedForDivideError() {

        selectOne = false;
        divideError = false;

        if (num_1 == true) {

            tv_1.setTextColor(Color.parseColor("#000000"));
            ln_1.setBackground(getResources().getDrawable(R.drawable.right_bottom_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_1 = false;

        } else {
        }

        if (num_2 == true) {

            tv_2.setTextColor(Color.parseColor("#000000"));
            ln_2.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_2 = false;

        } else {
        }

        if (num_3 == true) {

            tv_3.setTextColor(Color.parseColor("#000000"));
            ln_3.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_3 = false;

        } else {
        }

        if (num_4 == true) {

            tv_4.setTextColor(Color.parseColor("#000000"));
            ln_4.setBackground(getResources().getDrawable(R.drawable.left_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_4 = false;

        } else {
        }

        if (num_5 == true) {

            tv_5.setTextColor(Color.parseColor("#000000"));
            ln_5.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_5 = false;

        } else {
        }

        if (num_6 == true) {

            tv_6.setTextColor(Color.parseColor("#000000"));
            ln_6.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_6 = false;

        } else {
        }

        if (num_7 == true) {

            tv_7.setTextColor(Color.parseColor("#000000"));
            ln_7.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_7 = false;

        } else {
        }

        if (num_8 == true) {

            tv_8.setTextColor(Color.parseColor("#000000"));
            ln_8.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_8 = false;

        } else {
        }

        if (num_9 == true) {

            tv_9.setTextColor(Color.parseColor("#000000"));
            ln_9.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_9 = false;

        } else {
        }

        if (num_10 == true) {

            tv_10.setTextColor(Color.parseColor("#000000"));
            ln_10.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_10 = false;

        } else {
        }

        if (num_11 == true) {

            tv_11.setTextColor(Color.parseColor("#000000"));
            ln_11.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_11 = false;

        } else {
        }

        if (num_12 == true) {

            tv_12.setTextColor(Color.parseColor("#000000"));
            ln_12.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_12 = false;

        } else {
        }

        if (num_13 == true) {

            tv_13.setTextColor(Color.parseColor("#000000"));
            ln_13.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_13 = false;

        } else {
        }

        if (num_14 == true) {

            tv_14.setTextColor(Color.parseColor("#000000"));
            ln_14.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_14 = false;

        } else {
        }
        if (num_15 == true) {

            tv_15.setTextColor(Color.parseColor("#000000"));
            ln_15.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_15 = false;

        } else {
        }

        if (num_16 == true) {

            tv_16.setTextColor(Color.parseColor("#000000"));
            ln_16.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_16 = false;

        } else {
        }

        if (num_17 == true) {

            tv_17.setTextColor(Color.parseColor("#000000"));
            ln_17.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_17 = false;

        } else {
        }

        if (num_18 == true) {

            tv_18.setTextColor(Color.parseColor("#000000"));
            ln_18.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_18 = false;

        } else {
        }

        if (num_19 == true) {

            tv_19.setTextColor(Color.parseColor("#000000"));
            ln_19.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_19 = false;

        } else {
        }

        if (num_20 == true) {

            tv_20.setTextColor(Color.parseColor("#000000"));
            ln_20.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_20 = false;

        } else {
        }

        if (num_21 == true) {

            tv_21.setTextColor(Color.parseColor("#000000"));
            ln_21.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_21 = false;

        } else {
        }

        if (num_22 == true) {

            tv_22.setTextColor(Color.parseColor("#000000"));
            ln_22.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_22 = false;

        } else {
        }

        if (num_23 == true) {

            tv_23.setTextColor(Color.parseColor("#000000"));
            ln_23.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_23 = false;

        } else {
        }

        if (num_24 == true) {

            tv_24.setTextColor(Color.parseColor("#000000"));
            ln_24.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_24 = false;

        } else {
        }

        if (num_25 == true) {

            tv_25.setTextColor(Color.parseColor("#000000"));
            ln_25.setBackground(getResources().getDrawable(R.drawable.right_top_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_25 = false;

        } else {
        }

        if (num_26 == true) {

            tv_26.setTextColor(Color.parseColor("#000000"));
            ln_26.setBackground(getResources().getDrawable(R.drawable.left_right_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_26 = false;

        } else {
        }

        if (num_27 == true) {

            tv_27.setTextColor(Color.parseColor("#000000"));
            ln_27.setBackground(getResources().getDrawable(R.drawable.left_right_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_27 = false;

        } else {
        }

        if (num_28 == true) {

            tv_28.setTextColor(Color.parseColor("#000000"));
            ln_28.setBackground(getResources().getDrawable(R.drawable.left_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_28 = false;

        } else {

            Log.e("Nothing WINN", "........");
        }

        lhsNumberList.clear();
        rhsNumberList.clear();
        finalNumberList.clear();

    }

    private void removeSelectedNotWin() {

        lhsValue = false;
        rhsValue = true;
        startHint = false;
        equalNotDisplayRhs = false;

        tv_screen_final_equation.setText("");

        finalNumberList.addAll(lhsNumberList);
        finalNumberList.addAll(rhsNumberList);


        System.out.println(lhsNumberList.size());
        System.out.println(rhsNumberList.size());
        System.out.println(finalNumberList.size());


        if (num_1 == true) {

            tv_1.setTextColor(Color.parseColor("#000000"));
            ln_1.setBackground(getResources().getDrawable(R.drawable.right_bottom_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_1 = false;

        } else {
        }

        if (num_2 == true) {

            tv_2.setTextColor(Color.parseColor("#000000"));
            ln_2.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_2 = false;

        } else {
        }

        if (num_3 == true) {

            tv_3.setTextColor(Color.parseColor("#000000"));
            ln_3.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_3 = false;

        } else {
        }

        if (num_4 == true) {

            tv_4.setTextColor(Color.parseColor("#000000"));
            ln_4.setBackground(getResources().getDrawable(R.drawable.left_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_4 = false;

        } else {
        }

        if (num_5 == true) {

            tv_5.setTextColor(Color.parseColor("#000000"));
            ln_5.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_5 = false;

        } else {
        }

        if (num_6 == true) {

            tv_6.setTextColor(Color.parseColor("#000000"));
            ln_6.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_6 = false;

        } else {
        }

        if (num_7 == true) {

            tv_7.setTextColor(Color.parseColor("#000000"));
            ln_7.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_7 = false;

        } else {
        }

        if (num_8 == true) {

            tv_8.setTextColor(Color.parseColor("#000000"));
            ln_8.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_8 = false;

        } else {
        }

        if (num_9 == true) {

            tv_9.setTextColor(Color.parseColor("#000000"));
            ln_9.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_9 = false;

        } else {
        }

        if (num_10 == true) {

            tv_10.setTextColor(Color.parseColor("#000000"));
            ln_10.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_10 = false;

        } else {
        }

        if (num_11 == true) {

            tv_11.setTextColor(Color.parseColor("#000000"));
            ln_11.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_11 = false;

        } else {
        }

        if (num_12 == true) {

            tv_12.setTextColor(Color.parseColor("#000000"));
            ln_12.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_12 = false;

        } else {
        }

        if (num_13 == true) {

            tv_13.setTextColor(Color.parseColor("#000000"));
            ln_13.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_13 = false;

        } else {
        }

        if (num_14 == true) {

            tv_14.setTextColor(Color.parseColor("#000000"));
            ln_14.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_14 = false;

        } else {
        }
        if (num_15 == true) {

            tv_15.setTextColor(Color.parseColor("#000000"));
            ln_15.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_15 = false;

        } else {
        }

        if (num_16 == true) {

            tv_16.setTextColor(Color.parseColor("#000000"));
            ln_16.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_16 = false;

        } else {
        }

        if (num_17 == true) {

            tv_17.setTextColor(Color.parseColor("#000000"));
            ln_17.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_17 = false;

        } else {
        }

        if (num_18 == true) {

            tv_18.setTextColor(Color.parseColor("#000000"));
            ln_18.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_18 = false;

        } else {
        }

        if (num_19 == true) {

            tv_19.setTextColor(Color.parseColor("#000000"));
            ln_19.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_19 = false;

        } else {
        }

        if (num_20 == true) {

            tv_20.setTextColor(Color.parseColor("#000000"));
            ln_20.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_20 = false;

        } else {
        }

        if (num_21 == true) {

            tv_21.setTextColor(Color.parseColor("#000000"));
            ln_21.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_21 = false;

        } else {
        }

        if (num_22 == true) {

            tv_22.setTextColor(Color.parseColor("#000000"));
            ln_22.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_22 = false;

        } else {
        }

        if (num_23 == true) {

            tv_23.setTextColor(Color.parseColor("#000000"));
            ln_23.setBackground(getResources().getDrawable(R.drawable.main_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_23 = false;

        } else {
        }

        if (num_24 == true) {

            tv_24.setTextColor(Color.parseColor("#000000"));
            ln_24.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_24 = false;

        } else {
        }

        if (num_25 == true) {

            tv_25.setTextColor(Color.parseColor("#000000"));
            ln_25.setBackground(getResources().getDrawable(R.drawable.right_top_border));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_25 = false;

        } else {
        }

        if (num_26 == true) {

            tv_26.setTextColor(Color.parseColor("#000000"));
            ln_26.setBackground(getResources().getDrawable(R.drawable.left_right_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_26 = false;

        } else {
        }

        if (num_27 == true) {

            tv_27.setTextColor(Color.parseColor("#000000"));
            ln_27.setBackground(getResources().getDrawable(R.drawable.left_right_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_27 = false;

        } else {
        }

        if (num_28 == true) {

            tv_28.setTextColor(Color.parseColor("#000000"));
            ln_28.setBackground(getResources().getDrawable(R.drawable.left_top));

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_28 = false;

        } else {

            Log.e("Nothing WINN", "........");
        }

        lhsNumberList.clear();
        rhsNumberList.clear();
        finalNumberList.clear();

    }

    private void removeSelected() {

        lhsValue = false;
        rhsValue = true;
        startHint = false;
        equalNotDisplayRhs = false;

        tv_screen_final_equation.setText("");

        finalNumberList.addAll(lhsNumberList);
        finalNumberList.addAll(rhsNumberList);

        System.out.println(lhsNumberList.size());
        System.out.println(rhsNumberList.size());
        System.out.println(finalNumberList.size());

        if (easy == true){

            Log.e("EasyLevel","....pointNootassign");

            if (startedSeven == true){

                stopForSeven();
                startForSeven();

                Log.e("@@SSSSSS","....seven");

            }else if(startedFourteen == true){

                stopFourteen();
                startFourteen();

                Log.e("@@SSSSSS","....Fourteen");

            }else if (startedTwentyOne == true){

                stopTwentyOne();
                startTwentyOne();

                Log.e("@@SSSSSS","....TwentyOne");

            }else {

            }

        }else {

            scroreDialogPopup();
        }


        if (num_1 == true) {

            //tv_1.setText("");

            tv_1.setVisibility(View.INVISIBLE);
            ln_1.setBackground(getResources().getDrawable(R.drawable.right_bottom_border));
            ln_1.setClickable(false);

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            num_1 = false;

            rearrangeNumber_1 = true;
            rearrangeNumber_s1 = false;

            uniq_number1 = 0;

            automaticNumberList.add(uniq_number1);


            undoAll = true;

            addUndoNumberToListTwo("0");

        } else {
        }

        if (num_2 == true) {

//            tv_2.setText("");

            tv_2.setVisibility(View.INVISIBLE);

            ln_2.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));
            ln_2.setClickable(false);
            num_2 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_2 = true;
            rearrangeNumber_s2 = false;
            undoAll = true;

            uniq_number2 = 1;

            automaticNumberList.add(uniq_number2);

            addUndoNumberToListTwo("1");
        } else {
        }

        if (num_3 == true) {

            //tv_3.setText("");

            tv_3.setVisibility(View.INVISIBLE);

            ln_3.setBackground(getResources().getDrawable(R.drawable.left_right_bottom));
            ln_3.setClickable(false);
            num_3 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_3 = true;
            rearrangeNumber_s3 = false;
            undoAll = true;
            uniq_number3 = 2;

            automaticNumberList.add(uniq_number3);

            addUndoNumberToListTwo("2");
        } else {
        }

        if (num_4 == true) {

//            tv_4.setText("");

            tv_4.setVisibility(View.INVISIBLE);

            ln_4.setBackground(getResources().getDrawable(R.drawable.left_bottom));
            ln_4.setClickable(false);
            num_4 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_4 = true;
            rearrangeNumber_s4 = false;
            undoAll = true;
            uniq_number4 = 3;

            automaticNumberList.add(uniq_number4);

            addUndoNumberToListTwo("3");
        } else {
        }

        if (num_5 == true) {

//            tv_5.setText("");

            tv_5.setVisibility(View.INVISIBLE);

            ln_5.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));
            ln_5.setClickable(false);
            num_5 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_5 = true;
            rearrangeNumber_s5 = false;
            undoAll = true;
            uniq_number5 = 4;
            automaticNumberList.add(uniq_number5);
            addUndoNumberToListTwo("4");
        } else {
        }

        if (num_6 == true) {

//            tv_6.setText("");

            tv_6.setVisibility(View.INVISIBLE);

            ln_6.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_6.setClickable(false);
            num_6 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_6 = true;
            rearrangeNumber_s6 = false;
            undoAll = true;
            uniq_number6 = 5;
            automaticNumberList.add(uniq_number6);
            addUndoNumberToListTwo("5");
        } else {
        }

        if (num_7 == true) {

//            tv_7.setText("");

            tv_7.setVisibility(View.INVISIBLE);

            ln_7.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_7.setClickable(false);
            num_7 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_7 = true;
            rearrangeNumber_s7 = false;
            undoAll = true;
            uniq_number7 = 6;
            automaticNumberList.add(uniq_number7);
            addUndoNumberToListTwo("6");
        } else {
        }

        if (num_8 == true) {

//            tv_8.setText("");

            tv_8.setVisibility(View.INVISIBLE);

            ln_8.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
            ln_8.setClickable(false);
            num_8 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_8 = true;
            rearrangeNumber_s8 = false;
            undoAll = true;
            uniq_number8 = 7;
            automaticNumberList.add(uniq_number8);
            addUndoNumberToListTwo("7");
        } else {
        }

        if (num_9 == true) {

//            tv_9.setText("");

            tv_9.setVisibility(View.INVISIBLE);

            ln_9.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));
            ln_9.setClickable(false);
            num_9 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_9 = true;
            rearrangeNumber_s9 = false;
            undoAll = true;
            uniq_number9 = 8;
            automaticNumberList.add(uniq_number9);
            addUndoNumberToListTwo("8");
        } else {
        }

        if (num_10 == true) {

//            tv_10.setText("");

            tv_10.setVisibility(View.INVISIBLE);

            ln_10.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_10.setClickable(false);
            num_10 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_10 = true;
            rearrangeNumber_s10 = false;
            undoAll = true;
            uniq_number10 = 9;
            automaticNumberList.add(uniq_number10);
            addUndoNumberToListTwo("9");
        } else {
        }

        if (num_11 == true) {

//            tv_11.setText("");

            tv_11.setVisibility(View.INVISIBLE);

            ln_11.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_11.setClickable(false);
            num_11 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_11 = true;
            rearrangeNumber_s11 = false;
            undoAll = true;
            uniq_number11= 10;
            automaticNumberList.add(uniq_number11);
            addUndoNumberToListTwo("10");
        } else {
        }

        if (num_12 == true) {

//            tv_12.setText("");

            tv_12.setVisibility(View.INVISIBLE);

            ln_12.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
            ln_12.setClickable(false);
            num_12 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_12 = true;
            rearrangeNumber_s12 = false;
            undoAll = true;
            uniq_number12 = 11;
            automaticNumberList.add(uniq_number12);
            addUndoNumberToListTwo("11");
        } else {
        }

        if (num_13 == true) {

//            tv_13.setText("");

            tv_13.setVisibility(View.INVISIBLE);

            ln_13.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));
            ln_13.setClickable(false);
            num_13 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_13 = true;
            rearrangeNumber_s13 = false;
            undoAll = true;
            uniq_number13 = 12;
            automaticNumberList.add(uniq_number13);
            addUndoNumberToListTwo("12");
        } else {
        }

        if (num_14 == true) {

//            tv_14.setText("");

            tv_14.setVisibility(View.INVISIBLE);

            ln_14.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_14.setClickable(false);
            num_14 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_14 = true;
            rearrangeNumber_s14 = false;
            undoAll = true;
            uniq_number14 = 13;
            automaticNumberList.add(uniq_number14);
            addUndoNumberToListTwo("13");
        } else {
        }
        if (num_15 == true) {

//            tv_15.setText("");

            tv_15.setVisibility(View.INVISIBLE);

            ln_15.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_15.setClickable(false);
            num_15 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_15 = true;
            rearrangeNumber_s15 = false;
            undoAll = true;
            uniq_number15 = 14;
            automaticNumberList.add(uniq_number15);
            addUndoNumberToListTwo("14");
        } else {
        }
        if (num_16 == true) {

//            tv_16.setText("");

            tv_16.setVisibility(View.INVISIBLE);

            ln_16.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
            ln_16.setClickable(false);
            num_16 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_16 = true;
            rearrangeNumber_s16 = false;
            undoAll = true;
            uniq_number16 = 15;
            automaticNumberList.add(uniq_number16);
            addUndoNumberToListTwo("15");
        } else {
        }
        if (num_17 == true) {

//            tv_17.setText("");

            tv_17.setVisibility(View.INVISIBLE);

            ln_17.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));
            ln_17.setClickable(false);
            num_17 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_17 = true;
            rearrangeNumber_s17 = false;
            undoAll = true;
            uniq_number17 = 16;
            automaticNumberList.add(uniq_number17);
            addUndoNumberToListTwo("16");
        } else {
        }
        if (num_18 == true) {

//            tv_18.setText("");

            tv_18.setVisibility(View.INVISIBLE);

            ln_18.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_18.setClickable(false);
            num_18 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_18 = true;
            rearrangeNumber_s18 = false;
            undoAll = true;
            uniq_number18 = 17;
            automaticNumberList.add(uniq_number18);
            addUndoNumberToListTwo("17");
        } else {
        }
        if (num_19 == true) {

//            tv_19.setText("");

            tv_19.setVisibility(View.INVISIBLE);

            ln_19.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_19.setClickable(false);
            num_19 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_19 = true;
            rearrangeNumber_s19 = false;
            undoAll = true;
            uniq_number19 = 18;
            automaticNumberList.add(uniq_number19);
            addUndoNumberToListTwo("18");
        } else {
        }
        if (num_20 == true) {

//            tv_20.setText("");

            tv_20.setVisibility(View.INVISIBLE);

            ln_20.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
            ln_20.setClickable(false);
            num_20 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_20 = true;
            rearrangeNumber_s20 = false;
            undoAll = true;
            uniq_number20 = 19;
            automaticNumberList.add(uniq_number20);
            addUndoNumberToListTwo("19");
        } else {
        }
        if (num_21 == true) {

//            tv_21.setText("");

            tv_21.setVisibility(View.INVISIBLE);

            ln_21.setBackground(getResources().getDrawable(R.drawable.right_top_bottom));
            ln_21.setClickable(false);
            num_21 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_21 = true;
            rearrangeNumber_s21 = false;
            undoAll = true;
            uniq_number21 = 20;
            automaticNumberList.add(uniq_number21);
            addUndoNumberToListTwo("20");
        } else {
        }
        if (num_22 == true) {

//            tv_22.setText("");

            tv_22.setVisibility(View.INVISIBLE);

            ln_22.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_22.setClickable(false);
            num_22 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_22 = true;
            rearrangeNumber_s22 = false;
            undoAll = true;
            uniq_number22 = 21;
            automaticNumberList.add(uniq_number22);
            addUndoNumberToListTwo("21");
        } else {
        }
        if (num_23 == true) {

//            tv_23.setText("");

            tv_23.setVisibility(View.INVISIBLE);

            ln_23.setBackground(getResources().getDrawable(R.drawable.main_border));
            ln_23.setClickable(false);
            num_23 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_23 = true;
            rearrangeNumber_s23 = false;
            undoAll = true;
            uniq_number23 = 22;
            automaticNumberList.add(uniq_number23);
            addUndoNumberToListTwo("22");
        } else {
        }
        if (num_24 == true) {

//            tv_24.setText("");

            tv_24.setVisibility(View.INVISIBLE);

            ln_24.setBackground(getResources().getDrawable(R.drawable.left_bottom_top));
            ln_24.setClickable(false);
            num_24 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_24 = true;
            rearrangeNumber_s24 = false;
            undoAll = true;
            uniq_number24 = 23;
            automaticNumberList.add(uniq_number24);
            addUndoNumberToListTwo("23");
        } else {
        }
        if (num_25 == true) {

//            tv_25.setText("");

            tv_25.setVisibility(View.INVISIBLE);

            ln_25.setBackground(getResources().getDrawable(R.drawable.right_top_border));
            ln_25.setClickable(false);
            num_25 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_25 = true;
            rearrangeNumber_s25 = false;
            undoAll = true;
            uniq_number25 = 24;
            automaticNumberList.add(uniq_number25);
            addUndoNumberToListTwo("24");
        } else {
        }
        if (num_26 == true) {

//            tv_26.setText("");

            tv_26.setVisibility(View.INVISIBLE);

            ln_26.setBackground(getResources().getDrawable(R.drawable.left_right_top));
            ln_26.setClickable(false);
            num_26 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_26 = true;
            rearrangeNumber_s26 = false;
            undoAll = true;
            uniq_number26 = 25;
            automaticNumberList.add(uniq_number26);
            addUndoNumberToListTwo("25");
        } else {
        }
        if (num_27 == true) {

//            tv_27.setText("");

            tv_27.setVisibility(View.INVISIBLE);

            ln_27.setBackground(getResources().getDrawable(R.drawable.left_right_top));
            ln_27.setClickable(false);
            num_27 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_27 = true;
            rearrangeNumber_s27 = false;
            undoAll = true;
            uniq_number27 = 26;
            automaticNumberList.add(uniq_number27);
            addUndoNumberToListTwo("26");
        } else {
        }
        if (num_28 == true) {

//            tv_28.setText("");

            tv_28.setVisibility(View.INVISIBLE);

            ln_28.setBackground(getResources().getDrawable(R.drawable.left_top));
            ln_28.setClickable(false);
            num_28 = false;

            tv_op_1.setImageResource(R.drawable.add_operation);
            tv_op_2.setImageResource(R.drawable.minus_operation);
            tv_op_3.setImageResource(R.drawable.multiply_operation);
            tv_op_4.setImageResource(R.drawable.divide_operation);
            tv_op_5.setImageResource(R.drawable.equal_operation);

            rearrangeNumber_28 = true;
            rearrangeNumber_s28 = false;
            undoAll = true;
            uniq_number28 = 27;
            automaticNumberList.add(uniq_number28);
            addUndoNumberToListTwo("27");
        } else {

            Log.e("Nothing WINN", "........");
        }


        lhsNumberList.clear();
        rhsNumberList.clear();
        finalNumberList.clear();

        hint_second_arraylist.clear();

        addHintNumberToArraylist(tv_1);
        addHintNumberToArraylist(tv_2);
        addHintNumberToArraylist(tv_3);
        addHintNumberToArraylist(tv_4);
        addHintNumberToArraylist(tv_5);
        addHintNumberToArraylist(tv_6);
        addHintNumberToArraylist(tv_7);
        addHintNumberToArraylist(tv_8);
        addHintNumberToArraylist(tv_9);
        addHintNumberToArraylist(tv_10);
        addHintNumberToArraylist(tv_11);
        addHintNumberToArraylist(tv_12);
        addHintNumberToArraylist(tv_13);
        addHintNumberToArraylist(tv_14);
        addHintNumberToArraylist(tv_15);
        addHintNumberToArraylist(tv_16);
        addHintNumberToArraylist(tv_17);
        addHintNumberToArraylist(tv_18);
        addHintNumberToArraylist(tv_19);
        addHintNumberToArraylist(tv_20);
        addHintNumberToArraylist(tv_21);
        addHintNumberToArraylist(tv_22);
        addHintNumberToArraylist(tv_23);
        addHintNumberToArraylist(tv_24);
        addHintNumberToArraylist(tv_25);
        addHintNumberToArraylist(tv_26);
        addHintNumberToArraylist(tv_27);
        addHintNumberToArraylist(tv_28);


        displayAutomaticNumber();



        if (hint_second_arraylist.size() == 0 && easy == true){

            stopCountDownTimer();

            bestTime = tv_timer.getText().toString();

            cc.savePrefString("best_time",bestTime);

            winningIntentEasy();
            Log.e("winningIntentEasy","........If");

        }else if(hint_second_arraylist.size() == 0 && easy == false){

            winningIntentHard();

        }else {

            Log.e("winningIntent","........Else");
        }

    }



    private void displayAutomaticNumber() {


        Log.e("displayAutomaticNumber", String.valueOf(automaticNumberList.size()));

        if (automaticNumberList.size() >= 7 && autoNumberSeven == false){

            Log.e("displayAutomaticNumber","..........7");
            startForSeven();
            autoNumberSeven = true;

        }else if (automaticNumberList.size() >= 14 && autoNumberFourteen == false){

            stopForSeven();
            startFourteen();
            Log.e("displayAutomaticNumber","..........14");
            autoNumberFourteen = true;

        }else if (automaticNumberList.size() >= 21 && autoNumberTwentyOne == false ){

            stopFourteen();
            startTwentyOne();
            Log.e("displayAutomaticNumber","..........21");
            autoNumberTwentyOne = true;

        }else {

            Log.e("displayAutomaticNumber","..........nothing");
        }

    }


    private Runnable runnableNumberSeven = new Runnable() {
        @Override
        public void run() {

            addAutomaticNumberToGrid();
            Log.e("runnableNumberSeven",".....@@@");

            if(startedSeven) {
                startForSeven();
            }
        }
    };


    private Runnable runnableNumberFourteen = new Runnable() {
        @Override
        public void run() {

            addAutomaticNumberToGrid();
            Log.e("runnableNumberFourteen",".....@@@");

            if(startedFourteen) {
                startFourteen();
            }
        }
    };

    private Runnable runnableNumberTwentyOne = new Runnable() {
        @Override
        public void run() {

            addAutomaticNumberToGrid();
            Log.e("runnableNumberTwentyOne",".....@@@");

            if(startedTwentyOne) {
                startTwentyOne();
            }
        }
    };

    public void stopForSeven() {
        startedSeven = false;
        handlerSeven.removeCallbacks(runnableNumberSeven);
    }

    public void stopFourteen() {
        startedFourteen = false;
        handlerFourteen.removeCallbacks(runnableNumberFourteen);
    }

    public void stopTwentyOne() {
        startedTwentyOne = false;
        handlerTwentyOne.removeCallbacks(runnableNumberTwentyOne);
    }

    public void startForSeven() {
        startedSeven = true;
        handlerSeven.postDelayed(runnableNumberSeven, 15000);
    }

    public void startFourteen() {
        startedFourteen = true;
        handlerFourteen.postDelayed(runnableNumberFourteen, 10000);
    }

    public void startTwentyOne() {
        startedTwentyOne = true;
        handlerTwentyOne.postDelayed(runnableNumberTwentyOne, 8000);
    }


    /*Pending..................................................................*/

    private void addAutomaticNumberToGrid() {

        if (tv_1.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_1.setText(array_set_up_text[rand_text_set_up]);

            tv_1.setVisibility(View.VISIBLE);
            tv_1.setTextColor(Color.parseColor("#000000"));
            ln_1.setClickable(true);
        }else if (tv_2.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_2.setText(array_set_up_text[rand_text_set_up]);

            tv_2.setVisibility(View.VISIBLE);
            tv_2.setTextColor(Color.parseColor("#000000"));
            ln_2.setClickable(true);
        }else if (tv_3.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_3.setText(array_set_up_text[rand_text_set_up]);

            tv_3.setVisibility(View.VISIBLE);
            tv_3.setTextColor(Color.parseColor("#000000"));
            ln_3.setClickable(true);
        }else if (tv_4.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_4.setText(array_set_up_text[rand_text_set_up]);

            tv_4.setVisibility(View.VISIBLE);
            tv_4.setTextColor(Color.parseColor("#000000"));
            ln_4.setClickable(true);
        }else if (tv_5.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_5.setText(array_set_up_text[rand_text_set_up]);

            tv_5.setVisibility(View.VISIBLE);
            tv_5.setTextColor(Color.parseColor("#000000"));
            ln_5.setClickable(true);
        }else if (tv_6.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_6.setText(array_set_up_text[rand_text_set_up]);


            tv_6.setVisibility(View.VISIBLE);
            tv_6.setTextColor(Color.parseColor("#000000"));
            ln_6.setClickable(true);
        }else if (tv_7.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_7.setText(array_set_up_text[rand_text_set_up]);

            tv_7.setVisibility(View.VISIBLE);
            tv_7.setTextColor(Color.parseColor("#000000"));
            ln_7.setClickable(true);
        }else if (tv_8.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_8.setText(array_set_up_text[rand_text_set_up]);

            tv_8.setVisibility(View.VISIBLE);
            tv_8.setTextColor(Color.parseColor("#000000"));
            ln_8.setClickable(true);
        }else if (tv_9.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_9.setText(array_set_up_text[rand_text_set_up]);

            tv_9.setVisibility(View.VISIBLE);
            tv_9.setTextColor(Color.parseColor("#000000"));
            ln_9.setClickable(true);
        }else if (tv_10.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_10.setText(array_set_up_text[rand_text_set_up]);

            tv_10.setVisibility(View.VISIBLE);
            tv_10.setTextColor(Color.parseColor("#000000"));
            ln_10.setClickable(true);
        }else if (tv_11.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_11.setText(array_set_up_text[rand_text_set_up]);

            tv_11.setVisibility(View.VISIBLE);
            tv_11.setTextColor(Color.parseColor("#000000"));
            ln_11.setClickable(true);
        }else if (tv_12.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_12.setText(array_set_up_text[rand_text_set_up]);

            tv_12.setVisibility(View.VISIBLE);
            tv_12.setTextColor(Color.parseColor("#000000"));
            ln_12.setClickable(true);
        }else if (tv_13.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_13.setText(array_set_up_text[rand_text_set_up]);

            tv_13.setVisibility(View.VISIBLE);
            tv_13.setTextColor(Color.parseColor("#000000"));
            ln_13.setClickable(true);
        }else if (tv_14.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_14.setText(array_set_up_text[rand_text_set_up]);

            tv_14.setVisibility(View.VISIBLE);
            tv_14.setTextColor(Color.parseColor("#000000"));
            ln_14.setClickable(true);
        }else if (tv_15.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_15.setText(array_set_up_text[rand_text_set_up]);

            tv_15.setVisibility(View.VISIBLE);
            tv_15.setTextColor(Color.parseColor("#000000"));
            ln_15.setClickable(true);
        }else if (tv_16.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_16.setText(array_set_up_text[rand_text_set_up]);

            tv_16.setVisibility(View.VISIBLE);
            tv_16.setTextColor(Color.parseColor("#000000"));
            ln_16.setClickable(true);
        }else if (tv_17.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_17.setText(array_set_up_text[rand_text_set_up]);

            tv_17.setVisibility(View.VISIBLE);
            tv_17.setTextColor(Color.parseColor("#000000"));
            ln_17.setClickable(true);
        }else if (tv_18.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_18.setText(array_set_up_text[rand_text_set_up]);

            tv_18.setVisibility(View.VISIBLE);
            tv_18.setTextColor(Color.parseColor("#000000"));
            ln_18.setClickable(true);
        }else if (tv_19.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_19.setText(array_set_up_text[rand_text_set_up]);

            tv_19.setVisibility(View.VISIBLE);
            tv_19.setTextColor(Color.parseColor("#000000"));
            ln_19.setClickable(true);
        }else if (tv_20.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_20.setText(array_set_up_text[rand_text_set_up]);

            tv_20.setVisibility(View.VISIBLE);
            tv_20.setTextColor(Color.parseColor("#000000"));
            ln_20.setClickable(true);
        }else if (tv_21.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_21.setText(array_set_up_text[rand_text_set_up]);

            tv_21.setVisibility(View.VISIBLE);
            tv_21.setTextColor(Color.parseColor("#000000"));
            ln_21.setClickable(true);
        }else if (tv_22.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_22.setText(array_set_up_text[rand_text_set_up]);

            tv_22.setVisibility(View.VISIBLE);
            tv_22.setTextColor(Color.parseColor("#000000"));
            ln_22.setClickable(true);
        }else if (tv_23.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_23.setText(array_set_up_text[rand_text_set_up]);

            tv_23.setVisibility(View.VISIBLE);
            tv_23.setTextColor(Color.parseColor("#000000"));
            ln_23.setClickable(true);
        }else if (tv_24.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_24.setText(array_set_up_text[rand_text_set_up]);

            tv_24.setVisibility(View.VISIBLE);
            tv_24.setTextColor(Color.parseColor("#000000"));
            ln_24.setClickable(true);
        }else if (tv_25.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_25.setText(array_set_up_text[rand_text_set_up]);

            tv_25.setVisibility(View.VISIBLE);
            tv_25.setTextColor(Color.parseColor("#000000"));
            ln_25.setClickable(true);
        }else if (tv_26.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_26.setText(array_set_up_text[rand_text_set_up]);

            tv_26.setVisibility(View.VISIBLE);
            tv_26.setTextColor(Color.parseColor("#000000"));
            ln_26.setClickable(true);
        }else if (tv_27.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_27.setText(array_set_up_text[rand_text_set_up]);

            tv_27.setVisibility(View.VISIBLE);
            tv_27.setTextColor(Color.parseColor("#000000"));
            ln_27.setClickable(true);
        }else if (tv_28.getVisibility() == View.INVISIBLE){

            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
            tv_28.setText(array_set_up_text[rand_text_set_up]);

            tv_28.setVisibility(View.VISIBLE);
            tv_28.setTextColor(Color.parseColor("#000000"));
            ln_28.setClickable(true);
        }else {

            if (startedSeven == true){

                stopForSeven();
            }else if (startedFourteen == true){
                stopFourteen();
            }else if (startedTwentyOne == true){
                stopTwentyOne();
            }

            Log.e("@@addAutomaticNumberToGrid","......Else");
        }
    }


    private void winningIntentHard() {

        if (cc.loadPrefBoolean("first_time_hard") == false){

            Intent gameOverIntent = new Intent(MainActivity.this,GameOverActivity.class);
            cc.savePrefBoolean("winningIntentHard",true);
            cc.savePrefString("total_point_hard",tv_points.getText().toString());
            cc.savePrefString("total_highscore", String.valueOf(totalEarningHighScore));
            cc.savePrefBoolean("first_time_hard",true);
            startActivity(gameOverIntent);



        }else {

            Intent gameOverIntent = new Intent(MainActivity.this,GameOverActivity.class);
            cc.savePrefBoolean("winningIntentHard",true);
            cc.savePrefString("total_point_hard",tv_points.getText().toString());
            cc.savePrefString("total_highscore", String.valueOf(totalEarningHighScore));
            cc.savePrefBoolean("first_time_hard",false);
            startActivity(gameOverIntent);


        }



        Log.e("winningIntentHard","........ElseIf");
    }

    private void gameOverIntentFinal() {

        dialog1 = new Dialog(MainActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(false);
        dialog1.setContentView(R.layout.game_over_dialog);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog1.getWindow().setLayout((6 * width) / 7, ActionBar.LayoutParams.WRAP_CONTENT);



        dialog1.show();

        ImageView iv_close = (ImageView) dialog1.findViewById(R.id.iv_close);
        ImageView iv_play_again = (ImageView) dialog1.findViewById(R.id.iv_play_again);

        iv_play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (easy == true){
                    Intent intentMain = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intentMain);
                    cc.savePrefBoolean("Easy",true);
                    cc.savePrefBoolean("Hard",true);


                    finish();

                }else {
                    Intent intentMain = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intentMain);
                    cc.savePrefBoolean("Hard",false);
                    cc.savePrefBoolean("Easy",false);

                    finish();
                }

                dialog1.dismiss();


            }
        });

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
                finish();
            }
        });


    }

    private void addHintNumberToArraylist(TextView tv) {
        /*vishal*/
        if (tv.getVisibility() == View.VISIBLE){

            hint_second_arraylist.add(Integer.valueOf(tv.getText().toString()));

            hint_second_intarray = new int[hint_second_arraylist.size()];


            for (int i=0; i < hint_second_intarray.length; i++)
            {
                hint_second_intarray[i] = hint_second_arraylist.get(i).intValue();
            }

            Log.e("@@hint_second_arraylist", String.valueOf(hint_second_arraylist.size()));
            Log.e("@@hint_second_intarray",Arrays.toString(hint_second_intarray));
            System.out.println(hint_second_arraylist+"SecondArray");

        }else {

            Log.e("@@hint_second_arraylist",".......Else");
        }
    }


    /*Pending.........................................................*/


    private void scroreDialogPopup() {

        //For start and stop thread when make equation

        if (startedSeven == true){

            stopForSeven();
            startForSeven();

            Log.e("@@SSSSSS","....seven");

        }else if(startedFourteen == true){

            stopFourteen();
            startFourteen();

            Log.e("@@SSSSSS","....Fourteen");

        }else if (startedTwentyOne == true){

            stopTwentyOne();
            startTwentyOne();

            Log.e("@@SSSSSS","....TwentyOne");

        }else {

        }

        if (finalNumberList.size() == 2) {

            int one = 20;

           // popupScrore(String.valueOf(one));

            addPointsTodatabase(one);

            totalPointDisplayToTextview();

            addPointToHardTable(one);

        } else if (finalNumberList.size() == 3) {

            int two = 60;

           // popupScrore(String.valueOf(two));

          //  pauseTimer();
            addPointsTodatabase(two);

            totalPointDisplayToTextview();

            addPointToHardTable(two);

        } else if (finalNumberList.size() == 4) {

            int three = 160;

            //popupScrore(String.valueOf(three));

         //   pauseTimer();


            addPointsTodatabase(three);

            totalPointDisplayToTextview();

            addPointToHardTable(three);

        } else if (finalNumberList.size() == 5) {

            int four = 250;

          //  popupScrore(String.valueOf(four));

         //   pauseTimer();

            addPointsTodatabase(four);

            totalPointDisplayToTextview();

            addPointToHardTable(four);

        } else if (finalNumberList.size() == 6) {

            int five = 360;

            //popupScrore(String.valueOf(five));

         //   pauseTimer();

            addPointsTodatabase(five);

            totalPointDisplayToTextview();

            addPointToHardTable(five);

        } else if (finalNumberList.size() == 7) {

            int six = 490;

           // popupScrore(String.valueOf(six));

         //   pauseTimer();

            addPointsTodatabase(six);

            totalPointDisplayToTextview();

            addPointToHardTable(six);

        } else if (finalNumberList.size() == 8) {

            int seven = 640;

          //  popupScrore(String.valueOf(seven));

           // pauseTimer();

            addPointsTodatabase(seven);

            totalPointDisplayToTextview();

            addPointToHardTable(seven);

        } else {

            Log.e("Score not added", "....Hard");
        }



    }

    private void addPointToHardTable(int one) {

        PointTable point = new PointTable(one);

        long tag1_id = db.createTableHardPoint(point);

        Log.e("Point hard....", String.valueOf(tag1_id));


        List<PointTable> allPoint = db.getAllPointsFromTableHardPoint();

        for (int i = 0; i < allPoint.size(); i++) {

            totalEarningHighScore += allPoint.get(i).getPoints();

        }

        Log.e("Total point Hard.......", String.valueOf(totalEarningHighScore));

        db.closeDB();

    }

    private void pauseTimer() {

        TimeBuff += MillisecondTime;

        handler.removeCallbacks(runnable);
    }

    private void addPointsTodatabase(int point_add) {

        PointTable point = new PointTable(point_add);

        long tag1_id = db.createTableOne(point);

        Log.e("Point Player One", String.valueOf(tag1_id));


        int totalEarningPoint = 0;

        List<PointTable> allPoint = db.getAllPointsFromTableOne();

        for (int i = 0; i < allPoint.size(); i++) {

            totalEarningPoint += allPoint.get(i).getPoints();

        }

        Log.e("Total point table One", String.valueOf(totalEarningPoint));

        db.closeDB();
    }

    private void popupScrore(String one) {

        final Dialog dialog1 = new Dialog(MainActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(false);
        dialog1.setContentView(R.layout.scror_dialog);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog1.getWindow().setLayout((6 * width) / 7, ActionBar.LayoutParams.WRAP_CONTENT);

        TextView tv_dialog_scror_display = (TextView) dialog1.findViewById(R.id.tv_dialog_scror_display);
        ImageView iv_close = (ImageView) dialog1.findViewById(R.id.iv_close);

        tv_dialog_scror_display.setText("You win" + " " + one + " " + "points");


        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tv_op_5.setImageResource(R.drawable.equal_operation);
                dialog1.dismiss();

                //cc.logoutapp();
            }
        });

        dialog1.show();

    }


    private void changeBackNumber(View view) {

        view.setBackground(getResources().getDrawable(R.drawable.number_click_shadow));

        tv_op_5.setImageResource(R.drawable.equal_operation);
    }

    private void changeBackOperation() {


        tv_op_1.setImageResource(R.drawable.add_operation);
        tv_op_2.setImageResource(R.drawable.minus_operation);
        tv_op_3.setImageResource(R.drawable.multiply_operation);
        tv_op_4.setImageResource(R.drawable.divide_operation);
        tv_op_5.setImageResource(R.drawable.equal_operation);
    }

    private void startStop() {


        /*StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
*/
        if (timerStatus == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues();

            // changing the timer status to started
            timerStatus = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer();

        } else {


            // changing the timer status to stopped
            timerStatus = TimerStatus.STOPPED;
            stopCountDownTimer();

        }

    }

    private void setTimerValues() {
        int time = 10;


        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                tv_timer.setText(hmsTimeFormatter(millisUntilFinished));


            }

            @Override
            public void onFinish() {

                tv_timer.setText(hmsTimeFormatter(timeCountInMilliSeconds));

                // changing the timer status to stopped
                timerStatus = TimerStatus.STOPPED;

                gameOverIntentFinal();
            }

        }.start();
        countDownTimer.start();
    }

    private void winningIntentEasy() {



        if (cc.loadPrefBoolean("first_time") == false){

            Intent gameOverIntent = new Intent(MainActivity.this,GameOverActivity.class);
            cc.savePrefBoolean("first_time",true);
            startActivity(gameOverIntent);

        }else {

            Intent gameOverIntent = new Intent(MainActivity.this,GameOverActivity.class);
            cc.savePrefBoolean("first_time",false);
            startActivity(gameOverIntent);
        }

    }

    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));


        return hms;


    }


    @Override
    protected void onResume() {

        totalPointDisplayToTextview();



        stopMediaService();

        super.onResume();
    }

    private void stopMediaService() {

        Intent serviceMedia = new Intent(this, BackgroundMediaService.class);
        stopService(serviceMedia);
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            tv_timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds));

            handler.postDelayed(this, 0);
        }

    };


    private void totalPointDisplayToTextview() {

        int totalEarningPoint = 0;
        List<PointTable> allPoint = db.getAllPointsFromTableOne();
        for (int i = 0; i < allPoint.size(); i++) {
            totalEarningPoint += allPoint.get(i).getPoints();

        }

        if (easy == true){

            tv_points.setVisibility(View.INVISIBLE);
            tv_points_head.setVisibility(View.INVISIBLE);

        }else {

            tv_points.setText(String.valueOf(totalEarningPoint));
        }



        Log.e("Total point table One", String.valueOf(totalEarningPoint));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        exitDialog();
        //super.onBackPressed();
    }

    private void exitDialog() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.exit_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width) / 7, ActionBar.LayoutParams.WRAP_CONTENT);


        TextView tv_dialog_yes = (TextView) dialog.findViewById(R.id.tv_exit_yes);
        TextView tv_dialog_no = (TextView) dialog.findViewById(R.id.tv_exit_no);

        final Animation myAnim;
        myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        tv_dialog_yes.startAnimation(myAnim);
        tv_dialog_no.startAnimation(myAnim);

        tv_dialog_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

                dialog.dismiss();
            }
        });

        tv_dialog_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();
            }
        });



        dialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopMediaService();
        stopCountDownTimer();
    }
}
