using namespace std;
#include <iostream>
#include <string>

class HeapTree
{
    private:
        int *A;      //the heap array
        int n;          //the size of the array
        int heapSize;   //the heap size. Noted that 0<=heapSize<=size

    /* The constructor */
    public:
        HeapTree()
        {
            n = heapSize = 0;
        }

        HeapTree(const int * const array, int size)
        {
            A = new int[size];
            copy(array, array + size, A);
            n = size;
        }

        virtual ~HeapTree()
        {
            if (A != NULL)
            {
                delete A;
                A = NULL;
            }
        }

        //return the index i's left child
        int left(int i)
        {
            if (i <= (float(heapSize-2)/2))
            {
                return i * 2 + 1;
            }
            else
            {
                return -1;
            }
        }

        //return the index i's right child
        int right(int i)
        {
            if (i <= (float(heapSize-3)/2))
            {
                return i * 2 + 2;
            }
            else
            {
                return -1;
            }
        }

        //return the index i's parent
        int parent(int i)
        {
            if (i > 0)
            {
                return (i-1)/2;
            }
            else
            {
                return -1;
            }
        }

        void maxheapify(int i)
        {
            int l = left(i);
            int r = right(i);

            int largest = i;

            if (l != -1)
            {
                if ( A[l] > A[largest] ) 
                {
                    largest = l;
                }
            }
            
            if (r != -1)
            {
                if ( A[r] > A[largest] )
                {
                    largest = r;
                }
            }

            if (largest != i)
            {
                int temp = A[i];
                A[i] = A[largest];
                A[largest] = temp;

                maxheapify(largest);
            }
        }

        void buildmaxheap()
        {
            heapSize = n;

            for (int i = (heapSize/2-1); i >= 0; i--)
            {
                maxheapify(i);
            }
        }

        void heapSort()
        {
            buildmaxheap();
            //The reason why n-1 is because it is the last element in the array and we need to swap(A[i], A[0])
            for (int i = n-1; i > 0; i--)
            {
                int temp = A[0]; //A[0] is the largest element;
                A[0] = A[i];
                A[i] = temp;
                
                heapSize--;
                maxheapify(0);
            }
        }

        void print()
        {
            for (int i = 0; i < n; i++)
            {
                cout<<A[i]<<endl;
            }
        }
};

int main()
{
    int A[10] = {-10, 3, 5, -19, 36, 90, -12, 7, 22, -10};
    HeapTree ht = HeapTree(A, 10);

    //int B[5] = {-10, 3, 5, -19, 36};
    //HeapTree ht = HeapTree(B, 5);
    ht.heapSort();
    ht.print();
}
