package com.runarmc.models.client;

import com.runarmc.annotations.Route;
import com.runarmc.models.AbstractModel;
import com.runarmc.results.client.ServerResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import okhttp3.FormBody;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Route(method = "GET", route = "/api/area/fetch", result = ServerResult.class)
@Getter @Setter @NoArgsConstructor
public class EntityServer extends AbstractModel implements Serializable {
    String _id;
    String hostIp;
    String hostPort;
    String name;
    String banner;
    String description;

    Boolean isMaintenance;
    Boolean isRestricted;
    Boolean isUnavailable;

    List<String> flags;

    Timestamp createdAt;
    Timestamp updatedAt;
}
