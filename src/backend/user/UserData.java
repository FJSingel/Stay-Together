package backend.user;

import java.util.HashMap;
import java.util.Map;

public class UserData {
	public static final UserData invalid = new UserData("Invalid");
	public String name;

	private UserData(String name) {
		this.name = name;
	}

	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return map;
	}

	public static UserData deserialize(Object o) {
		if (o == null)
			return null;
		Map<String, Object> map = (Map<String, Object>) o;
		return new UserData((String) map.get("name"));
	}
}