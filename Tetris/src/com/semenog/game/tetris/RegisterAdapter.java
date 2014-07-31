package com.semenog.game.tetris;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;



public class RegisterAdapter {

	static final String DATABASE_NAME = "gesturelog"; 
	static final int DATABASE_VERSION = 1; 
	public static final int NAME_COLUMN = 1;
	
	
	static final String DATABASE_CREATE = "create table "+"REPORTDATA"+ 
	"( " +"ID"+" integer primary key autoincrement,"+ "DATE text,ROTATE integer,RIGHT integer,LEFT integer); "; 
	
	private final Context context;
	public  SQLiteDatabase db;	
	private DatabaseConnection dbcon; 
	
	public  RegisterAdapter(Context _context) 
	{ 
	context = _context; 	
	dbcon = new DatabaseConnection(context, DATABASE_NAME, null, DATABASE_VERSION); 
	
	}
	
	public  RegisterAdapter open() throws SQLException 
	{ 		
	db = dbcon.getWritableDatabase(); 
	return this; 
	} 

	public void close() 
	{ 
	db.close(); 
	} 
	
	public  SQLiteDatabase getDatabaseInstance() 
	{ 
	return db; 
	} 
	
	 
    public void insertEntry(Context cnt,String Date,int RotateMotionCount,int RightMotionCount,int LeftMotionCount)
    { 
    	Log.i("Balkrishna", "In Insert method");    	
    	ContentValues newValues = new ContentValues(); 
    	//Assign values for each row.    	
    	newValues.put("DATE", Date);    	
    	newValues.put("ROTATE",RotateMotionCount);
    	newValues.put("RIGHT",RightMotionCount);
    	newValues.put("LEFT",LeftMotionCount);
    	dbcon = new DatabaseConnection(cnt, DATABASE_NAME, null, DATABASE_VERSION);
    	db = dbcon.getWritableDatabase();    	
    	long id =db.insert("REPORTDATA", null, newValues);
    	
    	Log.i("Balkrishna", "After Inserting values row id as --->"+id);
    	Toast.makeText(cnt, "After Inserting values row id as --->"+id, Toast.LENGTH_SHORT).show();
    	Toast.makeText(cnt, "Inserted values ---> Rotatecount="+RotateMotionCount+"Rightcount="+RightMotionCount+"Leftcount="+LeftMotionCount, Toast.LENGTH_SHORT).show();
    	db.close();    		
    } 
    
    
public String onDataRetrieve(Context cnt)
    
    {
	
     DatabaseConnection connection = new DatabaseConnection(cnt);
    Cursor cursor = connection.getReadableDatabase().rawQuery("select * from REPORTDATA", null);
     System.out.println(cursor);
     Toast.makeText(cnt, "count -->"+cursor.getCount(), Toast.LENGTH_SHORT).show();
     cursor.moveToFirst();
     String str = "";
     while (cursor.moveToNext()) {
		//System.out.println(cursor.getString(0));
		str = "\n"+str + "    " + "    " + cursor.getString(1) + "    " + cursor.getString(2)+ "    " + cursor.getString(3)+ "    " + cursor.getString(4);
		//str = cursor.getString(2);
     }
     Toast.makeText(cnt, "Retrieved Gesture values -->"+str, Toast.LENGTH_SHORT).show();
    
    return str;
     
    }
    
       
}

