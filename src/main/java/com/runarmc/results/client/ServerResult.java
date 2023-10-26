package com.runarmc.results.client;

import com.runarmc.results.AbstractResult;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Getter @Setter
public class ServerResult extends AbstractResult {
    private final @NonNull String serverIp;
    private final @NonNull String serverPort;
    private final @NonNull String serverName;
    private final @NonNull String serverBanner;
    private final @NonNull String serverDescription;

    private final @NonNull Boolean isMaintenance;
    private final @NonNull Boolean isRestricted;
    private final @NonNull Boolean isUnavailable;

    private final @NonNull List<String> flags;

    private final @NonNull Timestamp createdAt;
    private final @NonNull Timestamp updatedAt;
}
