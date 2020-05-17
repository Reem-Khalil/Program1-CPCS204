/*  Reem khalil   -   ID : 1505841   -   section number : BAR   -    EMAIL : rkhalil0006@stu.kau.edu.sa  */
package fcitmobile;

import java.io.*;
import java.util.*;

public class FCITmobile {

    
    
    
    
    // ........ Data Members ........ //
    
    // To be accessible for each method   
    static Scanner scan;
    static PrintWriter print;
    static FCITcustomer[] customers; // declear 1 large array, which will hold all of the FCIT customers 
    static FCITcustomer newCustomer;

    
    
    
    
    // .......... Methods ........... //  
    
    public static void main(String[] args) throws FileNotFoundException {

        
        // creating variables ..
        
        File input = new File("FCITmobile.in.txt");
        scan = new Scanner(input);                             //  to read input from a inputfile
        File output = new File("FCITmobile.out.txt");
        print = new PrintWriter(output);                     // to write output in a output file

        
        
        int maxCustomers = scan.nextInt();                 // read the maximum number of customers that can be registered in the FCITMobile system.
        customers = new FCITcustomer[maxCustomers];       // creating the customers array
        boolean QUIT = false;   // to help me in trace 

        
        while (scan.hasNext()) { // to read all lins .

            switch (scan.next()) {  // use switch to implemente  The Commands 

                
                
                
                case "ADDCUSTOMER": // make a new FCITcustomer object if it's allowed then put it in suitable index  & save this command in output file

                    if (FCITcustomer.getNumcustomers() < maxCustomers) {
                        ADDCUSTOMER();
                        print.println("Command: ADDCUSTOMER");
                        newCustomer.printInfo(print);
                    } else 
                        scan.nextLine();  // Dispose of this object's information
                    
                    break;

                    
                    
                    
                case "MAKECALL":

                    MAKECALL(scan.nextInt());
                    break;

                    
                    
                    
                case "SENDTEXT":

                    SENDTEXT(scan.nextInt());
                    break;

                    
                    
                    
                case "RECHARGE":

                    RECHARGE(scan.nextInt());
                    break;

                    
                    
                    
                case "SEARCH":

                    SEARCH(scan.nextInt());
                    break;

                    
                    
                    
                    
                case "DISPLAYDETAILS":

                    DISPLAYDETAILS(scan.nextInt());
                    break;

                    
                    
                    
                case "QUIT":

                    QUIT = true;
                    print.println("Command: QUIT.");
                    print.println("\tExiting the FCIT Mobile System...");
                    print.println("\tGoodbye.");

                    
            }
            if(QUIT)
                break;
        }

        
        scan.close();
        print.flush();
        print.close();

    }

    
    
    
    
