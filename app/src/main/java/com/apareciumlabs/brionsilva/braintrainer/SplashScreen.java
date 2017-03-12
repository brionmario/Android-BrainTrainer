package com.apareciumlabs.brionsilva.braintrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * This Android application was designed and developed to be used as a basic Brain training game.
 *
 * @author  Brion Mario
 * @version 1.0
 * @since   2017-03-05
 */

public class SplashScreen extends AppCompatActivity {
    private final int DURATION =3000;
    private Thread mSplashThread;
    private ProgressBar progressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        mSplashThread = new Thread() {

            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(1000);
                        progressBar.setProgress(25);
                        wait(2000);
                        progressBar.setProgress(50);
                        wait(1500);
                        progressBar.setProgress(80);
                        wait(DURATION);
                    } catch (InterruptedException e) {
                    } finally {
                        progressBar.setProgress(100);
                        finish();
                        Intent intent = new Intent(getBaseContext(),
                                MainMenu.class);
                        startActivity(intent);
                    }
                }
            }

        };
        mSplashThread.start();
    }


}
