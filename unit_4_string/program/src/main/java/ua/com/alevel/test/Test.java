package ua.com.alevel.test;

import ua.com.alevel.ReverseString;

public class Test {

    public void run() {
        System.out.println("--> hello world");
        System.out.println("<-- " + ReverseString.reverse("hello world"));
        System.out.println("<-- " + ReverseString.reverse("hello world", "lo wo"));
        System.out.println("<-- " + ReverseString.reverse("hello world", 3, 7));
    }
}
