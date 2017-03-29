

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Forgot
  extends JDialog
  implements ActionListener
{
  JTextField u;
  JTextField q;
  JTextField a;
  JTextField p;
  JButton get;
  JButton back;
  String dans;
  String uans;
  String pass;
  Connection localConnection = LibraryManagementSystem.connection;
  public Forgot() {
}
  Forgot(String user)
  {
    setTitle("Forgot Password...");
    setSize(360, 330);
    setLayout(null);
    setVisible(true);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    JLabel localJLabel1 = new JLabel("Password Retrieval");
    JLabel localJLabel2 = new JLabel("Username");
    JLabel localJLabel3 = new JLabel("Question");
    JLabel localJLabel4 = new JLabel("Answer");
    JLabel localJLabel5 = new JLabel("Password");
    this.u = new JTextField(user);
    this.q = new JTextField();
    this.a = new JTextField();
    this.p = new JTextField();
    this.u.setEditable(false);
    this.p.setEditable(false);
    this.q.setEditable(false);
    this.get = new JButton("Get Password");
    this.back = new JButton("Back to Login");
    this.get.addActionListener(this);
    this.back.addActionListener(this);
    localJLabel1.setBounds(100, 10, 180, 30);
    localJLabel2.setBounds(10, 50, 100, 30);
    this.u.setBounds(150, 50, 180, 30);
    localJLabel3.setBounds(10, 90, 100, 30);
    this.q.setBounds(150, 90, 180, 30);
    localJLabel4.setBounds(10, 130, 100, 30);
    this.a.setBounds(150, 130, 180, 30);
    this.get.setBounds(100, 170, 160, 30);
    localJLabel5.setBounds(10, 210, 100, 30);
    this.p.setBounds(150, 210, 180, 30);
    this.back.setBounds(100, 260, 160, 30);
    add(localJLabel1);
    add(this.back);
    add(localJLabel2);
    add(localJLabel3);
    add(localJLabel4);
    add(this.u);
    add(this.a);
    add(this.q);
    add(this.get);
    add(this.p);
    add(localJLabel5);
    setModal(true);
    setResizable(false);
    try
    {
      String str = "select * from member where memberid=?";
      PreparedStatement localPreparedStatement = localConnection.prepareStatement(str);
      localPreparedStatement.setString(1,user);
      ResultSet localResultSet = localPreparedStatement.executeQuery();
      while (localResultSet.next())
      {
        this.dans = localResultSet.getString("securityans");
        this.q.setText(localResultSet.getString("securityques"));
        this.pass = localResultSet.getString("Password");
      }
    }
    catch (SQLException localSQLException)
    {
      localSQLException.printStackTrace();
    }
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    Object localObject = paramActionEvent.getSource();
    this.uans = this.a.getText();
    if (localObject == this.get) {
      if (this.uans.equalsIgnoreCase(this.dans)) {
        this.p.setText(this.pass);
      } else {
        JOptionPane.showMessageDialog(null, "Invalid Answer", "Project", 0);
      }
    }
    if (localObject == this.back)
    {
      new LibraryManagementSystem();
      dispose();
    }
  }
}
