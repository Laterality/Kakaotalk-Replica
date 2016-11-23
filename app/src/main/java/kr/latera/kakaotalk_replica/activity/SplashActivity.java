package kr.latera.kakaotalk_replica.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


import kr.latera.kakaotalk_replica.Preferences;
import kr.latera.kakaotalk_replica.R;
import kr.latera.kakaotalk_replica.utility.CustomApplication;
import kr.latera.kakaotalk_replica.utility.LConstants;

/**
 * Created by jinwoo on 2016-09-25.
 */

public class SplashActivity extends AppCompatActivity
{
	private static final long DELAY = 1500;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);


		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				boolean loggedIn = ((CustomApplication)getApplication()).isLoggedIn();

				if(loggedIn)
				{
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
				}
				else
				{
					// go to setup activity
					if(((CustomApplication)getApplication()).getDevice() == null)
					{
						startActivity(new Intent(SplashActivity.this, SetupActivity.class));
					}
					else
					{
						startActivity(new Intent(SplashActivity.this, LoginActivity.class));
					}
				}
				finish();
			}
		}, DELAY);
	}

}
