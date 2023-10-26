package com.runarmc.results;

import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RequestResult {
    private final Boolean status;
    private final JsonObject data;
}
