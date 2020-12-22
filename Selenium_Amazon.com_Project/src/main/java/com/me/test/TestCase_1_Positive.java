package com.me.test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.rmi.runtime.Log;

import java.io.File;

public class TestCase_1_Positive {

    WebDriver driver ;
    ExtentTest test;

    @BeforeEach
    public void setUpTest(){
        System.setProperty("webdriver.chrome.driver","/Users/denis/Dropbox/Summer2019/QA/SeleniumProject/chromedriver");
        driver = new ChromeDriver();
        test = CaptchCheck.extent.startTest("TestCase_1_Positive");
    }

    @Test
    public void runTest() throws Exception {

        //Creating an instance of a Chrome Driver
        test.log(LogStatus.INFO,"Expected: [First Page: Home Page] -> [Final Page: Authentication Required Page]");

        driver.get("http://www.amazon.com");

        //Click on the Create Account Button
        WebElement sighInButton = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]"));
        sighInButton.click();
        driver.findElement(By.id("createAccountSubmit")).click();
        Thread.sleep(1500);

        //Typing the Customer Name;
        WebElement name = driver.findElement(By.id("ap_customer_name"));
        name.sendKeys("Jane Joe");
        Thread.sleep(1500);

        //Typing the Email address
        WebElement email_address = driver.findElement(By.id("ap_email"));
        Thread.sleep(1500);
        email_address.sendKeys("megandsouza@yahoo.com");

        //Typing the password
        WebElement password = driver.findElement(By.id("ap_password"));
        password.sendKeys("12345qwer");
        Thread.sleep(1500);

        //Typing the Re-enter password
        WebElement apPasswordCheck = driver.findElement(By.id("ap_password_check"));
        apPasswordCheck.sendKeys("12345qwer");


        String screenshotPath = ScreenShots.takeScreenshot(driver,"T1PosDetails");
        test.log(LogStatus.INFO,"Entered the Details");
        test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));


        driver.findElement(By.id("continue")).click();
        Thread.sleep(1500);
        // Checking for the alert - missing customer name
        Thread.sleep(1500);

        if(CaptchCheck.checkForCaptch(driver,test,CaptchCheck.extent)){
            Assert.fail();
        }

//        //-----CAPTCHA-------------
//        try {
//            WebElement error = driver.findElement(By.id("auth-error-message-box")).findElement(By.className("a-list-item"));
//            screenshotPath = ScreenShots.takeScreenshot(driver, "T1Poscaptch");
//            System.out.println(error.getText());
//            if (error.getText().equals("Enter the characters as they are given in the challenge.")) {
//                test.log(LogStatus.WARNING, "Captcha Encounter!");
//                test.log(LogStatus.WARNING, test.addScreenCapture(screenshotPath));
//
//                extent.endTest(test);
//                extent.flush();
//                driver.quit();
//            }
//        }catch (NoSuchElementException e){
//            test.log(LogStatus.INFO, "No Captcha Encountered");
//        }
//        //-----CAPTCHA-------------

        String title = driver.getTitle();

        // System.out.println("I came here");
          System.out.println(driver.getTitle());
        screenshotPath = ScreenShots.takeScreenshot(driver, "T1PosFinal");
        try{
            Assert.assertEquals(title,("Authentication required"));
            test.log(LogStatus.PASS, "Test Successfully prevented a registration with incomplete information");
            test.log(LogStatus.PASS,test.addScreenCapture(screenshotPath));
            test.log(LogStatus.INFO,"Actual: [First Page: Home Page] -> [Final Page: Authentication Required Page]");

        }catch (ComparisonFailure e){
            screenshotPath = ScreenShots.takeScreenshot(driver, "T1PosFinal-1");
            test.log(LogStatus.FAIL,test.addScreenCapture(screenshotPath));
            test.log(LogStatus.FAIL, "Test Failed");
            test.log(LogStatus.INFO,"Actual: Error -> Check screenshots");
            Assert.fail();
        }


    }

    @AfterEach
    public void endTest(){
        CaptchCheck.extent.endTest(test);
        CaptchCheck.extent.flush();
        driver.quit();
    }



}
