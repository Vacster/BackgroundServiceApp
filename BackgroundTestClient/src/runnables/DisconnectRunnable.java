package runnables;

import helpers.Singleton;
import helpers.SingletonFactory;

import com.esotericsoftware.minlog.Log;

import service.ClientBase;
import android.os.AsyncTask;

public class DisconnectRunnable extends AsyncTask<Void, Void, Void>{
	
	//TODO: This seems to never be called
	@Override
	protected Void doInBackground(Void... params) {
		ClientBase clientBase = (ClientBase) SingletonFactory.getSingletonInstance(Singleton.CLIENT);
		clientBase.close();
		Log.info("DisconnectRunnable", "Client Disconnected");
		return null;
	}

}
