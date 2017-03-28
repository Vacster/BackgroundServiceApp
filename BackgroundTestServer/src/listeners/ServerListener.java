package listeners;

import packets.*;
import code.ServerMain;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ServerListener extends Listener{
	
	private static ServerListener context;
	
	private ServerListener() {
		Log.info("ServerListener", "ServerListener started");
	}
	
	public static ServerListener getInstance(ServerMain serverMain){
		if(context == null)
			context = new ServerListener();
		
		return context;
	}

	@Override
	public void connected(Connection arg0) {
		Log.debug("ServerListener", "ID: " + arg0.getID() + " connected.");
		super.connected(arg0);
	}
	
	@Override
	public void disconnected(Connection arg0) {
		Log.debug("ServerListener", "ID: " + arg0.getID() + " disconnected.");

		super.disconnected(arg0);
	}
	
	@Override
	public void received(Connection arg0, Object arg1) {
		Log.debug("ServerListener", "ID: " + arg0.getID() + " packet received.");
		if(arg1 instanceof AnswerPacket){
			AnswerPacket packet = (AnswerPacket) arg1;
			Log.info("ServerListener", "AnswerPacket: " + packet.str);
		}
		super.received(arg0, arg1);
	}
	
	
	
}