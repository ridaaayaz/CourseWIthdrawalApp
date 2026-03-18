package withdrawapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.border.*;

public class Policies {

    public static void policies() {
        JFrame frame = new JFrame("Policies");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(29, 39, 46));

        JPanel panel = new JPanel();
        panel.setBounds(200, 55, 940, 610);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 0, 940, 90);
        panel2.setLayout(null);
        panel2.setBackground(new Color(210, 220, 227));

        JLabel heading = new JLabel("WITHDRAWAL POLICIES");
        heading.setBounds(0, 0, 900, 90);
        heading.setForeground(new Color(29, 39, 46));
        heading.setFont(new Font("Garamond", Font.BOLD, 42));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel policy = new JLabel(getPolicies());

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
        policy.setBounds(50, 100, 870, 500);  
        policy.setForeground(new Color(29, 39, 46));  
        policy.setFont(new Font("Garamond", Font.BOLD, 18));  
        policy.setHorizontalAlignment(SwingConstants.LEFT);  
        panel2.add(heading);
        panel.add(panel2);
        panel.add(policy);
        frame.add(back);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static String getPolicies() {
        String query = "select allPolicies from policies where keyColumn = ?";
        String policiesVar = "";
        try {

            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                policiesVar = data.getString("allPolicies");

            }
        } catch (SQLException ae) {
            System.out.println(ae.getMessage() + "edw");

        }
        return policiesVar;
    }

}
