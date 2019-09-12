package com.bkunzhang.longconn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bkunzhang
 * @date 2019/9/12
 */
public class TestClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        String ip = "127.0.0.1";
        int port = 65432;
        Client client = new Client(ip, port);
        client.start();
        client.sendObject("你好啊");
        client.sendObject("Hello");
        client.sendObject(234);
        Thread.sleep(500);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "哈哈");
        client.sendObject(map);
    }
}
