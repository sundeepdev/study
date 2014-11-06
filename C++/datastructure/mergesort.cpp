using namespace std;
#include <iostream>
#include <string>

class MergeSort
{
    public:
        static void merge(int *A, int p, int q, int r)
        {
            int n1 = q - p + 1; //n1: the number of the array L(left)
            int n2 = r - q; //n2: the number of the array R(right)

            int i, j, k;

            int left[n1];
            int right[n2];

            //Copy the elements to the left array
            for(i = 0; i < n1; i++)
            {
                left[i] = A[p+i]; //the index is from 0 to n1-1. Both range ends are closed.
            }

            //Copy the elements to the right array
            for(j = 0; j < n2; j++)
            {
                right[j] = A[q+1+j]; //the index is from 0 to n2-1. Both range ends are closed.
            }
    
            i = j = 0;

            //k should be ranges from p to r. Both range ends are closed.
            for (k = p; k <= r; k++) 
            { 
                if (left[i] <= right[j])
                {
                    A[k] = left[i];
                    i++;
                    if (i == n1) //reach the end of the left array
                    {
                        k++;
                        //copy the rest from the right array
                        while(j < n2)
                        {
                            A[k] = right[j];
                            k++;
                            j++;
                        }
                    }
                }
                else
                {
                    A[k] = right[j];
                    j++;
                    if (j == n2) //reach the end of the right array
                    {
                        k++;
                        //copy the rest from the left array
                        while(i < n1)
                        {
                            A[k] = left[i];
                            k++;
                            i++;
                        }
                    }
                }
            }
        }

        public:
            static void mergesort(int *A, int p, int r)
            {
                if (p < r)
                {
                    int q = (p + r) / 2; //! why (p + r)/2

                    mergesort(A, p, q);

                    mergesort(A, q+1, r);//be careful, here the starting index is q+1 NOT q. 
                                         //If merrgesort starts from q here, Infinite loop can happen. E.g. p = 14, r = 15 (q = (p+r)/2 = 14).
                    merge(A, p, q, r);

                }
            }

         
};

int main() 
{
    int A[10] = {3, 23, -10, 9, 38, -17, 2, 5, 4, 22};
    MergeSort::mergesort(A, 0, 9);

    for(int i = 0; i < 10; i++)
    {
        cout<<A[i]<<endl;
    }

    /*
    int B[6] = {3, 4, 6, 2, 3, 10};
    MergeSort::merge(B, 0, 2, 5);

    for(int i = 0; i < 6; i++)
    {
        cout<<B[i]<<endl;
    }
    */

    return 0;
}
