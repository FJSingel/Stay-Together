package com.reduber.backend.room;

import java.util.HashMap;
import java.util.Map;

public class RoomData {
	public static final RoomData invalid = new RoomData("Invalid");
	public String name;

	private RoomData(String name) {
		this.name = name;
	}

	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return map;
	}

	public static RoomData deserialize(Object o) {
		if (o == null)
			return null;
		Map<String, Object> map = (Map<String, Object>) o;
		return new RoomData((String) map.get("name"));
	}
}
