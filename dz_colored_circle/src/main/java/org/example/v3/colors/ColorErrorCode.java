package org.example.v3.colors;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("Wrong color string format"),
    NULL_COLOR("Color string is null");
    private String errorString;

    ColorErrorCode(String error)
    {
        this.errorString=error;
    }
    String getCode()
    {
        return this.errorString;
    }
    String setCode(String code)
    {
        return this.errorString=code;
    }
}
