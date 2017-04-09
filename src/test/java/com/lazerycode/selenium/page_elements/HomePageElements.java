package com.lazerycode.selenium.page_elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page class to hold all tested elements on networkedinsights.com
 * Created by Calvin on 4/7/2017.
 */
public class HomePageElements {
    @FindBy(linkText = "Request A Demo")
    public WebElement requestDemoToolbarButton;

    @FindBy(linkText = "Blog")
    public WebElement blogToolbarButton;

    @FindBy(linkText = "Careers")
    public WebElement careersToolbarButton;

    @FindBy(linkText = "Contact")
    public WebElement contactToolbarButton;

    @FindBy(linkText = "Login")
    public WebElement loginToolbarButton;
}
