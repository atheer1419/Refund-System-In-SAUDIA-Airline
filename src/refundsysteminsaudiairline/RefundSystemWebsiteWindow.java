/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refundsysteminsaudiairline;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.Scanner;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class perform several main methods that are important for the refund
 * system to perform for both customers and employees.
 *
 * @author group 27
 */
public class RefundSystemWebsiteWindow extends javax.swing.JFrame {

    submit_cash framecash = new submit_cash();
    submit_cc framecc = new submit_cc();
    Close_complicated comp = new Close_complicated();

    Boolean verify_info;
    String pymentMethod;

    static String color;

    public static Customer customer;
    String cutomer_Ticktnum;
    double fees;
    claim claim;

    static String msg = "";

    Boolean verifyResult;
    RefundDepartment refundObj = new RefundDepartment();

    File redFile = new File("Customer.txt");
    File redFile2 = new File("Cutomer_Ticket_Info.txt");
    File output = new File("Claimes.txt");
    File output_temp = new File("temp.txt");

    public RefundSystemWebsiteWindow() {
    }

    public static String getMsg() {
        return msg;
    }

    public static String getColor() {
        return color;
    }

    /**
     * This method will check if the customer or employee info are in the
     * Customer or employee file or not
     *
     * @param one The Last name or password that the user entered.
     * @param two The Ticket number or id that the user entered.
     * @param type user type is either customer or employee
     * @return Boolean if the customer or employee info are in the Customer or
     * employee file or not
     * @throws FileNotFoundException to avoid having an error for not founding
     * the file.
     * @throws ParseException to avoid having an error because of the parse
     */
    public Boolean enter_verify(String one, int two, String type) throws FileNotFoundException, ParseException {
//        login logObj = new login();
        verifyResult = refundObj.verifyInfo(one, two);

        if (type.equalsIgnoreCase("Customer")) {
            if (verifyResult == true) {

                customer = new Customer(one, two, null, null, 0.0, null, 0.0);
                submit(verifyResult);
//                logObj.setVisible(false);
            }

            if (verifyResult == false) {
                JOptionPane.showMessageDialog(RefundSystemWebsiteWindow.this, "Incorrect Information, Please Re-enter.");
            }
        } else if (type.equalsIgnoreCase("Employee")) {

            if (verifyResult == false) {
                JOptionPane.showMessageDialog(RefundSystemWebsiteWindow.this, "Incorrect Information, Please Re-enter.");
            }

        }

        return verifyResult;
    }

