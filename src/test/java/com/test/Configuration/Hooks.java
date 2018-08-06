package com.test.Configuration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;

public class Hooks {

    static URL url;

    @BeforeSuite
    public void startServer () {

        AppiumDriverLocalService service;

        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort();
        service = appiumServiceBuilder.build();

        ServiceManager.setService(service);

        ServiceManager.getService().start();


        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
        }

        url = service.getUrl();

        System.out.println("Server started Successfully on " + service.getUrl());
    }

    @AfterSuite
    public void stopServer () {
        if (ServiceManager.getService() != null) {
            ServiceManager.getService().stop();
        }
    }

    @Parameters({"platform", "platformVersion", "deviceName", "port", "udid"})
    @BeforeClass(alwaysRun = true)
    public void launchDriver ( String platform, String platformVersion, String deviceName, String port, String udid ) {

        if (platform.toLowerCase().equals("android")) {
            LocalDriverManager.setWebDriver(getAndroidDriver(ServiceManager.getService(), platformVersion, deviceName, port, udid));

            DeviceHelper.setDeviceName(deviceName);

        } else {
            LocalDriverManager.setWebDriver(getIOSDriver(ServiceManager.getService(), platformVersion, deviceName, port, udid));

            DeviceHelper.setDeviceName(deviceName);
        }
    }

    @AfterClass(alwaysRun = true)
    public void disposeDriver () {
        if (LocalDriverManager.getDriver() != null) {
            LocalDriverManager.getDriver().quit();
        }
    }

    private AppiumDriver getIOSDriver ( AppiumDriverLocalService service, String platformVersion, String deviceName, String wdaLocalPort, String udid ) {

        if (url != null) {

            File appDir = new File("src/test/resources/");
            File app = new File(appDir, "ipa file name");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability("wdaLocalPort", Integer.valueOf(wdaLocalPort));
            capabilities.setCapability(MobileCapabilityType.APP, app);
            capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            capabilities.setCapability(IOSMobileCapabilityType.PREVENT_WDAATTACHMENTS, false);
            capabilities.setCapability("udid", udid);
            capabilities.setCapability(IOSMobileCapabilityType.WDA_CONNECTION_TIMEOUT, 80000);
            capabilities.setCapability(IOSMobileCapabilityType.WDA_LAUNCH_TIMEOUT, 80000);
            capabilities.setCapability(IOSMobileCapabilityType.SHOULD_USE_SINGLETON_TESTMANAGER, false);
            capabilities.setCapability(IOSMobileCapabilityType.SIMPLE_ISVISIBLE_CHECK, true);
            capabilities.setCapability(IOSMobileCapabilityType.MAX_TYPING_FREQUENCY, 10);
            capabilities.setCapability("clearSystemFiles", true);
            return new IOSDriver<IOSElement>(url, capabilities);
        } else {
            System.out.println("You have to launch appium server before launching driver");
            return null;
        }
    }

    private AppiumDriver getAndroidDriver ( AppiumDriverLocalService service, String platformVersion, String deviceName, String portNumber, String udid ) {

        if (url != null) {

            File appDir = new File("src/test/resources/");
            File app = new File(appDir, "apk file name");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "appium");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);

            if (udid != "") {
                capabilities.setCapability("udid", udid);
            }

            capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, Integer.valueOf(portNumber));
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            return new AndroidDriver<AndroidElement>(url, capabilities);

        } else {
            System.out.println("You have to launch appium server before launching driver");
            return null;
        }
    }
}