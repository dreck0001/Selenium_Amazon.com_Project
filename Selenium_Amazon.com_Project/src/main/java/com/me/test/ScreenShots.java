package com.me.test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShots {

    public static String takeScreenshot(WebDriver driver, String name) throws Exception {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destination = "/Users/denis/Dropbox/Summer2019/QA/SeleniumProject/seleniumReports/"+name+".png";
        FileUtils.copyFile(scrFile, new File(destination));
        return destination;
    }


}
