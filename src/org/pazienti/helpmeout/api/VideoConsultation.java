package org.pazienti.helpmeout.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class VideoConsultation extends API{
	public final static String SESSION_ID 		= "session_id";
	public final static String TOKEN 			= "token";
	public final static String VIDEO_API_KEY 	= "video_api_key";
	
	public VideoConsultation(APICallbackListener listener) {
		super(listener);
	}
	
	public void connectToSession(String pinCode){		
		try {
			URL apiUrl 						= new URL("http://www.pazienti.it/api/v1/video_consultations/" + pinCode);
			HttpURLConnection connection	= (HttpURLConnection) apiUrl.openConnection();
			
			execute(connection);
		} catch (MalformedURLException e) {
			raiseRequestError(e);
		} catch (IOException e) {
			raiseRequestError(e);
		}		
	}	
}
