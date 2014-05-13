package org.pazienti.helpmeout.activities;

import java.util.Locale;

import org.pazienti.helpmeout.R;
import org.pazienti.helpmeout.api.VideoConsultation;
import org.pazienti.helpmeout.app.HelpMeOut;
import org.pazienti.helpmeout.app.TokboxHandler;
import org.pazienti.helpmeout.fragments.MessagesFragment;
import org.pazienti.helpmeout.fragments.VideoFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    public SectionsPagerAdapter mSectionsPagerAdapter;

    public ViewPager 			mViewPager;

	protected TokboxHandler 	mTokbox;

	// Activity methods
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        if(mTokbox == null && getIntent().getBooleanExtra(HelpMeOut.FLAG_VIDEO_ACCESS_DONE, false)){
			String sessionId 	= getIntent().getStringExtra(VideoConsultation.SESSION_ID);
			String token		= getIntent().getStringExtra(VideoConsultation.TOKEN);
			String videoApiKey  = getIntent().getStringExtra(VideoConsultation.VIDEO_API_KEY);
			
			
			mTokbox = new TokboxHandler(this, sessionId, token, videoApiKey);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onStop() {
        super.onStop();

        if (getTokbox() != null && getTokbox().getSession() != null) {
        	getTokbox().getSession().disconnect();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
			case 0:
				return new VideoFragment();
			case 1:
				return new MessagesFragment();
            }
            
            return null;
        }

        @Override // Number of page on this Activity
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return VideoFragment.FRAGMENT_TITLE.toUpperCase(l);
                case 1:
                    return MessagesFragment.FRAGMENT_TITLE.toUpperCase(l);
            }
            return null;
        }
    }
    
    /** Main Activity methods */
    // mTokbox getter
    public TokboxHandler getTokbox() {
		return mTokbox;
	}    

}
