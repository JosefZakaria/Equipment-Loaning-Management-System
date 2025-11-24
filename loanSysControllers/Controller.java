package loanSysControllers;

import loanSysView.*;
import loanSysModel.managers.ProductManager;
import loanSysModel.managers.MemberManager;
import loanSysModel.managers.LoanItemManager;
import loanSysModel.managers.LoanSystemManager;


import java.time.format.DateTimeFormatter;

public class Controller {
    MainFrame view;
    private ProductManager productManager;
    private MemberManager memberManager;
    private LoanItemManager loanItemManager;
    private LoanSystemManager loanSystem;

    //LoanSysManager loanSystem;  //model

    //constructor, create view and model
    public Controller() {
        //loanSystem = new LoanSysManager(this);  //model

            productManager = new ProductManager();
            memberManager = new MemberManager();
            loanItemManager = new LoanItemManager();

            productManager.addTestProducts();
            memberManager.addTestMembers();

            loanSystem = new LoanSystemManager(productManager, memberManager, loanItemManager, this);
            view = new MainFrame(this);
    }


    //This method starts LoanSystem manager where the threads
    //are created and started to perform their tasks.
    //TO DO: Write code for the  case Start to start the loan system simulation
    //       Write code for the case stop to stop the threads
    public void buttonPressed(ButtonType button) {

        switch (button) {
            case Start:
                try {
                    view.updateEventLog(""); // rensar loggen
                    updateView("System started!");
                    loanSystem.startAll();   // starta trådarna
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case Stop:
                loanSystem.stopAll();       // stoppa trådarna
                updateAllItems();          // uppdatera GUI:t
                updateView("System stopped.");
                break;
        }
    }


    //This method prepares a list containing all products on loan item list and all products
    //saved int the product list.  The lists come from the respective manager class,
    //LoanItemManager and ProductManager.  The lists are merged together and
    //the resulting string array is sent to EastPanel via a call to the view, for displaying.


public void updateAllItems() {
    String[] products = productManager.getProductInfoStrings();
    String[] loans = loanItemManager.getLoanedItemInfoStrings();

    // Slå ihop båda listorna
    String[] combined = new String[products.length + loans.length];
    System.arraycopy(products, 0, combined, 0, products.length);
    System.arraycopy(loans, 0, combined, products.length, loans.length);

    view.updateItemsList(combined, true);

    //        boolean stop = false;
    //        String [] test = new String[10];
    //        for (int i=0; i < 10; i++)
    //            test[i] = "Item" + i;
    //
    //        view.updateItemsList(test, true);
}

    //Returns the current date and time to be displayed with updating
    //product and loan item lists.
    public String getCurrentDateTime() {
        java.time.LocalDateTime localDateTime = java.time.LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }

    public void updateView(String stringInfo) {
        view.updateEventLog(stringInfo);
      }

    //This method can be  called from the Central Panel
    //when an item is highlighted in the JList by the user.
    //This method is not used in this assignment. It
    //remains for future use.
    public void productLisIndexChanged(int index) {
        //Take an action on the index.

    }


}
