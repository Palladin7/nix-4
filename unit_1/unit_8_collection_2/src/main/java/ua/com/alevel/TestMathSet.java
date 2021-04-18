package ua.com.alevel;

import java.util.Arrays;

public class TestMathSet {

    public static void run() {
        Short[] shorts = {4, 8, 3, 5, 12};

        Short[] shorts1 = {23, 523, 523, 6, 23, 6};
        Short[] shorts2 = {11, 74, 2, 6, 2, 35, 734, 5};

        MathSet<Integer> mathSet1 = new MathSet<>();
        MathSet<Double> mathSet2 = new MathSet<>(5);
        MathSet<Short> mathSet3 = new MathSet<>(shorts);
        MathSet<Short> mathSet4 = new MathSet<>(shorts1, shorts2);
        MathSet<Short> mathSet5 = new MathSet<>(shorts);
        MathSet<Short> mathSet6 = new MathSet<>(mathSet3, mathSet4);

        System.out.println("----------------------MathSet---------------------");
        System.out.println("mathSet1: " + mathSet1);
        System.out.println("mathSet2: " + mathSet2);
        System.out.println("mathSet3: " + mathSet3);
        System.out.println("mathSet4: " + mathSet4);
        System.out.println("mathSet5: " + mathSet5);
        System.out.println("mathSet6: " + mathSet6);

        // add(Number number)
        mathSet5.add(11);

        // add(Number... numbers)
        mathSet5.add(23, 11, 67, 15);
        System.out.println("\nAfter adding new elements");
        System.out.println("mathSet5: " + mathSet5);

        // join(MathSet<E> mathSet)
        mathSet5.join(mathSet4);
        System.out.println("\nAfter joining mathSet5 with mathSet4");
        System.out.println("mathSet5: " + mathSet5);

        // join(MathSet<E>... mathSets)
        mathSet3.join(mathSet4, mathSet5);
        System.out.println("\nAfter joining mathSet3 with mathSet4 and mathSet5");
        System.out.println("mathSet3: " + mathSet3);

        // sortAsc(E value)
        Number number1 = 734;
        mathSet3.sortAsc(number1);
        System.out.println("\n------------------------------------------------");
        System.out.println("After sorting by ascending after element " + number1);
        System.out.println("mathSet3: " + mathSet3);

        // sortAsc(int firstIndex, int lastIndex)
        int fromIndex = 6;
        int toIndex = 11;
        mathSet3.sortAsc(fromIndex, toIndex);
        System.out.println("\nAfter sorting by ascending from index " + fromIndex + " to index " + toIndex);
        System.out.println("mathSet3: " + mathSet3);

        // sortAsc()
        mathSet3.sortAsc();
        System.out.println("\nAfter sorting by ascending");
        System.out.println("mathSet3: " + mathSet3);

        // sortDsc(E value)
        Number number2 = 35;
        mathSet3.sortDesc(number2);
        System.out.println("\n------------------------------------------------");
        System.out.println("After sorting by descending after element " + number2);
        System.out.println("mathSet3: " + mathSet3);

        // sortDesc(int firstIndex, int lastIndex)
        mathSet3.sortDesc(fromIndex, toIndex);
        System.out.println("\nAfter sorting by descending from index " + fromIndex + " to index " + toIndex);
        System.out.println("mathSet3: " + mathSet3);

        // sortDesc()
        mathSet3.sortDesc();
        System.out.println("\nAfter sorting by descending");
        System.out.println("mathSet3: " + mathSet3);

        // get(int index)
        int index = 4;
        System.out.println("-------------------------------------------");
        System.out.println("Number at index " + index + ": " + mathSet3.get(index));

        // getMax()
        System.out.println("Max number: " + mathSet3.getMax());

        // getMin()
        System.out.println("Min number: " + mathSet3.getMin());

        // getAverage()
        System.out.println("Average: " + mathSet3.getAverage());

        // getMedian()
        System.out.println("Median: " + mathSet3.getMedian());

        // toArray()
        System.out.println("\nArray representation of mathSet3: " + Arrays.toString(mathSet3.toArray()));

        // squash(int firstIndex, int lastIndex)
        System.out.println("Squashed from index " + fromIndex + " to index " + toIndex);
        System.out.println(mathSet3.squash(fromIndex, toIndex));

        // clear(Number[] numbers)
        Number[] numbers = new Number[mathSet3.getSize()];
        mathSet3.clear(numbers);
        System.out.println("Clear mathSet3: " + mathSet3);
    }
}
