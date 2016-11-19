package PowerAndLight;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class BillPayer extends JFrame implements ActionListener {
  
  DataOutputStream output;
  
  // Construct a panel for each row
  JPanel row1 = new JPanel();
  JPanel row2 = new JPanel();
  JPanel row3 = new JPanel();
  JPanel row4 = new JPanel();
  JPanel row5 = new JPanel();
  JPanel row6 = new JPanel();
  JPanel row7 = new JPanel();
  JPanel row8 = new JPanel();
  
  JPanel fieldPanel  = new JPanel();
  JPanel buttonPanel = new JPanel();
  
  // Construct labels and text boxes
  JLabel     acctNumLabel = new JLabel("Account Number: ");
  JTextField acctNum      = new JTextField(15);
  
  JLabel     pmtLabel = new JLabel("Payment Amount: ");
  JTextField pmt      = new JTextField(10);
  
  JLabel     firstNameLabel = new JLabel("First Name: ");
  JTextField firstName      = new JTextField(10);
  
  JLabel     lastNameLabel = new JLabel("Last Name: ");
  JTextField lastName      = new JTextField(20);
  
  JLabel     addressLabel = new JLabel("Address: ");
  JTextField address      = new JTextField(35);
  
  JLabel     cityLabel = new JLabel("City: ");
  JTextField city      = new JTextField(10);
  
  JLabel     stateLabel = new JLabel("State: ");
  JTextField state      = new JTextField(2);
  
  JLabel     zipLabel = new JLabel("Zip: ");
  JTextField zip      = new JTextField(9);
  
  JButton submitButton = new JButton("Submit");
  
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "The UIManager could not set the Look and Feel for this application.",
          "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    BillPayer f = new BillPayer();
    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    f.setSize(450, 300);
    f.setTitle("Power and Light Payments");
    f.setResizable(false);
    f.setLocation(200, 200);
    f.setVisible(true);
    
  }
  
  public BillPayer() {
    Container c = getContentPane();
    c.setLayout((new BorderLayout()));
    fieldPanel.setLayout(new GridLayout(8, 1));
    FlowLayout rowSetup = new FlowLayout(FlowLayout.LEFT, 5, 3);
    row1.setLayout(rowSetup);
    row2.setLayout(rowSetup);
    row3.setLayout(rowSetup);
    row4.setLayout(rowSetup);
    row5.setLayout(rowSetup);
    row6.setLayout(rowSetup);
    row7.setLayout(rowSetup);
    row8.setLayout(rowSetup);
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    
    // add fields to rows
    row1.add(acctNumLabel);
    row1.add(pmtLabel);
    
    row2.add(acctNum);
    row2.add(pmt);
    
    row3.add(firstNameLabel);
    row3.add(lastNameLabel);
    
    row4.add(firstName);
    row4.add(lastName);
    
    row5.add(addressLabel);
    
    row6.add(address);
    
    row7.add(cityLabel);
    row7.add(stateLabel);
    row7.add(zipLabel);
    
    row8.add(city);
    row8.add(state);
    row8.add(zip);
    
    // add rows to panel
    fieldPanel.add(row1);
    fieldPanel.add(row2);
    fieldPanel.add(row3);
    fieldPanel.add(row4);
    fieldPanel.add(row5);
    fieldPanel.add(row6);
    fieldPanel.add(row7);
    fieldPanel.add(row8);
    
    buttonPanel.add(submitButton);
    
    // add panels to frame
    c.add(fieldPanel, BorderLayout.CENTER);
    c.add(buttonPanel, BorderLayout.SOUTH);
    
    submitButton.addActionListener(this);
    
  }
  
  public void actionPerformed(ActionEvent e) {
    
  }
  
}
