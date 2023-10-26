package com.runarmc.results.client;

import com.runarmc.results.AbstractResult;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Builder
@Getter @Setter
public class VersionResult extends AbstractResult {
    private final @NonNull String version;
    private final @NonNull String build;
    private final @NonNull Timestamp createdAt;
    private final @NonNull Timestamp updatedAt;
}
