// package shippingstore;
//
// import java.util.*;
// import java.awt.*;
// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.event.*;
// import javax.swing.*;
// import javax.swing.Box;
// import javax.swing.BorderFactory;
// import javax.swing.border.TitledBorder;
// import javax.swing.border.Border;
// import javax.swing.BoxLayout;
//
// /**
// * Main access point
// */
// public class MainApp {
//   ShippingStore ss;
//   public final Scanner sc= new Scanner(System.in); // Used to read from System's standard input
//
//   /**
//    * Constructor
//    */
//   public MainApp() {
//     ss = ShippingStore.readDatabase();
//     //this.sc = new Scanner(System.in);
//   }
//
//   /**
//   * This method servers as the main interface between the program and the user.
//   * The method interacts with the user by printing out a set of options, and
//   * asking the user to select one.
//   */
//   public void runSoftware() {
//     /** Create an empty, labeled panel and display it */
//     JFrame frame = new JFrame("Shipping Store Database");
//     JLabel label = new JLabel("Welcome! Please choose menu option.");
//     JPanel panel = new JPanel(new GridLayout(10, 1, 1, 1));
//
//     // Create menu buttons
//     JButton button1 = new JButton ("Show all existing packages in the database.");
//     JButton button2 = new JButton ("Add a new package to the database.");
//     JButton button3 = new JButton ("Delete a package from a database (given its tracking number).");
//     JButton button4 = new JButton ("Search for a package (given its tracking number).");
//     JButton button5 = new JButton ("Show list of users.");
//     JButton button6 = new JButton ("Add a new user to the database.");
//     JButton button7 = new JButton ("Update user info (given their id).");
//     JButton button8 = new JButton ("Deliver a package.");
//     JButton button9 = new JButton ("Show a list of transactions.");
//     JButton button10 = new JButton ("Exit program.");
//
//     // Add buttons to panel
//     panel.add(button1);
//     panel.add(button2);
//     panel.add(button3);
//     panel.add(button4);
//     panel.add(button5);
//     panel.add(button6);
//     panel.add(button7);
//     panel.add(button8);
//     panel.add(button9);
//     panel.add(button10);
//
//     // Assign listener to buttons
//     button1.addActionListener(new showAllPackages());
//     button2.addActionListener(new addNewPackage());
//     //button3.addActionListener(new removePackage());
//     //button4.addActionListener(new removePackage());
//     //button5.addActionListener(new removePackage());
//     //button6.addActionListener(new removePackage());
//     //button7.addActionListener(new removePackage());
//     //button8.addActionListener(new removePackage());
//     //button9.addActionListener(new removePackage());
//     button10.addActionListener(new ActionListener(){
//       public void actionPerformed(ActionEvent event){
//         System.exit(0);
//       }
//     });
//
//     label.setHorizontalAlignment(SwingConstants.CENTER);
//     frame.add(label,BorderLayout.NORTH);
//     frame.add(panel);
//
//     frame.pack();
//     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     frame.setVisible(true);
//     }
//
//    /**
//     * This method prints out all the package currently in the inventory, in a
//     * formatted manner.
//     */
//     private class showAllPackages extends MainApp implements ActionListener {
//       public void actionPerformed(ActionEvent e) {
//
//          JFrame frame = new JFrame("All Packages");
//          frame.setVisible(true);
//          frame.setSize(900, 600);
//          String text = ss.getAllPackagesFormatted();
//          JLabel label = new JLabel(text, SwingConstants.CENTER);
//          JPanel panel = new JPanel();
//          frame.add(panel);
//          panel.add(label);
//        }
//     }
//
//    /**
//      * This method allows the user to enter a new package to the list
//      * database.
//      * @throws shippingstore.BadInputException bad input
//      */
//     private class addNewPackage extends MainApp implements ActionListener {
//       public void actionPerformed(ActionEvent e) {
//         JFrame frame = new JFrame("Add a new package");
//         frame.setVisible(true);
//         frame.setSize(900, 600);
//         frame.setLayout(new GridLayout(1,5));
//
//         // Type panel
//         JPanel tPanel = new JPanel();
//         tPanel.setBorder(BorderFactory.createTitledBorder("Type"));
//         tPanel.setLayout(new BoxLayout(tPanel, BoxLayout.Y_AXIS));
//
//         // Type radio buttons
//         JRadioButton envelope = new JRadioButton("Envelope");
//         JRadioButton box = new JRadioButton("Box");
//         JRadioButton crate = new JRadioButton("Crate");
//         JRadioButton drum = new JRadioButton("Drum");
//         ButtonGroup typeGroup = new ButtonGroup();
//
//         // Type radio group
//         typeGroup.add(envelope);
//         typeGroup.add(box);
//         typeGroup.add(crate);
//         typeGroup.add(drum);
//
//         // Add type radio buttons to panel
//         tPanel.add(envelope);
//         tPanel.add(box);
//         tPanel.add(crate);
//         tPanel.add(drum);
//
//         // Mailing Class panel
//         JPanel mClassPanel = new JPanel();
//         mClassPanel.setBorder(BorderFactory.createTitledBorder("Mailing Class"));
//         mClassPanel.setLayout(new BoxLayout(mClassPanel, BoxLayout.Y_AXIS));
//
//         // Mailing Class radio buttons
//         JRadioButton fClass = new JRadioButton("First-Class");
//         JRadioButton priority = new JRadioButton("Priority");
//         JRadioButton retail = new JRadioButton("Retail");
//         JRadioButton ground = new JRadioButton("Ground");
//         JRadioButton metro = new JRadioButton("Metro");
//         ButtonGroup mClassGroup = new ButtonGroup();
//
//         // Mailing Class radio group
//         mClassGroup.add(fClass);
//         mClassGroup.add(priority);
//         mClassGroup.add(retail);
//         mClassGroup.add(ground);
//         mClassGroup.add(metro);
//
//         // Add Mailing Class radio buttons to panel
//         mClassPanel.add(fClass);
//         mClassPanel.add(priority);
//         mClassPanel.add(retail);
//         mClassPanel.add(ground);
//         mClassPanel.add(metro);
//
//         // Specification panel
//         JPanel specPanel = new JPanel();
//         specPanel.setBorder(BorderFactory.createTitledBorder("Specification"));
//         specPanel.setLayout(new BoxLayout(specPanel, BoxLayout.Y_AXIS));
//
//         // Specification radio buttons
//         JRadioButton fragile = new JRadioButton("Fragile");
//         JRadioButton books = new JRadioButton("Books");
//         JRadioButton catalogs = new JRadioButton("Catalogs");
//         JRadioButton noBend = new JRadioButton("Do-Not-bend");
//         JRadioButton na = new JRadioButton("N/A");
//         ButtonGroup specGroup = new ButtonGroup();
//
//         // Specification radio group
//         specGroup.add(fragile);
//         specGroup.add(books);
//         specGroup.add(catalogs);
//         specGroup.add(noBend);
//         specGroup.add(na);
//
//         // Add Specification radio buttons to panel
//         specPanel.add(fragile);
//         specPanel.add(books);
//         specPanel.add(catalogs);
//         specPanel.add(noBend);
//         specPanel.add(na);
//
//         // Material panel
//         JPanel materialPanel = new JPanel();
//         materialPanel.setBorder(BorderFactory.createTitledBorder("Material"));
//         materialPanel.setLayout(new BoxLayout(materialPanel, BoxLayout.Y_AXIS));
//
//         // Material radio buttons
//         JRadioButton plastic = new JRadioButton("Plastic");
//         JRadioButton fiber = new JRadioButton("Fiber");
//         ButtonGroup materialGroup = new ButtonGroup();
//
//         // Material radio group
//         materialGroup.add(plastic);
//         materialGroup.add(fiber);
//
//         // Add Material radio buttons to panel
//         materialPanel.add(plastic);
//         materialPanel.add(fiber);
//
//         frame.add(tPanel);
//         frame.add(mClassPanel);
//         frame.add(specPanel);
//         //frame.getContentPane().add(mClassPanel,BorderLayout.WEST);
//        }
//   }
//
//
//   /**
//     * This method allows the user to enter a new package to the list
//     * database.
//     * @throws shippingstore.BadInputException bad input
//     */
//     /*
//    private class removePackage extends MainApp implements ActionListener {
//      public void actionPerformed(ActionEvent e) {
//       }
//  }
// */
//     /**
//      * This method allows the user to delete a package from the inventory
//      * database.
//      */
//     public void deletePackage() {
//         sc.nextLine();
//         System.out.print("\nEnter tracking number of pacakge to delete (string): ");
//         String ptn = sc.nextLine();
//
//         if (ss.deletePackage(ptn))
//             System.out.println("Package deleted.");
//         else
//             System.out.println("Package with given tracking number not found in the database.");
//     }
//
//
//     /**
//      * This method allows the users to search for a package given its tracking number
//      * and then it prints details about the package.
//      */
//     public void searchPackage() {
//         sc.nextLine();
//         System.out.print("\nEnter tracking number of package to search for (string): ");
//         String ptn = sc.nextLine();
//
//         if (ss.packageExists(ptn))
//             System.out.println(ss.getPackageFormatted(ptn));
//         else
//             System.out.println("Package with PTN " + ptn + " not found in the database");
//     }
//
//     /**
//      * Prints out a list of all users in the database.
//      */
//     public void showAllUsers() {
//         System.out.println(ss.getAllUsersFormatted());
//     }
//
//     /**
//      * This method allows a new user to be added to the database.
//      *
//      */
//     public void addNewUser() {
//         boolean success;
//         // Add fields for new user
//         int userType = 0;
//         boolean check = false;
//
//         while (!check) {
//             System.out.println("Select user type:\n"
//                     + "1. Customer\n"
//                     + "2. Employee");
//
//             if (sc.hasNextInt()) {
//                 userType = sc.nextInt();
//
//                 if (userType < 1 || userType > 2) {
//                     System.out.println("Wrong integer value: choose 1 or 2");
//                 } else {
//                     check = true;
//                 }
//             } else {
//                 System.out.println("Please select 1 or 2");
//             }
//         }
//
//         sc.nextLine();
//         System.out.println("\nEnter first name (string): ");
//         String firstName = sc.nextLine();
//
//         System.out.println("\nEnter last name (string): ");
//         String lastName = sc.nextLine();
//
//         if (userType == 1) {
//             System.out.println("\nEnter phone number (string): ");
//             String phoneNumber = sc.nextLine();
//
//             System.out.println("\nEnter address (string): ");
//             String address = sc.nextLine();
//
//             ss.addCustomer(firstName, lastName, phoneNumber, address);
//
//         } else if (userType == 2) {
//
//             check = false;
//             float monthlySalary = 0.0f;
//
//             while (!check) {
//
//                 System.out.println("\nEnter monthly salary (float): ");
//
//                 if (sc.hasNextFloat()) {
//                     monthlySalary = sc.nextFloat();
//                     if (monthlySalary < 0.0f) {
//                         System.out.println("Monthly salary cannot be negative.");
//                     } else {
//                         check = true;
//                     }
//                     sc.nextLine();
//
//                 } else {
//                     System.out.println("Please enter monthly salary as a non-zero float value.");
//                     sc.nextLine();
//                 }
//             }
//
//             int ssn = 0;
//             check = false;
//             while (!check) {
//
//                 System.out.println("\nEnter SSN (9-digital int): ");
//                 if (sc.hasNextInt()) {
//                     ssn = sc.nextInt();
//                     if (String.valueOf(ssn).length() != 9) {
//                         System.out.println("\nThat is not a nine digit number");
//                     } else if (ssn < 10000000 || ssn > 999999999) {
//                         System.out.println("\nGive a correct 9 digit integer");
//                     } else {
//                         check = true;
//                     }
//                     sc.nextLine();
//                 } else {
//                     System.out.println("\nNot a number!");
//                     sc.nextLine();
//                 } //end if
//             }// end while
//
//             check = false;
//             int bankAccNumber = 0;
//             while (!check) {
//                 System.out.println("\nEnter bank account number (int): ");
//                 if (sc.hasNextInt()) {
//                     bankAccNumber = sc.nextInt();
//                     if (bankAccNumber < 0) {
//                         System.out.println("\nBank account cannot be negative");
//                     } else {
//                         check = true;
//                     }
//                     sc.nextLine();
//                 } else {
//                     System.out.println("Invalid bank Account format, please try again");
//                     sc.nextLine();
//                 }
//
//             }//end while
//
//             ss.addEmployee(firstName, lastName, ssn, monthlySalary, bankAccNumber);
//         } else {
//             System.out.println("Unknown user type. Please try again.");
//         }
//
//     }
//
//     /**
//      * This method can be used to update a user's information, given their user
//      * ID.
//      *
//      * @throws shippingstore.BadInputException
//      */
//     public void updateUser() throws BadInputException {
//         boolean check = false;
//         System.out.print("\nEnter user ID: ");
//         int userID = sc.nextInt();
//
//         if (!ss.userExists(userID)) {
//             System.out.println("User not found.");
//             return;
//         }
//
//         sc.nextLine();
//         System.out.print("\nEnter first name (string): ");
//         String firstName = sc.nextLine();
//
//         System.out.print("\nEnter last name (string): ");
//         String lastName = sc.nextLine();
//
//         if (ss.isCustomer(userID)) {
//             System.out.print("\nEnter phone number (string): ");
//             String phoneNumber = sc.nextLine();
//             System.out.print("\nEnter address (string): ");
//             String address = sc.nextLine();
//
//             ss.updateCustomer(userID, firstName, lastName, phoneNumber, address);
//
//         } else { //User is an employee
//
//             float monthlySalary = 0.0f;
//             check = false;
//             while (!check) {
//
//                 System.out.println("\nEnter monthly salary (float): ");
//
//                 if (sc.hasNextFloat()) {
//                     monthlySalary = sc.nextFloat();
//                     if (monthlySalary < 0.0f) {
//                         new BadInputException("Monthly salary cannot be negative.");
//                     } else {
//                         check = true;
//                     }
//                     sc.nextLine();
//                 } else {
//                     System.out.println("Please enter monthly salary as a non-zero float value.");
//                     sc.nextLine();
//                 }
//             }
//
//             int ssn = 0;
//             check = false;
//             while (!check) {
//
//                 System.out.println("\nEnter SSN (9-digital int): ");
//                 if (sc.hasNextInt()) {
//                     ssn = sc.nextInt();
//                     if (String.valueOf(ssn).length() != 9) {
//                         new BadInputException("\nThat is not a nine digit number");
//                     } else if (ssn < 10000000 || ssn > 999999999) {
//                         new BadInputException("\nGive a correct 9 digit integer");
//
//                     } else {
//                         check = true;
//                     }
//                 } //end if
//                 sc.nextLine();
//
//             }// end while
//
//             int bankAccNumber = 0;
//             check = false;
//             while (!check) {
//                 System.out.println("\nEnter bank account number (int): ");
//
//                 if (sc.hasNextInt()) {
//                     bankAccNumber = sc.nextInt();
//                     if (bankAccNumber < 0) {
//                         new BadInputException("Bank account cannot be negative");
//                     } else {
//                         check = true;
//                     }
//                     sc.nextLine();
//                 } else {
//                     System.out.println("Invalid bank Account format, please try again");
//                     sc.nextLine();
//                 }
//             } //end while
//
//             ss.updateEmployee(userID, firstName, lastName, ssn, monthlySalary, bankAccNumber);
//         }
//     }
//
//     /**
//      * This method is used to complete a package shipping/delivery transaction.
//      *
//      * @throws shippingstore.BadInputException
//      */
//     public void deliverPackage() throws BadInputException {
//
//         Date currentDate = new Date(System.currentTimeMillis());
//
//         sc.nextLine();
//         System.out.println("\nEnter customer ID (int): ");
//         int customerId = sc.nextInt();
//         //Check that the customer exists in database
//         boolean customerExists = ss.userExists(customerId);
//
//         if (!customerExists) {
//             System.out.println("\nThe customer ID you have entered does not exist in the database.\n"
//                     + "Please add the customer to the database first and then try again.");
//             return;
//         }
//
//         System.out.println("\nEnter employee ID (int): ");
//
//         int employeeId = 0;
//         if (sc.hasNextInt()) {
//             employeeId = sc.nextInt();
//         }
//         //Check that the employee exists in database
//         boolean employeeExists = ss.userExists(employeeId);
//
//         if (!employeeExists) {
//             System.out.println("\nThe employee ID you have entered does not exist in the database.\n"
//                     + "Please add the employee to the database first and then try again.");
//             return;
//         }
//
//         sc.nextLine();
//         System.out.println("\nEnter tracking number (string): ");
//         String ptn = sc.nextLine();
//
//         //Check that the package exists in database
//         if (!ss.packageExists(ptn)) {
//             System.out.println("\nThe package with the tracking number you are trying to deliver "
//                     + "does not exist in the database. Aborting transaction.");
//             return;
//         }
//
//         System.out.println("\nEnter price (float): ");
//         float price = sc.nextFloat();
//         if (price < 0.0f) {
//             throw new BadInputException("Price cannot be negative.");
//         }
//
//         ss.addShppingTransaction(customerId, employeeId, ptn, currentDate, currentDate, price);
//         ss.deletePackage(ptn);
//
//         System.out.println("\nTransaction Completed!");
//     }
//
//
//     /**
//      * Prints out a list of all recorded transactions.
//      */
//     public void showAllTransactions() {
//         System.out.println(ss.getAllTransactionsText());
//     }
//
//
//     /**
//      * The main method of the program.
//      *
//      * @param args the command line arguments
//      */
//     @SuppressWarnings("unchecked")
//     public static void main(String[] args) {
//         MainApp app = new MainApp();
//
//         app.runSoftware();
//     }
// }
