package com.spotahome.client;

import org.apache.http.HttpStatus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient implements IRestClient {
    public String get(String requestUrl) throws IOException {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(requestUrl).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream stream;
            if (urlConnection.getResponseCode() >= HttpStatus.SC_OK && urlConnection.getResponseCode() < HttpStatus.SC_MULTIPLE_CHOICES) {
                stream = urlConnection.getInputStream();
            } else if (urlConnection.getResponseCode() == HttpStatus.SC_NOT_FOUND) {
                return null;
            } else {
                stream = urlConnection.getErrorStream();
                if (stream != null) {
                    StringBuilder errorBuilder = new StringBuilder();
                    errorBuilder.append("Error accessing to ").append(requestUrl).append(" : ");
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                        reader.lines().forEach(errorBuilder::append);
                    }
                    throw new IOException(errorBuilder.toString());
                } else {
                    throw new IOException("Error accessing to " + requestUrl);
                }
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                StringBuilder builder = new StringBuilder();
                reader.lines().forEach(builder::append);
                return builder.toString();
            }
        } catch (IOException e) {
            throw new IOException(e.toString());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public String post(String requestUrl, String input) throws IOException {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(requestUrl).openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            OutputStream os = urlConnection.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (urlConnection.getResponseCode() < HttpStatus.SC_OK || urlConnection.getResponseCode() >= HttpStatus.SC_MULTIPLE_CHOICES) {
                throw new RuntimeException("Failed : HTTP error code : " + urlConnection.getResponseCode());
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                StringBuilder builder = new StringBuilder();
                reader.lines().forEach(builder::append);
                return builder.toString();
            }
        } catch (IOException e) {
            throw new IOException(e.toString());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
