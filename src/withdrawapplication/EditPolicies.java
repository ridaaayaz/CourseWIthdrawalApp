package withdrawapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.border.BevelBorder;

public class EditPolicies {

    public static void view() {
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

        String policiesText = getPolicies();
        JEditorPane policy = new JEditorPane("text/html", policiesText);
        policy.setForeground(new Color(29, 39, 46));
        policy.setFont(new Font("Garamond", Font.BOLD, 18));
        policy.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(policy);
        scrollPane.setBounds(50, 100, 870, 400);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);

        JButton applyChanges = new JButton("Apply Changes");
        applyChanges.setBounds(400, 520, 140, 30);
        applyChanges.setForeground(Color.WHITE);
        applyChanges.setFont(new Font("Garamond", Font.BOLD, 14));
        applyChanges.setBackground(new Color(29, 39, 46));
        applyChanges.setBorder(new BevelBorder(BevelBorder.RAISED));
        applyChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String updatedPolicies = policy.getText();
                boolean success = updatePolicies(updatedPolicies);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Policies updated successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update policies. Please try again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel2.add(heading);
        panel.add(panel2);
        panel.add(applyChanges);
        frame.add(back);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static String getPolicies() {
        String query = "SELECT allPolicies FROM policies WHERE keyColumn = ?";
        String policiesVar = "";
        try {
            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                policiesVar = data.getString("allPolicies");
            }
        } catch (SQLException ae) {
            System.out.println("Error fetching policies: " + ae.getMessage());
        }
        return policiesVar;
    }

    public static boolean updatePolicies(String newpolicies) {
        String query = "update policies set allPolicies=? where keyColumn = ?";

        try {
            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setString(1, newpolicies);
            preparedStatement.setInt(2, 1);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException ae) {
            System.out.println("Error: " + ae.getMessage());
        }
        return false;
    }
}
