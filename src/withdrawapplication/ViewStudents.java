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
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import static withdrawapplication.EnrolledCourses.frameStack;

public class ViewStudents {

    public static void viewStudents(int Sem) {

        JFrame frame = new JFrame("Students");
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

        JLabel heading = new JLabel("Students of Semester " + Sem);
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
        JButton viewCourses = new JButton("View Enrollments");
        viewCourses.setBounds(670, 110, 170, 26);
        viewCourses.setForeground(new Color(29, 39, 46));
        viewCourses.setFont(new Font("Garamond", Font.BOLD, 14));
        viewCourses.setBackground(new Color(228, 227, 238));

        viewCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String userInput = JOptionPane.showInputDialog(null, "Enter student ID whose enrollments you want to view:", "Student ID", JOptionPane.PLAIN_MESSAGE);
                if (userInput == null || userInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No ID provided!Try again!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String savedInput = userInput;
                    String query = "select * from students where student_id=?";
                    try {

                        PreparedStatement statement = WithdrawApplication.connect.prepareStatement(query);
                        statement.setString(1, savedInput);

                        ResultSet data = statement.executeQuery();
                        if (data.next() && data.getInt("current_semester") == Sem) {

                            WithdrawApplication.frameStack.push(frame);

                            frame.setVisible(false);
                            Student student = new Student(data);
                            viewEnrollments.view(student);

                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect ID! Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
            }
        });
        panel.add(viewCourses);

        String[] columnNames = {"ID", "Name", "Email", "Department", "Semester", "CGPA "};
        Vector<Vector<Object>> rowData = new Vector<>();
        DefaultTableModel tableModel = new DefaultTableModel(rowData, new Vector<>(List.of(columnNames)));

        try {
            String query = "SELECT * FROM students WHERE current_semester = ?";

            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setInt(1, Sem);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("student_id"));
                row.add(resultSet.getString("name"));
                row.add(resultSet.getString("email"));
                row.add(resultSet.getString("department"));
                row.add(resultSet.getString("current_semester"));
                row.add(resultSet.getDouble("cgpa"));

                rowData.add(row);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        JTable table = new JTable(tableModel);
        table.setBounds(0, 150, 875, 550);
        table.setRowHeight(30);
        table.setFont(new Font("Garamond", Font.BOLD, 15));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(2).setPreferredWidth(220);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(20);
        columnModel.getColumn(5).setPreferredWidth(20);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 150, 875, 550);

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
