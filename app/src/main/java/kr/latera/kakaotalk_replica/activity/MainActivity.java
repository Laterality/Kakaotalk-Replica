package kr.latera.kakaotalk_replica.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import kr.latera.kakaotalk_replica.R;
import kr.latera.kakaotalk_replica.fragment.ChannelFragment;
import kr.latera.kakaotalk_replica.fragment.ChattingFragment;
import kr.latera.kakaotalk_replica.fragment.FriendsFragment;
import kr.latera.kakaotalk_replica.fragment.MoreFragment;
import kr.latera.kakaotalk_replica.view.TabTitleView;


public class MainActivity extends AppCompatActivity implements ChannelFragment.OnFragmentInteractionListener, ChattingFragment.OnFragmentInteractionListener, FriendsFragment.OnFragmentInteractionListener, MoreFragment.OnFragmentInteractionListener
{

	private static final String TAG = "MainActivity";

	private TabLayout tlTab;
	private ViewPager vpPager;
	private TabTitleView ttvFriends;
	private TabTitleView ttvChats;
	private TabTitleView ttvChannel;
	private TabTitleView ttvMore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(kr.latera.kakaotalk_replica.R.layout.activity_main);

		tlTab = (TabLayout)findViewById(R.id.tl_main);
		vpPager = (ViewPager)findViewById(R.id.vp_main);
		vpPager.setAdapter(new MenuPagerAdapter(getSupportFragmentManager()));
		tlTab.setupWithViewPager(vpPager);
		tlTab.getTabAt(0).setCustomView(ttvFriends = new TabTitleView(this, R.drawable.tab_friend));
		tlTab.getTabAt(1).setCustomView(ttvChats = new TabTitleView(this, R.drawable.tab_chatting));
		tlTab.getTabAt(2).setCustomView(ttvChannel = new TabTitleView(this, R.drawable.tab_channel));
		tlTab.getTabAt(3).setCustomView(ttvMore = new TabTitleView(this, R.drawable.tab_more));

		ttvChats.setCount(2);
		// If a notification message is tapped, any data accompanying the notification
		// message is available in the intent extras. In this sample the launcher
		// intent is fired when the notification is tapped, so any accompanying data would
		// be handled here. If you want a different intent fired, set the click_action
		// field of the notification message to the desired intent. The launcher intent
		// is used when no click_action is specified.
		//
		// Handle possible data accompanying notification message.
		// [START handle_data_extras]
//		if (getIntent().getExtras() != null) {
//			for (String key : getIntent().getExtras().keySet()) {
//				String value = getIntent().getExtras().getString(key);
//				Log.d(TAG, "Key: " + key + " Value: " + value);
//			}
//		}
//		// [END handle_data_extras]
//
//		Button subscribeButton = (Button) findViewById(R.id.subscribeButton);
//		subscribeButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// [START subscribe_topics]
//				FirebaseMessaging.getInstance().subscribeToTopic("news");
//				// [END subscribe_topics]
//
//				// Log and toast
//				String msg = getString(R.string.msg_subscribed);
//				Log.d(TAG, msg);
//				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//			}
//		});
//
//		Button logTokenButton = (Button) findViewById(R.id.logTokenButton);
//		logTokenButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// Get token
//				String token = FirebaseInstanceId.getInstance().getToken();
//
//				// Log and toast
//				String msg = getString(R.string.msg_token_fmt, token);
//				Log.d(TAG, msg);
//				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//			}
//		});
	}

	@Override
	public void onFragmentInteraction(Uri uri)
	{

	}

	private class MenuPagerAdapter extends FragmentPagerAdapter
	{
		private static final int PAGE_COUNT = 4;
		public MenuPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			switch (position)
			{
				case 0:
					return FriendsFragment.newInstance(null, null);
				case 1:
					return ChattingFragment.newInstance(null, null);
				case 2:
					return ChannelFragment.newInstance(null, null);
				case 3:
					return MoreFragment.newInstance(null, null);
			}
			return null;
		}

		@Override
		public int getCount()
		{
			return PAGE_COUNT;
		}
	}
}
