package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

	private static Properties properties;

	/**
	 * Load properties based on environment (qa/staging/prod)
	 */
	public static void loadConfig(String env) {
		
		String configFilePath = "src/test/resources/config/" + env + ".properties";
		
		properties = new Properties();
		
		try (FileInputStream fis = new FileInputStream(configFilePath)) {
			properties.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load configuration file: " + configFilePath, e);
		}
	}

	/**
	 * Get property by key
	 */
	public static String getProperty(String key) {
		if (properties == null) {
			throw new IllegalStateException("Config not loaded. Call loadConfig() first.");
		}
		return properties.getProperty(key);
	}
}
