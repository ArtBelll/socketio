package com.art.push;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Artur Belogur on 06.02.17.
 */
@Slf4j
public class PushService {

    private SocketIOServer socketIOServer;

    public PushService(Configuration conf) {
        socketIOServer = new SocketIOServer(conf);
        addListeners();
        socketIOServer.start();
    }

    private void addListeners() {
        socketIOServer.addConnectListener(socketIOClient ->
                System.out.println("Hello"));

        socketIOServer.addEventListener("pressBtn", Integer.class, (socketIOClient, data, ackRequest) -> {
            log.info("counter {}", data + 1);
            socketIOServer.getBroadcastOperations().sendEvent("updateCounter", data + 1);
        });

        socketIOServer.addEventListener("chatevent", ChatObject.class, (client, data, ackRequest) -> {
            socketIOServer.getBroadcastOperations().sendEvent("chatevent", data);
        });
    }

    public void updateCounter(Integer counter) {
        socketIOServer.getBroadcastOperations().sendEvent("updateCounter", counter);
    }
}
