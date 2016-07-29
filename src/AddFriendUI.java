import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class AddFriendUI extends JFrame {

    private JButton addButton, showButton;
    private JTextField idTxt, fnTxt, lnTxt, ageTxt;
    private JLabel id, fn, ln, age;

    public AddFriendUI() {
        super("Add Friend");
        setLayout(new FlowLayout());

        id = new JLabel("ID");
        add(id);
        idTxt = new JTextField();
        idTxt.setSize(idTxt.getPreferredSize());
        idTxt.setColumns(15);
        add(idTxt);

        fn = new JLabel("FN");
        add(fn);
        fnTxt = new JTextField();
        fnTxt.setSize(fnTxt.getPreferredSize());
        fnTxt.setColumns(15);
        add(fnTxt);

        ln = new JLabel("LN");
        add(ln);
        lnTxt = new JTextField();
        lnTxt.setSize(lnTxt.getPreferredSize());
        lnTxt.setColumns(15);
        add(lnTxt);

        age = new JLabel("AGE");
        add(age);
        ageTxt = new JTextField();
        ageTxt.setSize(ageTxt.getPreferredSize());
        ageTxt.setColumns(15);
        add(ageTxt);

        addButton = new JButton("Add");
        addButton.setSize(80, 23);
        add(addButton);

        showButton = new JButton("Show");
        showButton.setSize(80, 23);
        add(showButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    Connection myConn = DriverManager.getConnection("jdbc:derby:C:/Users/Eric/.netbeans-derby/SQLLink", "ericstaryou", "123");
                    System.out.println("connieked");

                    PreparedStatement pst = myConn.prepareStatement("INSERT INTO FRIENDS values (?, ?, ?, ?)");
                    pst.setInt(1, Integer.parseInt(idTxt.getText()));
                    pst.setString(2, fnTxt.getText());
                    pst.setString(3, lnTxt.getText());
                    pst.setInt(4, Integer.parseInt(ageTxt.getText()));
                    pst.execute();
                    
                    System.out.println("added");

                    /*System.out.println(myRs.getObject("ID") + "," + myRs.getString("FN")
                                + "," + myRs.getString("LN") + "," + myRs.getString("AGE"));*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    Connection myConn = DriverManager.getConnection("jdbc:derby:C:/Users/Eric/.netbeans-derby/SQLLink", "ericstaryou", "123");
                    System.out.println("connieked");

                    Statement myStatement = myConn.createStatement();
                    ResultSet myRs = myStatement.executeQuery("SELECT * from FRIENDS");

                    while (myRs.next()) {
                        if (myRs.getString("ID").equalsIgnoreCase(idTxt.getText())) {
                            fnTxt.setText(myRs.getString("FN"));
                            lnTxt.setText(myRs.getString("LN"));
                            ageTxt.setText(myRs.getString("AGE"));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
