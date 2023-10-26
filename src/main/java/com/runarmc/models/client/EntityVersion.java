package com.runarmc.models.client;

import com.runarmc.annotations.Route;
import com.runarmc.models.AbstractModel;
import com.runarmc.results.client.VersionResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import okhttp3.FormBody;

import java.io.Serializable;
import java.sql.Timestamp;
@Route(method = "GET", route = "/api/v1/features/version", result = VersionResult.class)
@Getter @Setter @NoArgsConstructor
public class EntityVersion extends AbstractModel implements Serializable {
    String version;
    String build;
    Timestamp createdAt;
    Timestamp updatedAt;
}
