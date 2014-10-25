package backend.room;


import backend.base.Base;
import backend.base.CompletionListener;
import backend.base.Monitor;
import backend.base.Util;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Room {

	private String roomid;
	private RoomData data = RoomData.invalid;
	private RoomPData pdata;

	public Room(String roomid) {
		this.roomid = roomid;
	}

	public void getRoomDataNonBlocking(final CompletionListener listener) {
		data = RoomData.invalid;
		Base.room(roomid).child("data")
				.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot snapshot) {
						data = RoomData.deserialize(snapshot.getValue());
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

	public RoomData getRoomDataBlocking() throws InterruptedException {
		getRoomDataNonBlocking(null);
		Monitor dataMonitor = new Monitor() {
			@Override
			public Object target() {
				return data;
			}
		};
		Util.block(dataMonitor, RoomData.invalid, 10 * Util.s);
		return data;
	}

	public void getRoomPDataNonBlocking(final CompletionListener listener) {
		/* TODO Authorization check */

		pdata = RoomPData.invalid;
		Base.room(roomid).child("pdata")
				.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot snapshot) {
						pdata = RoomPData.deserialize(snapshot.getValue());
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

	public RoomPData getRoomPDataBlocking() throws InterruptedException {
		getRoomPDataNonBlocking(null);
		Monitor pdataMonitor = new Monitor() {
			@Override
			public Object target() {
				return pdata;
			}
		};
		Util.block(pdataMonitor, RoomPData.invalid, 10 * Util.s);
		return pdata;
	}
}
