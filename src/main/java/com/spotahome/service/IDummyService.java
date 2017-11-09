package com.spotahome.service;

import java.io.IOException;

public interface IDummyService {
    String getDummyInfo(String apiUrl, String url, String title, String method) throws IOException;
    void printDummyInfo(String dummyInfo);
}
