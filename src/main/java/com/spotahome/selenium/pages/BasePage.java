package com.spotahome.selenium.pages;

import com.spotahome.selenium.framework.Browser;

public class BasePage {
    private Browser browser;

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public void closeBrowser() {
        getBrowser().close();
    }
}
