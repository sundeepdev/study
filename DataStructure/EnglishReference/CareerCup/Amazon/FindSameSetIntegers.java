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
        5           return true
        6   return false 

        Time Complexity: O(max(firstList.size(), secondList.size()))
        Space Complexity: O(firstList.size() + secondList.size())

        Can we find a better solution to reduce the space complexity??
        
    */
    public static boolean findSameSetIntegers(ArrayList<Integer> firstList, 
                                              ArrayList<Integer> secondList) {
        
        HashSet<Integer> firstSet = new HashSet<Integer>();
        
        for (Integer i : firstList) {
            firstSet.add(i);
        } 

        for (Integer j : secondList) {
            if (firstSet.contains(j)) {
                return true;
            }
        }

        return false;
    }

    
    public static boolean findSameSetIntegers2(ArrayList<Integer> firstList, 
                                               ArrayList<Integer> secondList) {
    
        int result = 0;

        for (Integer i : firstList) {
            result = result | MurmurHash.hash32( i.toString());
        }

        for (Integer j : secondList) {
            if ((result | MurmurHash.hash32( j.toString())) == result) {
                return true;
            }
        }

        return false;
    }
    

    public static void main(String[] args) {
        ArrayList<Integer> firstList = new ArrayList<Integer>();
        firstList.add(1);
        firstList.add(2);
        firstList.add(3);
        firstList.add(100);
        firstList.add(-8);
        firstList.add(102);
        firstList.add(33);
        firstList.add(34);
        firstList.add(-29);
        firstList.add(-1092);
        firstList.add(1293);
        firstList.add(39);

        ArrayList<Integer> secondList = new ArrayList<Integer>();
        secondList.add(32);

        boolean result = findSameSetIntegers2(firstList, secondList);

        System.out.println(result);
    }
}
