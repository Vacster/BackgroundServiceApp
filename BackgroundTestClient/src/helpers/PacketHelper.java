package helpers;

import service.ClientBase;

import com.esotericsoftware.minlog.Log;

public class PacketHelper {

	private static ClientBase client;

	public void sendPacket(Object obj){
		if(client == null)
			client = ((ClientBase) SingletonFactory.getSingletonInstance(Singleton.CLIENT));
		if(client.isConnected())
			client.sendTCP(obj);
		else
			Log.error("PacketHelper", "Client is Disconnected, Packet Not Sent");
	}
	
}
