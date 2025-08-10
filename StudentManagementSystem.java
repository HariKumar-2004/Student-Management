
import java.util.Scanner;

public class StudentManagementSystem {

    private static StudentService service = new StudentService();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n --------- Student Management System -------");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student By Roll No");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Export students to CSV file");
            System.out.println("7. Exit");
            System.out.println("Enter your choice : ");

            String input = sc.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
                continue;
            }

            switch (choice) {
                case 1 ->
                    service.addStudent();
                case 2 ->
                    service.viewAllStudents();
                case 3 ->
                    service.searchStudentById();
                case 4 ->
                    service.updateStudentById();
                case 5 ->
                    service.deleteStudentById();
                case 6 -> {
                    System.out.println("Enter filename to export (eg., students.csv): ");
                    String filename = sc.next();
                    service.exportStudentsToCSV(filename);
                }

                case 7 -> {
                    System.out.println("Exiting the system. Goodbye! ");
                    sc.close();
                    return;
                }
                default ->
                    System.out.println("Invalid choice. Please try again. ");
            }
        }
    }
}
