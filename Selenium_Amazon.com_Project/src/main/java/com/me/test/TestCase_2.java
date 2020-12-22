package com.me.test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase_2 {

    WebDriver driver ;
    ExtentTest test;

    @BeforeEach
    public void setUpTest(){
        System.setProperty("webdriver.chrome.driver","/Users/denis/Dropbox/Summer2019/QA/SeleniumProject/chromedriver");
        driver = new ChromeDriver();
        test = CaptchCheck.extent.startTest("TestCase_2");
    }


    @Test
    public void runTest() throws Exception {


        test.log(LogStatus.INFO,"Expected: [First Page: Home Page] -> [Final Page: HomePage with user signed in]");

        //Creating an instance of a Chrome Driver

        driver.get("http://www.amazon.com");
        String screenshotPath = ScreenShots.takeScreenshot(driver,"T2PosSignUP");
        test.log(LogStatus.INFO, "Sign-Up Page");
        test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
        //Click on the Create Account Button
        WebElement sighInButton = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]"));
        sighInButton.click();

        WebElement apEmail = driver.findElement(By.id("ap_email"));
        apEmail.sendKeys("denisansah@yahoo.com");
        Thread.sleep(2000);
        WebElement apPassword = driver.findElement(By.id("ap_password"));
        apPassword.sendKeys("CaptainMegan");
        Thread.sleep(2000);
        screenshotPath = ScreenShots.takeScreenshot(driver,"T2PosDetails");
        test.log(LogStatus.INFO, "Details of the User entered");
        test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
        driver.findElement(By.id("signInSubmit")).click();
        Thread.sleep(1000);

//        if(CaptchCheck.checkForCaptch(driver,test,CaptchCheck.extent)){
//            Assert.fail();
//        }
        try{
            WebElement error  = driver.findElement(By.xpath("//*[@id=\"auth-warning-message-box\"]/div/h4"));

            test.log(LogStatus.WARNING, "Captcha Encounter!");
            test.log(LogStatus.WARNING, test.addScreenCapture(screenshotPath));
            Assert.fail();

        }catch (Exception e){

        }

        WebElement name = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]")).findElement(By.className("nav-line-1"));
        System.out.println(name.getText());
        screenshotPath = ScreenShots.takeScreenshot(driver, "T2PosFinal");

        try{
            Assert.assertNotEquals(name.getText(),("Hello, Sign in"));
            test.log(LogStatus.PASS, "Test Successfully Signed-In the User");
            test.log(LogStatus.PASS,test.addScreenCapture(screenshotPath));
            test.log(LogStatus.INFO,"Actual: [First Page: Home Page] -> [Final Page: HomePage with user signed in]");

        }catch (ComparisonFailure e){
            screenshotPath = ScreenShots.takeScreenshot(driver, "T2PosFinal");

            test.log(LogStatus.FAIL, "Test Unsuccessfully Signed-In the User");
            test.log(LogStatus.FAIL,test.addScreenCapture(screenshotPath));
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
