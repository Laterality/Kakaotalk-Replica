package kr.latera.kakaotalk_replica.utility;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import io.realm.Realm;
import kr.latera.kakaotalk_replica.Preferences;
import kr.latera.kakaotalk_replica.api.dbo.Device;
import kr.latera.kakaotalk_replica.api.dbo.User;

/**
 * Created by jinwoo on 2016-11-16.
 */

public class CustomApplication extends Application
{
	private static String TAG = "";
	private Device userDevice;
	private User user;
	private boolean loggedIn;

	private SharedPreferences prefs;
	private SharedPreferences.Editor editor;

	@Override
	public void onCreate()
	{
		TAG = LConstants.TAG_PREFIX + getClass().getSimpleName();
		super.onCreate();

		loggedIn = false;
		userDevice = null;
		user = null;

		prefs = getSharedPreferences(Preferences.PREFERENCES_NAME, Context.MODE_PRIVATE);
		editor = prefs.edit();

		loggedIn = prefs.getBoolean(Preferences.USER_LOGGED_IN, false);

		Realm.init(this);
		Realm realm = Realm.getDefaultInstance();

		if(prefs.getBoolean(Preferences.DEVICE_REGISTERED, false))
		{
			// TODO : handle if this device is registered to inner db
			String id = prefs.getString(Preferences.CURRENT_DEVICE_ID, null);
			userDevice = realm.where(Device.class).equalTo("_id", id).findFirst();
		}

		if(loggedIn)
		{
			// TODO : Load user info from db
			String id = prefs.getString(Preferences.CURRENT_USER_ID, null);
			user = realm.where(User.class).equalTo("_id", id).findFirst();
		}
	}

	public void setDevice(Device device)
	{
		editor.putString(Preferences.CURRENT_DEVICE_ID, device.getId());
		editor.commit();
		userDevice = device;
		Log.d(TAG, "user device id : " + device.getId());
	}

	public Device getDevice()
	{
		return userDevice;
	}
	public boolean isLoggedIn() {return loggedIn;}

	public void setUser(User user)
	{
		editor.putString(Preferences.CURRENT_USER_ID, user.getId());
		editor.commit();
		this.user = user;
	}
	public void setLoggedIn(boolean loggedIn) {this.loggedIn = loggedIn;}

	public User getUser()
	{
		return user;
	}
}
