package pl.plewko.android.zadanie_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

public class SplashScreenActivity extends Activity {

	// Set Duration of the Splash Screen
	long Delay = 5000;
	private boolean isBackPressed = false;
	private Thread splashTread;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove the Title Bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Get the view from splash_screen.xml
		setContentView(R.layout.splash_screen);
		
		splashTread = new Thread() {
            public void run() {
                try {
                    sleep(5000);
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

        splashTread.start();

    }
	
	// Protects from running the application after the BACK key was pressed
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isBackPressed = true;
            Log.d("onKeyDown", "BACK key pressed: FINISHING THE APPLICATION");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}