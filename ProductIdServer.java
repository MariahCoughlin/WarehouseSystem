/******************************************************************************
Product ID server Class 
    Generates unique Product ID
******************************************************************************/

import java.io.*;
import java.lang.*;
import java.util.*;

public class ProductIdServer implements Serializable{
	private int idCounter;
	private static ProductIdServer server;
	
//-----------Constructor---------------
    private ProductIdServer(){
		idCounter = 1;
	}
    
//----------Instance---------------------
	public static ProductIdServer instance() {
		if(server == null)
			return (server = new ProductIdServer());
		else
			return server;
	}//end instance()

    
//----------accessor--------------------
	public int getId(){
		return idCounter++;
	}//end getId
	
//---------toString()----------------------
    public String toString(){
		return ("ProductIdServer. idCounter: " + idCounter + '\n');
	}//end toString

//----------retrieve-----------------------
	public static void retrieve(ObjectInputStream in){
		try{
			server = (ProductIdServer) in.readObject();
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}//end try-catch
	}//end retrieve()

//------------writeObject------------------
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		try{
			out.defaultWriteObject();
			out.writeObject(server);
		} catch(IOException e){
			e.printStackTrace();
		}//end try-catch
	}//end writeObject
    
//------------readObject--------------------
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		try{
			in.defaultReadObject();
			if(server == null){
				server = (ProductIdServer) in.readObject();
			} else{
				in.readObject();
			}//end if-else
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		} //end try-catch
	}//end readObject

}//end ProductIdServer class






