package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import configs.WebBrowserConfig;
import helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import models.LoginUserRequestModel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static data.TestData.PASSWORD;
import static data.TestData.USERNAME;

public class BaseTest {

    protected LoginUserRequestModel loginUserRequestModel = new LoginUserRequestModel(USERNAME, PASSWORD);

    @BeforeAll
    static void setUP(){
        RestAssured.baseURI = "https://demoqa.com";

        WebBrowserConfig webBrowserConfig = ConfigFactory.create(WebBrowserConfig.class, System.getProperties());
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = webBrowserConfig.browserName();
        Configuration.browserVersion = webBrowserConfig.browserVersion();
        Configuration.browserSize = webBrowserConfig.browserSize();

        if(webBrowserConfig.isRemote()){
            Configuration.remote = webBrowserConfig.remoteURL();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.pageSource();
        if (!Configuration.browser.equals("firefox")) {
            AllureAttachments.browserConsoleLogs();
        }
        AllureAttachments.addVideo();
        closeWebDriver();
    }

}
