package com.semenog.game.tetris;


import java.text.SimpleDateFormat;
import java.util.Date;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class Game extends Activity {
	
	GameView view;
	RegisterAdapter register;
	public static int RotateCount;
	public static int LeftCount;
	public static int RightCount;
		
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		//Intent intent = getIntent();
		   startService(new Intent(this,ConnectionService.class));
		   
		   //For calling Register Adapter Class By Balakrishna
		   	register=new RegisterAdapter(this); 
			register=register.open();	
		   		  
		//Bundle extras = intent.getExtras();
		//this.maze = (Maze)getLastNonConfigurationInstance();
		
		view = new GameView(this);
		
		
		registerReceiver(receiver, new IntentFilter("myproject"));
		
		setContentView(view);
		
	}
	
	public void nextPage(){
		
			Intent i = new Intent(this,PlayActivity.class);
			startActivity(i);
			
	}
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i("receivig", "Broadcast signal");
			
			Bundle bundle = intent.getExtras();
			if (bundle!=null) {
				
				//extra data inserted into the fired intent
				String data = bundle.getString("data");
				Log.i("data in main class", data);
				
				
				if ("Rotate".equalsIgnoreCase(data)) {
					Log.i("Balkrishna", "Rotate Motion Recognition");					
					view.figure.rotate();
					RotateCount++;
				}
				
				else if ("Left".equalsIgnoreCase(data)) {
					Log.i("Balkrishna", "Left Motion Recognition");					
					--view.figure.pos.x;
					LeftCount++;
					//System.out.print(LeftCount);
				}
				
				else if ("Right".equalsIgnoreCase(data)) {
					Log.i("Balkrishna", "Right Motion Recognition");					
					++view.figure.pos.x;
					RightCount++;
					//System.out.print(RightCount);
				}	
				
			}else{
				Log.i("data in main class", "bundle null");
				//Toast.makeText(getApplicationContext(), "not", Toast.LENGTH_SHORT).show();
			}
			//handleResult(bundle);
		}

		
	};
	
	//public Object onRetainNonConfigurationInstance() {
		//return this.view;
	//}
	
	public void getGesture(Context cnt)
	{
		register=new RegisterAdapter(this); 
		Log.i("Balakr", "in get gesture");		
		int RotateMotionCount = RotateCount=2;
		int RightMotionCount = RightCount=3;
		int LeftMotionCount = LeftCount=4;		
		
		String Date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//register.onDataRetrieve(cnt); 		
		register.insertEntry(cnt,Date,RotateMotionCount,RightMotionCount,LeftMotionCount);
		
		RotateCount = RightCount = LeftCount = 0;
	}
}
