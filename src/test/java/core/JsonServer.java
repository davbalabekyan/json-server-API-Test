package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonServer {

    private static Logger log = LoggerFactory.getLogger(JsonServer.class);

    public static void startJsonServer() {
        try {
            Runtime.getRuntime().exec("json-server --watch db.json");
        } catch (IOException e) {
            log.error("Error occurred while executing task");
        }
    }
}
