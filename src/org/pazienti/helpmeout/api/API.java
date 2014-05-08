package org.pazienti.helpmeout.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class API extends AsyncTask<Object, Void, JSONObject>{
	// Class attributes
	protected APICallbackListener 	mCallbackListener;
	protected ArrayList<Exception> 	mThrowedExceptions;
	protected int					mResponseCode;
	
	// Class methods
	
	/* Class constructor */
	public API(APICallbackListener listener){
		mCallbackListener = listener;
		initRequestAttributes();
	}
	
	/* Method to add error to API request*/
	protected void raiseAPIError(Exception e) {
		mThrowedExceptions.add(e);
	}
	/* Method to initialize and reset the API request attributes */
	private void initRequestAttributes(){
		mThrowedExceptions 	= new ArrayList<Exception>();
		mResponseCode		= -1;
	}
	
	// AsyncTask overridden methods
	@Override
	protected JSONObject doInBackground(Object... params) {
		JSONObject response = null;
		
		HttpURLConnection connection = (HttpURLConnection)params[0];
		
		try {
			connection.connect();
			
			mResponseCode = connection.getResponseCode();
			
			if(mResponseCode == HttpURLConnection.HTTP_OK){
				Reader reader = new InputStreamReader(connection.getInputStream());
				
				char[] byteResponse = new char[connection.getContentLength()];
				
				reader.read(byteResponse);
				
				response = new JSONObject(new String(byteResponse));
			}
		} catch (IOException e) {
			raiseAPIError(e);
		} catch (JSONException e) {
			raiseAPIError(e);
		}
		
		return response;
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		if(mCallbackListener != null){
			if(mResponseCode == HttpURLConnection.HTTP_OK){
				mCallbackListener.onSuccess(result);
			}else if(mResponseCode != -1){
				mCallbackListener.onFailure(mResponseCode);
			}else{
				for(Exception e : mThrowedExceptions){
					mCallbackListener.onError(e);
				}	
			}
		}
		
		initRequestAttributes();
	}
}
