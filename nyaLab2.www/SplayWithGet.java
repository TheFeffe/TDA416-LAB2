import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SplayWithGet<T> implements CollectionWithGet<TestMapWithCounter.TestMapEntry<String,List<Integer>>> {
    @Override
    public TestMapWithCounter.TestMapEntry<String, List<Integer>> get(TestMapWithCounter.TestMapEntry<String, List<Integer>> stringListTestMapEntry) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<TestMapWithCounter.TestMapEntry<String, List<Integer>>> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(TestMapWithCounter.TestMapEntry<String, List<Integer>> stringListTestMapEntry) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends TestMapWithCounter.TestMapEntry<String, List<Integer>>> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
