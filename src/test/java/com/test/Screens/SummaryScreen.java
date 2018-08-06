package com.test.Screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;

public class SummaryScreen {

    @AndroidFindBy(accessibility = "View Journey Details")
    @iOSFindBy(accessibility = "View Journey Details")
    public WebElement journeyDetailsButton;

    @AndroidFindBy(accessibility = "LegView_1")
    @iOSFindBy(accessibility = "LegView_1")
    public WebElement journeyLegs;

    @AndroidFindBy(accessibility = "CancelButton")
    @iOSFindBy(accessibility = "CancelButton")
    public WebElement cancelButton;
}
