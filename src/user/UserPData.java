package user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPData {
	public static final UserPData invalid = new UserPData(null);
	public List<Long> gids;

	private UserPData(List<Long> gids) {
		this.gids = gids;
	}

	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gids", gids);
		return map;
	}

	public static UserPData deserialize(Object o) {
		if (o == null)
			return null;
		Map<String, Object> map = (Map<String, Object>) o;
		return new UserPData((List<Long>) map.get("gids"));
	}
}