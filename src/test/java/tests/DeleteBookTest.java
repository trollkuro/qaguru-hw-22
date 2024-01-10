package tests;

import api.AuthorizationApi;
import api.BooksApi;
import extension.WithLogin;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.LoginUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static data.TestData.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Books")
public class DeleteBookTest extends BaseTest {
    private AuthorizationApi authorizationApi = new AuthorizationApi();
    private LoginUserResponseModel loginUserResponse = authorizationApi.loginUser(loginUserRequestModel);
    private BooksApi booksApi = new BooksApi();
    private ProfilePage profilePage = new ProfilePage();


    @Test
    @WithLogin
    @Owner("kegorova")
    @Feature("Books")
    @DisplayName("Delete book from user's list")
    void deleteBookTest(){
        step("Delete all books from the list (api)", () -> {
            booksApi.deleteAllBooks(loginUserResponse);
        });
        step("Add book in the list (api)", () -> {
            booksApi.addBook(loginUserResponse, BOOK_ISBN);
        });
        step("Delete added book from the list (api)", () -> {
            booksApi.deleteOneBook(loginUserResponse, BOOK_ISBN);
        });
        step("Open profile page", () -> {
            profilePage.openProfilePage();
        });
        step("Verify book is deleted", () -> {
            profilePage.verifyUserName(USERNAME)
                    .verifyNoResult();
        });
    }
}
