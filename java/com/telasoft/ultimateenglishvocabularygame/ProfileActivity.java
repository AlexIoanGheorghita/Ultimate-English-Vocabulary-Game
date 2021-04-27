package com.telasoft.ultimateenglishvocabularygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    TextView txtTotalWords, txtTotalScore, txtRank, textView6, textView4, textView8;
    int myOption;
    //public static final String SHARED_PREFS = "sharedPrefs";
    //public static final String INT1 = "int1";
    //public static final String INT2 = "int2";
    //public static final String RANK = "rank";
    private int text1, text2;
    //String bronze = "#CD7f32";
    //String silver = "#C0C0C0";
    //String gold = "#FFD700";
    //String legendary = "#017A5B";

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
            setContentView(R.layout.activity_profile);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_profile_large);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        txtTotalWords = (TextView) findViewById(R.id.txtTotalWords);
        txtTotalScore = (TextView) findViewById(R.id.txtTotalScore);
        txtRank = (TextView) findViewById(R.id.txtRank);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView8 = (TextView) findViewById(R.id.textView8);

        Intent getMyIntent = getIntent();

        textView6.setTextColor(getResources().getColor(R.color.white));

        loadData();
        String myRank = setRank(text1, text2);
        setRankMedal(myRank);
        setTotalWordsAndScore(myRank);
        updateTextViews(myRank);

        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView6.setTextColor(getResources().getColor(R.color.darkGray));
                Intent goToHome = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(goToHome);
            }
        });
    }

    /*public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(INT1, myWordsExtra);
        editor.putString(INT2, myScoreExtra);
        editor.putString(RANK, "");

        editor.apply();
    }*/

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        text1 = sharedPreferences.getInt("int1", 0);
        text2 = sharedPreferences.getInt("int2", 0);
        //text3 = sharedPreferences.getString(RANK, "");
    }

    public void updateTextViews(String r) {
        if (r.equals("Bronze")) {
            txtTotalWords.setText(String.valueOf(text2) + " / 300");
            txtTotalScore.setText(String.valueOf(text1) + " / 2000");
        } else if (r.equals("Silver")) {
            txtTotalWords.setText(String.valueOf(text2) + " / 700");
            txtTotalScore.setText(String.valueOf(text1) + " / 3500");
        } else if (r.equals("Gold")) {
            txtTotalWords.setText(String.valueOf(text2) + " / 1200");
            txtTotalScore.setText(String.valueOf(text1) + " / 5000");
        } else {
            txtTotalWords.setText(String.valueOf(text2) + " / 1200");
            txtTotalScore.setText(String.valueOf(text1) + " / 5000");
        }
    }

    public String setRank(int a, int b) {
        String rank = "";
        if (a <= 300 && b <= 2000) {
            rank = "Bronze";
        } else if (a <= 700 && b <= 3500) {
            rank = "Silver";
        } else if (a <= 1200 && b <= 5000) {
            rank = "Gold";
        } else {
            rank = "Legend";
        }
        return rank;
    }

    public void setRankMedal(String r) {
        if (r.equals("Bronze")) {
            txtRank.setBackground(getResources().getDrawable(R.drawable.bronze));
        } else if (r.equals("Silver")) {
            txtRank.setBackground(getResources().getDrawable(R.drawable.silver));
        } else if (r.equals("Gold")) {
            txtRank.setBackground(getResources().getDrawable(R.drawable.gold));
        } else {
            txtRank.setBackground(getResources().getDrawable(R.drawable.legend));
        }
    }

    public void setTotalWordsAndScore(String r) {
        if (r.equals("Bronze")) {
            textView4.setText("Words used until Silver Rank:");
            textView8.setText("Score until Silver Rank:");
        } else if (r.equals("Silver")) {
            textView4.setText("Words used until Gold Rank:");
            textView8.setText("Score until Gold Rank:");
        } else if (r.equals("Gold")) {
            textView4.setText("Words used until Legendary Rank");
            textView8.setText("Score until Legendary Rank:");
        } else {
            textView4.setText("Total words used");
            textView8.setText("Total score:");
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
