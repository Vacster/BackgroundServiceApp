package helpers;

import java.util.concurrent.ConcurrentHashMap;

import com.esotericsoftware.minlog.Log;

public class SingletonFactory {
	
	private static final ConcurrentHashMap<Singleton, Object> map = new ConcurrentHashMap<Singleton, Object>();

	public static Object getSingletonInstance(Singleton className) throws InstantiationException {

		Object classFound = map.get(className);
		if(classFound == null)
			try {
				classFound = className.classType.newInstance();
				map.put(className, classFound);
			} catch (IllegalAccessException e) {
				Log.error(e.getMessage());
			}
		
		return classFound;
	}
	
}
