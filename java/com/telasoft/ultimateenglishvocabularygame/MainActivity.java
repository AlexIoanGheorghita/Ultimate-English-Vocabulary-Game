package com.telasoft.ultimateenglishvocabularygame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

import static com.mopub.common.logging.MoPubLog.LogLevel.NONE;

public class MainActivity extends AppCompatActivity {

    CheckBox myCheck;
    Button btnPvAI, btnPvP, btnCompleteWord, btnWordBonanza, btnOpposites;
    TextView textView, textView3, textView2;
    String mode;
    private AdView mAdView;
    boolean myOption;

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
            setContentView(R.layout.activity_main);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_main_large);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        myCheck = (CheckBox) findViewById(R.id.myCheck);
        btnPvAI = (Button)findViewById(R.id.btnPvAI);
        btnPvP = (Button)findViewById(R.id.btnPvP);
        btnCompleteWord = (Button)findViewById(R.id.btnCompleteWord);
        btnWordBonanza = (Button) findViewById(R.id.btnWordBonanza);
        btnOpposites = (Button) findViewById(R.id.btnOpposites);
        textView = (TextView) findViewById(R.id.textView);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView2 = (TextView) findViewById(R.id.textView2);

        textView2.setTextColor(getResources().getColor(R.color.white));

        loadData();
        myCheck.setChecked(myOption);
        /*myOption = myCheck.isChecked();
        saveData(myOption);
        loadData();
        myCheck.setChecked(myOption);*/

        //MoPubAdsHandlerBannerAndInterstitial adsHandler = new MoPubAdsHandlerBannerAndInterstitial(this, AddCheck.MySourceActivity);
        //adsHandler.handleBannerAds();
        //adsHandler.handleInterstitialAds();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
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

        myCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myOption = myCheck.isChecked();
                saveData(myOption);
            }
        });

        btnPvAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOption) {
                    Intent i = new Intent(MainActivity.this, HowToPlay.class);
                    mode = "PvAI";
                    i.putExtra("mode", mode);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        btnPvP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOption) {
                    Intent i2 = new Intent(MainActivity.this, HowToPlay.class);
                    mode = "PvP";
                    i2.putExtra("mode", mode);
                    startActivity(i2);
                    finish();
                } else {
                    Intent i2 = new Intent(MainActivity.this, PvP.class);
                    startActivity(i2);
                    finish();
                }
            }
        });

        btnCompleteWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOption) {
                    Intent i3 = new Intent(MainActivity.this, HowToPlay.class);
                    mode = "CompleteWord";
                    i3.putExtra("mode", mode);
                    startActivity(i3);
                    finish();
                } else {
                    Intent i3 = new Intent(MainActivity.this, CompleteTheWord.class);
                    startActivity(i3);
                    finish();
                }
            }
        });

        btnWordBonanza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOption) {
                    Intent i4 = new Intent(MainActivity.this, HowToPlay.class);
                    mode = "Bonanza";
                    i4.putExtra("mode", mode);
                    startActivity(i4);
                    finish();
                } else {
                    Intent i4 = new Intent(MainActivity.this, Bonanza.class);
                    startActivity(i4);
                    finish();
                }
            }
        });

        btnOpposites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOption) {
                    Intent i5 = new Intent(MainActivity.this, HowToPlay.class);
                    mode = "Antonyms";
                    i5.putExtra("mode", mode);
                    startActivity(i5);
                    finish();
                } else {
                    Intent i5 = new Intent(MainActivity.this, Opposites.class);
                    startActivity(i5);
                    finish();
                }
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(getResources().getColor(R.color.darkGray));
                Intent homeToProfile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(homeToProfile);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please press the HOME button to exit game", Toast.LENGTH_SHORT).show();
    }

    public boolean checkOption() {
        return myCheck.isChecked();
    }

    public void saveData(boolean option) {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs2", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("set", option);

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs2", MODE_PRIVATE);
        myOption = sharedPreferences.getBoolean("set", true);
    }
}

