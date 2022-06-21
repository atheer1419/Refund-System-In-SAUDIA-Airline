package refundsysteminsaudiairline;

/**
 * This class will store the customer information
 *
 * @author Group 27
 */
public class Customer {

    String Lname;
    int TiketNo;
    String iban = null;
    String ticketState;
    String fees;
    double TicketPrice;
    double RefundedAmount;

    /**
     * Store the customer information
     *
     * @param Lname Customer last name
     * @param TiketNo Ticket number
     * @param ticketState Ticket state (Open or R)
     * @param fees Ticket fees (fees or nofees)
     * @param TicketPrice Original ticket price
     */
    public Customer(String Lname, int TiketNo, String ticketState, String fees, double TicketPrice) {
        this.Lname = Lname;
        this.TiketNo = TiketNo;
        this.TicketPrice = TicketPrice;
        this.fees = fees;
        this.ticketState = ticketState;

    }

    /**
     * Store the customer information
     *
     * @param Lname Customer last name
     * @param TiketNo Ticket number
     * @param ticketState Ticket state (Open or R)
     * @param fees Ticket fees (fees or nofees)
     * @param TicketPrice Original ticket price
     * @param iban The IBAN
     * @param RefundedAmount The refunded amount
     */
    public Customer(String Lname, int TiketNo, String ticketState, String fees, double TicketPrice, String iban, double RefundedAmount) {
        this.Lname = Lname;
        this.TiketNo = TiketNo;
        this.iban = iban;
        this.TicketPrice = TicketPrice;
        this.fees = fees;
        this.ticketState = ticketState;
        this.RefundedAmount = RefundedAmount;

    }

    /**
     * Return the Refunded amount.
     *
     * @return double Return the Refunded amount.
     */
    public double getRefundedAmount() {
        return RefundedAmount;
    }

    /**
     * Set new value for in RefundedAmount.
     *
     * @param RefundedAmount The new value.
     */
    public void setRefundedAmount(double RefundedAmount) {
        this.RefundedAmount = RefundedAmount;
    }

    /**
     * Return the Ticket State.
     *
     * @return String Return the Ticket State.
     */
    public String getTicketState() {
        return ticketState;
    }

    /**
     * Set new value for in ticketState.
     *
     * @param ticketState The new value .
     */
    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
    }

    /**
     * Return the fees
     *
     * @return String Return the fees.
     */
    public String getFees() {
        return fees;
    }

    /**
     * Set new value for in fees.
     *
     * @param fees The new value .
     */
    public void setFees(String fees) {
        this.fees = fees;
    }

    /**
     * Return the Ticket Price.
     *
     * @return double Return the Ticket Price.
     */
    public double getTicketPrice() {
        return TicketPrice;
    }

    /**
     * Set new value for in TicketPrice.
     *
     * @param TicketPrice The new value .
     */
    public void setTicketPrice(double TicketPrice) {
        this.TicketPrice = TicketPrice;
    }

    /**
     * Return the Last Name.
     *
     * @return String Return the Last Name.
     */
    public String getLname() {
        return Lname;
    }

    /**
     * Set new value for in Lname.
     *
     * @param Lname The new value .
     */
    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    /**
     * Return the Ticket Num.
     *
     * @return int Return the Ticket Num.
     */
    public int getTicketNum() {
        return TiketNo;
    }

    /**
     * Set new value for in ticketNum.
     *
     * @param ticketNum The new value .
     */
    public void setTicketNum(int ticketNum) {
        this.TiketNo = ticketNum;
    }

    /**
     * Return the iban.
     *
     * @return String Return the iban.
     */
    public String getIban() {
        return iban;
    }

    /**
     * Set new value for in iban.
     *
     * @param iban The new value .
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "Customer{" + "Lname=" + Lname + ", ticketNum=" + TiketNo + ", iban=" + iban + ", ticketState=" + ticketState + ", fees=" + fees + ", TicketPrice=" + TicketPrice + '}';
    }

}
