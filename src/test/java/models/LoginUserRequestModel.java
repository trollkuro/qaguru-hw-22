package models;

import lombok.Data;

@Data
public class LoginUserRequestModel {

    private String userName;
    private String password;

    public LoginUserRequestModel(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

}
