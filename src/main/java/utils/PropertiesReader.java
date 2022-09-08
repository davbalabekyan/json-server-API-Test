package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesReader {

    private static final Logger log = LoggerFactory.getLogger(PropertiesReader.class);

    private PropertiesReader() {
    }

    public static String getValueFromPropertyFile(String key) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/endpoints.properties");) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            log.error("Error occurred while reading file");
        }

        return properties.getProperty(key);
    }
}
