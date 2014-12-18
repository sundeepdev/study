import java.util.*;
import java.lang.*;
import org.junit.*;

import static org.junit.Assert.assertEquals;
//import org.junit.Test;
/*
    Design an algorithm and write code to remove the duplicate characters in a string
    without using any additional buffer. Note: One or two additional variables are 
    fine. An extra copy of the array is not. FOLLOW UP: Write the test cases for this
    method

    Question to ask: what is an additional buffer? Can we use an additional array of constant size?
*/

class problem16 {
    
    public static String removeDuplicate(String thisString) {
    
        //The checker is only 32 bit. It means it can only detect 32 characters. This is the
        //limitation of this algorithm
        int checker = 0;
        String result = "";

        for (int i = 0; i < thisString.length(); i++) {
            int value = thisString.charAt(i) - 'a';
            if ((checker & (1<<value)) > 0) {
                continue;
            } else {
                result += thisString.charAt(i);
                checker |= (1<<value);
            }
        }

        return result;
    }

    public static void removeDuplicates2(char[] str) {
        if (str == null) {
            return;
        }
        
        int len = str.length;
        if (len < 2) {
            return;
        }

        //create a 256-slot boolean array. The default value of each slot is false
        boolean[] hit = new boolean[256];

        hit[str[0]] = true;
        int tail = 1;

        //It takes O(n)
        for (int i = 1; i < len; i++) {
            if (!hit[str[i]]) {
                str[tail] = str[i];
                ++tail;
                hit[str[i]] = true;
            }
        }

        str[tail] = 0;
        System.out.println("tail = " + tail);
    }
    //Get over the weakness in the method removeDuplicate
    //It takes O(n.^2)
    public static void removeDuplicates(char[] str) {
        if (str == null) {
            return;
        }
        int len = str.length;
        if  (len < 2) {
            return;
        }

        int tail = 1;
        
        for (int i = 1; i < len; ++i) {
            int j;
            for (j = 0; j < tail; ++j) {
                if (str[i] == str[j]) {
                    break;
                }
            }
            if (j == tail) {
                str[tail] = str[i];
                ++tail;
            }
        }

        str[tail] = 0;
    }
    @Test
    public static void testRemoveDuplicate() {
        assertEquals("abc", removeDuplicate("aabbcc"));
        assertEquals("a", removeDuplicate("aa"));
        assertEquals("abc", removeDuplicate("abc"));
        assertEquals("", removeDuplicate(""));
        assertEquals("abc", removeDuplicate("ababab"));
    }
    
    public static void main(String[] args) {
        //System.out.println(removeDuplicate("aaabbbdasfoasderb"));
        //testRemoveDuplicate();
        //
        char[] str = {'a', 'b', 'a', 'b', 'a', 'b'};
        removeDuplicates2(str);
        //System.out.println(str);
        for (int i = 0; i < str.length; i++) {
                   
        }
    }
}
