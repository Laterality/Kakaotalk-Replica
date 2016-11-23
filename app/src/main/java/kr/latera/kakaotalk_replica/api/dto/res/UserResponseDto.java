package kr.latera.kakaotalk_replica.api.dto.res;

import kr.latera.kakaotalk_replica.api.dto.UserDto;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class UserResponseDto
{
	private String result;
	private UserDto user;

	public String getResult() { return result; }
	public UserDto getUser() { return user; }
}
