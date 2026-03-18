package withdrawapplication;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class StudentDashboard {

    public static void dashboard(ResultSet studentData) {
        Student student = new Student(studentData);
      
        JFrame dashboard = new JFrame("Student Dashboard");
        dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dashboard.setLayout(null);
        dashboard.getContentPane().setBackground(new Color(29, 39, 46));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100, 60, 800, 600);
        panel.setBackground(new Color(29, 39, 46));

        JLabel label = new JLabel("<html>Hello!<br>Welcome to<br>Dashboard.</html>");
        label.setBounds(950, 450, 400, 200);
        label.setFont(new Font("Garamond", Font.BOLD, 60));
        label.setForeground(new Color(29, 39, 46));

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(900, 0, 458, 776);
        panel2.setBackground(new Color(210, 220, 227));

        ImageIcon image = new ImageIcon("D:\\Ridajava\\GUI\\profile.png");
        JLabel iconLabel = new JLabel(image);
        iconLabel.setBounds(150, 70, image.getIconWidth(), image.getIconHeight());
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel name = new JLabel("Name: " + student.getName());
        name.setBounds(0, 180, 470, 200);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setFont(new Font("Garamond", Font.BOLD, 20));
        name.setForeground(new Color(29, 39, 46));
        JLabel ID = new JLabel("Student ID: " + student.getStudent_id());
        ID.setBounds(0, 210, 470, 200);
        ID.setHorizontalAlignment(SwingConstants.CENTER);
        ID.setFont(new Font("Garamond", Font.BOLD, 20));
        ID.setForeground(new Color(29, 39, 46));
        JLabel Sem = new JLabel("Semester: " + student.getSemester());
        Sem.setBounds(0, 240, 470, 200);
        Sem.setHorizontalAlignment(SwingConstants.CENTER);
        Sem.setFont(new Font("Garamond", Font.BOLD, 20));
        Sem.setForeground(new Color(29, 39, 46));

        JLabel Dep = new JLabel("Department: " + student.getDepartment());
        Dep.setBounds(0, 270, 470, 200);
        Dep.setHorizontalAlignment(SwingConstants.CENTER);
        Dep.setFont(new Font("Garamond", Font.BOLD, 20));
        Dep.setForeground(new Color(29, 39, 46));

        JButton viewCourses = new JButton("View Courses");
        viewCourses.setHorizontalTextPosition(SwingConstants.CENTER);
        viewCourses.setVerticalTextPosition(SwingConstants.BOTTOM);
        viewCourses.setBounds(70, 50, 200, 250);
        viewCourses.setForeground(new Color(29, 39, 46));
        viewCourses.setBackground(new Color(227, 234, 238));
        viewCourses.setBorder(new BevelBorder(BevelBorder.RAISED));
        viewCourses.setFont(new Font("Garamond", Font.BOLD, 21));
        ImageIcon icon = new ImageIcon("D:\\Ridajava\\GUI\\view.png");
        viewCourses.setIcon(icon);
        viewCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(dashboard);
                EnrolledCourses.enrolledCourses(student.getCourses());
                dashboard.setVisible(false);

            }
        });

        JButton withdraw = new JButton("Withdraw Course");
        withdraw.setHorizontalTextPosition(SwingConstants.CENTER);
        withdraw.setVerticalTextPosition(SwingConstants.BOTTOM);
        withdraw.setBounds(280, 50, 200, 250);
        withdraw.setForeground(new Color(29, 39, 46));
        withdraw.setBackground(new Color(227, 234, 238));
        withdraw.setBorder(new BevelBorder(BevelBorder.RAISED));
        withdraw.setFont(new Font("Garamond", Font.BOLD, 20));
        ImageIcon icon2 = new ImageIcon("D:\\Ridajava\\GUI\\withdraw.png");
        withdraw.setIcon(icon2);
        withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(dashboard);
                WithdrawCourse.withdrawCourse(student);
                dashboard.setVisible(false);

            }
        });

        JButton appStatus = new JButton("Track Application");
        appStatus.setHorizontalTextPosition(SwingConstants.CENTER);
        appStatus.setVerticalTextPosition(SwingConstants.BOTTOM);
        appStatus.setBounds(490, 50, 200, 250);
        appStatus.setForeground(new Color(29, 39, 46));
        appStatus.setBackground(new Color(227, 234, 238));
        appStatus.setBorder(new BevelBorder(BevelBorder.RAISED));
        appStatus.setFont(new Font("Garamond", Font.BOLD, 20));
        ImageIcon icon3 = new ImageIcon("D:\\Ridajava\\GUI\\tracking.png");
        appStatus.setIcon(icon3);
        appStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(dashboard);
                TrackApplications.trackApplications(student);
                dashboard.setVisible(false);

            }
        });

        JButton deadline = new JButton("View Deadline");
        deadline.setHorizontalTextPosition(SwingConstants.CENTER);
        deadline.setVerticalTextPosition(SwingConstants.BOTTOM);
        deadline.setBounds(375, 310, 200, 250);
        deadline.setForeground(new Color(29, 39, 46));
        deadline.setBackground(new Color(227, 234, 238));
        deadline.setBorder(new BevelBorder(BevelBorder.RAISED));
        deadline.setFont(new Font("Garamond", Font.BOLD, 20));
        ImageIcon icon5 = new ImageIcon("D:\\Ridajava\\GUI\\deadline.png");
        deadline.setIcon(icon5);
        deadline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(dashboard);
                ViewDeadline.viewDeadline();
                dashboard.setVisible(false);

            }
        });
        JButton policies = new JButton("View Policies");
        policies.setVerticalTextPosition(SwingConstants.BOTTOM);
        policies.setHorizontalTextPosition(SwingConstants.CENTER);
        policies.setBounds(165, 310, 200, 250);
        policies.setForeground(new Color(29, 39, 46));
        policies.setBackground(new Color(227, 234, 238));
        policies.setBorder(new BevelBorder(BevelBorder.RAISED));
        policies.setFont(new Font("Garamond", Font.BOLD, 21));
        ImageIcon icon4 = new ImageIcon("D:\\Ridajava\\GUI\\policies.png");
        policies.setIcon(icon4);

        policies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WithdrawApplication.frameStack.push(dashboard);
                dashboard.setVisible(false);
                Policies.policies();
            }
        });
        JButton signOut = new JButton("Sign Out");
        signOut.setBounds(0, 0, 80, 30);
        signOut.setForeground(new Color(29, 39, 46));
        signOut.setBackground(new Color(227, 234, 238));
        signOut.setBorder(new BevelBorder(BevelBorder.RAISED));
        signOut.setFont(new Font("Garamond", Font.BOLD, 16));
        signOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?", "Confirm Sign Out", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    WithdrawApplication.frameStack.pop().setVisible(true);
                    dashboard.setVisible(false);
                }
            }
        });
        panel.add(viewCourses);
        panel.add(withdraw);
        panel.add(appStatus);
        panel.add(policies);
        panel.add(deadline);
        dashboard.add(label);
        dashboard.add(panel);
        panel2.add(iconLabel);
        panel2.add(name);
        panel2.add(Sem);
        panel2.add(ID);
        panel2.add(Dep);
        dashboard.add(signOut);

        dashboard.add(panel2);
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setVisible(true);

    }

   
}
