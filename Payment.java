/******************************************************************************
	Payment Class
*******************************************************************************/

import java.util.*;
import java.lang.*;
import java.io.*;

public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
    
	private double clientPayment;
	private Client clientAccount;
    private String description;

//-----------Constructor-------------------------
	public Payment(double p, Client c, String d){
		clientPayment = p;
		this.clientAccount = c;
        this.description = d;
		sendToAccount();
	}//end Constructor
    
//-------------Accessor--------------------
    public double getPayment(){
        return clientPayment; }
    
//-------------SendToAccount---------------------------
// Sends payment to Clients account updating balance
//-----------------------------------------------------
	private void sendToAccount(){
			clientAccount.addPayment(this);  }
	
//-------------toString()-------------------
	public String toString(){
		return  " Amount: " + clientPayment + 
               "\n" + description;
	}//end toString

}//end Payment Class





