package com.test.StepDefinitions;

import com.test.Configuration.DeviceHelper;
import com.test.Configuration.LocalDriverManager;
import com.test.Screens.SearchScreen;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends DeviceHelper {

    SearchScreen searchPageObjects = new SearchScreen();

    public SearchPage () {
        PageFactory.initElements(new AppiumFieldDecorator(LocalDriverManager.getDriver()), searchPageObjects);
    }

    @When("^User is on Search page$")
    public void clickOnSkip () {
        waitForElement(searchPageObjects.skipButton).click();
    }


    @When("^User performs a Search from \"([^\"]*)\" to Manchester Station$")
    public void selectFromToStation ( String fromStation ) {

        openFromStation().enterStationName(fromStation).chooseStation();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        openToStation().enterStationName("Manchester").chooseStation();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clickOnFindTrains();
    }

    public void selectTicketTypes () {
        searchPageObjects.ticketTypeButton.click();

        searchPageObjects.selectButton.click();
    }

    public void clickOnFindTrains () {
        searchPageObjects.findTrains.click();
    }

    public SearchPage chooseStation () {
        waitForElement(searchPageObjects.selectStationName).click();
        return this;
    }

    public SearchPage enterStationName ( String fromStation ) {
        waitForElement(searchPageObjects.enterStationName).sendKeys(fromStation);
        return this;
    }

    public SearchPage openToStation () {
        waitForElement(searchPageObjects.toStationSearch).click();
        return this;
    }

    public SearchPage openFromStation () {
        waitForElement(searchPageObjects.fromStationSearch).click();
        return this;
    }
}
