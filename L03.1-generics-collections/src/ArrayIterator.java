import java.util.ListIterator;

public class ArrayIterator<T> implements ListIterator<T> {

    private int index = 0;
    int prevIndex = index--;
    private T[] values;

    public ArrayIterator(int index, T[] values) {
        this.index = index;
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public T next() {
        return values[index++];
    }

    @Override
    public boolean hasPrevious() {
        return previous() != null;
    }

    @Override
    public T previous() {
        if (prevIndex >= 0)
            return values[prevIndex];
        else return null;
    }

    @Override
    public int nextIndex() {
        return index++;
    }

    @Override
    public int previousIndex() {
        if (prevIndex >= 0)
            return prevIndex;
        return 0;
    }

    @Override
    public void remove() {

    }

    @Override
    public void set(T t) {

    }

    @Override
    public void add(T t) {

    }
}
