package com.kelly.activity;



import android.app.Activity;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.kelly.*;
import com.kelly.entity.DateDay;
import com.kelly.util.ToDoDB;
public class RemindInsert extends Activity{
	 private ToDoDB toDoDB;
	 private Cursor cursor;
	 private EditText et1;
	 private String strTimeNow,timeStr;
	 private int timeInt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.app_remind_insert);
	    //获取数据库
	    toDoDB=new ToDoDB(this);
	    cursor=toDoDB.selectRemind();
	    
	    DateDay dd=new DateDay(this);
		TextView tv2=(TextView)this.findViewById(R.id.remind_insert_tv2);
		strTimeNow=("第"+dd.getWeedDay()+"周 "+dd.getDays1()+" "+"   "+dd.getMonth3()+"月"+dd.getDate()+"日 ");
		Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/Roboto-MediumItalic.ttf");
		tv2.setTypeface(tf);
		tv2.setTextColor(getResources().getColor(R.color.blue));
		tv2.setText(strTimeNow);
		timeStr=dd.getCurrentTime();
			    
	    //ImageButton bt1=(ImageButton)findViewById(R.id.remind_insert_bt1);
	    ImageButton bt2=(ImageButton)findViewById(R.id.remind_insert_bt2);
	    
	    et1=(EditText)findViewById(R.id.remind_insert_et1);
    
	   // bt1.setText("确认");
	   // bt2.setText("返回");
	   
	    //bt1.setOnClickListener(new ButtonListener1());
	    bt2.setOnClickListener(new ButtonListener2());

	}  

	
	public class ButtonListener1 implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			addTodo();
			finish();
			overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
		}
		// TODO Auto-generated method stub
		
	}
	
	public class ButtonListener2 implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
			overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
		}
		// TODO Auto-generated method stub
		
	}
   
	
	private void addTodo() 
	  {
	    if (et1.getText().toString().equals("")) return;
	    /* 新增数据到数据库 */
	    toDoDB.insertRemind(et1.getText().toString(),strTimeNow,timeStr); 
	    /* 重新查询 */
	    cursor.requery();  
	    cursor.close();
	    toDoDB.close();
	    
	    //_id = 0; 
	    } 
	 
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//如果按下的是返回键，并且没有重复
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			addTodo();			
			TodayDateRemind.cursor.requery();
			TodayDateRemind.lv.invalidateViews();
			finish();
			overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
			return false;
		}
		return false;
	}
}
