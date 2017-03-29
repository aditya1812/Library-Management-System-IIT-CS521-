
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
class ManageUser implements ActionListener  
{
	private static Connection conn=LibraryManagementSystem.connection;
	private static DefaultTableModel tbm;
	private static JTable jt;
	private static JDialog viewuserlistdialog;
	private static JDialog removeuserdialog;
	private static JLabel emaillabel;
	private static JTextField emailtext;
	private static JButton Removeuser2;
	private static JDialog adduserdialog;
	private static JLabel firstnamelabel;
	private static JLabel lastnamelabel;
	private static JTextField firstnametext;
	private static JTextField lastnametext;
	private static JLabel passwordlabel;
	private static JPasswordField passwordtext;
	private static JLabel secquelabel;
	private static JTextField secquetext;
	private static JLabel secanslabel;
	private static JTextField secanstext;
	private static JButton Adduser2;
	void AddUser(){
		adduserdialog=new JDialog();
		adduserdialog.setModal(true);
		adduserdialog.setTitle("Add user");
		adduserdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		adduserdialog.setSize(350,330);
		adduserdialog.setResizable(false);
		adduserdialog.setLayout(null);
		firstnamelabel = new JLabel("First Name");
		firstnamelabel.setBounds(10, 10, 150,25);
		adduserdialog.add(firstnamelabel);
		lastnamelabel = new JLabel("Last Name");
		lastnamelabel.setBounds(10,45,150,25);
		adduserdialog.add(lastnamelabel);
		firstnametext=new JTextField();
		firstnametext.setBounds(170, 10, 150, 25);
		adduserdialog.add(firstnametext);
		lastnametext=new JTextField();
		lastnametext.setBounds(170, 45, 150, 25);
		adduserdialog.add(lastnametext);
		emaillabel=new JLabel("E-mail Address");
		emaillabel.setBounds(10,80,150,25);
		adduserdialog.add(emaillabel);
		emailtext = new JTextField();
		emailtext.setBounds(170, 80, 150, 25);
		adduserdialog.add(emailtext);
		passwordlabel=new JLabel("Password");
		passwordlabel.setBounds(10,115,150,25);
		adduserdialog.add(passwordlabel);
		passwordtext=new JPasswordField();
		passwordtext.setBounds(170,115,150,25);
		adduserdialog.add(passwordtext);
		secquelabel=new JLabel("Security Question");
		secquelabel.setBounds(10,150,150,25);
		adduserdialog.add(secquelabel);
		secquetext=new JTextField();
		secquetext.setBounds(170, 150, 150, 25);
		adduserdialog.add(secquetext);
		secanslabel=new JLabel("Security Answer");
		secanslabel.setBounds(10,185,150,25);
		adduserdialog.add(secanslabel);
		secanstext=new JTextField();
		secanstext.setBounds(170,185,150,25);
		adduserdialog.add(secanstext);
		Adduser2=new JButton("Add user");
		Adduser2.setBounds(120, 255, 100, 30);
		adduserdialog.add(Adduser2);
		Adduser2.addActionListener(this);
		adduserdialog.setVisible(true);
	}
	void RemoveUser(){
		removeuserdialog=new JDialog();
		removeuserdialog.setTitle("Remove user");
		removeuserdialog.setModal(true);
		removeuserdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		removeuserdialog.setSize(300, 150);
		removeuserdialog.setLayout(null);
		removeuserdialog.setResizable(false);
		emaillabel=new JLabel("Enter Email Address:");
		emailtext=new JTextField(); 
		emaillabel.setBounds(20,10,200,25);
		emailtext.setBounds(20,40, 200, 25);
		Removeuser2 = new JButton("Remove user");
		Removeuser2.setBounds(20,80,120,30);
		Removeuser2.addActionListener(this);
		removeuserdialog.add(emaillabel);
		removeuserdialog.add(emailtext);
		removeuserdialog.add(Removeuser2);
		removeuserdialog.setVisible(true);
	}
	void ViewUserList() throws SQLException{
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("Select * from member");
		tbm=new DefaultTableModel(new String[]{"Member ID","First Name","Last Name","Email"},0);
		while(rs.next())
		{
			tbm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
		}
		jt=new JTable();
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setModel(tbm);
		JScrollPane pane = new JScrollPane(jt);
		viewuserlistdialog=new JDialog();
		viewuserlistdialog.setTitle("Book List");
		jt.setEnabled(false);
		viewuserlistdialog.setSize(400,400);
		jt.setBounds(5,40,390,360);
		viewuserlistdialog.add(pane);
		viewuserlistdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		viewuserlistdialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try{
		if(e.getSource()==Adduser2){
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
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Staff Added", "Staff Added", 1);
			adduserdialog.dispose();
			new Administrator();
		}
		if(e.getSource()==Removeuser2){
			PreparedStatement pst = null;
			String deleteQuery = "DELETE FROM member WHERE email = ?;";
				pst = conn.prepareStatement(deleteQuery);
				pst.setString(1,emailtext.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Member Removed", "Member Removed", 1);
				removeuserdialog.dispose();
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}