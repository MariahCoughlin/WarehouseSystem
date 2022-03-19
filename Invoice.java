/******************************************************************************
	Invoice Class
        Creates Invoice for Client -- Invoice includes Items and Amount Due 
*******************************************************************************/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Invoice implements Serializable{
	private static final long serialVersionUID = 1L;
	private double amount;
	private Client clientAccount;
	private ItemList items;

//-----------Constructor---------------------------------
	public Invoice(Client client){
		this.clientAccount = client;
		items = new ItemList();
		amount = 0.0f;
	}//end Constructor

//-----------addItem---------------------------
// Add products to invoice
//----------------------------------------------
	public void addItem(OrderedProduct i){
		items.addItem(i);
		amount += i.getQuantity() * i.getProduct().getSalePrice();
	}//end addItem

//------------getInvoice-------------------
// Returns invoice amount
//-----------------------------------------
	public double getInvoiceAmount(){
		return amount;
}//end getInvoiceAmount

//-----------------sendInvoice------------------------------
//  Sends invoice to client account, updates their balance
//----------------------------------------------------------
	public void applyInvoice(){
		clientAccount.addInvoice(this);
	}//end applyInvoice

//--------------toString()-------------------------
	public String toString(){
        return "\nClient id: " + clientAccount.getId() + " Amount: " + amount;
	}//end toString
	
//-----------ItemListString()-------------------------
// Displays toString() AND item list.
//-------------------------------------------------------
	public String itemListString(){
		return toString() + '\n' + items.toString();
	}//end



} //end Invoice Class












