package service;

import helpers.KryoHelper;
import helpers.PacketHelper;
import helpers.Singleton;
import helpers.SingletonFactory;

import java.io.IOException;

import activity.MainActivity;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

public class ClientBase extends Client{

	public static final int waitingTime = 5000;
	public static PacketHelper packetHelper;

	public ClientBase() throws IOException {
		addListener((ClientListener)SingletonFactory.getSingletonInstance(Singleton.CLIENT_LISTENER));
		packetHelper = (PacketHelper) SingletonFactory.getSingletonInstance(Singleton.PACKET_HELPER);
		KryoHelper.registerClasses(getKryo());
		start();
		connect(waitingTime, MainActivity.ip, MainActivity.tcp, MainActivity.udp);
		Log.info("Client", "Client started.");
	}

}
