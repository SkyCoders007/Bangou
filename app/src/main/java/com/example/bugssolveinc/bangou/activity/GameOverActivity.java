package com.example.bugssolveinc.bangou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bugssolveinc.bangou.R;
import com.example.bugssolveinc.bangou.comman.CommanClass;
import com.example.bugssolveinc.bangou.helper.DatabaseHelper;

/**
 * Created by mxi on 12/1/18.
 */

public class GameOverActivity extends AppCompatActivity {

    CommanClass cc;

    LinearLayout ln_new_game_win,ln_total_point,ln_easy_visibility,ln_high_scrore;

    TextView tv_difficulty,tv_time,tv_best_time,tv_total_point,tv_excellent,tv_highscore;
    boolean levelDifficulty;
    String bestTime,timeNormal;

    int n1,n2,b1,b2;

    DatabaseHelper db;

    String highScoreOne,highScoreTwo;

    int hScoreOne,hScoreTwo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover_activity);

        init();
    }

    private void init() {

        /*Pending.......................................*/

        cc = new CommanClass(this);

        db = new DatabaseHelper(getApplicationContext());


        levelDifficulty = cc.loadPrefBoolean("Easy");


        tv_difficulty = (TextView)findViewById(R.id.tv_difficulty);
        tv_time = (TextView)findViewById(R.id.tv_time);
        tv_best_time = (TextView)findViewById(R.id.tv_best_time);
        tv_total_point = (TextView)findViewById(R.id.tv_total_point);
        tv_excellent = (TextView)findViewById(R.id.tv_excellent);
        tv_highscore = (TextView)findViewById(R.id.tv_highscore);

        ln_new_game_win = (LinearLayout) findViewById(R.id.ln_new_game_win);
        ln_total_point = (LinearLayout) findViewById(R.id.ln_total_point);
        ln_easy_visibility = (LinearLayout) findViewById(R.id.ln_easy_visibility);
        ln_high_scrore = (LinearLayout) findViewById(R.id.ln_high_scrore);

        ln_new_game_win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMain = new Intent(GameOverActivity.this,MainActivity.class);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intentMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentMain);

            }
        });


        if (levelDifficulty == true){

            tv_difficulty.setText("Easy");

        }else if (levelDifficulty == false) {

            tv_difficulty.setText("Hard");
        }else {

        }




        if (cc.loadPrefBoolean("winningIntentHard") == true){

            tv_total_point.setText(cc.loadPrefString("total_point_hard"));

            highScoreOne = cc.loadPrefString("total_highscore");

            hScoreOne = Integer.parseInt(highScoreOne);

            Log.e("hScoreOne", String.valueOf(hScoreOne));

            if (cc.loadPrefBoolean("first_time_hard") == true){

                tv_highscore.setText(highScoreOne);

                cc.savePrefString("highscore_final",tv_highscore.getText().toString());

                db.deleteAll();

                cc.savePrefBoolean("first_time_hard",true);

            }else {

                highScoreTwo = cc.loadPrefString("highscore_final");

                hScoreTwo = Integer.parseInt(highScoreTwo);

                db.deleteAll();

                if (hScoreOne > hScoreTwo){

                    tv_highscore.setText(highScoreOne);
                    cc.savePrefString("highscore_final",tv_highscore.getText().toString());

                }else {

                    tv_highscore.setText(highScoreTwo);
                }


                Log.e("highscore_final_else", String.valueOf(hScoreTwo));

                cc.savePrefBoolean("first_time_hard",true);
            }



            ln_total_point.setVisibility(View.VISIBLE);
            ln_high_scrore.setVisibility(View.VISIBLE);
            ln_easy_visibility.setVisibility(View.INVISIBLE);
            tv_excellent.setVisibility(View.INVISIBLE);
            cc.savePrefBoolean("winningIntentHard",false);

        }else {


            timeNormal = cc.loadPrefString("best_time");

            tv_time.setText(timeNormal);

            timeNormal = tv_time.getText().toString();

            String[] parts2 = timeNormal.split(":");
            n1 = Integer.parseInt(parts2[0]);
            n2 = Integer.parseInt(parts2[1]);

            if (cc.loadPrefBoolean("first_time") == true){


                tv_best_time.setText(timeNormal);

                cc.savePrefBoolean("first_time",true);
                cc.savePrefString("best_time2",timeNormal);

            }else {



                cc.savePrefBoolean("first_time",true);

                bestTime = cc.loadPrefString("best_time2");

                String[] parts = bestTime.split(":");
                b1 = Integer.parseInt(parts[0]);
                b2 = Integer.parseInt(parts[1]);

                Log.e("best", bestTime);
                Log.e("normal", timeNormal);

                int a1 = n1 + n2;
                int a2 = b1 + b2;

                Log.e("normaltime", String.valueOf(a1));
                Log.e("besttime", String.valueOf(a2));


                if (a1 > a2){

                    Log.e("besttime",".......");

                    tv_best_time.setText(bestTime);

                }else {

                    tv_best_time.setText(timeNormal);

                    cc.savePrefString("best_time2",timeNormal);

                    Log.e("normaltime",".......");
                }

            }


        }

    }

    @Override
    public void onBackPressed() {

    }
}
