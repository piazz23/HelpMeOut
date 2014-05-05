package org.pazienti.helpmeout.app;

import android.app.Application;
import android.content.Context;

public class HelpMeOut extends Application{
	// INTENT FLAGS EXTRA
	public static final String FLAG_EXTRA_ACCESS_DONE 	= "access_done";
		
	// TOCKBOX DATA
	public static final String TOKBOX_SESSION_ID 		= "1_MX4yOTUyOTM1Mn5-U3VuIE1heSAwNCAxNToyNjoyMiBQRFQgMjAxNH4wLjQ5ODQ2ODk0fn4";
	public static final String TOKBOX_TOKEN 			= "T1==cGFydG5lcl9pZD0yOTUyOTM1MiZzZGtfdmVyc2lvbj10YnJ1YnktdGJyYi12MC45MS4yMDExLTAyLTE3JnNpZz04MGM1M2ZmYWU2Y2I2NDk4NGQ3NGVlNDUyZjYzYWZlODE4M2UyOGZlOnJvbGU9cHVibGlzaGVyJnNlc3Npb25faWQ9MV9NWDR5T1RVeU9UTTFNbjUtVTNWdUlFMWhlU0F3TkNBeE5Ub3lOam95TWlCUVJGUWdNakF4Tkg0d0xqUTVPRFEyT0RrMGZuNCZjcmVhdGVfdGltZT0xMzk5MjQyNDE2Jm5vbmNlPTAuOTUwMjQ5OTIxMzMyNjUmZXhwaXJlX3RpbWU9MTQwMTgzNDQxMSZjb25uZWN0aW9uX2RhdGE9"; 
	public static final String TOKBOX_API_KEY			= "29529352";
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	 public static int dpToPx(int dp, Context context) {
		 double screenDensity = context.getResources().getDisplayMetrics().density;
	     return (int) (screenDensity * (double) dp);
	}

}
