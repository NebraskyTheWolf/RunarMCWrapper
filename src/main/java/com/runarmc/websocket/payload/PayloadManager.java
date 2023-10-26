package com.runarmc.websocket.payload;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PayloadManager {

    public List<PayloadAbstract> payloads = new CopyOnWriteArrayList<>();

    public PayloadManager() {

    }

    public PayloadAbstract findByKey(String key) {
        for (PayloadAbstract payload : payloads)
            if (payload.getKey().equals(key))
                return payload;
        return null;
    }
}
