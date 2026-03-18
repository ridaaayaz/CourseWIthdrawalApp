package withdrawapplication;

public class Request {

    private int requestID;
    private String studentID;
    private String studentDep;
    private int studentSemester;
    private String studentName;
    private String courseToWithdraw;
    private String reason;
    private String requestStatus;

    public Request(int requestID, String studentID, String studentDep, int studentSemester, String studentName,
            String courseToWithdraw, String reason, String requestStatus) {
        this.requestID = requestID;
        this.studentID = studentID;
        this.studentDep = studentDep;
        this.studentSemester = studentSemester;
        this.studentName = studentName;
        this.courseToWithdraw = courseToWithdraw;
        this.reason = reason;
        this.requestStatus = requestStatus;
    }

    // Getters for the fields
    public int getRequestID() {
        return requestID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentDep() {
        return studentDep;
    }

    public int getStudentSemester() {
        return studentSemester;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseToWithdraw() {
        return courseToWithdraw;
    }

    public String getReason() {
        return reason;
    }

    public String getRequestStatus() {
        return requestStatus;
    }
}
