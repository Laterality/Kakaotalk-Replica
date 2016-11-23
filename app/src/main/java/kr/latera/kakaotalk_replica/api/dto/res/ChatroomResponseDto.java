package kr.latera.kakaotalk_replica.api.dto.res;

import kr.latera.kakaotalk_replica.api.dto.ChatroomDto;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class ChatroomResponseDto
{
	private String result;
	private ChatroomDto chatroom;

	private String getResult() { return result; }
	private ChatroomDto getChatroom() { return chatroom; }
}
