package api;

import models.LoginUserRequestModel;
import models.LoginUserResponseModel;

import static data.Endpoints.LOGIN;
import static io.restassured.RestAssured.given;
import static specs.LoginUserSpec.loginUserRequestSpec;
import static specs.LoginUserSpec.loginUserResponseSpec;

public class AuthorizationApi {
    public LoginUserResponseModel loginUser(LoginUserRequestModel loginUserRequest){
        return given(loginUserRequestSpec)
                .body(loginUserRequest)
                .when()
                    .post(LOGIN)
                .then()
                    .spec(loginUserResponseSpec)
                    .statusCode(200)
                    .extract().as(LoginUserResponseModel.class);
    }
}
