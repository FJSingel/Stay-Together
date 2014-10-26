package backend.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGData {
	public static final UserGData invalid = new UserGData(null);
	public String location;

	private UserGData(String location) {
		this.location = location;
	}

	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("location", location);
		return map;
	}

	public static UserGData deserialize(Object o) {
		if (o == null)
			return null;
		Map<String, Object> map = (Map<String, Object>) o;
		return new UserGData((String) map.get("location"));
	}
}