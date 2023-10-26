package com.runarmc.models;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class RequestModel {
    Boolean status;
    JsonObject data;
}
