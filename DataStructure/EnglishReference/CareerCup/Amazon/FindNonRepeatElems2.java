/*
    Given an array in which all numbers except two are repeated once.
    (i.e. we have 2n+2 numbers and n numbers are occuring twice and
    remaining two have occured once. Find those two numbers in the
    most efficient way.

*/

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
    */
    public static int[] findNonRepeatElemTwo(int[] array) {
        int xor = 0;
        int set_bit_no;
    }

    /* Get the xor of all elements */
    for (i = 0; i < n; i++) {
        xor ^= array[i];
    }

    /* The the right most set bit in set_bit_no */
}
