


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Guest implements ActionListener  
{
	JFrame guestFrame;
	private JLabel firstnamelabel;
	private JTextField firstnametext;
	private JLabel lastnamelabel;
	private JTextField lastnametext;
	private JLabel emaillabel;
	private JTextField emailtext;
	private JLabel passwordlabel;
	private JPasswordField passwordtext;
	private JLabel secquelabel;
	private JLabel secanslabel;
	private JTextField secquetext;
	private JTextField secanstext;
	private JButton Submit;
	Connection conn=LibraryManagementSystem.connection;
	public Guest(){
		
		guestFrame= new JFrame("Register Guest");
		guestFrame.setSize(320,310);
		guestFrame.setResizable(false);
		guestFrame.setLayout(null);
		firstnamelabel=new JLabel("First Name");
		firstnamelabel.setBounds(10,10,150,25);
		guestFrame.add(firstnamelabel);
		firstnametext=new JTextField();
		firstnametext.setBounds(140, 10, 150, 25);
		guestFrame.add(firstnametext);
		lastnamelabel=new JLabel("Last Name");
		lastnamelabel.setBounds(10,45,150,25);
		guestFrame.add(lastnamelabel);
		lastnametext=new JTextField();
		lastnametext.setBounds(140, 45, 150, 25);
		guestFrame.add(lastnametext);
		emaillabel=new JLabel("Email Address");
		emaillabel.setBounds(10,80,150,25);
		guestFrame.add(emaillabel);
		emailtext=new JTextField();
		emailtext.setBounds(140, 80, 150, 25);
		guestFrame.add(emailtext);
		passwordlabel=new JLabel("Password");
		passwordlabel.setBounds(10,115,150,25);
		guestFrame.add(passwordlabel);
		passwordtext=new JPasswordField();
		passwordtext.setBounds(140,115,150,25);
		guestFrame.add(passwordtext);
		secquelabel=new JLabel("Security Question");
		secquelabel.setBounds(10,150,150,25);
		guestFrame.add(secquelabel);
		secquetext=new JTextField();
		secquetext.setBounds(140, 150, 150, 25);
		guestFrame.add(secquetext);
		secanslabel=new JLabel("Security Answer");
		secanslabel.setBounds(10,185,150,25);
		guestFrame.add(secanslabel);
		secanstext=new JTextField();
		secanstext.setBounds(140, 185, 150, 25);
		guestFrame.add(secanstext);
		guestFrame.setVisible(true);
		Submit=new JButton("Submit");
		Submit.addActionListener(this);
		Submit.setBounds(100,230,100,30);
		guestFrame.add(Submit);
	}
	public void Register()
	{
		String registerquery="Insert into member Values(?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(registerquery);
			ps.setString(1, null);
			ps.setString(2, firstnametext.getText());
			ps.setString(3, lastnametext.getText());
			ps.setString(4, emailtext.getText());
			ps.setString(5, passwordtext.getText());
			ps.setString(6, secquetext.getText());
			ps.setString(7, secanstext.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "User Registered", "User Registered", 1);
			guestFrame.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Submit)
		{
			Register();
		}

	}

}
