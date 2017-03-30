package code;

import helpers.KryoHelper;

import java.io.IOException;

import listeners.ServerListener;

import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class ServerMain extends Server{
	
	public ServerMain() {
		Log.trace("ServerMain", "Instance Created.");
	}
	
	public void init(int tcp, int udp) throws IOException{
		bind(tcp, udp);
		start();
		KryoHelper.registerClasses(getKryo());	
		addListener(ServerListener.getInstance(this));
		
		Log.info("ServerMain", "Server started.");
	}

}