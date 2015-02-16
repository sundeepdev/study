/*
For a given matrix, find the maximum product of k elements. The elements 
can be formed from continuous 4 elements horizontally, vertically or 
diagonally. Eg: For k= 4, the maximum product is (6*4*7*9) from the 
last column, 

1  2  0 -1  4 
3  1  2  4  6 
0  2  3  1  4
1  3  2  0  7 
2  1  3  2  9

*/

import java.util.*;
import java.lang.*;

class MaxKElemsForAMatrix {
    private int[][] array;
    
    public MaxKElemsForAMatrix() {
        array = null;
    }

    public MaxKElemsForAMatrix(int[][] array) {
        this.array = array;
    }

    public int getMaxKElemsProduct(int k) {
        
        if (array == null || k <= 0) {
            return Integer.MIN_VALUE;
        }

        int row = array.length;
        int col = array[0].length;

        int max = Integer.MIN_VALUE;

        //scan the array horizontally
        int x = 0;
        int y = 0;
        int x2 = 0;
        int y2 = 0;

        while( x < row ) {
            while(y + k - 1 < col) {
                int sum = 1;
                for(int j = 0; j < k; j++) {
                    sum = sum * array[x][y+j];
                }
                if (sum > max) {
                    max = sum;
                }
                y++;
            }

            x++;
            y = 0;
        }

        //scan the array vertically
        x = 0;
        y = 0;

        while(y < col) {
            while( x + k -1 < row) {
                int sum = 1;
                for (int i = 0; i < k; i++) {
                    sum = sum * array[x+i][y];
                }
                if (sum > max) {
                    max = sum;
                }
                x++;
            }

            y++;
            x = 0;
        }

        //scan the array diagonally(in back-slash order)
        x = 0;
        y2 = 0;
        y = y2;
    
        while(y < col) {
            while((x + k - 1) < row && (y+k-1) < col) {
                int sum = 1;
                for(int i = 0; i < k; i++) {
                    sum = sum * array[x+i][y+i];
                }
                if (sum > max) {
                    max = sum;
                }
                x++;
                y++;
            }
            x = 0;
            y2++;
            y = y2;
        }

        x2 = 0;
        x = x2;
        y = 0;
        while( x < row) {
           while((x + k - 1) < row && (y+k-1) < col) {
               int sum = 1;
               for(int i = 0; i < k; i++) {
                   sum = sum * array[x+i][y+i];
               }
               if (sum > max) {
                   max = sum;
               }
               x++;
               y++;
           }
           y = 0;
           x2++;
           x = x2;
       }

        //scan the array diagonally(in slash order)
        x = 0;
        y2 = col-1;
        y = y2;
        
        while(y >= 0) {
            while((x + k - 1) < row && (y-k+1) >= 0) {
                int sum = 1;
                for (int i = 0; i < k; i++) {
                    sum = sum * array[x+i][y-i];
                }
                if (sum > max) {
                    max = sum;
                }
                x++;
                y--;
            }
            x = 0;
            y2--;
            y = y2;
        }

        x2 = 0;
        x = x2;
        y = col-1; 
        
        while(x < col) {
            while((x + k - 1) < row && (y-k+1) >= 0) {
                int sum = 1;
                for (int i = 0; i < k; i++) {
                    sum = sum * array[x+i][y-i];
                }
                if (sum > max) {
                    max = sum;
                }
                y = 0;
                x++;
            }
            y = 0;
            x2++;
            x = x2;
        }

        return max;
    }

    static public void main(String[] args) {
        int [][] array = {
            {1, 2, 0, -1, 4},
            {3, 1, 2, 4, 6},
            {0, 2, 3, 1, 4},
            {1, 3, 2, 0, 7},
            {2, 1, 3, 2, 9}
        };

        MaxKElemsForAMatrix matrix = new MaxKElemsForAMatrix(array);
        int max = matrix.getMaxKElemsProduct(4);
        System.out.println(max);
    }
}
