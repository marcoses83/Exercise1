package com.spotahome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.spotahome.model.PageInfo;
import com.spotahome.model.Seat;
import com.spotahome.model.SeatType;
import com.spotahome.selenium.framework.Browser;
import com.spotahome.selenium.framework.BrowserFactory;
import com.spotahome.selenium.framework.enums.BrowserType;
import com.spotahome.selenium.pages.ProductPage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Properties prop = new Properties();
        Browser browser = null;
        try {
            SeatType[] seatTypes = mapper.readValue(new File(args[0]), SeatType[].class);

            for(int i = 0; i < seatTypes.length; i++) {
                Seat seat = seatTypes[i].getSeat();
                BrowserType browserType = BrowserType.valueOf(seat.getBrowser().toUpperCase());
                browser = BrowserFactory.createBrowser(browserType);
                String url = seat.getUrl();
                browser.navigateTo(url);
                String title = browser.getPage(ProductPage.class).getTitle();
                browser.close();
                String domain = getDomain(url);
                PageInfo pageInfo =  new PageInfo(url, domain, title);
                prop.setProperty("url", pageInfo.getUrl());
                prop.setProperty("domain", pageInfo.getDomain());
                prop.setProperty("title", pageInfo.getTitle());
                FileOutputStream fileOut = new FileOutputStream("resources/pageinfo.properties",true);
                prop.store(fileOut, null);
            }
        } catch (IOException e) {
            if (browser != null) {
                browser.close();
            }
            e.printStackTrace();
        }
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
