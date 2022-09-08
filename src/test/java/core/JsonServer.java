package core;

import lombok.SneakyThrows;

public class JsonServer {

    @SneakyThrows
    public static void startJsonServer() {
        Runtime.getRuntime().exec("json-server --watch db.json");
    }
}