    public static void ADDCUSTOMER() // this method make a new FCITcustomer objectthen put it in suitable index
    {

        newCustomer = new FCITcustomer();  // make a new FCITcustomer object then scan from the file the customer ID, the first and last name of the customer, and the new phone number
        newCustomer.setID(scan.nextInt());
        newCustomer.setFirstName(scan.next());
        newCustomer.setLastName(scan.next());
        newCustomer.setPhoneNumber(scan.next());

        
        // save the reference of this object inside the appropriate index of the customers array
        if (FCITcustomer.getNumcustomers() == 0) {  // Save the obj. in the first indix if the array is empty + increase the acounter  ... then exit the method
            customers[0] = newCustomer;
            FCITcustomer.increaseNumcustomers();
            return;
        }

        
        for (int i = 0; i < FCITcustomer.getNumcustomers(); i++) {  // to find the correct insertion location if the array isn't empty

            if (newCustomer.getID() > customers[i].getID() && i != (FCITcustomer.getNumcustomers() - 1)) {
                continue;     // to compare with a next customer .
                
                
            } else if (newCustomer.getID() > customers[i].getID() && i == (FCITcustomer.getNumcustomers() - 1)) // This means that the ID of new customer is bigger than all IDs ,so i will store it in the latter
            {
                customers[FCITcustomer.getNumcustomers()] = newCustomer;
                FCITcustomer.increaseNumcustomers();
                return;
                
                
            } // we use return; To leave the methode because we added the obj. in the array
            else if (newCustomer.getID() < customers[0].getID()) {   // This means that the ID of new customer is smaller than all IDs ,so i will SHIFT all objects to the right (one space to the right) &  store it in the first   
                for (int r = (FCITcustomer.getNumcustomers()); r > 0; r--) {
                    customers[r] = customers[r - 1];
                }

                customers[0] = newCustomer;
                FCITcustomer.increaseNumcustomers();
                return;
                
                
            } else if (newCustomer.getID() < customers[i].getID()) { // This means that the ID of new customer is smaller than specific IDs  ,so i will do appropriate SHIFTING  &  store newCustomer in the empty indix   
               
                int helpCounter = FCITcustomer.getNumcustomers();
                
                for (int j = 0; j <= (FCITcustomer.getNumcustomers() - i); j++) {

                    if (j == (FCITcustomer.getNumcustomers() - i)) {
                        customers[i] = newCustomer;
                        FCITcustomer.increaseNumcustomers();
                        return;
                    }

                    customers[helpCounter] = customers[(helpCounter - 1)];
                    helpCounter -= 1;
                }
            }
        }
    }

    
    
    
    
    
    public static void MAKECALL(int ID) // this method process MAKECALL command
    {

        String callednumber = scan.next();  // scan date 
        int callDuration = scan.nextInt();
        double callBalance = callDuration * 14;  // calculate the coct of call & stor it in callBalance
        boolean isfound = false;  // to help me in trace 
        print.println("Command: MAKECALL");   // to save this command in output file

        
        for (int i = 0; i < FCITcustomer.getNumcustomers(); i++) {

            
            if (ID == customers[i].getID()) { // This means that record is found in the customers array 
                isfound = true;

                
                
                if (callBalance <= customers[i].getBalance()) {  //************* if he has enough balance *************//

                    customers[i].setBalance((customers[i].getBalance() - callBalance)); // 1 //  We deduct the cost of the call from the balance

                    if (customers[i].getNumCalled() == 0) {    // 2 // to add  this call in index 0 Because the array is empty

                        customers[i].getCalledNumbers()[0] = callednumber;
                        customers[i].getCallDuration()[0] = callDuration;
                        customers[i].increaseNumCalled();
                        customers[i].printCallInfo(print, callednumber, callDuration, callBalance);
                        return;

                        
                    } else {

                        for (int j = customers[i].getNumCalled(); j > 0; j--) {  // 3 // to add last CalledNumber & callDuration in the first (in each array) & shift another objs to the rigth
                            if (j == 10) {
                                continue;
                            }

                            
                            customers[i].getCalledNumbers()[j] = customers[i].getCalledNumbers()[j - 1];
                            customers[i].getCallDuration()[j] = customers[i].getCallDuration()[j - 1];
                        }

                        
                        customers[i].getCalledNumbers()[0] = callednumber;
                        customers[i].getCallDuration()[0] = callDuration;
                        customers[i].increaseNumCalled();
                        customers[i].printCallInfo(print, callednumber, callDuration, callBalance);
                        return;

                        
                    }
                } else { //************** if has no balance or some balance, but not enough to complete the call *************//

                    callDuration = (int) customers[i].getBalance() / 14;
                    if (callDuration > 0) {

                        
                        callBalance = callDuration * 14;
                        customers[i].setBalance((customers[i].getBalance() - callBalance)); // 1 //  We deduct the cost of the call from the balance

                        
                        for (int j = customers[i].getNumCalled(); j > 0; j--) {  // 2 // to add this CalledNumber & callDuration in the well index (in CalledNumbers & CallDuration arrays) & shift another objs to the rigth
                            if (j == 10) {
                                continue;  // to delete saved call .. since the FCITMobile system has limited memory
                            }
                            
                            customers[i].getCalledNumbers()[j] = customers[i].getCalledNumbers()[j - 1];
                            customers[i].getCallDuration()[j] = customers[i].getCallDuration()[j - 1];
                        }

                        customers[i].getCalledNumbers()[0] = callednumber;
                        customers[i].getCallDuration()[0] = callDuration;
                        customers[i].increaseNumCalled();
                        customers[i].printCallInfo2(print, callednumber, callDuration, callBalance);
                        return;

                        
                    } else {  //************* if the customer has zero balance *************//
                        print.println("\tCannot perform MAKECALL. the customer has zero balance.");
                        return;

                    }
                }
            }
        }
        if (!isfound) // This means that record isn't found
        {
            print.println("\tCannot perform MAKECALL. Account does not exist in FCIT Mobile System.");
            print.println();
        }
    }

    
    
    
    
    
    public static void SENDTEXT(int ID) // this method process SENDTEXT command
    {

        String textedNumber = scan.next();  // scan date 
        boolean isfound = false;  // to help me in trace 
        print.println("Command: SENDTEXT");   // to save this command in output file

        
        for (int i = 0; i < FCITcustomer.getNumcustomers(); i++) {

            
            if (ID == customers[i].getID()) { // This means that record is found in the customers array 
                isfound = true;

                
                if (customers[i].getBalance() >= 8) {  //************* if he has enough balance *************//

                    customers[i].setBalance((customers[i].getBalance() - 8)); // 1 //  We deduct 8 halalas from the balance Because The text message rate is fixed at the rate of 8 halalas/message

                    if (customers[i].getNumTexted() == 0) {    // 2 // to add  this texted number in index 0 Because the array is empty

                        customers[i].getTextedNumbers()[0] = textedNumber;
                        customers[i].increaseNumTexted();
                        customers[i].printTextInfo(print, textedNumber);
                        return;

                        
                        
                    } else {
                        for (int j = customers[i].getNumTexted(); j > 0; j--) {  // 3 // to add  this texted number in the first in (textedNumbers array) & shift another objs to the rigth

                            if (j == 10) {
                                continue; // to delete saved NumTexted .. since the FCITMobile system has limited memory
                            }
                            customers[i].getTextedNumbers()[j] = customers[i].getTextedNumbers()[j - 1];
                        }
                        

                        customers[i].getTextedNumbers()[0] = textedNumber;
                        customers[i].increaseNumTexted();
                        customers[i].printTextInfo(print, textedNumber);
                        return;

                    }
                    
                    

                } else { //************** if has no balance or some balance, but not enough  *************//

                    print.println("\tCannot perform SENDTEXT. the customer does not have enough balance.");
                    return;

                }
            }
        }
        if (!isfound) // This means that record isn't found
        {
            print.println("\tCannot perform SENDTEXT. Account does not exist in FCIT Mobile System.");
            print.println();
        }

    }

    
    
    
    
