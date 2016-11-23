package kr.latera.kakaotalk_replica.api.dto.req;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class UserTempCreateRequestDto
{
	private String username;
	private String device_id;

	public UserTempCreateRequestDto(String username, String device_id)
	{
		this.username = username;
		this.device_id = device_id;
	}
}
