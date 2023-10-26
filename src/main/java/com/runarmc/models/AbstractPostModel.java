package com.runarmc.models;

import com.google.gson.JsonObject;

public abstract class AbstractPostModel extends AbstractModel {
    public abstract JsonObject body();
}
