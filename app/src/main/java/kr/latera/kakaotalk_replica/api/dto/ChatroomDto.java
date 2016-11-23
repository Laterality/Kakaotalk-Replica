package kr.latera.kakaotalk_replica.api.dto;

import java.util.List;

import kr.latera.kakaotalk_replica.api.dbo.Chatroom;

/**
 * Created by jinwoo on 2016-11-09.
 */

public class ChatroomDto
{
	private String _id;
	private String title;
	private List<UserDto> members;

	public String getId() {return _id;}
	public String getTitle() {return title;}
	public List<UserDto> getMembers() {return members;}

	public Chatroom toDbo()
	{
		Chatroom c = new Chatroom(_id, title, members);

		return c;
	}

}
