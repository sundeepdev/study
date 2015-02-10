/*
    Given 2 arrays of numbers find if each of the two arrays have the same set of integers.

    Suggest an algorithm which run faster than NlogN
*/

import java.util.*;
import java.lang.*;

class FindSameSetIntegers {
    /*
        This solution is quite straight forward:

        Find-SAME-SET-INTEGERA
        1   foreach i in firstList
        2       add i to the hashset called firstSet
        3   foreach j in secondList
        4       if firstSet.contains(j)
        5           add j to the hashset called result
        6   return result

        Time Complexity: O(max(firstList.size(), secondList.size()))
        Space Complexity: O(firstList.size() + secondList.size())

        Can we find a better solution to reduce the space complexity??
        
    */
    public static HashSet<Integer> findSameSetIntegers(ArrayList<Integer> firstList, 
                                                          ArrayList<Integer> secondList) {
        
        HashSet<Integer> firstSet = new HashSet<Integer>();
        HashSet<Integer> result = new HashSet<Integer>();
        
        
        for (Integer i : firstList) {
            firstSet.add(i);
        } 

        for (Integer j : secondList) {
            if (firstSet.contains(j)) {
                result.add(j);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> firstList = new ArrayList<Integer>();
        firstList.add(1);
        firstList.add(2);
        firstList.add(3);

        ArrayList<Integer> secondList = new ArrayList<Integer>();
        secondList.add(2);

        HashSet<Integer> result = findSameSetIntegers(firstList, secondList);

        System.out.println(result.toString());
    }
}
