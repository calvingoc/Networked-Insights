package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_elements.HomePageElements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Script to open the details of the QA Engineer position, enters my application information and preps the browser for me to apply for the job.
 * Created by Calvin on 4/7/2017.
 */
public class QAEngineerJobPosting extends DriverBase{
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
    public void applyForJob(){
        driver.get(HOME_PAGE);
        page.careersToolbarButton.click();
        Boolean foundJob = false;
        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href=\"#openpositions\"]")));
        List<WebElement> allPositions = driver.findElements(By.cssSelector("#resumator-jobs div.resumator-job"));
        for (WebElement position : allPositions){
            WebElement jobTitle = position.findElement(By.cssSelector("div.resumator-job-title.resumator-jobs-text"));
            if (jobTitle.getAttribute("innerHTML").equals("QA Engineer")){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", position);
                position.findElement(By.cssSelector("a.resumator-job-link.resumator-jobs-text")).click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", position.findElement(By.cssSelector("input.resumator-apply-button")));
                WebElement applyButton = position.findElement(By.cssSelector("input.resumator-apply-button"));
                applyButton.click();
                Assert.assertTrue(applyButton.getAttribute("value").equals("Cancel"));
                (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#resumator-resumes-title")));
                driver.findElement(By.id("resumator-firstname-value")).sendKeys("Calvin");
                driver.findElement(By.id("resumator-lastname-value")).sendKeys("Gehred-O'Connell");
                driver.findElement(By.id("resumator-email-value")).sendKeys("calgoc@gmail.com");
                driver.findElement(By.id("resumator-address-value")).sendKeys("640 West Wilson Street");
                driver.findElement(By.id("resumator-city-value")).sendKeys("Madison");
                driver.findElement(By.id("resumator-state-value")).sendKeys("WI");
                driver.findElement(By.id("resumator-postal-value")).sendKeys("53703");
                driver.findElement(By.id("resumator-phone-value")).sendKeys("920-222-1888");
                WebElement usCitzen = driver.findElement(By.id("resumator-citizen-value"));
                Select dropdown = new Select(usCitzen);
                dropdown.deselectByValue("10");

                foundJob = true;
            }
        }
        Assert.assertTrue(foundJob);
    }
}
