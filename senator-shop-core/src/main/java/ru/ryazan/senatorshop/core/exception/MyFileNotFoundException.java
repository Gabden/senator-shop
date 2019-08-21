package ru.ryazan.senatorshop.core.exception;

//It’s thrown when a file that the user is trying to download is not found.

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String s) {
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
