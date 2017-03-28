package service;

import helpers.KryoHelper;
import helpers.PacketHelper;

import java.io.IOException;

import activity.MainActivity;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

public class ClientBase extends Client{

	public static final int waitingTime = 5000;
	public static PacketHelper packetHelper;
	private static ClientBase context;

	private ClientBase() throws IOException {
		addListener(ClientListener.getInstance());
		packetHelper = PacketHelper.instanceOf();
		KryoHelper.registerClasses(getKryo());
		start();
		connect(waitingTime, MainActivity.ip, MainActivity.tcp, MainActivity.udp);
		Log.info("Client", "Client started.");
	}

	public static ClientBase getInstance() throws IOException{
		if(context == null)
			context = new ClientBase();
		
		return context;
	}
	
	public static ClientBase getSafeInstance() throws NullPointerException{
		if(context == null)
			throw new NullPointerException("ClientBase hasn't been initialized");
		
		return context;
	}

}
