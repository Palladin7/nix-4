package ua.com.alevel;

import java.util.Arrays;
import java.util.List;

public class TestOrderedList {

    public static void run() {
        // To test constructor
        Integer[] ints = {23, 6, 11, -3, 0, 45, -99, 18, 18};
        List<Integer> integers = Arrays.asList(ints);

        // 3 constructors
        OrderedList<Integer> list1 = new OrderedList<>();
        OrderedList<Integer> list2 = new OrderedList<>(7);
        OrderedList<Integer> list3 = new OrderedList<>(integers);

        // add method
        list1.add(7);
        list1.add(-13);
        list1.add(88);
        list1.add(-13);
        list1.add(99);


        System.out.println("---------------------OrderedList----------------------");
        // toString method
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);

        // isEmpty method
        System.out.println("\nIs list2 empty? " + list2.isEmpty());

        // addAll method
        list2.addAll(list1);
        System.out.println("list2: " + list2);
        System.out.println("Is list2 empty now? " + list2.isEmpty());

        // size method
        System.out.println("\nElements in list2: " + list2.size());
        int integer = 99;
        // contains method
        System.out.println("Does list2 contain " + integer + "? " + list2.contains(integer));
        // remove method
        list2.remove(Integer.valueOf(99));
        System.out.println("Does list2 contain " + integer + " now? " + list2.contains(integer));

        // containsAll method
        list2.addAll(list1);
        System.out.println("\nDoes list2 contain all elements of list1? " + list2.containsAll(list1));
        // removeAll method
        list2.removeAll(list1);
        System.out.println("Does list2 contain all elements of list1 now? " + list2.containsAll(list1));

        // retainAll method
        System.out.println("\nlist1: " + list1);
        System.out.println("list2: " + list2);
        list1.retainAll(list2);
        System.out.println("\nAfter retaining all elements from list2");
        System.out.println("list1: " + list1);

        // clear method
        list1.clear();
        System.out.println("\nAfter clearing");
        System.out.println("list1: " + list1);

        // get method
        System.out.println("\nlist3: " + list3);
        int index = 4;
        System.out.println("Element at index " + index + ": " + list3.get(index));

        // indexOf, lastIndexOf methods
        int element = 18;
        System.out.println("Index of " + element + ": " + list3.indexOf(element));
        System.out.println("Last index of " + element + ": " + list3.lastIndexOf(element));

        // Iterator usage
        System.out.println();
        System.out.print("list3: ");
        for (Integer e :
                list3) {
            System.out.print(e + " ");
        }

        System.out.println("\n\n-------------------Working with strings----------------------");
        OrderedList<String> list4 = new OrderedList<>();

        list4.add("Dallas");
        list4.add("Houston");
        list4.add("Chicago");
        list4.add("Boston");
        list4.add("Miami");
        list4.add("New York");
        list4.add("Atlanta");

        System.out.println("list4: " + list4);
        list4.remove("Dallas");
        System.out.println("list4: " + list4);
    }
}
