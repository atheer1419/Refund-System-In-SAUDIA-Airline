/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refundsysteminsaudiairline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * This class perform methods for the cash and banking department.
 *
 * @author Gruop 27
 */
public class Cash_and_Banking extends javax.swing.JFrame {

    RefundDepartment objectOfRefund;
    File approvalFile = new File("Approval.txt");
    claim creditArray[] = new claim[10];
    static claim[][] cash_array = new claim[5][2];

    public static int Index_cc = 0;
    public static int Index_cash = 0;

    /**
     * This method return the credit card array.
     *
     * @return claim[]
     */
    public claim[] getCreditArray() {
        return creditArray;
    }

    /**
     * This method return the Index_cc.
     *
     * @return int
     */

    public static int getIndex_cc() {
        return Index_cc;
    }

    /**
     * This method return the Index_cash.
     *
     * @return int
     */

    public static int getIndex_cash() {
        return Index_cash;
    }

    /**
     * This method return the cash array.
     *
     * @return claim[][]
     */
    public claim[][] getCash_array() {
        return cash_array;
    }

    /**
     * This method will store all the Cash ticket in files. each file will
     * contain two ticket at most.
     *
     * @throws FileNotFoundException to avoid having an error for not founding
     * the file.
     */
    public void storeInFile() throws FileNotFoundException {
        Scanner read = new Scanner(approvalFile); //read
        String[] line;

        boolean flag1 = false;

        while (read.hasNext()) {
            for (int i = 0; i < cash_array.length; i++) {
                if (read.hasNext() != false) {

                    for (int j = 0; j < cash_array[i].length; j++) {

                        line = read.nextLine().split(" ");
                        System.out.println("line "+Arrays.toString(line));
                        
                        if (line[2].equalsIgnoreCase("cash")) {
                            flag1 = true;
                            Customer customer = new Customer(line[0], Integer.parseInt(line[1]), line[6], line[4], Double.parseDouble(line[5]), line[3], Double.parseDouble(line[7]));
                            claim claim = new claim(customer);

                            cash_array[i][j] = claim;
                        }
                        if (flag1 == false) {
                            j -= 1;

                        }
                        flag1 = false;

                    }

                }
            }

        }

    }

    /**
     * This method will store all the Credit Card ticket in array.
     *
     * @throws FileNotFoundException to avoid having an error for not founding
     * the file.
     */
    public void storeCreditCard() throws FileNotFoundException, NoSuchElementException {

        Scanner read = new Scanner(approvalFile); //read
        String[] line;

        for (int i = 0; i < creditArray.length; i++) {
            while (read.hasNext()) {

                line = read.nextLine().split(" ");

                if (line[2].equalsIgnoreCase("creditcard")) {

                    Customer customer = new Customer(line[0], Integer.parseInt(line[1]), line[6], line[4], Double.parseDouble(line[5]), line[3], Double.parseDouble(line[7]));
                    claim claim2 = new claim(customer);
                    creditArray[i] = claim2;
                    break;
                }

            }

        }
    }

    /**
     * This method will send the files to the bank. it will send the Cash ticket
     * that is stored in files to the bank.
     *
     * @param cachArray[][] contain the cash tickets.
     * @return int return number of the file
     */
    public String sendFile(claim cachArray[][]) {
        String Tic_numCash = "";
        cash_banking frame_obj = new cash_banking();
        for (int i = 0; i < cash_array.length; i++) {

            for (int j = 0; j < cash_array[i].length; j++) {
                cash_array[i][j] = cash_array[(i + 1) % cash_array[j].length][j];

            }
            Index_cash = i;
            Tic_numCash = frame_obj.getNum();
            break;

        }

        return Tic_numCash;

    }

    /**
     * This method will send the tickets to the bank one by one.
     *
     * @param creditArray[] contain the credit card tickets.
     * @return int return the Ticket Number
     */
    public String sendCreditCardOneByOne(claim creditArray[]) throws NullPointerException {
        claim creditArray_copy[] = new claim[10];
        int cope_counter = 0;
        String tic_num = "";

        cash_banking frame_obj = new cash_banking();

        for (int i = 0; i < creditArray.length; i++) {

            if (frame_obj.getTickNum().equalsIgnoreCase(creditArray[i].getCustomer().getTicketNum() + "")) {
                tic_num = creditArray[i].getCustomer().getTicketNum() + "";
                for (int j = i; j < creditArray.length - 1; j++) {

                    creditArray[j] = creditArray[j + 1];
                }

                Index_cc = i;
                break;
            }

        }

        return tic_num;

    }

}
