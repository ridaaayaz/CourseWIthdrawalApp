package withdrawapplication;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
import java.sql.*;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {

    public static void loginPage() {
        JFrame frame = new JFrame("LoginPage");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(29, 39, 46));

        JPanel panel = new JPanel();
        panel.setBounds(400, 100, 500, 550);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 500, 150);
        header.setLayout(null);
        header.setBackground(new Color(45, 62, 72));

        JLabel label = new JLabel("WITHDRAWAL PORTAL");
        label.setBounds(0, 80, 500, 30);
        label.setFont(new Font("Garamond", Font.BOLD, 40));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(label);

        JLabel userLabel = new JLabel("Enter your user ID: ");
        userLabel.setBounds(100, 280, 500, 30);
        userLabel.setFont(new Font("Garamond", Font.BOLD, 20));
        userLabel.setForeground(new Color(45, 62, 72));
        userLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JTextField userID = new JTextField();
        userID.setBounds(100, 310, 300, 30);
        userID.setFont(new Font("Garamond", Font.PLAIN, 16));
        userID.setForeground(Color.BLACK);
        userID.setBackground(Color.WHITE);

        JLabel passLabel = new JLabel("Enter your password: ");
        passLabel.setBounds(100, 340, 500, 30);
        passLabel.setFont(new Font("Garamond", Font.BOLD, 20));
        passLabel.setForeground(new Color(45, 62, 72));
        passLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JPasswordField password = new JPasswordField();
        password.setBounds(100, 370, 300, 30);
        password.setFont(new Font("Garamond", Font.PLAIN, 16));
        password.setForeground(Color.BLACK);
        password.setBackground(Color.WHITE);

        JButton logInButton = new JButton("LOGIN");
        logInButton.setBounds(200, 420, 100, 30);
        logInButton.setFont(new Font("Garamond", Font.BOLD, 15));
        logInButton.setBackground(new Color(45, 62, 72));
        logInButton.setForeground(Color.WHITE);
        logInButton.setBorder(new BevelBorder(BevelBorder.RAISED));

        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent AE) {

                

                if (userID.getText().equals("Admin") && password.getText().equals("123")) {
                    userID.setText("");
                    password.setText("");
                    WithdrawApplication.frameStack.push(frame);

                    AdminDashboard.dashboard();
                    frame.setVisible(false);

                } else {
                    try {
                        String query = ("Select * from Students where student_id = ? and password = ?;");
                        PreparedStatement statement = WithdrawApplication.connect.prepareStatement(query);
                        statement.setString(1, userID.getText());
                        statement.setString(2, password.getText());
                        ResultSet data = statement.executeQuery();
                        if (data.next()) {
                            userID.setText("");
                            password.setText("");
                            WithdrawApplication.frameStack.push(frame);
                            StudentDashboard.dashboard(data);
                            frame.setVisible(false);

                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Detials\nThe account does not exist, please try again!");
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }

            }
        });
        ImageIcon icon = new ImageIcon("D:\\Ridajava\\GUI\\login.png");
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(185, 160, icon.getIconWidth(), icon.getIconHeight());

        panel.add(iconLabel);
        panel.add(logInButton);
        panel.add(header);
        panel.add(userID);
        panel.add(password);
        panel.add(userLabel);
        panel.add(passLabel);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
