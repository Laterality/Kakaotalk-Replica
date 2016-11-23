package kr.latera.kakaotalk_replica.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import io.realm.Realm;
import kr.latera.kakaotalk_replica.R;
import kr.latera.kakaotalk_replica.api.API_Internal;
import kr.latera.kakaotalk_replica.api.dto.DeviceDto;
import kr.latera.kakaotalk_replica.api.dto.res.DeviceResponseDto;
import kr.latera.kakaotalk_replica.api.tasks.AsyncCallback;
import kr.latera.kakaotalk_replica.api.tasks.DeviceCreateTask;
import kr.latera.kakaotalk_replica.utility.CustomApplication;
import kr.latera.kakaotalk_replica.utility.LConstants;
import kr.latera.kakaotalk_replica.view.EditTextWithClearButton;

/**
 * Created by jinwoo on 2016-09-25.
 */

public class SetupActivity extends AppCompatActivity implements View.OnClickListener
{

	private static String TAG;

	private EditTextWithClearButton etPN;
	private Button btnNext;

	private ProgressDialog pd;

	public SetupActivity()
	{
		TAG = getClass().getSimpleName();
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);

		etPN = (EditTextWithClearButton) findViewById(R.id.et_setup_phone_number);
		btnNext = (Button) findViewById(R.id.btn_setup_next);

		etPN.setInputType(InputType.TYPE_CLASS_PHONE);
		btnNext.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.btn_setup_next:
				submit();
				break;
		}
	}

	private void submit()
	{
		// If a notification message is tapped, any data accompanying the notification
		// message is available in the intent extras. In this sample the launcher
		// intent is fired when the notification is tapped, so any accompanying data would
		// be handled here. If you want a different intent fired, set the click_action
		// field of the notification message to the desired intent. The launcher intent
		// is used when no click_action is specified.
		//
		// Handle possible data accompanying notification message.
		// [START handle_data_extras]
		if (getIntent().getExtras() != null) {
			for (String key : getIntent().getExtras().keySet()) {
				String value = getIntent().getExtras().getString(key);
				Log.d(TAG, "Key: " + key + " Value: " + value);
			}
		}
		// [END handle_data_extras]

		// [START subscribe_topics]
//		FirebaseMessaging.getInstance().subscribeToTopic("news");
		// [END subscribe_topics]

		// Log and toast
//		String msg = getString(R.string.msg_subscribed);
//		Log.d(TAG, msg);
//		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

		// Get token
		String token = FirebaseInstanceId.getInstance().getToken();

		// Log and toast
		String msg = getString(R.string.msg_token_fmt, token);
		Log.d(TAG, msg);
//		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();


		// cc, phone, token, platform
		new DeviceCreateTask(new AsyncCallback<DeviceResponseDto>()
		{
			@Override
			public void onPreExecute()
			{
				pd = new ProgressDialog(SetupActivity.this);
				pd.setMessage("처리중");
				pd.setCancelable(false);
				pd.show();

			}

			@Override
			public void onPostExecute(DeviceResponseDto arg)
			{
				pd.dismiss();
				if(arg == null)
				{
					Toast.makeText(SetupActivity.this, "요청에 실패했습니다\n다시 시도해 주십시오", Toast.LENGTH_SHORT).show();
					return;
				}
				if(arg.getResult().equals(LConstants.RESULT_SUCCESS)
						|| arg.getResult().equals(LConstants.RESULT_DUPLICATE))
				{
					// 생성된 device 정보를 pref.에 저장
					DeviceDto dev = arg.getDevice();

					((CustomApplication)getApplication()).setDevice(dev.toDbo());

					startActivity(new Intent(SetupActivity.this, LoginActivity.class));
					finish();
				}
				else
				{
					Toast.makeText(SetupActivity.this, "요청에 실패했습니다\n다시 시도해 주십시오", Toast.LENGTH_SHORT).show();
				}
			}
		}).execute(
				"82",
				etPN.getText().toString(),
				token,
				API_Internal.PLATFORM_ANDROID
		);
	}
}
