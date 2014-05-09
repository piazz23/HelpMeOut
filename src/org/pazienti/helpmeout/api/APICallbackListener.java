package org.pazienti.helpmeout.api;

import org.json.JSONObject;

public abstract interface APICallbackListener {	
	public void onSuccess(JSONObject result);
	
	public void onFailure(int responseCode);
	
	public void onError(Exception e);
}
