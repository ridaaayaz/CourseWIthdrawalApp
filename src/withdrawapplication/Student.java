package withdrawapplication;

import java.util.List;
import java.util.LinkedList;
import java.sql.*;
import java.sql.PreparedStatement;

public class Student {

    private String student_id;
    private String name;
    private String gmail;
    private String password;
    private double cgpa;
    private int semester;
    private String department;
    private LinkedList<Course> courses = new LinkedList<Course>();
    private int withdrawsAllowed;
    private int withdrawApplicationsSubmitted;

    public Student(String student_id, String name, String gmail, String password, double cgpa, int semester, String department, LinkedList<Course> courses) {
        this.student_id = student_id;
        this.name = name;
        this.gmail = gmail;
        this.password = password;
        this.cgpa = cgpa;
        this.semester = semester;
        this.department = department;
        this.courses = new LinkedList<Course>();

    }

    public Student(ResultSet resultSet) {
        try {
            this.student_id = resultSet.getString("student_id");
            this.name = resultSet.getString("name");
            this.gmail = resultSet.getString("email");
            this.password = resultSet.getString("password");
            this.cgpa = resultSet.getDouble("cgpa");
            this.semester = resultSet.getInt("current_semester");
            this.department = resultSet.getString("department");

            String query = "SELECT course_id FROM enrollments WHERE student_id = ?";
            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setString(1, student_id);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                int courseID = data.getInt("course_id");
                Course course = getCourse(courseID, student_id);
                courses.add(course);
            }
            this.withdrawApplicationsSubmitted = withdrawApplicationsCount();

            this.withdrawsAllowed = countWithdraws() - withdrawApplicationsCount();
            if (this.withdrawsAllowed < 0) {
                this.withdrawsAllowed = 0;
            }

        } catch (Exception e) {
        }

    }

    public String getStudent_id() {
        return student_id;
    }

    public String getName() {
        return name;
    }

    public String getGmail() {
        return gmail;
    }

    public String getPassword() {
        return password;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getSemester() {
        return semester;
    }

    public String getDepartment() {
        return department;
    }

    public LinkedList<Course> getCourses() {
        return courses;
    }

    public Course getCourse(int courseID, String studentID) {
        String statusCourse = "";
        String courseName = "";
        int prerequisiteId = 0;
        int totalAbsences = 0;
        int totalPresences = 0;
        try {
            String query = "SELECT course_name, prerequisite_course_id FROM courses WHERE course_id = ?";

            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setInt(1, courseID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                courseName = resultSet.getString("course_name");
                prerequisiteId = resultSet.getInt("prerequisite_course_id");

            }

            query = "SELECT status, total_absences, total_presences " + "FROM enrollments WHERE course_id = ? AND student_id = ?";
            preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setInt(1, courseID);
            preparedStatement.setString(2, studentID);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                statusCourse = resultSet.getString("status");
                totalAbsences = resultSet.getInt("total_absences");
                totalPresences = resultSet.getInt("total_presences");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        Course course = new Course(courseID, courseName, statusCourse, prerequisiteId, totalAbsences, totalPresences);
        return course;

    }

    public int getWithdrawsAllowed() {
        return withdrawsAllowed;
    }

    public int countWithdraws() {
        int count = 0;
        if (cgpa >= 2.2 && cgpa <= 2.5) {
            count = 2;
        } else {
            count = 1;
        }

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getStatus().equals("Withdrawn")) {
                count--;
            }
            if (count == 0) {
                break;
            }
        }
        return count;
    }

    public void updateWithdraws() {
        withdrawsAllowed--;
        withdrawApplicationsSubmitted--;

    }

    public int withdrawApplicationsCount() {
        int count = 0;
        try {
            String query = "SELECT request_status FROM withdrawalrequests WHERE student_id = ?";

            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setString(1, student_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String status = resultSet.getString("request_status");
                if (status.equals("Pending")) {
                    count++;
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return count;

    }

}
