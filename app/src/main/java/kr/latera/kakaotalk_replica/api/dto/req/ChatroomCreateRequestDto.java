package kr.latera.kakaotalk_replica.api.dto.req;

import java.util.List;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class ChatroomCreateRequestDto
{
	private List<String> members;
	private String title;

	public ChatroomCreateRequestDto(List<String> members, String title)
	{
		this.members = members;
		this.title = title;
	}
}
