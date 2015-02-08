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
        suppose it is some value y. then max_numer of 1 till now is col-y-1. 
    2.  then go to next row but start from y. if you found 1 at that place then scan this 
        row and find new x as in first step and replace max_number by new value of col-y-1. 
    3.  repeat it till last row mean while if you get y=-1 then return the length of col. 

    Time Complexity: O(m+n)
    Space Complexity: nothing

    Analysis: This algorithm is very efficient and easy to understand. In short, see "0" goes
    "down"; see "1" goes left.
*/

import java.util.*;
import java.lang.*;

class TwoDArray {
    class Pos {
        int x;
        int y; 

        Pos(int x, int y) {
            this.x = x;    
            this.y = y;    
        }
    }

    private int[][] array; //The 2D array

    public TwoDArray() {
        array = null;    
    }

    public TwoDArray(int[][] array) {
        this.array = array;
    }

    public int whichRowHasMaxOnes_1() {
        int col = array[0].length;
        int i = 0;
        while (i < col) {
            Pos p = new Pos(0, i);
            Pos result = verticalSearch(p);
            if (result != null) {
                return result.x;
            }
            i++;
        }

        return -1;
    }

    /*
        Solution 2

        1.  start scanning from right most end of first row and find first occurrence of 0. 
            suppose it is some value y. then max_number of 1 till now is col-y-1. 
        2.  then go to next row but start from y. if you found 1 at that place then scan this 
            row and find new x as in first step and replace max_number by new value of col-y-1. 
        3.  repeat it till last row mean while if you get y=-1 then return the length of col. 
    */

    public int whichRowHasMaxOnes_2() {
        int row = array.length;
        int col = array[0].length;
        int x = 0;
        int y = col - 1;
        int result = 0;

        while(x < row) {
            result = x;
            Pos p = new Pos(x, y);//now p is (0, col-1), which is the top-right node.
            Pos p_new = HorizontalSearch(p);
            if (p_new == null) { 
                //couldn't find 0. That means this row is all 1.
                return result;
            } else {
                //found one zero node, look at one row below:
                x = x + 1;
                y = p_new.y;      

                if (x == row) {
                    return result;
                } else {
                    while((array[x][y] != 1) && (x < row) ) {
                        x = x + 1;
                    }
                }
            }
        }

        return result;
    }

    /*
     *  Search element "0" by scanning from the position p(x, y) to the left
     *  input: the int array; the starting position p(x, y)
     *  output: the position p'(x', y') that has the value "0". or null if it 
        couldn't find the position has value "0".
     */
    private Pos HorizontalSearch(Pos p) {
        if (array[p.x][p.y] == 0) {
            return p;
        } else {
            while (p.y >= 0) {
                if (array[p.x][p.y] == 0) {
                    return p;                   
                }

                p.y = p.y - 1;
            }

            return null;
        }
    }

    /*
        Search element "1" starting from the position p(x, y) downward
        input:  the int array; the starting position p(x, y)
        output: the position p'(x', y'), which has the value "1".
    */
    private Pos verticalSearch(Pos p) {
        if (array[p.x][p.y] == 1) {
            return p;
        }

        int row = array.length;

        while(p.x < row) {
            if (array[p.x][p.y] == 1) {
                return p;
            }
            p.x = p.x + 1;
        }

        return null;
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{
            {0, 0, 0, 1},
            {0, 0, 1, 1},
            {1, 1, 1, 1},
            {0, 1, 1, 1},
        };
    
        TwoDArray a = new TwoDArray(array);
        int result = a.whichRowHasMaxOnes_2();
        System.out.println("result = " + result);
    }
}
