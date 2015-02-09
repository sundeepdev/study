/*
    Find a pair of elements from an array whose sum equals a given number.

    Some questions you may ask during an interview:

    -   Does array contains only positive or negative numbers?
    -   What if same pair repeats twice, should we print it every time?
    -   Is reverse of pair is acceptable e.g. can we print both (4, 1) and (1, 4) if given sum is 5?
    -   Do we need to print only distinct pair? does (3, 3) is a valid pair for given sum of 6?
    -   How big the array is?
*/

import java.util.*;
import java.lang.*;

class FindSumInAnArray {
    /*
        Use HashSet to store temporary numbers to be matched. E.g. For a given sum 7, if we see an element
        3, instead of storing 3 in the hash table, we store 7-3=4. So that next time if we see number 4, we check
        the hashtable to see 4 is already there. If yes, we print: (3, 4) and remove 3 from the hashtable.

        Time Complexity: O(n)
        Space Complexity: O(n)

    */
    public static void printPairs(int[] array, int sum) {
        HashSet<Integer> hset = new HashSet<Integer>();

        for(int i : array) {
            if (hset.contains(i)) {
                int x = sum-i;
                int y = i;
                System.out.println("(" + x + "," + y + ")");
            }
            hset.add(sum-i);
        }
    }

    /*
        The hashset solution above is better than the brute force solution but still has big problems: 
        If an array is very large, you need need a solution which doesn't require additional space, also
        known as in-place solution. An interviewer will ask you how to find if two values in an array 
        sum to a given value without any additional space. A more efficient in-place solution would be to
        sort the array and use two pointers to scan through array from both directions e.g. beginning and end
        
        1)  If sum of both the values are equal to given number then we output the pair and advance them; 
        2)  If the sum of the two numbers is less than k then we increase the left pointer; 
        3)  If the sum of the two numbers is greater than k then we decrement the right pointer until both
            pointers meet at some part of the array. 

        The complexity of this solution would be O(NlogN) due to sorting. Remember to use a in-place sorting
        algorithm like quicksort to sort the array as we don't have additional space. Thinkfully, Arrays.sort()
        method uses a two pivot quicksort algorithm to sort array of primitives.

        Note: This method doesn't allow repairs repeat.

    */

    public static void printPairsWithSorting(int[] array, int sum) {
        if (array == null || array.length < 2) {
            return;
        } 

        Arrays.sort(array);

        int left = 0;
        int right = array.length - 1;

        while(left < right) {
            if (array[left] + array[right] == sum) {
                System.out.println("(" + array[left] + "," + array[right] + ")");
                left++;
                right--;
            } else if (array[left] + array[right] < sum) {
                left++;
            } else {
                right--;
            }
        }
    }
        
    public static void main(String[] args) {
        int[] array = {2, 4, 3, 5, 6, -2, 4, 7, 8, 9};
        
        printPairsWithSorting(array, 7);
    }
}
