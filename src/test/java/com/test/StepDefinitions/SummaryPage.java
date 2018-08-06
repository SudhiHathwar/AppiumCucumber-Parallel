package com.test.StepDefinitions;

import com.test.Configuration.DeviceHelper;
import com.test.Configuration.LocalDriverManager;
import com.test.Screens.SummaryScreen;
import cucumber.api.java.en.Then;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SummaryPage extends DeviceHelper {
    SummaryScreen summaryScreen = new SummaryScreen();

    public SummaryPage () {
        PageFactory.initElements(new AppiumFieldDecorator(LocalDriverManager.getDriver()), summaryScreen);
    }

    @Then("^User must be displayed with Summary page with journey details$")
    public void VerifyJourneyDetails () {
        Assert.assertTrue(waitForElement(summaryScreen.journeyDetailsButton).isDisplayed());

        waitForElement(summaryScreen.journeyDetailsButton).click();

        Assert.assertTrue(waitForElement(summaryScreen.journeyLegs).isDisplayed());

        waitForElement(summaryScreen.cancelButton).click();

        Assert.assertTrue(summaryScreen.journeyDetailsButton.isDisplayed());
    }

}
