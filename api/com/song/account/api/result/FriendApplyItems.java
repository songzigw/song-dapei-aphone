package com.song.account.api.result;

import java.util.List;

import com.song.account.entity.FriendApply;
import com.song.commons.api.Result;

public class FriendApplyItems extends Result {

	private static final long serialVersionUID = 7793822382480978841L;

	List<FriendApply> friendApplyList;

	public List<FriendApply> getFriendApplyList() {
		return friendApplyList;
	}

	public void setFriendApplyList(List<FriendApply> friendApplyList) {
		this.friendApplyList = friendApplyList;
	}

}