package org.example.v3.colors;

//import java.io.Exception;
public class ColorException extends Exception {

    private final ColorErrorCode errorCode;

    public ColorException(ColorErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
    }

    public ColorErrorCode getErrorCode() {
        return errorCode;
    }
}