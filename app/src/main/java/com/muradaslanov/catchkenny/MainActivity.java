package com.muradaslanov.catchkenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

TextView timeText;
TextView scoreText;
int score;
    ImageView kenFir;
    ImageView kenSec;
    ImageView kenThir;
    ImageView kenFour;
    ImageView kenFive;
    ImageView kenSix;
    ImageView kenSev;
    ImageView kenEig;
    ImageView kenNine;
    ImageView[] imageViews;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

timeText = findViewById(R.id.timeText);
scoreText = findViewById(R.id.scoreText);

kenFir = findViewById(R.id.kennyFir);
kenSec = findViewById(R.id.kennySec);
kenThir = findViewById(R.id.kennyThir);
kenFour = findViewById(R.id.kennyFour);
kenFive = findViewById(R.id.kennyFive);
kenSix = findViewById(R.id.kennySix);
kenSev = findViewById(R.id.kennySev);
kenEig = findViewById(R.id.kennyEigh);
kenNine = findViewById(R.id.kennyNine);

imageViews = new ImageView[]{kenFir, kenSec, kenThir, kenFour, kenFive, kenSix, kenSev, kenEig, kenNine};

hideImages();

score = 0;

       new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
timeText.setText("Time: " + l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Out of time!");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageViews){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                alertDialog.setTitle("Restart the Game");
                alertDialog.setMessage("Sure?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Restart

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.show();


            }
        }.start();
    }

    public void incScore(View view){
        score++;
        scoreText.setText("Score: " + score);
    }

public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageViews){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageViews[i].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable,500);
            }
        };

        handler.post(runnable);

}

}