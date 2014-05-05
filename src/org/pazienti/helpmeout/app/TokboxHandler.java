package org.pazienti.helpmeout.app;

import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;

import com.opentok.android.Connection;
import com.opentok.android.Publisher;
import com.opentok.android.Session;

public class TokboxHandler {
	// CLASS INFOS
	private final String 		TAG = TokboxHandler.class.getSimpleName();
	
	// Tokbox variables
	protected Session 			mSession;
	protected Connection 		mConnection;
	protected Publisher 		mPublisher;
	
	protected RelativeLayout 	mPublisherViewContainer;
	protected RelativeLayout 	mSubscriberViewContainer;
	
	// Activity context
	protected Context			mContext;
		
	public TokboxHandler(Context contenxt){
		mContext = contenxt;
		
		mSession = new Session(mContext, HelpMeOut.TOKBOX_SESSION_ID, new TokboxListeners());
		mSession.connect(HelpMeOut.TOKBOX_API_KEY, HelpMeOut.TOKBOX_TOKEN);
		
		Log.i(TAG, "wait");
	}

	// Subscriber.Listener methods
//	@Override
//	public void addPublisher(Session arg0, PublisherKit arg1) {
//	}
//
//	@Override
//	public void connected(Session session) {
//		Log.w(TAG, "Connected to the session.");
//        if (mPublisher == null) {
//            mPublisher = new Publisher(mContext, this, "Publisher");
//            
//            mPublisher.setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE,
//            		BaseVideoRenderer.STYLE_VIDEO_FILL);
//
//            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//                    320, 240);
//            
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//            
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
//            
//            layoutParams.bottomMargin = HelpMeOut.dpToPx(8, mContext);
//            layoutParams.rightMargin = HelpMeOut.dpToPx(8, mContext);
//            
//            mPublisherViewContainer.addView(mPublisher.getView(), layoutParams);
//
//            mSession.publish(mPublisher);
//        }
//	}
//
//	@Override
//	public void connectionCreated(Session arg0, Connection arg1) {
//	}
//
//	@Override
//	public void connectionDestroyed(Session arg0, Connection arg1) {
//	}
//
//	@Override
//	public void disconnected(Session arg0) {
//	}
//
//	@Override
//	public void droppedStream(Session arg0, Stream arg1) {
//	}
//
//	@Override
//	public void error(Session arg0, OpentokError arg1) {
//	}
//
//	@Override
//	public void onSignal(Session arg0, String arg1, String arg2, Connection arg3) {
//	}
//
//	@Override
//	public void receivedStream(Session arg0, Stream arg1) {
//	}
//
//	@Override
//	public void removePublisher(Session arg0, PublisherKit arg1) {	
//	}
//
//	@Override
//	public void streamChangeHasAudio(Session arg0, Stream arg1, int arg2) {
//	}
//
//	@Override
//	public void streamChangeHasVideo(Session arg0, Stream arg1, int arg2) {
//	}
//
//	@Override
//	public void streamChangeVideoDimensions(Session arg0, Stream arg1,
//			int arg2, int arg3) {		
//	}
//
//	
//	// Publisher.Listener methods
//	@Override
//	public void changedCamera(PublisherKit arg0, int arg1) {
//	}
//
//	@Override
//	public void error(PublisherKit arg0, OpentokError arg1) {
//	}
//
//	@Override
//	public void streamCreated(PublisherKit arg0, Stream arg1) {
//	}
//
//	@Override
//	public void streamDestroyed(PublisherKit arg0, Stream arg1) {
//	}
}
