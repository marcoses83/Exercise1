package com.spotahome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.spotahome.infrastructure.PropertiesManager;
import com.spotahome.model.Seat;
import com.spotahome.model.SeatType;
import com.spotahome.selenium.framework.Browser;
import com.spotahome.selenium.framework.BrowserFactory;
import com.spotahome.selenium.framework.enums.BrowserType;
import com.spotahome.selenium.pages.ProductPage;
import com.spotahome.service.DummyService;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            Properties applicationProp = new PropertiesManager().getApplicationProperties();
            String dummyApiUrl = applicationProp.getProperty("dummyApiUrl");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            SeatType[] seatTypes = mapper.readValue(new File(args[0]), SeatType[].class);

            processSeats(dummyApiUrl, seatTypes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processSeats(String dummyApiUrl, SeatType[] seatTypes) throws IOException {
        DummyService dummyService = new DummyService();

        for (SeatType seatType : seatTypes) {
            Seat seat = seatType.getSeat();
            String title = getTitleFromBrowser(seat.getBrowser(), seat.getUrl());

            new PropertiesManager().writePageInfoProperties(seat.getUrl(), title);

            String result = dummyService.getDummyInfo(dummyApiUrl, seat.getUrl(), title, seat.getMethod());
            dummyService.printDummyInfo(result);
        }
    }

    private static String getTitleFromBrowser(String browserType, String url) {
        String title;
        BrowserType browserTypeEnum = BrowserType.valueOf(browserType.toUpperCase());
        Browser browser = BrowserFactory.createBrowser(browserTypeEnum);

        browser.navigateTo(url);
        title = browser.getPage(ProductPage.class).getTitle();
        browser.close();

        return title;
    }
}
