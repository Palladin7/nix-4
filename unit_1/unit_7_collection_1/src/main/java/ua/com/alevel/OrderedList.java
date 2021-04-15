package ua.com.alevel;

import java.util.*;

public class OrderedList<E extends Comparable<E>> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int size;
    private E[] list;

    //-------------------Constructors--------------------

    // Constructs an empty list with DEFAULT_CAPACITY (10)
    @SuppressWarnings("unchecked")
    public OrderedList() {
        list = (E[]) new Comparable[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    // Constructs an empty list with given capacity
    @SuppressWarnings("unchecked")
    public OrderedList(int capacity) {
        list = (E[]) new Comparable[capacity];
        this.capacity = capacity;
    }

    // Constructs a list containing elements from specified collection
    @SuppressWarnings("unchecked")
    public OrderedList(Collection<? extends E> collection) {
        // First write each element from collection to array
        E[] objects = (E[]) new Comparable[collection.size()];
        int i = 0;
        for (E o : collection) {
            objects[i] = o;
            i++;
        }

        // Then sort the array
        for (i = 0; i < objects.length - 1; i++) {
            for (int j = 0; j < objects.length - i - 1; j++) {
                if (objects[j].compareTo(objects[j + 1]) > 0) {
                    E tmp = objects[j];
                    objects[j] = objects[j + 1];
                    objects[j + 1] = tmp;
                }
            }
        }

        size = objects.length;
        capacity = size * 2;
        list = (E[]) new Comparable[capacity];
        System.arraycopy(objects, 0, list, 0, size);
    }
    //---------------------------------------------------

    // Return current size of list
    @Override
    public int size() {
        return size;
    }

    // Check if list is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if list contains specified object
    @Override
    public boolean contains(Object o) {
        for (Object e : list) {
            if (o.equals(e)) {
                return true;
            }
        }
        return false;
    }
    // Iterator for OrderedList
    @Override
    public Iterator<E> iterator() {
        return new OrderedListIterator();
    }

    // Return array representation of list
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(list, size);
    }

    // Return array representation of list to specified array
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] destination) {
        if (destination.length >= size) {
            System.arraycopy(list, 0, destination, 0, size);
            return destination;
        }
        return (T[]) Arrays.copyOf(list, size, destination.getClass());
    }

    // Adds specified element to list
    @Override
    public boolean add(E e) {
        if (size + 1 > capacity) {
            ensureCapacity();
        }

        // If list contains 0 elements
        if (size == 0) {
            list[0] = e;
            size++;
            return true;
        }

        // Find where to put new element
        for (int i = 0; i < size; i++) {
            if (list[i].compareTo(e) >= 0) {
                System.arraycopy(list, i, list, i + 1, size - i);
                list[i] = e;
                size++;
                return true;
            }
        }

        // Add to the end of list
        list[size] = e;
        size++;

        return true;
    }

    // Removes specified element from list
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) {
                // Element to be deleted is not last
                if (i + 1 < size) {
                    System.arraycopy(list, i + 1, list, i, size - i);
                } else {
                    // Element to be deleted is last
                    list[i] = null;
                }
                size--;
                return true;
            }
        }

        return false;
    }

    // Checks if list contains all elements from specified collection
    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.size() > size) {
            return false;
        }

        // Check each element in collection
        for (Object e : collection) {
            boolean found = false;

            for (int i = 0; i < size; i++) {
                if (e.equals(list[i])) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }

        return true;
    }

    // Adds all elements from specified collection
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E o : collection) {
            add(o);
        }
        return true;
    }

    // Method can not be implemented
    // Adding element to SORTED list by specified INDEX has no logic
    @Override
    @Deprecated
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object o : collection) {
            remove(o);
        }
        return true;
    }

    // Remove all elements from list that are not present in specified collection
    @Override
    public boolean retainAll(Collection<?> collection) {
        for (int i = 0; i < size; i++) {
            boolean present = false;
            for (Object o : collection) {
                if (list[i].equals(o)) {
                    present = true;
                    break;
                }
            }

            if (!present) {
                remove(list[i]);
                i--;
            }
        }
        return true;
    }

    // Removes all elements from list
    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < capacity; i++) {
            if (list[i] == null) {
                break;
            } else {
                list[i] = null;
            }
        }
    }

    // Returns element at specified index
    @Override
    public E get(int index) {
        if (index >= 0) {
            return list[index];
        }

        return null;
    }

    // Method can not be implemented
    // Setting element in SORTED list by specified INDEX has no logic
    @Override
    @Deprecated
    public E set(int index, E element) {
        throw new RuntimeException("Method not implemented");
    }

    // Method can not be implemented
    // Adding element to SORTED list by specified INDEX has no logic
    @Override
    public void add(int index, E element) {
        throw new RuntimeException("Method not implemented");
    }

    // Remove element by specified index
    @Override
    public E remove(int index) {
        E element = list[index];
        remove(list[index]);
        return element;
    }

    // Find index of specified element
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    // Find last index of specified element
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (list[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new OrderedListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new OrderedListIterator(index);
    }

    // Returns sub list of our list
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        E[] sublist = Arrays.copyOfRange(list, fromIndex, toIndex);

        return Arrays.asList(sublist);
    }

    // Increase size of list
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (capacity * 2 > 0) {
            capacity *= 2;
        } else if (capacity < Integer.MAX_VALUE) {
            capacity = Integer.MAX_VALUE;
        } else {
            throw new RuntimeException("Exceeding maximum capacity");
        }

        E[] newList = (E[]) new Comparable[capacity];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    // Iterator for our OrderedList
    private class OrderedListIterator implements ListIterator<E> {

        private int current;

        public OrderedListIterator() {
            current = 0;
        }

        public OrderedListIterator(int from) {
            if (from < size) {
                current = from;
            } else {
                current = 0;
            }
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            return list[current++];
        }

        @Override
        public boolean hasPrevious() {
            return current >= 0;
        }

        @Override
        public E previous() {
            return list[current--];
        }

        @Override
        public int nextIndex() {
            return current + 1;
        }

        @Override
        public int previousIndex() {
            return current - 1;
        }

        @Override
        public void remove() {
            OrderedList.this.remove(current);
        }

        // Method can not be implemented
        // Setting element in SORTED list by specified INDEX has no logic
        @Override
        @Deprecated
        public void set(E e) {
            throw new RuntimeException("Method not implemented");
        }

        @Override
        public void add(E e) {
            OrderedList.this.add(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                stringBuilder.append(list[i]);
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(list[i]);
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
