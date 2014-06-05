package org.pazienti.helpmeout.activities;

import org.json.JSONException;
import org.json.JSONObject;
import org.pazienti.helpmeout.R;
import org.pazienti.helpmeout.api.APICallbackListener;
import org.pazienti.helpmeout.api.VideoConsultation;
import org.pazienti.helpmeout.app.HelpMeOut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	// CLASS INFO
	public static final String TAG = LoginActivity.class.getSimpleName();
	
	// VIEW COMPONENTS
	protected EditText mTxtLoginCode;
	protected Button mCmdLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		initialize_components();
	}

	// EVENT LISTENERS
	View.OnClickListener cmdLoginOnCLickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			VideoConsultation api = new VideoConsultation(new APICallbackListener() {
				@Override
				public void onSuccess(JSONObject result) {					
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(mTxtLoginCode.getWindowToken(), 0);
					
					try {						
						String sessionId 	= result.getString(VideoConsultation.SESSION_ID);
						String token 		= result.getString(VideoConsultation.TOKEN);
						String videoApiKey 	= result.getString(VideoConsultation.VIDEO_API_KEY);						
						
						Intent intent 		= new Intent(LoginActivity.this, MainActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						
						intent.putExtra(HelpMeOut.FLAG_VIDEO_ACCESS_DONE, true);
						intent.putExtra(VideoConsultation.SESSION_ID, sessionId);
						intent.putExtra(VideoConsultation.TOKEN, token);
						intent.putExtra(VideoConsultation.VIDEO_API_KEY, videoApiKey);
						
						startActivity(intent);						
					} catch (JSONException e) {
						Log.e(TAG, e.getMessage());
					}
				}
				@Override
				public void onFailure(int responseCode) {
					Toast.makeText(LoginActivity.this, "Response: " + responseCode, Toast.LENGTH_LONG).show();
				}
				@Override
				public void onError(Exception e) {
					Log.e(TAG, e.getMessage());
				}
			});
			
			api.connectToSession(mTxtLoginCode.getText().toString());
		}
	};
	
	private void initialize_components(){
		mTxtLoginCode 	= (EditText)findViewById(R.id.txt_login_code);
		
		mCmdLogin		= (Button)findViewById(R.id.cmd_login);
		mCmdLogin.setOnClickListener(cmdLoginOnCLickListener);
	}
}
