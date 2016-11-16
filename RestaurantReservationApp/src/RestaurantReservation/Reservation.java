package RestaurantReservation;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class Reservation extends Frame implements ActionListener {
  Color lightRed   = new Color(255, 90, 90);
  Color lightGreen = new Color(140, 215, 40);
  
  Table table = new Table(17, 3);
  
  Panel    tablePanel     = new Panel();
  TextArea tableDisplay[] = new TextArea[21];
  
  Panel  buttonPanel = new Panel();
  Button bookButton  = new Button("Book Table");
  
  Panel         inputPanel     = new Panel();
  Label         custNameLabel  = new Label("Name:");
  TextField     nameField      = new TextField(15);
  Label         custPhoneLabel = new Label("Phone number:");
  TextField     phoneField     = new TextField(15);
  Label         numLabel       = new Label("Number in party:");
  Choice        numberOfGuests = new Choice();
  CheckboxGroup options        = new CheckboxGroup();
  Checkbox      nonSmoking     = new Checkbox("Nonsmoking", false, options);
  Checkbox      smoking        = new Checkbox("Smoking", false, options);
  Checkbox      hidden         = new Checkbox("", true, options);
  
  private void addTablePanelComponents() {
    int numberOfSmokingTables = table.getNumberOfSmokingTables();
    int numberOfNonSmokingTables = table.getNumberOfNonSmokingTables();
    
    for (int i = 1; i <= (numberOfSmokingTables + numberOfNonSmokingTables); i++) {
      tableDisplay[i] = new TextArea(null, 4, 5, 3);
      if (i < numberOfNonSmokingTables) {
        labelTable(i, "Non-Smoking");
      } else {
        labelTable(i, "Smoking");
      }
      tableDisplay[i].setEditable(false);
      tableDisplay[i].setBackground(lightGreen);
      tablePanel.add(tableDisplay[i]);
    }
  };
  
  private void labelTable(int tableNumber, String smokingPolicy) {
    tableDisplay[tableNumber].setText("Table " + tableNumber + " " + smokingPolicy);
  }
  
  private void configureLayout() {
    this.setLayout(new BorderLayout());
    tablePanel.setLayout(new GridLayout(5, 4, 10, 10));
    buttonPanel.setLayout(new FlowLayout());
    inputPanel.setLayout(new FlowLayout());
  };
  
  private void addButtonPanelComponents() {
    buttonPanel.add(bookButton);
  }
  
  private void addInputPanelCompenents() {
    inputPanel.add(custNameLabel);
    inputPanel.add(nameField);
    inputPanel.add(custPhoneLabel);
    inputPanel.add(phoneField);
    inputPanel.add(numLabel);
    inputPanel.add(numberOfGuests);
    for (int i = 1; i <= 10; i++) {
      numberOfGuests.add(String.valueOf(i));
    }
    inputPanel.add(nonSmoking);
    inputPanel.add(smoking);
  }
  
  public Reservation() {
    configureLayout();
    addTablePanelComponents();
    addButtonPanelComponents();
    addInputPanelCompenents();
    
    add(buttonPanel, BorderLayout.SOUTH);
    add(inputPanel, BorderLayout.CENTER);
    add(tablePanel, BorderLayout.NORTH);
    
    bookButton.addActionListener(this);
    
    // overrides windowClosing() so user can close application window
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
  
  private void clearFields() {
    nameField.setText("");
    phoneField.setText("");
    numberOfGuests.select(0);
    nameField.requestFocus();
    hidden.setState(true);
  }
  
  public static void main(String[] args) {
    Reservation rsvp = new Reservation();
    rsvp.setBounds(200, 200, 600, 500);
    rsvp.setTitle("Reserve a Table");
    rsvp.setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (hidden.getState()) {
      JOptionPane.showMessageDialog(null, "You must select Nonsmoking or Smoking.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
      int available = table.bookTable(smoking.getState());
      
      if (available > 0) {
        tableDisplay[available].setBackground(lightRed);
        tableDisplay[available].setText(tableDisplay[available].getText() + "\n" + nameField.getText() + ""
            + phoneField.getText() + "\nparty of " + numberOfGuests.getSelectedItem());
        clearFields();
      } else { // room is not available
        if (smoking.getState()) {
          JOptionPane.showMessageDialog(null, "Smoking is full.", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(null, "Nonsmoking is full.", "Error", JOptionPane.INFORMATION_MESSAGE);
          hidden.setState(true);
        }
      }
    }
  }
  
}
