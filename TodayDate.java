package com.kelly.activity;


import com.kelly.activity.R;
import com.kelly.activity.TodayDateLogin;
import com.kelly.entity.DateDay;
import com.kelly.util.ToDoDB;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;





import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


public class TodayDate extends AppWidgetProvider 
{ 
	private static int[] bitmapid={R.drawable.number_0,R.drawable.number_1,R.drawable.number_2,R.drawable.number_3,R.drawable.number_4,R.drawable.number_5,R.drawable.number_6,R.drawable.number_7,R.drawable.number_8,R.drawable.number_9,};
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		//Log.i("onUpdate", "已进入TD的onUpdate");
		final int N=appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			updateWidget(appWidgetManager, context, appWidgetIds[i]);
		}
				
		//super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	 @Override //这个onReceive还是沿用旧方法
	public void onReceive(Context context, Intent intent) {
		   // TODO Auto-generated method stub
		   super.onReceive(context, intent);
		   if (intent.getAction().equalsIgnoreCase("android.appwidget.action.APPWIDGET_UPDATE")) {
			        //Log.i("onReceive","td已进入onReceive");
			        /*DateDay dd=new DateDay(context);
				    String month = dd.getMonth() + " " + dd.getYear();
				    String date =dd.getDate();
				    String weekDay;
				    weekDay ="第"+dd.getWeedDay()+"周"+" "+dd.getDays1();
				    
				    
				    RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			    	views.setTextViewText(R.id.Month, month);
				    views.setTextViewText(R.id.Date, date);
			    	views.setTextViewText(R.id.WeekDay, weekDay);

 
			 		AppWidgetManager appWidgetManger=AppWidgetManager.getInstance(context);
					int[] appIds=appWidgetManger.getAppWidgetIds(new ComponentName(context,TodayDate.class));
					appWidgetManger.updateAppWidget(appIds, views);*/
			    Time time=new Time();
			    time .setToNow();
			    RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.widget_layout_4x1);
//			    String weekDay;
			    
			    DateDay dd=new DateDay(context);
//			    weekDay =dd.getWeedDay();//+" "+dd.getDays1();
			    
			    
			    //String month = dd.getMonth() + " " + dd.getYear();
			    //String date =dd.getDate();
			    String WEEK=time.weekDay+"";
			    if (WEEK.equals("0")||WEEK.equals("6")) {
				
				}
			    else {
					
			    
			    String []course=new String[5];
				String []add=new String[5];
			    
			    
			    Cursor cursor; 
			    SQLiteDatabase db;
			     ToDoDB toDoDB=new ToDoDB(context);
				 db=toDoDB.getReadableDatabase();
				 String sql="select * from todo_schedule where todo_week="+WEEK;
				 cursor =db.rawQuery(sql, null);
				 Log.i("", sql);
				//判断游标是否为空  
				 if(cursor !=null ){  
					 int i=0,n=cursor .getCount();
					 cursor .moveToFirst();
					 Log.i("", "cursor !=null");
					 Log.i("n=?", n+"");
					
		             //遍历游标  11.       
					  while (!cursor .isAfterLast()) {  
						 
						  //获得ID            
						  //int id = mCursor .getInt(0);          
						  //获得课程            
						  course[i]=cursor .getString(3); 
						  Log.i("", cursor .getString(3));
						  //获得地址           
						  add[i]=cursor .getString(4);
						  i++;
						  cursor .moveToNext();
					   }
				 }
			    db.close();
			    cursor.close();
			    
			    views.setTextViewText(R.id.textView1, dd.getWeedDay());
			    views.setTextViewText(R.id.textView2, dd.getDays2());
			    
			    views.setViewVisibility(R.id.ll1, View.GONE);
			    views.setViewVisibility(R.id.ll2, View.GONE);
			    views.setViewVisibility(R.id.ll3, View.GONE);
			    views.setViewVisibility(R.id.ll4, View.GONE);
			    views.setViewVisibility(R.id.ll5, View.GONE);
			    
			    if (!course[0].equals("")||!add[0].equals("")) {
			    	views.setViewVisibility(R.id.ll1, View.VISIBLE);	
			    	views.setTextViewText(R.id.schedule_1, course[0]);
				    views.setTextViewText(R.id.add_1, add[0]);
				    Log.i("course[0]",course[0]);
				}
			    
			    
			    if (!course[1].equals("")||!add[1].equals("")) {
			    	views.setViewVisibility(R.id.ll2, View.VISIBLE);
			    	views.setTextViewText(R.id.schedule_2, course[1]);
				    views.setTextViewText(R.id.add_2, add[1]);
				    Log.i("course[1]",course[1]);
				}
			   
			    
			    if (!course[2].equals("")||!add[2].equals("")) {
			    	views.setViewVisibility(R.id.ll3, View.VISIBLE);
			    	views.setTextViewText(R.id.schedule_3, course[2]);
				    views.setTextViewText(R.id.add_3, add[2]);
				}
			   
			    
			    if (!course[3].equals("")||!add[3].equals("")) {
			    	views.setViewVisibility(R.id.ll4, View.VISIBLE);
			    	views.setTextViewText(R.id.schedule_4, course[3]);
				    views.setTextViewText(R.id.add_4, add[3]);
				}
			    
			    if (!course[4].equals("")||!add[4].equals("")) {
			    	views.setViewVisibility(R.id.ll5, View.VISIBLE);
			    	views.setTextViewText(R.id.schedule_5, course[4]);
				    views.setTextViewText(R.id.add_5, add[4]);
				}
			   
//			    if (course[0].equals("")&&add[0].equals("")) {
//			    	views.setViewVisibility(R.id.l1, View.GONE);		    	
//				}
//			    else {
//			    	views.setTextViewText(R.id.widget_tv1_2, course[0]);
//				    views.setTextViewText(R.id.widget_tv1_3, add[0]);
//				}
//			    
//			    if (course[1].equals("")&&add[1].equals("")) {
//			    	views.setViewVisibility(R.id.l2, View.GONE);		    	
//				}
//			    else {
//			    	views.setTextViewText(R.id.widget_tv2_2, course[1]);
//				    views.setTextViewText(R.id.widget_tv2_3, add[1]);
//				}
//			    
//			    if (course[2].equals("")&&add[2].equals("")) {
//			    	views.setViewVisibility(R.id.l3, View.GONE);		    	
//				}
//			    else {
//			    	views.setTextViewText(R.id.widget_tv3_2, course[2]);
//				    views.setTextViewText(R.id.widget_tv3_3, add[2]);
//				}
//			    
//			    if (course[3].equals("")&&add[3].equals("")) {
//			    	views.setViewVisibility(R.id.l4, View.GONE);		    	
//				}
//			    else {
//			    	views.setTextViewText(R.id.widget_tv4_2, course[3]);
//				    views.setTextViewText(R.id.widget_tv4_3, add[3]);
//				}
//			    
//			    if (course[4].equals("")&&add[4].equals("")) {
//			    	views.setViewVisibility(R.id.l5, View.GONE);		    	
//				}
//			    else {
//			    	views.setTextViewText(R.id.widget_tv5_2, course[4]);
//				    views.setTextViewText(R.id.widget_tv5_3, add[4]);
//				}
//			    
			   
					
			 		
			    } 
			    
//			    views.setTextViewText(R.id.widget_tv0, weekDay);
			    AppWidgetManager appWidgetManger=AppWidgetManager.getInstance(context);
				int[] appIds=appWidgetManger.getAppWidgetIds(new ComponentName(context,TodayDate.class));
				appWidgetManger.updateAppWidget(appIds, views);
		   }
		   
	   }   
      
   public static void updateWidget(AppWidgetManager appWidgetManager, Context context, int appWidgetId){
	        //设置点击事件
	 		RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.widget_layout_4x1);
	 		Intent onClickIntent = new Intent(context, TodayDateLogin.class);
		    PendingIntent intent1 = PendingIntent.getActivity(context, 0, onClickIntent, 0);
		    views.setOnClickPendingIntent(R.id.Base, intent1);
	    
		    Time time=new Time();
		    time .setToNow();
            DateDay dd=new DateDay(context);
		    //String month = dd.getMonth() + " " + dd.getYear();
		    //String date =dd.getDate();
		    String WEEK=time.weekDay+"";
