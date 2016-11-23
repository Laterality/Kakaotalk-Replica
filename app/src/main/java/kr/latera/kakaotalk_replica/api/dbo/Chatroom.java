package kr.latera.kakaotalk_replica.api.dbo;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import kr.latera.kakaotalk_replica.api.dto.UserDto;

/**
 * Created by jinwoo on 2016-11-22.
 */
public class Chatroom extends RealmObject
{
	@PrimaryKey
	private String _id;
	private String title;
	private RealmList<User> members;

	public Chatroom()
	{
	}

	public Chatroom(String id, String title, List<UserDto> members)
	{
		this._id = id;
		this.title = title;
		this.members = new RealmList<>();
		for (UserDto u : members)
		{
			this.members.add(u.toDbo());
		}
	}

	public String getId()
	{
		return _id;
	}

	public String getTitle()
	{
		return title;
	}

	public List<User> getMembers()
	{
		return members;
	}
}
