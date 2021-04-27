package com.telasoft.ultimateenglishvocabularygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

import java.util.ArrayList;
import java.util.Arrays;

public class Main2Activity extends AppCompatActivity {
    EditText userWord;
    Button btnUse, btnGiveUp;
    TextView txtAI, txtDescription, txtWordChosenByAI, txtTurn, txtScore;
    AlertDialog.Builder builder;

    ArrayList<Character> wordArray;
    ArrayList<Character> aiArray;
    ArrayList<String> wordsUsedByUser;
    String[] endingLetters = {"aa", "ay", "bb", "bc", "bd", "bf", "bg", "bj", "bk", "bm", "bn", "bp", "bq", "bs", "bv", "bw", "bx", "bz",
            "cb", "cc", "cd", "cf", "cg", "cj", "ck", "cm", "cn", "cp", "cq", "cs", "ct", "cv", "cw", "cx", "cz", "db", "dc",
            "dd", "df", "dg", "dj", "dk", "dl", "dm", "dn", "dp", "dq", "ds", "dt", "dv", "dw", "dx", "dz", "eh",
            "ek", "ew", "ez", "fb", "fc", "fd", "ff", "fg", "fh", "fj", "fk", "fm", "fn", "fp", "fq", "fs", "ft",
            "fv", "fw", "fx", "fy", "fz", "gb", "gc", "gd", "gf", "gg", "gj", "gk", "gm", "gp", "gq", "gs", "gt",
            "gv", "gw", "gx", "gz", "hb", "hc", "hd", "hf", "hg", "hh", "hj", "hk", "hl", "hm", "hn", "hp", "hq",
            "hr", "hs", "ht", "hv", "hw", "hx", "hz", "ia", "ie", "if", "ig", "ih", "ii", "ij", "ik", "ip", "iq",
            "iu", "iw", "ix", "iy", "iz", "jb", "jc", "jd", "jf", "jg", "jh", "jj", "jk", "jl", "jm", "jn", "jp",
            "jq", "jr", "js", "jt", "jv", "jw", "jx", "jy", "jz", "kb", "kc", "kd", "kf", "kg", "kj", "kk", "kl",
            "km", "kp", "kq", "ks", "kt", "kv", "kw", "kx", "kz", "lb", "lc", "ld", "lf", "lg", "lh", "lj", "lk",
            "lm", "ln", "lp", "lq", "lr", "ls", "lt", "lv", "lw", "lx", "lz", "mb", "mc", "md", "mf", "mg", "mh",
            "mj", "mk", "ml", "mm", "mp", "mq", "mr", "ms", "mt", "mv", "mw", "mx", "mz", "nb", "nc", "nd", "nf",
            "ng", "nh", "nj", "nk", "nl", "nm", "nn", "np", "nq", "nr", "ns", "nt", "nv", "nw", "nx", "nz", "oh",
            "oj", "oq", "pb", "pc", "pd", "pf", "pg", "pj", "pk", "pm", "pp", "pq", "pt", "pv", "pw", "px", "pz",
            "qb", "qc", "qd", "qe", "qf", "qg", "qh", "qi", "qj", "qk", "ql", "qm", "qn", "qo", "qp", "qr", "qs",
            "qt", "qv", "qw", "qx", "qy", "qz", "rb", "rc", "rd", "rf", "rg", "rj", "rk", "rl", "rm", "rn", "rp",
            "rq", "rr", "rs", "rt", "rv", "rx", "rz", "sb", "sd", "sf", "sg", "sj", "sr", "ss", "sv", "sx", "sz",
            "tb", "tc", "td", "tf", "tg", "tj", "tk", "tl", "tm", "tn", "tp", "tq", "tt", "tv", "tx", "tz", "ua",
            "ub", "ud", "ue", "uf", "uh", "ui", "uj", "uo", "uq", "uu", "uv", "uw", "ux", "uy", "uz", "vb", "vc",
            "vd", "vf", "vg", "vh", "vj", "vk", "vl", "vm", "vn", "vp", "vq", "vr", "vs", "vt", "vv", "vw", "vx",
            "vy", "vz", "wb", "wc", "wd", "wf", "wg", "wj", "wk", "wl", "wm", "wn", "wp", "wq", "ws", "wt", "wu",
            "wv", "ww", "wx", "wy", "wz", "xa", "xb", "xc", "xd", "xf", "xg", "xh", "xi", "xj", "xk", "xl", "xm",
            "xn", "xo", "xp", "xq", "xr", "xs", "xt", "xu", "xv", "xw", "xx", "xz", "yb", "yc", "yd", "yf", "yg",
            "yh", "yj", "yk", "yl", "ym", "yn", "yp", "yq", "yr", "ys", "yv", "yw", "yx", "yy", "yz", "zb", "zc",
            "zd", "zf", "zg", "zh", "zj", "zk", "zl", "zm", "zn", "zp", "zq", "zr", "zs", "zt", "zv", "zw", "zx", "zz"};
    Boolean flag, turnAI, valid, win, endOfGame;
    int initialWordAI, score, scoreInMemory, NrOfWordsInMemory, newScore, oldScore, count;
    String valueAI, valueUser;
    boolean myOption;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String INT1 = "int1";
    public static final String INT2 = "int2";

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
            setContentView(R.layout.activity_main2);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_main_large2);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        userWord = (EditText) findViewById(R.id.characters);
        btnUse = (Button) findViewById(R.id.btnUse);
        btnGiveUp = (Button) findViewById(R.id.btnGiveUp);
        txtAI = (TextView) findViewById(R.id.txtAI);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtWordChosenByAI = (TextView) findViewById(R.id.textView5);
        txtTurn = (TextView) findViewById(R.id.txtTurn);
        txtScore = (TextView) findViewById(R.id.txtScore);
        builder = new AlertDialog.Builder(this);

        //MoPubAdsHandlerBannerAndInterstitial adsHandler = new MoPubAdsHandlerBannerAndInterstitial(this, AddCheck.MySourceActivity);
        //adsHandler.handleInterstitialAds();
        Intent intentFromHowToPlay = getIntent();

        userWord.setEnabled(false);
        btnUse.setEnabled(false);
        btnGiveUp.setEnabled(false);
        userWord.setTextColor(getResources().getColor(R.color.gray));
        userWord.setHintTextColor(getResources().getColor(R.color.gray));
        btnUse.setTextColor(getResources().getColor(R.color.gray));
        btnGiveUp.setTextColor(getResources().getColor(R.color.gray));
        txtTurn.setText("Turn - AI");
        endOfGame = false;
        score = 0;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                turnAI = true;
                initialWordAI = 0;
                String valueWord = generateRandomWord();
                txtWordChosenByAI.setText(valueWord);
                aiArray = new ArrayList<Character>();
                for (int x = 0; x <= valueWord.length() - 1; x++) {
                    aiArray.add(valueWord.charAt(x));
                }

                valueAI = aiArray.get(aiArray.size() - 2).toString() + aiArray.get(aiArray.size() - 1).toString();

                for (String item : endingLetters) {
                    if (valueAI.equals(item)) {
                        //Toast.makeText(Main2Activity.this, "Letters are not good", Toast.LENGTH_SHORT).show();
                        initialWordAI = 1;
                        break;
                    }
                }

                while (turnAI == true) {
                    if (initialWordAI == 1) {
                        initialWordAI = 0;
                        String newValueWord = generateRandomWord();
                        txtWordChosenByAI.setText(newValueWord);
                        aiArray = new ArrayList<Character>();
                        for (int x = 0; x <= newValueWord.length() - 1; x++) {
                            aiArray.add(newValueWord.charAt(x));
                        }

                        valueAI = aiArray.get(aiArray.size() - 2).toString() + aiArray.get(aiArray.size() - 1).toString();

                        for (String item : endingLetters) {
                            if (valueAI.equals(item)) {
                                //Toast.makeText(Main2Activity.this, "Letters are not good", Toast.LENGTH_SHORT).show();
                                initialWordAI = 1;
                                turnAI = true;
                                break;
                            }
                        }
                    } else {
                        turnAI = false;
                        txtDescription.setText("Your word should start with: " + valueAI);
                        userWord.setEnabled(true);
                        btnUse.setEnabled(true);
                        btnGiveUp.setEnabled(true);
                        userWord.setTextColor(getResources().getColor(R.color.darkGray));
                        userWord.setHintTextColor(getResources().getColor(R.color.darkGray));
                        btnUse.setTextColor(getResources().getColor(R.color.black));
                        btnGiveUp.setTextColor(getResources().getColor(R.color.black));
                        txtTurn.setText("Turn - You");
                    }
                }
            }
        }, 1800);

        turnAI = false;
        wordsUsedByUser = new ArrayList<String>();

        btnUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean returnedFlag = validateWordFromUser();
                if (returnedFlag == false) {
                    builder.setMessage("You are not allowed to use characters that are different from letters. You also cannot enter upper-case letters or nothing at all.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Incorrect character(s) entered");
                    alert.show();
                    userWord.setText("");
                    userWord.requestFocus();
                } else if (returnedFlag == true) {
                    String wholeUserWord = userWord.getText().toString().toLowerCase();
                    ArrayList<Character> firstTwoLettersOfUsersWord = new ArrayList<Character>();
                    firstTwoLettersOfUsersWord.add(wholeUserWord.charAt(0));
                    firstTwoLettersOfUsersWord.add(wholeUserWord.charAt(1));

                    if (firstTwoLettersOfUsersWord.get(0).equals(valueAI.charAt(0)) && firstTwoLettersOfUsersWord.get(1).equals(valueAI.charAt(1)) && !wholeUserWord.equals(txtWordChosenByAI.getText().toString())) {
                        Boolean returnedValidity = validateExistenceOfUsersWord();
                        if (returnedValidity == true) {
                            Boolean returnedWin = winning();
                            if (returnedWin == true) {
                                endOfGame = true;
                                wordsUsedByUser.add(userWord.getText().toString().toLowerCase());
                                score = score + userWord.getText().toString().toLowerCase().length();
                                txtScore.setText("Score: " + score);
                                loadData();
                                scoreInMemory = scoreInMemory + score;
                                NrOfWordsInMemory = NrOfWordsInMemory + wordsUsedByUser.size();
                                saveData(scoreInMemory, NrOfWordsInMemory);

                                Intent intentResults = new Intent(Main2Activity.this, ResultsActivity.class);
                                intentResults.putExtra("userScore", score);
                                intentResults.putStringArrayListExtra("userWords", wordsUsedByUser);
                                startActivity(intentResults);
                            } else {
                                turnAI = true;
                                wordsUsedByUser.add(userWord.getText().toString().toLowerCase());
                                if (wordsUsedByUser.size() > 1) {
                                    for (int x = 0; x<=wordsUsedByUser.toArray().length-2; x++) {
                                        if (userWord.getText().toString().toLowerCase().equals(wordsUsedByUser.get(x))) {
                                            //Toast.makeText(Main2Activity.this, "True", Toast.LENGTH_SHORT).show();
                                            wordsUsedByUser.remove(wordsUsedByUser.size() - 1);
                                            builder.setMessage("You are not allowed to use the same word more than once in a round.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            });
                                            AlertDialog alert = builder.create();
                                            alert.setTitle("Word already used");
                                            alert.show();
                                            userWord.setText("");
                                            userWord.requestFocus();
                                            turnAI = false;
                                        }
                                    }
                                }
                                if (turnAI == true) {
                                    endOfGame = false;
                                    score = score + userWord.getText().toString().toLowerCase().length();
                                    txtScore.setText("Score: " + score);
                                    //Toast.makeText(Main2Activity.this, "Word: " + userWord.getText().toString() + " added to list", Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(Main2Activity.this, "Length of list: " + wordsUsedByUser.size(), Toast.LENGTH_SHORT).show();
                                    turnOfAI();
                                }
                            }
                        } else {
                            Toast.makeText(Main2Activity.this, "Word not found", Toast.LENGTH_SHORT).show();
                            userWord.setText("");
                            userWord.requestFocus();
                        }
                    } else {
                        Toast.makeText(Main2Activity.this, "Your word needs to start with \"" + valueAI.charAt(0) + valueAI.charAt(1) + "\"", Toast.LENGTH_LONG).show();
                        userWord.setText("");
                        userWord.requestFocus();
                    }
                }
            }
        });

        btnGiveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wordsUsedByUser.size() == 0) {
                    builder.setMessage("Don't give up on your first try!").setCancelable(true).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Try again");
                    alert.show();
                    userWord.setText("");
                    userWord.requestFocus();
                } else {
                    endOfGame = true;
                    if (!userWord.getText().toString().toLowerCase().isEmpty()) {
                        wordsUsedByUser.add(userWord.getText().toString().toLowerCase());
                        score = score + userWord.getText().toString().toLowerCase().length();
                        txtScore.setText("Score: " + score);
                    }
                    loadData();
                    scoreInMemory = scoreInMemory + score;
                    NrOfWordsInMemory = NrOfWordsInMemory + wordsUsedByUser.size();
                    saveData(scoreInMemory, NrOfWordsInMemory);
                    Toast.makeText(Main2Activity.this, "GAME ENDED", Toast.LENGTH_LONG).show();
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intentResults = new Intent(Main2Activity.this, ResultsActivity.class);
                            intentResults.putExtra("userScore", score);
                            intentResults.putStringArrayListExtra("userWords", wordsUsedByUser);
                            startActivity(intentResults);
                        }
                    }, 1500);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        loadData2();
        if (myOption) {
            Intent returnIntent = new Intent(Main2Activity.this, HowToPlay.class);
            returnIntent.putExtra("mode", "PvAI");
            startActivity(returnIntent);
        } else {
            Intent returnIntent = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(returnIntent);
        }
    }

    // First move made by AI
    public String generateRandomWord() {
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

    public Boolean validateWordFromUser() {
        final String word = userWord.getText().toString().toLowerCase();
        wordArray = new ArrayList<Character>();
        if (word.equals("")) {
            flag = false;
            //builder.setMessage("You need to enter a word to continue playing.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            //    @Override
            //    public void onClick(DialogInterface dialog, int which) {
            //        dialog.cancel();
            //    }
            //});
            //AlertDialog alert = builder.create();
            //alert.setTitle("No word entered");
            //alert.show();
            //userWord.setText("");
            //userWord.requestFocus();
        } else {
            for (int i = 0; i <= word.length() - 1; i++) {
                wordArray.add(word.charAt(i));
            }
            for (int i = 0; i <= wordArray.size() - 1; i++) {
                switch (wordArray.get(i).toString()) {
                    case "a":
                    case "b":
                    case "c":
                    case "d":
                    case "e":
                    case "f":
                    case "g":
                    case "h":
                    case "i":
                    case "j":
                    case "k":
                    case "l":
                    case "m":
                    case "n":
                    case "o":
                    case "p":
                    case "q":
                    case "r":
                    case "s":
                    case "t":
                    case "u":
                    case "v":
                    case "w":
                    case "x":
                    case "y":
                    case "z":
                        flag = true;
                        break;
                    default:
                        flag = false;
                }
            }
        }
        return flag;
    }

    public Boolean validateExistenceOfUsersWord() {
        valid = false;
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

        String myWord = userWord.getText().toString().toLowerCase();
        ArrayList<Character> firstLetter = new ArrayList<Character>();
        firstLetter.add(myWord.charAt(0));
        //Toast.makeText(this, "First letter: " + firstLetter, Toast.LENGTH_SHORT).show();
        switch (firstLetter.get(0).toString()) {
            case "a":
                listValue = Arrays.copyOf(lista, lista.length);
                break;
            case "b":
                listValue = Arrays.copyOf(listb, listb.length);
                break;
            case "c":
                listValue = Arrays.copyOf(listc, listc.length);
                break;
            case "d":
                listValue = Arrays.copyOf(listd, listd.length);
                break;
            case "e":
                listValue = Arrays.copyOf(liste, liste.length);
                break;
            case "f":
                listValue = Arrays.copyOf(listf, listf.length);
                break;
            case "g":
                listValue = Arrays.copyOf(listg, listg.length);
                break;
            case "h":
                listValue = Arrays.copyOf(listh, listh.length);
                break;
            case "i":
                listValue = Arrays.copyOf(listi, listi.length);
                break;
            case "j":
                listValue = Arrays.copyOf(listj, listj.length);
                break;
            case "k":
                listValue = Arrays.copyOf(listk, listk.length);
                break;
            case "l":
                listValue = Arrays.copyOf(listl, listl.length);
                break;
            case "m":
                listValue = Arrays.copyOf(listm, listm.length);
                break;
            case "n":
                listValue = Arrays.copyOf(listn, listn.length);
                break;
            case "o":
                listValue = Arrays.copyOf(listo, listo.length);
                break;
            case "p":
                listValue = Arrays.copyOf(listp, listp.length);
                break;
            case "q":
                listValue = Arrays.copyOf(listq, listq.length);
                break;
            case "r":
                listValue = Arrays.copyOf(listr, listr.length);
                break;
            case "s":
                listValue = Arrays.copyOf(lists, lists.length);
                break;
            case "t":
                listValue = Arrays.copyOf(listt, listt.length);
                break;
            case "u":
                listValue = Arrays.copyOf(listu, listu.length);
                break;
            case "v":
                listValue = Arrays.copyOf(listv, listv.length);
                break;
            case "w":
                listValue = Arrays.copyOf(listw, listw.length);
                break;
            case "x":
                listValue = Arrays.copyOf(listx, listx.length);
                break;
            case "y":
                listValue = Arrays.copyOf(listy, listy.length);
                break;
            case "z":
                listValue = Arrays.copyOf(listz, listz.length);
                break;
            default:
        }
        int x = 0;
        for (String item : listValue) {
            if (myWord.equals(item)) {
                valid = true;
                break;
            }
            x++;
        }
        return valid;
    }

    public Boolean winning() {
        String myWord = userWord.getText().toString().toLowerCase();
        ArrayList<Character> winningList = new ArrayList<Character>();
        for (int x = 0; x <= myWord.length() -1; x++) {
            winningList.add(myWord.charAt(x));
        }

        valueUser = winningList.get(winningList.size() - 2).toString() + winningList.get(winningList.size() - 1).toString();

        win = false;
        for (String item: endingLetters) {
            if (valueUser.equals(item)) {
                win = true;
                break;
            }
        }
        return win;
    }

    public String respondToUser() {
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

        String aiWord = wordsUsedByUser.get(wordsUsedByUser.size()-1);
        ArrayList<Character> firstLetter = new ArrayList<Character>();
        firstLetter.add(aiWord.charAt(aiWord.length()-2));
        switch (firstLetter.get(0).toString()) {
            case "a":
                listValue = Arrays.copyOf(lista, lista.length);
                break;
            case "b":
                listValue = Arrays.copyOf(listb, listb.length);
                break;
            case "c":
                listValue = Arrays.copyOf(listc, listc.length);
                break;
            case "d":
                listValue = Arrays.copyOf(listd, listd.length);
                break;
            case "e":
                listValue = Arrays.copyOf(liste, liste.length);
                break;
            case "f":
                listValue = Arrays.copyOf(listf, listf.length);
                break;
            case "g":
                listValue = Arrays.copyOf(listg, listg.length);
                break;
            case "h":
                listValue = Arrays.copyOf(listh, listh.length);
                break;
            case "i":
                listValue = Arrays.copyOf(listi, listi.length);
                break;
            case "j":
                listValue = Arrays.copyOf(listj, listj.length);
                break;
            case "k":
                listValue = Arrays.copyOf(listk, listk.length);
                break;
            case "l":
                listValue = Arrays.copyOf(listl, listl.length);
                break;
            case "m":
                listValue = Arrays.copyOf(listm, listm.length);
                break;
            case "n":
                listValue = Arrays.copyOf(listn, listn.length);
                break;
            case "o":
                listValue = Arrays.copyOf(listo, listo.length);
                break;
            case "p":
                listValue = Arrays.copyOf(listp, listp.length);
                break;
            case "q":
                listValue = Arrays.copyOf(listq, listq.length);
                break;
            case "r":
                listValue = Arrays.copyOf(listr, listr.length);
                break;
            case "s":
                listValue = Arrays.copyOf(lists, lists.length);
                break;
            case "t":
                listValue = Arrays.copyOf(listt, listt.length);
                break;
            case "u":
                listValue = Arrays.copyOf(listu, listu.length);
                break;
            case "v":
                listValue = Arrays.copyOf(listv, listv.length);
                break;
            case "w":
                listValue = Arrays.copyOf(listw, listw.length);
                break;
            case "x":
                listValue = Arrays.copyOf(listx, listx.length);
                break;
            case "y":
                listValue = Arrays.copyOf(listy, listy.length);
                break;
            case "z":
                listValue = Arrays.copyOf(listz, listz.length);
                break;
            default:
        }

        ArrayList<Character> lastLetters = new ArrayList<Character>();
        lastLetters.add(aiWord.charAt(aiWord.length()-2));
        lastLetters.add(aiWord.charAt(aiWord.length()-1));


        int valueMin = 0;
        for (int x = 0; x<= listValue.length-1; x++) {
            try {
                if (listValue[x].charAt(1) == lastLetters.get(1)) {
                    valueMin = x;
                    //txtAI.setText(String.format("Word chosen by AI: %s", value));
                    //Toast.makeText(Main2Activity.this, "Value taken from lista: " + value, Toast.LENGTH_SHORT).show();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int myIncrementer = 0;
        for (int x = 0; x<= listValue.length-1; x++) {
            try {
                if (listValue[x].charAt(1) == lastLetters.get(1)) {
                    myIncrementer = myIncrementer + 1;
                    //txtAI.setText(String.format("Word chosen by AI: %s", value));
                    //Toast.makeText(Main2Activity.this, "Value taken from lista: " + value, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String value = "";
        long random = Math.round(Math.random() * (myIncrementer-1));
        for (int z = valueMin; z <= (valueMin+myIncrementer); z++) {
            if ((random + valueMin) == z) {
                value = listValue[z];
            }
        }
        return value;
    }

    public void turnOfAI() {
        if (turnAI == true) {
            userWord.setEnabled(false);
            btnUse.setEnabled(false);
            btnGiveUp.setEnabled(false);
            userWord.setTextColor(getResources().getColor(R.color.gray));
            userWord.setHintTextColor(getResources().getColor(R.color.gray));
            btnUse.setTextColor(getResources().getColor(R.color.gray));
            btnGiveUp.setTextColor(getResources().getColor(R.color.gray));
            txtTurn.setText("Turn - AI");

            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initialWordAI = 0;
                    String wordFromAI = respondToUser();
                    txtWordChosenByAI.setText(wordFromAI);
                    aiArray = new ArrayList<Character>();
                    for (int x = 0; x <= wordFromAI.length() - 1; x++) {
                        aiArray.add(wordFromAI.charAt(x));
                    }

                    valueAI = aiArray.get(aiArray.size() - 2).toString() + aiArray.get(aiArray.size() - 1).toString();

                    for (String item : endingLetters) {
                        if (valueAI.equals(item)) {
                            //Toast.makeText(Main2Activity.this, "Letters are not good", Toast.LENGTH_SHORT).show();
                            initialWordAI = 1;
                            break;
                        }
                    }

                    while (turnAI == true) {
                        if (initialWordAI == 1) {
                            initialWordAI = 0;
                            String newWordFromAI = respondToUser();
                            txtWordChosenByAI.setText(newWordFromAI);
                            aiArray = new ArrayList<Character>();
                            for (int x = 0; x <= newWordFromAI.length() - 1; x++) {
                                aiArray.add(newWordFromAI.charAt(x));
                            }

                            valueAI = aiArray.get(aiArray.size() - 2).toString() + aiArray.get(aiArray.size() - 1).toString();

                            for (String item : endingLetters) {
                                if (valueAI.equals(item)) {
                                    //Toast.makeText(Main2Activity.this, "Letters are not good", Toast.LENGTH_SHORT).show();
                                    initialWordAI = 1;
                                    turnAI = true;
                                    break;
                                }
                            }
                        } else {
                            turnAI = false;
                            txtDescription.setText("Your word should start with: " + valueAI);
                            userWord.setEnabled(true);
                            btnUse.setEnabled(true);
                            btnGiveUp.setEnabled(true);
                            userWord.setTextColor(getResources().getColor(R.color.darkGray));
                            userWord.setHintTextColor(getResources().getColor(R.color.darkGray));
                            btnUse.setTextColor(getResources().getColor(R.color.black));
                            btnGiveUp.setTextColor(getResources().getColor(R.color.black));
                            userWord.setText("");
                            txtTurn.setText("Turn - You");
                        }
                    }
                }
            }, 1800);
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
        NrOfWordsInMemory = sharedPreferences.getInt("int2", 0);
    }

    public void loadData2() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs2", MODE_PRIVATE);
        myOption = sharedPreferences.getBoolean("set", true);
    }
}
