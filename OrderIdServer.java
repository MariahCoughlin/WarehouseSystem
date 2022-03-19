/******************************************************************************
OrderIdServer Class 
 
 Generates unique id for Order object
******************************************************************************/

import java.io.*;
import java.lang.*;
import java.util.*;

public class OrderIdServer implements Serializable{
	
    private int idCounter;
	private static OrderIdServer server;
	
//------------Constructor-----------
    private OrderIdServer(){
		idCounter = 1;          }
    
//--------------Instance--------------
	public static OrderIdServer instance() {
		if(server == null)
			return (server = new OrderIdServer());
		else
			return server;
	}//end instance()
	

//------------accessor-----------------
	public int getId(){
		return idCounter++;  }
	
//-----------toString()----------------
	public String toString(){
		return ("OrderIdServer. idCounter: " + idCounter + '\n');
	}//end toString
    
//----------------Retrieve------------------
	public static void retrieve(ObjectInputStream in){
		try{
			server = (OrderIdServer) in.readObject();
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}//end try-catch
	}//end retrieve()	
	
//------------writeObject--------------------
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		try{
			out.defaultWriteObject();
			out.writeObject(server);
		} catch(IOException e){
			e.printStackTrace();
		}//end try-catch
	}//end writeObject
	
//--------------readObject------------------------
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		try{
			in.defaultReadObject();
			if(server == null){
				server = (OrderIdServer) in.readObject();
			} else{
				in.readObject();
			}//end if-else
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		} //end try-catch
	}//end readObject
}//end OrderIdServer class



