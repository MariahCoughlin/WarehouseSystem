/*****************************************************************************
	OrderList Class
	
	List of all the Orders in the Warehouse System
	
*****************************************************************************/

import java.util.*;
import java.io.*;
import java.lang.*;

public class OrderList implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private ArrayList<Order> orders;
	private static OrderList orderList;
	
	
//----------Constructor--------------
	public OrderList(){
		orders = new ArrayList<Order>();  }

//----------Instance------------------
    public static OrderList instance() {
        if(orderList == null){
            return (orderList = new OrderList() );
        } else{
            return orderList;
        }//end if-else
    }//end instance()
    
//----------Add Order--------------
// Adds Order to OrderList
//--------------------------------
	public void addOrder(Order o){
		orders.add(o);
	}//end addOrder

//--------Remove Order---------------
// Removes order from Order List
//-----------------------------------
    public void removeOrder(Order o){
		orders.remove(o);
	}//end removeOrder

//--------------Find Order-----------------------
// Finds Order in Order List by the Order id.
// Returns Null if id not found
//---------------------------------------------
	public Order findOrder(int id){
		Iterator it = orders.iterator();
		Order curr = null;
		boolean done = false;
		while(!done && it.hasNext() ){
			curr = (Order)it.next();
			if(curr.getId() == id)
				done = true;
		}//end while
		if(!done){
			System.out.println("Order not found.");
			curr = null;
		}//end if
		return curr;
	}//end findOrder

//-------------getIterator----------------
// Iterates through all Order objects
//----------------------------------------
	public Iterator getIterator(){
		return orders.iterator();  }

//--------------writeObject---------------------
	private void writeObject(java.io.ObjectOutputStream output){
		try{
			output.defaultWriteObject();
			output.writeObject(orderList);
		} catch(IOException ioe){
			ioe.printStackTrace();
		}//end try-catch IOException
	}//end writeObject
	
//--------------readObject----------------------
	private void readObject(java.io.ObjectInputStream input){
		try{
			if(orderList != null){
				return;
			} else {
				input.defaultReadObject();
				if(orderList == null) {
					orderList = (OrderList) input.readObject();
				} else {
					input.readObject();
				} //end else
			}//end else
		} catch( IOException ioe) {
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end try-catch
	
	}
    
//----------------toString()--------------------
  public String toString(){
	String returnedString = "";
	Iterator curr = orders.iterator();
	while(curr.hasNext())
		returnedString = returnedString.concat(curr.next().toString() + '\n');
	return returnedString;
  }//end toString
}






