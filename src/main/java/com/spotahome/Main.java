package com.spotahome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.spotahome.model.Seat;
import com.spotahome.model.SeatType;
import com.spotahome.selenium.framework.Browser;
import com.spotahome.selenium.framework.BrowserFactory;
import com.spotahome.selenium.framework.enums.BrowserType;
import com.spotahome.selenium.pages.ProductPage;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            SeatType[] seatTypes = mapper.readValue(new File(args[0]), SeatType[].class);

            for(int i = 0; i < seatTypes.length; i++) {
                Seat seat = seatTypes[i].getSeat();
                BrowserType browserType = BrowserType.valueOf(seat.getBrowser().toUpperCase());
                Browser browser = BrowserFactory.createBrowser(browserType);
                browser.navigateTo(seat.getUrl());
                String title = browser.getPage(ProductPage.class).getTitle();
                browser.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
