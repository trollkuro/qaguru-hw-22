package extension;

import api.AuthorizationApi;
import models.LoginUserRequestModel;
import models.LoginUserResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static data.TestData.PASSWORD;
import static data.TestData.USERNAME;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context){
        LoginUserRequestModel loginUserRequestModel = new LoginUserRequestModel(USERNAME, PASSWORD);
        AuthorizationApi authorizationApi = new AuthorizationApi();
        LoginUserResponseModel loginUserResponse = authorizationApi.loginUser(loginUserRequestModel);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginUserResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginUserResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginUserResponse.getExpires()));
    }
}