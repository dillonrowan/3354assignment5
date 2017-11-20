package shippingstore;

import shippingstore.Validate;
import java.util.*;
import java.awt.*;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Box;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static java.awt.GridBagConstraints.*;

public class MainApp {

  JFrame frame = new JFrame("Shipping Store Database");
  JLabel label = new JLabel("Welcome! Please choose menu option.");
  JPanel panelCont = new JPanel();
  JPanel panelFirst = new JPanel();
  JPanel panelSecond = new JPanel();
  JPanel panelThird = new JPanel();
  JPanel panelFourth = new JPanel();
  JPanel panelFifth = new JPanel();
  JPanel panelSixth = new JPanel();
  JPanel panelSeventh = new JPanel();

  JButton buttonBack = new JButton("Back");
  CardLayout cl = new CardLayout();

  public MainApp() {
    ShippingStore ss;
    ss = ShippingStore.readDatabase();

    frame.setResizable(false);
    panelCont.setLayout(cl);
    panelCont.setPreferredSize(new Dimension(600,400));

    //panelFirst contents
    panelFirst.setLayout(new GridLayout(0,1));
    panelFirst.add(label);
    JButton button1 = new JButton ("Show all existing packages in the database.");
    panelFirst.add(button1);
    JButton button2 = new JButton ("Add a new package to the database.");
    panelFirst.add(button2);
    JButton button3 = new JButton ("Delete a package from a database (given its tracking number).");
    panelFirst.add(button3);
    JButton button4 = new JButton ("Search for a package (given its tracking number).");
    panelFirst.add(button4);
    JButton button5 = new JButton ("Show list of users.");
    panelFirst.add(button5);
    JButton button6 = new JButton ("Add a new user to the database.");
    panelFirst.add(button6);
    JButton button7 = new JButton ("Update user info (given their id).");
    panelFirst.add(button7);
    JButton button8 = new JButton ("Deliver a package.");
    panelFirst.add(button8);
    JButton button9 = new JButton ("Show a list of transactions.");
    panelFirst.add(button9);
    JButton button10 = new JButton ("Exit program.");
    panelFirst.add(button10);

    panelSecond.add(buttonBack);
    panelThird.add(buttonBack);
    panelFourth.add(buttonBack);
    panelFifth.add(buttonBack);
    panelSixth.add(buttonBack);
    panelSeventh.add(buttonBack);

    //panelFirst.setBackground(Color.blue);

    panelCont.add(panelFirst, "1");  //Main menu
    panelCont.add(panelSecond, "2"); //Show all existing packages
    panelCont.add(panelThird, "3");  //Add a new package to the Database
    panelCont.add(panelFourth, "4"); //Delete a package from the database
    panelCont.add(panelFifth, "5");  //Search for package given its tracking number
    panelCont.add(panelSixth, "6");  //Show a list of users in the database
    panelCont.add(panelSeventh, "7");//add a new user to the database

    cl.show(panelCont, "1");


  //action for Show all existing packages in the database.
    button1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        cl.show(panelCont, "2");
        String text = ss.getAllPackagesFormatted();
        JLabel gapLabel = new JLabel(text, SwingConstants.CENTER);
        panelSecond.add(gapLabel);
      }
    });

    //action for adding a package
    button2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        panelThird.removeAll();
        cl.show(panelCont, "3");
        addNewPackage(panelThird);
      }
    });

    //action for deleting a package
    button3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        cl.show(panelCont, "4");
        deletePackage(panelFourth);
      }
    });

    //action for searching a package
    button4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        cl.show(panelCont, "5");
        searchPackage(panelFifth);
      }
    });

    //action for showing a list of users
    button5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        cl.show(panelCont, "6");
        String text = ss.getFormattedUserList();
        JLabel gapLabel = new JLabel(text);
        panelSixth.add(gapLabel);
      }
    });

    button6.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        cl.show(panelCont, "7");
      }
    });



    //action to exit
    button10.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        System.exit(0);
      }
    });

  //action for back button
    buttonBack.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        cl.show(panelCont, "1");
      }
    });

    frame.add(panelCont);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }


  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable () {
      @Override
      public void run() {
         new MainApp();
      }
    });
  }


  public void addNewUser(JPanel masterPanel) {
    masterPanel.setLayout(new BorderLayout());
    JPanel panel1 = new JPanel(new GridBagLayout());
    JPanel panel2 = new JPanel(new FlowLayout());
    JPanel panelNorth = new JPanel();
    JPanel panelEast = new JPanel();
    JPanel panelSouth = new JPanel();
    JPanel panelWest = new JPanel();

    JLabel fn = new JLabel("First Name:");
    JLabel ln = new JLabel("Last Name:");
    JLabel phoneNo = new JLabel("PhoneNumber");
    JLabel monSalary = new JLabel("Monthly Salary");
    JLabel ssn = new JLabel("SSN (9-digit):");
    JLabel bankNo = new JLabel("Bank Account Number:");
    JTextField firstText = new JTextField(10);
    JTextField lastText = new JTextField(10);
    JTextField phoneText = new JTextField(10);
    JTextField monText = new JTextField(10);
    JTextField ssnText = new JTextField(10);
    JTextField bankText = new JTextField(10);

    JRadioButton cust = new JRadioButton("Customer");
    JRadioButton emp = new JRadioButton("Employee");

    ButtonGroup bg = new ButtonGroup();
    bg.add(cust);
    bg.add(emp);

    panelNorth.setPreferredSize(new Dimension(600, 40));
    panelEast.setPreferredSize(new Dimension(40, 400));
    panelSouth.setPreferredSize(new Dimension(600, 40));
    panelWest.setPreferredSize(new Dimension(40, 400));

    JLabel title = new JLabel("Select User Type");
    title.setFont(new Font("Serif", Font.BOLD, 17));
    JTextField search = new JTextField(5);
    GridBagConstraints c = new GridBagConstraints();
    panel1.setBorder(BorderFactory.createLineBorder(Color.black));

    // c.GridBagConstraints.PAGE_START;
    // c.weightx = 0.1;
    // c.weighty = 0.1;
    // panel1.add(title);

    c.gridx = 0;
    c.gridy = 0;
    panel1.add(fn);

    c.gridx = 0;
    c.gridy = 1;
    panel1.add(firstText);

    c.gridx = 0;
    c.gridy = 2;
    panel1.add(ln);

    c.gridx = 0;
    c.gridy = 3;
    panel1.add(lastText);

    masterPanel.add(panelNorth, BorderLayout.NORTH);
    masterPanel.add(panelEast, BorderLayout.EAST);
    masterPanel.add(panelSouth, BorderLayout.SOUTH);
    masterPanel.add(panelWest, BorderLayout.WEST);
    masterPanel.add(panel1);
  }

  public void searchPackage(JPanel masterPanel) {
    masterPanel.setLayout(new BorderLayout());
    JPanel panel1 = new JPanel(new FlowLayout());
    JPanel panelNorth = new JPanel();
    JPanel panelEast = new JPanel();
    JPanel panelSouth = new JPanel();
    JPanel panelWest = new JPanel();
    JLabel dummy1 = new JLabel("     ");
    JLabel dummy2 = new JLabel("     ");
    JLabel dummy3 = new JLabel("     ");
    JLabel dummy4 = new JLabel("     ");
    JLabel result = new JLabel();

    panelNorth.setPreferredSize(new Dimension(600, 40));
    panelEast.setPreferredSize(new Dimension(40, 400));
    panelSouth.setPreferredSize(new Dimension(600, 40));
    panelWest.setPreferredSize(new Dimension(40, 400));

    JLabel trackNo = new JLabel("Enter tracking number of package to search for (string):");
    trackNo.setFont(new Font("Serif", Font.BOLD, 17));
    JTextField search = new JTextField(5);
    GridBagConstraints c = new GridBagConstraints();
    panel1.setBorder(BorderFactory.createLineBorder(Color.black));


    panel1.add(trackNo);
    panel1.add(dummy1);
    panel1.add(dummy2);
    panel1.add(search);
    panel1.add(dummy3);
    panel1.add(dummy4);
    panel1.add(result);

    masterPanel.add(panelNorth, BorderLayout.NORTH);
    masterPanel.add(panelEast, BorderLayout.EAST);
    masterPanel.add(panelSouth, BorderLayout.SOUTH);
    masterPanel.add(panelWest, BorderLayout.WEST);
    masterPanel.add(panel1);
  }

  public void deletePackage(JPanel masterPanel) {
    JPanel panel1 = new JPanel(new FlowLayout());

    //FILL JTABLE WITH CONTENTS OF packageList, ROW BY ROW
    //TRY MAKING getSimplePackageList() INTO ShippingStore.java

    // ArrayList<Package> p = ss.getSimplePackageList();
     JButton deleteButton = new JButton("Delete");
     String[] columnNames = {"Package Type", "Tracking #", "Specification", "Mailing Class", "Other Details"};
     Object[][] dummyData = {
       {"TEST1", "AAA", "BBB", "CCC", "DDD"},
       {"TEST2", "###", "^^^", "~~!!!", "@@@??", "**()$"},
       {"TEST3", "111", "222.222", "333", "444", "555"},
     };
    // Object [] rowData = new Object[5];
    //
    //   for(int i = 0; i < p.size(); i++) {
    //     rowData[0] = p.get(i).ptn;
    //     rowData[0] = p.get(i).specification;
    //     rowData[0] = p.get(i).mailingClass;
    //     rowData[0] = p.get(i).ptn;
    //     rowData[0] = p.get(i).ptn;
    //   }
    //
    JTable table = new JTable(dummyData, columnNames);
    table.setPreferredScrollableViewportSize(new Dimension(550, 50));
    table.setFillsViewportHeight(true);

    JScrollPane scrollPane = new JScrollPane(table);
    panel1.add(scrollPane);
    panel1.add(deleteButton);

  }

  public void addNewPackage(JPanel masterPanel) {
    masterPanel.setLayout(new GridLayout(0,3));
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    panel1.setBorder(BorderFactory.createTitledBorder("Type"));

    JRadioButton envelope = new JRadioButton("Envelope");
    JRadioButton box = new JRadioButton("Box");
    JRadioButton crate = new JRadioButton("Crate");
    JRadioButton drum = new JRadioButton("Drum");

    ButtonGroup tbg = new ButtonGroup();
    tbg.add(envelope);
    tbg.add(box);
    tbg.add(crate);
    tbg.add(drum);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 1;
    panel1.add(envelope, c);

    c.gridx = 0;
    c.gridy = 2;
    panel1.add(box, c);

    c.gridx = 0;
    c.gridy = 3;
    panel1.add(crate, c);

    c.gridx = 0;
    c.gridy = 4;
    panel1.add(drum, c);

    //specification panel
    JPanel panel2 = new JPanel();
    panel2.setLayout(new GridBagLayout());
    panel2.setBorder(BorderFactory.createTitledBorder("Specification"));

    JRadioButton fragile = new JRadioButton("Fragile");
    JRadioButton books = new JRadioButton("Books");
    JRadioButton catalogs = new JRadioButton("Catalogs");
    JRadioButton dnb = new JRadioButton("Do-not-bend");
    JRadioButton na = new JRadioButton("N/A");

    ButtonGroup sbg = new ButtonGroup();
    sbg.add(fragile);
    sbg.add(books);
    sbg.add(catalogs);
    sbg.add(dnb);
    sbg.add(na);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 1;
    panel2.add(fragile, c);

    c.gridx = 0;
    c.gridy = 2;
    panel2.add(books, c);

    c.gridx = 0;
    c.gridy = 3;
    panel2.add(catalogs, c);

    c.gridx = 0;
    c.gridy = 4;
    panel2.add(dnb, c);

    c.gridx = 0;
    c.gridy = 5;
    panel2.add(na, c);

    //mailingClass panel
    JPanel panel3 = new JPanel();
    panel3.setLayout(new GridBagLayout());
    panel3.setBorder(BorderFactory.createTitledBorder("Mailing Class"));

    JRadioButton firstClass = new JRadioButton("First-Class");
    JRadioButton priority = new JRadioButton("Priority");
    JRadioButton retial = new JRadioButton("Retail");
    JRadioButton ground = new JRadioButton("Ground");
    JRadioButton metro = new JRadioButton("Metro");

    ButtonGroup mbg = new ButtonGroup();
    mbg.add(firstClass);
    mbg.add(priority);
    mbg.add(retial);
    mbg.add(ground);
    mbg.add(metro);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 1;
    panel3.add(firstClass, c);

    c.gridx = 0;
    c.gridy = 2;
    panel3.add(priority, c);

    c.gridx = 0;
    c.gridy = 3;
    panel3.add(retial, c);

    c.gridx = 0;
    c.gridy = 4;
    panel3.add(ground, c);

    c.gridx = 0;
    c.gridy = 5;
    panel3.add(metro, c);

    //trackingNumber
    JPanel panel4 = new JPanel();
    panel4.setLayout(new GridBagLayout());
    JTextField tn = new JTextField(5);

    JLabel trackNo = new JLabel("Enter Tracking Number:");
    trackNo.setFont(new Font("Serif", Font.BOLD, 13));
    JLabel entered = new JLabel();

    //special attributes
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel(); //OK button panel
    panel6.setLayout(new BoxLayout(panel6, BoxLayout.PAGE_AXIS));
    panel6.add(Box.createVerticalGlue());
    JButton okButton = new JButton("OK");


    String[] drumStrings = {"Plastic", "Fiber"};
    JComboBox <String> drumAttributes = new JComboBox<>(drumStrings);
    drumAttributes.setPreferredSize(new Dimension(100, 25));

    JLabel drumText = new JLabel();
    JLabel p5Label1 = new JLabel();
    JLabel p5Label2 = new JLabel();

    drumAttributes.setVisible(false);
    panel5.add(drumText);
    panel5.add(drumAttributes);
    drumAttributes.setSelectedItem(null);

    JTextField p5textField1 = new JTextField(5);
    JTextField p5textField2 = new JTextField(5);

    Font f=new Font("Arial", Font.BOLD,13);
    p5Label1.setFont(f);
    p5Label2.setFont(f);

    p5textField1.setVisible(false);
    p5textField2.setVisible(false);
    panel6.add(okButton);  //put OK button in panel6


    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    panel4.add(trackNo, c);

    c.insets = new Insets(0,0,20,0);
    c.gridx = 0;
    c.gridy = 1;
    panel4.add(tn, c);

    c.insets = new Insets(0,0,0,0);
    c.gridx = 0;
    c.gridy = 2;
    panel4.add(p5Label1, c);

    c.insets = new Insets(0,0,20,0);
    c.gridx = 0;
    c.gridy = 3;
    panel4.add(p5textField1, c);

    c.insets = new Insets(0,0,0,0);
    c.gridx = 0;
    c.gridy = 4;
    panel4.add(p5Label2, c);

    c.gridx = 0;
    c.gridy = 5;
    panel4.add(p5textField2, c);

    //manage appearance based on what radio button is selected
    envelope.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        p5Label1.setText("Height:");
        p5Label2.setText("Width:");
        p5Label1.setVisible(true);
        p5Label2.setVisible(true);
        p5textField1.setVisible(true);
        p5textField2.setVisible(true);
        drumText.setVisible(false);
        drumAttributes.setVisible(false);
        okButton.setVisible(true);
      }
    });

    box.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        p5Label1.setText("Dimension:");
        p5Label2.setText("Volume:");
        p5Label1.setVisible(true);
        p5Label2.setVisible(true);
        p5textField1.setVisible(true);
        p5textField2.setVisible(true);
        drumText.setVisible(false);
        drumAttributes.setVisible(false);
        okButton.setVisible(true);
      }
    });

    crate.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        p5Label1.setText("Weight:");
        p5Label2.setText("Content:");
        p5Label1.setVisible(true);
        p5Label2.setVisible(true);
        p5textField1.setVisible(true);
        p5textField2.setVisible(true);
        drumText.setVisible(false);
        drumAttributes.setVisible(false);
        okButton.setVisible(true);
      }
    });

    drum.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        p5Label2.setText("Diameter: ");
        p5Label1.setVisible(false);
        p5Label2.setVisible(true);
        p5textField1.setVisible(false);
        p5textField2.setVisible(true);
        drumText.setVisible(true);
        drumAttributes.setVisible(true);

      }
    });

    drumAttributes.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String msg = (String)cb.getSelectedItem();
        switch(msg) {
          case "Plastic": drumText.setText("You selected Plastic.");
            break;
          case "Fiber": drumText.setText("You selected Fiber");
            break;
          default: drumText.setText("There is an error.");
        }
      }
    });

    //OK button listener, validates before adding
    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String getAtt1 = p5textField1.getText();
        String getAtt2 = p5textField2.getText();
        String getTn = tn.getText();

        //check at least one thing in each buttongroup is selected
        if(tbg.getSelection() == null || mbg.getSelection() == null ||
         sbg.getSelection() == null) {
           JOptionPane.showMessageDialog(null, "Please specify all package properties.", "Package Property Error",
            JOptionPane.ERROR_MESSAGE);
         }

        if(drum.isSelected() && getAtt2.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Please fill in all fields", "Empty Field Error",
           JOptionPane.ERROR_MESSAGE);
        }

        if((getTn.isEmpty() || getAtt1.isEmpty() || getAtt2.isEmpty()) && !drum.isSelected()) {
          JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Empty Field Error",
           JOptionPane.ERROR_MESSAGE);
        }

        if(getTn.length() > 5) {
          JOptionPane.showMessageDialog(frame,"Tracking number must be 5 characters or less.",
           "Character Limit Error", JOptionPane.ERROR_MESSAGE);
          tn.setText("");
        }

        if(envelope.isSelected()){
          if(!Validate.isPosInt(getAtt1) || !Validate.isPosInt(getAtt2)) {
            JOptionPane.showMessageDialog(frame,"Height and Widtht must be a positive integer.",
             "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
          }
          //It has passed, add it and return to cl.show(panelCont, "1")
        }

        if(box.isSelected()) {
          if(!Validate.isPosInt(getAtt1) || !Validate.isPosInt(getAtt2)) {
            JOptionPane.showMessageDialog(frame,"Dimension and Volume must be a positive integer.",
             "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
          }
          //It has passed, add it and return to cl.show(panelCont, "1")
        }

        if(crate.isSelected()) {
          if(!Validate.isPositive(getAtt1)) {
            JOptionPane.showMessageDialog(frame,"Weight must be a positive Number.",
             "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
          }
          //It has passed, add it and return to cl.show(panelCont, "1")
        }

        if(drum.isSelected()) {
          if(!Validate.isPositive(getAtt2)) { //THIS VALIDATION IS NOT WORKING
            JOptionPane.showMessageDialog(frame,"Weight must be a positive integer.",
             "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
          }
          //It has passed, add it and return to cl.show(panelCont, "1")
        }


      }
    });

    //Add panels
    masterPanel.add(panel1);
    masterPanel.add(panel2);
    masterPanel.add(panel3);
    masterPanel.add(panel4);
    masterPanel.add(panel5);
    masterPanel.add(panel6);
  }
}
