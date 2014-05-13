package org.pazienti.helpmeout.fragments;

import org.pazienti.helpmeout.R;
import org.pazienti.helpmeout.activities.LoginActivity;
import org.pazienti.helpmeout.activities.MainActivity;
import org.pazienti.helpmeout.app.HelpMeOut;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class VideoFragment extends Fragment{
	// FRAGMENT INFOS
	public final static String 	FRAGMENT_TITLE 	= "Video";
	public final static int 	POSITION 		= 0;
	
	private MainActivity 		mParentActivity;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        
        mParentActivity = (MainActivity)getActivity();
        
        if(mParentActivity.getTokbox() == null){
        	Intent intent = new Intent(getActivity(), LoginActivity.class);
        	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        	
        	startActivity(intent);
        }
        
        return rootView;
    }
	
	@Override
	public void onResume() {
		super.onResume();
		
		if(!mParentActivity.getTokbox().isReady()){
	        mParentActivity.getTokbox().setView((RelativeLayout) mParentActivity.findViewById(R.id.publisher_view), (RelativeLayout) mParentActivity.findViewById(R.id.subscriber_view));
		}
		
		if(mParentActivity.getTokbox() != null && mParentActivity.getTokbox().getSession() != null){
			mParentActivity.getTokbox().getSession().onResume();
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();

        if (mParentActivity.getTokbox() != null && mParentActivity.getTokbox().getSession() != null) {
        	mParentActivity.getTokbox().getSession().onPause();
        }
	}
}
