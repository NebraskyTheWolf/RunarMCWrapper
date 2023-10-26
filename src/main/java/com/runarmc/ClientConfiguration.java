package com.runarmc;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class ClientConfiguration {
    private String host;
    private int port;
    private boolean useHttps;
    private long connectTimeout;
    private long readTimeout;
    private String bearerToken;

    @Override
    public String toString() {
        return "ClientConfiguration{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", useHttps=" + useHttps +
                ", connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                ", bearerToken='" + bearerToken + '\'' +
                '}';
    }
}
