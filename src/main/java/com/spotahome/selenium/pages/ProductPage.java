package com.spotahome.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(tagName = "title")
    private WebElement title;

    public String getTitle() {
        return title.getText();
    }
}
