package com.spotahome.selenium.framework;

import com.spotahome.selenium.framework.enums.BrowserType;

public class BrowserFactory {
    public static Browser createBrowser(BrowserType browserType){
        return new Browser(browserType);
    }
}
