package com.me.test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import sun.jvm.hotspot.utilities.Assert;

public class CaptchCheck {

//    public static ExtentReports extent = new ExtentReports("/Users/megandsouza/Desktop/seleniumReports/Amazon_Selenium_Basic_Function_Test"+System.currentTimeMillis()+".html");
    public static ExtentReports extent = new ExtentReports("/Users/denis/Dropbox/Summer2019/QA/SeleniumProject/seleniumReports/Amazon_Selenium_Basic_Function_Test"+System.currentTimeMillis()+".html");

    public static boolean checkForCaptch(WebDriver driver, ExtentTest test, ExtentReports extent) throws Exception {
        try {
            WebElement error = driver.findElement(By.id("auth-error-message-box")).findElement(By.className("a-list-item"));
            String screenshotPath = ScreenShots.takeScreenshot(driver, "T1Poscaptch");
            System.out.println(error.getText());
            if (error.getText().equals("Enter the characters as they are given in the challenge.")) {
                test.log(LogStatus.WARNING, "Captcha Encountered!");
                test.log(LogStatus.WARNING, test.addScreenCapture(screenshotPath));

                extent.endTest(test);
                extent.flush();
                driver.quit();
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;

        }
        return false;
    }
}
