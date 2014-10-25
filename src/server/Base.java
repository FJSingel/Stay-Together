package server;

import com.firebase.client.Firebase;

public class Base {

	private final static String url = "https://staytogether.firebaseio.com/";
	private static Firebase root; 
	private static Firebase user;
	private static Firebase room;
	
	public static Firebase root(){
		if (root == null)
			root = new Firebase(url);
		return user;
	}
	
	public static Firebase user(){
		if (user == null)
			user = root().child("user");
		return user;
	}
	
	public static Firebase room(){
		if (room == null)
			room = root().child("user");
		return user;
	}
	
	public static void main(String[] args) {
		System.out.println("");
	}
	
}
