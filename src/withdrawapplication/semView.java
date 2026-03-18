package withdrawapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class semView {

    public static void view() {
        JFrame frame = new JFrame("Deadline");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(29, 39, 46));

        JPanel panel = new JPanel();
        panel.setBounds(230, 50, 870, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(29, 39, 46));

        JLabel heading = new JLabel("Please choose a semester: ");
        heading.setBounds(0, 0, 870, 90);
        heading.setForeground(new Color(210, 220, 227));
        heading.setFont(new Font("Garamond", Font.BOLD, 42));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JButton sem1 = new JButton("Semester 1");
        sem1.setBounds(35, 80, 200, 240);
        sem1.setForeground(new Color(29, 39, 46));
        sem1.setFont(new Font("Garamond", Font.BOLD, 35));
        sem1.setBackground(new Color(210, 220, 227));
        sem1.setBorder(new BevelBorder(BevelBorder.RAISED));
        sem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(frame);
                frame.setVisible(false);
                Applications.view(1);

            }

        });

        JButton sem2 = new JButton("Semester 2");
        sem2.setBounds(245, 80, 200, 240);
        sem2.setForeground(new Color(29, 39, 46));
        sem2.setFont(new Font("Garamond", Font.BOLD, 35));
        sem2.setBackground(new Color(210, 220, 227));
        sem2.setBorder(new BevelBorder(BevelBorder.RAISED));
        sem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(frame);
                frame.setVisible(false);
                Applications.view(2);

            }

        });

        JButton sem3 = new JButton("Semester 3");
        sem3.setBounds(455, 80, 200, 240);
        sem3.setForeground(new Color(29, 39, 46));
        sem3.setFont(new Font("Garamond", Font.BOLD, 35));
        sem3.setBackground(new Color(210, 220, 227));
        sem3.setBorder(new BevelBorder(BevelBorder.RAISED));
        sem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(frame);
                frame.setVisible(false);
                Applications.view(3);

            }

        });

        JButton sem4 = new JButton("Semester 4");
        sem4.setBounds(665, 80, 200, 240);
        sem4.setForeground(new Color(29, 39, 46));
        sem4.setFont(new Font("Garamond", Font.BOLD, 35));
        sem4.setBackground(new Color(210, 220, 227));
        sem4.setBorder(new BevelBorder(BevelBorder.RAISED));
        sem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(frame);
                frame.setVisible(false);
                Applications.view(4);

            }

        });

        JButton sem5 = new JButton("Semester 5");
        sem5.setBounds(35, 330, 200, 240);
        sem5.setForeground(new Color(29, 39, 46));
        sem5.setFont(new Font("Garamond", Font.BOLD, 35));
        sem5.setBackground(new Color(210, 220, 227));
        sem5.setBorder(new BevelBorder(BevelBorder.RAISED));
        sem5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                WithdrawApplication.frameStack.push(frame);
                frame.setVisible(false);
                Applications.view(5);
            }

        });

        JButton sem6 = new JButton("Semester 6");
        sem6.setBounds(245, 330, 200, 240);
        sem6.setForeground(new Color(29, 39, 46));
        sem6.setFont(new Font("Garamond", Font.BOLD, 35));
        sem6.setBackground(new Color(210, 220, 227));
        sem6.setBorder(new BevelBorder(BevelBorder.RAISED));
        sem6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(frame);
                frame.setVisible(false);
                Applications.view(6);

            }

        });

        JButton sem7 = new JButton("Semester 7");
        sem7.setBounds(455, 330, 200, 240);
        sem7.setForeground(new Color(29, 39, 46));
        sem7.setFont(new Font("Garamond", Font.BOLD, 35));
        sem7.setBackground(new Color(210, 220, 227));
        sem7.setBorder(new BevelBorder(BevelBorder.RAISED));
        sem7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(frame);
                frame.setVisible(false);
                Applications.view(7);

            }

        });

        JButton sem8 = new JButton("Semester 8");
        sem8.setBounds(665, 330, 200, 240);
        sem8.setForeground(new Color(29, 39, 46));
        sem8.setFont(new Font("Garamond", Font.BOLD, 35));
        sem8.setBackground(new Color(210, 220, 227));
        sem8.setBorder(new BevelBorder(BevelBorder.RAISED));
        sem8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(frame);
                frame.setVisible(false);
                Applications.view(8);

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

        panel.add(sem1);
        panel.add(sem2);
        panel.add(sem3);
        panel.add(sem4);
        panel.add(sem5);
        panel.add(sem6);
        panel.add(sem7);
        panel.add(sem8);
        panel.add(heading);
        frame.add(back);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
