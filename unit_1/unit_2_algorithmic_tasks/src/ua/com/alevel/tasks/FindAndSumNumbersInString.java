package ua.com.alevel.tasks;

public class FindAndSumNumbersInString {

    // Function takes string as input and returns sum of all numbers in it
    public static int findAndSumNumbers(String str) {
        int sum = 0;
        int number = 0;
        int firstIndex = 0; // Index of first digit in current number
        int lastIndex = 0; // Index of last digit in current number

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                firstIndex = i;
                lastIndex = i;
                // Start looking for last digit of current number
                while (lastIndex < str.length() - 1 && Character.isDigit(str.charAt(i + 1))) {
                    i++;
                    lastIndex++;
                }

                // Cast string number to int
                number = Integer.parseInt(str.substring(firstIndex, lastIndex + 1));
                sum += number;
            }
        }
        return sum;
    }
}
