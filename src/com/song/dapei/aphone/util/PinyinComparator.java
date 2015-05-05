package com.song.dapei.aphone.util;

import java.util.Comparator;

import com.song.dapei.aphone.bean.XUser;

public class PinyinComparator implements Comparator<XUser> {

	public int compare(XUser o1, XUser o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
