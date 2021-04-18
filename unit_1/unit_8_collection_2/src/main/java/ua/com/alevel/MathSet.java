package ua.com.alevel;

import java.math.BigDecimal;
import java.util.Comparator;

public class MathSet<E extends Number & Comparable<E>> {

    private final int DEFAULT_CAPACITY = 16;
    private int capacity;
    private int size;
    private Number[] data;
    private final NumberComparator numberComparator = new NumberComparator();

    // ------------------Constructors---------------------------
    public MathSet() {
        data = new Number[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    public MathSet(int capacity) {
        data = new Number[capacity];
        this.capacity = capacity;
    }

    public MathSet(Number[] numbers) {
        capacity = DEFAULT_CAPACITY;
        data = new Number[capacity];

        for (Number n : numbers) {
            add(n);
        }
    }

    public MathSet(Number[]... numbers) {
        capacity = DEFAULT_CAPACITY;
        data = new Number[capacity];

        for (Number[] nums : numbers) {
            add(nums);
        }
    }

    public MathSet(MathSet<E> numbers) {
        this.capacity = numbers.capacity;
        data = new Number[capacity];

        for (Number n : numbers.data) {
            add(n);
        }
    }

    @SafeVarargs
    public MathSet(MathSet<E>... numbers) {
        this.capacity = DEFAULT_CAPACITY;
        data = new Number[capacity];

        for (MathSet<E> nums : numbers) {
            add(nums.data);
        }
    }

    public int getSize() {
        return size;
    }

    // ------------------------Add-------------------------------
    public void add(Number number) {
        if (number == null) {
            return;
        }

        // If data is empty, add first element
        if (size == 0) {
            data[0] = number;
            size++;
        }

        // Do not add number that is already present
        for (Number n : data) {
            if (n == null) {
                break;
            }
            if (numberComparator.compare(number, n) == 0) {
                return;
            }
        }

        // If we exceed current capacity
        if (size + 1 > capacity) {
            ensureCapacity();
        }

        data[size++] = number;
    }

    public final void add(Number... numbers) {
        for (Number n : numbers) {
            add(n);
        }
    }

    // ---------------------Join-------------------------
    public void join(MathSet<E> mathSet) {
        for (Number n : mathSet.data) {
            add(n);
        }
    }

    @SafeVarargs
    public final void join(MathSet<E>... mathSets) {
        for (MathSet<E> mathSet : mathSets) {
            add(mathSet.data);
        }
    }

    // -----------------------Sort-----------------------
    public void sortDesc() {
        sortingDesc(0, size);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        // Check bounds of sort
        if (firstIndex <= 0) {
            firstIndex = 0;
        }
        if (lastIndex > size) {
            lastIndex = size;
        }

        sortingDesc(firstIndex, lastIndex);
    }

    public void sortDesc(Number value) {
        int i = 0;
        // Find index of value
        for (; i < size - 1; i++) {
            if (data[i] != null && numberComparator.compare(data[i], value) == 0) {
                break;
            }
        }

        sortingDesc(i, size);
    }

    // Helper sorting method
    private void sortingDesc(int fromIndex, int toIndex) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = fromIndex; j < toIndex - i - 1; j++) {
                if (numberComparator.compare(data[j], data[j + 1]) < 0) {
                    Number tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
    }

    public void sortAsc() {
        sortingAsc(0, size);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        // Check bounds of sort
        if (firstIndex <= 0) {
            firstIndex = 0;
        }
        if (lastIndex > size) {
            lastIndex = size;
        }

        sortingAsc(firstIndex, lastIndex);
    }

    public void sortAsc(Number value) {
        int i = 0;
        // Finds index of value
        for (; i < size - 1; i++) {
            if (data[i] != null && numberComparator.compare(data[i], value) == 0) {
                break;
            }
        }

        sortingAsc(i, size);
    }

    // Helper sorting method
    private void sortingAsc(int fromIndex, int toIndex) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = fromIndex; j < toIndex - i - 1; j++) {
                if (numberComparator.compare(data[j], data[j + 1]) > 0) {
                    Number tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
    }

    public Number get(int index) {
        return data[index];
    }

    public Number getMax() {
        Number max = data[0];

        for (int i = 1; i < size; i++) {
            if (numberComparator.compare(data[i], max) > 0) {
                max = data[i];
            }
        }

        return max;
    }

    public Number getMin() {
        Number min = data[0];

        for (int i = 1; i < size; i++) {
            if (numberComparator.compare(data[i], min) < 0) {
                min = data[i];
            }
        }

        return min;
    }

    public Number getAverage() {
        double sum = data[0].doubleValue();

        for (int i = 1; i < size; i++) {
            sum += data[i].doubleValue();
        }

        return sum / size;
    }

    public Number getMedian() {
        sortAsc();

        if (size % 2 == 1) {
            return data[size / 2];
        } else {
            return (data[size / 2].doubleValue() + data[size / 2 - 1].doubleValue()) / 2;
        }
    }

    public Number[] toArray() {
        Number[] array = new Number[size];

        System.arraycopy(data, 0, array, 0, size);

        return array;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        // Check borders
        if (firstIndex < 0) {
            firstIndex = 0;
        }
        if (lastIndex > size) {
            lastIndex = size;
        }

        Number[] array = new Number[lastIndex - firstIndex];
        int index = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            array[index] = data[i];
        }

        return array;
    }

    public MathSet<E> squash(int firstIndex, int lastIndex) {
        MathSet<E> newSet = new MathSet<>();

        // Check borders
        if (firstIndex < 0) {
            firstIndex = 0;
        }
        if (lastIndex > size) {
            lastIndex = size;
        }

        for (int i = firstIndex; i < lastIndex; i++) {
            newSet.add(get(i));
        }

        return newSet;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    public void clear(Number[] numbers) {
        System.arraycopy(data, 0, numbers, 0, numbers.length);

        clear();
    }

    private void ensureCapacity() {
        if (capacity * 2 > 0) {
            capacity *= 2;
        } else if (capacity < Integer.MAX_VALUE) {
            capacity = Integer.MAX_VALUE;
        } else {
            throw new RuntimeException("Exceeding maximum capacity");
        }

        Number[] newData = new Number[capacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                stringBuilder.append(data[i]);
            } else {
                stringBuilder.append(data[i]);
                stringBuilder.append(" ");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private static class NumberComparator implements Comparator<Number> {

        public int compare(Number a, Number b){
            return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString()));
        }
    }
}
