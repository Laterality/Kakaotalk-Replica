package kr.latera.kakaotalk_replica.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import kr.latera.kakaotalk_replica.R;

/**
 * Created by jinwoo on 2016-11-01.
 */

public class TermsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener
{

	private CheckBox cbForUse;
	private CheckBox cbForPrivacy;

	private boolean isAcceptedForUse;
	private boolean isAcceptedForPrivacy;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_terms);

		isAcceptedForUse = false;
		isAcceptedForPrivacy = false;

		cbForUse = (CheckBox)findViewById(R.id.cb_activity_terms_use);
		cbForPrivacy = (CheckBox)findViewById(R.id.cb_activity_terms_privacy);

		cbForUse.setOnCheckedChangeListener(this);
		cbForPrivacy.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton compoundButton, boolean b)
	{
		if(compoundButton.equals(cbForUse))
		{
			isAcceptedForUse = b;
		}
		else if(compoundButton.equals(cbForPrivacy))
		{
			isAcceptedForPrivacy = b;
		}

		if(isAcceptedForUse && isAcceptedForPrivacy)
		{
			startActivity(new Intent(this, ProfileSettingActivity.class));
			finish();
		}
	}
}
