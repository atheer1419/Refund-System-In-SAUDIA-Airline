/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refundsysteminsaudiairline;

/**
 * Store the submitted claims
 *
 * @author Group 27
 */
public class claim {

    String ticketState;
    String fees;
    double refundAmount;

    Customer customer;

    /**
     * Store the claim
     *
     * @param ticketState The Ticket State
     * @param fees The fees
     * @param refundAmount The Refund Amount
     * @param customer The customer information
     */
    public claim(String ticketState, String fees, double refundAmount, Customer customer) {
        this.ticketState = ticketState;
        this.fees = fees;
        this.refundAmount = refundAmount;
        this.customer = customer;
    }

    /**
     * Store The Customer Information and some of The ticket information.
     *
     * @param customer The customer information and some of The ticket
     * information
     */
    public claim(Customer customer) {
        this.customer = customer;
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
     * Return the Refunded amount.
     *
     * @return double Return the Refunded amount.
     */
    public double getRefundAmount() {
        return refundAmount;
    }

    /**
     * Set new value for in RefundedAmount.
     *
     * @param refundAmount The new value.
     */
    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * Return the customer.
     *
     * @return double Return the customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
       * Set new value for in customer.
     *
     * @param customer The new value.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return customer.toString();
    }

  
}
