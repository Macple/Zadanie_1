package pl.plewko.android.zadanie_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

/**
 * @author Maciej Plewko
 * @since December 6, 2013
 */
public class SplashScreenActivity extends Activity {

    private final static long DELAY = 5000;
    private volatile boolean isBackPressed = false;
    private Thread splashThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen);

        splashThread = new Thread() {
            public void run() {
                try {
                    sleep(DELAY);
                    if (!isBackPressed) {

                        Log.d("splashThread", "LoginActivity is running");
                        Intent myIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(myIntent);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }

        };

        splashThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (splashThread.getState() == Thread.State.TIMED_WAITING) {
            splashThread.interrupt();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Log.d("onKeyDown", "BACK key pressed: FINISHING THE APPLICATION");
        isBackPressed = true;
        splashThread.interrupt();
        finish();
    }
}