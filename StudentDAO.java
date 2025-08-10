
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final String url = System.getenv("DB_URL");
    private final String user = System.getenv("DB_USERNAME");
    private final String password = System.getenv("DB_PASSWORD");

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void insertStudent(Student student) {
        String sql = "INSERT INTO student(id, firstname, lastname, major, phonenumber, gpa, dateofbirth) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getFirstName());
            pstmt.setString(3, student.getLastName());
            pstmt.setString(4, student.getMajor());
            pstmt.setString(5, student.getPhoneNumber());
            pstmt.setDouble(6, student.getGpa());
            pstmt.setString(7, student.getDateOfBirth());

            pstmt.executeUpdate();
            System.out.println("Student inserted into DB");

        } catch (SQLException e) {
            System.out.println(" DB Insert Error :" + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastName"),
                        rs.getString("major"),
                        rs.getString("phoneNumber"),
                        rs.getDouble("gpa"),
                        rs.getString("dateOfBirth"));
                students.add(s);
            }

        } catch (SQLException e) {
            System.out.println("DB Fetch Error : " + e.getMessage());
        }

        return students;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("major"),
                        rs.getString("phonenumber"),
                        rs.getDouble("gpa"),
                        rs.getString("dateofbirth"));
            }

        } catch (SQLException e) {
            System.out.println("DB Fetch Error : " + e.getMessage());
        }
        return null;
    }

    public void deleteStudentById(int id) {
        String sql = "DELETE FROM student WHERE id = ? ";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affected = pstmt.executeUpdate();

            if (affected > 0) {
                System.out.println("Student deleted. ");
            } else {
                System.out.println("No student found with ID : " + id);
            }

        } catch (SQLException e) {
            System.out.println("DB Delete Error : " + e.getMessage());
        }
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE student SET firstname = ?, lastname = ?, major = ?, phonenumber = ?, gpa = ?, dateofbirth = ?, WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getMajor());
            pstmt.setString(4, student.getPhoneNumber());
            pstmt.setDouble(5, student.getGpa());
            pstmt.setString(6, student.getDateOfBirth());
            pstmt.setInt(7, student.getId());

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                System.out.println("Student Updated");
            } else {
                System.out.println("No student found to update.");
            }

        } catch (SQLException e) {
            System.out.println("DB Update Error : " + e.getMessage());
        }
    }
}
