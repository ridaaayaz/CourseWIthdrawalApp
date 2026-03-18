package withdrawapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.border.BevelBorder;


public class WithdrawCourse {

    public static void withdrawCourse(Student student) {

        JFrame frame = new JFrame("Withdraw Course");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(29, 39, 46));

        JPanel panel = new JPanel();
        panel.setBounds(230, 55, 870, 610);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 0, 870, 50);
        panel2.setLayout(null);
        panel2.setBackground(new Color(210, 220, 227));

        JLabel heading = new JLabel("Withdrawal Form");
        heading.setBounds(0, 0, 870, 50);
        heading.setForeground(new Color(29, 39, 46));
        heading.setFont(new Font("Garamond", Font.BOLD, 30));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel withdrawCount = new JLabel("You can withdraw " + student.getWithdrawsAllowed() + " course(s)");
        withdrawCount.setBounds(0, 60, 870, 50);
        withdrawCount.setForeground(new Color(29, 39, 46));
        withdrawCount.setFont(new Font("Garamond", Font.BOLD, 20));
        withdrawCount.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(withdrawCount);

        JLabel Name = new JLabel("Full name: ");
        Name.setBounds(100, 100, 870, 50);
        Name.setForeground(new Color(29, 39, 46));
        Name.setFont(new Font("Garamond", Font.BOLD, 16));
        Name.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField nameField = new JTextField(student.getName());
        nameField.setBounds(260, 110, 190, 30);
        nameField.setFont(new Font("Garamond", Font.PLAIN, 16));
        nameField.setForeground(Color.BLACK);
        nameField.setBackground(Color.WHITE);
        nameField.setEditable(false);

        JLabel ID = new JLabel("Student ID: ");
        ID.setBounds(100, 150, 870, 50);
        ID.setForeground(new Color(29, 39, 46));
        ID.setFont(new Font("Garamond", Font.BOLD, 16));
        ID.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField IDField = new JTextField(student.getStudent_id());
        IDField.setBounds(260, 160, 190, 30);
        IDField.setFont(new Font("Garamond", Font.PLAIN, 16));
        IDField.setForeground(Color.BLACK);
        IDField.setBackground(Color.WHITE);
        IDField.setEditable(false);

        JLabel Dep = new JLabel("Department: ");
        Dep.setBounds(100, 200, 870, 50);
        Dep.setForeground(new Color(29, 39, 46));
        Dep.setFont(new Font("Garamond", Font.BOLD, 16));
        Dep.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField depField = new JTextField(student.getDepartment());
        depField.setBounds(260, 210, 190, 30);
        depField.setFont(new Font("Garamond", Font.PLAIN, 16));
        depField.setForeground(Color.BLACK);
        depField.setBackground(Color.WHITE);
        depField.setEditable(false);

        JLabel Sem = new JLabel("Semester: ");
        Sem.setBounds(100, 250, 870, 50);
        Sem.setForeground(new Color(29, 39, 46));
        Sem.setFont(new Font("Garamond", Font.BOLD, 16));
        Sem.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField semField = new JTextField("" + student.getSemester());
        semField.setBounds(260, 260, 190, 30);
        semField.setFont(new Font("Garamond", Font.PLAIN, 16));
        semField.setForeground(Color.BLACK);
        semField.setBackground(Color.WHITE);
        semField.setEditable(false);

        JLabel course = new JLabel("Select a course: ");
        course.setBounds(100, 300, 870, 50);
        course.setForeground(new Color(29, 39, 46));
        course.setFont(new Font("Garamond", Font.BOLD, 16));
        course.setHorizontalAlignment(SwingConstants.LEFT);

        LinkedList<Course> courses = student.getCourses();

        int enrolledCount = 0;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getStatus().equals("Enrolled")) {
                enrolledCount++;
            }
        }
        String courseOptions[] = new String[enrolledCount + 1];
        courseOptions[0] = " ";
        int index = 1;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getStatus().equals("Enrolled")) {
                courseOptions[index++] = courses.get(i).getCourse_name();
            }
        }
        JComboBox<String> dropdown = new JComboBox<>(courseOptions);
        dropdown.setBounds(260, 310, 190, 30);
        dropdown.setBackground(Color.WHITE);

        JLabel reason = new JLabel("Reason of withdrawal: ");
        reason.setBounds(100, 350, 870, 50);
        reason.setForeground(new Color(29, 39, 46));
        reason.setFont(new Font("Garamond", Font.BOLD, 16));
        reason.setHorizontalAlignment(SwingConstants.LEFT);
        String[] withdrawalReasons = {" ",
            "Personal health issues",
            "Family emergency",
            "Academic challenges",
            "Financial constraints",
            "Other"
        };
        JComboBox<String> dropdown2 = new JComboBox<>(withdrawalReasons);
        dropdown2.setBounds(260, 360, 190, 30);
        dropdown2.setBackground(Color.WHITE);

        JLabel otherLabel = new JLabel("If other, please specify below");
        otherLabel.setBounds(260, 378, 870, 50);
        otherLabel.setForeground(Color.darkGray);
        otherLabel.setFont(new Font("Garamond", Font.BOLD, 12));
        otherLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField other = new JTextField();
        other.setBounds(260, 410, 190, 70);
        other.setFont(new Font("Garamond", Font.PLAIN, 16));
        other.setForeground(Color.BLACK);
        other.setBackground(Color.WHITE);
        other.setEnabled(false);

        dropdown2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (dropdown2.getSelectedItem() != null && dropdown2.getSelectedItem().equals("Other")) {
                    other.setEnabled(true);
                } else {
                    other.setEnabled(false);
                }
            }
        });

        JButton submitButton = new JButton("Submit request");
        submitButton.setBounds(360, 490, 160, 35);
        submitButton.setFont(new Font("Garamond", Font.BOLD, 18));
        submitButton.setBackground(new Color(210, 220, 227));
        submitButton.setForeground(new Color(29, 39, 46));
        submitButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        submitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isEmpty() || IDField.getText().isEmpty() || depField.getText().isEmpty()
                        || semField.getText().isEmpty() || dropdown.getSelectedItem().equals(" ")) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields before submitting.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String selectedReason = (String) dropdown2.getSelectedItem();
                if (selectedReason.equals(" ") || selectedReason.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please select a reason for withdrawal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if ("Other".equals(selectedReason) && other.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please specify the reason if 'Other' is selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {

                    String query = "INSERT INTO withdrawalRequests (student_name, student_id, student_dep," + "course_to_withdraw, reason,semester) VALUES ( ?, ?, ?, ?, ?,?)";
                    PreparedStatement pst = WithdrawApplication.connect.prepareStatement(query);

                    pst.setString(1, nameField.getText());
                    pst.setString(2, IDField.getText());
                    pst.setString(3, depField.getText());
                    pst.setString(4, dropdown.getSelectedItem().toString());
                    pst.setString(5, dropdown2.getSelectedItem().toString());
                    pst.setString(6, semField.getText());

                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Your withdrawal request has been submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        student.updateWithdraws();

                    } else {
                        JOptionPane.showMessageDialog(frame, "Your withdrawal request cannot be submitted.", "No Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ee) {
                    ee.getMessage();
                    System.out.println("hoi");
                }
                dropdown.setSelectedIndex(0);
                dropdown2.setSelectedIndex(0);
                other.setText("");
                other.setEnabled(false);
            }
        });

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

        if (student.getWithdrawsAllowed() == 0) {
            nameField.setEnabled(false);
            IDField.setEnabled(false);
            depField.setEnabled(false);
            semField.setEnabled(false);
            dropdown.setEnabled(false);
            dropdown2.setEnabled(false);
            submitButton.setEnabled(false);

        }
        frame.add(back);
        panel.add(submitButton);
        panel.add(dropdown);
        panel.add(dropdown2);
        panel.add(other);
        panel.add(otherLabel);
        panel2.add(heading);
        panel.add(panel2);
        panel.add(Name);
        panel.add(ID);
        panel.add(Dep);
        panel.add(Sem);
        panel.add(course);
        panel.add(reason);
        panel.add(nameField);
        panel.add(IDField);
        panel.add(depField);
        panel.add(semField);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
