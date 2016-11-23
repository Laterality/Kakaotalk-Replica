package kr.latera.kakaotalk_replica.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import kr.latera.kakaotalk_replica.R;

/**
 * Created by jinwoo on 2016-09-25.
 */

public class EditTextWithClearButton extends LinearLayout
{
	private EditText etInput;
	private ImageView ivClear;

	public EditTextWithClearButton(Context context)
	{
		super(context);
		init(context);
	}

	public EditTextWithClearButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	public EditTextWithClearButton(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public EditTextWithClearButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context);
	}

	private void init(Context context)
	{
		View.inflate(context, R.layout.view_et_with_clear_btn, this);
		etInput = (EditText) findViewById(R.id.et_custom_et_input);
		ivClear = (ImageView) findViewById(R.id.iv_custom_et_clear);

		etInput.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
			{

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
			{
				if(charSequence.length() > 0)
				{
					ivClear.setVisibility(View.VISIBLE);
				}
				else
				{
					ivClear.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void afterTextChanged(Editable editable)
			{

			}
		});

		ivClear.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				etInput.setText("");
			}
		});
	}

	public Editable getText()
	{
		return etInput.getText();
	}

	public EditText getEditText()
	{
		return etInput;
	}

	public void setText(CharSequence chars)
	{
		etInput.setText(chars);
	}

	public void setInputType(int inputType)
	{
		etInput.setInputType(inputType);
	}
}
