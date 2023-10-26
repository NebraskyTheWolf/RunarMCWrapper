package com.runarmc.tests;

import com.google.gson.Gson;
import com.runarmc.ClientConfiguration;
import com.runarmc.ClientWrapper;
import com.runarmc.handler.RequestCallback;
import com.runarmc.handler.RequestHandler;
import com.runarmc.models.client.EntityServer;
import com.runarmc.results.ErrorResult;
import com.runarmc.results.RequestResult;
import okhttp3.Response;

public class TestClient {

    public static void main(String[] args) throws Exception {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .host("https://endpoint.riniya.uk")
                .port(443)
                .readTimeout(30)
                .connectTimeout(30)
                .bearerToken("Bearer <token>")
                .build();
        ClientWrapper wrapper = new ClientWrapper(configuration);

        System.out.println(configuration.toString());

        RequestHandler handler = new RequestHandler();
        handler.execute(wrapper, new EntityServer(), new RequestCallback() {
            @Override
            public void onFailure(ErrorResult error) {
                System.out.println();
                System.out.println("==== FAILURE ====");
                System.out.println("Error : " + error.getError());
                System.out.println("Error Ts : " + error.getErrorTs());
                System.out.println("Message : " + error.getMessage());
            }

            @Override
            public void onSuccess(RequestResult result, Response response) {
                System.out.println();
                System.out.println("==== SUCCESS ====");
                System.out.println("Status : " + result.getStatus());
                System.out.println("Data : " + new Gson().toJson(result.getData()));
            }
        });
    }
}
