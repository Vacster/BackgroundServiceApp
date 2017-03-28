package helpers;

import packets.*;

import com.esotericsoftware.kryo.Kryo;

public class KryoHelper {

	public static void registerClasses(Kryo kryo)
	{
		kryo.register(AnswerPacket.class);
	}
	
}
