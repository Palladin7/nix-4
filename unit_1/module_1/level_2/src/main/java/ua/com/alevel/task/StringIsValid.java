package ua.com.alevel.task;

import java.util.Stack;

public class StringIsValid {

    public static boolean isValid(String string) {
        String openBraces = "({[";
        String closeBraces = ")}]";
        Stack<Character> stringBraces = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            char symbol = string.charAt(i);
            // If character is open brace, add it to list
            if (openBraces.contains(Character.toString(symbol))) {
                stringBraces.push(string.charAt(i));
            } else if (closeBraces.contains(Character.toString(symbol))) {
                // If character is close brace which has corresponding open brace,
                // remove open brace from stack without adding close brace to it
                if (symbol == ')' && stringBraces.peek() == '(' ||
                        symbol == '}' && stringBraces.peek() == '{' ||
                        symbol == ']' && stringBraces.peek() == '[') {
                    stringBraces.pop();
                } else {
                    // Close brace without corresponding open brace
                    return false;
                }
            }
        }
        // If stack is empty, string is valid, otherwise not
        return stringBraces.size() == 0;
    }
}
