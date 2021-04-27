package com.telasoft.ultimateenglishvocabularygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class Bonanza extends AppCompatActivity {

    Button btnUse3;
    EditText words;
    TextView txtTimer, txtDescription2, txtScore3;
    char letterChosen;
    int myScore, nrOfWords, scoreInMemory, nrOfWordsInMemory;
    public int counter;
    String max, min;
    ArrayList<String> repeatedWords;
    CountDownTimer timer;
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
            setContentView(R.layout.activity_bonanza);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_bonanza_large);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        btnUse3 = (Button) findViewById(R.id.btnUse3);
        words = (EditText) findViewById(R.id.words);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtDescription2 = (TextView) findViewById(R.id.txtDescription2);
        txtScore3 = (TextView) findViewById(R.id.txtScore3);
        myScore = 0;
        nrOfWords = 0;
        max = "";
        min = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        repeatedWords = new ArrayList<String>();

        MoPubAdsHandlerBannerAndInterstitial adsHandler = new MoPubAdsHandlerBannerAndInterstitial(this, AddCheck.MySourceActivity);
        adsHandler.handleInterstitialAds();

        final Intent intent = getIntent();

        letterChosen = generateLetter();
        txtDescription2.setText("Letter chosen by AI: " + letterChosen);

        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimer.setText(String.valueOf(counter));
                counter++;
            }

            @Override
            public void onFinish() {
                Toast.makeText(Bonanza.this, "FINISHED", Toast.LENGTH_SHORT).show();
                btnUse3.setEnabled(false);
                loadData();
                scoreInMemory = scoreInMemory + myScore;
                nrOfWordsInMemory = nrOfWordsInMemory + nrOfWords;
                saveData(scoreInMemory, nrOfWordsInMemory);
                Intent intent1 = new Intent(Bonanza.this, ResultsActivity3.class);
                intent1.putExtra("max", max);
                intent1.putExtra("min", min);
                intent1.putExtra("score4", myScore);
                intent1.putExtra("nrOfWords3", nrOfWords);
                startActivity(intent1);
            }
        }.start();

        btnUse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateWords();
            }
        });
    }

    public void MyCounter() {

    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        loadData2();
        if (myOption) {
            Intent returnIntent = new Intent(Bonanza.this, HowToPlay.class);
            returnIntent.putExtra("mode", "Bonanza");
            startActivity(returnIntent);
        } else {
            Intent returnIntent = new Intent(Bonanza.this, MainActivity.class);
            startActivity(returnIntent);
        }
    }

    public char generateLetter() {
        char letter = 'a';
        long randomNum = Math.round(Math.random() * 25);
        int randomListNum = (int) randomNum;
        switch (randomListNum) {
            case 0:
                letter = 'a';
                break;
            case 1:
                letter = 'b';
                break;
            case 2:
                letter = 'c';
                break;
            case 3:
                letter = 'd';
                break;
            case 4:
                letter = 'e';
                break;
            case 5:
                letter = 'f';
                break;
            case 6:
                letter = 'g';
                break;
            case 7:
                letter = 'h';
                break;
            case 8:
                letter = 'i';
                break;
            case 9:
                letter = 'j';
                break;
            case 10:
                letter = 'k';
                break;
            case 11:
                letter = 'l';
                break;
            case 12:
                letter = 'm';
                break;
            case 13:
                letter = 'n';
                break;
            case 14:
                letter = 'o';
                break;
            case 15:
                letter = 'p';
                break;
            case 16:
                letter = 'q';
                break;
            case 17:
                letter = 'r';
                break;
            case 18:
                letter = 's';
                break;
            case 19:
                letter = 't';
                break;
            case 20:
                letter = 'u';
                break;
            case 21:
                letter = 'v';
                break;
            case 22:
                letter = 'w';
                break;
            case 23:
                letter = 'x';
                break;
            case 24:
                letter = 'y';
                break;
            case 25:
                letter = 'z';
                break;
            default:
        }
        return letter;
    }

    public void validateWords() {
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

        switch (letterChosen) {
            case 'a':
                listValue = Arrays.copyOf(lista, lista.length);
                break;
            case 'b':
                listValue = Arrays.copyOf(listb, listb.length);
                break;
            case 'c':
                listValue = Arrays.copyOf(listc, listc.length);
                break;
            case 'd':
                listValue = Arrays.copyOf(listd, listd.length);
                break;
            case 'e':
                listValue = Arrays.copyOf(liste, liste.length);
                break;
            case 'f':
                listValue = Arrays.copyOf(listf, listf.length);
                break;
            case 'g':
                listValue = Arrays.copyOf(listg, listg.length);
                break;
            case 'h':
                listValue = Arrays.copyOf(listh, listh.length);
                break;
            case 'i':
                listValue = Arrays.copyOf(listi, listi.length);
                break;
            case 'j':
                listValue = Arrays.copyOf(listj, listj.length);
                break;
            case 'k':
                listValue = Arrays.copyOf(listk, listk.length);
                break;
            case 'l':
                listValue = Arrays.copyOf(listl, listl.length);
                break;
            case 'm':
                listValue = Arrays.copyOf(listm, listm.length);
                break;
            case 'n':
                listValue = Arrays.copyOf(listn, listn.length);
                break;
            case 'o':
                listValue = Arrays.copyOf(listo, listo.length);
                break;
            case 'p':
                listValue = Arrays.copyOf(listp, listp.length);
                break;
            case 'q':
                listValue = Arrays.copyOf(listq, listq.length);
                break;
            case 'r':
                listValue = Arrays.copyOf(listr, listr.length);
                break;
            case 's':
                listValue = Arrays.copyOf(lists, lists.length);
                break;
            case 't':
                listValue = Arrays.copyOf(listt, listt.length);
                break;
            case 'u':
                listValue = Arrays.copyOf(listu, listu.length);
                break;
            case 'v':
                listValue = Arrays.copyOf(listv, listv.length);
                break;
            case 'w':
                listValue = Arrays.copyOf(listw, listw.length);
                break;
            case 'x':
                listValue = Arrays.copyOf(listx, listx.length);
                break;
            case 'y':
                listValue = Arrays.copyOf(listy, listy.length);
                break;
            case 'z':
                listValue = Arrays.copyOf(listz, listz.length);
                break;
            default:
        }

        String myWord = words.getText().toString().toLowerCase();
        if (myWord.length() > 0 && !myWord.equals(" ")) {
            Character myWordsfirstLetter = words.getText().toString().toLowerCase().charAt(0);
            int found = 0;
            if (myWordsfirstLetter.equals(letterChosen)) {
                for (String item : listValue) {
                    if (myWord.equals(item)) {
                        repeatedWords.add(myWord);
                        found = 1;
                        break;
                    }
                }
            } else {
                found = 2;
            }
            if (repeatedWords.size() > 1) {
                for (int x = 0; x <= repeatedWords.size() - 2; x++) {
                    if (myWord.equals(repeatedWords.get(x).toString())) {
                        found = 0;
                        break;
                    }
                }
            }

            if (found == 1) {
                if (myWord.length() > max.length()) {
                    max = myWord;
                }
                if (myWord.length() < min.length()) {
                    min = myWord;
                }
                myScore = myScore + myWord.length();
                nrOfWords = nrOfWords + 1;
                txtScore3.setText("Score: " + myScore);
            } else if (found == 2) {
                Toast.makeText(this, "Word not found", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Word already used", Toast.LENGTH_SHORT).show();
                words.setText("");
                words.requestFocus();
            }

            words.setText("");
        } else {
            Toast.makeText(this, "Enter a word", Toast.LENGTH_SHORT).show();
        }
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
