#include <stdlib.h>
#include <stdio.h>

int main()
{
    int* const p; //the keyword "const" is sitting after "*". So p cannot be changed(p becomes read only) but the content that p pointing to can.

    //const int* p; //the keyword "const" is sitting before "*". So the the content that p is pointing to cannot be changed. But p can be changed
    *p = 10;

    //int* q = malloc(sizeof(int));
    //*q = 10;
    //p = q;
    //free((void *)p);

    p = NULL;

    return 0;
}
