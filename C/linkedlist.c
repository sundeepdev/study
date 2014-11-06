#include <stdio.h>
#include <stdlib.h>

struct Node
{
    int value;
    struct Node* next;
};

typedef struct Node Node;

void remove_match(Node **list, int value);
void printList(Node *list);
void destroy(Node **list);

int main()
{
    //Create a linked list
    Node *n1 = (Node*)malloc(sizeof(Node));
    n1->value = 3;
    

    Node *n2 = (Node*)malloc(sizeof(Node));
    n2->value = 3;

    Node *n3 = (Node*)malloc(sizeof(Node));
    n3->value = 3;
    
    Node *n4 = (Node*)malloc(sizeof(Node));
    n4->value = 4;
    
    Node *n5 = (Node*)malloc(sizeof(Node));
    n5->value = 7;

    Node *n6 = (Node*)malloc(sizeof(Node));
    n6->value = 3;

    n1->next = n2;
    n2->next = n3;
    n3->next = n4;
    n4->next = n5;
    n5->next = n6;
    n6->next = NULL;

    remove_match(&n1, 3);

    printList(n1);

    destroy(&n1);

    return 0;
}

void printList(Node *list)
{
    Node *cur = list;

    while(cur != NULL)
    {
        printf("%d\n", cur->value);
        cur = cur->next;
    }
}

void destroy(Node **list)
{
    Node *cur = *list;
    while(cur != NULL)
    {
        Node *temp= cur;
        cur = cur->next;
        free(temp);
        temp = NULL;
    }
}

void remove_match(Node **list, int value)
{
    Node *cur = *list;
    Node *parent = NULL;

    while(cur != NULL)
    {
        if (cur->value == value)
        {
            if (parent != NULL)
            {
                parent->next = cur->next;
                free(cur);
                cur = parent->next;
            }
            else
            {
                Node * temp = cur;
                cur = cur->next;
                free(temp);
                temp = NULL;
                *list = cur;
            }
        }
        else
        {
            parent = cur;
            cur = cur->next;
        }
    }
}
