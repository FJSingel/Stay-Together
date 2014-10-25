package server;

import com.firebase.client.Firebase;

public class Base {

	private final static String url = "https://staytogether.firebaseio.com/";
	public final static Firebase root  = new Firebase(url);
	public final static Firebase user  = root.child("user");
	public final static Firebase room  = root.child("room");
	
	
	public static void main(String[] args) {
		room.setValue("VALUE");
		System.out.println("done");
	}
	
}
