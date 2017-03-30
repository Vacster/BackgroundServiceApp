package helpers;

import java.util.concurrent.ConcurrentHashMap;

import com.esotericsoftware.minlog.Log;

public class SingletonFactory {
	
	private static final ConcurrentHashMap<Singleton, Object> map = new ConcurrentHashMap<Singleton, Object>();

	public static Object getSingletonInstance(Singleton className) {

		Object classFound = map.get(className);
		if(classFound == null)
			try {
				classFound = className.classType.newInstance();
				map.put(className, classFound);
			} catch (IllegalAccessException e) {
				Log.error("SingletonFactory", e.getMessage());
			} catch (InstantiationException e) {
				Log.error("SingletonFactory", e.getMessage());
			} catch (Exception e) {
				Log.error("SingletonFactory", "Extra exception");
				e.printStackTrace();
			}
		
		return classFound;
	}
	
}
