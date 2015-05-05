package com.song.account.api.result;

import java.util.List;

import com.song.account.entity.Friend;
import com.song.commons.api.Result;

public class FriendItems extends Result {

	private static final long serialVersionUID = -4255076721934489790L;

	List<Friend> friendList;

	public List<Friend> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Friend> friendList) {
		this.friendList = friendList;
	}

}
