package withdrawapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JFrame;

public class WithdrawApplication {

    static String URL = "jdbc:mysql://localhost:3306/studentsdata";
    static String username = "root";
    static String pass = "Hadiqa1@";
    static Connection connect;
    static Stack<JFrame> frameStack = new Stack<>();
    public static ArrayList<Queue<Request>> requestQueues = new ArrayList<>();

    public static void main(String[] args) {

        LoginPage.loginPage();
       
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connect = DriverManager.getConnection(URL, username, pass);
            System.out.println("Connection established!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < 8; i++) {
            requestQueues.add(new LinkedList<>());
        }
    }
}
