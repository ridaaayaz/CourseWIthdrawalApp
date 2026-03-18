/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package withdrawapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import static withdrawapplication.EnrolledCourses.frameStack;

public class ViewDeadline {

    public static void viewDeadline() {
        JFrame frame = new JFrame("Deadline");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(29, 39, 46));

        JPanel panel = new JPanel();
        panel.setBounds(230, 190, 870, 300);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 0, 870, 90);
        panel2.setLayout(null);
        panel2.setBackground(new Color(210, 220, 227));

        JLabel heading = new JLabel("Deadline for withdrawal!");
        heading.setBounds(0, 0, 870, 90);
        heading.setForeground(new Color(29, 39, 46));
        heading.setFont(new Font("Garamond", Font.BOLD, 42));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel heading2 = new JLabel("Deadline for withdrawal is : ");
        heading2.setBounds(0, 60, 870, 150);
        heading2.setForeground(new Color(29, 39, 46));
        heading2.setFont(new Font("Garamond", Font.BOLD, 25));
        heading2.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel heading3 = new JLabel(getDeadline());
        heading3.setBounds(0, 130, 870, 150);
        heading3.setForeground(Color.RED);
        heading3.setFont(new Font("Garamond", Font.ITALIC, 40));
        heading3.setHorizontalAlignment(SwingConstants.CENTER);
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

        panel.add(heading3);
        panel2.add(heading);
        panel.add(heading2);
        panel.add(panel2);
        frame.add(back);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static String getDeadline() {
        String query = "select deadline from policies where keyColumn = ?";
        String deadline = "";
        try {

            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                deadline = data.getString("deadline");

            }
        } catch (SQLException ae) {
            System.out.println(ae.getMessage() + "edw");

        }
        return deadline;
    }

}
