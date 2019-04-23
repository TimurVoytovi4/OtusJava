import java.util.*;

public class DIYarratList<T> implements List<T> {
    private T[] values;

    DIYarratList() {
        values = (T[]) new Object[0];
    }

    @Override
    public int size() {
        return values.length;
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
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        try {
            T[] temp = values;
            values = (T[]) new Object[temp.length + 1];
            System.arraycopy(temp, 0, values, 0, temp.length);
            values[values.length - 1] = t;
            return true;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return remove(indexOf(o)) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
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
        Arrays.fill(values, values.length);
    }

    @Override
    public T get(int index) {
        return values[index];
    }

    @Override
    public T set(int index, T element) {
        return values[index] = element;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        T o = values[index];
        try {
            T[] temp = values;
            values = (T[]) new Object[temp.length - 1];
            System.arraycopy(temp, 0, values, 0, index);
            int amountElemAfterIndex = temp.length - index--;
            System.arraycopy(temp, index + 1, values, index, amountElemAfterIndex);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return o;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Object searcher : values) {
            index++;
            if (searcher.equals(o))
                break;
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ArrayIterator<>(index, values);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return Arrays.asList(Arrays.copyOfRange(values, fromIndex, toIndex));
    }
}
