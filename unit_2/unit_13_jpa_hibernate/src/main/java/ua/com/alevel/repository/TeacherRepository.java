package ua.com.alevel.repository;

import org.hibernate.Session;
import ua.com.alevel.entity.*;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherRepository {

    public Teacher getTeacherById(Long id) {
        Teacher teacher = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            teacher = session.load(Teacher.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Teacher> cr = cb.createQuery(Teacher.class);
            Root<Teacher> root = cr.from(Teacher.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            teachers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(teacher);
            session.getTransaction().commit();
        }
    }

    public void updateTeacher(Teacher teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(teacher);
            session.getTransaction().commit();
        }
    }

    public void deleteTeacherById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Teacher teacher = getTeacherById(id);
            session.beginTransaction();
            session.delete(teacher);
            session.getTransaction().commit();
        }
    }

    public String getTheMostSuccessfulGroupForTeacher(Long id) {
        StringBuilder stringBuilder = new StringBuilder("");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Teacher teacher = session.load(Teacher.class, id);
            List<Group> groups = teacher.getGroups();

            List<Double> medians = new ArrayList<>();
            int groupId = 0;
            int bestGroupId = 0;

            for (Group group : groups) {
                List<Student> students = group.getStudents();

                List<Integer> examGrades = new ArrayList<>();
                for (Student student : students) {
                    Integer examGrade = student.getGrades().stream()
                            .sorted(Comparator.comparingInt(Grade::getGrade))
                            .skip(student.getGrades().size() - 1)
                            .findFirst()
                            .get().getGrade();

                    examGrades.add(examGrade);
                }

                examGrades.sort(Comparator.comparingInt(e -> e));

                int size = examGrades.size();
                double median = 0;

                if (size % 2 == 1) {
                    median = (double) (examGrades.get(size / 2));
                } else {
                    median = examGrades.get(size / 2 - 1) + examGrades.get(size / 2) / 2.0;
                }

                medians.add(median);

                if (medians.stream().max(Comparator.comparingDouble(e -> e)).get() < median) {
                    bestGroupId = groupId;
                }

                groupId++;
            }

            Group group = groups.get(bestGroupId);
            Course course = group.getCourse();

            stringBuilder.append(group.getName());
            stringBuilder.append(" ");
            stringBuilder.append(course.getName());
            stringBuilder.append(" ");
            stringBuilder.append(course.getDescription());
            stringBuilder.append(" ");
            stringBuilder.append(medians.get(bestGroupId));
        }

        return stringBuilder.toString();
    }
}
