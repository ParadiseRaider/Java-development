package shop.rest;

import lombok.Getter;

public class MessageError {

    @Getter
    String error;

    public MessageError(String error) {
        this.error = error;
    }
}
