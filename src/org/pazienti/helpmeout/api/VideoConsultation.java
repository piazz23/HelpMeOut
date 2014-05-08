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
import android.text.Editable;

public class VideoConsultation extends AsyncTask<Object, Void, JSONObject>{
	private APICallbackListener callbackListener;
	
	protected JSONObject doInBackground(Object... params) {
		JSONObject response = null;
		
		HttpURLConnection connection = (HttpURLConnection)params[0];
		
		try {
			connection.connect();
			
			if(connection.getResponseCode() == 	HttpURLConnection.HTTP_OK){
				Reader reader = new InputStreamReader(connection.getInputStream());
				
				char[] byteResponse = new char[connection.getContentLength()];
				
				reader.read(byteResponse);
				
				response = new JSONObject(new String(byteResponse));
			}else{
				callbackListener.onFailure(connection.getResponseCode());
			}
		} catch (IOException e) {
			callbackListener.onError(e);
		} catch (JSONException e) {
			callbackListener.onError(e);
		}
		
		return response;
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		callbackListener.onSuccess(result);
	}
	
	
	public void connectToSession(String pinCode, APICallbackListener listener){
		URL apiUrl;
		try {
			apiUrl = new URL("http://www.pazienti.it/api/v1/video_consultations/" + pinCode);
			HttpURLConnection connection	= (HttpURLConnection) apiUrl.openConnection();
			
			execute(connection, API.API_CONNECT_TO_SESSION, listener);
		} catch (MalformedURLException e) {
			callbackListener.onError(e);
		} catch (IOException e) {
			callbackListener.onError(e);
		}
		
		
		
	}	
}
