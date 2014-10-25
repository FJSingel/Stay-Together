package user;

import server.Base;
import server.CompletionListener;
import server.Monitor;
import server.Util;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class User {

	private String username;
	private UserData data = UserData.invalid;
	private UserPData pdata;

	public User(String username) {
		this.username = username;
	}

	public void getUserDataNonBlocking(final CompletionListener listener) {
		data = UserData.invalid;
		Base.user(username).child("data")
				.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot snapshot) {
						data = UserData.deserialize(snapshot.getValue());
						if (listener != null)
							listener.completed(data);
					}

					@Override
					public void onCancelled(FirebaseError firebaseError) {
						data = null;
						if (listener != null)
							listener.completed(data);
					}
				});
	}

	public UserData getUserDataBlocking() throws InterruptedException {
		getUserDataNonBlocking(null);
		Monitor dataMonitor = new Monitor() {
			@Override
			public Object target() {
				return data;
			}
		};
		Util.block(dataMonitor, UserData.invalid, 10 * Util.s);
		return data;
	}

	public void getUserPDataNonBlocking(final CompletionListener listener) {
		/* TODO Authorization check */

		pdata = UserPData.invalid;
		Base.user(username).child("pdata")
				.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot snapshot) {
						pdata = UserPData.deserialize(snapshot.getValue());
						if (listener != null)
							listener.completed(pdata);
					}

					@Override
					public void onCancelled(FirebaseError firebaseError) {
						pdata = null;
						if (listener != null)
							listener.completed(pdata);
					}
				});
	}

	public UserPData getUserPDataBlocking() throws InterruptedException {
		getUserPDataNonBlocking(null);
		Monitor pdataMonitor = new Monitor() {
			@Override
			public Object target() {
				return pdata;
			}
		};
		Util.block(pdataMonitor, UserPData.invalid, 10 * Util.s);
		return pdata;
	}

}
