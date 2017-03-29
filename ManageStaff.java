

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ManageStaff  implements ItemListener,ActionListener
{
	private static JLabel firstnamelabel;
	private static JLabel lastnamelabel;
	private static JTextField firstnametext;
	private static JTextField lastnametext;
	private static JLabel emaillabel;
	private static JLabel passwordlabel;
	private static JPasswordField passwordtext;
	private static JLabel secquelabel;
	private static JTextField secquetext;
	private static Component secanslabel;
	private static JTextField secanstext;
	private static JLabel rolelabel;
	private static JButton AddStaff2;
	private static JComboBox<String> roletext;
	private static JDialog addstaffdialog,viewstafflistdialog,removestaffdialog;
	static Connection conn=LibraryManagementSystem.connection;
	private static DefaultTableModel tbm;
	private static JTable jt;
	private static JTextField emailtext;
	private static JButton RemoveStaff2;
	String role=null;
	public void AddStaff()
	{
		addstaffdialog=new JDialog();
		addstaffdialog.setModal(true);
		addstaffdialog.setTitle("Add Staff");
		addstaffdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addstaffdialog.setSize(350,330);
		addstaffdialog.setResizable(false);
		addstaffdialog.setLayout(null);
		firstnamelabel = new JLabel("First Name");
		firstnamelabel.setBounds(10, 10, 150,25);
		addstaffdialog.add(firstnamelabel);
		lastnamelabel = new JLabel("Last Name");
		lastnamelabel.setBounds(10,45,150,25);
		addstaffdialog.add(lastnamelabel);
		firstnametext=new JTextField();
		firstnametext.setBounds(170, 10, 150, 25);
		addstaffdialog.add(firstnametext);
		lastnametext=new JTextField();
		lastnametext.setBounds(170, 45, 150, 25);
		addstaffdialog.add(lastnametext);
		emaillabel=new JLabel("E-mail Address");
		emaillabel.setBounds(10,80,150,25);
		addstaffdialog.add(emaillabel);
		emailtext = new JTextField();
		emailtext.setBounds(170, 80, 150, 25);
		addstaffdialog.add(emailtext);
		passwordlabel=new JLabel("Password");
		passwordlabel.setBounds(10,115,150,25);
		addstaffdialog.add(passwordlabel);
		passwordtext=new JPasswordField();
		passwordtext.setBounds(170,115,150,25);
		addstaffdialog.add(passwordtext);
		secquelabel=new JLabel("Security Question");
		secquelabel.setBounds(10,150,150,25);
		addstaffdialog.add(secquelabel);
		secquetext=new JTextField();
		secquetext.setBounds(170, 150, 150, 25);
		addstaffdialog.add(secquetext);
		secanslabel=new JLabel("Security Answer");
		secanslabel.setBounds(10,185,150,25);
		addstaffdialog.add(secanslabel);
		secanstext=new JTextField();
		secanstext.setBounds(170,185,150,25);
		addstaffdialog.add(secanstext);
		rolelabel=new JLabel("Role");
		rolelabel.setBounds(10,220,100,25);
		addstaffdialog.add(rolelabel);
		roletext=new JComboBox<>(new String[]{"Admin","Librarian"});
		roletext.setBounds(170, 220, 100, 25);
		roletext.addItemListener(this);
		addstaffdialog.add(roletext);
		AddStaff2=new JButton("Add Staff");
		AddStaff2.setBounds(120, 255, 100, 30);
		addstaffdialog.add(AddStaff2);
		AddStaff2.addActionListener(this);
		addstaffdialog.setVisible(true);
	}
	public void RemoveStaff()
	{
		removestaffdialog=new JDialog();
		removestaffdialog.setTitle("Remove Staff");
		removestaffdialog.setModal(true);
		removestaffdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		removestaffdialog.setSize(300, 150);
		removestaffdialog.setLayout(null);
		removestaffdialog.setResizable(false);
		emaillabel=new JLabel("Enter Email Address:");
		emailtext=new JTextField(); 
		emaillabel.setBounds(20,10,200,25);
		emailtext.setBounds(20,40, 200, 25);
		RemoveStaff2 = new JButton("Remove Staff");
		RemoveStaff2.setBounds(20,80,120,30);
		RemoveStaff2.addActionListener(this);
		removestaffdialog.add(emaillabel);
		removestaffdialog.add(emailtext);
		removestaffdialog.add(RemoveStaff2);
		removestaffdialog.setVisible(true);
	}
	public void ViewStaffList() throws SQLException
	{
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("Select * from staff");
		tbm=new DefaultTableModel(new String[]{"StaffID","First Name","Last Name","Email","Role"},0);
		while(rs.next())
		{
		tbm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
		}
		jt=new JTable();
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setModel(tbm);
		JScrollPane pane = new JScrollPane(jt);
		viewstafflistdialog=new JDialog();
		viewstafflistdialog.setTitle("Staff List");
		jt.setEnabled(false);
		viewstafflistdialog.setSize(400,400);
		jt.setBounds(5,40,390,360);
		viewstafflistdialog.add(pane);
		viewstafflistdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		viewstafflistdialog.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		try{
		if(e.getSource()==AddStaff2){
			PreparedStatement pst = null;
			String insertIntoMemberQuery = "INSERT INTO Staff VALUES "
					+ "(?, ?, ?, ?, ?,?,?,?);";
			pst = conn.prepareStatement(insertIntoMemberQuery);
			pst.setString(1, null);
			pst.setString(2, firstnametext.getText());
			pst.setString(3, lastnametext.getText());
			pst.setString(4, emailtext.getText());
			pst.setString(5, passwordtext.getText());
			pst.setString(6, secquetext.getText());
			pst.setString(7, secanstext.getText());
			pst.setString(8, role);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Staff Added", "Staff Added", 1);
			addstaffdialog.dispose();
			new Administrator();
		}
		if(e.getSource()==RemoveStaff2){
			PreparedStatement pst = null;
			String deleteQuery = "DELETE FROM staff WHERE email = ?;";
			try{
				pst = conn.prepareStatement(deleteQuery);
				pst.setString(1,emailtext.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Staff Removed", "Staff Removed", 1);
				removestaffdialog.dispose();
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==roletext){
			role=roletext.getSelectedItem().toString();
		}	
	}
}
