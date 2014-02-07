package com.jkahigiso.ngomastations;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import java.io.IOException;

/**
 * Created by Jean Kahigiso on 07-02-2014.
 */
public class Player extends Activity implements View.OnClickListener {

    private ProgressBar playSeekBar;

    private Button buttonPlay;

    private Button buttonStopPlay;

    private MediaPlayer player;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUIElements();

        initializeMediaPlayer();
    }

    private void initializeUIElements() {

        playSeekBar = (ProgressBar) findViewById(R.id.progressBar);
        playSeekBar.setMax(100);
        playSeekBar.setVisibility(View.INVISIBLE);

        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

        buttonStopPlay = (Button) findViewById(R.id.buttonStopPlay);
        buttonStopPlay.setEnabled(false);
        buttonStopPlay.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v == buttonPlay) {
            startPlaying();
        } else if (v == buttonStopPlay) {
            stopPlaying();
        }
    }

    private void startPlaying() {
        buttonStopPlay.setEnabled(true);
        buttonPlay.setEnabled(false);

        playSeekBar.setVisibility(View.VISIBLE);

        player.prepareAsync();

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                player.start();
            }
        });

    }

    private void stopPlaying() {
        if (player.isPlaying()) {
            player.stop();
            player.release();
            initializeMediaPlayer();
        }

        buttonPlay.setEnabled(true);
        buttonStopPlay.setEnabled(false);
        playSeekBar.setVisibility(View.INVISIBLE);
    }

    private void initializeMediaPlayer() {
        player = new MediaPlayer();
        try {
            //Kigali today OK
            player.setDataSource("http://70.87.43.26:8000/;stream.mp3&amp;13916693258&amp;duration=99999&amp;id=scplayer&amp;autostart=true");
            //Radio Salus OK
            //player.setDataSource("http://salus.nur.ac.rw:8006/saluslive");
            //Flash fm OK
            //player.setDataSource("http://imbaraga2.primcast.com:9482/;&lang=auto&codec=mp3&volume=100&introurl=&autoplay=true&tracking=true&jsevents=false&buffering=5&skin=http://www.flashfm.rw//modules/mod_shoutcastplayer/ffmp3-compact.xml&title=Radio%20Flash%2089.2%20fm");
            //Magic FM
            //player.setDataSource("http://85.25.164.57:80/;");


            //Radio Rwanda
            //player.setDataSource("http://85.25.154.48:80/;");
            //Isango start
            //player.setDataSource("");
            //Radio 10
            //player.setDataSource("http://static.infomaniak.ch/infomaniak/radio/html/radio10-876fm_player.html");


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.i("Buffering======>--++", "" + percent);
                playSeekBar.setSecondaryProgress(percent);
                Log.i("Buffering======>++", "" + percent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player.isPlaying()) {
            player.stop();
        }
    }

}
