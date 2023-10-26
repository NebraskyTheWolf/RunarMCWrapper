package com.runarmc.handler;

import com.runarmc.results.ErrorResult;
import com.runarmc.results.RequestResult;
import okhttp3.Response;

public interface RequestCallback {

    void onFailure(ErrorResult error);

    void onSuccess(RequestResult result, Response response);
}
