package com.song.dapei.aphone.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.song.account.entity.User;

public class CollectionUtils {

	public static boolean isNotNull(Collection<?> collection) {
		if (collection != null && collection.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * list转map以用户昵称为key
	 * 
	 * @param users
	 * @return
	 */
	public static Map<String, User> list2map(List<User> users) {
		Map<String, User> friends = new HashMap<String, User>();
		for (User user : users) {
			friends.put(user.getNickName(), user);
		}
		return friends;
	}

	/**
	 * mapתlist
	 * 
	 * @Title: map2list
	 * @return List<User>
	 * @throws
	 */
	public static List<User> map2list(Map<String, User> maps) {
		List<User> users = new ArrayList<User>();
		Iterator<Entry<String, User>> iterator = maps.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, User> entry = iterator.next();
			users.add(entry.getValue());
		}
		return users;
	}
}
