package code;

import helpers.KryoHelper;

import java.io.IOException;

import listeners.ServerListener;

import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class ServerMain extends Server{

	private static ServerMain context;

	private ServerMain(int tcp, int udp) throws IOException{
		bind(tcp, udp);
		start();
		KryoHelper.registerClasses(getKryo());	
		addListener(ServerListener.getInstance(this));
		
		Log.info("ServerMain", "Server started.");
	}

	public static ServerMain getInstance(int tcp, int udp) throws IOException{
		if(context == null)
			context = new ServerMain(tcp, udp);

		return context;
	}
	
}