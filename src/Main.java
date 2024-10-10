import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Student {
    String name;
    int id;
    int age;
    ArrayList<Integer> grades;

    public Student(String name, int id, int age, ArrayList<Integer> grades) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.grades = grades;
    }

    public double calculateAverage() {
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return grades.size() > 0 ? (double) total / grades.size() : 0;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id + ", Age: " + age + ", Average Grade: " + String.format("%.2f", calculateAverage());
    }
}

class StudentManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. List All Students");
            System.out.println("4. Calculate Average Grade");
            System.out.println("5. Delete Student");
            System.out.println("6. Update Student");
            System.out.println("7. Show Top 3 Students");
            System.out.println("8. Show Grade Categories and Statistics");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    searchStudent();
                    break;
                case 3:
                    listAllStudents();
                    break;
                case 4:
                    calculateAverageGrade();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    updateStudent();
                    break;
                case 7:
                    showTopStudents();
                    break;
                case 8:
                    showGradeCategories();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void addStudent() {
        System.out.print("Enter Student Name: ");
        scanner.nextLine(); // consume the leftover newline
        String name = scanner.nextLine();
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Student Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter number of grades: ");
        int numGrades = scanner.nextInt();
        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = 1; i <= numGrades; i++) {
            System.out.print("Enter Grade " + i + ": ");
            grades.add(scanner.nextInt());
        }

        students.add(new Student(name, id, age, grades));
        System.out.println("Student added successfully!");
    }

    public static void searchStudent() {
        System.out.print("Enter Student ID or Name to search: ");
        scanner.nextLine(); // consume the leftover newline
        String input = scanner.nextLine();

        for (Student student : students) {
            if (student.name.equalsIgnoreCase(input) || Integer.toString(student.id).equals(input)) {
                System.out.println("Student found: " + student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            System.out.println("All Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public static void calculateAverageGrade() {
        System.out.print("Enter Student ID to calculate average grade: ");
        int id = scanner.nextInt();

        for (Student student : students) {
            if (student.id == id) {
                System.out.println("Average Grade for " + student.name + ": " + String.format("%.2f", student.calculateAverage()));
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        for (Student student : students) {
            if (student.id == id) {
                students.remove(student);
                System.out.println("Student deleted successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Новый метод для обновления студента
    public static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();

        for (Student student : students) {
            if (student.id == id) {
                System.out.print("Enter new Student Name: ");
                scanner.nextLine(); // consume the leftover newline
                student.name = scanner.nextLine();
                System.out.print("Enter new Student Age: ");
                student.age = scanner.nextInt();
                System.out.println("Student details updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Новый метод для отображения топ-3 студентов
    public static void showTopStudents() {
        students.sort(Comparator.comparingDouble(Student::calculateAverage).reversed());
        System.out.println("Top 3 Students:");
        for (int i = 0; i < Math.min(3, students.size()); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }

    // Новый метод для вывода категорий оценок и статистики
    public static void showGradeCategories() {
        int excellent = 0, good = 0, satisfactory = 0, unsatisfactory = 0;

        for (Student student : students) {
            double avg = student.calculateAverage();
            if (avg >= 85) {
                excellent++;
            } else if (avg >= 70) {
                good++;
            } else if (avg >= 50) {
                satisfactory++;
            } else {
                unsatisfactory++;
            }
        }

        System.out.println("Grade Categories:");
        System.out.println("Excellent (85-100): " + excellent);
        System.out.println("Good (70-84): " + good);
        System.out.println("Satisfactory (50-69): " + satisfactory);
        System.out.println("Unsatisfactory (0-49): " + unsatisfactory);
    }
}
