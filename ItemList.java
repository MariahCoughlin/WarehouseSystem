/******************************************************************************
	ItemList Class
        List of items with quantity
******************************************************************************/

import java.util.*;
import java.lang.*;
import java.io.*;

public class ItemList implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private ArrayList<OrderedProduct> itemList;
	
 //-------Constructor---------------------
	public ItemList(){
		itemList = new ArrayList<OrderedProduct>();
	}//end ItemList constructor

 //-------Add Item--------------------
 // Create new item to add to list
 //-----------------------------------
	public void addItem(Product newProduct, int quantity){
		itemList.add( new OrderedProduct(newProduct, quantity) );
	}
    
 //-------Add item-------------------
 // Add product to list
 //----------------------------------
	public void addItem(OrderedProduct item){
		itemList.add(item);
	}

//-------------Remove Item-------------------
// Remove product from list
//------------------------------------------
	public void removeItem(Product removeProduct){
		Iterator<OrderedProduct> it = itemList.iterator();
		OrderedProduct curr;
		boolean done = false;
		while(it.hasNext() && !done){
			curr = it.next();
			if(curr.getProduct() == removeProduct){
				it.remove();
				done = true;
			}//end if
		}//end while
	}//end removeItem

//----------------Clear---------------------
// Gives ItemList a blank list to refer to
//-----------------------------------------
	public void clear(){
		itemList = new ArrayList<OrderedProduct>();
    }//end clear()

//--------------Change Quantity-----------------------
//  Change quantity of product
//----------------------------------------------------
	public void changeQuantity(Product changeProduct, int quantity){
		Iterator<OrderedProduct> it = itemList.iterator();
		OrderedProduct curr;
		boolean done = false;
		while(it.hasNext() && !done){
			curr = it.next();
			if(curr.getProduct() == changeProduct){
				curr.changeQuantity(quantity);
				done = true;
			}//end if
		}//end while()
	}//end changeInQuantity

//-----------get Item List------------------
// Iterate through Item List
//------------------------------------------
	public Iterator<OrderedProduct> getIterator(){
		return itemList.iterator();
	}//end getIterator

//---------toString()--------------------
  public String toString(){
	String returnedString = "";
	Iterator curr = itemList.iterator(); 
	while(curr.hasNext())
		returnedString = returnedString.concat(curr.next().toString() + '\n');
	return returnedString;
  }//end toString

}//end ItemList Class



