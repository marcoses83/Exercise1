package com.spotahome.service;

import com.spotahome.client.IRestClient;
import com.spotahome.client.model.DummyModel;
import com.spotahome.dal.DummyDal;
import com.spotahome.dal.IDummyDal;
import com.spotahome.infrastructure.InjectionModule;
import dagger.ObjectGraph;

import java.io.IOException;

public class DummyService implements IDummyService{

    public String getDummyInfo(String apiUrl, String url, String title, String method) throws IOException {
        ObjectGraph objectGraph = ObjectGraph.create(new InjectionModule());
        IRestClient client = objectGraph.get(IRestClient.class);
        String result = "";
        IDummyDal dummyDal = new DummyDal(client);
        dummyDal.initialize(apiUrl);
        if (method.equals("get")) {
            result = dummyDal.getDummy(url, title);
        }
        else if (method.equals("post")) {
            DummyModel model = new DummyModel();
            model.setUrl(url);
            model.setTitle(title);
            result = dummyDal.postDummy(model);
        }

        return result;
    }

    public void printDummyInfo(String dummyInfo) {
        System.out.println(dummyInfo);
    }
}
