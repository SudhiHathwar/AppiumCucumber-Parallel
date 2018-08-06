package com.test.Screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;

public class SearchScreen {

    @AndroidFindBy(accessibility = "Skip Button")
    @iOSFindBy(accessibility = "Skip Button")
    public WebElement skipButton;

    @AndroidFindBy(accessibility = "from station")
    @iOSFindBy(accessibility = "from station")
    public WebElement fromStationSearch;

    @AndroidFindBy(accessibility = "to station")
    @iOSFindBy(accessibility = "to station")
    public WebElement toStationSearch;

    @AndroidFindBy(accessibility = "journeySearchType")
    @iOSFindBy(accessibility = "journeySearchType")
    public WebElement ticketTypeButton;

    @AndroidFindBy(accessibility = "Select")
    @iOSFindBy(accessibility = "Select")
    public WebElement selectButton;

    @AndroidFindBy(accessibility = "enterStationName")
    @iOSFindBy(accessibility = "enterStationName")
    public WebElement enterStationName;

    @AndroidFindBy(accessibility = "stationFinder")
    @iOSFindBy(accessibility = "stationFinder")
    public WebElement selectStationName;

    @AndroidFindBy(accessibility = "Find trains")
    @iOSFindBy(accessibility = "Find trains")
    public WebElement findTrains;
}
