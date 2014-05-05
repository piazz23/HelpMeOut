package org.pazienti.helpmeout.fragments;

import org.pazienti.helpmeout.R;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessagesFragment extends ListFragment{
	// FRAGMENT INFOS
		public final static String 	FRAGMENT_TITLE 	= "Messages";
		public final static int 	POSITION 		= 1;
		
		@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);
	        
	        return rootView;
	    }
}
