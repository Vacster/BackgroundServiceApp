package runnables;

import java.io.IOException;

import com.esotericsoftware.minlog.Log;

import service.ClientBase;
import android.os.AsyncTask;

public class DisconnectRunnable extends AsyncTask<Void, Void, Void>{

	private static DisconnectRunnable context;
	
	public static DisconnectRunnable instanceOf(){
		if(context == null)
			context = new DisconnectRunnable();
		return context;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		try {
			ClientBase.getInstance().close();
			Log.info("DisconnectRunnable", "Client Disconnected");
		} catch (IOException e) {
			Log.error("DisconnetRunnable", e.toString());
		}
		return null;
	}

}
