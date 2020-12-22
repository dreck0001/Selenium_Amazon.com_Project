package com.me.test;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;



// Sign - In Test Case Negative

public class TestCase_1_Negative {

    WebDriver driver ;
    ExtentTest test;


    @BeforeEach
    public void setUpTest(){
        System.setProperty("webdriver.chrome.driver","/Users/denis/Dropbox/Summer2019/QA/SeleniumProject/chromedriver");
        driver = new ChromeDriver();
        test = CaptchCheck.extent.startTest("TestCase_1_Negative");
    }


 @Test
 public void runTest() throws Exception {


     //Creating an instance of a Chrome Driver
     test.log(LogStatus.INFO,"Expected: [First Page: Home Page] -> [Final Page: Create Account Page]");

     driver.get("http://www.amazon.com");

     //Click on the Create Account Button
     WebElement sighInButton = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]"));
     sighInButton.click();
     driver.findElement(By.id("createAccountSubmit")).click();
     Thread.sleep(1500);

     //Typing the Email address
     WebElement email_address = driver.findElement(By.id("ap_email"));
     Thread.sleep(1500);
     email_address.sendKeys("abcxyz@yahoo.com");

     //Typing the password
     WebElement password = driver.findElement(By.id("ap_password"));
     password.sendKeys("abcxyz");
     Thread.sleep(1500);

     //Typing the Re-enter password
     WebElement apPasswordCheck = driver.findElement(By.id("ap_password_check"));
     apPasswordCheck.sendKeys("abcxyz");

     //---------------
        String screenshotPath = ScreenShots.takeScreenshot(driver,"T1NegDetails");
        test.log(LogStatus.INFO,"Entered the Details");
        test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));

     //---------------
     driver.findElement(By.id("continue")).click();

     if(CaptchCheck.checkForCaptch(driver,test,CaptchCheck.extent)){
         Assert.fail();
     }

     // Checking for the alert - missing customer name
     WebElement alert = driver.findElement(By.id("auth-customerName-missing-alert"));
     Thread.sleep(1500);
    // System.out.println("I came here");
     // System.out.println(alert.isDisplayed());

     System.out.println(alert.findElement(By.className("a-alert-content")).getText());

     String alertText = alert.findElement(By.className("a-alert-content")).getText();
     screenshotPath = ScreenShots.takeScreenshot(driver, "T1NegFinal");
     try{

         Assert.assertEquals(alertText,("Enter your name"));
         test.log(LogStatus.PASS, "Test Successfully prevented a registeration with incomplete information");
         test.log(LogStatus.PASS,test.addScreenCapture(screenshotPath));
         test.log(LogStatus.INFO,"Actual: [First Page: Home Page] -> [Final Page: Create Account Page]");

         //assert.
         //test.log(LogStatus.INFO, "Expected Value = Enter your name "+
     }catch (ComparisonFailure e){

         test.log(LogStatus.FAIL, "Test Failed to identify error");
       //  takeScreenshot(driver);
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
