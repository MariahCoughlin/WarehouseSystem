/******************************************************************************
 Client Id Server Class
    Creates a unique ID for client object.
******************************************************************************/

import java.io.*;
import java.lang.*;
import java.util.*;

public class ClientIdServer implements Serializable{
	
    private static ClientIdServer server;
    private int idCounter;
	
 //----------Constructor----------------
    private ClientIdServer() {
        idCounter = 1; }
	
 //----------Instance()------------------
    public static ClientIdServer instance() {
		if(server == null)
			return (server = new ClientIdServer());
		else
			return server;  }
    
 //----------Accessor--------------------
	public int getId(){
		return idCounter++;
	}//end getId
	
 //--------toString()------------------------------
	public String toString(){
		return ("ClientIdServer. idCounter: " + idCounter + '\n');
	}//end toString
	
    
//-------------Retrieve method----------------------------
	public static void retrieve(ObjectInputStream in){
		try{
			server = (ClientIdServer) in.readObject();
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}//end try-catch
	}//end retrieve()
	
 //--------------------Write Object----------------------
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		try{
			out.defaultWriteObject();
			out.writeObject(server);
		} catch(IOException e){
			e.printStackTrace();
		}//end try-catch
	}//end writeObject

 //-----------------Read Object--------------------------
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		try{
			in.defaultReadObject();
			if(server == null){
				server = (ClientIdServer) in.readObject();
			} else{
				in.readObject();
			}//end if-else
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		} //end try-catch
	}//end readObject
}//end ClientIdServer class
