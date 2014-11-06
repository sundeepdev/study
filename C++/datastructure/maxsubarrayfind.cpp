using namespace std;
#include <iostream>

class maxsubarrayfind
{
    private: 

        class found
        {
            private:
                int low;
                int high;
                int sum;
            public:
                int getHigh() const
                {
                    return this->high;
                }

                int getLow() const
                {
                    return this->low;
                }

                int getSum() const
                {
                    return this->sum;
                }

                void setLow(int low)
                {
                    this->low = low;
                }

                void setHigh(int high)
                {
                    this->high = high;
                }

                void setSum(int sum)
                {
                    this->sum = sum;
                }
        };

    public:

        static found find_max_subarray_cross(int const * const A, int low, int mid, int high)
        {
            found result;
            int sum_left = 0;
            int sum_right = 0;
            int sum = 0;
            int i, j, left, right;

            //calculate max(left)
            sum_left = A[mid];
            left = mid;
            for (i = mid; i >= low; i--)
            {
                sum = sum + A[i];
                if (sum > sum_left)
                {
                    sum_left = sum;
                    left = i;
                }
            }

            //calculate max(right)
            sum = 0;
            sum_right = A[mid+1];
            right = mid+1;

            for (j = mid+1; j <= high; j++)
            {
                sum = sum + A[j];
                if (sum > sum_right)
                {
                    sum_right = sum;
                    right = j;
                }
            }

            //update the return info
            result.setLow(left);
            result.setHigh(right);
            result.setSum(sum_left+sum_right);

            return result;
        }

        static found find_max_subarray(int const * const A, int low, int high)
        {
            found result;
            if (low == high)
            {
                result.setLow(low);
                result.setHigh(high);
                result.setSum(A[low]);
            
                return result;
            }
            else
            {
                int mid = (low + high) / 2;
                found result_left;
                found result_right;
                found result_cross;

                result_left = find_max_subarray(A, low, mid); //takes T(n/2)
                result_right = find_max_subarray(A, mid+1, high); //takes n
                result_cross = find_max_subarray_cross(A, low, mid, high); //takes T(n/2)

                result = result_left;
                if (result_right.getSum() > result.getSum())
                {
                    result = result_right;
                }

                if (result_cross.getSum() > result.getSum())
                {
                    result = result_cross;
                }
                
                return result;
            }
        }

        static void printMaxSubArray(int const * const A, int low, int high)
        {
            found result = find_max_subarray(A, low, high);

            cout<<"low = "<<result.getLow()<<endl;
            cout<<"high = "<<result.getHigh()<<endl;
            cout<<"max sum = "<<result.getSum()<<endl;
        }
};

int main()
{
    int A[10] = {1, -9, -2, 6, 10, -3, 17, 4, 8, -10};
    maxsubarrayfind::printMaxSubArray(A, 0, 9);
    

    return 0;
}
