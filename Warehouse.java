/******************************************************************************
	Warehouse Class
		
******************************************************************************/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Warehouse implements Serializable{
    
	private ClientList clients;
	private ProductList products;
    private OrderList orders;
	private static Warehouse warehouse;
	private List<WaitListProduct> waitListRemoval = new LinkedList<>();
	private List<Product> waitListProducts = new LinkedList<>();

//-------Constructor---------------------
	public Warehouse(){
		clients = new ClientList();
        products = new ProductList();
		orders = new OrderList();
		
	}

//--------Instance-------------------------
	public static Warehouse instance(){
		if(warehouse == null) {
			ClientIdServer.instance();
            ProductIdServer.instance();
            OrderIdServer.instance();
			return (warehouse = new Warehouse() );
		} else {
			return warehouse;
		}//end if-else
	}//end instance



//~~~~~~~~~~~~CLIENT STUFF~~~~~~~~~~~~~~~~
    
//---------------AddClient--------------------
// Construct new Client Object and adds it
//  to client list
//----------------------------------------------
	public void addClient(String name, String addr){
		clients.addClient(new Client(name, addr) );
	}//end addClient
	
//---------------findClient----------------------
// Finds client by ID. Returns null if not found
//-----------------------------------------------
	public Client findClient(int id){
		Iterator it = clients.getIterator();
		Client c;
		while(it.hasNext()){
			c = (Client)it.next();
			if(c.getId() == id)
				return c;
		}//end while
		return null;
	}//end findClient

	
//---------VerifyClient---------------
// True if client is in clientList
// False if not found
//------------------------------------
	public boolean verifyClient(int id){
		return !(clients.findClient(id) == null);
	}//end verifyClient


//------ClientIterator-----------------
// Iterator to List of clients in
//   Warehouse System
//-------------------------------------
    public Iterator getClients(){
		return clients.getIterator();
	}//end getClients

//---------getClientBalance----------------
// Uses Client ID to get client Balance
//-----------------------------------------
	public double getClientBalance(int id){
		return clients.findClient(id).getClientBalance();
	}//end getClientBalance

//------------AddToCart--------------------------
// Using Client Id, Product Id, and a specified
// quantity it adds the specified product to
// the clients cart.
//-----------------------------------------------
	public void addToCart(int cId, int pId, int quantity){
		Client myClient = clients.findClient(cId);
		Product myProduct = products.findProduct(pId);
		myClient.addToCart(myProduct, quantity);
	}//end addToCart

//-----------getCart----------------------
// Iterator to return clients cart using
// specified client Id.
//----------------------------------------
    public Iterator getCart(int cId){
		return clients.findClient(cId).getCart();
	}//end getCart
   
//--------clearCart---------------------
// Clears client cart using specified
// client Id.
//--------------------------------------
    public void clearCart(int id){
      clients.findClient(id).clearCart();
   }//end clearCart
 
//---------modifyCart-------------------------------
// Using clientId, productId, and quantity it
// updates the quantity of product in clients cart
// if quantity is 0, it will be removed from cart
//-------------------------------------------------
    public void modifyCart(int cId, int pId, int q){
      clients.findClient(cId).changeItemQuantity(products.findProduct(pId), q);
   }//end modifyCartItem

//-----------makePayment----------------
// Using client id it creates a payment
// object for specified client account
//--------------------------------------
    public void makePayment(int clientId, double amount, String description){
		Payment p = new Payment(amount, clients.findClient(clientId), description);
	}//end makePayment

//----------getPayment----------------
// Iterator for list of payments for
// specified client.
//------------------------------------
    public Iterator getPayment(int id){
		return clients.findClient(id).getPayments();
	}//end getPaymentIt

//--------getInvoice-------------
// Iterator for list of invoices
// for specified client
//-------------------------------
    public Iterator getInvoice(int id){
		return clients.findClient(id).getInvoices();
	}//end getPaymentIt



//~~~~~~~~PRODUCT STUFF~~~~~~~~~~~~~~~
    
//----------addProduct---------------------
// Construct New product Object and add
//  it to product List
//-----------------------------------------
    public void addProduct(String n, double sp, int st){
        products.addProduct(n,sp, st);  }

//---------getProducts--------------
// Iterator to List of products in
// warehouse System
//----------------------------------
    public Iterator getProducts(){
        return products.getProduct();  }

//-----------productList--------------------
// Displays List of products in warehouse
//------------------------------------------
    public void productlist(){
        products.displayList(); }
    
//----------findProduct----------------------------
// Finds product by ID. Returns null if not found
//------------------------------------------------
    public Product findProduct(int id){
        return products.findProduct(id);
    }//end findProduct

//-------RemoveProduct-------------------
  public void removeProducts(int PID){
        products.removeProduct(PID);
    }//end removeProduct

//---------VerifyProduct---------------
// True if product is in productList
// False if not found
//------------------------------------
    public boolean verifyProduct(int id){
        return !(products.findProduct(id) == null);
    }//end verifyProduct
    
//--------getStock------------------------
// Uses product ID to get amount in stock
//----------------------------------------
    public int getStock(int productId){
        return products.findProduct(productId).getStock();
    }//end getStock
    
//-------getWaitList------------------------
// Iterator for waitlist using product id
//------------------------------------------
    public Iterator getWaitList(int productId){
        return products.findProduct(productId).getWaitList();
    }//end getProductWaitList


    
//~~~~~~~~~ORDER STUFF~~~~~~~~~~~~~~~~
    
//-------------placeOrder------------------------
// Uses clientId to add order to order list and
// clears clients wishlist
//----------------------------------------------
    public void placeOrder(int clientId){
		Order order = new Order(clients.findClient(clientId) );
		orders.addOrder(order);
	}//end placeOrder

    //-----------addShippedItem----------------------
    public Iterator addShippedItem(int productId, int quantity){
        Product p = products.findProduct(productId);
        p.addStock(quantity);
        return p.getWaitList();
    }
    
    //--------finishWaitlist--------------------
    public void finishWaitList(int productId, WaitListProduct item){
        waitListProducts.add(products.findProduct(productId));
        waitListRemoval.add(item);
    }//end finishWaitlist
    
    
    public void doneFulfilling() {
        while(waitListRemoval.size() > 0) {
            //waitListRemoval.get(0).finishWaitList(waitListRemoval.get(0));
            waitListProducts.remove(0);
            waitListRemoval.remove(0);
        }
    }

    
//----------Retrieve Data---------------------------------
    public static Warehouse retrieveData(String filename){
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            
            in.readObject();
            ClientIdServer.retrieve(in);
            OrderIdServer.retrieve(in);
            ProductIdServer.retrieve(in);
            return warehouse;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return null;
        }
    }//end retrieveData
    
