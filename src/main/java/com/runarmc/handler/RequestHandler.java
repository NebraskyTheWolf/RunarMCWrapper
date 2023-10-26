package com.runarmc.handler;

import com.google.gson.Gson;
import com.runarmc.ClientWrapper;
import com.runarmc.annotations.Route;
import com.runarmc.exceptions.IllegalAnnotationStatementException;
import com.runarmc.models.AbstractModel;
import com.runarmc.models.AbstractPostModel;
import com.runarmc.models.ErrorModel;
import com.runarmc.models.RequestModel;
import com.runarmc.results.ErrorResult;
import com.runarmc.results.RequestResult;
import okhttp3.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    public void execute(ClientWrapper wrapper, AbstractModel model, RequestCallback callback) throws Exception {
        this.execute(wrapper, model, callback, new HashMap<>());
    }

    public void execute(ClientWrapper wrapper, AbstractModel model, RequestCallback callback, Map<String, String> arguments) throws Exception {
        if (model.getClass().getDeclaredAnnotationsByType(Route.class) != null) {
            Route route = model.getClass().getDeclaredAnnotation(Route.class);

            if (route.method().equals("POST")) {
                if (model instanceof AbstractPostModel) {
                    AbstractPostModel modelPost = (AbstractPostModel) model;
                    if (modelPost.body() != null) {
                        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),  modelPost.body().toString());
                        Request request = this.requestBuilder(wrapper, route.method(), route.route(), body);
                        Call post = wrapper.getClient().newCall(request);

                        this.responseBuilder(post.execute(), callback);
                    } else {
                        throw new Exception("Post body cannot be null.");
                    }
                }
                return;
            }

            String finalRoute = route.route();

            if (route.arguments()) {
                for (Map.Entry entry : arguments.entrySet()) {
                    finalRoute = finalRoute.replaceAll(":" + entry.getKey(), entry.getValue().toString());
                }
            }

            Request request = this.requestBuilder(wrapper, route.method(), finalRoute);
            Call execute = wrapper.getClient().newCall(request);

            this.responseBuilder(execute.execute(), callback);
        } else {
            callback.onFailure(
                    ErrorResult
                            .builder()
                            .error("ILLEGAL_ANNOTATION_STATEMENT")
                            .message("Unable to find the annotation in the class " + model.getClass().getSimpleName())
                            .errorTs(Instant.now())
                            .statusCode(0)
                            .handler(IllegalAnnotationStatementException.class)
                            .build()
            );
        }
    }

    private void responseBuilder(Response response, RequestCallback callback) throws Exception {
        if (response.code() != 200) {
            ErrorModel error = new Gson().fromJson(response.body().string(), ErrorModel.class);

            callback.onFailure(
                    ErrorResult
                            .builder()
                            .error(error.getError())
                            .message(error.getMessage())
                            .errorTs(Instant.now())
                            .statusCode(response.code())
                            .handler(Exception.class)
                            .build()
            );
        } else {
            RequestModel data = new Gson().fromJson(response.body().string(), RequestModel.class);

            callback.onSuccess(
                    RequestResult.builder()
                            .status(data.getStatus())
                            .data(data.getData())
                            .build(),
                    response

            );
        }
    }


    private Request requestBuilder(ClientWrapper wrapper, String method, String route) {
        return this.requestBuilder(wrapper, method, route, null);
    }

    private Request requestBuilder(ClientWrapper wrapper, String method, String route, RequestBody body) {
        Request request = null;

        switch (method) {
            case "GET": {
                request = new Request.Builder()
                        .url(wrapper.getHost() + route)
                        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/118.0")
                        .addHeader("Authorization", "Bearer " + wrapper.getBearerToken())
                        .get()
                        .build();
            }
            break;
            case "POST": {
                request = new Request.Builder()
                        .url(wrapper.getHost() + route)
                        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/118.0")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "Bearer " + wrapper.getBearerToken())
                        .post(body)
                        .build();
            }
            break;
        }

        return request;
    }
}
