package kr.latera.kakaotalk_replica.fcm;

import android.app.Service;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jinwoo on 2016-09-11.
 */
public class KR_FirebaseInstanceIDService extends FirebaseInstanceIdService
{
	private static final String TAG = "IIDService";
	@Override
	public void onTokenRefresh() {
		// Get updated InstanceID token.
		String refreshedToken = FirebaseInstanceId.getInstance().getToken();
		Log.d(TAG, "Refreshed token: " + refreshedToken);

		// If you want to send messages to this application instance or
		// manage this apps subscriptions on the server side, send the
		// Instance ID token to your app server.
		sendRegistrationToServer(refreshedToken);
	}

	private void sendRegistrationToServer(String token)
	{

	}
}
