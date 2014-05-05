package org.pazienti.helpmeout.app;

import android.util.Log;

import com.opentok.android.Connection;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.opentok.android.SubscriberKit;

public class TokboxListeners implements Session.Listener, Publisher.Listener, Subscriber.Listener{
	// CLASS INFOS
	public final static String TAG = TokboxListeners.class.getSimpleName();

	
	@Override
	public void connected(SubscriberKit arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(SubscriberKit arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(SubscriberKit arg0, OpentokError arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void videoDataReceived(SubscriberKit arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void videoDisabled(SubscriberKit arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedCamera(PublisherKit arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(PublisherKit arg0, OpentokError arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamCreated(PublisherKit arg0, Stream arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamDestroyed(PublisherKit arg0, Stream arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPublisher(Session arg0, PublisherKit arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connected(Session session) {
		Log.i(TAG, "Connesso alla session del cazzo.");
	}

	@Override
	public void connectionCreated(Session arg0, Connection arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionDestroyed(Session arg0, Connection arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(Session arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void droppedStream(Session arg0, Stream arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Session session, OpentokError e) {
		Log.e(TAG, e.getMessage());	
		Log.e(TAG, String.valueOf(e.getErrorCode()));
		e.getErrorDomain();
	}

	@Override
	public void onSignal(Session arg0, String arg1, String arg2, Connection arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivedStream(Session arg0, Stream arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePublisher(Session arg0, PublisherKit arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamChangeHasAudio(Session arg0, Stream arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamChangeHasVideo(Session arg0, Stream arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void streamChangeVideoDimensions(Session arg0, Stream arg1,
			int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

}
