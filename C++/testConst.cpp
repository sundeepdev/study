using namespace std;

#include <iostream>
#include <string>

/*
Test const keyword in C++
*/

class People
{
    private:
        string name;
        char sex;
        float height;

    public:
        People(const string name, const char sex, const float height)
        {
            this->name = name;
            this->sex = sex;
            this->height = height;
        }

        virtual ~People()
        {
            return;
        }

        string getName() const
        {
            //this->name = "Heidi";//This doesn't allow because of the const keyword. So the declaration of the function becomes string getName(const People * const this)
            return this->name;
        }

        char getSex() const
        {
            return this->sex;
        }

        float getHeight() const
        {
            return this->height;
        }

        void setName(const string name) {
            this->name = name;
        }

        void setSex(const char sex) {
            this->sex = sex;
        }

        void setHeight(const float height) {
            this->height = height;
        }
};

int main()
{
    /*
        demo1: the const usage in reference
    */
    int i = 10;
    int j = 20;
    int const & refToConst = i; //refToConst becomes readonly
    //int & const refToConst = i; //error as const becomes redundant because references can never be made to refer to another object.

    /*
        demo2: the const usage in function c++ syntax
    */

    People p = People("Alex", 'm', 165);
    cout<<p.getName()<<endl;
    cout<<p.getSex()<<endl;
    cout<<p.getHeight()<<endl;
    

    return 0;
}
