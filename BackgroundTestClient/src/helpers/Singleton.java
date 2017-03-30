package helpers;

import com.esotericsoftware.minlog.Log;

public enum Singleton {

	LOCATION_HELPER("helpers.LocationHelper"), 
	CLIENT("service.ClientBase"), 
	CLIENT_RUNNABLE("runnables.ClientRunnable"),
	DISCONNECT_RUNNABLE("runnables.DisconnectRunnable"),
	PACKET_HELPER("helpers.PacketHelper"),
	CLIENT_LISTENER("service.ClientListener");

	Class<?> classType;
	
	Singleton(String s)
	{
		try {
			this.classType = Class.forName(s);
		} catch (ClassNotFoundException e) {
			Log.error("Singleton", e.getMessage());
		}
	}
}
