package helpers;

import java.util.List;

import com.esotericsoftware.minlog.Log;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class LocationHelper implements LocationListener{

	private static LocationHelper context;
	private static LocationManager locationManager;
	private static Context activityContext;
	
	public void init(Context aContext){
		activityContext = aContext;
		locationManager = (LocationManager)activityContext.getSystemService(Context.LOCATION_SERVICE);
		if(getLocation() == null)
			requestLocations();
		
	}
	
	public static Location getLocation() {//Change to use criteria instead of iteration
	    List<String> providers = locationManager.getProviders(true);
	    Location bestLocation = null;
	    for (String provider : providers) {
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
		Toast toast = Toast.makeText(activityContext, "Requesting Location...", Toast.LENGTH_SHORT);
		toast.show();
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, context);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, context);
	}

	public static boolean compareLocations(float distance, Location l1, Location l2){
		return (l1.distanceTo(l2) <= distance);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}

	@Override
	public void onProviderEnabled(String provider) {}

	@Override
	public void onProviderDisabled(String provider) {}
	

}
