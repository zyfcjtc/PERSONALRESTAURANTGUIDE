package ca.gbc.mobile.dgy.personalrestaurantguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



/*********************************
 *    Students:                  *
 * David Olano - ID: 100847924   *
 * Yafan Zhang - ID: 100816652   *
 * Gary  Chan  - ID: 100882663   *
 *                               *
 ********************************/
public class Splash extends Activity {


    private final int SPLASH_DISPLAY_LENGHT = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

		/* New Handler to start the Menu-Activity
		 * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
				/* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}