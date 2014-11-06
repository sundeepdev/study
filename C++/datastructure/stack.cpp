#include <string>
#include <iostream>
using namespace std;

class Stack {
    private:
        int top;
        int capacity;
        int *storage;
    public:
        Stack(int capacity) {
            if (capacity <= 0) {
                throw string("Stack's capacity must be positive");
            }

            storage = new int[capacity];
            this->capacity = capacity;
            top = -1;

            cout<<"The constructor is called"<<endl;
        }

        virtual ~Stack() {
            if (storage != NULL) {
                delete storage;
                storage = NULL;
            }

            cout<<"The destructor is called"<<endl;
        }

        void push(int value) {
            if (top+1 == capacity) {
                int *new_storage = new int[capacity * 2];
                std::copy(storage, storage + capacity, new_storage);

                delete storage;

                storage = new_storage;
                capacity = capacity * 2;
            }

            top++;
            storage[top] = value;
        }

        int pop() {
            if (top != -1) {
                return storage[top--];
            } else {
                throw string("the stack is empty, nothing to pop");
            }
        }

        bool isEmpty() {
            return (top == -1);
        }

        int getSize() {
            return top+1;
        }

        int getCapacity() {
            return capacity;
        }
};

int main() {
    Stack stack = Stack(5);

    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    stack.push(6);
    stack.push(7);
    stack.push(8);
    stack.push(9);
    stack.push(10);
    stack.push(11);

    stack.pop();
    stack.pop();
    stack.pop();
    stack.pop();

    cout<<"size = "<<stack.getSize()<<endl;
    cout<<"capacity = "<<stack.getCapacity()<<endl;

    return 0;
}
