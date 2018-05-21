package com.example.bugssolveinc.bangou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.bugssolveinc.bangou.comman.CommanClass;
import com.example.bugssolveinc.bangou.R;

/**
 * Created by mxi on 13/10/17.
 */

public class MainActivityHome extends AppCompatActivity implements View.OnClickListener{

    CommanClass cc;

    TextView tv_easy,tv_hard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        cc = new CommanClass(this);

        initTextview();

    }

    private void initTextview() {

        tv_easy = (TextView)findViewById(R.id.tv_easy);
        tv_hard = (TextView)findViewById(R.id.tv_hard);

        tv_easy.setOnClickListener(this);
        tv_hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_easy:

                Intent easy = new Intent(MainActivityHome.this,MainActivity.class);
                cc.savePrefString2("Easy","easy");
                startActivity(easy);
                finish();

                break;

            case R.id.tv_hard:

                Intent hard = new Intent(MainActivityHome.this,MainActivity.class);
                cc.savePrefString2("Hard","hard");
                startActivity(hard);
                finish();

                break;

        }
    }


}
