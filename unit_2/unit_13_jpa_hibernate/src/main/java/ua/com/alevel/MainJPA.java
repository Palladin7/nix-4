package ua.com.alevel;

import ua.com.alevel.controller.StudentController;
import ua.com.alevel.controller.TeacherController;

public class MainJPA {

    public static void main(String[] args) {
        long studentId = 1L;
        StudentController studentController = new StudentController();
        System.out.println("Next lesson for student with id " + studentId + ": ");
        System.out.println("Date\t\t\tTeacher\t\tCourse\tSubject");
        System.out.println(studentController.getNextLessonForStudent(3L));

        long teacherId = 1;
        TeacherController teacherController = new TeacherController();
        System.out.println("\nThe most successful group for teacher with id " + teacherId + ": ");
        System.out.println("Name Course\tDescription\tExam grades median");
        System.out.println(teacherController.getTheMostSuccessfulGroupForTeacher(2L));
    }
}
