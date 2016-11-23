package kr.latera.kakaotalk_replica.api.dto.req;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class UserUpdateRequestDto
{
	private String user_id;
	private String email;
	private String password;

	public UserUpdateRequestDto(String user_id, String email, String password)
	{
		this.user_id = user_id;
		this.email = email;
		this.password = password;
	}
}
