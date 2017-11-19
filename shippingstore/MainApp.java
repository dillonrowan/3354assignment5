package shippingstore;

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

    //panelSecond.add(buttonBack);
    //panelThird.add(buttonBack);

    //panelFirst.setBackground(Color.blue);

    panelCont.add(panelFirst, "1");
    panelCont.add(panelSecond, "2");
    panelCont.add(panelThird, "3");

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
        cl.show(panelCont, "3");
        addNewPackage(panelThird);
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

  public void addNewPackage(JPanel masterPanel) {
    masterPanel.setLayout(new GridLayout(0,3));
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    panel1.setBorder(BorderFactory.createTitledBorder("Type"));
    c.fill = GridBagConstraints.HORIZONTAL;

    JRadioButton envelope = new JRadioButton("Envelope");
    JRadioButton box = new JRadioButton("Box");
    JRadioButton crate = new JRadioButton("Crate");
    JRadioButton drum = new JRadioButton("Drum");

    ButtonGroup tbg = new ButtonGroup();
    tbg.add(envelope);
    tbg.add(box);
    tbg.add(crate);
    tbg.add(drum);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 1;
    panel1.add(envelope, c);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 2;
    panel1.add(box, c);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 3;
    panel1.add(crate, c);

    c.weightx = 0.1;
    c.weighty = 0.1;
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

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 2;
    panel2.add(books, c);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 3;
    panel2.add(catalogs, c);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 4;
    panel2.add(dnb, c);

    c.weightx = 0.1;
    c.weighty = 0.1;
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

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 2;
    panel3.add(priority, c);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 3;
    panel3.add(retial, c);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 4;
    panel3.add(ground, c);

    c.weightx = 0.1;
    c.weighty = 0.1;
    c.gridx = 0;
    c.gridy = 5;
    panel3.add(metro, c);

    //trackingNumber panel
    JPanel panel4 = new JPanel(new GridBagLayout());
    GridBagConstraints tgc = new GridBagConstraints();
    JTextField tf = new JTextField(5);

    JLabel trackNo = new JLabel("Enter Tracking Number");
    //trackNo.setFont(new Font("Serif", Font.BOLD, 13));
    JLabel entered = new JLabel();

    tf.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if(tf.getText().length() == 5) {
          String input = tf.getText();
          entered.setText("Tracking Number: " + input);
          tf.setText("");

        }else{
          JOptionPane.showMessageDialog(frame,"Tracking number must be 5 characters.", "Character Limit Error", JOptionPane.ERROR_MESSAGE);
          tf.setText("");
        }
      }
    });

    tgc.gridx = 0;
    tgc.gridy = 0;
    panel4.add(trackNo, tgc);
    tgc.gridy = 10;
    panel4.add(tf, tgc);
    tgc.gridy = 20;
    panel4.add(entered,tgc);




    //special attributes panel
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel(); //OK button panel
    panel5.setLayout(new FlowLayout());
    GridBagConstraints sgc = new GridBagConstraints();
    JLabel p5Label1 = new JLabel();
    JLabel p5Label2 = new JLabel();
    JButton okButton = new JButton("OK");
    JLabel dummy1 = new JLabel(" ");
    JLabel dummy2 = new JLabel(" ");
    JLabel dummy3 = new JLabel(" ");
    JLabel dummy4 = new JLabel(" ");
    JLabel dummy5 = new JLabel(" ");
    JTextField p5textField1 = new JTextField(5);
    JTextField p5textField2 = new JTextField(5);


    Font f=new Font("Arial", Font.BOLD,13);
    p5Label1.setFont(f);
    p5Label2.setFont(f);


    // p5Label1.setBounds(100,50,100,40);
    // p5Label2.setBounds(100,140,100,40);
    // p5textField1.setBounds(250,50,200,40);
    // p5textField2.setBounds(250,140,200,40);

    p5textField1.setVisible(false);
    p5textField2.setVisible(false);
    okButton.setVisible(false);

    panel6.add(okButton);
    panel5.add(p5Label1);
    panel5.add(p5textField1);
    panel5.add(dummy1);
    panel5.add(dummy2);
    panel5.add(dummy3);
    panel5.add(dummy4);
    panel5.add(dummy5);
    panel5.add(p5Label2);
    panel5.add(p5textField2);
    envelope.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        p5Label1.setText("Height:");
        p5Label2.setText("Width: ");
        p5Label1.setVisible(true);
        p5Label2.setVisible(true);
        p5textField1.setVisible(true);
        p5textField2.setVisible(true);
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
        okButton.setVisible(true);
      }
    });

    crate.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        p5Label1.setText("Weight: ");
        p5Label2.setText("Content: ");
        p5Label1.setVisible(true);
        p5Label2.setVisible(true);
        p5textField1.setVisible(true);
        p5textField2.setVisible(true);
        okButton.setVisible(true);
      }
    });

    drum.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        p5Label1.setText("Material: ");
        p5Label2.setText("Diameter: ");
        p5Label1.setVisible(true);
        p5Label2.setVisible(true);
        p5textField1.setVisible(true);
        p5textField2.setVisible(true);
        okButton.setVisible(true);
      }
    });

    //okButton.setActionCommand(getText(p5TextField1));

    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
         String getTxt = p5textField1.getText();
    	   int messageType = JOptionPane.PLAIN_MESSAGE;
    	   JOptionPane.showMessageDialog(null, getTxt, "Java Programming Forums!!", messageType);
      }
    });

    //Add panels
    //first row
    // mc.gridx = 0;
    // mc.gridy = 0;
    // mc.weightx = 0.5;
    // mc.weighty = 0.5;
    // mc.gridheight = 200;
    // mc.gridwidth = 133;
    //mc.fill = GridBagConstraints.VERTICAL;
    //mc.fill = GridBagConstraints.VERTICAL;;
    masterPanel.add(panel1);
    masterPanel.add(panel2);
    masterPanel.add(panel3);
    masterPanel.add(panel4);
    masterPanel.add(panel5);
    masterPanel.add(panel6);
  }
}
