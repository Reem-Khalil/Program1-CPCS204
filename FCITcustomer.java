/*  Reem khalil   -   ID : 1505841   -   section number : BAR   -    EMAIL : rkhalil0006@stu.kau.edu.sa  */

package fcitmobile;

import java.io.PrintWriter;

public class FCITcustomer {
    
    
    
    // ........ Data Members ........ //
    
    private int ID; 
    private String firstName; 
    private String lastName; 
    private String phoneNumber; 
    private double balance; 
    private String[] calledNumbers = new String[10] ;  // hold call history for the last 10 called numbers
    private int[] callDuration = new int [10];
    private int numCalled;  // As acounter .
    private String[] textedNumbers = new String[10];  // to hold the history of the last ten texted numbers
    private int numTexted;  // As acounter .
    private static int numcustomers;  // As acounter .
    
    
    
    
    // .......... Methods ........... //
    
    // .... Constructors .... //
    
    public FCITcustomer(int ID, String firstName, String lastName, String phoneNumber)
        {this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        
};

    public FCITcustomer() {
        this.balance = 1500 ; // all new accounts receive a free initial balance of 15 sr for subscribing to the service
    }

    // ...... Gettars ...... //
    
    public int getID() {
        return ID;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String[] getCalledNumbers() {
        return calledNumbers;
    }

    public int[] getCallDuration() {
        return callDuration;
    }

    public int getNumCalled() {
        return numCalled;
    }

    public String[] getTextedNumbers() {
        return textedNumbers;
    }

    public int getNumTexted() {
        return numTexted;
    }
    
    public static int getNumcustomers() {
        return numcustomers;
    }
 
    
    // ..... Settars ...... //
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCalledNumbers(String[] calledNumbers) {
        this.calledNumbers = calledNumbers;
    }
    
    public void setCallDuration(int[] callDuration) {
        this.callDuration = callDuration;
    }

    public void setNumCalled(int numCalled) {
        this.numCalled = numCalled;
    }

    public void setTextedNumbers(String[] textedNumbers) {
        this.textedNumbers = textedNumbers;
    }

    public void setNumTexted(int numTexted) {
        this.numTexted = numTexted;
    }

    public static void setNumcustomers(int numcustomers) {
        FCITcustomer.numcustomers = numcustomers;
    }
    
    
    // ... Serves Methods ... //

    
    public static void increaseNumcustomers( ) {  // Methode  increase Num. of customers 1
        numcustomers += 1 ;
    }
    
    
    public void increaseNumCalled( ) {  // Methode  increase Num. of calls 1
        numCalled += 1 ;
    }
    
    
    public void increaseNumTexted( ) {  // Methode  increase Num. of calls 1
        numTexted += 1 ;
    }
    
    
    public void printInfo( PrintWriter print ) {  // Methode prints customer information
        
        print.println("\tName:          " + firstName +" "+ lastName );
        print.println("\tCustomer ID:    " + ID ); 
        print.println("\tPhone Number:  " + phoneNumber ); 
        print.printf("\tBalance:       %.2f"  , (balance/100) );
        print.println();
}


    public void printCallInfo( PrintWriter print , String callednumber , int callDuration , double callBalance) {  // Methode prints Customer call information
        
        print.println("\tName:           " + firstName +" "+ lastName );
        print.println("\tPhone Number:   " + phoneNumber ); 
        print.println("\tNumber Called:  " + callednumber ); 
        print.println("\tCall Duration:  " + callDuration + " minutes" ); 
        print.printf("\tCall Cost:      %.2f"  , (callBalance/100) );
        print.printf("	New Balance:    %.2f"  , (balance/100)); 
        print.println(); 
}


    
     public void printCallInfo2( PrintWriter print , String callednumber , int callDuration , double callBalance) {  // Methode prints limited call information
        
        print.println("\tName:           " + firstName +" "+ lastName );
        print.println("\tPhone Number:   " + phoneNumber ); 
        print.println("\tNumber Called:  " + callednumber ); 
        print.println("\tCall Duration:  " + callDuration  ); 
        print.printf("\tCall Cost:      %.2f"  , (callBalance/100) );
        print.printf("	New Balance:    %.2f	***Call terminated due to low balance."  , (balance/100)); 
        print.println(); 
}


     
     public void printTextInfo( PrintWriter print , String textedNumber ) {  // Methode prints Customer call information
        
        print.println("\tName:           " + firstName +" "+ lastName );
        print.println("\tPhone Number:   " + phoneNumber ); 
        print.println("\tNumber Texted:  " + textedNumber );
        print.print("\tText Cost:      0.08");
        print.printf("	New Balance:    %.2f"  , (balance/100)); 
        print.println(); 
}
     
     
      public void printRechargeInfo( PrintWriter print , double RechargeAmount ) {  // Methode prints Recharge information
        
        print.println("\tName:            " + firstName +" "+ lastName );
        print.println("\tPhone Number:    " + phoneNumber ); 
        print.printf("\tRecharge Amount: %.2f" , RechargeAmount );
        print.printf("	New Balance:     %.2f"  , (balance/100)); 
        print.println(); 
}
      
      
      
      public void DISPLAYDETAILS( PrintWriter print ) {  // Methode prints DETAILS 
        
        print.println("\tName:          " + firstName +" "+ lastName );
        print.println("\tCustomer ID:    " + ID ); 
        print.println("\tPhone Number:  " + phoneNumber ); 
        print.println("\tCalled Numbers and Duration:" );
        if (numCalled != 0){
        for (int i = 0 ; i<numCalled ; i++)
            print.println("\t	" + calledNumbers[i] + " (" + callDuration[i] + ")"); }
        else 
            print.println("\t	(user has not made any calls yet)");
        
        print.println("\tTexted Numbers:" );
        if (numTexted != 0){
        for (int i = 0 ; i<numTexted ; i++)
            print.println("\t	" + textedNumbers[i] ); }
        else 
            print.println("\t	(user has not made any calls yet)");
        
        print.println();
}
}