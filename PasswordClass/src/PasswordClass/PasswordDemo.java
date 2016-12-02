package PasswordClass;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;

public class PasswordDemo {
  
  public static void main(String[] argv) {
    int width = 400;
    int height = 130;
    final demoFrame f = new demoFrame("PasswordDemo");
    
    f.pack();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(width, height);
    f.setVisible(true);
  }
  
}

class demoFrame extends JFrame implements ActionListener {
  Password password = null;
  String   pswd, newPswd;
  
  JPasswordField pswdField;
  JPasswordField newPswdField;
  JButton        jbtAddPswd, jbtChangePswd, jbtUsePswd;
  
  public demoFrame(String title) {
    super(title);
    
    JLabel label1 = new JLabel("Current Password:");
    pswdField = new JPasswordField(20);
    pswdField.setEchoChar('*');
    
    JLabel label2 = new JLabel("New Password:", JLabel.RIGHT);
    newPswdField = new JPasswordField(20);
    pswdField.setEchoChar('*');
    
    jbtAddPswd = new JButton("Add password");
    jbtChangePswd = new JButton("Change password");
    jbtUsePswd = new JButton("Use password");
    
    jbtAddPswd.addActionListener(this);
    jbtChangePswd.addActionListener(this);
    jbtUsePswd.addActionListener(this);
    
    JPanel pswdPanel = new JPanel(new BorderLayout(10, 10));
    pswdPanel.add(label1, BorderLayout.WEST);
    pswdPanel.add(pswdField, BorderLayout.EAST);
    
    JPanel newPswdPanel = new JPanel(new BorderLayout(19, 19));
    newPswdPanel.add(label2, BorderLayout.WEST);
    newPswdPanel.add(newPswdField, BorderLayout.EAST);
    
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(jbtAddPswd);
    buttonPanel.add(jbtChangePswd);
    buttonPanel.add(jbtUsePswd);
    
    JPanel contentPanel = new JPanel(new FlowLayout());
    contentPanel.add(pswdPanel);
    contentPanel.add(newPswdPanel);
    contentPanel.add(buttonPanel);
    setContentPane(contentPanel);
    
    InputMap map;
    map = jbtAddPswd.getInputMap();
    if (map != null) {
      map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
      map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
    }
    map = jbtChangePswd.getInputMap();
    if (map != null) {
      map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
      map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
    }
    map = jbtUsePswd.getInputMap();
    if (map != null) {
      map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
      map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
    }
  }
  
  public void centerOnScreen(int width, int height) {
    int top, left, x, y;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    x = (screenSize.width - width) / 2;
    y = (screenSize.height - height) / 2;
    top = (x < 0) ? 0 : x;
    left = (y < 0) ? 0 : y;
    
    this.setLocation(top, left);
  }
  
  public void actionPerformed(ActionEvent e) {
    String msg;
    String title;
    int optionType;
    try {
      if (e.getSource() == jbtUsePswd) {
        pswd = new String(pswdField.getPassword());
        password.validate(pswd);
        if (password.isExpiring()) {
          msg = "Password use successful: " + password.getRemainingUses() + " use(s) remaining.";
          title = "Success";
          optionType = JOptionPane.WARNING_MESSAGE;
        } else {
          msg = "Password use successful";
          title = "Success";
          optionType = JOptionPane.INFORMATION_MESSAGE;
        }
      } else if (e.getSource() == jbtChangePswd) {
        newPswd = new String(newPswdField.getPassword());
        password.set(newPswd);
        
        msg = "Password changed.";
        title = "Success";
        optionType = JOptionPane.INFORMATION_MESSAGE;
      } else if (e.getSource() == jbtAddPswd) {
        newPswd = new String(newPswdField.getPassword());
        password = new Password(newPswd, 5);
        
        if (password.getAutoExpires()) {
          msg = "Success! Password added with " + password.getRemainingUses() + " remaining uses.";
        } else {
          msg = "Success! Password added - not set to expire.";
        }
        
        title = "Success";
        optionType = JOptionPane.INFORMATION_MESSAGE;
      } else {
        msg = "Please choose a valid action.";
        title = "Invalid Action";
        optionType = JOptionPane.WARNING_MESSAGE;
      }
      
      JOptionPane.showMessageDialog(this, msg, title, optionType);
      pswdField.setText("");
      newPswdField.setText("");
      pswdField.requestFocus();
    } catch (
    
    NullPointerException ex) {
      JOptionPane.showMessageDialog(this, "No Password yet created", "Invalid password. Try again.",
          JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid password. Try again.", JOptionPane.ERROR_MESSAGE);
    }
  }
}
