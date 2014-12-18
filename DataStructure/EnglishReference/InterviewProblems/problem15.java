import java.util.*;
import java.lang.*;

/*
    Write code to reverse a C-Style String. (C-String means that "abcd" is represented
    as five characters, including the null character.)
*/

class problem15 {
    public static String reverse(String thisString) {
        if (thisString.length() <= 1) {
            return null;
        }

        StringBuffer result = new StringBuffer(thisString);
        for (int i = 0; i < (thisString.length()-1)/2; i++) {
            char first = thisString.charAt(i);
            char second = thisString.charAt(thisString.length()-2-i);
            result.setCharAt(i, second);
            result.setCharAt(thisString.length()-2-i, first);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String thisString = "abcd adfadfa \0";
        System.out.println(reverse(thisString));
    }
}
