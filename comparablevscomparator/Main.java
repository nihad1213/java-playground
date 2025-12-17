package comparablevscomparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Teacher {
    String name;
    int age;

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}


class Student implements Comparable<Student> {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Student other) {
        return this.age - other.age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class Main {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 22));
        students.add(new Student("Bob", 20));
        students.add(new Student("Charlie", 25));

        Collections.sort(students);
        System.out.println(students);

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Nihad", 16));
        teachers.add(new Teacher("Dagin", 34));
        teachers.add(new Teacher("Teacher", 25));

        Comparator<Teacher> nameComparator = new Comparator<Teacher>() {
            @Override
            public int compare(Teacher s1, Teacher s2) {
                return s1.name.compareTo(s2.name);
            }
        };

        Comparator<Teacher> ageComparator = new Comparator<Teacher>() {
            @Override
            public int compare(Teacher s1, Teacher s2) {
                return s1.age - s2.age;
            }
        };

        Collections.sort(teachers, nameComparator);
        Collections.sort(teachers, ageComparator);

        System.out.println(teachers);
    }
}
