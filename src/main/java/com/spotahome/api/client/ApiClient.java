package com.spotahome.api.client;

public class ApiClient {
    private String apiUrl;
    public ApiClient(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void get(String url, String title) {
        String requestURL = apiUrl + "?url=" + url + "&title=" + title;
        //String response = RestApiClient.getApiRequest(requestURL);
    }
}
