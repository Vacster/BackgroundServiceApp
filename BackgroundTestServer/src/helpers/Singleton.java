package helpers;

import com.esotericsoftware.minlog.Log;

public enum Singleton {

	ServerMain("code.ServerMain"), ServerListener("listeners.ServerListener");

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
