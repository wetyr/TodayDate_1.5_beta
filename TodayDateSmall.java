package com.kelly.activity;




import android.app.PendingIntent;
import com.kelly.*;
import com.kelly.activity.R;
import com.kelly.activity.TodayDateLogin;
import com.kelly.entity.DateDay;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import android.widget.RemoteViews;


public class TodayDateSmall extends AppWidgetProvider
{
   
	   @Override
	   public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	      
		  //Log.i("onUpdate", "已进入TDS的onUpdate");
			final int N=appWidgetIds.length;
			for (int i = 0; i <N; i++) {
				updateWidget(appWidgetManager, context, appWidgetIds[i]);
			}
	        super.onUpdate(context, appWidgetManager, appWidgetIds);
	        
	      
	   }

	   	  
	   @Override
	   public void onReceive(Context context, Intent intent) {
		   // TODO Auto-generated method stub
		   super.onReceive(context, intent);
		   if (intent.getAction().equals("android.appwidget.action.APPWIDGET_UPDATE")) {
			  //Log.i("ok","tds已捕捉广播");
			  
			  RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.widget_layout_small);
 
		      //System.out.println("这里是TDS的strInt"+SetInt);
		      //System.out.println("这里是TDS的NowInt"+NowInt);
		      
		      DateDay dd=new DateDay(context);
		      String day=dd.getDays2();//MON
			  views.setTextViewText(R.id.s_day, day);
			  
			  String date=dd.getDate();//20
			  views.setTextViewText(R.id.s_date, date);
			  
			  String month = dd.getMonth2()+"";//Feb
			  views.setTextViewText(R.id.s_month, month);
		      
			  String weedDay =dd.getWeedDay()+"";//K
		      views.setTextViewText(R.id.s_weekday, weedDay);/**/
			  		      
			  AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
			  appWidgetManager.updateAppWidget(new ComponentName(context, TodayDateSmall.class), views);
			  //Log.i("ok","tds已更新");
		  }
	   }
   
	   public static void updateWidget(AppWidgetManager appWidgetManager, Context context, int appWidgetId){
	        //设置点击事件
	 		RemoteViews views=new RemoteViews(context .getPackageName(), R.layout.widget_layout_small);
	 		Intent onClickIntent = new Intent(context, TodayDateLogin.class);
	 		PendingIntent intent = PendingIntent.getActivity(context, 0, onClickIntent, 0);
	 		views.setOnClickPendingIntent(R.id.SmallBase, intent);
	 		//Log.i("TDS", "已设置监听器");
	 		
	 		  DateDay dd=new DateDay(context);
	 		
			  String day=dd.getDays2();//MON
			  views.setTextViewText(R.id.s_day, day);
			    
			  String date=dd.getDate();//20
			  views.setTextViewText(R.id.s_date, date);
			  
			  String month = dd.getMonth2()+"";//Feb
			  views.setTextViewText(R.id.s_month, month);
		      
			  String weedDay =dd.getWeedDay()+"";//K
		      views.setTextViewText(R.id.s_weekday, weedDay);
		      
	 		appWidgetManager.updateAppWidget(appWidgetId, views);
	 		//Log.i("TDS更新情况", "2个views已完成更新");
  }

      
}

