package com.spotahome.dal;

import com.google.gson.Gson;
import com.spotahome.client.model.DummyModel;
import com.spotahome.client.IRestClient;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URLEncoder;

public class DummyDal implements IDummyDal {
    private final IRestClient client;
    private String apiUrl;

    @Inject
    public DummyDal(IRestClient client) {
        this.client = client;
    }

    public void initialize(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getDummy(String url, String title) throws IOException {
        String request = apiUrl + "?url=" + URLEncoder.encode(url,"UTF-8") + "6title=" + URLEncoder.encode(title,"UTF-8");
        return client.get(request);
    }

    public String postDummy(DummyModel model) throws IOException {
        return client.post(apiUrl, new Gson().toJson(model));
    }
}
