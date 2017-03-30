package runnables;

import helpers.Singleton;
import helpers.SingletonFactory;


import com.esotericsoftware.minlog.Log;

import android.os.AsyncTask;

public class ClientRunnable extends AsyncTask<Void, Void, Void>{
	
	public ClientRunnable() {
		SingletonFactory.getSingletonInstance(Singleton.CLIENT);
	}
	
	//TODO: This seems wrong, not too important though
	@Override
	protected Void doInBackground(Void... params) {
		SingletonFactory.getSingletonInstance(Singleton.CLIENT);
		Log.info("ClientRunnable", "Runnable Started");
		return null;
	}

}
