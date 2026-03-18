
package withdrawapplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class EditDeadline {

    public static void editDeadline() {
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

        JLabel heading = new JLabel("Edit deadline for withdrawal.");
        heading.setBounds(0, 0, 870, 90);
        heading.setForeground(new Color(29, 39, 46));
        heading.setFont(new Font("Garamond", Font.BOLD, 42));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel heading2 = new JLabel("Deadline for withdrawal is : ");
        heading2.setBounds(0, 60, 870, 150);
        heading2.setForeground(new Color(29, 39, 46));
        heading2.setFont(new Font("Garamond", Font.BOLD, 25));
        heading2.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField date = new JTextField(getDeadline());
        date.setBounds(0, 150, 870, 60);
        date.setForeground(Color.RED);
        date.setFont(new Font("Garamond", Font.ITALIC, 40));
        date.setHorizontalAlignment(SwingConstants.CENTER);

        JButton applyChanges = new JButton("Apply Changes");
        applyChanges.setBounds(350, 220, 140, 30);
        applyChanges.setForeground(Color.WHITE);
        applyChanges.setFont(new Font("Garamond", Font.BOLD, 14));
        applyChanges.setBackground(new Color(29, 39, 46));
        applyChanges.setBorder(new BevelBorder(BevelBorder.RAISED));
        applyChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String updatedDate = date.getText();
                boolean success = updateDeadline(updatedDate);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Policies updated successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update policies. Please try again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
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

        panel.add(date);
        panel2.add(heading);
        panel.add(heading2);
        panel.add(panel2);
        panel.add(applyChanges);
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

    public static boolean updateDeadline(String newDate)
    {
        String query = "update policies set deadline=? where keyColumn = ?";
        
        try {
            PreparedStatement preparedStatement = WithdrawApplication.connect.prepareStatement(query);
            preparedStatement.setString(1, newDate);
            preparedStatement.setInt(2, 1);
            int rows = preparedStatement.executeUpdate();
            if (rows>0) {
               return true;
            }
        } catch (SQLException ae) {
            System.out.println("Error: " + ae.getMessage());
        }
        return false;
    }
}