//		    String weekDay;
//		    weekDay ="第"+dd.getWeedDay()+"周";//+" "+dd.getDays1();
		    
		    if (WEEK.equals("0")||WEEK.equals("6")) {
				
			}
		    else {
				
			
		    String []course=new String[5];
			String []add=new String[5];
		    
		    
		    Cursor cursor; 
		    SQLiteDatabase db;
		    ToDoDB toDoDB=new ToDoDB(context);
			 db=toDoDB.getReadableDatabase();
			 
			 String sql="select * from todo_schedule where todo_week="+WEEK;
			 cursor =db.rawQuery(sql, null);
			 Log.i("", sql);
			//判断游标是否为空  
			 if(cursor !=null ){  
				 int i=0,n=cursor .getCount();
				 cursor .moveToFirst();
				 Log.i("", "cursor !=null");
				 Log.i("n=?", n+"");
				
	             //遍历游标  11.       
				  while (!cursor .isAfterLast()) {  
					 
					  //获得ID            
					  //int id = mCursor .getInt(0);          
					  //获得用户名             
					  course[i]=cursor .getString(3); 
					  Log.i("", cursor .getString(3));
					  //获得密码           
					  add[i]=cursor .getString(4);
					  i++;
					  cursor .moveToNext();
				   }
			 }
		    db.close();
		    cursor.close();
		    
//		    LinearLayout llhostLayout=context.findViewById(R.id.widget_ll_host);
			
//			for (int j = 0; j < course.length; j++) {
////			     RemoteViews rViews=La
//				 LinearLayout llhost=get
//		    	 LinearLayout ll=new LinearLayout(context);//初始化LinearLayout 
//		    	 LinearLayout.LayoutParams lParams = new LayoutParams(55,LinearLayout.LayoutParams.FILL_PARENT);//这个属性是设置空间的长宽，其实还可以设置其他的控件的其他属性；
//				    ll.setOrientation(LinearLayout.VERTICAL); //设置朝向 
//				    ll.setPadding(2, 5, 0, 2);//设置四周留白 
//				    
//				    ll.setBackgroundResource(bitmapid[j+1]);
//				    TextView tv1=new TextView(context);//初始化TextView 
//				    tv1.setText(course[j]);//设置内容 
//				    tv1.setLines(4);
//				    tv1.setGravity(Gravity.CENTER);
//				    tv1.setMaxLines(4);
//				    tv1.setTextSize(10);//设置字体大小 
//				    tv1.setTextColor(context.getResources().getColor(R.color.button_back));
//				    ll.addView(tv1);//添加到LinearLayout中 
//				    TextView tv2=new TextView(context);//初始化TextView 
//				    tv2.setText(add[j]);//设置内容 
//				    tv2.setTextSize(10);//设置字体大小 
//				    tv2.setGravity(Gravity.HORIZONTAL_GRAVITY_MASK);
//				    tv2.setLines(1);
//				    tv2.setMaxLines(1);
//				    tv2.setTextScaleX((float) 0.85);
//				    tv2.setTextColor(context.getResources().getColor(R.color.white));
//				    ll.addView(tv2);//添加到LinearLayout中 
//				    views.addView(R.id.widget_ll_host, ll);
//		    Typeface tf = Typeface.createFromFile("fonts/Roboto-Thin.ttf");   

            
		    views.setTextViewText(R.id.textView1, dd.getWeedDay());
		    views.setTextViewText(R.id.textView2, dd.getDays2());
		    
		    
//		    views.setViewVisibility(R.id.ll1, View.GONE);
//		    views.setViewVisibility(R.id.ll2, View.GONE);
//		    views.setViewVisibility(R.id.ll3, View.GONE);
//		    views.setViewVisibility(R.id.ll4, View.GONE);
//		    views.setViewVisibility(R.id.ll5, View.GONE);
		    
		    if (!course[0].equals("")||!add[0].equals("")) {
		    	views.setViewVisibility(R.id.ll1, View.VISIBLE);	
		    	views.setTextViewText(R.id.schedule_1, course[0]);
			    views.setTextViewText(R.id.add_1, add[0]);
			}
		    
		    
		    if (!course[1].equals("")||!add[1].equals("")) {
		    	views.setViewVisibility(R.id.ll2, View.VISIBLE);
		    	views.setTextViewText(R.id.schedule_2, course[1]);
			    views.setTextViewText(R.id.add_2, add[1]);
			}
		   
		    
		    if (!course[2].equals("")||!add[2].equals("")) {
		    	views.setViewVisibility(R.id.ll3, View.VISIBLE);
		    	views.setTextViewText(R.id.schedule_3, course[2]);
			    views.setTextViewText(R.id.add_3, add[2]);
			}
		   
		    
		    if (!course[3].equals("")||!add[3].equals("")) {
		    	views.setViewVisibility(R.id.ll4, View.VISIBLE);
		    	views.setTextViewText(R.id.schedule_4, course[3]);
			    views.setTextViewText(R.id.add_4, add[3]);
			}
		    
		    if (!course[4].equals("")||!add[4].equals("")) {
		    	views.setViewVisibility(R.id.ll5, View.VISIBLE);
		    	views.setTextViewText(R.id.schedule_5, course[4]);
			    views.setTextViewText(R.id.add_5, add[4]);
			}
		    
		    
		    }
	    	
//		    views.setTextViewText(R.id.widget_tv0, weekDay);
			appWidgetManager.updateAppWidget(appWidgetId, views);
			//Log.i("TD","已为ID为"+appWidgetId+"的view设置监听器");
			//Log.i("TD更新情况", "ID为"+appWidgetId+"的views已完成更新");
	 		
	 		
   }
  
}
