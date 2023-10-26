package com.runarmc.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.runarmc.websocket.payload.Payload;
import com.runarmc.websocket.payload.PayloadAbstract;
import com.runarmc.websocket.payload.PayloadManager;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebSocket  extends WebSocketClient {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

    private final PayloadManager payloadManager = new PayloadManager();

    public WebSocket() throws URISyntaxException {
        super(new URI("wss://gateway.runarmc.net"));
    }

    @Override
    public void onOpen(ServerHandshake data) {
        executorService.scheduleAtFixedRate(this::sendPing, 0, 30, TimeUnit.SECONDS);
    }

    @Override
    public void onMessage(String message) {
        Payload payload = this.gson.fromJson(message, Payload.class);

        if (payload.getKey() != null) {
            PayloadAbstract fW = this.payloadManager.findByKey(payload.getKey());
            if (fW != null) {
                fW.handle();
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
