package gui.configuration.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Configurator {

	private String result = "";
	private InputStream inputStream;
	private Properties prop;
	private final String propFileName = "config.properties";
	// private Logger logger = new Logger(class)
	private static Configurator conf;
	private static final Object lock = new Object();

	private Configurator() {
		init(propFileName);
	}

	public Properties init(String fileName) {
		try {
			prop = new Properties();
			inputStream = getInputStream(fileName);

			if (inputStream != null) {
				System.out.println("reading property file: "+ fileName);
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			closeInputStream();
			return prop;

		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return null;
		}
	}

	private InputStream getInputStream(String propFileName) {

		return inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

	}

	private void closeInputStream() throws IOException {

		inputStream.close();
		// TODO Auto-generated method stub

	}

	/**
	 * Thread safe operation only one instance of this class is needed
	 * 
	 * @return
	 */
	public static Configurator getObject() {
		if (conf == null) {
			synchronized (lock) {
				if (conf == null) {
					conf = new Configurator();
				}
			}

			return conf;
		}
		return conf;

	}

	public List<String> readAllValues() {
		List<String> productList = new ArrayList<String>();
		Set<Object> keySet = prop.keySet();
		for (Object obj : keySet) {
			
			String key = obj.toString();
			if(key.contains("properties") || key.contains("|")){
				continue;
			}
			else{
			checkAvailability(obj, productList, key);
			}
			
			}
			/*if ((getPropValues(availableKey)).equals("true")) {
				prop = readSecondPropertyFile(getPropValues(obj.toString()));
				Set<Object> subSet = prop.keySet();
				for (Object subObj : keySet) {
					String value = subObj.toString();
					productList.add(value);

				}

			}*/

			
		//}
		return productList;
	}

	private void checkAvailability(Object obj, List<String> productList, String key) {
		
			String prodAvailable = getPropValues(obj.toString());
			if(prodAvailable.equalsIgnoreCase("true")){
				prop = readSecondPropertyFile(key);
				Set<Object> subSet = prop.keySet();
				for (Object subObj : subSet) {
					String value = subObj.toString();
					productList.add(value);

				}
			}
			

		
		
	}

	private Properties readSecondPropertyFile(String fileName) {

		StringBuffer sb = new StringBuffer(fileName);
		sb.append(".properties");
		String secondFileName = getPropValues(sb.toString());
		return init(secondFileName);

	}

	public String getPropValues(String propName) {

		// get the property value and print it out
		String propertyValue = prop.getProperty(propName);
		if (propertyValue == null || propertyValue.isEmpty()) {
			System.err.println("No value found");
			return "";
		}

		return propertyValue;

	}

}
