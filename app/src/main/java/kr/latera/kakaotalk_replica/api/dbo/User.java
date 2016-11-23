package kr.latera.kakaotalk_replica.api.dbo;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import kr.latera.kakaotalk_replica.api.dto.ChatroomDto;
import kr.latera.kakaotalk_replica.api.dto.DeviceDto;
import kr.latera.kakaotalk_replica.api.dto.FriendDto;

/**
 * Created by jinwoo on 2016-11-22.
 */
public class User extends RealmObject
{
	@PrimaryKey
	private String _id;
	private String email;
	private String username;
	private String profile_img;
	private long lastModDate;
	private RealmList<Device> devices;
	private RealmList<User> friends;
	private RealmList<Chatroom> chatrooms;

	public User() {}

	public User(String id, String email, String username, String profile_img, long lastModDate, List<DeviceDto> devices, List<FriendDto> friends, List<ChatroomDto> chatrooms)
	{
		this._id = id;
		this.email = email;
		this.username = username;
		this.profile_img = profile_img;
		this.lastModDate = lastModDate;
		this.devices = new RealmList<>();
		this.friends = new RealmList<>();
		this.chatrooms = new RealmList<>();
		for (FriendDto _u : friends)
		{
			this.friends.add(_u.toDbo());
		}
		for (DeviceDto d : devices)
		{
			this.devices.add(d.toDbo());
		}
		for (ChatroomDto c : chatrooms)
		{
			this.chatrooms.add(c.toDbo());
		}
	}


	public String getId()
	{
		return _id;
	}

	public String getEmail()
	{
		return email;
	}

	public String getUsername()
	{
		return username;
	}

	public String getProfileImageURL()
	{
		return profile_img;
	}

	public long getLastModDate()
	{
		return lastModDate;
	}

	public List<Device> getDevices()
	{
		return devices;
	}

	public List<User> getFriends()
	{
		return friends;
	}

	public List<Chatroom> getChatrooms()
	{
		return chatrooms;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setLastModDate(long now)
	{
		this.lastModDate = now;
	}

	public void setProfileImage(String url)
	{
		this.profile_img = url;
	}
}
