/*
Write a method to decide if two strings are anagrams or not
e.g. "tea" and "eat" are anagrams
*/

import java.util.*;
import java.lang.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/*

Time complexity: O(2n). 
Storage complexity: hmap, whose size is the total distince characers in firstString and secondString

*/

class problem17 {
    public static boolean testAnagrams(String firstString, String secondString) {
        int firstLen = firstString.length();
        int secondLen = secondString.length();

        if (firstLen == 0 || secondLen == 0) {
            return false;
        }

        if (firstLen != secondLen) {
            return false;
        }
    
        //Use hashmap do char stastics
        HashMap<Character, Integer> hmap = new HashMap<Character, Integer>();
        
        //Iterate though the first string and update hmap. This takes O(n).
        for (int i = 0; i < firstLen; i++) {
            Character k = firstString.charAt(i);
            if (hmap.containsKey(k)) {
                Integer v = hmap.get(k);
                v++;
                hmap.put(k, v);
            } else {
                hmap.put(k, 1);
            }
        }

        //Iterate through the second second string and update hmap. This takes another O(n).
        for (int j = 0; j < secondLen; j++) {
            Character k = secondString.charAt(j);
            if (hmap.containsKey(k)) {
                Integer v = hmap.get(k);
                v--;
                if (v == 0) {
                    hmap.remove(k);
                } else {
                    hmap.put(k, v);
                }

            } else {
                return false;
            }
        }

        if (hmap.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        assertEquals(true, testAnagrams("eat", "ate"));
        assertEquals(false, testAnagrams("eat", "aat"));
        assertEquals(false, testAnagrams("", ""));
    }
}
