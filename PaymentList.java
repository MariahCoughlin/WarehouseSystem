/*******************************************************************
PaymentList.java
    List of specific Clients Payments
*******************************************************************/

import java.util.*;
import java.lang.*;
import java.io.*;

public class PaymentList implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private ArrayList<Payment> payments;
    
//--------Contructor---------------
	public PaymentList(){
		payments = new ArrayList<Payment>();  }

//--------Add Payment-------------
// Adds payment to payment list
//---------------------------------
	public void addPayment(Payment t){
		payments.add(t);   }

//-------Remove Payment------------
// Remove payment from payment list
//---------------------------------
	public void removePayment(Payment t){
		payments.remove(t);   }
    
//-----------Iterator-------------------
	public Iterator<Payment> getIterator(){
		return payments.iterator();
	}//end getIterator
	
//----------toString()-------------------
	public String toString(){
		return payments.toString();
	}//end toString
}
