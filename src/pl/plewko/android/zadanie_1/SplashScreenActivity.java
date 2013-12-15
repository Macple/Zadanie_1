package pl.plewko.android.zadanie_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class SplashScreenActivity extends Activity {

	// Set Duration of the Splash Screen
	private final static long DELAY = 5000;
	private boolean isBackPressed = false;
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
                    	
                    	Log.d("splashThread", "MainActivity is running");
                    	Intent myIntent = new Intent(SplashScreenActivity.this,
        						MainActivity.class);
        				startActivity(myIntent);

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally {

                    finish();

                }
            }

        };

        splashThread.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBackPressed = true;
        Log.d("onKeyDown", "BACK key pressed: FINISHING THE APPLICATION");
        finish();
    }
}