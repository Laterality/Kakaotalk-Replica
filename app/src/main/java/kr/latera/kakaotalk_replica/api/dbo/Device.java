package kr.latera.kakaotalk_replica.api.dbo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import kr.latera.kakaotalk_replica.api.dto.DeviceDto;
import kr.latera.kakaotalk_replica.api.dto.UserDto;

/**
 * Created by jinwoo on 2016-11-22.
 */
public class Device extends RealmObject
{
	@PrimaryKey
	private String _id;
	private String owner;
	private String platform;
	private String token;
	private String number;

	public Device() {}

	public Device(String id, String owner, String platform, String token, DeviceDto.Number number)
	{
		_id = id;
		if(owner != null) {this.owner = owner;}
		this.platform = platform;
		this.token = token;
		this.number = number.toString();
	}

	public void setOwner(String user)
	{
		owner = user;
	}

	public String getId()
	{
		return _id;
	}

	public String getPlatform()
	{
		return platform;
	}

	public String getToken()
	{
		return token;
	}

	public String getOwner()
	{
		return owner;
	}

}
