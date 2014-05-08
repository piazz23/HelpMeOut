package org.pazienti.helpmeout.activities;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONObject;
import org.pazienti.helpmeout.R;
import org.pazienti.helpmeout.api.APICallbackListener;
import org.pazienti.helpmeout.api.VideoConsultation;
import org.pazienti.helpmeout.app.HelpMeOut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
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
			VideoConsultation api = new VideoConsultation();
			
			api.connectToSession(mTxtLoginCode.getText().toString(), new APICallbackListener() {
				@Override
				public void onSuccess(JSONObject result) {
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(mTxtLoginCode.getWindowToken(), 0);
					
					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					intent.putExtra(HelpMeOut.FLAG_EXTRA_ACCESS_DONE, true);
					
					startActivity(intent);
				}
				@Override
				public void onFailure(int responseCode) {
					Toast.makeText(LoginActivity.this, "Response: " + responseCode, Toast.LENGTH_LONG).show();
				}
				@Override
				public void onError(Exception e) {}
			});

		}
	};
	
	private void initialize_components(){
		mTxtLoginCode 	= (EditText)findViewById(R.id.txt_login_code);
		
		mCmdLogin		= (Button)findViewById(R.id.cmd_login);
		mCmdLogin.setOnClickListener(cmdLoginOnCLickListener);
	}
}
