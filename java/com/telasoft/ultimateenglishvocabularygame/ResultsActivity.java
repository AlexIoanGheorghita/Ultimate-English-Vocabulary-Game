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
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    String gradeMessage;
    TextView percent, txtGradeMessage, totalPoints, wordsUsed, longestWord, shortestWord;
    Button next;
    ProgressBar myProgressBar;
    ArrayList<String> wordsUsedUser;

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
            setContentView(R.layout.activity_results);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_results_large);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        percent = (TextView) findViewById(R.id.percent);
        txtGradeMessage = (TextView) findViewById(R.id.txtGradeMessage);
        totalPoints = (TextView) findViewById(R.id.totalPoints);
        wordsUsed = (TextView) findViewById(R.id.wordsUsed);
        longestWord = (TextView) findViewById(R.id.longestWord);
        shortestWord = (TextView) findViewById(R.id.shortestWord);
        myProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        next = (Button) findViewById(R.id.next);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView1);
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

        Bundle intentFromMain2 = getIntent().getExtras();
        wordsUsedUser = new ArrayList<String>();
        wordsUsedUser = intentFromMain2.getStringArrayList("userWords");
        final int userScore = intentFromMain2.getInt("userScore", 0);
        totalPoints.setText("Total\npoints:\n" + userScore);

        gradeMessage = "TIP: Check again the list of ending letters and use more words before winning, to get a higher score";
        int percentage = (int)Math.round((userScore*100.0)/80.0);
        percent.setText(percentage + "%");
        if (percentage > 100) {
            myProgressBar.setProgress(100);
        } else {
            myProgressBar.setProgress(percentage);
        }

        txtGradeMessage.setText(gradeMessage);

        final int wordsLength = wordsUsedUser.size();
        //Toast.makeText(this, "Size of list: " + wordsLength, Toast.LENGTH_SHORT).show();

        wordsUsed.setText("Words\nused:\n" + wordsLength);

        String max = wordsUsedUser.get(0);
        for (int x = 1; x<=wordsUsedUser.size()-1; x++) {
            if (wordsUsedUser.get(x).length() > max.length()) {
                max = wordsUsedUser.get(x);
            }
        }

        longestWord.setText("Longest\nword:\n" + max);

        String min = wordsUsedUser.get(0);
        for (int x = 1; x<=wordsUsedUser.size()-1; x++) {
            if (wordsUsedUser.get(x).length() < min.length()) {
                min = wordsUsedUser.get(x);
            }
        }

        shortestWord.setText("Shortest\nword:\n" + min);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToProfile = new Intent(ResultsActivity.this, ProfileActivity.class);
                startActivity(goToProfile);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You are not allowed to go back to the game now", Toast.LENGTH_LONG).show();
    }
}
