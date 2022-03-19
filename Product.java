/**************************************************************************
Product Class 
 Create product object. Each product has a description, id,
 Sale Price and amount in stock.
***************************************************************************/

import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private String name;
  private int id;
  private int inStock;
  private double salePrice;
  private List<WaitListProduct> waitList = new ArrayList<WaitListProduct>();

//----------Contructor-------------------
  public Product(){
    id = ProductIdServer.instance().getId();
    name = "";
    salePrice = 0;
    inStock = 0;
  }

//---------accessors------------------
  public int getProductNumber(){
    return id;   }

  public String getName(){
    return name;  }

  public double getSalePrice(){
    return salePrice;     }

  public int getStock(){
	return inStock;  }

  
  public String getData(){
    String data = "";
    data += name;
    data += "\n\tId: " + id;
    data += "\n\tSale Price:     ";
    data += salePrice;
    data += "\n\tStock:          ";
    data += inStock;
    data += "\n\tNumber of waitlisted orders: ";
    data += waitList.size();
    return data;
    }
    
    
//-----------mutators----------------
  public void setName(String input){
    name = input;  }
    
  public void setSalePrice(double input){
    salePrice = input;     }

    
  public void setData(){
    System.out.println("To use data Setter input data members in this order\n\tString name\n\tdouble salePrice");
    }
    
  public void setData(String n, double sp, int st){
    name            =    n;
    salePrice       =   sp;
    inStock 		=   st;

    }
    
    
//-------------removeStock------------
// Remove specified amount of stock
//------------------------------------
  public void removeStock(int r){
	inStock -= r;
  }//end removeStock
  
//-----------AddItem-----------------
// Add Item to Stock from Shipment
//-----------------------------------
  public void addStock(int quantity){
	  inStock += quantity;
  }//end addShippedItem
  
  
//--------WaitListProduct--------------
  public void waitListProduct(Order o, int quantity){
	waitList.add(new WaitListProduct(o, quantity));
  }//end waitListItem

    
//------WaitList Iterator-----------------
  public Iterator getWaitList(){
	  return waitList.iterator();	  
  }//end getWaitList
  
//-----------FulfillWaitlist---------------------
// Process item from waitlist then remove it
//-------------------------------------------------
  public void finishWaitList(WaitListProduct item){
     removeStock(item.getQuantity());
	  item.process();
	  waitList.remove(item);
  }//end finishWaitlist
    
//-------------toString()----------------
  public String toString(){
     return getData();  }
    
	  
}//end Product Class





