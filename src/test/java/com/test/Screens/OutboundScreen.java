package com.test.Screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;

public class OutboundScreen {

    @AndroidFindBy(accessibility = "journeySearchResultsContainer1")
    @iOSFindBy(accessibility = "journeySearchResultsContainer1")
    public WebElement ticket;
}
