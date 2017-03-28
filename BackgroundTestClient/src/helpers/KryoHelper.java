package helpers;

import packets.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.minlog.Log;

public class KryoHelper {

	public static void registerClasses(Kryo kryo)
	{
		kryo.register(AnswerPacket.class);
		Log.trace("KryoRegister", "Packets Registered Succesfully");
	}
	
}
