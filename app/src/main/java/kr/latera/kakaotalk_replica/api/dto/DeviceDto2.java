package kr.latera.kakaotalk_replica.api.dto;

import kr.latera.kakaotalk_replica.api.dto.DeviceDto;

/**
 * Created by jinwoo on 2016-11-20.
 * Device Data Object with owner id of string
 */

public class DeviceDto2
{
	private String _id;
	private String owner;
	private String platform;
	private String token;
	private DeviceDto.Number _number;

	public class Number
	{
		private String country;
		private String _number;

		public String getCountryCode() {return country;}
		public String getNumber() {return _number;}
	}

	public String getId() {return _id;}
	public String getToken() {return token;}
}
