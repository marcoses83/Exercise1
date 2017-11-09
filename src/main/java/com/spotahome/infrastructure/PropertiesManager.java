package com.spotahome.infrastructure;

import com.spotahome.model.PageInfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class PropertiesManager {
    public void writePageInfoProperties(String url, String title) throws IOException {
        PageInfo pageInfo =  new PageInfo(url, getDomain(url), title);
        java.util.Properties pageInfoProp = new java.util.Properties();
        pageInfoProp.setProperty("url", pageInfo.getUrl());
        pageInfoProp.setProperty("domain", pageInfo.getDomain());
        pageInfoProp.setProperty("title", pageInfo.getTitle());
        FileOutputStream fileOut = new FileOutputStream("resources/pageinfo.properties",true);
        pageInfoProp.store(fileOut, null);
    }

    public Properties getApplicationProperties() throws IOException {
        Properties applicationProp = new Properties();
        InputStream input = new FileInputStream("resources/application.properties");
        applicationProp.load(input);
        return applicationProp;
    }

    private static String getDomain(String url) {
        String domain = "";
        URI uri = null;
        try {
            uri = new URI(url);
            String domainName = uri.getHost();
            if (domainName != null) {
                domain = domainName.startsWith("www.") ? domainName.substring(4) : domainName;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return domain;
    }
}