    public static void RECHARGE(int ID) // this method process RECHARGE command
    {

        boolean isfound = false;  // to help me in trace 
        print.println("Command: RECHARGE");   // to save this command in output file

        for (int i = 0; i < FCITcustomer.getNumcustomers(); i++) {

            if (ID == customers[i].getID()) { // This means that record is found in the customers array 
                isfound = true;
                double RechargeAmount = scan.nextDouble();
                customers[i].setBalance((customers[i].getBalance() + (RechargeAmount * 100)));  // it will be recharged by adding the amount to the existing balance & a message printed to output file
                customers[i].printRechargeInfo(print, RechargeAmount);
                return;
            }
        }
        
        
        if (!isfound) // This means that record isn't found
        {
            print.println("\tCannot perform RECHARGE. Account does not exist in FCIT Mobile System.");
            print.println();
        }

    }

    
    
    public static void SEARCH(int ID) // this method process SEARCH command by using Linear search
    {

        boolean isfound = false;  // to help me in trace 
        print.println("Command: SEARCH");   // to save this command in output file

        
        for (int i = 0; i < FCITcustomer.getNumcustomers(); i++) {

            
            if (ID == customers[i].getID()) { // This means that record is found in the customers array 
                isfound = true;
                customers[i].printInfo(print); // to save this in output file
            }
        }
        if (!isfound) // This means that record isn't found
        {
            print.println("\tCannot perform SEARCH. Account does not exist in FCIT Mobile System.");
            print.println();
        }
    }
    
    

    public static void DISPLAYDETAILS(int ID) // this method process DISPLAYDETAILS command by using Binary search
    {

        int low = 0;
        int high = (FCITcustomer.getNumcustomers() - 1);
        ArrayList<Integer> indices = new ArrayList();   // to save indices that are visited during the search then print it .
        boolean isfound = false;  // to help me in trace 
        
        
        print.println("Command: DISPLAYDETAILS");   // to save this command in output file
        print.print("\tPerforming Binary Search...(Indices viewed: ");  
        
        
        while (low <= high) {
            int mid = (low + high) / 2;
            indices.add(mid);
            
            
            if (ID == customers[mid].getID()) {
                // meaning, “found” ... it will display last 10 call details (called number with duration) and last 10 numbers for sent messages.
                //*****************************************************************************************************************************
                
                isfound = true ;
                for (int i = 0 ; i<indices.size() ; i++)
                    print.print(indices.get(i)+ " ");
                
                
                print.println(")");
                customers[mid].DISPLAYDETAILS(print);
                break;
                
                
            } else if (ID < customers[mid].getID()) 
                high = mid - 1;
            
            
             else // if ( ID > customers[mid].getID() )
            
                low = mid + 1;
            

        }
        if ( ! isfound )  // this only happens if not found
        { print.println(")");
          print.println("\tCannot perform DISPLAYDETAILS. Account does not exist in FCIT Mobile System."); 
          print.println(); 
        }
    }

}
