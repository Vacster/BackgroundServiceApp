package runnables;

import java.io.IOException;

import com.esotericsoftware.minlog.Log;

import android.os.AsyncTask;
import service.ClientBase;

public class ClientRunnable extends AsyncTask<Void, Void, Void>{

	private static ClientRunnable context;
	
	public ClientRunnable() throws IOException {
		ClientBase.getInstance();
	}

	public static ClientRunnable instanceOf() throws IOException{
		if(context == null)
			context = new ClientRunnable();
		return context;
	}
	
	//TODO: This seems wrong, not too important though
	@Override
	protected Void doInBackground(Void... params) {
		try {
			ClientBase.getInstance();
		} catch (IOException e) {
			Log.error("ClientRunnable", e.toString());
		}

		Log.info("ClientRunnable", "Runnable Started");
		return null;
	}

}
