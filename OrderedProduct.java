/******************************************************************************
	Ordered Product Class
            Specific product ordered with specified quantity ordered.
*******************************************************************************/

import java.util.*;
import java.io.*;
import java.lang.*;

public class OrderedProduct implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private Product productOrdered;
	private int quantity;

//----------Constructor-----------------
	public OrderedProduct(Product item, int quantity){
		productOrdered = item;
		this.quantity = quantity;               }

//-----------Accessors--------------------
    public Product getProduct() {
        return productOrdered;   }
    
    public int getQuantity() {
        return quantity;      }
 
//--------------mutator--------------
	public void changeQuantity(int quantity){
		this.quantity = quantity;        }

//--------toString()---------------------
	public String toString(){
		return "Item Id: " + productOrdered.getProductNumber() + "   Name: " + productOrdered.getName() + "   Quantity: " + quantity + '\n';
	}//end toString
}
