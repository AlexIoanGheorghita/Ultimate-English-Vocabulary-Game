package com.telasoft.ultimateenglishvocabularygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class HowToPlay extends AppCompatActivity {
    VideoView mVideoView;
    //TextView playDescription;
    Button btnNext;
    String mode, text;

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
            setContentView(R.layout.activity_how_to_play);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnPvAI.setWidth(240);
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons));
        } else {
            setContentView(R.layout.activity_how_to_play_large);
            //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_large));
            //textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.subtitle_large));
            //btnPvAI.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnPvP.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnCompleteWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
            //btnWordBonanza.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.buttons_large));
        }

        //playDescription = (TextView) findViewById(R.id.playDescription);
        btnNext = (Button) findViewById(R.id.btnNext);
        mVideoView = (VideoView)findViewById(R.id.video1);

        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");

        switch (mode) {
            case "PvAI":
                /*text = "The AI starts by choosing a random word for you. Afterwards, you need to answer with a word that starts with the last two letters of the AI's word. And so on. " +
                        "You win when the AI is unable to create a word out of the last two letters of your word. However, the purpose of the game is not to win as soon as possible." + "" +
                        "You are supposed to use as many words as you can before winning.";*/
                mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                MediaController mediaController = new MediaController(HowToPlay.this);
                                mVideoView.setMediaController(mediaController);
                                mediaController.setAnchorView(mVideoView);
                                mediaController.setPadding(0,0,0,70);
                            }
                        });
                    }
                });
                String uriPath2 = "android.resource://com.telasoft.ultimateenglishvocabularygame/"+R.raw.record1;
                Uri uri2 = Uri.parse(uriPath2);
                mVideoView.setVideoURI(uri2);
                mVideoView.requestFocus();
                mVideoView.start();
                mVideoView.canPause();
                break;
            case "PvP":
                /*text = "Play against a friend on the same device! One of you starts by writing a word that begins with the letter chosen by the AI. " +
                        "The other player then has to create a word starting with the last two letters of the first player's word. The objective of the game is not to accumulate points. " +
                        "This is a game mode that tests how well your English vocabulary is. " + "One of you will win when a player is blocked by the other's word.";*/
                mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                MediaController mediaController2 = new MediaController(HowToPlay.this);
                                mVideoView.setMediaController(mediaController2);
                                mediaController2.setAnchorView(mVideoView);
                                mediaController2.setPadding(0,0,0,70);
                            }
                        });
                    }
                });
                String uriPath3 = "android.resource://com.telasoft.ultimateenglishvocabularygame/"+R.raw.record2;
                Uri uri3 = Uri.parse(uriPath3);
                mVideoView.setVideoURI(uri3);
                mVideoView.requestFocus();
                mVideoView.start();
                mVideoView.canPause();
                break;
            case "CompleteWord":
                //text = "The AI will choose a word and hide some of its characters. However, the word's length will be visible. Your objective is to guess correctly the missing characters of the word.";
                mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                MediaController mediaController3 = new MediaController(HowToPlay.this);
                                mVideoView.setMediaController(mediaController3);
                                mediaController3.setAnchorView(mVideoView);
                                mediaController3.setPadding(0,0,0,70);
                            }
                        });
                    }
                });
                String uriPath4 = "android.resource://com.telasoft.ultimateenglishvocabularygame/"+R.raw.record3;
                Uri uri4 = Uri.parse(uriPath4);
                mVideoView.setVideoURI(uri4);
                mVideoView.requestFocus();
                mVideoView.start();
                mVideoView.canPause();
                break;
            case "Bonanza":
                //text = "The AI will choose a random letter. You have to write as many words as you know that start with that letter in a given time period.";
                mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                MediaController mediaController4 = new MediaController(HowToPlay.this);
                                mVideoView.setMediaController(mediaController4);
                                mediaController4.setAnchorView(mVideoView);
                                mediaController4.setPadding(0,0,0,70);
                            }
                        });
                    }
                });
                String uriPath5 = "android.resource://com.telasoft.ultimateenglishvocabularygame/"+R.raw.record4;
                Uri uri5 = Uri.parse(uriPath5);
                mVideoView.setVideoURI(uri5);
                mVideoView.requestFocus();
                mVideoView.start();
                mVideoView.canPause();
                break;
            case "Antonyms":
                mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                MediaController mediaController5 = new MediaController(HowToPlay.this);
                                mVideoView.setMediaController(mediaController5);
                                mediaController5.setAnchorView(mVideoView);
                                mediaController5.setPadding(0,0,0,70);
                            }
                        });
                    }
                });
                String uriPath6 = "android.resource://com.telasoft.ultimateenglishvocabularygame/"+R.raw.record5;
                Uri uri6 = Uri.parse(uriPath6);
                mVideoView.setVideoURI(uri6);
                mVideoView.requestFocus();
                mVideoView.start();
                mVideoView.canPause();
                break;
            default:
        }

        //playDescription.setText(text);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mode) {
                    case "PvAI":
                        Intent myIntent = new Intent(HowToPlay.this, ListWords.class);
                        startActivity(myIntent);
                        break;
                    case "PvP":
                        Intent myIntent2 = new Intent(HowToPlay.this, PvP.class);
                        startActivity(myIntent2);
                        break;
                    case "CompleteWord":
                        Intent myIntent3 = new Intent(HowToPlay.this, CompleteTheWord.class);
                        startActivity(myIntent3);
                        break;
                    case "Bonanza":
                        Intent myIntent4 = new Intent(HowToPlay.this, Bonanza.class);
                        startActivity(myIntent4);
                        break;
                    case "Antonyms":
                        Intent myIntent5 = new Intent(HowToPlay.this, Opposites.class);
                        startActivity(myIntent5);
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent returnToMain = new Intent(HowToPlay.this, MainActivity.class);
        startActivity(returnToMain);
    }
}
