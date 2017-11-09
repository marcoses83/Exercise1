package com.spotahome.client;

import java.io.IOException;

public interface IRestClient {
    String get(String requestUrl) throws IOException;
    String post(String requestUrl, String input) throws IOException;
}
