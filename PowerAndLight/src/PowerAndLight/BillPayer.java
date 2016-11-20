package PowerAndLight;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class BillPayer extends JFrame implements ActionListener {
  
  DataOutputStream output;
  String           filename;
  
  // Construct a panel for each row
  JPanel row1 = new JPanel();
  JPanel row2 = new JPanel();
  JPanel row3 = new JPanel();
  JPanel row4 = new JPanel();
  JPanel row5 = new JPanel();
  JPanel row6 = new JPanel();
  JPanel row7 = new JPanel();
  JPanel row8 = new JPanel();
  
  JPanel[] rows = new JPanel[] { row1, row2, row3, row4, row5, row6, row7, row8 };
  
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
  
  JTextField[] inputs = new JTextField[] { acctNum, pmt, firstName, lastName, address, city, state, zip, acctNum };
  
  private static void configBillPayer() {
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
    
    // configure Row layouts
    for (JPanel row : rows) {
      row.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
    }
    
    addLabelsToRow(row1, acctNumLabel, pmtLabel);
    addFieldsToRow(row2, acctNum, pmt);
    addLabelsToRow(row3, firstNameLabel, lastNameLabel);
    addFieldsToRow(row4, firstName, lastName);
    addLabelsToRow(row5, addressLabel);
    addFieldsToRow(row6, address);
    addLabelsToRow(row7, cityLabel, stateLabel, zipLabel);
    addFieldsToRow(row8, city, state, zip);
    
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    
    for (JPanel row : rows) {
      fieldPanel.add(row);
    }
    
    buttonPanel.add(submitButton);
    
    // add panels to frame
    c.add(fieldPanel, BorderLayout.CENTER);
    c.add(buttonPanel, BorderLayout.SOUTH);
    
    submitButton.addActionListener(this);
    
    setFilename();
    openDataFile();
    
    // Closing the Data File
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit and submit the file?",
            "File Submission", JOptionPane.YES_NO_OPTION);
      }
    });
    
  }
  
  private void addLabelsToRow(JPanel row, JLabel... jLabels) {
    for (JLabel labels : jLabels)
      row.add(labels);
  }
  
  private void addFieldsToRow(JPanel row, JTextField... jTextFields) {
    for (JTextField textFields : jTextFields)
      row.add(textFields);
  }
  
  private void setFilename() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyy");
    filename = "payments" + dateFormat.format(new Date());
  }
  
  private void openDataFile() {
    try {
      output = new DataOutputStream(new FileOutputStream(filename));
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null,
          "The program could not create a storage location."
              + "Please check the disk drive and then run the program again.",
          "Error", JOptionPane.INFORMATION_MESSAGE);
    }
  }
  
  public void actionPerformed(ActionEvent e) {
    String arg = e.getActionCommand();
    
    if (checkFields()) {
      try {
        for (JTextField field : inputs) {
          output.writeUTF(field.getText());
        }
        JOptionPane.showMessageDialog(null, "The payment information has been saved.", "Submission Successful",
            JOptionPane.INFORMATION_MESSAGE);
      } catch (IOException f) {
        System.exit(1);
      }
      clearInputFields();
    }
  }
  
  public void clearInputFields() {
    for (JTextField field : inputs) {
      field.setText("");
    }
  }
  
  public boolean checkFields() {
    if ((acctNum.getText().compareTo("") < 1) || (pmt.getText().compareTo("") < 1)
        || (firstName.getText().compareTo("") < 1) || (lastName.getText().compareTo("") < 1)
        || (address.getText().compareTo("") < 1) || (city.getText().compareTo("") < 1)
        || (state.getText().compareTo("") < 1) || (zip.getText().compareTo("") < 1)) {
      JOptionPane.showMessageDialog(null, "You must complete all fields.", "Data Entry Error",
          JOptionPane.WARNING_MESSAGE);
      return false;
    } else {
      return true;
    }
  }
  
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "The UIManager could not set the Look and Feel for this application.",
          "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    configBillPayer();
  }
  
}
