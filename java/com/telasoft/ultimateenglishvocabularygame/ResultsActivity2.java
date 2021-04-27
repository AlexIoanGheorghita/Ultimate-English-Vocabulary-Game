package com.telasoft.ultimateenglishvocabularygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ResultsActivity2 extends AppCompatActivity {
    String gradeMessage2;
    TextView percent2, txtGradeMessage2, totalPoints2, wordsUsed2, longestWord2, shortestWord2;
    Button next2;
    ProgressBar myProgressBar2;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if (width<= 1200) {
            setContentView(R.layout.activity_results2);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_results_large2);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        percent2 = (TextView) findViewById(R.id.percent2);
        txtGradeMessage2 = (TextView) findViewById(R.id.txtGradeMessage2);
        totalPoints2 = (TextView) findViewById(R.id.totalPoints2);
        wordsUsed2 = (TextView) findViewById(R.id.wordsUsed2);
        longestWord2 = (TextView) findViewById(R.id.longestWord2);
        shortestWord2 = (TextView) findViewById(R.id.shortestWord2);
        myProgressBar2 = (ProgressBar) findViewById(R.id.myProgressBar2);
        next2 = (Button) findViewById(R.id.next2);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Test banner ad: ca-app-pub-3940256099942544/6300978111

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        Intent i = getIntent();
        int wordsTotal = i.getIntExtra("wordsAnswered", 0);
        int userScore2 = i.getIntExtra("myScore", 0);
        totalPoints2.setText("Total\npoints:\n" + userScore2);

        gradeMessage2 = "TIP: Answer correctly to more words in order to get a higher score";
        txtGradeMessage2.setText(gradeMessage2);

        if (wordsTotal == 0) {
            myProgressBar2.setProgress(0);
            percent2.setText("0%");
        } else if (wordsTotal == 1) {
            myProgressBar2.setProgress(25);
            percent2.setText("25%");
        } else if (wordsTotal == 2) {
            myProgressBar2.setProgress(50);
            percent2.setText("50%");
        } else if (wordsTotal == 3) {
            myProgressBar2.setProgress(75);
            percent2.setText("75%");
        } else if (wordsTotal == 4) {
            myProgressBar2.setProgress(100);
            percent2.setText("100%");
        }

        myProgressBar2.animate();

        wordsUsed2.setText("Answered\ncorrectly:\n" + wordsTotal);

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(ResultsActivity2.this, ProfileActivity.class);
                startActivity(goToHome);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You are not allowed to go back to the game now", Toast.LENGTH_LONG).show();
    }
}
