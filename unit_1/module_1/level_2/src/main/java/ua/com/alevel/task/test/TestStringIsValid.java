package ua.com.alevel.task.test;

import ua.com.alevel.task.StringIsValid;

public class TestStringIsValid {

    public static void run() {
        String string1 = "{[Hello](World)}";
        String string2 = "{{Hello]World";
        System.out.println("-----------------------String Is Valid---------------------");
        System.out.print("Is string \"" + string1 + "\" valid? ");
        System.out.println(StringIsValid.isValid(string1));

        System.out.print("Is string \"" + string2 + "\" valid? ");
        System.out.println(StringIsValid.isValid(string2));
        System.out.println("-----------------------------------------------------------");
    }
}
