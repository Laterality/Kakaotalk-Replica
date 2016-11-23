package kr.latera.kakaotalk_replica.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import kr.latera.kakaotalk_replica.Preferences;
import kr.latera.kakaotalk_replica.R;
import kr.latera.kakaotalk_replica.api.dto.req.LoginRequestDto;
import kr.latera.kakaotalk_replica.api.dto.res.UserResponseDto;
import kr.latera.kakaotalk_replica.api.tasks.AsyncCallback;
import kr.latera.kakaotalk_replica.api.tasks.UserLoginTask;
import kr.latera.kakaotalk_replica.utility.CustomApplication;
import kr.latera.kakaotalk_replica.utility.LConstants;

/**
 * Created by jinwoo on 2016-09-25.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher
{
	private TextView tvForgotInfo;
	private Button btnLogin;
	private Button btnSkip;
	private EditText etEmail;
	private EditText etPassword;

	private ProgressDialog pd;

	private boolean validityEmail;
	private boolean validityPassword;


	public LoginActivity()
	{
		validityEmail = false;
		validityPassword = false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);


		tvForgotInfo = (TextView) findViewById(R.id.tv_login_forgot_info);
		btnLogin = (Button) findViewById(R.id.btn_activity_login_login);
		btnSkip = (Button)findViewById(R.id.btn_activity_login_skip);
		etEmail = (EditText) findViewById(R.id.et_login_email);
		etPassword = (EditText) findViewById(R.id.et_login_password);

		if(Build.VERSION.SDK_INT < 24)
		{
			tvForgotInfo.setText(Html.fromHtml("<u>" + getString(R.string.msg_for_forgot_ur_info) + "</u>"));
		}
		else
		{
			tvForgotInfo.setText(Html.fromHtml("<u>" + getString(R.string.msg_for_forgot_ur_info) + "</u>", 0));
		}

		btnLogin.setOnClickListener(this);
		btnSkip.setOnClickListener(this);

		etEmail.addTextChangedListener(this);
		etPassword.addTextChangedListener(this);

	}

	@Override
	public void onClick(View view)
	{
		switch(view.getId())
		{
			case R.id.btn_activity_login_login:
				if(validityEmail && validityPassword)
				{
					new UserLoginTask(new AsyncCallback<UserResponseDto>()
					{
						@Override
						public void onPreExecute()
						{
							pd = new ProgressDialog(LoginActivity.this);
							pd.setMessage("로그인 중");
							pd.setCancelable(false);
							pd.show();
						}

						@Override
						public void onPostExecute(UserResponseDto arg)
						{
							pd.dismiss();
							if(arg.getResult().equals(LConstants.RESULT_SUCCESS))
							{
								((CustomApplication)getApplication()).setUser(arg.getUser().toDbo());
								((CustomApplication)getApplication()).setLoggedIn(true);
								startActivity(new Intent(LoginActivity.this, MainActivity.class));
								finish();
							}
							else if(arg.getResult().equals(LConstants.RESULT_INCORRECT))
							{
								Toast.makeText(
										LoginActivity.this,
										getString(R.string.msg_login_incorrect),
										Toast.LENGTH_SHORT).show();
							}
							else
							{
								Toast.makeText(
										LoginActivity.this,
										getString(R.string.msg_login_error),
										Toast.LENGTH_SHORT).show();
							}
						}
					}).execute(etEmail.getText().toString(), etPassword.getText().toString());
				}
				break;
			case R.id.btn_activity_login_skip:
				startActivity(new Intent(this, TermsActivity.class));
				finish();
				break;
		}
	}

	@Override
	public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
	{

	}

	@Override
	public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
	{
		validityEmail = LConstants.PATTERN_EMAIL.matcher(etEmail.getText()).matches();
		validityPassword = etPassword.getText().length() >= LConstants.PASSSWORD_MIN_LENGTH;

		if(validityEmail && validityPassword)
		{
			if(Build.VERSION.SDK_INT >= 23)
			{
				btnLogin.setTextColor(getColor(R.color.colorWhite));
			}
			else
			{
				btnLogin.setTextColor(getResources().getColor(R.color.colorWhite));
			}
		}
		else
		{
			if(Build.VERSION.SDK_INT >= 23)
			{
				btnLogin.setTextColor(getColor(R.color.colorTextBrown2));
			}
			else
			{
				btnLogin.setTextColor(getResources().getColor(R.color.colorTextBrown2));
			}
		}
	}

	@Override
	public void afterTextChanged(Editable editable)
	{

	}
}
