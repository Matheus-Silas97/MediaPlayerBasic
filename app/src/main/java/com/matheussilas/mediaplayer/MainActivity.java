package com.matheussilas.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.windofchange);
        starSeekVolume();
    }

    public void starSeekVolume() {

        seekVolume = findViewById(R.id.seekVolume);

        //Set AudioManager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //retrieve current volume from device
        int volumeMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeCurrent = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //Set Max Values SeekBar
        seekVolume.setMax(volumeMax);
        seekVolume.setProgress(volumeCurrent);

        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void playSong(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void pauseSong(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stopSong(View view) {
        if (mediaPlayer.isPlaying()) {
        }
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.windofchange);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    if (mediaPlayer != null && mediaPlayer.isPlaying()){
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
    }
}
