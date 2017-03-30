package helpers;

import java.util.List;

import com.esotericsoftware.minlog.Log;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

public class LocationHelper implements LocationListener{

	private static LocationHelper context;
	private static LocationManager locationManager;
	private static Context activityContext;
	
	public void init(Context aContext){
		context = this; //Best way?
		activityContext = aContext;
		locationManager = (LocationManager)activityContext.getSystemService(Context.LOCATION_SERVICE);
		if(getLocation() == null)
			requestLocations();
	}
	
	private static void locationPrompt(){//TODO: Have a prompt for location
		Log.info("LocationHelper", "Prompting for Location");

        Intent onGPS = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        onGPS.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activityContext.startActivity(onGPS);
	}
	
	public static Location getLocation() {
	    List<String> providers = locationManager.getProviders(true);
	    Location bestLocation = null;
	    for (String provider : providers) {
	    	Log.trace("LocationHelper", "Found Provider: " + provider);
	    	Location l = locationManager.getLastKnownLocation(provider);
	        if (l == null)
		        continue;
		    if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy())
	            bestLocation = l;
	    }
	    if(bestLocation == null)
	    	Log.error("LocationHelper", "Last Known Location is null");
	    return bestLocation;
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.info("LocationHelper", "New location received: " + location.toString());
		locationManager.removeUpdates(this);
	}
	
	private static void requestLocations(){
		if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, context);
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, context);
		else
			locationPrompt();
		if(locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER))
			locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, context);
	}

	public static boolean compareLocations(float distance, Location l1, Location l2){
		return (l1.distanceTo(l2) <= distance);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.info("LocationHelper", "Status Changed: " + provider + " with Status: " + status);
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.info("LocationHelper", "Provider Enabled: " + provider);
	}

	@Override
	public void onProviderDisabled(String provider) {//TODO: Check if location can still be obtained; if not, exit
		Log.info("LocationHelper", "Provider Disabled: " + provider);
	}
	

}
