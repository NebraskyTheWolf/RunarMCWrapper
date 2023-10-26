package com.runarmc.websocket.payload;

import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class Payload implements Serializable {
    private final @NonNull String key;
    private final @NonNull JsonObject data;
}
