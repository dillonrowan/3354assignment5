/*
 * Shipping Store Management Software v0.1
 * Developed for CS3354: Object Oriented Design and Programming.
 * Copyright: Junye Wen (j_w236@txstate.edu)
 */

package shippingstore;

/**
 * Employee is a subclass of User
 * @author Junye Wen
 */
public class Employee extends User {
    private int socialSecurityNumber;
    private float monthlySalary;
    private int bankAccountNumber;

    /**
     * Default constructor.
     */
    public Employee() {
        this.socialSecurityNumber = 0;
        this.monthlySalary = 0.0f;
        this.bankAccountNumber = 0;
    }

    /**
     * Constructor initializes an employee object with the provided values.
     * @param id
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param monthlySalary
     * @param bankAccountNumber
     */
    public Employee(int id, String firstName, String lastName, int socialSecurityNumber,
            float monthlySalary, int bankAccountNumber) {
        super(id, firstName, lastName);
        this.socialSecurityNumber = socialSecurityNumber;
        this.monthlySalary = monthlySalary;
        this.bankAccountNumber = bankAccountNumber;
    }

    /**
     * Get the SSN.
     * @return socialSecurityNumber
     */
    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Set the SSN.
     * @param socialSecurityNumber
     */
    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * Get the monthly salary.
     * @return monthlySalary
     */
    public float getMonthlySalary() {
        return monthlySalary;
    }

    /**
     * Set the monthly salary.
     * @param monthlySalary
     */
    public void setMonthlySalary(float monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    /**
     * Get the bank account number.
     * @return
     */
    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    /**
     * Set the bank account number.
     * @param bankAccountNumber
     */
    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    /**
     * Returns the attributes of the employee, in a formatted text fashion.
     * @return Formated Text.
     */
    @Override
    public String getFormattedText() {
      String data = "Employee " + id +" "+ firstName +" "+ lastName +" "+ socialSecurityNumber +" "+ monthlySalary + " " + bankAccountNumber;
      return data;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", socialSecurityNumber=" + socialSecurityNumber
                + ", monthlySalary=" + monthlySalary
                + ", bankAccountNumber=" + bankAccountNumber + '}';
    }

    /**
     * Returns the user type.
     * @return User type.
     */
    @Override
    public String getType() {
      return "Employee";
    }
}
