#include <stdlib.h>
#include <stdio.h>

int get() {
    static i = 1;
    return i++;
}

/*
This question tests how pamameters are passed to a function. e.g.
in what order

the parameters are passed from right to left
So the answer should be 3, 2, 1

*/
int main(int argc, char *argv[])
{
    printf("%d %d %d\n", get(), get(), get());
    return 0;
}
