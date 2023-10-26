package com.runarmc.websocket.payload;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class PayloadAbstract {
    private final String key;
    private final JsonObject data;

    public abstract void handle();
}
