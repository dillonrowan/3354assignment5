package shippingstore;

import shippingstore.Validate;
import java.util.*;
import java.awt.*;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
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
  ShippingStore ss;

  public MainApp() {
    ss = ShippingStore.readDatabase();

    frame.setResizable(false);
    panelCont.setLayout(cl);
    panelCont.setPreferredSize(new Dimension(600,400));

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
      public void actionPerformed(ActionEvent e) {
        panelSecond.removeAll();
        cl.show(panelCont, "2");
        showPackage(panelSecond);
      }
    });

    //action for adding a package
    button2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panelThird.removeAll();
        cl.show(panelCont, "3");
        addNewPackage(panelThird);
      }
    });

    //action for deleting a package
    button3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panelFourth.removeAll();
        cl.show(panelCont, "4");
        deletePackage(panelFourth);
      }
    });

    //action for searching a package
    button4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panelFifth.removeAll();
        cl.show(panelCont, "5");
        searchPackage(panelFifth);
      }
    });

    //action for showing a list of users
    button5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panelSixth.removeAll();
        cl.show(panelCont, "6");
        showUsers(panelSixth);
      }
    });

    button6.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panelSeventh.removeAll();
        cl.show(panelCont, "7");
        addNewUser(panelSeventh);
      }
    });



    //action to exit
    button10.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

  //action for back button
    buttonBack.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
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

  public void showPackage(JPanel masterPanel){
    Object[] pColumnNames = {"Type", "Tracking #","Specification","Mailing Class","Other Detail 1", "Other Detail 2"};
    ArrayList<String> pListData = ss.getAllPackagesFormatted();
    if (!(pListData.isEmpty())){
      Object[][] pRowData = new Object[pListData.size()][6];
      for(int i = 0; i < pListData.size(); i++){
        String[] parts = pListData.get(i).split(" ");
        for(int j = 0; j < 6; j++){
          pRowData[i][j] = parts[j];
        }
      }
      JTable packageTable = new JTable(pRowData, pColumnNames);
      JScrollPane scrollPane = new JScrollPane(packageTable);
      masterPanel.add(scrollPane);
      masterPanel.add(buttonBack);
    } else {
      masterPanel.add(buttonBack);
      JOptionPane.showMessageDialog(null, "Database has no packages.", "No packages to display ", JOptionPane.WARNING_MESSAGE);
    }
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

    envelope.setActionCommand(envelope.getText());
    box.setActionCommand(box.getText());
    crate.setActionCommand(crate.getText());
    drum.setActionCommand(drum.getText());

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
    c.gridy = 2;
    panel1.add(box, c);
    c.gridy = 3;
    panel1.add(crate, c);
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

    fragile.setActionCommand(fragile.getText());
    books.setActionCommand(books.getText());
    catalogs.setActionCommand(catalogs.getText());
    dnb.setActionCommand(dnb.getText());
    na.setActionCommand(na.getText());

    ButtonGroup sbg = new ButtonGroup();
    sbg.add(fragile);
    sbg.add(books);
    sbg.add(catalogs);
    sbg.add(dnb);
    sbg.add(na);

    c.gridy = 1;
    panel2.add(fragile, c);
    c.gridy = 2;
    panel2.add(books, c);
    c.gridy = 3;
    panel2.add(catalogs, c);
    c.gridy = 4;
    panel2.add(dnb, c);
    c.gridy = 5;
    panel2.add(na, c);

    //mailingClass panel
    JPanel panel3 = new JPanel();
    panel3.setLayout(new GridBagLayout());
    panel3.setBorder(BorderFactory.createTitledBorder("Mailing Class"));

    JRadioButton firstClass = new JRadioButton("First-Class");
    JRadioButton priority = new JRadioButton("Priority");
    JRadioButton retail = new JRadioButton("Retail");
    JRadioButton ground = new JRadioButton("Ground");
    JRadioButton metro = new JRadioButton("Metro");

    firstClass.setActionCommand(firstClass.getText());
    priority.setActionCommand(priority.getText());
    retail.setActionCommand(retail.getText());
    ground.setActionCommand(ground.getText());
    metro.setActionCommand(metro.getText());

    ButtonGroup mbg = new ButtonGroup();
    mbg.add(firstClass);
    mbg.add(priority);
    mbg.add(retail);
    mbg.add(ground);
    mbg.add(metro);

    c.gridy = 1;
    panel3.add(firstClass, c);
    c.gridy = 2;
    panel3.add(priority, c);
    c.gridy = 3;
    panel3.add(retail, c);
    c.gridy = 4;
    panel3.add(ground, c);
    c.gridy = 5;
    panel3.add(metro, c);

    JPanel panel4 = new JPanel();
    panel4.setLayout(new GridBagLayout());
    JTextField tn = new JTextField(5);

    JLabel trackNo = new JLabel("Tracking Number:");
    JLabel entered = new JLabel();

    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel(); //OK button panel
    panel4.setBorder(BorderFactory.createTitledBorder("Attributes"));
    panel5.setBorder(BorderFactory.createTitledBorder("Drum Material"));
    panel5.setVisible(false);
    panel4.setVisible(false);
    panel6.setLayout(new BoxLayout(panel6, BoxLayout.PAGE_AXIS));
    panel6.add(Box.createVerticalGlue());
    JButton okButton = new JButton("OK");


    String[] drumStrings = {"Plastic", "Fiber"};
    JComboBox <String> drumAttributes = new JComboBox<>(drumStrings);
    drumAttributes.setPreferredSize(new Dimension(100, 25));

    JLabel drumText = new JLabel("Choose a material for the drum");
    JLabel p5Label1 = new JLabel();
    JLabel p5Label2 = new JLabel();

    drumAttributes.setVisible(false);
    panel5.add(drumText);
    panel5.add(drumAttributes);
    drumAttributes.setSelectedItem(null);

    JTextField p5textField1 = new JTextField(5);
    JTextField p5textField2 = new JTextField(5);

    p5textField1.setVisible(false);
    p5textField2.setVisible(false);
    panel6.add(okButton);  //put OK button in panel6
    panel6.add(buttonBack);

    c.gridy = 0;
    panel4.add(trackNo, c);
    c.gridy = 1;
    panel4.add(tn, c);
    c.gridy = 2;
    panel4.add(p5Label1, c);
    c.gridy = 3;
    panel4.add(p5textField1, c);
    c.gridy = 4;
    panel4.add(p5Label2, c);
    c.gridy = 5;
    panel4.add(p5textField2, c);

    //manage appearance based on what radio button is selected
    envelope.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        panel4.setVisible(true);
        panel5.setVisible(false);
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
        panel4.setVisible(true);
        panel5.setVisible(false);
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
        panel4.setVisible(true);
        panel5.setVisible(false);
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
        panel4.setVisible(true);
        panel5.setVisible(true);
        p5Label2.setText("Diameter: ");
        p5Label1.setVisible(false);
        p5Label2.setVisible(true);
        p5textField1.setVisible(false);
        p5textField2.setVisible(true);
        drumText.setVisible(true);
        drumAttributes.setVisible(true);

      }
    });

    //OK button listener, validates before adding
    okButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String getAtt1 = p5textField1.getText();
        String getAtt2 = p5textField2.getText();
        String material = (String)drumAttributes.getSelectedItem();
        String type = "";
        String mailingClass = "";
        String specification = "";
        String getTn = tn.getText();
        boolean isInDB = ss.packageExists(getTn);
        boolean isOK = false;

        //check at least one thing in each buttongroup is selected
        if (tbg.getSelection() == null || mbg.getSelection() == null ||
         sbg.getSelection() == null || getTn == null || getAtt1 == null){
           JOptionPane.showMessageDialog(null, "Please specify all package properties.", "Package Property Error",
            JOptionPane.ERROR_MESSAGE);
        } else if (drum.isSelected() && material == null) {
          JOptionPane.showMessageDialog(null, "Please select a material type", "Empty Field Error",
           JOptionPane.ERROR_MESSAGE);
        } else if (!(drum.isSelected()) && getAtt2.isEmpty()){
          JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Empty Field Error",
           JOptionPane.ERROR_MESSAGE);
        } else if (getTn.length() != 5) {
          JOptionPane.showMessageDialog(null,"Tracking number must be 5 characters.",
           "Character Limit Error", JOptionPane.ERROR_MESSAGE);
        } else if (isInDB){
          JOptionPane.showMessageDialog(null,"Tracking number already in database.",
           "Duplicate Tracking Number Error", JOptionPane.ERROR_MESSAGE);
         } else {
          type = tbg.getSelection().getActionCommand();
          mailingClass = mbg.getSelection().getActionCommand();
          specification = sbg.getSelection().getActionCommand();
          isOK = true;
        }

        if (envelope.isSelected() && isOK){
          if (!Validate.isPosInt(getAtt1) || !Validate.isPosInt(getAtt2)) {
            JOptionPane.showMessageDialog(null,"Height and width must be a positive integer.",
             "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
          } else {
            ss.addEnvelope(getTn, specification, mailingClass, Integer.parseInt(getAtt1), Integer.parseInt(getAtt2));
            JOptionPane.showMessageDialog(null, "Package successfully added!", "Package input successful",
             JOptionPane.INFORMATION_MESSAGE);
          }
        }

        if (box.isSelected() && isOK) {
          if (!Validate.isPosInt(getAtt1) || !Validate.isPosInt(getAtt2)) {
            JOptionPane.showMessageDialog(null,"Dimension and Volume must be a positive integer.",
             "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
          } else {
            ss.addBox(getTn, specification, mailingClass, Integer.parseInt(getAtt1), Integer.parseInt(getAtt2));
            JOptionPane.showMessageDialog(null, "Package successfully added!", "Package input successful",
             JOptionPane.INFORMATION_MESSAGE);
          }
        }

        if (crate.isSelected() && isOK) {
          if (!Validate.isPositive(getAtt1)) {
            JOptionPane.showMessageDialog(null,"Weight must be a positive Number.",
             "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
          } else {
            ss.addCrate(getTn, specification, mailingClass, Float.parseFloat(getAtt1), getAtt2);
            JOptionPane.showMessageDialog(null, "Package successfully added!", "Package input successful",
             JOptionPane.INFORMATION_MESSAGE);
          }
        }

        if (drum.isSelected() && isOK) {
          if (!Validate.isPositive(getAtt2)) {
            JOptionPane.showMessageDialog(null,"Weight must be a positive integer.",
             "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
          } else {
            ss.addDrum(getTn, specification, mailingClass, material, Float.parseFloat(getAtt2));
            JOptionPane.showMessageDialog(null, "Package successfully added!", "Package input successful",
             JOptionPane.INFORMATION_MESSAGE);
          }
        }
      }
    });
    masterPanel.add(panel1);
    masterPanel.add(panel2);
    masterPanel.add(panel3);
    masterPanel.add(panel4);
    masterPanel.add(panel5);
    masterPanel.add(panel6);
  }

  public void deletePackage(JPanel masterPanel) {
    JButton deleteButton = new JButton("Delete");
    deleteButton.setEnabled(false);
    masterPanel.add(deleteButton);
    masterPanel.add(buttonBack);
    masterPanel.setLayout(new FlowLayout());
    ArrayList<String> pListData = ss.getAllPackagesFormatted();
    Object[] pColumnNames = {"Type", "Tracking #","Specification","Mailing Class","Other Detail 1", "Other Detail 2"};
    Object[][] pRowData = new Object[pListData.size()][6];
    if (!(pListData.isEmpty())){
      deleteButton.setEnabled(true);
      for(int i = 0; i < pListData.size(); i++){
        String[] parts = pListData.get(i).split(" ");
        for(int j = 0; j < 6; j++){
          pRowData[i][j] = parts[j];
        }
      }
    } else {
      JOptionPane.showMessageDialog(null, "Database has no packages.", "No packages to display ", JOptionPane.WARNING_MESSAGE);
    }
    DefaultTableModel model = new DefaultTableModel(pRowData, pColumnNames){
      public boolean isCellEditable(int row, int column){
        return false;
      }
    };
    JTable packageTable = new JTable();
    packageTable.setModel(model);
    JScrollPane scrollPane = new JScrollPane(packageTable);
    masterPanel.add(scrollPane);
    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int col = 1;
        int row = packageTable.getSelectedRow();
        String value = model.getValueAt(row, col).toString();
        model.removeRow(row);
        model.fireTableDataChanged();
        ss.deletePackage(value);
      }
    });
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

  public void showUsers(JPanel masterPanel){
    Object[] pColumnNames = {"Type", "ID #","First Name","Last Name","Other Detail 1", "Other Detail 2", "Other Detail 3"};
    ArrayList<String> uListData = ss.getAllUsersFormatted();
    if (!(uListData.isEmpty())){
      Object[][] uRowData = new Object[uListData.size()][7];
      for(int i = 0; i < uListData.size(); i++){
        String[] parts = uListData.get(i).split(" ");
        for(int j = 0; j < 7; j++){
          uRowData[i][j] = parts[j];
        }
      }
      JTable packageTable = new JTable(uRowData, pColumnNames);
      JScrollPane scrollPane = new JScrollPane(packageTable);
      masterPanel.add(scrollPane);
      masterPanel.add(buttonBack);
    } else {
      masterPanel.add(buttonBack);
      JOptionPane.showMessageDialog(null, "Database has no users.", "No users to display ", JOptionPane.WARNING_MESSAGE);
    }
  }

  public void addNewUser(JPanel masterPanel) {
    masterPanel.setLayout(new GridLayout(2,2));
    JPanel panel1 = new JPanel(new FlowLayout());
    JPanel panel2 = new JPanel(new FlowLayout());
    JPanel panel3 = new JPanel(new FlowLayout());
    JPanel panel4 = new JPanel(new FlowLayout());
    JLabel firstName = new JLabel("First Name:");
    JLabel lastName = new JLabel("Last Name:");
    JLabel phoneNo = new JLabel("Phone Number:");
    JLabel address = new JLabel("Address:           ");
    JLabel monSalary = new JLabel("Monthly Salary:");
    JLabel ssn = new JLabel("SSN (9):             ");
    JLabel bank = new JLabel("Bank Account #:");
    JTextField firstText = new JTextField(17);
    JTextField lastText = new JTextField(17);
    JTextField phoneText = new JTextField(17);
    JTextField addText = new JTextField(17);
    JTextField monText = new JTextField(17);
    JTextField ssnText = new JTextField(17);
    JTextField bankText = new JTextField(17);
    JButton okButton = new JButton("OK");

    //customer attributes
    panel3.add(phoneNo);
    panel3.add(phoneText);
    panel3.add(address);
    panel3.add(addText);

    phoneNo.setVisible(false);
    phoneText.setVisible(false);
    address.setVisible(false);
    addText.setVisible(false);

    //employee attributes
    panel3.add(monSalary);
    panel3.add(monText);
    panel3.add(ssn);
    panel3.add(ssnText);
    panel3.add(bank);
    panel3.add(bankText);

    monSalary.setVisible(false);
    monText.setVisible(false);
    ssn.setVisible(false);
    ssnText.setVisible(false);
    bank.setVisible(false);
    bankText.setVisible(false);

    panel2.add(firstName);
    panel2.add(firstText);
    panel2.add(lastName);
    panel2.add(lastText);
    panel4.add(okButton);  //put OK button in panel6
    panel4.add(buttonBack);

    //radio buttons
    panel1.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    panel1.setBorder(BorderFactory.createTitledBorder("User Type"));

    JRadioButton customer = new JRadioButton("Customer");
    JRadioButton employee = new JRadioButton("Employee");

    customer.setActionCommand(customer.getText());
    employee.setActionCommand(employee.getText());

    ButtonGroup tbg = new ButtonGroup();
    tbg.add(customer);
    tbg.add(employee);


    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 0;
    panel1.add(customer, c);
    c.gridy = 1;
    panel1.add(employee, c);

    //manage appearance based on what radio button is selected
    customer.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        monSalary.setVisible(false);
        monText.setVisible(false);
        ssn.setVisible(false);
        ssnText.setVisible(false);
        bank.setVisible(false);
        bankText.setVisible(false);
        phoneNo.setVisible(true);
        phoneText.setVisible(true);
        address.setVisible(true);
        addText.setVisible(true);
      }
    });
    //
    employee.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        phoneNo.setVisible(false);
        phoneText.setVisible(false);
        address.setVisible(false);
        addText.setVisible(false);
        monSalary.setVisible(true);
        monText.setVisible(true);
        ssn.setVisible(true);
        ssnText.setVisible(true);
        bank.setVisible(true);
        bankText.setVisible(true);
      }
    });

    //OK button listener, validates before adding
     okButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        boolean isOK = false;
        //check at least one thing in each buttongroup is selected
        if (tbg.getSelection() == null || firstText.getText() == null || lastText.getText() == null){
           JOptionPane.showMessageDialog(null, "Input all user details.", "User Property Error",
            JOptionPane.ERROR_MESSAGE);
        } else if (customer.isSelected() && (phoneText.getText() == null || addText.getText() == null)) {
          JOptionPane.showMessageDialog(null, "Enter valid customer information", "Empty Customer Fields Error",
           JOptionPane.ERROR_MESSAGE);
        } else if (employee.isSelected() && (monText.getText() == null || ssnText.getText() == null || bankText.getText() == null)) {
          JOptionPane.showMessageDialog(null, "Enter valid employee information", "Empty Employee Fields Error",
           JOptionPane.ERROR_MESSAGE);
        } else if (employee.isSelected() && ssnText.getText().length() != 9) {
          JOptionPane.showMessageDialog(null,"Social Security Number must be 9 digits.",
           "Digit Limit Error", JOptionPane.ERROR_MESSAGE);
        } else {
          isOK = true;
        }

        if (customer.isSelected() && isOK){
          ss.addCustomer(firstText.getText(), lastText.getText(), phoneText.getText(), addText.getText());
          JOptionPane.showMessageDialog(null, "Customer successfully added!", "Custoer Input Successful",
           JOptionPane.INFORMATION_MESSAGE);
        }

        if (employee.isSelected() && isOK) {
          if (!Validate.isPosInt(ssnText.getText()) || !Validate.isPositive(monText.getText()) || !Validate.isPosInt(bankText.getText())) {
            JOptionPane.showMessageDialog(null,"Social Security Number, Bank Account Number and Salary must be positive numbers.",
             "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
          } else {
            int social = Integer.parseInt(ssnText.getText());
            int bankNo = Integer.parseInt(bankText.getText());
            float salary = Float.parseFloat(monText.getText());
            if (social < 10000000 || social > 999999999){
              JOptionPane.showMessageDialog(null,"Social Security Number must be 9 digits.",
               "Digit Limit Error", JOptionPane.ERROR_MESSAGE);
            } else {
              ss.addEmployee(firstText.getText(), lastText.getText(), social, salary, bankNo);
              JOptionPane.showMessageDialog(null, "Package successfully added!", "Package input successful",
               JOptionPane.INFORMATION_MESSAGE);
            }
          }
        }
      }
   });
    masterPanel.add(panel1);
    masterPanel.add(panel2);
    masterPanel.add(panel3);
    masterPanel.add(panel4);
  }
}
