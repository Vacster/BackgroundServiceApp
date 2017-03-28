package helpers;

import service.ClientBase;

import com.esotericsoftware.minlog.Log;

public class PacketHelper {

	private static PacketHelper context;
	private static ClientBase client;
	
	private PacketHelper() {}
	
	public static PacketHelper instanceOf(){
		if(context == null)
			context = new PacketHelper();
		
		return context;
	}

	public void sendPacket(Object obj){
		try {
			if(client == null)
				client = ClientBase.getSafeInstance();
			if(client.isConnected())
				client.sendTCP(obj);
			else
				Log.error("PacketHelper", "Client is Disconnected, Packet Not Sent");
		} catch (NullPointerException f){
			Log.error("PacketHelper", f.toString());
		}
	}
	
}
