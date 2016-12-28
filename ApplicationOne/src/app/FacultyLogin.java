package app;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FacultyLogin {

	private JFrame frmStudentPerfomanceAnalysis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyLogin window = new FacultyLogin();
					window.frmStudentPerfomanceAnalysis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	Connection connection = null;
	private JTextField textUserName;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public FacultyLogin() {
		initialize();
		connection = SqliteConnection.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentPerfomanceAnalysis = new JFrame();
		frmStudentPerfomanceAnalysis.setTitle("Student perfomance analysis");
		frmStudentPerfomanceAnalysis.setBounds(100, 100, 726, 468);
		frmStudentPerfomanceAnalysis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentPerfomanceAnalysis.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblUserName.setBounds(154, 150, 118, 38);
		frmStudentPerfomanceAnalysis.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblPassword.setBounds(154, 199, 109, 38);
		frmStudentPerfomanceAnalysis.getContentPane().add(lblPassword);
		
		textUserName = new JTextField();
		textUserName.setFont(new Font("Arial Black", Font.BOLD, 18));
		textUserName.setBounds(326, 154, 177, 29);
		frmStudentPerfomanceAnalysis.getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs;
				PreparedStatement statement;
				try{
					String query = "select * from FacultyInfo where username=? and passworrd=?";
					statement = connection.prepareStatement(query);
					statement.setString(1, textUserName.getText());
					statement.setString(2, passwordField.getText());
					 rs = statement.executeQuery();
					int count=0;
					while(rs.next()){
						++count;
						
					}
					if(count==1){
						JOptionPane.showMessageDialog(null, "Login Successful");
						frmStudentPerfomanceAnalysis.dispose();
						FileUpload upload = new FileUpload();
						upload.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null,"Invalid Username/Password Combination");
						textUserName.setText("");
						passwordField.setText("");
					}
					rs.close();
					statement.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				} 
			}
		});
		btnLogin.setFont(new Font("Arial Black", Font.BOLD, 18));
		btnLogin.setBounds(306, 282, 109, 38);
		frmStudentPerfomanceAnalysis.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Arial Black", Font.BOLD, 18));
		passwordField.setBounds(326, 202, 177, 32);
		frmStudentPerfomanceAnalysis.getContentPane().add(passwordField);
	}
}
