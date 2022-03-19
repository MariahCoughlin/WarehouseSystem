/******************************************************************************
	InvoiceList Class
 
     List of all invoices for a single Client

******************************************************************************/

import java.util.*;
import java.lang.*;
import java.io.*;

public class InvoiceList implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
    private ArrayList<Invoice> invoices;

//--------------Constructor------------------
//  Creates new empty invoice list
//-------------------------------------------
	public InvoiceList(){
		invoices = new ArrayList<Invoice>();
	}//end InvoiceList

//-------------Add Invoice----------------
//  Add Invoice to Client Invoice List
//----------------------------------------
	public void addInvoice(Invoice in){
		invoices.add(in);
	}//end addInvoice

//----------Remove Invoice------------------
//  Remove invoice from Client Invoice List
//-------------------------------------------
	public void removeInvoice(Invoice in){
		invoices.remove(in);
	}//end removeInvoice

//-----------get Invoice -------------------
//  Iterator that iterates through Invoice
//-------------------------------------------
	public Iterator<Invoice> getInvoice(){
		return invoices.iterator();
	}//end getIterator

//-----------toString()--------------------
	public String toString(){
		return invoices.toString();
	}//end toString
}
