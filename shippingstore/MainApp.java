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
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0,0,10,10);
        JButton button1 = new JButton ("Show all existing packages in the database.");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(button1, c);
        JButton button2 = new JButton ("Add a new package to the database.");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(button2, c);
        JButton button3 = new JButton ("Delete a package from a database (given its tracking number).");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(button3, c);
        JButton button4 = new JButton ("Search for a package (given its tracking number).");
        c.gridx = 0;
        c.gridy = 3;
        panel.add(button4, c);
        JButton button5 = new JButton ("Show list of users.");
        c.gridx = 0;
        c.gridy = 4;
        panel.add(button5, c);
        JButton button6 = new JButton ("Add a new user to the database.");
        c.gridx = 0;
        c.gridy = 5;
        panel.add(button6, c);
        JButton button7 = new JButton ("Update user info (given their id).");
        c.gridx = 0;
        c.gridy = 6;
        panel.add(button7, c);
        JButton button8 = new JButton ("Deliver a package.");
        c.gridx = 0;
        c.gridy = 7;
        panel.add(button8, c);
        JButton button9 = new JButton ("Show a list of transactions.");
        c.gridx = 0;
        c.gridy = 8;
        panel.add(button9, c);
        c.gridx = 0;
        c.gridy = 9;
        JButton button10 = new JButton ("Exit program.");
        panel.add(button10, c);

        button1.addActionListener(new showAllPackages());
        button2.addActionListener(new addNewPackage());

        panel.setPreferredSize(new Dimension(400,450));
        JLabel label = new JLabel("Welcome! Please choose menu option.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(panel,BorderLayout.WEST);
        frame.add(label,BorderLayout.NORTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       // int choice = 0;
       // boolean exitProgram = false;
       // do {
       //     printMenu();
       //     try {
       //         choice = sc.nextInt();
       //
       //         switch (choice) {
       //             case 1: showAllPackages(); break;
       //             case 2: addNewPackage(); break;
       //             case 3: deletePackage(); break;
       //             case 4: searchPackage(); break;
       //             case 5: showAllUsers(); break;
       //             case 6: addNewUser(); break;
       //             case 7: updateUser(); break;
       //             case 8: deliverPackage(); break;
       //             case 9: showAllTransactions(); break;
       //             case 10: ss.writeDatabase(); exitProgram = true; break;
       //             default: System.err.println("Please select a number between 1 and 10.");
       //         }
       //     } catch (InputMismatchException ex) {
       //         System.err.println("Input missmatch. Please Try again.");
       //         continue;
       //     } catch (BadInputException ex) {
       //         System.err.println("Bad input. "+ex.getMessage());
       //         System.err.println("Please try again.");
       //         continue;
       //     }
       // } while (!exitProgram);
   }

   /**
    * Auxiliary method that prints out the operations menu.
    */
   private static void printMenu() {
       System.out.println(
               "\n 1. Show all existing packages in the database.\n" +
               " 2. Add a new package to the database. \n" +
               " 3. Delete a package from a database (given its tracking number).\n" +
               " 4. Search for a package (given its tracking number).\n" +
               " 5. Show list of users.\n" +
               " 6. Add a new user to the database.\n" +
               " 7. Update user info (given their id).\n" +
               " 8. Deliver a package.\n" +
               " 9. Show a list of transactions.\n" +
               "10. Exit program.\n");
   }

   /**
     * This method allows the user to enter a new package to the list
     * database.
     * @throws shippingstore.BadInputException bad input
     */
    static class addNewPackage extends MainApp implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         JFrame frame = new JFrame("Add a new package");
         frame.setVisible(true);
         frame.setSize(900, 600);
         JPanel panel1 = new JPanel();
         panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));


         JRadioButton envelope = new JRadioButton("Envelope");
         JRadioButton box = new JRadioButton("Box");
         JRadioButton crate = new JRadioButton("Crate");
         JRadioButton drum = new JRadioButton("Drum");
         // Box b = Box.createVerticalBox();
         // panel1.setLayout(new GridLayout(1,1));


         ButtonGroup pType = new ButtonGroup();
         pType.add(envelope);
         pType.add(box);
         pType.add(crate);
         pType.add(drum);

         panel1.add(envelope);
         panel1.add(Box.createRigidArea(new Dimension(0,20)));
         panel1.add(box);
         panel1.add(Box.createRigidArea(new Dimension(0,20)));
         panel1.add(crate);
         panel1.add(Box.createRigidArea(new Dimension(0,20)));
         panel1.add(drum);

         // panel1.setBackground(Color.red);
         // panel1.setLayout(new GridBagLayout());
         // panel1.pack();
         frame.getContentPane().add(panel1,BorderLayout.WEST);
         //panel.add(label);
       }
      // public void addNewPackage(ActionEvent e) throws BadInputException {
      //     System.out.println("Select package type:\n"
      //             + "1. Envelope\n"
      //             + "2. Box\n"
      //             + "3. Crate\n"
      //             + "4. Drum");
      //     int packageType = sc.nextInt();
      //     if (packageType < 1 || packageType > 4) {
      //         throw new BadInputException("Legal package type values: 1-4.");
      //     }
      //     sc.nextLine();
      //
      //     System.out.println("\nEnter tracking number (string): ");
      //     String ptn = sc.nextLine();
      //     if (ptn.length() > 5) {
      //         throw new BadInputException("Tracking number should not be more that 5 characters long.");
      //     }
      //
      //     if (ss.packageExists(ptn)) {
      //         System.out.println("\nPackage with the same tracking number exists, try again");
      //         return;
      //     }
      //
      //     System.out.println("\nEnter Specification: Fragile, Books, Catalogs, Do-not-bend, or N/A");
      //     String specification = sc.nextLine();
      //     boolean correct = false;
      //
      //     correct = specification.equalsIgnoreCase("Fragile") || specification.equalsIgnoreCase("Books") || specification.equalsIgnoreCase("Catalogs");
      //     correct = correct || specification.equalsIgnoreCase("Do-not-bend") || specification.equalsIgnoreCase("N/A");
      //
      //     if (!(correct)) {
      //         throw new BadInputException("Specifications can only be one of the following: Fragile, Books, Catalogs, Do-not-bend, or N/A");
      //     }
      //
      //     System.out.println("\nEnter mailing class can be First-Class, Priority, Retail, Ground, or Metro.");
      //     String mailingClass = sc.nextLine();
      //
      //     correct = mailingClass.equalsIgnoreCase("First-Class") || mailingClass.equalsIgnoreCase("Priority") || mailingClass.equalsIgnoreCase("Retail");
      //     correct = correct || mailingClass.equalsIgnoreCase("Ground") || mailingClass.equalsIgnoreCase("Metro");
      //     if (!(correct)) {
      //         throw new BadInputException("Specifications can only be one of the following: Fragile, Books, Catalogs, Do-not-bend, or N/A");
      //     }
      //
      //     if (packageType == 1) {
      //         System.out.println("\nEnter height (inch), (int): ");
      //         int height = 0;
      //         if (sc.hasNextInt()) {
      //             height = sc.nextInt();
      //             sc.nextLine();
      //             if (height < 0) {
      //                 throw new BadInputException("Height of Envelope cannot be negative.");
      //             }
      //         } else {
      //             sc.nextLine();
      //             throw new BadInputException("Height of Envelope is integer.");
      //         }
      //
      //         int width = 0;
      //         System.out.println("\nEnter width (inch), (int): ");
      //         if (sc.hasNextInt()) {
      //             width = sc.nextInt();
      //             sc.nextLine();
      //             if (width < 0) {
      //                 throw new BadInputException("Width of Envelope cannot be negative.");
      //             }
      //         } else {
      //             sc.nextLine();
      //             throw new BadInputException("Width of Envelope is integer.");
      //         }
      //
      //         ss.addEnvelope(ptn, specification, mailingClass, height, width);
      //
      //     } else if (packageType == 2) {
      //         System.out.println("\nEnter largest dimension (inch), (int): ");
      //
      //         int dimension = 0;
      //         if (sc.hasNextInt()) {
      //             dimension = sc.nextInt();
      //             sc.nextLine();
      //             if (dimension < 0) {
      //                 throw new BadInputException("Largest dimension of Box cannot be negative.");
      //             }
      //         } else {
      //             sc.nextLine();
      //             throw new BadInputException("Dimension should be integer.");
      //         }
      //
      //         System.out.println("\nEnter volume (inch^3), (int): ");
      //
      //         int volume = 0;
      //         if (sc.hasNextInt()) {
      //             volume = sc.nextInt();
      //             sc.nextLine();
      //             if (volume < 0) {
      //                 throw new BadInputException("Volume of Box cannot be negative.");
      //             }
      //         } else {
      //             sc.nextLine();
      //             throw new BadInputException("Volume should be integer.");
      //         }
      //
      //         ss.addBox(ptn, specification, mailingClass, dimension, volume);
      //
      //     } else if (packageType == 3) {
      //         System.out.println("\nEnter maximum load weight (lb), (float): ");
      //
      //         float weight = 0.0f;
      //         if (sc.hasNextFloat()) {
      //             weight = sc.nextFloat();
      //             sc.nextLine();
      //             if (weight < 0.0f) {
      //                 throw new BadInputException("Maximum load weight of Crate cannot be negative.");
      //             }
      //         } else {
      //             sc.nextLine();
      //             throw new BadInputException("Max load should be float");
      //         }
      //
      //         System.out.println("\nEnter content (string): ");
      //         String content = sc.nextLine();
      //
      //         ss.addCrate(ptn, specification, mailingClass, weight, content);
      //
      //     } else if (packageType == 4) {
      //
      //         System.out.println("\nEnter material (Plastic / Fiber): ");
      //         String material = sc.nextLine();
      //         if (!(material.equalsIgnoreCase("Plastic") || material.equalsIgnoreCase("Fiber"))) {
      //             throw new BadInputException("Material of Drum can only be plastic or fiber.");
      //         }
      //
      //         float diameter = 0.0f;
      //         System.out.println("\nEnter diameter (float): ");
      //         if (sc.hasNextFloat()) {
      //             diameter = sc.nextFloat();
      //             sc.nextLine();
      //             if (diameter < 0.0f) {
      //                 throw new BadInputException("Diameter of Drum cannot be negative.");
      //             }
      //         } else {
      //             sc.nextLine();
      //             throw new BadInputException("Diameter should be float");
      //         }
      //
      //         ss.addDrum(ptn, specification, mailingClass, material, diameter);
      //
      //     } else {
      //         System.out.println("Unknown package type entered. Please try again.");
      //     }
      // }
  }
    /**
     * This method prints out all the package currently in the inventory, in a
     * formatted manner.
     */

     static class showAllPackages extends MainApp implements ActionListener {
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
