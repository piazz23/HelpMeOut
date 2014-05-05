package org.pazienti.helpmeout.fragments;

import org.pazienti.helpmeout.R;
import org.pazienti.helpmeout.app.TokboxHandler;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VideoFragment extends Fragment{
	// FRAGMENT INFOS
	public final static String 	FRAGMENT_TITLE 	= "Video";
	public final static int 	POSITION 		= 0;
	
	protected TokboxHandler tokbox;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        
        tokbox = new TokboxHandler(getActivity());
        
        return rootView;
    }
}
