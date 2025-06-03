package org.example.v3.colors;

public enum Color {

    RED,

    GREEN,
    BLUE;

    public static Color colorFromString(String colorString) throws ColorException
    {
        /*if (colorString == null|| colorString.isEmpty()) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }*/
        /*if (colorString !=Color) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }*/
        try {
            return Color.valueOf(colorString);
        }
        catch(IllegalArgumentException e){
         throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }
        catch(NullPointerException e){
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
    }
    //colorFromString(String colorString) throws ColorException
}

