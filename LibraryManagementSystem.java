import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class LibraryManagementSystem extends JFrame implements ActionListener
{
	private JPanel User;
	private JPanel Staff;
	private JPasswordField password1,password2;
	private JTextField username1,username2;
	private JButton Login1;
	private JButton Register;
	private JButton forgot1;
	private JButton cancel1;
	private JButton Login2;
	private JButton cancel2;
	private String user;
	private String pass;
	private String role;
	public int memberID;
	Administrator admin;
	Librarian libr;
	public static Connection connection = null;
	public LibraryManagementSystem() 
	{
		DbConnection dbConnection = new DbConnection("jdbc:mysql://localhost:3306/library", "root", "root");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(dbConnection.url, dbConnection.user, dbConnection.password);
		} catch (SQLException e) {
			// handle any errors
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			e.printStackTrace();
		} catch (Exception ex) {
			// handle the error
			ex.printStackTrace();
		}
		setTitle("Library Management System");
		setSize(340,280);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane BaseTabs=new JTabbedPane();
		User=new JPanel();
		Staff=new JPanel();
		BaseTabs.addTab("UserLogin", User);
		User.setLayout(null);
		BaseTabs.addTab("Staff Login", Staff);
		Staff.setLayout(null);
		JLabel usernameLabel1 = new JLabel("Username");
		JLabel passwordLabel1 = new JLabel("Password");
		this.Login1 = new JButton("Login");
		this.Register = new JButton("Register User");
		this.forgot1 = new JButton("Forgot Password");
		forgot1.setToolTipText("Input you ID here for reteriving password");
		this.cancel1 = new JButton("Cancel");
		this.username1 = new JTextField();
		this.password1 = new JPasswordField();
		usernameLabel1.setBounds(20, 10, 100, 30);
		passwordLabel1.setBounds(20, 50, 100, 30);
		this.username1.setBounds(130, 10, 150, 30);
		this.password1.setBounds(130, 50, 150, 30);
		this.Login1.setBounds(75, 100, 75, 30);
		this.cancel1.setBounds(180, 100, 75, 30);
		this.forgot1.setBounds(75, 140, 150, 30);
		this.Register.setBounds(75, 180, 150, 30);
		User.add(usernameLabel1);
		User.add(passwordLabel1);
		User.add(username1);
		User.add(password1);
		User.add(Login1);
		User.add(Register);
		User.add(cancel1);
		User.add(forgot1);
		Login1.addActionListener(this);
		Register.addActionListener(this);
		cancel1.addActionListener(this);
		forgot1.addActionListener(this);
		//Making staff tab
		JLabel usernameLabel2 = new JLabel("Username");
		JLabel passwordLabel2 = new JLabel("Password");
		this.username2 = new JTextField();
		this.password2 = new JPasswordField();
		this.Login2 = new JButton("Login");
		this.cancel2 = new JButton("Cancel");
		usernameLabel2.setBounds(20, 10, 100, 30);
		passwordLabel2.setBounds(20, 50, 100, 30);
		this.username2.setBounds(130, 10, 150, 30);
		this.password2.setBounds(130, 50, 150, 30);
		this.Login2.setBounds(75, 100, 75, 30);
		this.cancel2.setBounds(180, 100, 75, 30);
		Staff.add(usernameLabel2);
		Staff.add(passwordLabel2);
		Staff.add(username2);
		Staff.add(password2);
		Staff.add(Login2);
		Staff.add(cancel2);
		Login2.addActionListener(this);
		cancel2.addActionListener(this);
		add(BaseTabs);
		setVisible(true);
	}
	public static void main(String[] args) {
		new LibraryManagementSystem();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try{
			if(e.getSource()==Login1)
			{
				user=username1.getText();
				pass=password1.getText();
				String loginquery = "select * from member where memberID=? and password=?";
				PreparedStatement localPreparedStatement = connection.prepareStatement(loginquery);
				localPreparedStatement.setString(1, user);
				localPreparedStatement.setString(2, pass);
				ResultSet rs = localPreparedStatement.executeQuery();
				if (rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login Ok", "Project", 1);
					dispose();
					new Member(rs.getInt(1));
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid ID", "Project", 0);
				}
			}
			if(e.getSource()==Login2){
				user=username2.getText();
				pass=password2.getText();
				String loginquery = "select * from staff where staffID=? and password=?";
				PreparedStatement localPreparedStatement = connection.prepareStatement(loginquery);
				localPreparedStatement.setString(1, user);
				localPreparedStatement.setString(2, pass);
				ResultSet rs = localPreparedStatement.executeQuery();
				if (rs.next())
				{
					JOptionPane.showMessageDialog(null, "Login Ok", "Project", 1);
					role=rs.getString(8);
					if(role.equalsIgnoreCase("Admin")){
						admin = new Administrator();
						dispose();
					}
					if(role.equalsIgnoreCase("Librarian")){
						libr = new Librarian();
						dispose();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid ID", "Project", 0);
				}
			}
			if(e.getSource()==cancel1){
				dispose();	
			}
			if(e.getSource()==cancel2){
				dispose();
			}
			if(e.getSource()==forgot1){
				new Forgot(username1.getText());
				dispose();	
			}
			if(e.getSource()==Register){
				new Guest(); 
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
