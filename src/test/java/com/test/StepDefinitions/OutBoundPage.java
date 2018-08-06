package com.test.StepDefinitions;

import com.test.Configuration.DeviceHelper;
import com.test.Configuration.LocalDriverManager;
import com.test.Screens.OutboundScreen;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OutBoundPage extends DeviceHelper {

    OutboundScreen outboundScreen = new OutboundScreen();

    public OutBoundPage () {
        PageFactory.initElements(new AppiumFieldDecorator(LocalDriverManager.getDriver()), outboundScreen);
    }

    @When("^User selects a ticket$")
    public void ClickOnAnyTicket () {
        Assert.assertTrue(waitForElement(outboundScreen.ticket).isDisplayed());

        outboundScreen.ticket.click();
    }

}
