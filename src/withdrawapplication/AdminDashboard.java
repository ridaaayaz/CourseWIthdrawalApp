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

public class AdminDashboard {

    public static void dashboard() {

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

        JButton viewStudents = new JButton("View Student Details");
        viewStudents.setHorizontalTextPosition(SwingConstants.CENTER);
        viewStudents.setVerticalTextPosition(SwingConstants.BOTTOM);
        viewStudents.setBounds(140, 50, 200, 250);
        viewStudents.setForeground(new Color(29, 39, 46));
        viewStudents.setBackground(new Color(227, 234, 238));
        viewStudents.setBorder(new BevelBorder(BevelBorder.RAISED));
        viewStudents.setFont(new Font("Garamond", Font.BOLD, 21));
        ImageIcon icon = new ImageIcon("D:\\Ridajava\\GUI\\view.png");
        viewStudents.setIcon(icon);
        viewStudents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(dashboard);
                SemestersView.semestersView();
                dashboard.setVisible(false);

            }
        });

        JButton appStatus = new JButton("View Applications");
        appStatus.setHorizontalTextPosition(SwingConstants.CENTER);
        appStatus.setVerticalTextPosition(SwingConstants.BOTTOM);
        appStatus.setBounds(360, 50, 200, 250);
        appStatus.setForeground(new Color(29, 39, 46));
        appStatus.setBackground(new Color(227, 234, 238));
        appStatus.setBorder(new BevelBorder(BevelBorder.RAISED));
        appStatus.setFont(new Font("Garamond", Font.BOLD, 20));
        ImageIcon icon3 = new ImageIcon("D:\\Ridajava\\GUI\\tracking.png");
        appStatus.setIcon(icon3);
        appStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(dashboard);
                semView.view();
                dashboard.setVisible(false);

            }
        });

        JButton deadline = new JButton("Edit Deadline");
        deadline.setHorizontalTextPosition(SwingConstants.CENTER);
        deadline.setVerticalTextPosition(SwingConstants.BOTTOM);
        deadline.setBounds(140, 310, 200, 250);
        deadline.setForeground(new Color(29, 39, 46));
        deadline.setBackground(new Color(227, 234, 238));
        deadline.setBorder(new BevelBorder(BevelBorder.RAISED));
        deadline.setFont(new Font("Garamond", Font.BOLD, 20));
        ImageIcon icon5 = new ImageIcon("D:\\Ridajava\\GUI\\deadline.png");
        deadline.setIcon(icon5);
        deadline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                WithdrawApplication.frameStack.push(dashboard);
                EditDeadline.editDeadline();
                dashboard.setVisible(false);

            }
        });
        JButton policies = new JButton("Edit Policies");
        policies.setVerticalTextPosition(SwingConstants.BOTTOM);
        policies.setHorizontalTextPosition(SwingConstants.CENTER);
        policies.setBounds(360, 310, 200, 250);
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
                EditPolicies.view();
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

        panel.add(appStatus);
        panel.add(policies);
        panel.add(deadline);
        panel.add(viewStudents);
        dashboard.add(label);
        dashboard.add(panel);
        panel2.add(iconLabel);

        dashboard.add(signOut);

        dashboard.add(panel2);
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setVisible(true);

    }

}