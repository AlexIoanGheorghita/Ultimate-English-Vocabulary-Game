package com.telasoft.ultimateenglishvocabularygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class CompleteTheWord extends AppCompatActivity {
    TextView txtRound, txtScore2, missingWord;
    EditText characters;
    Button btnUse2, btnGiveUp2;
    int round, charsToMask, score2, wordsAnswered, scoreInMemory, nrOfWordsInMemory;
    ArrayList<String> storeMissingLetters, storeCompleteWord;
    Boolean turnUser;
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
            setContentView(R.layout.activity_complete_the_word);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_complete_the_word_large);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        txtRound = (TextView) findViewById(R.id.txtRound);
        txtScore2 = (TextView) findViewById(R.id.txtScore2);
        missingWord = (TextView) findViewById(R.id.missingWord);
        characters = (EditText) findViewById(R.id.characters2);
        btnUse2 = (Button) findViewById(R.id.btnUse2);
        btnGiveUp2 = (Button) findViewById(R.id.btnGiveUp2);
        round = 1;
        score2 = 0;
        wordsAnswered = 0;
        txtScore2.setText("Score: " + score2);

        MoPubAdsHandlerBannerAndInterstitial adsHandler = new MoPubAdsHandlerBannerAndInterstitial(this, AddCheck.MySourceActivity);
        adsHandler.handleInterstitialAds();

        //missingWord.setPaintFlags(missingWord.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Intent intentFromHome1 = getIntent();

        turnUser = false;
        characters.setEnabled(false);
        btnUse2.setEnabled(false);
        btnGiveUp2.setEnabled(false);
        characters.setTextColor(getResources().getColor(R.color.gray));
        characters.setHintTextColor(getResources().getColor(R.color.gray));
        btnUse2.setTextColor(getResources().getColor(R.color.gray));
        btnGiveUp2.setTextColor(getResources().getColor(R.color.gray));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String returnedValue = generateRandomWord2();
                switch (returnedValue.length()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        charsToMask = 1;
                        break;
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        charsToMask = 2;
                        break;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        charsToMask = 3;
                        break;
                    default:
                        charsToMask = 4;
                        break;
                }
                //if (returnedValue.length() <=5) {
                //    charsToMask = 1;
                //} else if (returnedValue.length() <=9) {
                //    charsToMask = 2;
                //} else if (returnedValue.length() <=12) {
                //    charsToMask = 3;
                //} else {
                //    charsToMask = 4;
                //}

                storeCompleteWord = new ArrayList<String>();
                storeMissingLetters = new ArrayList<String>();
                for (int x=0; x <= returnedValue.length()-1; x++) {
                    storeCompleteWord.add(String.valueOf(returnedValue.charAt(x)));
                }
                int arraySize = storeCompleteWord.size();
                switch (charsToMask) {
                    case 1:
                        long random = Math.round(Math.random() * arraySize-1);
                        int randomLetter = (int) random;
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        String word = "";
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            word = word.concat(storeCompleteWord.get(x));
                        }
                        missingWord.setText(word);
                        break;
                    case 2:
                        long random2 = Math.round(Math.random() * arraySize-1);
                        int randomLetter2 = (int) random2;
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter2 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        long random3 = Math.round(Math.random() * arraySize);
                        int randomLetter3 = (int) random3;
                        while (randomLetter2 == randomLetter3 || randomLetter2 > randomLetter3) {
                            random3 = Math.round(Math.random() * arraySize);
                            randomLetter3 = (int) random3;
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter3 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        word = "";
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            word = word.concat(storeCompleteWord.get(x));
                        }
                        missingWord.setText(word);
                        break;
                    case 3:
                        long random4 = Math.round(Math.random() * arraySize-2);
                        int randomLetter4 = (int) random4;
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter4 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        long random5 = Math.round(Math.random() * arraySize);
                        int randomLetter5 = (int) random5;
                        long random6 = Math.round(Math.random() * arraySize);
                        int randomLetter6 = (int) random6;
                        while ((randomLetter4 == randomLetter5) || (randomLetter5 == randomLetter6) || (randomLetter4 == randomLetter6) || (randomLetter4 > randomLetter5) || (randomLetter5 > randomLetter6)) {
                            random5 = Math.round(Math.random() * arraySize);
                            randomLetter5 = (int) random5;
                            random6 = Math.round(Math.random() * arraySize);
                            randomLetter6 = (int) random6;
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter5 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter6 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        word = "";
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            word = word.concat(storeCompleteWord.get(x));
                        }
                        missingWord.setText(word);
                        break;
                    case 4:
                        long random7 = Math.round(Math.random() * arraySize-3);
                        int randomLetter7 = (int) random7;
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter7 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        long random8 = Math.round(Math.random() * arraySize);
                        int randomLetter8 = (int) random8;
                        long random9 = Math.round(Math.random() * arraySize);
                        int randomLetter9 = (int) random9;
                        long random10 = Math.round(Math.random() * arraySize);
                        int randomLetter10 = (int) random10;
                        while ((randomLetter7 == randomLetter8) || (randomLetter8 == randomLetter9) || (randomLetter9 == randomLetter10) || (randomLetter7 == randomLetter10) || (randomLetter7 == randomLetter9) || (randomLetter8 == randomLetter10) ||
                                (randomLetter7 > randomLetter8) || (randomLetter8 > randomLetter9) || (randomLetter9 > randomLetter10)) {
                            random8 = Math.round(Math.random() * arraySize);
                            randomLetter8 = (int) random8;
                            random9 = Math.round(Math.random() * arraySize);
                            randomLetter9 = (int) random9;
                            random10 = Math.round(Math.random() * arraySize);
                            randomLetter10 = (int) random10;
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter8 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter9 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter10 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }

                        word = "";
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            word = word.concat(storeCompleteWord.get(x));
                        }
                        missingWord.setText(word);
                    default:
                }
                turnUser = true;
                characters.setEnabled(true);
                btnUse2.setEnabled(true);
                btnGiveUp2.setEnabled(true);
                characters.setTextColor(getResources().getColor(R.color.darkGray));
                characters.setHintTextColor(getResources().getColor(R.color.darkGray));
                btnUse2.setTextColor(getResources().getColor(R.color.black));
                btnGiveUp2.setTextColor(getResources().getColor(R.color.black));
                txtRound.setText("Round - " + round);
            }
        }, 1000);

        btnUse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (round <= 4) {
                    String letters = characters.getText().toString().toLowerCase();
                    String miss = "";
                    for (int x = 0; x <= storeMissingLetters.size() - 1; x++) {
                        miss = miss.concat(storeMissingLetters.get(x));
                    }
                    if (letters.equals(miss)) {
                        score2 = score2 + missingWord.length();
                        txtScore2.setText("Score: " + score2);
                        round = round + 1;
                        wordsAnswered = wordsAnswered + 1;
                        turnOfAI();
                    } else {
                        Toast.makeText(CompleteTheWord.this, "Incorrect letters", Toast.LENGTH_SHORT).show();
                        characters.setText("");
                    }
                } else {
                    loadData();
                    scoreInMemory = scoreInMemory + score2;
                    nrOfWordsInMemory = nrOfWordsInMemory + wordsAnswered;
                    saveData(scoreInMemory, nrOfWordsInMemory);

                    Intent newIntent = new Intent(CompleteTheWord.this, ResultsActivity2.class);
                    newIntent.putExtra("wordsAnswered", wordsAnswered);
                    newIntent.putExtra("myScore", score2);
                    startActivity(newIntent);
                }
            }
        });
        btnGiveUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (round <= 4) {
                    round = round + 1;
                    turnOfAI();
                } else {
                    loadData();
                    scoreInMemory = scoreInMemory + score2;
                    nrOfWordsInMemory = nrOfWordsInMemory + wordsAnswered;
                    saveData(scoreInMemory, nrOfWordsInMemory);

                    Intent newIntent = new Intent(CompleteTheWord.this, ResultsActivity2.class);
                    newIntent.putExtra("wordsAnswered", wordsAnswered);
                    newIntent.putExtra("myScore", score2);
                    startActivity(newIntent);
                }
            }
        });

        //Intent intentTowardsProfile = new Intent(CompleteTheWord.this, MainActivity.class);
        //startActivity(intentTowardsProfile);
    }

    @Override
    public void onBackPressed() {
        loadData2();
        if (myOption) {
            Intent returnIntent = new Intent(CompleteTheWord.this, HowToPlay.class);
            returnIntent.putExtra("mode", "CompleteWord");
            startActivity(returnIntent);
        } else {
            Intent returnIntent = new Intent(CompleteTheWord.this, MainActivity.class);
            startActivity(returnIntent);
        }
    }

    public String generateRandomWord2() {
        String[] lista = getResources().getStringArray(R.array.lista);
        String[] listb = getResources().getStringArray(R.array.listb);
        String[] listc = getResources().getStringArray(R.array.listc);
        String[] listd = getResources().getStringArray(R.array.listd);
        String[] liste = getResources().getStringArray(R.array.liste);
        String[] listf = getResources().getStringArray(R.array.listf);
        String[] listg = getResources().getStringArray(R.array.listg);
        String[] listh = getResources().getStringArray(R.array.listh);
        String[] listi = getResources().getStringArray(R.array.listi);
        String[] listj = getResources().getStringArray(R.array.listj);
        String[] listk = getResources().getStringArray(R.array.listk);
        String[] listl = getResources().getStringArray(R.array.listl);
        String[] listm = getResources().getStringArray(R.array.listm);
        String[] listn = getResources().getStringArray(R.array.listn);
        String[] listo = getResources().getStringArray(R.array.listo);
        String[] listp = getResources().getStringArray(R.array.listp);
        String[] listq = getResources().getStringArray(R.array.listq);
        String[] listr = getResources().getStringArray(R.array.listr);
        String[] lists = getResources().getStringArray(R.array.lists);
        String[] listt = getResources().getStringArray(R.array.listt);
        String[] listu = getResources().getStringArray(R.array.listu);
        String[] listv = getResources().getStringArray(R.array.listv);
        String[] listw = getResources().getStringArray(R.array.listw);
        String[] listx = getResources().getStringArray(R.array.listx);
        String[] listy = getResources().getStringArray(R.array.listy);
        String[] listz = getResources().getStringArray(R.array.listz);
        String[] listValue = new String[0];

        long randomNum = Math.round(Math.random() * 25);
        int randomListNum = (int) randomNum;
        //Toast.makeText(this, "randomListNum: " + randomListNum, Toast.LENGTH_SHORT).show();
        switch (randomListNum) {
            case 0:
                listValue = Arrays.copyOf(lista, lista.length);
                break;
            case 1:
                listValue = Arrays.copyOf(listb, listb.length);
                break;
            case 2:
                listValue = Arrays.copyOf(listc, listc.length);
                break;
            case 3:
                listValue = Arrays.copyOf(listd, listd.length);
                break;
            case 4:
                listValue = Arrays.copyOf(liste, liste.length);
                break;
            case 5:
                listValue = Arrays.copyOf(listf, listf.length);
                break;
            case 6:
                listValue = Arrays.copyOf(listg, listg.length);
                break;
            case 7:
                listValue = Arrays.copyOf(listh, listh.length);
                break;
            case 8:
                listValue = Arrays.copyOf(listi, listi.length);
                break;
            case 9:
                listValue = Arrays.copyOf(listj, listj.length);
                break;
            case 10:
                listValue = Arrays.copyOf(listk, listk.length);
                break;
            case 11:
                listValue = Arrays.copyOf(listl, listl.length);
                break;
            case 12:
                listValue = Arrays.copyOf(listm, listm.length);
                break;
            case 13:
                listValue = Arrays.copyOf(listn, listn.length);
                break;
            case 14:
                listValue = Arrays.copyOf(listo, listo.length);
                break;
            case 15:
                listValue = Arrays.copyOf(listp, listp.length);
                break;
            case 16:
                listValue = Arrays.copyOf(listq, listq.length);
                break;
            case 17:
                listValue = Arrays.copyOf(listr, listr.length);
                break;
            case 18:
                listValue = Arrays.copyOf(lists, lists.length);
                break;
            case 19:
                listValue = Arrays.copyOf(listt, listt.length);
                break;
            case 20:
                listValue = Arrays.copyOf(listu, listu.length);
                break;
            case 21:
                listValue = Arrays.copyOf(listv, listv.length);
                break;
            case 22:
                listValue = Arrays.copyOf(listw, listw.length);
                break;
            case 23:
                listValue = Arrays.copyOf(listx, listx.length);
                break;
            case 24:
                listValue = Arrays.copyOf(listy, listy.length);
                break;
            case 25:
                listValue = Arrays.copyOf(listz, listz.length);
                break;
            default:
        }

        int count = listValue.length;
        long random = Math.round(Math.random() * count); //new Random().nextInt(count);

        //Toast.makeText(Main2Activity.this, "Number of items: " + count + "\nRandom number: " + random, Toast.LENGTH_SHORT).show();
        int i = 0;
        String value = "";
        for (String item : listValue) {
            if (i == random) {
                value = item;
                //txtAI.setText(String.format("Word chosen by AI: %s", value));
                //Toast.makeText(Main2Activity.this, "Value taken from lista: " + value, Toast.LENGTH_SHORT).show();
                break;
            }
            i++;
        }
        return value;
    }


    public void turnOfAI() {
        characters.setText("");
        turnUser = false;
        characters.setEnabled(false);
        btnUse2.setEnabled(false);
        btnGiveUp2.setEnabled(false);
        characters.setTextColor(getResources().getColor(R.color.gray));
        characters.setHintTextColor(getResources().getColor(R.color.gray));
        btnUse2.setTextColor(getResources().getColor(R.color.gray));
        btnGiveUp2.setTextColor(getResources().getColor(R.color.gray));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String returnedValue = generateRandomWord2();
                switch (returnedValue.length()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        charsToMask = 1;
                        break;
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        charsToMask = 2;
                        break;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        charsToMask = 3;
                        break;
                    default:
                        charsToMask = 4;
                        break;
                }
                //if (returnedValue.length() <=5) {
                //    charsToMask = 1;
                //} else if (returnedValue.length() <=9) {
                //    charsToMask = 2;
                //} else if (returnedValue.length() <=12) {
                //    charsToMask = 3;
                //} else {
                //    charsToMask = 4;
                //}

                storeCompleteWord = new ArrayList<String>();
                storeMissingLetters = new ArrayList<String>();
                for (int x=0; x <= returnedValue.length()-1; x++) {
                    storeCompleteWord.add(String.valueOf(returnedValue.charAt(x)));
                }
                int arraySize = storeCompleteWord.size();
                switch (charsToMask) {
                    case 1:
                        long random = Math.round(Math.random() * arraySize-1);
                        int randomLetter = (int) random;
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        String word = "";
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            word = word.concat(storeCompleteWord.get(x));
                        }
                        missingWord.setText(word);
                        break;
                    case 2:
                        long random2 = Math.round(Math.random() * arraySize-1);
                        int randomLetter2 = (int) random2;
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter2 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        long random3 = Math.round(Math.random() * arraySize);
                        int randomLetter3 = (int) random3;
                        while (randomLetter2 == randomLetter3 || randomLetter2 > randomLetter3) {
                            random3 = Math.round(Math.random() * arraySize);
                            randomLetter3 = (int) random3;
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter3 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        word = "";
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            word = word.concat(storeCompleteWord.get(x));
                        }
                        missingWord.setText(word);
                        break;
                    case 3:
                        long random4 = Math.round(Math.random() * arraySize-2);
                        int randomLetter4 = (int) random4;
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter4 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        long random5 = Math.round(Math.random() * arraySize);
                        int randomLetter5 = (int) random5;
                        long random6 = Math.round(Math.random() * arraySize);
                        int randomLetter6 = (int) random6;
                        while ((randomLetter4 == randomLetter5) || (randomLetter5 == randomLetter6) || (randomLetter4 == randomLetter6) || (randomLetter4 > randomLetter5) || (randomLetter5 > randomLetter6)) {
                            random5 = Math.round(Math.random() * arraySize);
                            randomLetter5 = (int) random5;
                            random6 = Math.round(Math.random() * arraySize);
                            randomLetter6 = (int) random6;
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter5 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter6 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        word = "";
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            word = word.concat(storeCompleteWord.get(x));
                        }
                        missingWord.setText(word);
                        break;
                    case 4:
                        long random7 = Math.round(Math.random() * arraySize-3);
                        int randomLetter7 = (int) random7;
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter7 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        long random8 = Math.round(Math.random() * arraySize);
                        int randomLetter8 = (int) random8;
                        long random9 = Math.round(Math.random() * arraySize);
                        int randomLetter9 = (int) random9;
                        long random10 = Math.round(Math.random() * arraySize);
                        int randomLetter10 = (int) random10;
                        while ((randomLetter7 == randomLetter8) || (randomLetter8 == randomLetter9) || (randomLetter9 == randomLetter10) || (randomLetter7 == randomLetter10) || (randomLetter7 == randomLetter9) || (randomLetter8 == randomLetter10) ||
                                (randomLetter7 > randomLetter8) || (randomLetter8 > randomLetter9) || (randomLetter9 > randomLetter10)) {
                            random8 = Math.round(Math.random() * arraySize);
                            randomLetter8 = (int) random8;
                            random9 = Math.round(Math.random() * arraySize);
                            randomLetter9 = (int) random9;
                            random10 = Math.round(Math.random() * arraySize);
                            randomLetter10 = (int) random10;
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter8 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter9 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            if (randomLetter10 == x) {
                                storeMissingLetters.add(storeCompleteWord.get(x));
                                storeCompleteWord.set(x, "_");
                            }
                        }

                        word = "";
                        for (int x=0; x<=storeCompleteWord.size()-1; x++) {
                            word = word.concat(storeCompleteWord.get(x));
                        }
                        missingWord.setText(word);
                    default:
                }
                turnUser = true;
                characters.setEnabled(true);
                btnUse2.setEnabled(true);
                btnGiveUp2.setEnabled(true);
                characters.setTextColor(getResources().getColor(R.color.darkGray));
                characters.setHintTextColor(getResources().getColor(R.color.darkGray));
                btnUse2.setTextColor(getResources().getColor(R.color.black));
                btnGiveUp2.setTextColor(getResources().getColor(R.color.black));
                txtRound.setText("Round - " + round);
            }
        }, 1000);
    }

    public void saveData(int a, int b) {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("int1", a);
        editor.putInt("int2", b);
        //editor.putString(RANK, "");

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
}
