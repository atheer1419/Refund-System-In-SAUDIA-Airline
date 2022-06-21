/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refundsysteminsaudiairline;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * This class perform methods for the refund department.
 *
 * @author Group 27
 */
public class RefundDepartment extends javax.swing.JFrame {

    public static double ticketPrice = 0.0;

    public static String fees;

    String ticketType;
    Boolean verifyResult;

    File redFile = new File("Customer.txt");
    File redFile_emp = new File("Employee.txt");
    File output = new File("Claimes.txt");
    File approvalFile = new File("Approval.txt");

    /**
     *
     * @param one The Last name or password that the user entered.
     * @param two The Ticket number or id that the user entered.
     * @return boolean if true the user can enter the system if false it can not
     * enter.
     * @throws FileNotFoundException to avoid having an error for not founding
     * the file.
     * @throws ParseException to avoid having an error because of the parse
     */
    public boolean verifyInfo(String one, int two) throws FileNotFoundException, ParseException {

        boolean flag1 = false;

        boolean verivedOrNot = false;
        Scanner reder = new Scanner(redFile); //read
        String[] line;

        while (reder.hasNext()) {
            line = reder.nextLine().split(" ");

            if (one.equalsIgnoreCase(line[0]) && two == Integer.parseInt(line[1])) {
                flag1 = true;
                verivedOrNot = true;

            }
            if (flag1 == false) {
                verivedOrNot = false;

            }

        }

        String password = one;
        int id = two;
        Scanner rederemp_scan = new Scanner(redFile_emp);
        while (rederemp_scan.hasNext()) {
            line = rederemp_scan.nextLine().split(" ");

            if (password.equalsIgnoreCase(line[0]) && id == Integer.parseInt(line[1])) {

                flag1 = true;
                verivedOrNot = true;

            }
            if (flag1 == false) {
                verivedOrNot = false;

            }

        }

        return verivedOrNot;
    }

    /**
     * This method check if the payment is cash or credit card.
     *
     * @param CustomerTicketNum the ticket number
     * @return String return the payment method
     * @throws FileNotFoundException to avoid having an error for not founding
     * the file.
     */
    public String checkPaymentMethod(String CustomerTicketNum) throws FileNotFoundException {
        Scanner reder = new Scanner(redFile); //read

        while (reder.hasNext()) {
            String[] line = reder.nextLine().split(" ");

            if (line[1].equalsIgnoreCase(CustomerTicketNum)) {
                return line[2];
            }
        }

        return null;
    }

    /**
     * this method will check if the ticket type is simple or complicated
     * depending on the fees.
     *
     * @param TicketNumbr the ticket number
     * @return String return the fees
     */
    public String checkTicketType(String TicketNumbr) {

        String[] line;
        Scanner reder;
        try {
            reder = new Scanner(redFile); //read
            while (reder.hasNext()) {
                line = reder.nextLine().split(" ");

                if (TicketNumbr.equalsIgnoreCase(line[1])) {

                    if (line[2].equalsIgnoreCase("cash")) {

                        this.ticketPrice = Double.parseDouble(line[5]);
                    } else if (line[2].equalsIgnoreCase("creditcard")) {
                        this.ticketPrice = Double.parseDouble(line[5]);
                    }

                    if (line[4].equalsIgnoreCase("fees")) {
                        fees = "fees";

                    } else if (line[4].equalsIgnoreCase("nofees")) {
                        fees = "nofees";

                    }

                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(close.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fees;
    }

    /**
     * This method check if the ticket will be approved or not . by checking the
     * ticket state and the refunded amount.
     *
     * @throws FileNotFoundException to avoid having an error for not founding
     * the file.
     * @throws IOException to avoid having an error because a failed or
     * interrupted I/O operations.
     */
    public boolean checkApproval() throws FileNotFoundException, IOException {
        approve obj_approve = new approve();

        FileWriter fw = new FileWriter(approvalFile, true);
        BufferedWriter bw = new BufferedWriter(fw);

        boolean o = false;

        Scanner read = new Scanner(output); //read
        String[] line;
        while (read.hasNext()) {
            line = read.nextLine().split(" ");

            if (obj_approve.getTicketNumber().equalsIgnoreCase(line[1])) {
                o = true;
                if (line[6].equalsIgnoreCase("R") && Double.parseDouble(line[7]) > 0.0) {

                    for (int i = 0; i < line.length; i++) {
                        bw.write(line[i] + " ");
                    }

                }
                bw.newLine();
            }

        }
        read.close();
        bw.flush();
        bw.close();

        return o;
    }

    /**
     * This method will calculate the ticket price to find the refunded amount.
     *
     * @return double return the ticket price
     */
    public double calculateAuto() {

        if (fees.equalsIgnoreCase("fees")) {
            this.ticketPrice = (ticketPrice - 200);

            if (this.ticketPrice < 0) {
                this.ticketPrice = 0.0;
            }

        } else if (fees.equalsIgnoreCase("nofees")) {
            this.ticketPrice = ticketPrice;

        }

        return ticketPrice;

    }

    /**
     * This method will change the ticket state from Open to R
     *
     * @param r the value of the check box
     * @return String return the ticket state
     */
    public String turnStateIntoR_auto(Boolean r) {
        String returnR = "Open";

        if (r == true) {
            returnR = "R";
            return returnR;
        }
        return returnR;
    }

    /**
     * This method will change the ticket state from Open to R
     *
     * @param r the value of the user input
     * @return String return the ticket state
     */
    public String turnStateIntoR_manually(String r) {
        String returnR = "Open";
        if (r.equalsIgnoreCase("R")) {
            returnR = "R";
            return returnR;
        }
        return returnR;

    }

}
