package org.pazienti.helpmeout.app;

import org.pazienti.helpmeout.R;

import android.app.Activity;
import android.opengl.Visibility;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.Connection;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.opentok.android.SubscriberKit;

public class TokboxHandler implements Session.Listener, Publisher.Listener, Subscriber.Listener{
	// CLASS INFOS
	private final String 		TAG = TokboxHandler.class.getSimpleName();
	
	// Tokbox variables
	protected String			mSessionId;
	protected String 			mToken;
	protected String 			mApiKey;
	
	protected Session 			mSession;
	protected Connection 		mConnection;
	protected Publisher 		mPublisher;
	protected Subscriber 		mSubscriber;
	
	// Tokbox views conteiners
	protected RelativeLayout 	mPublisherViewContainer;
	protected RelativeLayout 	mSubscriberViewContainer;
	
	// Activity context
	protected Activity			mContext;
		
	public TokboxHandler(Activity context, String session, String token, String apiKey){
		mContext 					= context;
		mSessionId					= session;
		mToken						= token;
		mApiKey						= apiKey;
		
		mSession 					= new Session(mContext, mSessionId, this);
		mSession.connect(mApiKey, mToken);
		
		mPublisherViewContainer 	= (RelativeLayout) mContext.findViewById(R.id.publisher_view);
		mSubscriberViewContainer 	= (RelativeLayout) mContext.findViewById(R.id.subscriber_view);
	}
	
	// Class getter methods
	public Session getSession() {
		return mSession;
	}

	public Connection getConnection() {
		return mConnection;
	}

	public Publisher getPublisher() {
		return mPublisher;
	}
	
	// Util methods
	private void subscribeToStream(Stream stream) {
        mSubscriber = new Subscriber(mContext, stream, this);
        
        mSubscriber.setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE, BaseVideoRenderer.STYLE_VIDEO_FILL);
        
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                mContext.getResources().getDisplayMetrics().widthPixels, mContext.getResources()
                        .getDisplayMetrics().heightPixels);
                
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//        
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        
        mSubscriberViewContainer.addView(mSubscriber.getView(), layoutParams);

        mSession.subscribe(mSubscriber);
    }

    private void unsubscriberToStream(Stream stream) {
        if (mSubscriber.getStream().getStreamId().equals(stream.getStreamId())) {
            mSubscriberViewContainer.removeView(mSubscriber.getView());
            mSubscriber = null;
        }
    }

	// Subscriber.Listener methods
	@Override
	public void addPublisher(Session arg0, PublisherKit arg1) {
	}

	@Override
	public void connected(Session session) {
		Log.w(TAG, "Connected to the session.");
        if (mPublisher == null) {
            mPublisher = new Publisher(mContext, this, "Publisher");
            
            mPublisher.setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE, BaseVideoRenderer.STYLE_VIDEO_FILL);
            
            int mPublisherWidthDim 	= mContext.getResources().getDimensionPixelSize(R.dimen.publisher_width_dimension);
			int mPublisherHeightDim = mContext.getResources().getDimensionPixelSize(R.dimen.publisher_height_dimension);
            
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(mPublisherWidthDim, mPublisherHeightDim);
            
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, R.id.publisher_view);
            
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, R.id.publisher_view);
            
            mPublisherViewContainer.addView(mPublisher.getView(), layoutParams);

            mSession.publish(mPublisher);
            
            RelativeLayout loadingLayout = (RelativeLayout)mContext.findViewById(R.id.video_loading_view);
            loadingLayout.setVisibility(View.INVISIBLE);
//            mContext.findViewById(R.id.video_loading_view);
        }
	}

	@Override
	public void connectionCreated(Session arg0, Connection arg1) {
	}

	@Override
	public void connectionDestroyed(Session arg0, Connection arg1) {
	}

	@Override
	public void disconnected(Session session) {
		if (mPublisher != null) {
			mPublisherViewContainer.removeView(mPublisher.getView());
        }

        if (mSubscriber != null) {
        	mSubscriberViewContainer.removeView(mSubscriber.getView());
        }

        mPublisher = null;
        mSubscriber = null;
//        mStreams.clear();
        mSession = null;
	}

	@Override
	public void droppedStream(Session session, Stream stream) {
		if (mSubscriber != null) {
            unsubscriberToStream(stream);
        }
	}

	@Override
	public void error(Session session, OpentokError e) {
		Log.e(TAG, "Session exception: " + e.getMessage());
	}

	@Override
	public void onSignal(Session arg0, String arg1, String arg2, Connection arg3) {
	}

	@Override
	public void receivedStream(Session session, Stream stream) {
		if (!HelpMeOut.SUBSCRIBE_TO_SELF) {
            if (mSubscriber == null) {
                subscribeToStream(stream);
            }
        }
	}

	@Override
	public void removePublisher(Session arg0, PublisherKit arg1) {	
	}

	@Override
	public void streamChangeHasAudio(Session arg0, Stream arg1, int arg2) {
	}

	@Override
	public void streamChangeHasVideo(Session arg0, Stream arg1, int arg2) {
	}

	@Override
	public void streamChangeVideoDimensions(Session arg0, Stream arg1,
			int arg2, int arg3) {		
	}

	
	// Publisher.Listener methods
	@Override
	public void changedCamera(PublisherKit arg0, int arg1) {
	}

	@Override
	public void error(PublisherKit arg0, OpentokError arg1) {
	}

	@Override
	public void streamCreated(PublisherKit publisher, Stream stream) {
		if (HelpMeOut.SUBSCRIBE_TO_SELF) {
            if (mSubscriber == null) {
                subscribeToStream(stream);
            }
    	}
	}

	@Override
	public void streamDestroyed(PublisherKit publisher, Stream stream) {
		if ((HelpMeOut.SUBSCRIBE_TO_SELF && mSubscriber != null)) {
            unsubscriberToStream(stream);
        }
	}

	@Override
	public void connected(SubscriberKit arg0) {
		Log.i(TAG, "Subscriber connected.");
	}

	@Override
	public void disconnected(SubscriberKit arg0) {
		Log.i(TAG, "Subscriber disconnected.");	
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
}
