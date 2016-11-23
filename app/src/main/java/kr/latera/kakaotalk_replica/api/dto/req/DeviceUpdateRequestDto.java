package kr.latera.kakaotalk_replica.api.dto.req;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class DeviceUpdateRequestDto
{
	private String device_id;
	private String user_id;

	public DeviceUpdateRequestDto(String device_id, String user_id)
	{
		this.device_id = device_id;
		this.user_id = user_id;
	}
}
