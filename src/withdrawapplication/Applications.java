package withdrawapplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;
import javax.swing.table.*;


public class Applications {

    public static void view(int Sem) {

        updateQueues();
        JFrame frame = new JFrame("Applications");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(29, 39, 46));

        JPanel panel = new JPanel();
        panel.setBounds(150, 55, 1100, 610);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 0, 1100, 90);
        panel2.setLayout(null);
        panel2.setBackground(new Color(210, 220, 227));

        JLabel heading = new JLabel("Applications Received");
        heading.setBounds(0, 0, 1100, 90);
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

        String[] columnNames = {"Request ID", "Name", "ID", "Department", "Semester", "Course", "Reason", "Status"};
        Vector<Vector<Object>> rowData = new Vector<>();
        DefaultTableModel tableModel = new DefaultTableModel(rowData, new Vector<>(List.of(columnNames)));

        while (!WithdrawApplication.requestQueues.get(Sem - 1).isEmpty()) {
            Vector<Object> row = new Vector<>();
            Request req = WithdrawApplication.requestQueues.get(Sem - 1).poll();
            row.add(String.valueOf(req.getRequestID()));
            row.add(req.getStudentName());
            row.add(req.getStudentID());
            row.add(req.getStudentDep());
            row.add(String.valueOf(req.getStudentSemester()));
            row.add(req.getCourseToWithdraw());
            row.add(req.getReason());
            row.add(req.getRequestStatus());

            rowData.add(row);
        }

        JTable table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        table.setBounds(0, 100, 1100, 400);
        table.setRowHeight(30);
        table.setFont(new Font("Garamond", Font.BOLD, 15));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(5).setPreferredWidth(220);

        columnModel.getColumn(3).setPreferredWidth(20);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 1100, 400);

        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                    int selectedRow = table.getSelectedRow();
                    String studentName = (String) table.getValueAt(selectedRow, 1);
                    String studentId = (String) table.getValueAt(selectedRow, 2);
                    String courseToWithdraw = (String) table.getValueAt(selectedRow, 5);

                    int reqID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

                    Object[] options = {"Accept", "Reject"};

                    int response = JOptionPane.showOptionDialog(
                            frame,
                            "Do you want to accept or reject the withdrawal request for " + studentName + " (" + studentId + ") for the course " + courseToWithdraw + "?",
                            "Confirm Request",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );
                    if (response == 0) {
                        if (updateRequestStatus(reqID, "Accepted")) {
                            JOptionPane.showMessageDialog(frame, "Request Accepted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            updateEnrollment(studentId, courseToWithdraw);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed to accept request.", "Failure", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (response == 1) {
                        if (updateRequestStatus(reqID, "Rejected")) {
                            JOptionPane.showMessageDialog(frame, "Request Rejected Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed to reject request.", "Failure", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        panel.add(scrollPane);
        panel2.add(heading);
        panel.add(panel2);
        frame.add(panel);
        frame.add(back);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private static boolean updateRequestStatus(int reqID, String status) {
        String query = "UPDATE withdrawalrequests SET request_status = ? WHERE request_number = ?";
        try {

            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, reqID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException ae) {
            System.out.println(ae.getMessage() + "edw");

        }
        return false;

    }

    private static boolean updateEnrollment(String studentID, String course) {
        int courseID = Course.getCourseID(course);
        System.out.println(courseID);
        String query = "UPDATE enrollments SET status = ? WHERE student_id= ? and course_id = ?";
        try {

            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setString(1, "Withdrawn");
            preparedStatement.setString(2, studentID);
            preparedStatement.setInt(3, courseID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {

                return true;
            }
        } catch (SQLException ae) {
            System.out.println(ae.getMessage() + "edw");

        }
        return false;
    }

    private static void updateQueues() {
        String query = "Select * from withdrawalrequests where request_status=?";
        try {

            PreparedStatement statement = WithdrawApplication.connect.prepareStatement(query);
            statement.setString(1, "Pending");

            ResultSet data = statement.executeQuery();
            while (data.next()) {
                String studentName = data.getString("student_name");
                String studentId = data.getString("student_id");
                String studentDep = data.getString("student_dep");
                String courseToWithdraw = data.getString("course_to_withdraw");
                String reason = data.getString("reason");
                String requestStatus = data.getString("request_status");
                int semester = data.getInt("semester");
                int requestNumber = data.getInt("request_number");

                Request req = new Request(requestNumber, studentId, studentDep, semester, studentName, courseToWithdraw, reason, requestStatus);
                WithdrawApplication.requestQueues.get(semester - 1).add(req);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
