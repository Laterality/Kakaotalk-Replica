package kr.latera.kakaotalk_replica.api.dto;

import kr.latera.kakaotalk_replica.api.dbo.Device;

/**
 * Created by jinwoo on 2016-11-09.
 * Device Data Object with owner id of User Data Object
 */

public class DeviceDto
{
	private String _id;
	private String owner;
	private String platform;
	private String token;
	private Number _number;

	public void setOwner(String user)
	{
		owner = user;
	}

	public String getId() {return _id;}
	public String getPlatform() {return platform;}
	public String getToken() {return token;}
	public String getOwner() {return owner;}
	public Number getNumber() {return _number;}

	public Device toDbo()
	{
		return new Device(_id, owner, platform, token, _number);
	}


	public static class Number
	{
		private String country;
		private String _number;

		private Number(String country, String number)
		{
			this.country = country;
			this._number = number;
		}

		public static Number fromString(String number)
		{
			String[] splitted = number.split("/");
			return new Number(splitted[0], splitted[1]);
		}

		public String getCountryCode() {return country;}
		public String getNumber() {return _number;}
		public String toString()
		{
			return country + "/" + _number;
		}
	}

}
