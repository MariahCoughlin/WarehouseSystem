/**********************************************************************
 Userinterface class
    This is the driver program
 ***********************************************************************/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Userinterface
{
    private static Warehouse warehouse;
    private static Userinterface Userinterface;
    final static double outstandingBalance = 3000.00;
    final static String FILENAME = "WareData";
    
    final static String MAINMENU =  "	MAIN MENU	\n" +
    "______________________________________________\n" +
    "Enter 0 to quit \n" + "Enter 1 for Client menu\n" +
    "Enter 2 for Order menu \n" + "Enter 3 for Product menu\n";
    
    
    final static String CLIENT_MENU =
            "CLIENT MENU:\n" +
            "-------------------------------------------------\n" +
    
                "\tEnter A to Add a new client\n" +
                "\tEnter B to Edit Current Client information\n" +
                "\tEnter C to Display list of all Clients\n" +
                "\tEnter D to Find information on current Client\n" +
                "\tEnter E to Show a Client's balance\n" +
                "\tEnter F to Make a payment for a Client\n" +
                "\tEnter G to Display all Client's Payments\n" +
                "\tEnter H to Display all Client's Invoices\n" +
                "\tEnter I to Display all Client's Transactions\n" +
                "\tEnter J to Display list of Client's with outstanding balance\n" +
                "\tEnter Z to return to main menu OR Enter 0 to quit\n";
    
    final static String ORDER_MENU =
            "ORDER MENU:\n" +
            "-------------------------------------------------\n" +
    
                "\tEnter K to Add a product to a Client's Wish List\n" +
                "\tEnter L to Display Wish List\n" +
                "\tEnter N to Place an order from Wish List\n" +
                "\tEnter O to Modify a client's Wish List\n" +
                "\tEnter P to Clear Wish List \n"+
                "\tEnter Z to return to main menu OR Enter 0 to quit\n";
  
    final static String PRODUCT_MENU =
            "PRODUCT MENU: \n" +
            "-------------------------------------------------\n" +
    
                "\tEnter Q for Add product to Warehouse\n" +
                "\tEnter R to Display all Products in Warehouse\n" +
                "\tEnter S to Get product Information\n" +
                "\tEnter T to Get product current Stock\n" +
                "\tEnter U to Display Waitlist for Product\n" +
                "\tEnter V to Add Shipment\n" +
                "\tEnter Z to return to main menu OR Enter 0 to quit\n";

  

    
    public static void main(String[] args){
        

        Scanner input = new Scanner(System.in);
        System.out.println("\nOpen existing warehouse?(Y/N)");       //opening warehouse
        String opt = input.next();
        if(opt.equals("Y"))
            openWarehouse();
        else{
            warehouse = Warehouse.instance();
            System.out.println("New Warehouse\n");
        }//end else

        processInput();
        
        
        System.out.println("Save changes to warehouse?\n(Y/N)");        //closing warehouse
        opt = input.next();
        if(opt.equals("Y"))
            saveChanges();
        
        
    }//end run
    
    
    
    //-------processInput---------
    public static void processInput(){
        Scanner input = new Scanner(System.in);
        String inputStr = "";
        System.out.println(MAINMENU);
        while(!inputStr.equals("zero") && !inputStr.equals("0")){
            inputStr = input.next();
            
            switch(inputStr.toUpperCase()){
            
    //------MAIN MENU----------------------
                case "ZERO": case "0":
                    System.out.println("Exiting Program\n"); break;
                case "Z":
                    System.out.println(MAINMENU);
                    break;
                case "ONE":case "1":
                    System.out.println(CLIENT_MENU); break;
                case "TWO": case "2":
                    System.out.println(ORDER_MENU); break;
                case "THREE": case "3":
                    System.out.println(PRODUCT_MENU); break;

//------------END MAIN MENU-----------------------------------------------
                    
              
               
                case "A":
                    addClient(); break;
                case "B":
                    displayClient(); break;
                case "C":
                    displayAllClients(); break;
                case "D":
                    modifyClient(); break;
                case "E":
                    displayClientBalance(); break;
                case "F":
                    makePayment(); break;
                case "G":
                    displayPayments(); break;
                case "H":
                    displayInvoices(); break;
                case "I":
                    displayTransactions(); break;
                case "J":
                    displayOutstandingBalances(); break;
                case "K":
                    addToWishList(); break;
                case "L":
                    displayWishList(); break;
                case "N":
                    placeOrder(); break;
                case "O":
                    modifyWishList(); break;
                case "P":
                    clearCart(); break;
                case "Q":
                    addProduct(); break;
                case "R":
                    displayAllProducts(); break;
                case "S":
                    displayProduct(); break;
                case "T":
                    getStock(); break;
                case "U":
                    displayWaitList(); break;
                case "V":
                    addShipment(); break;
               
                default:
                    System.out.println("Input Invalid. Try Again.");
            }//end switch
            System.out.println("Please Choose what you would like to do next: ");
        }//end while
        
    }//end processInput
   
    
//--------addProduct--------------------------
// User provides information about product.
//--------------------------------------------
    public static void addProduct(){
        boolean adding = true;
        Scanner input;
        while(adding){
            input = new Scanner(System.in);
            System.out.print("Enter the name of the product: ");
            String name = input.nextLine();
            System.out.print("Enter a sale price for the product: ");
            String salePrice = input.nextLine();
            System.out.print("Enter a stock for the product: ");
            int stock = input.nextInt();
            warehouse.addProduct(name, Double.valueOf(salePrice), stock);
            System.out.println("\nProduct added successfully");
            System.out.print("Add another product? (Y/N) ");
            input = new Scanner(System.in);
            adding = (input.next().charAt(0) == 'Y');
        }//end while
    }//end addProduct
    
//---------DisplayProduct----------------------
// User enters product ID, system displays it
//---------------------------------------------
    public static void displayProduct(){
        System.out.print("Enter Product id: ");
        Scanner s = new Scanner(System.in);
        Product p = warehouse.findProduct(s.nextInt() );
        if(p == null)
            System.out.println("No Product found with given id");
        else
            System.out.println(p.toString());
    }//end displayProduct
    
//---------DisplayALL-------------------------
//Displays list of all products in warehouse
//--------------------------------------------
    public static void displayAllProducts(){
        Iterator it = warehouse.getProducts();
        while(it.hasNext() )
            System.out.println(it.next().toString());
    }//end displayAllProducts
    
//-------getStock()-------------------------
// User enters product ID, system returns
//  stock number for that product
//------------------------------------------
    public static void getStock(){
        int id;
        System.out.print("Enter an id for the product: ");
        id = (new Scanner(System.in)).nextInt();
        if(!warehouse.verifyProduct(id)){
            System.out.println("ERROR, invalid product id.");
            return;
        }//end if
        System.out.println("Current stock: " + warehouse.getStock(id));
    }//end getStock
    
//--------DisplayWaitlist-----------------
// User enters product ID, waitlist for
//   that product is displayed
//----------------------------------------
    public static void displayWaitList(){
        int productID = getProductId();
        Iterator it;
        if(!warehouse.verifyProduct(productID)){
            System.out.println("ERROR, invalid product id");
            return;
        }//end if
        it = warehouse.getWaitList(productID);
        System.out.println("Product: \n" + warehouse.findProduct(productID).toString());
        if(!it.hasNext())
            System.out.println("Product has no wait list currently");
        else{
            System.out.println("Wait list:\n"+
                               "__________________");
            while(it.hasNext())
                System.out.println(((WaitListProduct)it.next()).toString());
        }//end else
    }//end showWaitList
    

//-----------addClient------------------------
// User provides name and address for client
//--------------------------------------------
    public static void addClient(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a name for the client: ");
        String name = input.nextLine();
        System.out.print("Enter an address for the client: ");
        String address = input.nextLine();
        warehouse.addClient(name, address);
        System.out.println("\nClient added successfully\n");
    }//end addClient
    
//------------displayClient---------------------------
//user enters client ID, client information displayed
//---------------------------------------------------
    public static void displayClient(){
        System.out.print("Enter client id: ");
        Scanner s = new Scanner(System.in);
        Client c = warehouse.findClient(s.nextInt() );
        if(c == null)
            System.out.println("ERROR No client found with given id");
        else
            System.out.println(c.toString());
    }//end displayClient
    
//---------modifyClient-------------------
// Client enters new client information
// System updates information
//--------------------------------------
    public static void modifyClient(){
        System.out.print("Enter the client's id number: ");
        Scanner s = new Scanner(System.in);
        Client c = warehouse.findClient(s.nextInt());
        if(c == null)
            System.out.println("ERROR Given id does not match a client.");
        else{
            s = new Scanner(System.in);
            System.out.println("Select an element to modify.\n" +
                               "Enter 1 for name\n" +
                               "Enter 2 for address");
            switch(s.nextLine() ){
                case "1":
                    System.out.println("Enter a new name for the client:");
                    c.setClientName(s.nextLine() );
                    System.out.println("Client name updated");
                    break;
            
                case "2":
                    System.out.println("Enter a new address for the client: ");
                    c.setAddress(s.nextLine() );
                    System.out.println("Client address updated");
                    break;
                default:
                    System.out.println("ERROR Invalid option given");
                    break;
            }//end switch
        }//end else
    }//end modfiyClient
    
//-----------displayClients------------------
// Displays list of all clients in warehouse
//--------------------------------------------
    public static void displayAllClients(){
        Iterator it = warehouse.getClients();
        while(it.hasNext() )
            System.out.println(it.next().toString());
    }//end displayAllClients
    
//-----------displayBalance-------------------
//
    public static void displayClientBalance(){
        int clientId = getClientId();
        if(!warehouse.verifyClient(clientId)){
            System.out.println("ERROR invalid client id");
            return;
        }//end if
        System.out.println("Client Balance: " + warehouse.getClientBalance(clientId));
    }//end displayClientBalance
    
  
//----------makePayment------------------------
// User provides client ID and payment amount
// Payment gets made to clients account
//---------------------------------------------
    public static void makePayment(){
        //Get client id
        int clientId = getClientId();
        if(!warehouse.verifyClient(clientId)){
            System.out.println("ERROR, invalid client id.");
            return;
        }//end if
       
        System.out.print("Enter a payment amount: ");
        double amount = Double.valueOf(new Scanner(System.in).nextLine());
        if(amount <= 0.0){
            System.out.println("ERROR, payment must be a positive value. ");
            return;
        }//end if
        System.out.println("Please enter a Payment method for this transaction ");
        String description = (new Scanner(System.in).nextLine());
        warehouse.makePayment(clientId, amount, description);
        System.out.println("Payment received successfully");
    }//end makePayment
    
//-------displayPayments----------------------------
// User enters client id, displays all payments for
// that client
//---------------------------------------------------
    public static void displayPayments(){
        int clientId = getClientId();
        Iterator paymentIt;
        if(!warehouse.verifyClient(clientId)){
            System.out.println("ERROR invalid client id.");
            return;
        }//end if
        paymentIt = warehouse.getPayment(clientId);
        while(paymentIt.hasNext() )
            System.out.println( ((Payment)(paymentIt.next())).toString() );
    }//end displayPayments
    
//------displayInvoices---------------------
    public static void displayInvoices(){
        int clientId = getClientId();
        Iterator invoiceIt;
        boolean choice;
        if(!warehouse.verifyClient(clientId)){
            System.out.println("ERROR invalid client id");
            return;
        }//end if
        System.out.print("Would you like to display detailed transactions? (Y|N) ");
        choice = new Scanner(System.in).next().equals("Y");
        invoiceIt = warehouse.getInvoice(clientId);
        if(choice)
            while(invoiceIt.hasNext() )
                System.out.println(((Invoice)(invoiceIt.next())).itemListString() );
        else
            while(invoiceIt.hasNext() )
                System.out.println(((Invoice)(invoiceIt.next())).toString());
    }//end displayInvoices()
    
//-----------displayTransactions--------------------
    public static void displayTransactions(){
        int clientId = getClientId();
        Iterator paymentIt, invoiceIt;
        if(!warehouse.verifyClient(clientId)){
            System.out.println("ERROR invalid client id ");
            return;
        }//end if
        invoiceIt = warehouse.getInvoice(clientId);
        paymentIt = warehouse.getPayment(clientId);
        System.out.println("INVOICES\n" +
                           "_____________________");
        while(invoiceIt.hasNext() )
            System.out.println(((Invoice)(invoiceIt.next())).toString());
        System.out.println("\nPAYMENTS\n" +
                           "_____________________");
        while(paymentIt.hasNext() )
            System.out.println( ((Payment)(paymentIt.next())).toString() );
    }//end displayTransactions
    
  //--------displayOutstandingBalances-------------------
    public static void displayOutstandingBalances(){
        Iterator it = warehouse.getClients();
        Client currClient = null;
        boolean found = false;
        while(it.hasNext()){
            currClient = (Client)it.next();
            if(currClient.getClientBalance() >= outstandingBalance){
                System.out.println(currClient.toString());
                found = true;
            }//end if
        }//end while()
        if(!found)
            System.out.println("No clients with an outstanding balance found");
        if(currClient == null)
            System.out.println("No Outstanding Balances currently");
    }//end displayOutstandingBalances
    
  //-------addToWishList--------------
    public static void addToWishList(){
        int productid, clientid, quantity;
        clientid = getClientId();
        if(!warehouse.verifyClient(clientid)){
            System.out.println("ERROR invalid client id");
            return;
        }//end if
        
       
        productid = getProductId();
        if(!warehouse.verifyProduct(productid) ){
            System.out.println("ERROR invalid product id");
            return;
        }//end if
        
        System.out.print("Enter a quantity: ");
        Scanner s = new Scanner(System.in);
        quantity = s.nextInt();
        warehouse.addToCart(clientid, productid, quantity);
        System.out.println("Item added to cart.");
    }//end addToWishList
    
  //-----------displayWishList-------------
    public static void displayWishList(){
        System.out.print("Please enter a client id: ");
        Scanner s = new Scanner(System.in);
        int id = s.nextInt();
        Iterator it;
        if(warehouse.verifyClient(id)){
            it = warehouse.getCart(id);
            while(it.hasNext())
                System.out.println( ((OrderedProduct)it.next()).toString() );
        }//end if
        else
            System.out.println("ERROR Invalid id given");
    }//end displaywishlist
    
   //--------placeOrder---------------
    public static void placeOrder(){
        int clientId = getClientId();
        if(!warehouse.verifyClient(clientId) ){
            System.out.println("ERROR invalid client id");
            return;
        }//end if
        warehouse.placeOrder(clientId);
        System.out.println("Order placed successfully");
    }//end placeOrder
    
  //-----modifyWishList----------------
    public static void modifyWishList(){
        int clientId = getClientId();
        Iterator it;
        boolean done, continueModifying = true;
        Scanner s;
        int productId, quantity;
        OrderedProduct currProd;
        if(!warehouse.verifyClient(clientId)){
            System.out.println("ERROR, invalid client id");
            return;
        }//end if
        while(continueModifying){
            it = warehouse.getCart(clientId);
            while(it.hasNext())
                System.out.println(((OrderedProduct)it.next()).toString());
            System.out.print("Modify cart? (Y/N) ");
            s = new Scanner(System.in);
            if(s.next().charAt(0) == 'Y'){
                System.out.print("Enter the id for the product to modify: ");
                s = new Scanner(System.in); //clear buffer
                productId = s.nextInt();
                it = warehouse.getCart(clientId);
                done = false;
                while(it.hasNext() && !done){
                    currProd = (OrderedProduct)it.next();
                    if(currProd.getProduct().getProductNumber() == productId){
                        System.out.print("Enter a new quantity for this product (0 to remove it): ");
                        s = new Scanner(System.in);//clear buffer
                        quantity = s.nextInt();
                        if(quantity > -1){
                            warehouse.modifyCart(clientId, productId, quantity);
                            System.out.println("Changes made successfully");
                        } else
                            System.out.println("Invalid quantity entered; cannot be negative");
                        done = true;
                    }//end if
                }//end while
                if(!done)
                    System.out.println("Given product id not found in cart.");
            } else
                continueModifying = false;
        }//end while
    }//end modifyCart()
    
   //-------clearCart---------------------
    public static void clearCart(){
        int clientId = getClientId();
        if(!warehouse.verifyClient(clientId)){
            System.out.println("ERROR invalid client id ");
            return;
        }//end if
        warehouse.clearCart(clientId);
    }//end clearCart
    
  //-------getClientID-------------------
    public static int getClientId(){
        System.out.print("Please enter a client id: ");
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }//end getClientId()
    
 //-------getProductId------------------
    public static int getProductId(){
        System.out.print("Please enter a product id: ");
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }//end getProductId()
    
//-----------addShipment()---------------------
    public static void addShipment() {
        System.out.println("\nShipment Accepted.\n");
        int productId, quantity, quantityCount;
        boolean moreProducts = true;
        Scanner scanner;
        Iterator waitList;
        WaitListProduct currItem;
        char choice;
        while (moreProducts) {
            System.out.println("Enter received product's id (0 to quit):");
            scanner = new Scanner(System.in);
            productId = scanner.nextInt();
            if(productId == 0) return;
            
            if(!warehouse.verifyProduct(productId)){
                System.out.println("Error. Invalid product id");
                return;
            }//endif
            
            System.out.println("Enter quantity for the product: ");
            scanner = new Scanner(System.in);
            quantity = scanner.nextInt();
            waitList = warehouse.addShippedItem(productId, quantity);
            quantityCount = 0;
            
            while(waitList.hasNext()){
                currItem = (WaitListProduct)waitList.next();
                if((currItem.getQuantity() + quantityCount) <= warehouse.getStock(productId)){
                    System.out.println("Order " + currItem.getOrder().getId() + "can be fulfilled with new stock.\n" + " Current Stock: " + warehouse.getStock(productId) + " Order quantity needed: " + currItem.getQuantity() + "\nFulfill? (Y/N) ");
                    scanner = new Scanner(System.in);
                    choice = scanner.next().charAt(0);
                    if (choice == 'Y') {
                        System.out.println("Fulfilling order");
                        warehouse.finishWaitList(productId, currItem);
                        quantityCount += currItem.getQuantity();
                    }
                }//endif
            }//endwhile
            warehouse.doneFulfilling();
        }//endwhile
        
        
        
        
    }//end shipment
    
    
    
 //-------OPEN---------------------------
// Opens warehouse or creates New
//----------------------------------------
    public static void openWarehouse(){
        Warehouse w = Warehouse.retrieveData(FILENAME);
        if(w == null){
            System.out.println("\n\nWarehouse not found. Creating NEW Warehouse.");
            warehouse = Warehouse.instance();
        } else{
            System.out.println("Warehouse successfully read from file.");
            warehouse = w;
        }//end else
    }//end openWarehouse
    
 //---------SAVE-------------------------
    public static void saveChanges(){
        if(warehouse.saveData(FILENAME) )
            System.out.println("Saved successfully");
        else
            System.out.println("Save failed. Error occured");
    }//end saveChanges
    
//---------instance------------------------
    public static Userinterface instance() {
        if(Userinterface == null)
            return Userinterface = new Userinterface();
        else
            return Userinterface;
    }//end instance()
    
}
