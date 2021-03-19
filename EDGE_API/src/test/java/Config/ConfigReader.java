package Config;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private Properties properties;
	private final String propertyFilePath= "env//default//user.properties";

	
	public ConfigReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getEnvironmenr() {
		String url = properties.getProperty("APP_ENV");
		if(url != null) return url;
		else throw new RuntimeException("Application ENV not specified in the Configuration.properties file.");
	}
	
	public String getBaseURI() {
		String url = properties.getProperty("BASE_URI");
		if(url != null) return url;
		else throw new RuntimeException("Application base URL not specified in the Configuration.properties file.");
	}
	public String getStageToken() {
		String url = properties.getProperty("STAGE_TOKEN");
		if(url != null) return url;
		else throw new RuntimeException("Application base URL not specified in the Configuration.properties file.");
	}
	
	

}