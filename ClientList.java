/*****************************************************************************
	Client List Class

	List of all Clients
	
******************************************************************************/

import java.util.*;
import java.lang.*;
import java.io.*;

public class ClientList implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Client> clients;
	private static ClientList clientList;
	
//-------------Constructor----------------
    public ClientList(){
		clients = new ArrayList<Client>();  }
	
//-------------Instance--------------------
	public static ClientList instance() {
		if(clientList == null){
			return (clientList = new ClientList() );
		} else{
			return clientList;
		}//end else	
	}//end instance()

//-----------Add Client------------------
//  Add Client to Client List
//---------------------------------------
	public void addClient(Client c){
		clients.add(c);
	}//end addClient()

//----------Remove Client-----------------
// Remove Client from Client List
//----------------------------------------
	public void removeClient(Client c){
		clients.remove(c);
	}//end removeClient()

//------------findClient---------------------
//  Find Client in Client List
//      Returns Client ID or NULL
//--------------------------------------------
	public Client findClient(int id){
		Iterator it = clients.iterator();
		Client curr = null;
		boolean done = false;
		while(!done && it.hasNext() ){
			curr = (Client)it.next();
			if(curr.getId() == id)
				done = true;
		}//endwhile
		if(!done){
			System.out.println("Client not found.");
			curr = null;
		}//endif
		return curr;
	}//end findClient

    
//------------getIterator()-----------------------
	public Iterator<Client> getIterator(){
		return clients.iterator();
	}//end getIterator

//---------------Write Object--------------------------------
	private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(clientList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }//end try-catch block
  }

//------------------Read Object-------------------------
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (clientList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (clientList == null) {
          clientList = (ClientList)input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }//end try-catch block
  }
    
//--------------toString()-------------------------
  public String toString(){
	String returnedString = "";
	Iterator curr = clients.iterator(); 
	while(curr.hasNext())
		returnedString = returnedString.concat(curr.next().toString() + '\n');
	return returnedString;
  }//end toString
    
    
}//end ClientList Class











