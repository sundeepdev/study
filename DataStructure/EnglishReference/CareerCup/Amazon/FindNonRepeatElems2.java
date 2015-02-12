/*
    Given an array in which all numbers except two are repeated once.
    (i.e. we have 2n+2 numbers and n numbers are occuring twice and
    remaining two have occured once. Find those two numbers in the
    most efficient way.

*/

import java.util.*;
import java.lang.*;

class FindNonRepeatElemsTwo {
    
    /*
        Let x and y be the non-repeating elements we are looking
        for and arr[] be the input array. First calculate the XOR
        of all the array elements.

        xor = arr[0]^[arr[1].....arr[n-1]

        All the bits that are set in xor will be set in one non-repeating
        element(x or y) and not in other. So if we take any set bit
        of xor and divide the elements of the array in two sets - one
        set of elements with same bit set and other set with same bit
        not set. By doing so, we will get x in one set and y in another
        set. Now if we do XOR of all the elements in first set, we will
        get first non-repeating element, and by doing same in other set
        we will get the second non-repeating element.

        Note(!!): Since x != y, we are 100% sure that x^y cannot produce 0. That
        means there is at least one bit is set to 1.

        Let us see an example.
           arr[] = {2, 4, 7, 9, 2, 4}

        1) Get the XOR of all the elements.
             xor = 2^4^7^9^2^4 = 14 (1110)
        2) Get a number which has only one set bit of the xor.   
           Since we can easily get the rightmost set bit, let us use it.
             set_bit_no = xor & ~(xor-1) = (1110) & ~(1101) = 0010
           Now set_bit_no will have only set as rightmost set bit of xor.
        3) Now divide the elements in two sets and do xor of         
           elements in each set, and we get the non-repeating 
           elements 7 and 9. Please see implementation for this   
           step.

        Note(!!): To get the rightmost set bit, we can do:

        x & ~(x-1)

        Time complexity: O(n)
        Space compliexity: O(1)

    */
    public static int[] findNonRepeatElemTwo(int[] array) {
        if (array == null || array.length < 4) {
            return null;
        }

        int xor = 0;
        int set_bit_no;
        int n = array.length;

        int x = 0;
        int y = 0;

        /* Get the xor of all elements */
        for (int i = 0; i < n; i++) {
            xor ^= array[i];
        }

        /* Get the right most set bit in set_bit_no */
        set_bit_no = xor & ~(xor-1);

        //System.out.println(xor);
        //System.out.println(set_bit_no);

        /*  Now divide elements in two sets by comparing rightmost set
            bit of xor with bit at same position in each element */
        for (int i = 0; i < n; i++) {
            if (((array[i]) & set_bit_no) != 0) {
                x = x ^ array[i];
            } else {
                y = y ^ array[i];
            } 
        }

        int[] result = new int[2];
        result[0] = x;
        result[1] = y;

        return result;
       
    }

    public static void main(String[] args) {
        int array[] = {2, 3, 7, 9, 11, 2, 3, 11};
        int[] result = findNonRepeatElemTwo(array);

        
        System.out.println(Arrays.toString(result));

        return;
    }
}   
