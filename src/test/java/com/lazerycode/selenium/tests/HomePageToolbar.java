package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_elements.HomePageElements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Tests the top toolbar on networkedinsights.com
 * Created by Calvin on 4/7/2017.
 */
public class HomePageToolbar extends DriverBase {
    final static String HOME_PAGE = "http://www.networkedinsights.com/";
    final static String GECKO_DRIVER_LOCATION ="C:\\selenium\\gecko\\geckodriver.exe";
    private WebDriver driver;
    private HomePageElements page;


    @BeforeClass
    public void setUp() throws Exception{
        System.setProperty("webdriver.gecko.driver", GECKO_DRIVER_LOCATION);
        driver = getDriver();
        page = PageFactory.initElements(driver,HomePageElements.class);
    }

    @Test
    public void homePageToolbarVisibility(){
        driver.get(HOME_PAGE);
        Assert.assertTrue(page.requestDemoToolbarButton.isDisplayed());
        Assert.assertTrue(page.blogToolbarButton.isDisplayed());
        Assert.assertTrue(page.careersToolbarButton.isDisplayed());
        Assert.assertTrue(page.contactToolbarButton.isDisplayed());
        Assert.assertTrue(page.loginToolbarButton.isDisplayed());
    }

    @Test
    public void demoLink(){
        driver.get(HOME_PAGE);
        page.requestDemoToolbarButton.click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://www.networkedinsights.com/request-a-demo/"));
    }

    @Test
    public void blogLink(){
        driver.get(HOME_PAGE);
        page.blogToolbarButton.click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("post-area")));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://www.networkedinsights.com/blog/"));
    }

    @Test
    public void careersLink(){
        driver.get(HOME_PAGE);
        page.careersToolbarButton.click();
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href=\"#openpositions\"]")));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://www.networkedinsights.com/company/careers/"));
    }

    @Test
    public void contactLink(){
        driver.get(HOME_PAGE);
        page.contactToolbarButton.click();
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
        Assert.assertTrue(driver.getCurrentUrl().equals("http://www.networkedinsights.com/contact/"));
    }

    @Test
    public void loginLink() throws Exception{
        driver.get(HOME_PAGE);
        page.loginToolbarButton.click();
        Thread.sleep(1000);
        ArrayList<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());
        Boolean correctWindow = false;
        for(String eachHandle : tabHandles){
            driver.switchTo().window(eachHandle);
            if(driver.getCurrentUrl().equals("https://login.nisocialsense.com/login?service=http%3A%2F%2Ftools.nisocialsense.com%2Fsecure%2Fredirect")){
                correctWindow = true;
            }
        }
        Assert.assertTrue(correctWindow);
    }
}
