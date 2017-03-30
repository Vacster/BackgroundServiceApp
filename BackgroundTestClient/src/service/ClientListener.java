package service;

import packets.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ClientListener extends Listener{

	@Override
	public void connected(Connection arg0) {
		Log.debug("ClientListener", "Connected to server, ID is " + arg0.getID());
		
		super.connected(arg0);
	}
	
	@Override
	public void disconnected(Connection arg0) {
		Log.debug("ClientListener", "Disconnected from server");
		
		super.disconnected(arg0);
	}
	
	@Override
	public void received(Connection arg0, Object arg1) {
		Log.debug("ClientListener", "Received packet from server");
		if(arg1 instanceof AnswerPacket)
			Log.debug("ClientListener", "Message: " + ((AnswerPacket)arg1).str);
		super.received(arg0, arg1);
	}
	
}
