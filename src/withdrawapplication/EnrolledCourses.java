package withdrawapplication;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class EnrolledCourses {

    static Stack<JFrame> frameStack = new Stack<>();

    public static void enrolledCourses(LinkedList<Course> courses) {
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

        JLabel heading = new JLabel("ENROLLED COURSE(S)");
        heading.setBounds(0, 0, 870, 90);
        heading.setForeground(new Color(29, 39, 46));
        heading.setFont(new Font("Garamond", Font.BOLD, 42));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel absences = new JLabel("Absences");
        absences.setBounds(550, 130, 100, 30);
        absences.setForeground(new Color(29, 39, 46));
        absences.setFont(new Font("Garamond", Font.BOLD, 20));
        absences.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel presences = new JLabel("Presences");
        presences.setBounds(681, 130, 100, 30);
        presences.setForeground(new Color(29, 39, 46));
        presences.setFont(new Font("Garamond", Font.BOLD, 20));
        presences.setHorizontalAlignment(SwingConstants.LEFT);
        JLabel course = new JLabel("Course");
        course.setBounds(70, 130, 100, 30);
        course.setForeground(new Color(29, 39, 46));
        course.setFont(new Font("Garamond", Font.BOLD, 20));
        course.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(course);
        JButton back = new JButton("Back");
        back.setBounds(0, 0, 70, 25);
        back.setForeground(new Color(29, 39, 46));
        back.setFont(new Font("Garamond", Font.BOLD, 14));
        back.setBackground(new Color(210, 220, 227));
        back.setBorder(new BevelBorder(BevelBorder.RAISED));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.setVisible(false);
                while (!frameStack.isEmpty()) {
                    frameStack.pop();
                }
                WithdrawApplication.frameStack.pop().setVisible(true);

            }

        });

        JButton viewAll = new JButton("View unenrolled courses");
        viewAll.setBounds(681, 100, 170, 25);
        viewAll.setForeground(new Color(29, 39, 46));
        viewAll.setFont(new Font("Garamond", Font.BOLD, 14));
        viewAll.setBackground(new Color(210, 220, 227));
        viewAll.setBorder(new BevelBorder(BevelBorder.RAISED));
        viewAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frameStack.push(frame);
                frame.setVisible(false);
                UnenrolledCourses.unenrolledCourses(courses);

            }
        });

        printCourses(panel, courses);
        printAbsences(panel, courses);
        printPresences(panel, courses);
        panel2.add(heading);
        panel.add(panel2);
        panel.add(absences);
        panel.add(presences);
        panel.add(viewAll);
        frame.add(back);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static void printAbsences(JPanel panel, LinkedList<Course> courses) {

        int yPosition = 160;
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (course.getStatus().equals("Enrolled")) {
                JLabel absenceLabel = new JLabel(course.getAbsences() + " ");
                absenceLabel.setBounds(520, yPosition, 130, 30);
                absenceLabel.setFont(new Font("Garamond", Font.BOLD, 25));
                absenceLabel.setHorizontalAlignment(SwingConstants.CENTER);

                absenceLabel.setBackground(new Color(251, 198, 201));
                absenceLabel.setOpaque(true);
                absenceLabel.setForeground(new Color(29, 39, 46));
                panel.add(absenceLabel);

                yPosition += 40;
            }
        }

    }

    static void printPresences(JPanel panel, LinkedList<Course> courses) {

        int yPosition = 160;
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (course.getStatus().equals("Enrolled")) {
                JLabel presenceLabel = new JLabel(course.getPresences() + " ");
                presenceLabel.setBounds(650, yPosition, 130, 30);
                presenceLabel.setFont(new Font("Garamond", Font.BOLD, 25));
                presenceLabel.setHorizontalAlignment(SwingConstants.CENTER);

                presenceLabel.setBackground(new Color(213, 249, 214));
                presenceLabel.setOpaque(true);
                presenceLabel.setForeground(new Color(29, 39, 46));
                panel.add(presenceLabel);

                yPosition += 40;
            }
        }

    }

    static void printCourses(JPanel panel, LinkedList<Course> courses) {
        int yPosition = 160;
        int courseIndex = 0;
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (course.getStatus().equals("Enrolled")) {
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
    }

}
