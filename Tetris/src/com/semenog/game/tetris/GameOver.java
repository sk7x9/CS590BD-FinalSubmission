package com.semenog.game.tetris;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

// For Saving into Database by balakrishna


public class GameOver extends Activity {
	
	
	RegisterAdapter register;
	Game game=new Game();
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ChessType.ttf");
	        setContentView(R.layout.game_over);
	        
	      //For checking database connection
	        register=new RegisterAdapter(this); 
			register=register.open();
	        
	        TextView btnsavegesture = (TextView)findViewById(R.id.txt_gesturelog);	        
	        btnsavegesture.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {					 	
			    			    	
					game.getGesture(GameOver.this);
					Intent gameover = new Intent(GameOver.this,MainActivity.class);
					startActivity(gameover);					
					}       			
			});
	        
	 }

}
