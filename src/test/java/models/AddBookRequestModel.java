package models;

import lombok.Data;

import java.util.List;

@Data
public class AddBookRequestModel {
    private String userId;
    List<IsbnModel> collectionOfIsbns;

    @Data
    public static class IsbnModel{
        String isbn;
    }
}