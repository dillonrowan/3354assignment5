package shippingstore;

import java.util.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Box;

/**
* Main access point
*/
public class MainApp {
  ShippingStore ss;
  public final Scanner sc= new Scanner(System.in); // Used to read from System's standard input

  /**
   * Constructor
   */
  public MainApp() {
    ss = ShippingStore.readDatabase();
    //this.sc = new Scanner(System.in);
  }

  /**
  * This method servers as the main interface between the program and the user.
  * The method interacts with the user by printing out a set of options, and
  * asking the user to select one.
  */
  public void runSoftware() {
    /** Create an empty, labeled panel and display it */
    JFrame frame = new JFrame("Shipping Store Database");
    JLabel label = new JLabel("Welcome! Please choose menu option.");
    JPanel panel = new JPanel(new GridLayout(10, 1, 1, 1));

    JButton button1 = new JButton ("Show all existing packages in the database.");
    panel.add(button1);

    JButton button2 = new JButton ("Add a new package to the database.");
    panel.add(button2);

    JButton button3 = new JButton ("Delete a package from a database (given its tracking number).");
    panel.add(button3);

    JButton button4 = new JButton ("Search for a package (given its tracking number).");
    panel.add(button4);

    JButton button5 = new JButton ("Show list of users.");
    panel.add(button5);

    JButton button6 = new JButton ("Add a new user to the database.");
    panel.add(button6);

    JButton button7 = new JButton ("Update user info (given their id).");
    panel.add(button7);

    JButton button8 = new JButton ("Deliver a package.");
    panel.add(button8);

    JButton button9 = new JButton ("Show a list of transactions.");
    panel.add(button9);

    JButton button10 = new JButton ("Exit program.");
    panel.add(button10);

    button1.addActionListener(new showAllPackages());
    button2.addActionListener(new addNewPackage());
    //button3.addActionListener(new removePackage());
    button10.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        System.exit(0);
      }
    });

    label.setHorizontalAlignment(SwingConstants.CENTER);
    frame.add(label,BorderLayout.NORTH);
    frame.add(panel);

    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    }



   /**
    * This method prints out all the package currently in the inventory, in a
    * formatted manner.
    */

    private class showAllPackages extends MainApp implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         // ss.addBox("Test1", "Fragile", "Retail", 3, 9);
         JFrame frame = new JFrame("All Packages");
         frame.setVisible(true);
         frame.setSize(900, 600);
         String text = ss.getAllPackagesFormatted();
         JLabel label = new JLabel(text, SwingConstants.CENTER);
         JPanel panel = new JPanel();
         frame.add(panel);
         panel.add(label);
       }
    }

   /**
     * This method allows the user to enter a new package to the list
     * database.
     * @throws shippingstore.BadInputException bad input
     */
    private class addNewPackage extends MainApp implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         JFrame frame = new JFrame("Add a new package");
         frame.setVisible(true);
         frame.setSize(900, 600);

         //Text and Tab panel
          JPanel panel2 = new JPanel();
          JLabel trackingNumber = new JLabel("Enter packing number");
          JTextField tf = new JTextField(5);
          panel2.add(tf);
          //JTabbedPane jtp = new JTabbedPane();

         //Radio Button panel
         JPanel panel1 = new JPanel();
         panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
         JRadioButton envelope = new JRadioButton("Envelope");
         JRadioButton box = new JRadioButton("Box");
         JRadioButton crate = new JRadioButton("Crate");
         JRadioButton drum = new JRadioButton("Drum");
         ButtonGroup group = new ButtonGroup();
         group.add(envelope);
         group.add(box);
         group.add(crate);
         group.add(drum);



         //Add space between radio buttons and add to panel1
         panel1.add(envelope);
         //panel1.add(Box.createRigidArea(new Dimension(0,20)));
         panel1.add(box);
         //panel1.add(Box.createRigidArea(new Dimension(0,20)));
         panel1.add(crate);
         //panel1.add(Box.createRigidArea(new Dimension(0,20)));
         panel1.add(drum);


          //panel2.setBackground(Color.red);
         // panel1.setLayout(new GridBagLayout());
         // panel1.pack();



         //Add panels
         frame.getContentPane().add(panel1,BorderLayout.WEST);
         frame.getContentPane().add(panel2);
       }
  }

  /**
    * This method closes the window and exits the program.
    */


    /**
     * This method allows the user to delete a package from the inventory
     * database.
     */
    public void deletePackage() {
        sc.nextLine();
        System.out.print("\nEnter tracking number of pacakge to delete (string): ");
        String ptn = sc.nextLine();

        if (ss.deletePackage(ptn))
            System.out.println("Package deleted.");
        else
            System.out.println("Package with given tracking number not found in the database.");
    }


    /**
     * This method allows the users to search for a package given its tracking number
     * and then it prints details about the package.
     */
    public void searchPackage() {
        sc.nextLine();
        System.out.print("\nEnter tracking number of package to search for (string): ");
        String ptn = sc.nextLine();

        if (ss.packageExists(ptn))
            System.out.println(ss.getPackageFormatted(ptn));
        else
            System.out.println("Package with PTN " + ptn + " not found in the database");
    }

    /**
     * Prints out a list of all users in the database.
     */
    public void showAllUsers() {
        System.out.println(ss.getAllUsersFormatted());
    }

    /**
     * This method allows a new user to be added to the database.
     *
     */
    public void addNewUser() {
        boolean success;
        // Add fields for new user
        int userType = 0;
        boolean check = false;

        while (!check) {
            System.out.println("Select user type:\n"
                    + "1. Customer\n"
                    + "2. Employee");

            if (sc.hasNextInt()) {
                userType = sc.nextInt();

                if (userType < 1 || userType > 2) {
                    System.out.println("Wrong integer value: choose 1 or 2");
                } else {
                    check = true;
                }
            } else {
                System.out.println("Please select 1 or 2");
            }
        }

        sc.nextLine();
        System.out.println("\nEnter first name (string): ");
        String firstName = sc.nextLine();

        System.out.println("\nEnter last name (string): ");
        String lastName = sc.nextLine();

        if (userType == 1) {
            System.out.println("\nEnter phone number (string): ");
            String phoneNumber = sc.nextLine();

            System.out.println("\nEnter address (string): ");
            String address = sc.nextLine();

            ss.addCustomer(firstName, lastName, phoneNumber, address);

        } else if (userType == 2) {

            check = false;
            float monthlySalary = 0.0f;

            while (!check) {

                System.out.println("\nEnter monthly salary (float): ");

                if (sc.hasNextFloat()) {
                    monthlySalary = sc.nextFloat();
                    if (monthlySalary < 0.0f) {
                        System.out.println("Monthly salary cannot be negative.");
                    } else {
                        check = true;
                    }
                    sc.nextLine();

                } else {
                    System.out.println("Please enter monthly salary as a non-zero float value.");
                    sc.nextLine();
                }
            }

            int ssn = 0;
            check = false;
            while (!check) {

                System.out.println("\nEnter SSN (9-digital int): ");
                if (sc.hasNextInt()) {
                    ssn = sc.nextInt();
                    if (String.valueOf(ssn).length() != 9) {
                        System.out.println("\nThat is not a nine digit number");
                    } else if (ssn < 10000000 || ssn > 999999999) {
                        System.out.println("\nGive a correct 9 digit integer");
                    } else {
                        check = true;
                    }
                    sc.nextLine();
                } else {
                    System.out.println("\nNot a number!");
                    sc.nextLine();
                } //end if
            }// end while

            check = false;
            int bankAccNumber = 0;
            while (!check) {
                System.out.println("\nEnter bank account number (int): ");
                if (sc.hasNextInt()) {
                    bankAccNumber = sc.nextInt();
                    if (bankAccNumber < 0) {
                        System.out.println("\nBank account cannot be negative");
                    } else {
                        check = true;
                    }
                    sc.nextLine();
                } else {
                    System.out.println("Invalid bank Account format, please try again");
                    sc.nextLine();
                }

            }//end while

            ss.addEmployee(firstName, lastName, ssn, monthlySalary, bankAccNumber);
        } else {
            System.out.println("Unknown user type. Please try again.");
        }

    }

    /**
     * This method can be used to update a user's information, given their user
     * ID.
     *
     * @throws shippingstore.BadInputException
     */
    public void updateUser() throws BadInputException {
        boolean check = false;
        System.out.print("\nEnter user ID: ");
        int userID = sc.nextInt();

        if (!ss.userExists(userID)) {
            System.out.println("User not found.");
            return;
        }

        sc.nextLine();
        System.out.print("\nEnter first name (string): ");
        String firstName = sc.nextLine();

        System.out.print("\nEnter last name (string): ");
        String lastName = sc.nextLine();

        if (ss.isCustomer(userID)) {
            System.out.print("\nEnter phone number (string): ");
            String phoneNumber = sc.nextLine();
            System.out.print("\nEnter address (string): ");
            String address = sc.nextLine();

            ss.updateCustomer(userID, firstName, lastName, phoneNumber, address);

        } else { //User is an employee

            float monthlySalary = 0.0f;
            check = false;
            while (!check) {

                System.out.println("\nEnter monthly salary (float): ");

                if (sc.hasNextFloat()) {
                    monthlySalary = sc.nextFloat();
                    if (monthlySalary < 0.0f) {
                        new BadInputException("Monthly salary cannot be negative.");
                    } else {
                        check = true;
                    }
                    sc.nextLine();
                } else {
                    System.out.println("Please enter monthly salary as a non-zero float value.");
                    sc.nextLine();
                }
            }

            int ssn = 0;
            check = false;
            while (!check) {

                System.out.println("\nEnter SSN (9-digital int): ");
                if (sc.hasNextInt()) {
                    ssn = sc.nextInt();
                    if (String.valueOf(ssn).length() != 9) {
                        new BadInputException("\nThat is not a nine digit number");
                    } else if (ssn < 10000000 || ssn > 999999999) {
                        new BadInputException("\nGive a correct 9 digit integer");

                    } else {
                        check = true;
                    }
                } //end if
                sc.nextLine();

            }// end while

            int bankAccNumber = 0;
            check = false;
            while (!check) {
                System.out.println("\nEnter bank account number (int): ");

                if (sc.hasNextInt()) {
                    bankAccNumber = sc.nextInt();
                    if (bankAccNumber < 0) {
                        new BadInputException("Bank account cannot be negative");
                    } else {
                        check = true;
                    }
                    sc.nextLine();
                } else {
                    System.out.println("Invalid bank Account format, please try again");
                    sc.nextLine();
                }
            } //end while

            ss.updateEmployee(userID, firstName, lastName, ssn, monthlySalary, bankAccNumber);
        }
    }

    /**
     * This method is used to complete a package shipping/delivery transaction.
     *
     * @throws shippingstore.BadInputException
     */
    public void deliverPackage() throws BadInputException {

        Date currentDate = new Date(System.currentTimeMillis());

        sc.nextLine();
        System.out.println("\nEnter customer ID (int): ");
        int customerId = sc.nextInt();
        //Check that the customer exists in database
        boolean customerExists = ss.userExists(customerId);

        if (!customerExists) {
            System.out.println("\nThe customer ID you have entered does not exist in the database.\n"
                    + "Please add the customer to the database first and then try again.");
            return;
        }

        System.out.println("\nEnter employee ID (int): ");

        int employeeId = 0;
        if (sc.hasNextInt()) {
            employeeId = sc.nextInt();
        }
        //Check that the employee exists in database
        boolean employeeExists = ss.userExists(employeeId);

        if (!employeeExists) {
            System.out.println("\nThe employee ID you have entered does not exist in the database.\n"
                    + "Please add the employee to the database first and then try again.");
            return;
        }

        sc.nextLine();
        System.out.println("\nEnter tracking number (string): ");
        String ptn = sc.nextLine();

        //Check that the package exists in database
        if (!ss.packageExists(ptn)) {
            System.out.println("\nThe package with the tracking number you are trying to deliver "
                    + "does not exist in the database. Aborting transaction.");
            return;
        }

        System.out.println("\nEnter price (float): ");
        float price = sc.nextFloat();
        if (price < 0.0f) {
            throw new BadInputException("Price cannot be negative.");
        }

        ss.addShppingTransaction(customerId, employeeId, ptn, currentDate, currentDate, price);
        ss.deletePackage(ptn);

        System.out.println("\nTransaction Completed!");
    }


    /**
     * Prints out a list of all recorded transactions.
     */
    public void showAllTransactions() {
        System.out.println(ss.getAllTransactionsText());
    }


    /**
     * The main method of the program.
     *
     * @param args the command line arguments
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        MainApp app = new MainApp();

        app.runSoftware();
    }
}
