package com.telasoft.ultimateenglishvocabularygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Opposites extends AppCompatActivity {

    ArrayList<String> contents2, wordsUsed;
    String[] contents;
    Button btnUseWordOpposite, btnGiveUpOpposite;
    TextView scoreOpposite, yourWord, round;
    EditText opposite;
    int scoreOp, counter, scoreInMemory, nrOfWordsInMemory;
    boolean myOption;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String INT1 = "int1";
    public static final String INT2 = "int2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_opposites);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if (width<= 1200) {
            setContentView(R.layout.activity_opposites);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_opposites_large);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        btnUseWordOpposite = (Button) findViewById(R.id.btnUseOpposite);
        btnGiveUpOpposite = (Button) findViewById(R.id.btnGiveUpOpposite);
        scoreOpposite = (TextView) findViewById(R.id.scoreOpposite);
        yourWord = (TextView) findViewById(R.id.txtYourWord);
        opposite = (EditText) findViewById(R.id.opposite);
        round = (TextView) findViewById(R.id.textView11);
        scoreOp = 0;
        counter = 0;

        wordsUsed = new ArrayList<String>();

        Toast.makeText(this, "You have 10 rounds", Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                round.setText("Round: " + (counter + 1));
                getWord();
            }
        }, 1000);

        btnUseWordOpposite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wordEnteredByUser = opposite.getText().toString().toLowerCase();
                validateWord(wordEnteredByUser, contents2);
            }
        });

        btnGiveUpOpposite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < 9) {
                    opposite.setText("");
                    Toast.makeText(Opposites.this, "You skipped round " + counter, Toast.LENGTH_SHORT).show();
                    counter = counter + 1;
                    round.setText("Round: " + (counter + 1));
                    getWord();
                } else {
                    loadData();
                    scoreInMemory = scoreInMemory + scoreOp;
                    nrOfWordsInMemory = nrOfWordsInMemory + wordsUsed.size();
                    saveData(scoreInMemory, nrOfWordsInMemory);

                    Intent i = new Intent(Opposites.this, ResultsActivity5.class);
                    i.putExtra("opScore", scoreOp);
                    i.putStringArrayListExtra("opWords", wordsUsed);
                    startActivity(i);
                }
            }
        });

    }

    public void getWord() {
        String[] opposites = getResources().getStringArray(R.array.antonyms);

        long random = Math.round(Math.random() * opposites.length - 1);
        int randNum = (int) random;

        for (int i = 0; i < opposites.length; i++) {
            if (i == randNum) {
                contents = opposites[i].split(",", 0);
            }
        }

        contents2 = new ArrayList<String>();
        for (String item : contents) {
            contents2.add(item);
        }

        yourWord.setText(contents[0]);
    }

    public void validateWord(String word, ArrayList<String> array) {
        boolean found = false;
        if (counter < 9) {
            int x;
            for (x = 1; x < array.size(); x++) {
                if (word.equals(array.get(x))) {
                    found = true;
                    break;
                }
            }

            if (found == true) {
                scoreOp = scoreOp + array.get(x).length();
                scoreOpposite.setText("Score: " + scoreOp);
                opposite.setText("");
                counter = counter + 1;
                wordsUsed.add(word);
                round.setText("Round: " + (counter + 1));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getWord();
                    }
                }, 1000);
            } else {
                Toast.makeText(this, "Try again with another word", Toast.LENGTH_SHORT).show();
                opposite.setText("");
            }
        } else {
            loadData();
            scoreInMemory = scoreInMemory + scoreOp;
            nrOfWordsInMemory = nrOfWordsInMemory + wordsUsed.size();
            saveData(scoreInMemory, nrOfWordsInMemory);

            Intent i = new Intent(Opposites.this, ResultsActivity5.class);
            i.putExtra("opScore", scoreOp);
            i.putStringArrayListExtra("opWords", wordsUsed);
            startActivity(i);
        }
    }

    public void saveData(int a, int b) {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("int1", a);
        editor.putInt("int2", b);
        //editor.putString(RANK, "");s

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        scoreInMemory = sharedPreferences.getInt("int1", 0);
        nrOfWordsInMemory = sharedPreferences.getInt("int2", 0);
    }

    public void loadData2() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs2", MODE_PRIVATE);
        myOption = sharedPreferences.getBoolean("set", true);
    }

    @Override
    public void onBackPressed() {
        loadData2();
        if (myOption) {
            Intent returnIntent = new Intent(Opposites.this, HowToPlay.class);
            returnIntent.putExtra("mode", "Antonyms");
            startActivity(returnIntent);
        } else {
            Intent returnIntent = new Intent(Opposites.this, MainActivity.class);
            startActivity(returnIntent);
        }
    }
}
