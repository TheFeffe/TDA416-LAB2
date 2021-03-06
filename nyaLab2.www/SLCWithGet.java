/**
 * A Sorted COllection With Get (SLCWithGet)
 * sorted in ascending order (lowest first)
 *
 * @param <E> an element that is naturally comparable
 */
public class SLCWithGet<E
        extends Comparable<? super E>>
        extends LinkedCollection<E>
        implements CollectionWithGet<E> {

    /**
     * Adds element e to the collection, so that the smallest element
     * is the first element and the greatest is the last element
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Entry newEntry = new Entry(e, null);

        // if collection is empty, add as new head
        if (head == null) {
            head = newEntry;
            return true;
        }

        Entry curr = head;
        Entry pre = null;
        while (true) {
            if (e.compareTo(curr.element) < 0) { // if e < current element, attach to previous, if any
                if (pre == null) {   // if current element is head
                    head = newEntry;
                    newEntry.next = curr;
                } else {              // if current element is not head
                    newEntry.next = curr;
                    pre.next = newEntry;
                }
                return true;
            }
            if (curr.next == null) { // if current element is last in list, i.e. e > all elements
                curr.next = newEntry;
                return true;
            }
            pre = curr;
            curr = curr.next;
        }
    }

    /**
     * Returns an element elem that satisfies
     * e.compareTo(elem) == 0 or null if no such element
     *
     * @param e The dummy element to compare to.
     * @return
     */
    @Override
    public E get(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        // If the collection is empty, return null
        if (head == null) {
            return null;
        }
        Entry entry = head;
        // iterate until e is according to natural order identical to entry.element
        while (e.compareTo(entry.element) != 0) {
            if (entry.next != null)
                entry = entry.next;
            else
                return null;
        }
        return entry.element;
    }
}
