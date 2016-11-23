package kr.latera.kakaotalk_replica.api.dto;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import kr.latera.kakaotalk_replica.api.dbo.User;

/**
 * Created by jinwoo on 2016-11-20.
 */

public class Message extends RealmObject
{
	private String type;
	private User sender;
	@Index private String chatroomId;


	public String getType() {return type;}
	public User getSender() {return sender;}
	public String getChatroom() {return chatroomId;}
}
