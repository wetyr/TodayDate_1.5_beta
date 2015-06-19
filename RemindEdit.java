package com.kelly.activity;


import com.kelly.*;
import com.kelly.entity.DateDay;
import com.kelly.util.ToDoDB;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class RemindEdit extends Activity{
	 private ToDoDB toDoDB;
	 private Cursor cursor;
	 private int _id;
	 private EditText et1;
	 private String strTimeNow,timeStr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.app_remind_edit);
	    //获取数据库
	    toDoDB=new ToDoDB(this);
	    cursor=toDoDB.selectRemind();
	    //获取intent传过来的_id
	    Intent intent=getIntent();
	    int arg2=intent.getIntExtra("ARG2", 0);
	    intent.getFlags();
	    cursor.moveToPosition(arg2);
	    _id=cursor.getInt(0);
	    String timeTv=cursor.getString(2);	    
		TextView tv2=(TextView)this.findViewById(R.id.remind_edit_tv2);
		Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/Roboto-MediumItalic.ttf");
		tv2.setTypeface(tf);
		tv2.setTextColor(getResources().getColor(R.color.red));
		tv2.setText(timeTv);
		
		DateDay dd=new DateDay(this);
		strTimeNow=("第"+dd.getWeedDay()+"周 "+dd.getDays1()+" "+"   "+dd.getMonth3()+"月"+dd.getDate()+"日 ");
		timeStr=dd.getCurrentTime();
		
		//ImageButton bt1=(ImageButton)findViewById(R.id.remind_edit_bt1);
	    ImageButton bt2=(ImageButton)findViewById(R.id.remind_edit_bt2);
	    et1=(EditText)findViewById(R.id.remind_edit_et1);
	    
	    et1.setText(cursor.getString(1));
	    //bt1.setText("确认");
	    //bt2.setText("删除");
	    //bt1.setOnClickListener(new ButtonListener1());
	    bt2.setOnClickListener(new ButtonListener2());	    
	}

	
	public class ButtonListener1 implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			editTodo();
			setResult(RESULT_OK);
			finish();
		}
		// TODO Auto-generated method stub
		
	}
   
	public class ButtonListener2 implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new AlertDialog.Builder(RemindEdit.this)
		.setTitle("注意")
			.setMessage("确定删除该条备忘?")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					deleteTodo();
					TodayDateRemind.cursor.requery();
					TodayDateRemind.lv.invalidateViews();
					finish();	
				}
			})
			 .setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			}).show();
		}
		
	}
	

	
	  private void editTodo()
	  { 
	    if (et1.getText().toString().equals("")){
	    	deleteTodo();}
	    
	    else {
	    	String remindTv=cursor.getString(1);
	    	if (!et1.getText().toString().equals(remindTv)) {/* 修改数据 */ 
	    
	    	toDoDB.updateRemind(_id, et1.getText().toString(),strTimeNow,timeStr); 
		    cursor.requery(); 
		    
		    cursor.close();
		    toDoDB.close();
		    _id = 0; 
	    	}
		}
	    
	    } 
	  private void deleteTodo()
	  {
	    if(_id == 0)
	    return;
	    /* 删除数据 */ 
	    toDoDB.delete(_id,"todo_table"); 
	    cursor.requery();
	    
	    cursor.close();
	    toDoDB.close();
	    _id = 0; 
	    } 
	  @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			//如果按下的是返回键，并且没有重复
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				editTodo();
				
				TodayDateRemind.cursor.requery();
				TodayDateRemind.lv.invalidateViews();
				finish();
				overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
				return false;
			}
			return false;
		}
}
