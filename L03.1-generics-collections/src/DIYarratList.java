import java.util.*;

public class DIYarratList<T> implements List<T> {
    private T[] values;
    private static final int DEFAULT = 0;

    DIYarratList(int initialCapacity) {
        if (initialCapacity > 0) {
            values = (T[]) new Object[initialCapacity];
        }
    }

    DIYarratList() {
        values = (T[]) new Object[DEFAULT];
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
        return new DIYIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(values, values.length);
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
        return new DiyListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new DiyListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return Arrays.asList(Arrays.copyOfRange(values, fromIndex, toIndex));
    }

    class DIYIterator implements Iterator<T> {
        int cursor;
        int previousRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != values.length;
        }

        @Override
        public T next() {
            int i = cursor;
            cursor = i + 1;
            return values[previousRet = i];
        }
    }

    class DiyListIterator extends DIYIterator implements ListIterator<T> {
        DiyListIterator(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            if (previousRet >= 0) {
                return values[previousRet];
            }
            return null;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            DIYarratList.this.set(previousRet, t);
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }
}
