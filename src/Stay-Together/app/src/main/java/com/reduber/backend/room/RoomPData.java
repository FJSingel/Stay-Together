package com.reduber.backend.room;

import java.util.HashMap;
import java.util.Map;

public class RoomPData {
	public static final RoomPData invalid = new RoomPData(null);
	public Map<String, Boolean> members;

	private RoomPData(Map<String, Boolean> members) {
		this.members = members;
	}

	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("members", members);
		return map;
	}

	public static RoomPData deserialize(Object o) {
		if (o == null)
			return null;
		Map<String, Object> map = (Map<String, Object>) o;
		return new RoomPData((Map<String, Boolean>) map.get("members"));
	}
}
