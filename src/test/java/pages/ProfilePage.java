package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    SelenideElement userName = $("#userName-value"),
    noRowsFound =  $(".rt-noData");

    public ProfilePage openProfilePage(){
        open("/profile");
        return this;
    }

    public ProfilePage verifyUserName(String name){
        userName.shouldBe(text(name));
        return this;
    }

    public ProfilePage verifyNoResult(){
        noRowsFound.shouldBe(visible);
        return this;
    }
}
