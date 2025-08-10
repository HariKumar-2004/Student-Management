
import java.io.*;
import java.util.*;

public class StudentService {

    private final StudentDAO dao;
    private final Scanner sc;

    public StudentService() {
        dao = new StudentDAO();
        sc = new Scanner(System.in);
    }

    public void addStudent() {
        try {
            System.out.println("Enter Roll No :");
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("Enter First Name : ");
            String fname = sc.nextLine();

            System.out.println("Enter Last Name : ");
            String lname = sc.nextLine();

            System.out.println("Enter Branch : ");
            String branch = sc.nextLine();

            System.out.println("Enter Phone Number : ");
            String phone = sc.nextLine();

            System.out.println("Enter CGPA : ");
            double gpa = Double.parseDouble(sc.nextLine());

            System.out.println("Enter Date Of Birth (yyyy-mm-dd) : ");
            String dob = sc.nextLine();

            Student student = new Student(id, fname, lname, branch, phone, gpa, dob);
            dao.insertStudent(student);
        } catch (Exception e) {
            System.out.println("Error adding student : " + e.getMessage());
        }
    }

    public void viewAllStudents() {
        List<Student> students = dao.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void searchStudentById() {
        try {
            System.out.println("Enter Roll No to search: ");
            int id = Integer.parseInt(sc.nextLine());
            Student student = dao.getStudentById(id);
            if (student != null) {
                System.out.println("Student Found : ");
                System.out.println(student);
            } else {
                System.out.println("Student not found. ");
            }
        } catch (Exception e) {
            System.out.println("Error searching student : " + e.getMessage());
        }
    }

    public void deleteStudentById() {
        try {
            System.out.println("Enter Roll No to delete : ");
            int id = Integer.parseInt(sc.nextLine());
            dao.deleteStudentById(id);
        } catch (Exception e) {
            System.out.println("Error deleting student : " + e.getMessage());
        }
    }

    public void updateStudentById() {
        try {
            System.out.println("Enter Roll No to update : ");
            int id = Integer.parseInt(sc.nextLine());
            Student student = dao.getStudentById(id);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            System.out.println("Leave blank to keep current value");

            System.out.print("Update First Name (" + student.getFirstName() + "): ");
            String fname = sc.nextLine();
            if (!fname.isBlank()) {
                student.setFirstName(fname);
            }

            System.out.print("Update Last Name (" + student.getLastName() + "): ");
            String lname = sc.nextLine();
            if (!lname.isBlank()) {
                student.setLastName(lname);
            }

            System.out.print("Update Branch (" + student.getMajor() + "): ");
            String branch = sc.nextLine();
            if (!branch.isBlank()) {
                student.setMajor(branch);
            }

            System.out.print("Update Phone No (" + student.getPhoneNumber() + "): ");
            String phone = sc.nextLine();
            if (!phone.isBlank()) {
                student.setPhoneNumber(phone);
            }

            System.out.print("Update CGPA (" + student.getGpa() + "):");
            String cgpaStr = sc.nextLine();
            if (!cgpaStr.isBlank()) {
                student.setGpa(Double.parseDouble(cgpaStr));
            }

            System.out.print("Update DOB (" + student.getDateOfBirth() + "): ");
            String dob = sc.nextLine();
            if (!dob.isBlank()) {
                student.setDateOfBirth(dob);
            }

            dao.updateStudent(student);
        } catch (Exception e) {
            System.out.println("Error updating student : " + e.getMessage());
        }
    }

    public void exportStudentsToCSV(String filename) {
        List<Student> students = dao.getAllStudents();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Write header 
            writer.write("ID,First Name, Last Name, Major, Phone Number, Gpa, Date Of Birth");
            writer.newLine();

            for (Student s : students) {
                writer.write(
                        s.getId() + ", "
                        + s.getFirstName() + ", "
                        + s.getLastName() + ", "
                        + s.getMajor() + ", "
                        + s.getPhoneNumber() + ", "
                        + s.getGpa() + ", "
                        + s.getDateOfBirth());
                writer.newLine();
            }

            System.out.println("Exported to CSV successfully: " + filename);

        } catch (IOException e) {
            System.err.println("Failed to export CSV: ");
            e.printStackTrace();
        }
    }
}
