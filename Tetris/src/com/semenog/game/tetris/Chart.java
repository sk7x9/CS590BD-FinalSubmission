package com.semenog.game.tetris;

//For Chart Display By  Balakrishna

import java.text.SimpleDateFormat;
import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Chart extends Activity {	
	
	private String[] mMonth = new String[] {
			"2014-07-24", "2014-07-25" , "2014-07-26", "2014-07-27","2014-07-28","2014-07-29"
		};

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_report);
              	
        
        // Getting reference to the button btn_chart
        Button btnChart = (Button) findViewById(R.id.btnreport);
        
        // Defining click event listener for the button btn_chart
        OnClickListener clickListener = new OnClickListener() {			
			@Override
			public void onClick(View v) {			
									
				RegisterAdapter adpt = new RegisterAdapter(Chart.this);
				adpt.onDataRetrieve(Chart.this);
				openChart();
				
			}
		};
				
		// Setting event click listener for the button btn_chart of the MainActivity layout
		btnChart.setOnClickListener(clickListener);  
		
		// for Barchart
		Button btnBarChart = (Button) findViewById(R.id.btnbar);
        
        // Defining click event listener for the button btn_chart
        OnClickListener barclickListener = new OnClickListener() {			
			@Override
			public void onClick(View v) {			
									
				//RegisterAdapter adpt = new RegisterAdapter(Chart.this);
				//adpt.onDataRetrieve(Chart.this);
				openBarchart();
				
			}
		};
		
		btnBarChart.setOnClickListener(barclickListener);
		
		
		//for line chart
		
		
				Button btnLineChart = (Button) findViewById(R.id.btnline);
		        
		        // Defining click event listener for the button btn_chart
		        OnClickListener lineclickListener = new OnClickListener() {			
					@Override
					public void onClick(View v) {			
											
						//RegisterAdapter adpt = new RegisterAdapter(Chart.this);
						//adpt.onDataRetrieve(Chart.this);
						openLinechart();
						
					}
				};
				
				btnLineChart.setOnClickListener(lineclickListener);
    }
    
    private void openChart(){    	
    	
         
    	// Pie Chart Section Names
    	String[] code = new String[] {"Rotation", "Left", "Right", "Punch", "Down"};    	
    	
    	// Pie Chart Section Value
    	double[] distribution = { 20, 22, 37,3,13};
    	
    	
    	// Color of each Pie Chart Sections
    	int[] colors = { Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW,Color.CYAN };
    	
    	
    	// Instantiating CategorySeries to plot Pie Chart    	
    	CategorySeries distributionSeries = new CategorySeries(" Android version distribution as on October 1, 2012");    	    	
    	
    	for(int i=0 ;i < distribution.length;i++){
    		// Adding a slice with its values and name to the Pie Chart
    		distributionSeries.add(code[i], distribution[i]);
    	}   
    	
    	// Instantiating a renderer for the Pie Chart
    	DefaultRenderer defaultRenderer  = new DefaultRenderer();    	
    	for(int i = 0 ;i<distribution.length;i++){    		
    		SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
    		seriesRenderer.setColor(colors[i]);
    		seriesRenderer.setDisplayChartValues(true);
    		// Adding a renderer for a slice
    		defaultRenderer.addSeriesRenderer(seriesRenderer);
    	}
    	
    	//defaultRenderer.setChartTitle("Android version distribution as on October 1, 2012 ");
    	//defaultRenderer.setChartTitleTextSize(20);
    	//defaultRenderer.setZoomButtonsVisible(true);    	    		
    		
    	// Creating an intent to plot bar chart using dataset and multipleRenderer    	
    	Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "Tetris Gustures PieChart");    	
    	
    	// Start Activity
    	startActivity(intent);
    	
    }
    

    private void openBarchart()
    {
    
    	int[] x = { 0,1,2,3,4,5 };
    	int[] Rotate = { 2000,3000,2800,3500,3700,3800};
    	int[] Right = {2200, 2700, 2900,  3000, 3300, 3400 };
    	int[] Left = {2200, 2800, 2600, 3000, 3300, 3400 };
    	
    	
    	
    	
    	// Creating an  XYSeries for Rotate
    	//CategorySeries RotateSeries = new CategorySeries("Rotate");
    	XYSeries RotateSeries = new XYSeries("Rotate");
    	// Creating an  XYSeries for Rotate
    	XYSeries RightSeries = new XYSeries("Right");
    	XYSeries LeftSeries = new XYSeries("Left");
    	// Adding data to Rotate and Right Series
    	for(int i=0;i<x.length;i++){    		
    		RotateSeries.add(i,Rotate[i]);
    		RightSeries.add(i,Right[i]);
    		LeftSeries.add(i,Left[i]);
    	}
    	
    	
    	// Creating a dataset to hold each series
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    	// Adding Rotate Series to the dataset
    	dataset.addSeries(RotateSeries);
    	// Adding Right Series to dataset
    	dataset.addSeries(RightSeries);   
    	dataset.addSeries(LeftSeries); 
    	
    	
    	// Creating XYSeriesRenderer to customize RotateSeries
    	XYSeriesRenderer RotateRenderer = new XYSeriesRenderer();
    	RotateRenderer.setColor(Color.rgb(130, 130, 230));
    	RotateRenderer.setFillPoints(true);
    	RotateRenderer.setLineWidth(2);
    	RotateRenderer.setDisplayChartValues(true);
    	
    	// Creating XYSeriesRenderer to customize RightSeries
    	XYSeriesRenderer RightRenderer = new XYSeriesRenderer();
    	RightRenderer.setColor(Color.rgb(220, 80, 80));
    	RightRenderer.setFillPoints(true);
    	RightRenderer.setLineWidth(2);
    	RightRenderer.setDisplayChartValues(true); 
    	
    	XYSeriesRenderer LeftRenderer = new XYSeriesRenderer();
    	LeftRenderer.setColor(Color.rgb(220, 130, 80));
    	LeftRenderer.setFillPoints(true);
    	LeftRenderer.setLineWidth(2);
    	LeftRenderer.setDisplayChartValues(true); 
    	
    	// Creating a XYMultipleSeriesRenderer to customize the whole chart
    	XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
    	multiRenderer.setXLabels(0);
    	multiRenderer.setChartTitle("Rotate vs Right vs Left Chart");
    	multiRenderer.setXTitle("Days");
    	multiRenderer.setYTitle("Number of Gesture with multiple of hundreds");
    	multiRenderer.setZoomButtonsVisible(true);    	    	
    	for(int i=0; i< x.length;i++){
    		multiRenderer.addXTextLabel(i, mMonth[i]);    		
    	}    	
    	
    	
    	// Adding RotateRenderer and RightRenderer to multipleRenderer
    	// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
    	// should be same
    	multiRenderer.addSeriesRenderer(RotateRenderer);
    	multiRenderer.addSeriesRenderer(RightRenderer);
    	multiRenderer.addSeriesRenderer(LeftRenderer);
    	
    	// Creating an intent to plot bar chart using dataset and multipleRenderer    	
    	Intent intent = ChartFactory.getBarChartIntent(getBaseContext(), dataset, multiRenderer, Type.DEFAULT);
    	
    	// Start Activity
    	startActivity(intent);
    	
    }
    
    
    private void openLinechart()
    {
    	
    	int[] x = { 0,1,2,3,4,5 };
    	int[] Rotate = { 2000,3000,2800,3500,3700,3800};
    	int[] Right = {2200, 2700, 2900,  3000, 3300, 3400 };
    	int[] Left = {2200, 2800, 2600, 3000, 3300, 3400 };
    	
    	// Creating an  XYSeries for Rotate
    	XYSeries RotateSeries = new XYSeries("Rotate");
    	// Creating an  XYSeries for Rotate
    	XYSeries RightSeries = new XYSeries("Right");
    	XYSeries LeftSeries = new XYSeries("Left");
    	// Adding data to Rotate and Right Series
    	for(int i=0;i<x.length;i++){
    		RotateSeries.add(x[i], Rotate[i]);
    		RightSeries.add(x[i],Right[i]);
    		LeftSeries.add(x[i],Left[i]);
    	}
    	
    	// Creating a dataset to hold each series
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    	// Adding Rotate Series to the dataset
    	dataset.addSeries(RotateSeries);
    	// Adding Right Series to dataset
    	dataset.addSeries(RightSeries);   
    	dataset.addSeries(LeftSeries);
    	
    	
    	// Creating XYSeriesRenderer to customize RotateSeries
    	XYSeriesRenderer RotateRenderer = new XYSeriesRenderer();
    	RotateRenderer.setColor(Color.RED);
    	RotateRenderer.setPointStyle(PointStyle.CIRCLE);
    	RotateRenderer.setFillPoints(true);
    	RotateRenderer.setLineWidth(2);
    	RotateRenderer.setDisplayChartValues(true);
    	
    	// Creating XYSeriesRenderer to customize RightSeries
    	XYSeriesRenderer RightRenderer = new XYSeriesRenderer();
    	RightRenderer.setColor(Color.YELLOW);
    	RightRenderer.setPointStyle(PointStyle.CIRCLE);
    	RightRenderer.setFillPoints(true);
    	RightRenderer.setLineWidth(2);
    	RightRenderer.setDisplayChartValues(true);
    	
    	
    	XYSeriesRenderer LeftRenderer = new XYSeriesRenderer();
    	RightRenderer.setColor(Color.GREEN);
    	RightRenderer.setPointStyle(PointStyle.CIRCLE);
    	RightRenderer.setFillPoints(true);
    	RightRenderer.setLineWidth(2);
    	RightRenderer.setDisplayChartValues(true);
    	
    	// Creating a XYMultipleSeriesRenderer to customize the whole chart
    	XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
    	multiRenderer.setXLabels(0);
    	multiRenderer.setChartTitle("Rotate vs Right vs Left Chart");
    	multiRenderer.setXTitle("Daya");
    	multiRenderer.setYTitle("Number of Gesture with multiple of hundreds");
    	multiRenderer.setZoomButtonsVisible(true);    	    	
    	for(int i=0;i<x.length;i++){
    		multiRenderer.addXTextLabel(i+1, mMonth[i]);    		
    	}    	
    	
    	// Adding RotateRenderer and RightRenderer to multipleRenderer
    	// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
    	// should be same
    	multiRenderer.addSeriesRenderer(RotateRenderer);
    	multiRenderer.addSeriesRenderer(RightRenderer);
    	multiRenderer.addSeriesRenderer(LeftRenderer);
    	
    	// Creating an intent to plot line chart using dataset and multipleRenderer
    	Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);
    	
    	// Start Activity
    	startActivity(intent);
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}