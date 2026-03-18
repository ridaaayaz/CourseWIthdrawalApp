package withdrawapplication;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnenrolledCourses {

    public static void unenrolledCourses(LinkedList<Course> courses) {
        JFrame frame = new JFrame("Unenrolled Courses");
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

        JLabel heading = new JLabel("UNENROLLED COURSE(S)");
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
                while (!EnrolledCourses.frameStack.isEmpty()) {
                    EnrolledCourses.frameStack.pop();
                }

                WithdrawApplication.frameStack.pop().setVisible(true);

            }

        });

        JButton viewAll = new JButton("View enrolled courses");
        viewAll.setBounds(681, 100, 170, 25);
        viewAll.setForeground(new Color(29, 39, 46));
        viewAll.setFont(new Font("Garamond", Font.BOLD, 14));
        viewAll.setBackground(new Color(210, 220, 227));
        viewAll.setBorder(new BevelBorder(BevelBorder.RAISED));
        viewAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                EnrolledCourses.frameStack.pop().setVisible(true);
                frame.setVisible(false);

            }
        });

        printCourses(panel, courses);
        printStatus(panel, courses);

        panel2.add(heading);
        panel.add(panel2);
        panel.add(viewAll);
        frame.add(back);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static void printStatus(JPanel panel, LinkedList<Course> courses) {

        int yPosition = 160;
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (!course.getStatus().equals("Enrolled")) {
                JLabel statusLabel = new JLabel(course.getStatus() + " ");
                statusLabel.setBounds(520, yPosition, 230, 30);
                statusLabel.setFont(new Font("Garamond", Font.BOLD, 22));
                statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

                statusLabel.setBackground(new Color(251, 198, 201));
                statusLabel.setOpaque(true);
                statusLabel.setForeground(new Color(29, 39, 46));
                panel.add(statusLabel);

                yPosition += 40;
            }
        }

    }

    static void printCourses(JPanel panel, LinkedList<Course> courses) {
        int yPosition = 160;
        int courseIndex = 0;
        Boolean empty = true;
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (!course.getStatus().equals("Enrolled")) {
                empty = false;
                JLabel courseLabel = new JLabel((courseIndex + 1) + ". " + course.getCourse_name());

                courseLabel.setBounds(70, yPosition, 450, 30);
                courseLabel.setFont(new Font("Garamond", Font.BOLD, 22));
                courseLabel.setHorizontalAlignment(SwingConstants.LEFT);

                courseLabel.setBackground(new Color(210, 220, 227));
                courseLabel.setOpaque(true);
                courseLabel.setForeground(new Color(29, 39, 46));
                panel.add(courseLabel);

                yPosition += 40;
                courseIndex++;
            }
        }
        if (empty) {
            JLabel message = new JLabel("No records to display!");
            message.setBounds(0, 300, 870, 30);
            message.setFont(new Font("Garamond", Font.BOLD, 22));
            message.setHorizontalAlignment(SwingConstants.CENTER);
            message.setForeground(new Color(29, 39, 46));
            panel.add(message);
            return;
        }
        JLabel status = new JLabel("Status");
        status.setBounds(570, 130, 100, 30);
        status.setForeground(new Color(29, 39, 46));
        status.setFont(new Font("Garamond", Font.BOLD, 20));
        status.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(status);
        JLabel course = new JLabel("Course");
        course.setBounds(70, 130, 100, 30);
        course.setForeground(new Color(29, 39, 46));
        course.setFont(new Font("Garamond", Font.BOLD, 20));
        course.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(course);
    }

}
