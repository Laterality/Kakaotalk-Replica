package kr.latera.kakaotalk_replica.api.dto;

import java.util.ArrayList;
import java.util.List;

import kr.latera.kakaotalk_replica.api.dbo.User;

/**
 * Created by jinwoo on 2016-11-22.
 */

public class FriendDto
{
	private String _id;
	private String email;
	private String username;
	private String profile_img;
	private long lastModDate;
	private List<DeviceDto> devices;
	private List<ExtFriendDto> friends;
	private List<ChatroomDto> chatrooms;


	public String getId() {return _id;}
	public String getEmail() {return email;}
	public String getUsername() {return username;}
	public String getProfileImageURL() {return profile_img;}
	public long getLastModDate() {return lastModDate;}
	public List<DeviceDto> getDevices() {return devices;}
	public List<ExtFriendDto> getFriends() {return friends;}
	public List<ChatroomDto> getChatrooms() {return chatrooms;}

	public void setUsername(String username) {this.username = username;}
	public void setLastModDate(long now) {this.lastModDate = now;}
	public void setProfileImage(String url) {this.profile_img = url;}

	public User toDbo()
	{
		return new User(_id, email, username, profile_img, lastModDate,
				devices, new ArrayList<FriendDto>(), chatrooms);
	}

}
