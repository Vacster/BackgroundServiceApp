package service;

import helpers.LocationHelper;
import helpers.Singleton;
import helpers.SingletonFactory;

import runnables.ClientRunnable;
import runnables.DisconnectRunnable;

import com.esotericsoftware.minlog.Log;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;

public class BackgroundService extends IntentService{
	
	public BackgroundService() {
		super("Service Thread");
		//TODO: creating a new thread like this seems ugly
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				ClientRunnable clientRunnable = (ClientRunnable) SingletonFactory.getSingletonInstance(Singleton.CLIENT_RUNNABLE);
				clientRunnable.execute();
			}
		});
		thread.start();
		Log.info("BackgroundService", "Started");
	}
	
	@Override
	public void onCreate() {
		LocationHelper locationHelper = (LocationHelper) SingletonFactory.getSingletonInstance(Singleton.LOCATION_HELPER);
		locationHelper.init(this);
		Location loc = LocationHelper.getLocation();
		Log.info("BackgroundService", loc == null ? "No Location Found" : loc.toString());
	}
	
	//TODO: Test if this is ever called, remove otherwise
	public BackgroundService(String name) {
		super(name);
		Log.warn("WARNING", "CONSTRUCTOR WITH STRING IS CALLED");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		DisconnectRunnable disconnectRunnable = (DisconnectRunnable) SingletonFactory.getSingletonInstance(Singleton.DISCONNECT_RUNNABLE);
		disconnectRunnable.execute();
	}
	
	//TODO: investigate what flags do and see if they could be used to determine return object
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}
	
	//TODO: Investigate what this does
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.debug("BackgroundService", "onHandleIntent");
	}

}
