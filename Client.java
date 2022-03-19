/*****************************************************************
 Client Class
 Creates a Client Object. Each client object has a Name, id, 
 address, balance due, shopping cart, payment list,
 and invoice list. 
 *****************************************************************/


import java.util.*;
import java.lang.*;
import java.io.*;

public class Client implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int clientId;
    private double balanceDue;
    private String name;
    private String address;
    private PaymentList myPayments;
    private InvoiceList myInvoices;
    private ItemList shoppingCart;
    
    //--------Client Constructor--------------------------------
    public Client(String cName, String addr){
        balanceDue = 0.0f;
        clientId = (ClientIdServer.instance() ).getId();
        name = cName;
        this.address = addr;
        shoppingCart = new ItemList();
        myInvoices = new InvoiceList();
        myPayments = new PaymentList();

    } //end constructor
   
    //-----Acessors-----------------
    public String getName(){
        return name;   }
    
    public int getId(){
        return clientId; }
    
    public double getClientBalance(){
        return balanceDue;               }
    
    public String getClientAddress(){
        return address;                }
    
    public Iterator<Payment> getPayments(){
        return myPayments.getIterator();    }
    
    public Iterator<Invoice> getInvoices(){
        return myInvoices.getInvoice(); }
    
    
    public Iterator<OrderedProduct> getCart(){
        return shoppingCart.getIterator();  }
 
  
 //-------------Mutators---------------------
    public void setClientName(String cName){
        name = cName;          }
    
    private void setBalance(double change){
        balanceDue += change;       }
    
    public void setAddress(String addr){
        this.address = addr;        }
   
    
 //--------Add Payment------------------------
 // Add or Remove Payment for Client
 //--------------------------------------------
    public void addPayment(Payment newPayment){
        balanceDue -= newPayment.getPayment();
        myPayments.addPayment(newPayment);  }//end AddPayment

 //----------Add Invoice------------------------
 //  Add or remove Invoice for Client
 //---------------------------------------------
    public void addInvoice(Invoice newInvoice){
        balanceDue += newInvoice.getInvoiceAmount();
        myInvoices.addInvoice(newInvoice);  }   //end AddInvoice
    
 //---------------Add To Cart------------------------
 // Add item to Client cart
 //-------------------------------------------------
    public void addToCart(Product p, int quantity){
        shoppingCart.addItem(p, quantity);  }//end addToCart
    
 //----------Update Quantity----------------------
 //Updates quantity of items already in cart
 //---------------------------------------------
    public void changeItemQuantity(Product p, int q){
        if(q == 0)
            shoppingCart.removeItem(p);
        else
            shoppingCart.changeQuantity(p, q);  }//end changeQuantity
    
 //---------Clear cart----------------
 //  Removes all items from cart
 //-------------------------------------
    public void clearCart(){
        shoppingCart.clear();       }//end clearCart
   
    
 //----------ToString()-------------------
    public String toString(){
        return "\nName: " + name + "   Id: " + clientId + "\nAddress: " + address
        + "\nBalance: $" + balanceDue;
    }//end toString

}//end Client class






