package org.pazienti.helpmeout.fragments;

import org.pazienti.helpmeout.R;
import org.pazienti.helpmeout.activities.LoginActivity;
import org.pazienti.helpmeout.app.HelpMeOut;
import org.pazienti.helpmeout.app.TokboxHandler;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VideoFragment extends Fragment{
	// FRAGMENT INFOS
	public final static String 	FRAGMENT_TITLE 	= "Video";
	public final static int 	POSITION 		= 0;
	
	protected TokboxHandler 	tokbox;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        
        
        if(!getActivity().getIntent().getBooleanExtra(HelpMeOut.FLAG_EXTRA_ACCESS_DONE, false)){
        	Intent intent = new Intent(getActivity(), LoginActivity.class);
        	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        	
        	startActivity(intent);
        }
        
        return rootView;
    }
	
	@Override
	public void onResume() {
		super.onResume();
		
		if(tokbox == null && getActivity().getIntent().getBooleanExtra(HelpMeOut.FLAG_EXTRA_ACCESS_DONE, false)){
			tokbox = new TokboxHandler(getActivity());
		}else if(tokbox != null && tokbox.getSession() != null){
			tokbox.getSession().onResume();
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();

        if (tokbox != null && tokbox.getSession() != null) {
        	tokbox.getSession().onPause();
        }
	}

    @Override
    public void onStop() {
        super.onStop();

        if (tokbox != null && tokbox.getSession() != null) {
        	tokbox.getSession().disconnect();
        }
        getActivity().finish();
    }
}
