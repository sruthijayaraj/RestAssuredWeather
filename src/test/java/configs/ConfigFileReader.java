package configs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private  final String propertyPath = "src/test/java/configs/Conf.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyPath));
            properties = new Properties();
            properties.load(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("no configuration details found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl(){
        return properties.getProperty("URL");

    }
    public String getAPIKey(){
        return properties.getProperty("key");
    }
}
