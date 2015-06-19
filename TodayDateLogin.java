package com.kelly.activity;

import java.util.Timer;
import com.kelly.*;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TodayDateLogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.app_login);
		
		final Intent intent = new Intent(this, Main.class);   
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		       @Override
		       public void run() {
		    	   startActivity(intent);
		    	   overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                   finish();
                   overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);  
		       }
		  };timer.schedule(task, 1000 * 2  ); 
            }


}


	
    

