package com.test.Utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.utils.ExceptionUtil;
import com.test.Configuration.DeviceHelper;
import com.test.Configuration.LocalDriverManager;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Listener implements ITestListener {

    private static String getTestMethodName ( ITestResult iTestResult ) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart ( ITestContext iTestContext ) {
        iTestContext.setAttribute("WebDriver", LocalDriverManager.getDriver());
    }

    @Override
    public void onFinish ( ITestContext iTestContext ) {
        ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestStart ( ITestResult iTestResult ) {

        ExtentTestManager.createTest(iTestResult.getMethod().getMethodName().toUpperCase() + "-" + DeviceHelper.getDeviceName().toUpperCase(),
                "DeviceDetails:" + "<br></br>"
                        + "<b>DeviceName: <b>" + DeviceHelper.getDeviceName() + "<br></br>"
                        + "<b>DeviceUdid: <b>" + LocalDriverManager.getDriver().getPlatformName() + "<br></br>"
                        + "<b>DeviceModel: <b>" + LocalDriverManager.getDriver().getSessionDetails().get("deviceModel"));

        ExtentTestManager.getTest();
    }

    @Override
    public void onTestSuccess ( ITestResult iTestResult ) {
        ExtentTestManager.getTest().log(Status.INFO, "Test passed");
    }

    @Override
    public void onTestFailure ( ITestResult iTestResult ) {

        Object testClass = iTestResult.getInstance();
        AppiumDriver webDriver = LocalDriverManager.getDriver();

        try {

            File scrFile = ((TakesScreenshot) webDriver)
                    .getScreenshotAs(OutputType.FILE);

            String failedScreen =
                    System.getProperty("user.dir") + "/target/screenshot/" + "/"
                            + testClass.toString() + "_"
                            + currentDateAndTime() + "_" + "_failed" + ".png";


            FileUtils.copyFile(scrFile, new File(failedScreen));

            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
            ExtentTestManager.getTest().log(Status.INFO, "Failure Reason--->>> " + iTestResult.getThrowable().getCause().getMessage());
            ExtentTestManager.getTest().log(Status.INFO, "Exception Details--->>>" + ExceptionUtil.getStackTrace(iTestResult.getThrowable()));
            ExtentTestManager.getTest().addScreenCaptureFromPath(failedScreen, ExtentTestManager.getTest().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String currentDateAndTime () {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        return now.format(dtf);
    }

    @Override
    public void onTestSkipped ( ITestResult iTestResult ) {
        ExtentTestManager.extent.removeTest(ExtentTestManager.getTest());
        IRetryAnalyzer retryAnalyzer = iTestResult.getMethod().getRetryAnalyzer();
        if (((Retry) retryAnalyzer).retryCountForTest == ((Retry) retryAnalyzer).maxRetryCount) {
            ExtentManager.getReporter().flush();
        }
        System.out.println("I am onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped or failed on 1st Execution");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage ( ITestResult iTestResult ) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}