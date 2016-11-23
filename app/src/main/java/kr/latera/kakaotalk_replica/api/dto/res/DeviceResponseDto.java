package kr.latera.kakaotalk_replica.api.dto.res;

import kr.latera.kakaotalk_replica.api.dto.DeviceDto;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class DeviceResponseDto
{
	private String result;
	private DeviceDto device;

	public String getResult() { return result; }
	public DeviceDto getDevice() { return device; }
}
