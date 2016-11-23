package kr.latera.kakaotalk_replica.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import kr.latera.kakaotalk_replica.R;

/**
 * Created by jinwoo on 2016-09-25.
 */

public class TabTitleView extends LinearLayout
{
	private View v;
	private ImageView ivIcon;
	private TextView tvCount;

	public TabTitleView(Context context, int icon)
	{
		super(context);
		init(context, icon);
	}

	private void init(Context context, int icon)
	{
		v = View.inflate(context, R.layout.view_tabmenu, this);
		ivIcon = (ImageView)v.findViewById(R.id.iv_tab_icon);
		tvCount = (TextView) findViewById(R.id.tv_tab_count);
		ivIcon.setImageDrawable(context.getDrawable(icon));
	}

	public void setCount(int count)
	{
		if(count == 0)
		{
			tvCount.setVisibility(View.INVISIBLE);
		}
		else
		{
			tvCount.setVisibility(View.VISIBLE);
			tvCount.setText(String.valueOf(count));
		}
	}
}
