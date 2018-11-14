package redblacktree;

public class RedBlackTree< T extends Comparable< T > >
{
    final boolean Red = true;
    final boolean Black = false;
    Node< T > root;

    public RedBlackTree()
    {
        root = null;
    }

    public void add( T item )
    {
        Node< T > node = new Node< T >( item );
        if( isEmpty() )
        {
            root = node;
            root.color = Black;
        }
        balanceTree();
    }

    private void balanceTree()
    {
        balanceHelper( root );
        if( root.color == Red )
        {
            root.color = Black;
        }
    }

    private void balanceHelper( Node< T > current )
    {
        if( current.uncleIsBlack() )
        {
            if( current.isLeftChild() )
            {
                rotateRight();
                rotateLeft();
            }
        }
        if( current.hasLeftChild() )
        {
            balanceHelper( current.leftChild );
        }
        if( current.hasRightChild() )
        {
            balanceHelper( current.rightChild );
        }
    }

    private void pushBlack( Node< T > current )
    {
        current.color = Red;
        if( current.hasLeftChild() )
        {
            current.leftChild.color = Black;
        }
        if( current.hasRightChild() )
        {
            current.rightChild.color = Black;
        }
    }

    private void rotateLeft()
    {

    }

    private void rotateRight()
    {

    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public void clear()
    {
        root = null;
        System.gc();
    }

    private class Node< T extends Comparable< T > >
    {
        T data;
        Node< T > parent;
        Node< T > leftChild;
        Node< T > rightChild;
        boolean color;

        Node()
        {
            data = null;
            parent = null;
            leftChild = null;
            rightChild = null;
            color = Black;
        }

        Node( T data )
        {
            this.data = data;
            parent = null;
            leftChild = null;
            rightChild = null;
            color = data != null;
        }

        boolean hasParent()
        {
            return parent != null;
        }

        boolean hasLeftChild()
        {
            return leftChild != null;
        }

        boolean hasRightChild()
        {
            return rightChild != null;
        }

        boolean uncleIsBlack()
        {
            Node< T > grandparent;
            if( hasParent() && parent.hasParent() )
            {
                grandparent = parent.parent;
                if( parent == grandparent.leftChild )
                {
                    return grandparent.rightChild == null
                           || grandparent.rightChild.color == Black;
                }
                else
                {
                    return grandparent.leftChild == null
                           || grandparent.leftChild.color == Black;
                }
            }
            return false;
        }

        boolean uncleIsRed()
        {
            Node< T > grandparent;
            if( hasParent() && parent.hasParent() )
            {
                grandparent = parent.parent;
                if( parent == grandparent.leftChild )
                {
                    return grandparent.rightChild != null
                           && grandparent.rightChild.color == Red;
                }
                else
                {
                    return grandparent.leftChild != null
                           && grandparent.leftChild.color == Red;
                }
            }
            return false;
        }

        boolean isLeftChild()
        {
            if( hasParent() )
            {
                return parent.leftChild == this;
            }
            return false;
        }

        boolean isRightChild()
        {
            if( hasParent() )
            {
                return parent.rightChild == this;
            }
            return false;
        }
    }
}
