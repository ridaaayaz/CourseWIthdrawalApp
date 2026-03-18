package withdrawapplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.*;

public class Course {

    private int course_id;
    private String course_name;
    private String status;
    private int prerequisite_course_id;
    private int total_absences;
    private int total_presences;

    public Course(int course_id, String course_name, String status, int prerequisite_course_id, int total_absences, int total_presences) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.status = status;
        this.prerequisite_course_id = prerequisite_course_id;
        this.total_absences = total_absences;
        this.total_presences = total_presences;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public int getPrerequisite_course_id() {
        return prerequisite_course_id;
    }

    public int getAbsences() {
        return total_absences;
    }

    public int getPresences() {
        return total_presences;
    }

    public String getStatus() {
        return status;
    }

    public static int getCourseID(String courseName) {
        int courseId = -1;
        String query = "SELECT course_id FROM courses WHERE course_name = ?";

        try {
            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                courseId = resultSet.getInt("course_id");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return courseId;
    }

}
