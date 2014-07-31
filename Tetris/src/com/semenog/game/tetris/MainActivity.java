package com.semenog.game.tetris;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {
	GameView view;
	
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ChessType.ttf");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        TextView btnPlay = (TextView)findViewById(R.id.textView1);
        btnPlay.setTypeface(font);     
       // startService(new Intent(MainActivity.this,ConnectionService.class));
        btnPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {								
					Intent game = new Intent(MainActivity.this,Game.class);
					startActivity(game);					
				}       			
		});
       
      //For Chart Button By Balakrishna
       TextView btnchart = (TextView)findViewById(R.id.txt_chart);
        btnchart.setTypeface(font);
        btnchart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {					
					Intent chart = new Intent(MainActivity.this,Chart.class);
					startActivity(chart);					
				}       			
		});
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
