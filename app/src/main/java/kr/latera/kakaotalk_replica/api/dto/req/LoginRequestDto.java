package kr.latera.kakaotalk_replica.api.dto.req;

/**
 * Created by jinwoo on 2016-11-16.
 */

public class LoginRequestDto
{
	private String email;
	private String password;

	public LoginRequestDto(String email, String password)
	{
		this.email = email;
		this.password = password;
	}
}
