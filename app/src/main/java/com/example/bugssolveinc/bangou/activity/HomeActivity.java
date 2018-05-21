package com.example.bugssolveinc.bangou.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugssolveinc.bangou.R;
import com.example.bugssolveinc.bangou.comman.CommanClass;
import com.example.bugssolveinc.bangou.gameanimation.MyBounceInterpolator;
import com.example.bugssolveinc.bangou.service.BackgroundMediaService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_play,btn_help,btn_rate;
    ImageView iv_set_music,iv_share;

    CommanClass cc;

    boolean on_off_media = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cc = new CommanClass(HomeActivity.this);


       // cc.savePrefBoolean("on_off_media",false);

        initButton();

    }

    private void initButton() {

        btn_play=(Button)findViewById(R.id.btn_play);
        btn_rate=(Button)findViewById(R.id.btn_rate);
        btn_help=(Button)findViewById(R.id.btn_help);

        iv_set_music =(ImageView) findViewById(R.id.iv_set_music);
        iv_share =(ImageView) findViewById(R.id.iv_share);


        btn_play.setOnClickListener(this);
        btn_rate.setOnClickListener(this);
        btn_help.setOnClickListener(this);
        iv_set_music.setOnClickListener(this);
        iv_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_play:

                Intent i =new Intent(HomeActivity.this,Choselevel.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();

                btn_play.setBackground(getResources().getDrawable(R.drawable.bt_change_bg));
                btn_play.setTextColor(Color.WHITE);

                btn_rate.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                btn_help.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));

                btn_rate.setTextColor(Color.BLACK);
                btn_help.setTextColor(Color.BLACK);


                break;

            case R.id.btn_rate:

                btn_rate.setBackground(getResources().getDrawable(R.drawable.bt_change_bg));
                btn_rate.setTextColor(Color.WHITE);

                btn_play.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                btn_help.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));

                btn_play.setTextColor(Color.BLACK);
                btn_help.setTextColor(Color.BLACK);



                break;

            case R.id.btn_help:

                btn_help.setBackground(getResources().getDrawable(R.drawable.bt_change_bg));
                btn_help.setTextColor(Color.WHITE);

                btn_rate.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));
                btn_play.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corners));


                btn_play.setTextColor(Color.BLACK);
                btn_rate.setTextColor(Color.BLACK);

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

        final Dialog dialog = new Dialog(HomeActivity.this);
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

                stopMediaService();
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
    protected void onResume() {
        super.onResume();

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        btn_play.startAnimation(myAnim);
        btn_rate.startAnimation(myAnim);
        btn_help.startAnimation(myAnim);


        if (cc.loadPrefBoolean("on_off_media_back") == false){

            playService();

            iv_set_music.setImageResource(R.drawable.sound);

        }else if (cc.loadPrefBoolean("on_off_media_back") == true){

            stopMediaService();

            iv_set_music.setImageResource(R.drawable.mute_sound);

        }else {

            playService();

            iv_set_music.setImageResource(R.drawable.sound);
        }
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
