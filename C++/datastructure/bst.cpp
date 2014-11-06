using namespace std;

#include <iostream>
#include <string>

class Node
{
    private:
        int value;
        Node* parent;
        Node* left;
        Node* right;

    public:
        Node(int value) {
            this->value = value;
        }

        virtual ~Node()
        {
            return;
        }

        void setValue(int value) {
            this->value = value;
        }

        void setParent(Node* parent)
        {
            this->parent = parent;
        }

        void setLeft(Node* child) {
            this->left = child;
        }

        void setRight(Node* child) {
            this->right= child;
        }

        Node* getLeft() const {
            return this->left;
        }
        
        Node* getRight() const {
            return this->right;
        }

        Node* getParent() const {
            return this->parent;
        }

        int getValue() const {
            return this->value;
        }

};

class Bst
{
    private:
        Node* root;
    public:
        Bst(Node* root)
        {
            this->root = root;
            root->setLeft(NULL);
            root->setRight(NULL);
        }

        Node* getRoot() const
        {
            return this->root;
        }
    
        static void remove(Node *node)
        {

            if (node != NULL)
            {
                if (node->getLeft() != NULL)
                {
                    remove(node->getLeft());
                }

                if (node->getRight() != NULL)
                {
                    remove(node->getRight());
                }

                cout<<"Remove the node: "<<node->getValue()<<endl;
                delete node;
                node = NULL;
            }
        }

        virtual ~Bst() {
            cout<<"Start removing..."<<endl;
            remove(root);
        }

        void insert(Node* node)
        {
            Node* cur = root;
            if (cur == NULL)
            {
                root = node;
                root->setLeft(NULL);
                root->setRight(NULL);
                return;
            }

            while(cur != NULL) {
                if (node->getValue() < cur->getValue()) {
                    if ( cur->getLeft() != NULL) {
                        cur = cur->getLeft();
                    } else {
                        cur->setLeft(node);
                        node->setParent(cur);
                        node->setLeft(NULL);
                        node->setRight(NULL);
    
                        return;
                    }
                } else {
                    if (cur->getRight() != NULL) {
                        cur = cur->getRight();
                    } else {
                        cur->setRight(node);
                        node->setParent(cur);
                        node->setLeft(NULL);
                        node->setRight(NULL);
    
                        return;
                    }
                }
            }
        }

        static void inOrder(const Node* const cur)
        {
            if (cur == NULL)
            {
                return;
            }

            if (cur->getLeft()!= NULL)
            {
                inOrder(cur->getLeft());
            }

            if (cur != NULL)
            {
                cout<<cur->getValue()<<endl;;
            }

            if (cur->getRight()!= NULL)
            {
                inOrder(cur->getRight());
            }

            return;
        }

        static void preOrder(const Node* const cur)
        {
            if (cur == NULL)
            {
                return;
            }

            if (cur != NULL)
            {
                cout<<cur->getValue()<<endl;;
            }

            if (cur->getLeft()!= NULL)
            {
                preOrder(cur->getLeft());
            }

            if (cur->getRight()!= NULL)
            {
                preOrder(cur->getRight());
            }
        }

        static void postOrder(const Node* const cur)
        {
            if (cur == NULL)
            {
                return;
            }

            if (cur->getLeft()!= NULL)
            {
                postOrder(cur->getLeft());
            }

            if (cur->getRight()!= NULL)
            {
                postOrder(cur->getRight());
            }

            if (cur != NULL)
            {
                cout<<cur->getValue()<<endl;;
            }
        }

        const Node* largest(const Node* const node)
        {
            Node const *cur = node;
            Node const *rst = NULL;

            while(cur != NULL) 
            {
                rst = cur;
                cur = rst->getRight();   
            }

            return rst;
        }

        const Node* smallest(const Node* const node)
        {
            Node const *cur = node;
            Node const *rst = NULL;

            while(cur != NULL)
            {
                rst = cur;
                cur = rst->getLeft();
            }

            return rst;
        }

        /** Return the largest key smaller than x */
        // We break the code into two cases:
        // 1) If the left subtree of node x is nonempty, then the predecessor of x is just the rightmost node of x's left subtree by calling maximum(x.left());
        // 2) If the left subtree of node x empty and x has a predecessor y, to find y, we simply go up the tree from x until we encounter a node that 
        //    is the right child of its parent.
        const Node* predecessor(const Node* const node)
        {
            if (node == NULL)
            {
                return NULL;
            }

            if (node->getLeft() != NULL)
            {
                return largest(node->getLeft());
            }
            else
            {
                Node const * rst = NULL;
                Node const * cur = node;
                Node* parent = cur->getParent();    
            
                while (parent != NULL)
                {
                    if (parent->getRight() == cur)
                    {
                       rst = parent;
                       break;
                    }
                    
                    cur = cur->getParent();
                    parent = cur->getParent();
                }

                return rst;
            }
        }

        /** Return the smallest key larger than x */
        // We break the code into two cases: 
        // 1) If the right subtree of node x is nonempty, then the sucessor of x is just the leftmost node in x's right subtree by calling minimum(x.right());
        // 2) If the right subtree of node x is empty and x has a sucessor y, then y is the lowest ancestor of x(??) whose left child is also an ancestor of x(remember
        //    that x is also the ancestor of itself). To find y, we simply go up the tree from x until we encounter a node that is the left child of its parent.

        const Node* successor(const Node* const node)
        {
            if (node == NULL)
            {
                return NULL;
            }

            if (node->getRight() != NULL)
            {
                return smallest(node->getRight());
            }
            else
            {
                Node const * rst = NULL;
                Node const * cur = node;
                Node* parent = cur->getParent();    
            
                while (parent != NULL)
                {
                    if (parent->getLeft() == cur)
                    {
                       rst = parent;
                       break;
                    }
                    
                    cur = cur->getParent();
                    parent = cur->getParent();
                }

                return rst;
            }
        }

};

int main()
{
    //Create some Nodes
    Node* n1 = new Node(1);
    Node* n2 = new Node(2);
    Node* n3 = new Node(3);
    Node* n4 = new Node(4);
    Node* n5 = new Node(5);

    Bst tree = Bst(n1);
    
    tree.insert(n2);
    tree.insert(n4);
    tree.insert(n3);
    tree.insert(n5);
    //Bst::preOrder(tree.getRoot());
    //Bst::inOrder(tree.getRoot());
    //Bst::postOrder(tree.getRoot());
    //cout<<tree.largest()->getValue()<<endl;

    //const Node* rst = tree.successor(n1);
    const Node* rst = tree.predecessor(n2);
    if (rst != NULL)
    {
        //cout<<rst->getValue()<<endl;    
    }

    rst = tree.predecessor(tree.largest(tree.getRoot()));
    //rst = tree.successor(tree.smallest(tree.getRoot()));

    if (rst != NULL)
    {
        cout<<rst->getValue()<<endl;    
    }
    

    //cin.ignore();

    return 0;
}
