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

import java.util.ArrayList;

public class ResultsActivity4 extends AppCompatActivity {
    String gradeMessage4, min, max;
    TextView percent4, txtGradeMessage4, totalPoints4, wordsUsed4, longestWord4, shortestWord4;
    Button next4;
    ProgressBar myProgressBar4;
    ArrayList<String> MinMax;

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
            setContentView(R.layout.activity_results4);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_results_large4);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        percent4 = (TextView) findViewById(R.id.percent4);
        txtGradeMessage4 = (TextView) findViewById(R.id.txtGradeMessage4);
        totalPoints4 = (TextView) findViewById(R.id.totalPoints4);
        wordsUsed4 = (TextView) findViewById(R.id.wordsUsed4);
        longestWord4 = (TextView) findViewById(R.id.longestWord4);
        shortestWord4 = (TextView) findViewById(R.id.shortestWord4);
        myProgressBar4 = (ProgressBar) findViewById(R.id.myProgressBar4);
        next4 = (Button) findViewById(R.id.next4);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView4);
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
        MinMax = new ArrayList<>();
        MinMax = intent.getStringArrayListExtra("array");
        int nrOfWords = intent.getIntExtra("nrOfWords", 0);
        int score = intent.getIntExtra("score3", 0);

        gradeMessage4 = "TIP: Use more words before winning, to get a higher score";
        txtGradeMessage4.setText(gradeMessage4);
        int percentage = (int)Math.round((score*100.0)/80.0);
        percent4.setText(percentage + "%");
        if (percentage > 100) {
            myProgressBar4.setProgress(100);
        } else {
            myProgressBar4.setProgress(percentage);
        }

        myProgressBar4.animate();

        totalPoints4.setText("Total\npoints:\n" + score);
        wordsUsed4.setText("Words\nused:\n" + nrOfWords);

        assert MinMax != null;
        min = MinMax.get(0);
        max = MinMax.get(0);
        for (int x=1; x<=MinMax.size()-1; x++) {
            if (MinMax.get(x).length() > max.length()) {
                max = MinMax.get(x);
            }
            if (MinMax.get(x).length() < min.length()) {
                min = MinMax.get(x);
            }
        }

        longestWord4.setText("Longest\nword:\n" + max);
        shortestWord4.setText("Shortest\nword:\n" + min);

        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ResultsActivity4.this, ProfileActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You are not allowed to go back to the game now", Toast.LENGTH_LONG).show();
    }
}
