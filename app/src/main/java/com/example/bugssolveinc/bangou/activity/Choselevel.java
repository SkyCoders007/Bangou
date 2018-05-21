package com.example.bugssolveinc.bangou.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bugssolveinc.bangou.R;
import com.example.bugssolveinc.bangou.comman.CommanClass;
import com.example.bugssolveinc.bangou.gameanimation.MyBounceInterpolator;
import com.example.bugssolveinc.bangou.service.BackgroundMediaService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Choselevel extends AppCompatActivity implements View.OnClickListener{
    Button btn_easy_level,btn_hard_level;
    ImageView iv_set_music,iv_share;

    Animation myAnim;

    CommanClass cc;

    boolean on_off_media = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choselevel);

        cc = new CommanClass(Choselevel.this);

        initButton();

    }

    private void initButton() {

        btn_easy_level=(Button)findViewById(R.id.btn_easy_level);
        btn_hard_level=(Button)findViewById(R.id.btn_hard_level);

        iv_set_music =(ImageView) findViewById(R.id.iv_set_music);
        iv_share =(ImageView) findViewById(R.id.iv_share);

        btn_easy_level.setOnClickListener(this);
        btn_hard_level.setOnClickListener(this);
        iv_set_music.setOnClickListener(this);
        iv_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_easy_level:

                Intent i = new Intent(Choselevel.this, MainActivity.class);
                cc.savePrefBoolean("Easy",true);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();

                btn_easy_level.setBackground(getResources().getDrawable(R.drawable.bt_change_bg));
                btn_easy_level.setTextColor(Color.WHITE);


                btn_hard_level.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                btn_hard_level.setTextColor(Color.BLACK);


                break;

            case R.id.btn_hard_level:

                Intent j = new Intent(Choselevel.this, MainActivity.class);
                cc.savePrefBoolean("Hard",false);
                startActivity(j);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();

                btn_hard_level.setBackground(getResources().getDrawable(R.drawable.bt_change_bg));
                btn_hard_level.setTextColor(Color.WHITE);

                btn_easy_level.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                btn_easy_level.setTextColor(Color.BLACK);


                break;

            case R.id.iv_set_music:

                if (cc.loadPrefBoolean("on_off_media") == false){

                    stopMediaService();

                    cc.savePrefBoolean("on_off_media",true);

                    cc.savePrefBoolean("on_off_media_back",true);

                    iv_set_music.setImageResource(R.drawable.mute_sound);

                }else {

                    playService();

                    cc.savePrefBoolean("on_off_media",false);

                    cc.savePrefBoolean("on_off_media_back",false);

                    iv_set_music.setImageResource(R.drawable.sound);

                }

                break;

            case R.id.iv_share:

                shareViaIntentDialog();

                break;

        }


    }

    private void shareViaIntentDialog() {

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.bangoe_logo);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + "temporary_file.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        share.putExtra(Intent.EXTRA_TEXT, "Bangao Mathematics Equation Solve Game,For Download visit" + " " + "https://play.google.com/store/apps/details?id=com.facebook.katana&hl=en");

        share.putExtra(Intent.EXTRA_STREAM,
                Uri.parse("file:///sdcard/temporary_file.jpg"));
        startActivity(Intent.createChooser(share, "Share Image"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        btn_easy_level.startAnimation(myAnim);
        btn_hard_level.startAnimation(myAnim);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent homeIntent = new Intent(Choselevel.this,HomeActivity.class);
        startActivity(homeIntent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        finish();
    }

    private void playService() {

        Intent serviceMedia = new Intent(this, BackgroundMediaService.class);
        startService(serviceMedia);
    }

    private void stopMediaService() {

        Intent serviceMedia = new Intent(this, BackgroundMediaService.class);
        stopService(serviceMedia);
    }

}