//-----------Save Data----------------------------------
    public static boolean saveData(String filename){
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(warehouse);
            out.writeObject(ClientIdServer.instance());
            out.writeObject(OrderIdServer.instance());
            out.writeObject(ProductIdServer.instance());
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            return false;
        }//end try-cach block
        
    }//end saveData
    
    
//-----------Write Object----------------------
// Write Serialization
//---------------------------------------------
    private void writeObject(java.io.ObjectOutputStream output){
        try{
            output.defaultWriteObject();
            output.writeObject(warehouse);
        } catch(IOException ioe){
            ioe.printStackTrace();
        }//end try-catch block
    }//end writeObject
    
//-------------Read Object-------------------
// Read Serialization
//-------------------------------------------
    private void readObject(java.io.ObjectInputStream input){
        try{
            if(warehouse != null){
                return;
            } else{
                input.defaultReadObject();
                if(warehouse == null){
                    warehouse = (Warehouse) input.readObject();
                } else {
                    input.readObject();
                }//end if-else
            }//end if-else
        } catch (IOException ioe){
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }//end try-catch block
        
    }//end readObject
    
    
    
//-----------toString()--------------------------
    public String toString(){
        return "Clients: \n" + clients.toString() +
        "\nOrders: \n" + orders.toString() +
        "\nProducts: \n" + products.toString();
    }//end toString
    
    
    

	
}//end Warehouse class
