/******************************************************************************
WaitListProduct Class
******************************************************************************/

import java.util.*;
import java.io.*;
import java.lang.*;

public class WaitListProduct implements Serializable{
	private static final long serialVersionUID = 1L;
    
	private Order order;
	private Product product;
	private int quantity;

//----------Constructor---------------------
	public WaitListProduct(Order o, int q){
		order = o;
		this.quantity = q;

	}
    
//---------accessors----------------
	public Order getOrder(){
        return order;	}

	public int getQuantity(){
		return quantity; 	}
	
	public Product getProduct(){
		return product; }
	

//-------toString()-----------------
	public String toString(){
		return "Order Id: " + order.getId() + " | Quantity: " + quantity + '\n';
	}//end toString
	
    
//----------Process()-------------------
// Process waitList Item
//--------------------------------------
    public void process(){
		Invoice i = new Invoice(order.getClient());
		i.addItem(new OrderedProduct(product, quantity));
		i.applyInvoice();
	}//end process

}//end Waitlist Item class
