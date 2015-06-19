package com.kelly.activity;


import java.util.ArrayList;
import com.kelly.*;
import com.kelly.entity.DateDay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.util.Log;
import android.view.View;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;


import android.os.Bundle;
import android.widget.AdapterView;


import android.widget.Button;
import android.widget.GridView;

import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class TodayDateSchedule extends Activity implements OnItemSelectedListener, OnItemClickListener,android.view.View.OnClickListener{

	private Integer[]array={R.drawable.mon_icon,R.drawable.tue_icon,R.drawable.wed_icon,R.drawable.thu_icon,R.drawable.fri_icon}; 

	
	/*public TodayDateSchedule()
	{
		toDoDB_Schedule = new ToDoDB_Schedule(this);
	}*/
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id)
	{
		//imageView.setImageResource(resIds[position]);
		Intent intent=new Intent();
		intent.setClass(this, ScheduleShow.class);
		intent.putExtra("POSITION", position+1);
		TodayDateSchedule.this.startActivity(intent);
		overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		//imageView.setImageResource(resIds[position]);
		Intent intent=new Intent();
		intent.setClass(this, ScheduleShow.class);
		intent.putExtra("POSITION", position+1);
		//Log.i("intent.putExtra", position+"");
		TodayDateSchedule.this.startActivity(intent);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_schedule);
		inti();
	}

	private void inti() {
		// TODO Auto-generated method stub
		DateDay dd=new DateDay(this);
		TextView tv1=(TextView )findViewById(R.id.schedule_tv1);
		TextView tv2=(TextView )findViewById(R.id.schedule_tv2);
		Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/Roboto-MediumItalic.ttf");
        tv1.setTypeface(tf);
        tv1.setTextColor(getResources().getColor(R.color.purple));
		tv1.setText(dd.getWeedDay()+"周 ");
		tv2.setText(dd.getDays1());
		tv2.setTextColor(getResources().getColor(R.color.blue));
		
		Button bt1=(Button)this.findViewById(R.id.app_schedule_bt1);
		bt1.setOnClickListener(this);
		
		
		GridView gridView = (GridView) findViewById(R.id.gridview);
		List<Map<String, Object>> cells = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < array.length; i++)
		{
			Map<String, Object> cell = new HashMap<String, Object>();
			cell.put("imageview", array[i]);
			cells.add(cell);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, cells,
				R.layout.cell, new String[]
				{ "imageview" }, new int[]
				{ R.id.cell_imageview });
		//Animation animation=(Animation) getResources().getAnimation(R.anim.scale_rotate);
		//gridView.startAnimation(animation);

		gridView.setAdapter(simpleAdapter);	
		
		gridView.setOnItemSelectedListener(this);
		gridView.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this, ScheduleInsert.class);
		TodayDateSchedule.this.startActivity(intent);
		
	}
	
	
	
    
       
}
