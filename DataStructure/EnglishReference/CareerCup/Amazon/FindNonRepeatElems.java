/*
    Find non repeating elements in an array

    I have an array consisting of 2n+1 elements. n elements 
    in it are married, i.e they occur twice in the array, 
    however there is one element which only appears once in 
    the array. I need to find that number in a single pass 
    using constant memory. {assume all are positive numbers}

    E.g. 3, 4, 1, 3, 1, 7, 2, 2, 4.
    Ans: 7

    Time complixity: O(n)
    Space complexity: O(1)

    We can XOR all the elements in the array. The result is that
    single person.
*/

import java.util.*;
import java.lang.*;

class findNonRepeatElems {
    public static int findTheSinglePerson(int[] array) {
        int result = 0;
        for (int i : array) {
            result ^= i; 
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 7, 7, 1, 4, 5};
        System.out.println(findTheSinglePerson(array));
    }
}
