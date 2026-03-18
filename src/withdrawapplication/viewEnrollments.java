package withdrawapplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import static withdrawapplication.EnrolledCourses.frameStack;

public class viewEnrollments {

    public static void view(Student student) {

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

        JLabel heading = new JLabel("Enrolled courses");
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

        String[] columnNames = {"Student ID", "Student Name", "Course ID", "Course Name", "Status", "Total Absences", "Total Presences"};
        Vector<Vector<Object>> rowData = new Vector<>();
        DefaultTableModel tableModel = new DefaultTableModel(rowData, new Vector<>(List.of(columnNames)));

        int i = 0;
        LinkedList<Course> courses = new LinkedList<Course>();
        courses = student.getCourses();
        for (int j = 0; j < student.getCourses().size(); j++) {
            Vector<Object> row = new Vector<>();
            row.add(student.getStudent_id());
            row.add(student.getName());
            row.add(courses.get(i).getCourse_id());
            row.add(courses.get(i).getCourse_name());
            row.add(courses.get(i).getStatus());
            row.add(courses.get(i).getAbsences());
            row.add(courses.get(i).getPresences());
            rowData.add(row);
            i++;
        }

        JTable table = new JTable(tableModel);
        table.setBounds(0, 100, 870, 500);
        table.setRowHeight(30);
        table.setFont(new Font("Garamond", Font.BOLD, 17));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(2).setPreferredWidth(30);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(40);
        columnModel.getColumn(5).setPreferredWidth(40);
        columnModel.getColumn(6).setPreferredWidth(40);
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
