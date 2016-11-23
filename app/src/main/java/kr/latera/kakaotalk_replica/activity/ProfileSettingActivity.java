package kr.latera.kakaotalk_replica.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import kr.latera.kakaotalk_replica.R;
import kr.latera.kakaotalk_replica.api.dbo.User;
import kr.latera.kakaotalk_replica.api.dto.DeviceDto;
import kr.latera.kakaotalk_replica.api.dto.UserDto;
import kr.latera.kakaotalk_replica.api.dto.res.UserResponseDto;
import kr.latera.kakaotalk_replica.api.tasks.AsyncCallback;
import kr.latera.kakaotalk_replica.api.tasks.UserCreateTask;
import kr.latera.kakaotalk_replica.utility.CustomApplication;
import kr.latera.kakaotalk_replica.utility.LConstants;
import kr.latera.kakaotalk_replica.view.EditTextWithClearButton;

/**
 * Created by jinwoo on 2016-11-01.
 */

public class ProfileSettingActivity extends AppCompatActivity implements View.OnClickListener
{
	private static String TAG;
	private Button btnBegin;
	private EditText etUsername;
	private ProgressDialog pd;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		TAG = LConstants.TAG_PREFIX + getClass().getSimpleName();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_setting);

		pd = new ProgressDialog(ProfileSettingActivity.this);
		pd.setMessage("처리 중");
		pd.setCancelable(false);

		btnBegin = (Button) findViewById(R.id.btn_activity_profile_setting_begin);
		etUsername = ((EditTextWithClearButton) findViewById(R.id.et_profile_setting_username)).getEditText();

		btnBegin.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.btn_activity_profile_setting_begin:
//				startActivity(new Intent(this, MainActivity.class));
				Log.d(TAG, "device id : " + ((CustomApplication)getApplication()).getDevice().getId());
				new UserCreateTask(new AsyncCallback<UserResponseDto>()
				{
					@Override
					public void onPreExecute()
					{
						pd.show();
					}

					@Override
					public void onPostExecute(UserResponseDto arg)
					{
						pd.dismiss();
						if(arg == null)
						{
							Toast.makeText(ProfileSettingActivity.this,
									"오류가 발생했습니다\n잠시후 다시 시도해 주세요",
									Toast.LENGTH_SHORT)
									.show();
							return;
						}
						else if(arg.getResult().equals(LConstants.RESULT_SUCCESS))
						{
							// 계정 생성 성공
							User u = arg.getUser().toDbo();

							((CustomApplication)getApplication()).setUser(u);
							((CustomApplication)getApplication()).setLoggedIn(true);

							Realm realm = Realm.getDefaultInstance();
							realm.beginTransaction();
							realm.copyToRealm(u);
							realm.commitTransaction();

							startActivity(new Intent(ProfileSettingActivity.this, MainActivity.class));
							finish();
						}
						else if(arg.getResult().equals(LConstants.RESULT_ERROR))
						{
							Toast.makeText(ProfileSettingActivity.this,
									"오류가 발생했습니다\n잠시후 다시 시도해 주세요",
									Toast.LENGTH_SHORT)
									.show();
							return;
						}
					}
				}).execute(
						etUsername.getText().toString(),
						((CustomApplication)getApplication()).getDevice().getId()
				);

				break;
		}
	}
}
