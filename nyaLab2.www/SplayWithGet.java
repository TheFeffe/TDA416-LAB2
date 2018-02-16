public class SplayWithGet<E extends Comparable<? super E>> extends BinarySearchTree<E> implements CollectionWithGet<E> {

    private boolean exactMatch = false;

    /**
     * Returns an Entry containing element e that satisfies
     * elem.compareTo(e) == 0 or null if no such
     *
     * @param elem
     * @param t    the "root" entry for the search, usually root
     * @return
     */
    @Override
    protected Entry find(E elem, Entry t) {
        if (root == null) return null;
        if (elem == null || t == null) throw new NullPointerException();
        exactMatch = true;
        Entry found = findInternal(elem, t);
        splay(found);
        return exactMatch ? root : null;
    }

    /**
     * Overloaded default find, searching from root
     * @param elem
     * @return
     */
    protected Entry find(E elem) {
        return find(elem, root);
    }

    /**
     * Returns an Entry containing element e that satisfies
     * elem.compareTo(e) or, if there is no such element, what
     * its parent would be
     *
     * @param elem the element to find (such that elem.compareTo returns 0)
     * @param t    an Entry to search from
     * @return the Entry containing elem, or its would-be parent if elem is missing
     */
    private Entry findInternal(E elem, Entry t) {
        int diff = elem.compareTo(t.element);

        if (diff > 0) {
            if (t.right == null) {
                exactMatch = false;
                return t;
            } else
                return findInternal(elem, t.right);
        } else if (diff < 0) {
            if (t.left == null) {
                exactMatch = false;
                return t;
            } else
                return findInternal(elem, t.left);
        } else
            return t;
    }


    /**
     * Returns an element that satisfies e.compareTo(element) == 0
     * or null if no such element.
     * @param e The dummy element to compare to.
     * @return
     */
    @Override
    public E get(E e) {
        Entry entry = find(e, root);  // this will splay the tree
        return entry == null ? null : entry.element;
    }

    /**
     * "splays" Entry t so that it is the root of the tree
     * by performing rotations
     *
     * @param t
     */
    private void splay(Entry t) {
        //we have arrived at our destination, mind the gap
        if (t == root) {
            return;
        }

        //if t is on the "first level"
        if (t.parent == root) {
            if (t.parent.right == t) {
                rightToSubRoot(t.parent);
                splay(t.parent);
                return;
            }

            if (t.parent.left == t) {
                leftToSubRoot(t.parent);
                splay(t.parent);
                return;
            }
        } else {
            boolean parentIsRightChild = t.parent.parent.right == t.parent;
            boolean tIsRightChild = t.parent.right == t;

            //t is 2 "levels" or more down
            if (parentIsRightChild == tIsRightChild) {   // is rightToSubRoot-rightToSubRoot or leftToSubRoot-leftToSubRoot
                if (tIsRightChild) {    // is rightToSubRoot-rightToSubRoot
                    rightRightToSubRoot(t.parent.parent);
                    splay(t.parent.parent);
                    return;
                } else {                // is leftToSubRoot-leftToSubRoot
                    leftLeftToSubRoot(t.parent.parent);
                    splay(t.parent.parent);
                    return;
                }
            } else {  // is rightToSubRoot-leftToSubRoot or leftToSubRoot-rightToSubRoot
                if (tIsRightChild) {    // t is leftToSubRoot-rightToSubRoot
                    leftRightToSubRoot(t.parent.parent);
                    splay(t.parent);
                    return;
                } else {                // t is rightToSubRoot-leftToSubRoot
                    rightLeftToSubRoot(t.parent.parent);
                    splay(t.parent);
                    return;
                }
            }

        }
        return;


//        while (t.parent != null) {
//            if (t.parent.parent == null) {  // t is at depth 1 -> simple leftToSubRoot/rightToSubRoot
//                if (t.parent.rightToSubRoot == t) {  // if t is rightToSubRoot child to root
//                    rightToSubRoot(t.parent);
//                    t = t.parent;
//                } else {                    // else t is leftToSubRoot child to root
//                    leftToSubRoot(t.parent);
//                    t = t.parent;
//                }
//            } else {
//                boolean parentIsRightChild = t.parent.parent.rightToSubRoot == t.parent;
//                boolean tIsRightChild = t.parent.rightToSubRoot == t;
//
//                // t is 2 levels down from a sub-root, some combination of leftToSubRoot/rightToSubRoot-child
//                if (parentIsRightChild == tIsRightChild) {   // is rightToSubRoot-rightToSubRoot or leftToSubRoot-leftToSubRoot
//                    if (tIsRightChild) {    // is rightToSubRoot-rightToSubRoot
//                        rightRightToSubRoot(t.parent.parent);
//                        t = t.parent.parent;
//                    } else {                // is leftToSubRoot-leftToSubRoot
//                        leftLeftToSubRoot(t.parent.parent);
//                        t = t.parent.parent;
//                    }
//                } else {  // is rightToSubRoot-leftToSubRoot or leftToSubRoot-rightToSubRoot
//                    if (tIsRightChild) {    // t is leftToSubRoot-rightToSubRoot
//                        leftRightToSubRoot(t.parent.parent);
//                        t = t.parent.parent;
//                    } else {                // t is rightToSubRoot-leftToSubRoot
//                        rightLeftToSubRoot(t.parent.parent);
//                        t = t.parent.parent;
//                    }
//                }
//            }
//        }
    }

    // pulls leftToSubRoot child to x
    // is ok
    void leftToSubRoot(Entry x) {
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

    // pulls rightToSubRoot child to x
    // is ok
    void rightToSubRoot(Entry x) {

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

    // pulls leftToSubRoot-rightToSubRoot child to x
    // is ok
    void leftRightToSubRoot(Entry x) {
//        Entry y = x.leftToSubRoot,
//                z = x.leftToSubRoot.rightToSubRoot;
//        E e = x.element;
//        x.element = z.element;
//        z.element = e;
//        y.rightToSubRoot = z.leftToSubRoot;
//        if (y.rightToSubRoot != null) y.rightToSubRoot.parent = y;
//        z.leftToSubRoot = z.rightToSubRoot;
//        z.rightToSubRoot = x.rightToSubRoot;
//        if (z.rightToSubRoot != null) z.rightToSubRoot.parent = z;
//        x.rightToSubRoot = z;
//        if (x.rightToSubRoot != null) x.rightToSubRoot.parent = x;
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

    // pulls rightToSubRoot-leftToSubRoot child to x
    // is ok
    void rightLeftToSubRoot(Entry x) {
//        Entry y = x.rightToSubRoot,
//                z = x.rightToSubRoot.leftToSubRoot;
//        E e = x.element;
//        x.element = z.element;
//        z.element = e;
//        y.leftToSubRoot = z.rightToSubRoot;
//        if (y.leftToSubRoot != null) y.leftToSubRoot.parent = y;
//        z.rightToSubRoot = z.leftToSubRoot;
//        z.leftToSubRoot = x.leftToSubRoot;
//        if (z.leftToSubRoot != null) z.leftToSubRoot.parent = z;
//        x.leftToSubRoot = z;
//        if (x.leftToSubRoot != null) x.leftToSubRoot.parent = x;
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

    // pulls leftToSubRoot-leftToSubRoot child to x
    // is ok
    void leftLeftToSubRoot(Entry x) {
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
        x.left = z.left;
        if (x.left != null) x.left.parent = x;
        z.left = y.right;
        if (z.left != null) z.left.parent = z;
        y.right = z;
        x.right = y;

    }

    // pulls rightToSubRoot-rightToSubRoot child to x
    // is ok
    void rightRightToSubRoot(Entry x) {
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
        x.right = z.right;
        if (x.right != null) x.right.parent = x;
        z.right = y.left;
        if (z.right != null) z.right.parent = z;
        y.left = z;
        x.left = y;


    }

}
