/*

You are given a 2D Array(m*n) that contains only 0s and 1s in sorted order. i.e.

Array:

0 0 0 1
1 1 1 1
0 0 1 1
0 1 1 1

You have to figure out the row that contains maximum number of 1s.

e.g. in above case we have row 2 as the answer.

Solution1:

-   Solution 1

    Instead of iterating over elements row by row, we can do it line by line.

    For each column, starting from left to right, we try to find which element has the 1. Therefore, we know
    the row which contains that element has the maximum number of 1s.

    Time Complexity: n*m
    Space Complexity: nothing

-   Solution 2
    1.  start scanning from right most end of first row and find first occurrence of 0. 
        suppose it is some value x. then max_numer of 1 till now is col-x-1. 
    2.  then go to next row but start from x. if you found 1 at that place then scan this 
        row and find new x as in first step and replace max_number by new value of col-x-1. 
    3.  repeat it till last row mean while if you get x=-1 then return the length of col. 

    Time Complexity: m*log(n), why log(n)? because we can use binary search to do row scanning.
    Space Complexity: nothing
*/

import java.util.*;
import java.lang.*;

class 2DArray {

    /*
        search 0 that is the first  
    */
    private int binarySearch(boolean[][] array, int x, int y) {
        
    }

}
