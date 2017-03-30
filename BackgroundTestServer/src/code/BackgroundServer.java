package code;

import helpers.Singleton;
import helpers.SingletonFactory;

import com.esotericsoftware.minlog.Log;

public class BackgroundServer {
	
	public static void main(String[] args) throws Exception {
		
		if(args.length != 3)
		{
			Log.error("Main", "Incorrect number of arguments, have " + args.length + " need 2.");
			throw new Exception("Usage: BackgroundServer [TCPport] [UDPport] [Log-Level]");
		}
		
		try {
			Log.set(Integer.valueOf(args[2]).intValue());
			ServerMain serverMain = (ServerMain) SingletonFactory.getSingletonInstance(Singleton.ServerMain);
			serverMain.init(Integer.parseInt(args[0], 10), Integer.parseInt(args[1], 10));			
		} catch (Exception e) {
			Log.error("Main", "Server exception: " + e.getMessage());
		}
	}
	
}
