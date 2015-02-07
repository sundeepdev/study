/*

You are given a 2D Array that contains only 0s and 1s in sorted order. i.e.

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

    Time Complexity: n
    Space Complexity: nothing

*/
