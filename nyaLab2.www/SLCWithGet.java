/**
 * A Sorted COllection With Get (SLCWithGet)
 * @param <E> an element that is naturally comparable
 */
public class SLCWithGet<E
        extends Comparable<? super E>>
        extends LinkedCollection<E>
        implements CollectionWithGet<E> {

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

        Entry entry = head;

        // iterate through collection until our element is smaller than or equal to the next element
        // or the next element is null (which means element is the largest in the collection)
        while (e.compareTo(entry.element) >= 1) {
            if (entry.next != null)
                entry = entry.next;
            else {   // element > all elements in collection
                entry.next = newEntry;
                return true;
            }
        }
        // if element isn't largest
        // Bucket swap to insert newEntry 'between' e and e.next
        Entry next = entry.next;
        entry.next = newEntry;
        newEntry.next = next;

        return true;
    }

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