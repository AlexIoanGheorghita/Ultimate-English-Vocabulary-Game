package com.telasoft.ultimateenglishvocabularygame;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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

public class PvP extends AppCompatActivity {
    EditText charactersP1, charactersP2;
    TextView txtTurnPlayer, txtP1, txtP2, txtScoreP1, txtScoreP2, startWord, startWord2;
    Button btnUse4, btnGiveUp3;
    boolean turnPlayer1, flagP1, flagP2, validP1, validP2, winP1, winP2, beginningOfRound, usedP1, usedP2, myOption;
    AlertDialog.Builder builder;
    ArrayList<Character> wordArrayP1, wordArrayP2;
    ArrayList<String> wordsUsedP1Array, wordsUsedP2Array;
    String valueP1, valueP2;
    int wordsUsedP1, wordsUsedP2, scoreP1, scoreP2;
    char letterFromAI, myletter;
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
            setContentView(R.layout.activity_pv_p);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_pv_p_large);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        charactersP1 = (EditText) findViewById(R.id.charactersP1);
        charactersP2 = (EditText) findViewById(R.id.charactersP2);
        txtTurnPlayer = (TextView) findViewById(R.id.txtTurnPlayer);
        txtP1 = (TextView) findViewById(R.id.txtP1);
        txtP2 = (TextView) findViewById(R.id.txtP2);
        txtScoreP1 = (TextView) findViewById(R.id.txtScoreP1);
        txtScoreP2 = (TextView) findViewById(R.id.txtScoreP2);
        startWord = (TextView) findViewById(R.id.startWord);
        startWord2 = (TextView) findViewById(R.id.startWord2);
        btnUse4 = (Button) findViewById(R.id.btnUse4);
        btnGiveUp3 = (Button) findViewById(R.id.btnGiveUp3);
        wordsUsedP1 = 0;
        wordsUsedP2 = 0;
        scoreP1 = 0;
        scoreP2 = 0;
        builder = new AlertDialog.Builder(this);

        MoPubAdsHandlerBannerAndInterstitial adsHandler = new MoPubAdsHandlerBannerAndInterstitial(this, AddCheck.MySourceActivity);
        adsHandler.handleInterstitialAds();

        charactersP1.setEnabled(false);
        charactersP2.setEnabled(false);
        btnUse4.setEnabled(false);
        btnGiveUp3.setEnabled(false);
        charactersP1.setHintTextColor(getResources().getColor(R.color.gray));
        charactersP2.setHintTextColor(getResources().getColor(R.color.gray));
        btnGiveUp3.setTextColor(getResources().getColor(R.color.gray));
        btnUse4.setTextColor(getResources().getColor(R.color.gray));

        startWord.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myletter = chooseLetter();
                startWord.setText("Player 1, start your word with: " + myletter);
                beginningOfRound = true;
                turnPlayer1 = true;
                txtTurnPlayer.setText("Turn - Player 1");
                charactersP1.setEnabled(true);
                charactersP1.setHintTextColor(getResources().getColor(R.color.darkGray));
                btnUse4.setTextColor(getResources().getColor(R.color.black));
                btnUse4.setEnabled(true);
            }
        }, 1500);

        wordsUsedP1Array = new ArrayList<>();
        wordsUsedP2Array = new ArrayList<>();

        btnUse4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turnPlayer1 == true) {
                    boolean returnedFlagP1 = validateWordPlayer1();
                    if (returnedFlagP1 == false) {
                        builder.setMessage("You are not allowed to use characters that are different from letters or enter nothing at all.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Incorrect character(s) entered");
                        alert.show();
                        charactersP1.setText("");
                        charactersP1.requestFocus();
                    } else if (returnedFlagP1 == true) {
                        String wholeP1Word = charactersP1.getText().toString().toLowerCase();
                        ArrayList<Character> firstTwoLettersOfP1Word = new ArrayList<Character>();
                        if (wholeP1Word.length() > 1) {
                            firstTwoLettersOfP1Word.add(wholeP1Word.charAt(0));
                            firstTwoLettersOfP1Word.add(wholeP1Word.charAt(1));
                            if (beginningOfRound == true) {
                                if (wholeP1Word.charAt(0) == myletter) {
                                    boolean returnedValidityP1 = validateExistenceOfP1Word();
                                    if (returnedValidityP1 == true) {
                                        boolean returnedWinningP1 = winningP1();
                                        if (returnedWinningP1 == true) {
                                            startWord.setVisibility(View.INVISIBLE);
                                            wordsUsedP1 = wordsUsedP1 + 1;
                                            wordsUsedP1Array.add(wholeP1Word);
                                            scoreP1 = scoreP1 + wholeP1Word.length();
                                            txtScoreP1.setText("Score P1: " + scoreP1);
                                            Toast.makeText(PvP.this, "Player 1 won!", Toast.LENGTH_SHORT).show();
                                            Intent intentP1 = new Intent(PvP.this, ResultsActivity4.class);
                                            intentP1.putExtra("score3", scoreP1);
                                            intentP1.putExtra("nrOfWords", wordsUsedP1);
                                            intentP1.putStringArrayListExtra("array", wordsUsedP1Array);
                                            startActivity(intentP1);
                                        } else {
                                            usedP1 = false;
                                            wordsUsedP1Array.add(wholeP1Word);
                                            if (wordsUsedP1Array.size() > 1) {
                                                for (int x = 0; x <= wordsUsedP1Array.toArray().length - 2; x++) {
                                                    if (charactersP1.getText().toString().toLowerCase().equals(wordsUsedP1Array.get(x))) {
                                                        builder.setMessage("You are not allowed to use the same word more than once in a round.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                                        AlertDialog alert = builder.create();
                                                        alert.setTitle("Word already used");
                                                        alert.show();
                                                        charactersP1.setText("");
                                                        charactersP1.requestFocus();
                                                        usedP1 = true;
                                                    }
                                                }
                                            }
                                            if (usedP1 == false) {
                                                wordsUsedP1 = wordsUsedP1 + 1;
                                                scoreP1 = scoreP1 + wholeP1Word.length();
                                                txtScoreP1.setText("Score P1: " + scoreP1);
                                                turnPlayer1 = false;
                                                txtTurnPlayer.setText("Turn - Player 2");
                                                startWord2.setText("Player 2, start your word with: " + charactersP1.getText().toString().toLowerCase().charAt(charactersP1.getText().toString().toLowerCase().length()-2) + charactersP1.getText().toString().toLowerCase().charAt(charactersP1.getText().toString().toLowerCase().length()-1));
                                                charactersP1.setEnabled(false);
                                                charactersP2.setEnabled(true);
                                                btnGiveUp3.setEnabled(true);
                                                charactersP1.setHintTextColor(getResources().getColor(R.color.gray));
                                                charactersP1.setTextColor(getResources().getColor(R.color.gray));
                                                charactersP2.setHintTextColor(getResources().getColor(R.color.darkGray));
                                                btnGiveUp3.setTextColor(getResources().getColor(R.color.black));
                                                beginningOfRound = false;
                                                //startWord.setVisibility(View.INVISIBLE);
                                            } else {
                                                wordsUsedP1Array.remove(wordsUsedP1Array.size() - 1);
                                            }
                                        }
                                    } else {
                                        Toast.makeText(PvP.this, "Word entered is not in our database, or it does not exist", Toast.LENGTH_LONG).show();
                                        charactersP1.setText("");
                                        charactersP1.requestFocus();
                                    }
                                } else {
                                    builder.setMessage("Your word does not start with the letter from the AI.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.setTitle("Incorrect letter used");
                                    alert.show();
                                    charactersP1.setText("");
                                    charactersP1.requestFocus();
                                }
                            } else {
                                String P2word = charactersP2.getText().toString().toLowerCase();
                                if (firstTwoLettersOfP1Word.get(0).equals(P2word.charAt(P2word.length() - 2)) && firstTwoLettersOfP1Word.get(1).equals(P2word.charAt(P2word.length() - 1)) && !wholeP1Word.equals(charactersP2.getText().toString().toLowerCase())) {
                                    boolean returnedValidityP1 = validateExistenceOfP1Word();
                                    if (returnedValidityP1 == true) {
                                        boolean returnedWinningP1 = winningP1();
                                        if (returnedWinningP1 == true) {
                                            wordsUsedP1 = wordsUsedP1 + 1;
                                            wordsUsedP1Array.add(wholeP1Word);
                                            scoreP1 = scoreP1 + wholeP1Word.length();
                                            txtScoreP1.setText("Score P1: " + scoreP1);
                                            Toast.makeText(PvP.this, "Player 1 won!", Toast.LENGTH_SHORT).show();
                                            Intent intentP1 = new Intent(PvP.this, ResultsActivity4.class);
                                            intentP1.putExtra("score3", scoreP1);
                                            intentP1.putExtra("nrOfWords", wordsUsedP1);
                                            intentP1.putStringArrayListExtra("array", wordsUsedP1Array);
                                            startActivity(intentP1);
                                        } else {
                                            usedP1 = false;
                                            wordsUsedP1Array.add(wholeP1Word);
                                            if (wordsUsedP1Array.size() > 1) {
                                                for (int x = 0; x <= wordsUsedP1Array.toArray().length - 2; x++) {
                                                    if (charactersP1.getText().toString().toLowerCase().equals(wordsUsedP1Array.get(x))) {
                                                        builder.setMessage("You are not allowed to use the same word more than once in a round.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                                        AlertDialog alert = builder.create();
                                                        alert.setTitle("Word already used");
                                                        alert.show();
                                                        charactersP1.setText("");
                                                        charactersP1.requestFocus();
                                                        usedP1 = true;
                                                    }
                                                }
                                            }
                                            if (usedP1 == false) {
                                                wordsUsedP1 = wordsUsedP1 + 1;
                                                scoreP1 = scoreP1 + wholeP1Word.length();
                                                txtScoreP1.setText("Score P1: " + scoreP1);
                                                turnPlayer1 = false;
                                                startWord2.setText("Player 2, start your word with: " + charactersP1.getText().toString().toLowerCase().charAt(charactersP1.getText().toString().toLowerCase().length()-2) + charactersP1.getText().toString().toLowerCase().charAt(charactersP1.getText().toString().toLowerCase().length()-1));
                                                txtTurnPlayer.setText("Turn - Player 2");
                                                charactersP1.setEnabled(false);
                                                charactersP2.setEnabled(true);
                                                charactersP1.setHintTextColor(getResources().getColor(R.color.gray));
                                                charactersP1.setTextColor(getResources().getColor(R.color.gray));
                                                charactersP2.setHintTextColor(getResources().getColor(R.color.darkGray));
                                                charactersP2.setTextColor(getResources().getColor(R.color.darkGray));
                                            } else {
                                                wordsUsedP1Array.remove(wordsUsedP1Array.size() - 1);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            builder.setMessage("You are not allowed to use only one letter").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.setTitle("Not enough characters");
                            alert.show();
                            charactersP1.setText("");
                            charactersP1.requestFocus();
                        }
                    }
                } else if (turnPlayer1 == false) {
                    boolean returnedFlagP2 = validateWordPlayer2();
                    if (returnedFlagP2 == false) {
                        builder.setMessage("You are not allowed to use characters that are different from letters. You also cannot enter upper-case letters or nothing at all.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Incorrect character(s) entered");
                        alert.show();
                        charactersP2.setText("");
                        charactersP2.requestFocus();
                    } else if (returnedFlagP2 == true) {
                        String wholeP2Word = charactersP2.getText().toString().toLowerCase();
                        ArrayList<Character> firstTwoLettersOfP2Word = new ArrayList<Character>();
                        if (wholeP2Word.length() > 1) {
                            firstTwoLettersOfP2Word.add(wholeP2Word.charAt(0));
                            firstTwoLettersOfP2Word.add(wholeP2Word.charAt(1));
                            String P1word = charactersP1.getText().toString();
                            if (firstTwoLettersOfP2Word.get(0).equals(P1word.charAt(P1word.length() - 2)) && firstTwoLettersOfP2Word.get(1).equals(P1word.charAt(P1word.length() - 1)) && !wholeP2Word.equals(charactersP1.getText().toString().toLowerCase())) {
                                boolean returnedValidityP2 = validateExistenceOfP2Word();
                                if (returnedValidityP2 == true) {
                                    boolean returnedWinningP2 = winningP2();
                                    if (returnedWinningP2 == true) {
                                        wordsUsedP2 = wordsUsedP2 + 1;
                                        wordsUsedP2Array.add(wholeP2Word);
                                        scoreP2 = scoreP2 + wholeP2Word.length();
                                        txtScoreP2.setText("Score P2: " + scoreP2);
                                        Toast.makeText(PvP.this, "Player 2 won!", Toast.LENGTH_SHORT).show();
                                        Intent intentP2 = new Intent(PvP.this, ResultsActivity4.class);
                                        intentP2.putExtra("score3", scoreP2);
                                        intentP2.putExtra("nrOfWords", wordsUsedP2);
                                        intentP2.putStringArrayListExtra("array", wordsUsedP2Array);
                                        startActivity(intentP2);
                                    } else {
                                        usedP2 = false;
                                        wordsUsedP2Array.add(wholeP2Word);
                                        if (wordsUsedP2Array.size() > 1) {
                                            for (int x = 0; x <= wordsUsedP2Array.toArray().length - 2; x++) {
                                                if (charactersP2.getText().toString().toLowerCase().equals(wordsUsedP2Array.get(x))) {
                                                    builder.setMessage("You are not allowed to use the same word more than once in a round.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                                    AlertDialog alert = builder.create();
                                                    alert.setTitle("Word already used");
                                                    alert.show();
                                                    charactersP2.setText("");
                                                    charactersP2.requestFocus();
                                                    usedP2 = true;
                                                }
                                            }
                                        }
                                        if (usedP2 == false) {
                                            wordsUsedP2 = wordsUsedP2 + 1;
                                            scoreP2 = scoreP2 + wholeP2Word.length();
                                            txtScoreP2.setText("Score P2: " + scoreP2);
                                            turnPlayer1 = true;
                                            txtTurnPlayer.setText("Turn - Player 1");
                                            startWord.setText("Player 1, start your word with: " + charactersP2.getText().toString().toLowerCase().charAt(charactersP2.getText().toString().toLowerCase().length()-2) + charactersP2.getText().toString().toLowerCase().charAt(charactersP2.getText().toString().toLowerCase().length()-1));
                                            charactersP2.setEnabled(false);
                                            charactersP1.setEnabled(true);
                                            charactersP1.setHintTextColor(getResources().getColor(R.color.darkGray));
                                            charactersP1.setTextColor(getResources().getColor(R.color.darkGray));
                                            charactersP2.setHintTextColor(getResources().getColor(R.color.gray));
                                            charactersP2.setTextColor(getResources().getColor(R.color.gray));
                                        } else {
                                            wordsUsedP2Array.remove(wordsUsedP2Array.size() - 1);
                                        }
                                    }
                                } else {
                                    Toast.makeText(PvP.this, "Word entered is not in our database, or it does not exist", Toast.LENGTH_LONG).show();
                                    charactersP2.setText("");
                                    charactersP2.requestFocus();
                                }
                            }
                        } else {
                            builder.setMessage("You are not allowed to use only one letter").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.setTitle("Not enough characters");
                            alert.show();
                            charactersP2.setText("");
                            charactersP2.requestFocus();
                        }
                    }
                }
            }
        });

        btnGiveUp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turnPlayer1 == true) {
                    Toast.makeText(PvP.this, "Player 2 won!", Toast.LENGTH_SHORT).show();
                    Intent intentP2 = new Intent(PvP.this, ResultsActivity4.class);
                    intentP2.putExtra("score3", scoreP2);
                    intentP2.putExtra("nrOfWords", wordsUsedP2);
                    intentP2.putStringArrayListExtra("array", wordsUsedP2Array);
                    startActivity(intentP2);
                } else {
                    Toast.makeText(PvP.this, "Player 1 won!", Toast.LENGTH_SHORT).show();
                    Intent intentP1 = new Intent(PvP.this, ResultsActivity4.class);
                    intentP1.putExtra("score3", scoreP1);
                    intentP1.putExtra("nrOfWords", wordsUsedP1);
                    intentP1.putStringArrayListExtra("array", wordsUsedP1Array);
                    startActivity(intentP1);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        loadData2();
        if (myOption) {
            Intent returnIntent = new Intent(PvP.this, HowToPlay.class);
            returnIntent.putExtra("mode", "PvP");
            startActivity(returnIntent);
        } else {
            Intent returnIntent = new Intent(PvP.this, MainActivity.class);
            startActivity(returnIntent);
        }
    }

    public void loadData2() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs2", MODE_PRIVATE);
        myOption = sharedPreferences.getBoolean("set", true);
    }

    public char chooseLetter() {
        char[] listOfLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
         'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        long randomLetter = Math.round(Math.random()*listOfLetters.length-1);
        int position = (int) randomLetter;

        for (int i = 0; i<=listOfLetters.length-1; i++) {
            if (position == i) {
                letterFromAI = listOfLetters[i];
            }
        }
        return letterFromAI;
    }

    public Boolean validateWordPlayer1() {
        final String word = charactersP1.getText().toString().toLowerCase();
        wordArrayP1 = new ArrayList<Character>();
        if (word.equals("")) {
            flagP1 = false;
            //builder.setMessage("You need to enter a word to continue playing.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            //    @Override
            //    public void onClick(DialogInterface dialog, int which) {
            //        dialog.cancel();
            //    }
            //});
            //AlertDialog alert = builder.create();
            //alert.setTitle("No word entered");
            //alert.show();
            //charactersP1.setText("");
            //charactersP1.requestFocus();
        } else {
            for (int i = 0; i <= word.length() - 1; i++) {
                wordArrayP1.add(word.charAt(i));
            }
            for (int i = 0; i <= wordArrayP1.size() - 1; i++) {
                switch (wordArrayP1.get(i).toString()) {
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
                        flagP1 = true;
                        break;
                    default:
                        flagP1 = false;
                }
            }
        }
        return flagP1;
    }

    public Boolean validateExistenceOfP1Word() {
        validP1 = false;
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

        String myWord = charactersP1.getText().toString().toLowerCase();
        ArrayList<Character> firstLetterP1 = new ArrayList<Character>();
        firstLetterP1.add(myWord.charAt(0));
        //Toast.makeText(this, "First letter: " + firstLetterP1, Toast.LENGTH_SHORT).show();
        switch (firstLetterP1.get(0).toString()) {
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
                validP1 = true;
                break;
            }
            x++;
        }
        return validP1;
    }

    public Boolean winningP1() {
        String myWord = charactersP1.getText().toString().toLowerCase();
        ArrayList<Character> winningListP1 = new ArrayList<Character>();
        for (int x = 0; x <= myWord.length() -1; x++) {
            winningListP1.add(myWord.charAt(x));
        }

        valueP1 = winningListP1.get(winningListP1.size() - 2).toString() + winningListP1.get(winningListP1.size() - 1).toString();

        winP1 = false;
        for (String item: endingLetters) {
            if (valueP1.equals(item)) {
                winP1 = true;
                break;
            }
        }
        return winP1;
    }

    public Boolean validateWordPlayer2() {
        final String word2 = charactersP2.getText().toString().toLowerCase();
        wordArrayP2 = new ArrayList<Character>();
        if (word2.equals("")) {
            flagP2 = false;
            //builder.setMessage("You need to enter a word to continue playing.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            //    @Override
            //    public void onClick(DialogInterface dialog, int which) {
            //        dialog.cancel();
            //    }
            //});
            //AlertDialog alert = builder.create();
            //alert.setTitle("No word entered");
            //alert.show();
            //charactersP2.setText("");
            //charactersP2.requestFocus();
        } else if (!TextUtils.isEmpty(word2)) {
            for (int i = 0; i <= word2.length() - 1; i++) {
                wordArrayP2.add(word2.charAt(i));
            }
            for (int i = 0; i <= wordArrayP2.size() - 1; i++) {
                switch (wordArrayP2.get(i).toString()) {
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
                        flagP2 = true;
                        break;
                    default:
                        flagP2 = false;
                }
            }
        }
        return flagP2;
    }

    public Boolean validateExistenceOfP2Word() {
        validP2 = false;
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

        String myWord2 = charactersP2.getText().toString().toLowerCase();
        ArrayList<Character> firstLetterP2 = new ArrayList<Character>();
        firstLetterP2.add(myWord2.charAt(0));
        //Toast.makeText(this, "First letter: " + firstLetterP2, Toast.LENGTH_SHORT).show();
        switch (firstLetterP2.get(0).toString()) {
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
            if (myWord2.equals(item)) {
                validP2 = true;
                break;
            }
            x++;
        }
        return validP2;
    }

    public Boolean winningP2() {
        String myWord2 = charactersP2.getText().toString().toLowerCase();
        ArrayList<Character> winningListP2 = new ArrayList<Character>();
        for (int x = 0; x <= myWord2.length() -1; x++) {
            winningListP2.add(myWord2.charAt(x));
        }

        valueP2 = winningListP2.get(winningListP2.size() - 2).toString() + winningListP2.get(winningListP2.size() - 1).toString();

        winP2 = false;
        for (String item: endingLetters) {
            if (valueP2.equals(item)) {
                winP2 = true;
                break;
            }
        }
        return winP2;
    }
}
