package com.song.account.api.result;

import java.util.List;

import com.song.account.entity.FriendMessage;
import com.song.commons.api.Result;

public class FriendMessageItems extends Result {

	private static final long serialVersionUID = 7793822382480978841L;

	List<FriendMessage> friendMessageList;

	public List<FriendMessage> getFriendMessageList() {
		return friendMessageList;
	}

	public void setFriendMessageList(List<FriendMessage> friendMessageList) {
		this.friendMessageList = friendMessageList;
	}

}