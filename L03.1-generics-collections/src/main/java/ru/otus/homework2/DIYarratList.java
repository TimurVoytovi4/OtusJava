package ru.otus.homework2;

import java.util.*;

public class DIYarratList<T> implements List<T> {
    private Object[] values;
    private static final int DEFAULT_CAPACITY_EMPTY = 0;
    private int size = DEFAULT_CAPACITY_EMPTY;

    DIYarratList(int initialCapacity) {
        if (initialCapacity > 0) {
            values = new Object[initialCapacity];
        }
    }

    DIYarratList() {
        values = new Object[DEFAULT_CAPACITY_EMPTY];
    }

    private Object[] grow() {
        return new Object[(values.length * 3) / 2 + 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("main.java.ru.otus.homework2.DIYarratList.isEmpty()");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("main.java.ru.otus.homework2.DIYarratList.contains(Object o)");
    }

    @Override
    public Iterator<T> iterator() {
        return new DIYIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(values, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("main.java.ru.otus.homework2.DIYarratList.toArray(T1[] a)");
    }

    @Override
    public boolean add(T t) {
        try {
            if (size + 1 >= values.length) {
                Object[] temp = values;
                values = grow();
                System.arraycopy(temp, 0, values, 0, temp.length);
            }
            values[size] = t;
            size++;
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
        throw new UnsupportedOperationException("main.java.ru.otus.homework2.DIYarratList.containsAll(Collection<?> c)");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("main.java.ru.otus.homework2.DIYarratList.addAll(Collection<? extends T> c)");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("main.java.ru.otus.homework2.DIYarratList.addAll(int index, Collection<? extends T> c)");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("main.java.ru.otus.homework2.DIYarratList.removeAll(Collection<?> c)");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("main.java.ru.otus.homework2.DIYarratList.retainAll(Collection<?> c)");
    }

    @Override
    public void clear() {
        Arrays.fill(values, size);
    }

    @Override
    public T get(int index) {
        checkValue(index);
        @SuppressWarnings("unchecked") T elem = (T) values[index];
        return elem;
    }

    @Override
    public T set(int index, T element) {
        checkValue(index);
        @SuppressWarnings("unchecked") T elem = (T) values[index];
        return elem;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("main.java.ru.otus.homework2.DIYarratList.add()");
    }

    @Override
    public T remove(int index) {
        checkValue(index);
        @SuppressWarnings("unchecked") T o = (T) values[index];
        try {
            Object[] temp = values;
            values = new Object[temp.length - 1];
            System.arraycopy(temp, 0, values, 0, index);
            int amountElemAfterIndex = temp.length - index--;
            System.arraycopy(temp, index + 1, values, index, amountElemAfterIndex);
            size--;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return o;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Object currentElem : values) {
            index++;
            if (currentElem.equals(o))
                break;
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("lastIndexOf(Object o)");
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DiyListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        checkValue(index);
        return new DiyListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        @SuppressWarnings("unchecked") List<T> res = (List<T>) Arrays.asList(Arrays.copyOfRange(values, fromIndex, toIndex));
        return res;
    }

    private void checkValue(int index) {
        if (index > size || index < 0)
            throw new IllegalArgumentException("Incorrect value");
    }

    class DIYIterator implements Iterator<T> {
        int cursor;
        int previousRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            cursor = i + 1;
            @SuppressWarnings("unchecked") T res = (T) values[previousRet = i];
            return res;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("DIYIterator.remove()");
        }
    }

    class DiyListIterator extends DIYIterator implements ListIterator<T> {
        DiyListIterator(int index) {
            super();
            checkValue(index);
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            if (previousRet >= 0) {
                @SuppressWarnings("unchecked") T res = (T) values[previousRet];
                return res;
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
        public void set(T t) {
            DIYarratList.this.set(previousRet, t);
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException("DiyListIterator.add()");
        }
    }
}
