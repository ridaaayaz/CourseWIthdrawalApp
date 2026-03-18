package withdrawapplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import static withdrawapplication.EnrolledCourses.frameStack;

public class TrackApplications {

    public static void trackApplications(Student student) {

        JFrame frame = new JFrame("Enrolled Courses");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(29, 39, 46));

        JPanel panel = new JPanel();
        panel.setBounds(230, 55, 870, 610);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 0, 870, 90);
        panel2.setLayout(null);
        panel2.setBackground(new Color(210, 220, 227));

        JLabel heading = new JLabel("Applications Submitted");
        heading.setBounds(0, 0, 870, 90);
        heading.setForeground(new Color(29, 39, 46));
        heading.setFont(new Font("Garamond", Font.BOLD, 42));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JButton back = new JButton("Back");
        back.setBounds(0, 0, 70, 25);
        back.setForeground(new Color(29, 39, 46));
        back.setFont(new Font("Garamond", Font.BOLD, 14));
        back.setBackground(new Color(210, 220, 227));
        back.setBorder(new BevelBorder(BevelBorder.RAISED));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.setVisible(false);

                WithdrawApplication.frameStack.pop().setVisible(true);

            }

        });

        String[] columnNames = {"Name", "ID", "Department", "Course", "Reason", "Status"};
        Vector<Vector<Object>> rowData = new Vector<>();
        DefaultTableModel tableModel = new DefaultTableModel(rowData, new Vector<>(List.of(columnNames)));

        try {
            String query = "SELECT student_name, student_id, student_dep, course_to_withdraw, reason, request_status FROM withdrawalrequests WHERE student_id = ?";

            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setString(1, student.getStudent_id());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("student_name"));
                row.add(resultSet.getString("student_id"));
                row.add(resultSet.getString("student_dep"));
                row.add(resultSet.getString("course_to_withdraw"));
                row.add(resultSet.getString("reason"));
                row.add(resultSet.getString("request_status"));

                rowData.add(row);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        JTable table = new JTable(tableModel);
        table.setBounds(0, 100, 870, 500);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 870, 500);

        table.setFillsViewportHeight(true);

        panel.add(scrollPane);
        panel2.add(heading);
        panel.add(panel2);
        frame.add(panel);
        frame.add(back);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
