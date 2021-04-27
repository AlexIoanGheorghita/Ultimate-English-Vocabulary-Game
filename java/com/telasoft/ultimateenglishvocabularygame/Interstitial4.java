package com.telasoft.ultimateenglishvocabularygame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

public class Interstitial4 extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    //Button adNext4;
    //ArrayList<String> wordsUsedByUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial1);

        //adNext4 = (Button) findViewById(R.id.adNext4);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score4", 0);
        int wordsUsed = intent.getIntExtra("nrOfWords3", 0);
        String max = intent.getStringExtra("max");
        String min = intent.getStringExtra("min");
        //wordsUsedByUser = new ArrayList<String>();
        //wordsUsedByUser = intent.getStringArrayListExtra("array");

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        //Test ad: ca-app-pub-3940256099942544/1033173712

        AdRequest adRequest = new AdRequest.Builder().build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                showInterstitial();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Intent intentResults = new Intent(Interstitial4.this, ResultsActivity3.class);
                intentResults.putExtra("score4Int", score);
                intentResults.putExtra("nrOfWords3Int", wordsUsed);
                intentResults.putExtra("maxInt", max);
                intentResults.putExtra("minInt", min);
                //intentResults.putStringArrayListExtra("arrayInt", wordsUsedByUser);
                startActivity(intentResults);
                /*Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adNext4.setVisibility(View.VISIBLE);
                        adNext4.setEnabled(true);
                    }
                }, 2500);*/
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                /*Intent intentResults = new Intent(Interstitial1.this, ResultsActivity.class);
                intentResults.putExtra("userScoreInt", score);
                intentResults.putStringArrayListExtra("userWordsInt", wordsUsedByUser);
                startActivity(intentResults);*/
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                Intent intentResults = new Intent(Interstitial4.this, ResultsActivity3.class);
                intentResults.putExtra("score4Int", score);
                intentResults.putExtra("nrOfWords3Int", wordsUsed);
                intentResults.putExtra("maxInt", max);
                intentResults.putExtra("minInt", min);
                //intentResults.putStringArrayListExtra("arrayInt", wordsUsedByUser);
                startActivity(intentResults);
            }
        });

        /*adNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResults = new Intent(Interstitial4.this, ResultsActivity3.class);
                intentResults.putExtra("score4Int", score);
                intentResults.putExtra("nrOfWords3Int", wordsUsed);
                intentResults.putExtra("maxInt", max);
                intentResults.putExtra("minInt", min);
                //intentResults.putStringArrayListExtra("arrayInt", wordsUsedByUser);
                startActivity(intentResults);
            }
        });*/
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You are not allowed to perform this action", Toast.LENGTH_LONG).show();
    }
}