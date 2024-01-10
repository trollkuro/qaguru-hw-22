package api;

import models.AddBookRequestModel;
import models.DeleteBookRequestModel;
import models.LoginUserResponseModel;

import java.util.ArrayList;
import java.util.List;

import static data.Endpoints.*;
import static io.restassured.RestAssured.given;
import static specs.BookSpec.*;

public class BooksApi {

    public void addBook(LoginUserResponseModel loginUserResponse, String BookIsbn){

//        AddBookRequestModel.IsbnModel isbnModel = new AddBookRequestModel.IsbnModel();
//        isbnModel.setIsbn(Bookisbn);
//        List<AddBookRequestModel.IsbnModel> isbnList = new ArrayList<>();
//        isbnList.add(isbnModel);


        AddBookRequestModel.IsbnModel isbnModel = new AddBookRequestModel.IsbnModel();
        isbnModel.setIsbn(BookIsbn);
        List<AddBookRequestModel.IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        AddBookRequestModel addBookRequest = new AddBookRequestModel();
        addBookRequest.setCollectionOfIsbns(isbnList);
        addBookRequest.setUserId(loginUserResponse.getUserId());

        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginUserResponse.getToken())
                .body(addBookRequest)
                .when()
                    .post(ADD_BOOK)
                .then()
                    .spec(bookAddResponseSpec);
    }

    public void deleteAllBooks(LoginUserResponseModel loginUserResponse){
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginUserResponse.getToken())
                .queryParam("UserId", loginUserResponse.getUserId())
                .when()
                    .delete(DELETE_ALL_BOOKS)
                .then()
                    .spec(bookDeleteResponseSpec);
    }

    public void deleteOneBook(LoginUserResponseModel loginUserResponse, String BookIsbn){
        DeleteBookRequestModel deleteBookRequest = new DeleteBookRequestModel();
        deleteBookRequest.setIsbn(BookIsbn);
        deleteBookRequest.setUserId(loginUserResponse.getUserId());
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginUserResponse.getToken())
                .body(deleteBookRequest)
                .when()
                    .delete(DELETE_BOOK)
                .then()
                    .spec(bookDeleteResponseSpec);
    }
}
