package ua.com.alevel.tasks;

public class WhenDoesTheLessonEnds {

    // Function takes integer lesson number as input and displays hour and minute of lesson ending
    public static void whenDoesTheLessonEnds(int lesson) {
        // Condition used for checking the input, not part of the solution
        if (lesson < 1 || lesson > 10) {
            System.out.println("Invalid input: " + lesson);
            System.out.println("Can accept 1 - 10");
        } else {
            final int LESSON_DURATION = 45;
            final int ODD_BREAK_DURATION = 5;
            final int EVEN_BREAK_DURATION = 15;
            int hours = 9;
            int minutes = 0;

            int lessonsTime = lesson * LESSON_DURATION;
            int oddBreaksTime = lesson / 2 * ODD_BREAK_DURATION;
            int evenBreakTime = (lesson - 1) / 2 * EVEN_BREAK_DURATION;

            int allTime = lessonsTime + oddBreaksTime + evenBreakTime;

            hours += allTime / 60;
            minutes += allTime % 60;

            System.out.println(hours + " " + minutes);
        }
    }
}
