package com.spotahome.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.name;

public class ProductPage extends BasePage {
    @FindBy(xpath = "/html/head/meta[contains(@name, 'title')]")
    private WebElement title;

    public String getTitle() {
        return title.getAttribute("content");
    }
}
