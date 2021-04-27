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

public class ResultsActivity3 extends AppCompatActivity {
    String gradeMessage3;
    TextView percent3, txtGradeMessage3, totalPoints3, wordsUsed3, longestWord3, shortestWord3;
    Button next3;
    ProgressBar myProgressBar3;

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
            setContentView(R.layout.activity_results3);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_results_large3);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        percent3 = (TextView) findViewById(R.id.percent3);
        txtGradeMessage3 = (TextView) findViewById(R.id.txtGradeMessage3);
        totalPoints3 = (TextView) findViewById(R.id.totalPoints3);
        wordsUsed3 = (TextView) findViewById(R.id.wordsUsed3);
        longestWord3 = (TextView) findViewById(R.id.longestWord3);
        shortestWord3 = (TextView) findViewById(R.id.shortestWord3);
        myProgressBar3 = (ProgressBar) findViewById(R.id.myProgressBar3);
        next3 = (Button) findViewById(R.id.next3);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView3);
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

        Intent intent = getIntent();
        String max = intent.getStringExtra("max");
        String min = intent.getStringExtra("min");
        int nrOfWords = intent.getIntExtra("nrOfWords3", 0);
        int score = intent.getIntExtra("score4", 0);

        gradeMessage3 = "TIP: Check again the list of ending letters and use more words before winning, to get a higher score";
        txtGradeMessage3.setText(gradeMessage3);
        int percentage = (int)Math.round((score*100.0)/80.0);
        percent3.setText(percentage + "%");
        if (percentage > 100) {
            myProgressBar3.setProgress(100);
        } else {
            myProgressBar3.setProgress(percentage);
        }

        myProgressBar3.animate();

        totalPoints3.setText("Total\npoints:\n" + score);
        longestWord3.setText("Longest\nword:\n" + max);
        shortestWord3.setText("Shortest\nword:\n" + min);
        wordsUsed3.setText("Words\nused:\n" + nrOfWords);

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(ResultsActivity3.this, ProfileActivity.class);
                startActivity(goToHome);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You are not allowed to go back to the game now", Toast.LENGTH_LONG).show();
    }
}
