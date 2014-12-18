#ifndef __QueueClassH__
#define __QueueClassH__

#include <assert.h> //For error-checking purposes

//----------------------
//Main structure of Queue Class:
//----------------------

template <class Elem>
class Queue
{
    public:
    Queue(int MaxSize=500);
    Queue(const Queue<Elem> &OtherQueue);
    virtual ~Queue(void);

    void Enqueue(const Elem &Item); //Adds Item to Queue end
    Elem Dequeue(void); //Returns Item from Queue
    inline int ElemNum(void;    //Returns Number of Elements

    protected:
    Elem *Data //The actual Data array
    const int MAX_NUM;  //The actual spaces will be one more than this
    int Beginning;  //Numbered location of the start 
    int End;        //Numbered location of the end
    
    //Instead of calculating the number of elements, using this variable
    //is much more convenient
    int ElemCount;
};

//----------------------
// Implementation of Queue Class:
//----------------------

// Queue Constructor function
template <class Elem>
Queue<Elem>::Queue(int MaxSize) : 
    MAX_NUM( MaxSize ) //Initialize the Content
{
    // This extra space added will allow us to distinguish between
    // the Beginning and the End locations
    Data = new Elem[MAX_NUM+1];
    Beginning = 0;
    End = 0;
    ElemCount = 0;
}

template <class Elem>
Queue<Elem>::Queue(const Queue &OtherQueue) :
                MAX_NUM(OtherQueue.MAX_NUM) //Initialize the constant
{
    Beginning = OtherQueue.Beginning;
    End = OtherQueue.End;
    ElemCount = OtherQueue.ElemCount;
    
    for (int i = 0; i < MAX_NUM; i++)
    {
        Data[i] = OtherQueue.Data[i];
    }
}

template <class Elem>
Queue<Elem>::~Queue(void)
{
    delete Data;
    Data = NULL;
}

template <class Elem>
Queue<Elem>::Enqueue(const Elem &Item)
{
    assert( ElemCount < MAX_NUM );
    Data[End++] = Item;
    ++ElemCount;

    //Check for wrap-around
}

