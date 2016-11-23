package kr.latera.kakaotalk_replica.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import kr.latera.kakaotalk_replica.api.dbo.User;
import kr.latera.kakaotalk_replica.utility.LConstants;

/**
 * Created by jinwoo on 2016-11-23.
 */

public class FriendListAdapter extends BaseAdapter
{
	private static String TAG;
	private Context mContext;
	private List<User> friends;

	public FriendListAdapter(@NonNull Context context, List<User> list)
	{
		TAG = LConstants.TAG_PREFIX + getClass().getSimpleName();
		mContext = context;
		friends = new ArrayList<>();
		if(list != null)
		{
			friends.addAll(list);
		}
	}


	@Override
	public int getCount()
	{
		return friends.size();
	}

	@Override
	public User getItem(int position)
	{
		return friends.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup root)
	{

	}


	private class ViewHolder
	{

	}
}
