package kr.latera.kakaotalk_replica.api.dto.req;

/**
 * Created by jinwoo on 2016-11-13.
 */

public class DeviceRequestDto
{
	private String cc;
	private String phone;
	private String token;
	private String platform;

	public DeviceRequestDto(String cc, String phone, String token, String platform)
	{
		this.cc = cc;
		this.phone = phone;
		this.token = token;
		this.platform = platform;
	}
}