    /**
     * This Method will write the customer information in the Claim file. so the
     * employee can perform operations on it.
     *
     * @param verify_info if the customer or employee info are in the Customer
     * or employee file or not
     * @throws FileNotFoundException to avoid having an error for not founding
     * the file.
     */
    public void submit(Boolean verify_info) throws FileNotFoundException {

        RefundDepartment Refund_obj = new RefundDepartment();
        cutomer_Ticktnum = customer.getTicketNum() + "";

        String fees = Refund_obj.checkTicketType(cutomer_Ticktnum) + "";
        String refunded_amount = Refund_obj.calculateAuto() + "";

        if (verify_info == true) {
            if (refundObj.checkPaymentMethod(cutomer_Ticktnum).equalsIgnoreCase("cash")) {

                framecash.setVisible(true);

                Scanner r = new Scanner(redFile2); //read

                while (r.hasNext()) {
                    String[] line = r.nextLine().split(",");

                    if (cutomer_Ticktnum.equalsIgnoreCase(line[0])) {
                        framecash.info(line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], "Whithin 10 days", refunded_amount);
                    }

                }

            } else if (refundObj.checkPaymentMethod(cutomer_Ticktnum).equalsIgnoreCase("creditcard")) {
                framecc.setVisible(true);

                Scanner r = new Scanner(redFile2); //read

                while (r.hasNext()) {
                    String[] line = r.nextLine().split(",");

                    if (cutomer_Ticktnum.equalsIgnoreCase(line[0])) {
                        framecc.info(line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], "Whithin 10 days", refunded_amount);

                    }

                }

            }
        }

    }

    /**
     *
     * @param iban the iban
     * @param Endo_check if the customer agreed on the endorsment or not
     * @return Boolean either the claim is submitted or not
     * @throws FileNotFoundException to avoid having an error for not founding
     * the file.
     * @throws IOException to avoid having an error because a failed or
     * interrupted I/O operations.
     */
    public String cash(String iban, Boolean Endo_check) throws FileNotFoundException, IOException {

        FileWriter fw = new FileWriter(output, true);
        BufferedWriter bw = new BufferedWriter(fw);

        boolean flag1 = false;
        boolean flag2 = false;

        cutomer_Ticktnum = customer.getTicketNum() + "";
        String lastName = customer.getLname();
        boolean result = false;
        Scanner read = new Scanner(redFile); //read

        while (read.hasNext()) {

            String[] line = read.nextLine().split(" ");
            if (lastName.equalsIgnoreCase(line[0]) && cutomer_Ticktnum.equalsIgnoreCase(line[1])) {
                if (iban.equalsIgnoreCase(line[3])) {
                    flag1 = true;
                }
                if (Endo_check == true) {
                    flag2 = true;
                }

                if (Endo_check == true && flag1 == true) {
                    flag2 = true;
                    int approve = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit your claim ? ", " ", JOptionPane.OK_CANCEL_OPTION);

                    if (approve == 0) {
                        color = "green";
                        msg = "Your claim submited";
                        for (int j = 0; j < line.length; j++) {
                            bw.write(line[j] + " ");
                        }
                        bw.newLine();

                        customer.setIban(iban);
                        customer.setFees(line[4]);
                        customer.setTicketPrice(Double.parseDouble(line[5]));
                        customer.setTicketState(line[6]);
                        customer.setRefundedAmount(Double.parseDouble(line[7]));

                        claim = new claim(customer);
                    } else {
                        color = "red";
                        msg = "The claim is not submited";

                    }

                  

//                    result = true;
                    break;

                }
            }
        }

        if (flag2 == false && flag1 == false) {
            color = "red";
            msg = "Fill the required fields";
        }

        if (flag2 == false && flag1 == true) {
            color = "red";
            msg = "Agree on the Endorsment.";
        }

        if (flag2 == true && flag1 == false) {
            color = "red";
            msg = "Re-enter your IBAN.";
        }

        read.close();
        bw.flush();
        bw.close();

        return msg;
    }

    /**
     *
     * @param Endo_check if the customer agreed on the endorsment or not
     * @return Boolean either the claim is submitted or not
     * @throws FileNotFoundException to avoid having an error for not founding
     * the file.
     * @throws IOException to avoid having an error because a failed or
     * interrupted I/O operations.
     */
    public String creditCard(Boolean Endo_check) throws FileNotFoundException, IOException {

        FileWriter fw = new FileWriter(output, true);
        BufferedWriter bw = new BufferedWriter(fw);

        boolean flag2 = false;
        boolean result = false;

        String num = customer.getTicketNum() + "";
        String lastName = customer.getLname();

        Scanner read = new Scanner(redFile); //read
        while (read.hasNext()) {

            String[] line = read.nextLine().split(" ");

            if (lastName.equalsIgnoreCase(line[0]) && num.equalsIgnoreCase(line[1])) {
                if (Endo_check == true) {
                    flag2 = true;
                    int approve = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit your claim ? ", " ", JOptionPane.OK_CANCEL_OPTION);

                    if (approve == 0) {
                        color = "green";
                        msg = "Your claim submited";

                        for (int j = 0; j < line.length; j++) {
                            bw.write(line[j] + " ");
                        }
                        bw.newLine();

                        customer.setIban(line[3]);
                        customer.setFees(line[4]);
                        customer.setTicketPrice(Double.parseDouble(line[5]));
                        customer.setTicketState(line[6]);
                        customer.setRefundedAmount(Double.parseDouble(line[7]));

                        claim = new claim(customer);

                    } else {
                        color = "red";
                        msg = "The claim is not submited";

                    }

//                    JOptionPane.showMessageDialog(RefundSystemWebsiteWindow.this, "Your claim submited");
//                    msg = "Your claim submited";
                    result = true;
                    break;

                }
            }
        }

        if (flag2 == false) {
            color = "red";
            msg = "Agree on the Endorsment.";
        }

        read.close();
        bw.flush();
        bw.close();

        return msg;

    }

    /**
     * This method display the interfaces depending on the ticket type. if the
     * ticket have fees it type will be complicated otherwise it is simple
     */
    public void close() throws FileNotFoundException {
        Close_complicated comp = new Close_complicated();
        Close_simple simple = new Close_simple();

        close obj_close = new close();

        String Tic_num = obj_close.getTicketNumber();
        Scanner r = new Scanner(redFile2); //read
        String fees;
        fees = refundObj.checkTicketType(Tic_num);

        if (fees.equalsIgnoreCase("fees")) {
            comp.setVisible(true);

            while (r.hasNext()) {
                String[] line = r.nextLine().split(",");
                if (Tic_num.equalsIgnoreCase(line[0])) {
                    comp.info(line[8]);
                }

            }
        } else if (fees.equalsIgnoreCase("nofees")) {
            simple.setVisible(true);
            while (r.hasNext()) {
                String[] line = r.nextLine().split(",");
                if (Tic_num.equalsIgnoreCase(line[0])) {
                    simple.info(line[8]);
                }

            }

        }

    }

}
