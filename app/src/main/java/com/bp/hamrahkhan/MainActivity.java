package com.bp.hamrahkhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imgcar);
        playSound(getApplicationContext(),R.raw.car_honk);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.enter_left);
        img.setVisibility(View.VISIBLE);
        img.startAnimation(animation);





    }

    public static void playSound(Context context, int soundID){
        MediaPlayer mp = MediaPlayer.create(context, soundID);
        Handler handler = new Handler();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                   mp.start();
                    timer.cancel();
                });

            }
        }, 1500, 1);


    }
}