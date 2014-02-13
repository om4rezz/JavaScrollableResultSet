/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javascrollableresultset;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 *
 * @author om4rezz
 */
public class JavaScrollableResultSet extends JApplet {

    private JComboBox jcboDriver = new JComboBox(new String[]{
        "com.mysql.jdbc.Driver", "oracle.jdbc.driver.OracleDriver",
        "sun.jdbc.odbc.JdbcOdbcDriver"});
    private JComboBox jcboURL = new JComboBox(new String[]{
        "jdbc:mysql://localhost/users", "jdbc:odbc:exampleMDBDataSource",
        "jdbc:oracle:thin:@liang.armstrong.edu:1521:ora9i"});

    private JButton jbtConnect
            = new JButton("Connect to DB & Get Table");
    private JTextField jtfUserName = new JTextField();
    private JPasswordField jpfPassword = new JPasswordField();
    private JTextField jtfTableName = new JTextField();
    private TableEditor tableEditor1 = new TableEditor();
    private JLabel jlblStatus = new JLabel();

    /**
     * Creates new form TestTableEditor
     */
    public JavaScrollableResultSet() {
        JPanel jPane1 = new JPanel();
        jPane1.setLayout(new GridLayout(5, 0));
        jPane1.add(jcboDriver);
        jPane1.add(jcboURL);
        jPane1.add(jtfUserName);
        jPane1.add(jpfPassword);
        jPane1.add(jtfTableName);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new GridLayout(5, 0));
        jPanel2.add(new JLabel("JDBC Driver"));
        jPanel2.add(new JLabel("Database URL"));
        jPanel2.add(new JLabel("Username"));
        jPanel2.add(new JLabel("Password"));
        jPanel2.add(new JLabel("Table Name"));

        JPanel jPane3 = new JPanel();
        jPane3.setLayout(new BorderLayout());
        jPane3.add(jbtConnect, BorderLayout.SOUTH);
        jPane3.add(jPanel2, BorderLayout.WEST);
        jPane3.add(jPane1, BorderLayout.CENTER);
        tableEditor1.setPreferredSize(new Dimension(400, 200));

        add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                jPane3, tableEditor1), BorderLayout.CENTER);
        add(jlblStatus, BorderLayout.SOUTH);

        jbtConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    // Connect to the database
                    Connection connection = getConnection();
                    tableEditor1.setConnectionAndTable(connection,
                            jtfTableName.getText().trim());
                } catch (Exception ex) {
                    jlblStatus.setText(ex.toString());
                }
            }
        });
    }

    /**
     * Connect to a database
     */
    private Connection getConnection() throws Exception {
        // Load the JDBC driver
        System.out.println((String) jcboDriver.getSelectedItem());
        Class.forName(((String) jcboDriver.getSelectedItem()).trim());
        System.out.println("Driver loaded");
        // Establish a connection
        Connection connection = DriverManager.getConnection(((String) jcboURL.getSelectedItem()).trim(),
                jtfUserName.getText().trim(),
                new String(jpfPassword.getPassword()));
        System.out.println("Connection established");

        // print username and password for testing
        System.out.println(jtfUserName.getText());
        System.out.println(jpfPassword.getText());

        jlblStatus.setText("Database connected");

        return connection;
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        JavaScrollableResultSet applet = new JavaScrollableResultSet();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setTitle("TestTableEditor");
        frame.add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(800, 320);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
