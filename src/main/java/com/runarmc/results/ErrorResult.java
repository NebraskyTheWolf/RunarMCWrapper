package com.runarmc.results;

import lombok.*;

import java.time.Instant;

@Builder
@Getter @Setter
public class ErrorResult extends AbstractResult {
    private final @NonNull Instant errorTs;

    private final @NonNull Integer statusCode;
    private final @NonNull String error;
    private final @NonNull String message;

    private final @NonNull Class handler;
}
