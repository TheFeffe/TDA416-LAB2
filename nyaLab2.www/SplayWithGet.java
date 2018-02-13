public class SplayWithGet<E extends Comparable<? super E>> extends BinarySearchTree<E> implements CollectionWithGet<E> {


    public Entry find(E elem) {
        return find(elem, root);
    }

    @Override
    protected Entry find(E elem, Entry t) {
        if (elem == null) {
            throw new NullPointerException();
        }

        Entry current = root;

        while (current.element != elem) {
            if (current.element.compareTo(elem) < 0) {
                if (current.left == null) {
                    splay(current);
                    return null;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    splay(current);
                    return null;
                }
                current = current.right;
            }
        }
        splay(current);
        return current;
    }

    private void splay(Entry t) {
        while (t.parent != null) {
            if (t.parent.parent == null) {  // t is at depth 1 -> simple zig/zag
                if (t.parent.right == t) {  // if t is right child to root
                    zag(t.parent);
                } else {                    // else t is left child to root
                    zig(t.parent);
                }
            } else {
                boolean parentIsRightChild = t.parent.parent.right == t.parent;
                boolean tIsRightChild = t.parent.right == t;

                // t is 2 levels down from a sub-root, some combination of left/right-child
                if (parentIsRightChild == tIsRightChild) {   // is right-right or left-left
                    if (tIsRightChild) {    // is right-right
                        zagzag(t.parent.parent);
                    } else {                // is left-left
                        zigzig(t.parent.parent);
                    }
                } else {  // is right-left or left-right
                    if (tIsRightChild) {    // t is left-right
                        zagzig(t);
                    } else {                // t is right-left
                        zigzag(t);
                    }
                }
            }
        }
    }


    // pulls left child to x
    // is ok
    private void zig(Entry x) {
        Entry y = x.left;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left = y.left;
        if (x.left != null)
            x.left.parent = x;
        y.left = y.right;
        y.right = x.right;
        if (y.right != null)
            y.right.parent = y;
        x.right = y;
    }

    // pulls right child to x
    // is ok
    private void zag(Entry x) {
        Entry y = x.right;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.right = y.right;
        if (x.right != null)
            x.right.parent = x;
        y.right = y.left;
        y.left = x.left;
        if (y.left != null)
            y.left.parent = y;
        x.left = y;
    }

    // pulls left-right child to x
    // is ok
    private void zagzig(Entry x) {
        Entry y = x.left,
                z = x.left.right;
        E e = x.element;
        x.element = z.element;
        z.element = e;
        y.right = z.left;
        if (y.right != null)
            y.right.parent = y;
        z.left = z.right;
        z.right = x.right;
        if (z.right != null)
            z.right.parent = z;
        x.right = z;
        z.parent = x;
    }

    // pulls right-left child to x
    // is ok
    private void zigzag(Entry x) {
        Entry y = x.right,
                z = x.right.left;
        E e = x.element;
        x.element = z.element;
        z.element = e;
        y.left = z.right;
        if (y.left != null)
            y.left.parent = y;
        z.right = z.left;
        z.left = x.left;
        if (z.left != null)
            z.left.parent = z;
        x.left = z;
        z.parent = x;
    }

    // pulls left-left child to x
    // is ok
    private void zigzig(Entry x) {
        Entry y = x.left,
                z = x.left.left;
        E e = x.element;
        x.element = z.element;
        z.element = e;
        y.left = z.right;
        if (y.left != null)
            y.left.parent = y;
        z.right = x.right;
        if (z.right != null)
            z.right.parent = z;
        z.left = y.right;
        y.right = z;
        x.right = y;
    }

    // pulls right-right child to x
    // is ok
    private void zagzag(Entry x) {
        Entry y = x.right,
                z = x.right.right;
        E e = x.element;
        x.element = z.element;
        z.element = e;
        y.right = z.left;
        if (y.right != null)
            y.right.parent = y;
        z.left = x.left;
        if (z.left != null)
            z.left.parent = z;
        z.right = y.left;
        x.right = z.right;
        y.left = z;
        x.left = y;


    }


    @Override
    public E get(E e) {
        Entry entry = find(e);  // this will splay the tree
        return entry != null ? entry.element : null;    // return the element is found, otherwise null
    }
}
