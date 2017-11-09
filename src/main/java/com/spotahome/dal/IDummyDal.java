package com.spotahome.dal;

import com.spotahome.client.model.DummyModel;

import java.io.IOException;

public interface IDummyDal {
    void initialize(String apiUrl);
    String getDummy(String url, String title) throws IOException;
    String postDummy(DummyModel model) throws IOException;
}
