package ua.com.alevel;

public class ReverseString {

    public static String reverse(String src) {
        String[] words = src.split(" ");    // Get array of words in string
        StringBuilder reversedWord = new StringBuilder(); // Tmp reversed word
        StringBuilder reversedString = new StringBuilder();   // Final reversed string

        // Traverse all words in string
        for (int i = 0; i < words.length; i++) {
            // Reverse one word at a time
            for (int j = words[i].length() - 1; j >= 0; j--) {
                reversedWord.append(words[i].charAt(j));
            }

            reversedString.append(reversedWord);
            // Append space after words, except last one
            if (i != words.length - 1) {
                reversedString.append(" ");
            }

            // Reset tmp reversed word
            reversedWord.delete(0, reversedWord.length());
        }

        return reversedString.toString();
    }

    public static String reverse(String src, String dest) {
        StringBuilder reversedString = new StringBuilder(); // Final reversed string

        try {
            // Append part of sting before dest
            reversedString.append(src, 0, src.indexOf(dest));

            // Split dest into sub words if any
            String[] words = src.substring(src.indexOf(dest), src.indexOf(dest) + dest.length()).split(" ");

            for (int i = 0; i < words.length; i++) {
                // Reverse sub words and append to reversedString
                for (int j = words[i].length() - 1; j >= 0; j--) {
                    reversedString.append(words[i].charAt(j));
                }
                // Append space after words, except last one
                if (i != words.length - 1) {
                    reversedString.append(" ");
                }
            }
            // Append part of string after dest
            reversedString.append(src.substring(src.indexOf(dest) + dest.length()));
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Invalid 'dest'");
        }
        return reversedString.toString();
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        StringBuilder reversedString = new StringBuilder();

        try {
            // Append part of sting before dest
            reversedString.append(src, 0, firstIndex);

            String[] words = src.substring(firstIndex, lastIndex + 1).split(" ");

            for (int i = 0; i < words.length; i++) {
                // Reverse sub words and append to reversedString
                for (int j = words[i].length() - 1; j >= 0; j--) {
                    reversedString.append(words[i].charAt(j));
                }
                // Append space after words, except last one
                if (i != words.length - 1) {
                    reversedString.append(" ");
                }
            }
            // Append part of string after dest
            reversedString.append(src.substring(lastIndex + 1));

        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("Invalid index");
        }

        return reversedString.toString();
    }
}
