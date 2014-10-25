package backend.base;


import backend.test.TestWindow;

import com.firebase.client.Firebase;

public class Base {

	private final static String url = "https://staytogether.firebaseio.com/";
	private final static String user = url + "user/";
	private final static String room = url + "room/";

	public static Firebase root() {
		return new Firebase(url);
	}

	public static Firebase user() {
		return new Firebase(user);
	}
	
	public static Firebase user(String username) {
		return new Firebase(user + username + "/");
	}
	

	public static Firebase room() {
		return new Firebase(room);
	}
	
	public static Firebase room(String roomid) {
		return new Firebase(room + roomid + "/");
	}

	public static void main(String[] args) {
		TestWindow w = new TestWindow();
	}

}
