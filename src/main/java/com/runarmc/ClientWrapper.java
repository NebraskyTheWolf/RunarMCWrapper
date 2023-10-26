package com.runarmc;

import lombok.Getter;
import lombok.NonNull;
import okhttp3.OkHttpClient;

import java.time.Duration;

@Getter
public class ClientWrapper {

    @Getter
    public static ClientWrapper instance;

    @Getter
    private OkHttpClient client;

    private final @NonNull String host;
    private final @NonNull String bearerToken;
    private final @NonNull Long readTimeout;
    private final @NonNull Long connectTimeout;

    public ClientWrapper(ClientConfiguration configuration) {
        instance = this;

        this.host = configuration.getHost();
        this.bearerToken = configuration.getBearerToken();
        this.readTimeout = configuration.getReadTimeout();
        this.connectTimeout = configuration.getConnectTimeout();

        this.client = new OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(this.connectTimeout))
                .readTimeout(Duration.ofSeconds(this.readTimeout))
                .build();
    }
}