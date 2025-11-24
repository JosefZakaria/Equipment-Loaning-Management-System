package loanSysModel.managers;

import loanSysModel.Product;

import java.util.ArrayList;
import java.util.List;

//This class administers a list of products, adding a new object,
//removing an existing, etc.  The list contains products that are
//available for loan.  When a product is loaned by a member the
//product is removed from this list and placed in the LoanItenmList,
//and when returned, the object is added to the list again.
public class ProductManager {
    private List<Product> products = new ArrayList<>();  //you may use other types of collections

    //Products receive an Id sarting from 100 and then
    //everyting a new product is created, the id is
    //incremented.
    private int lastProductID = 100; //Optional start id

    //returns a product at a position = index in the list
    public Product get(int index)
    {
        if (checkIndex(index))
            return products.get(index);
        else
            return null;
    }
    //Adds a given product at the end of the list
    public void add(Product product)
    {
        products.add(product);
    }

    //Removes a product from a position = index from
    //the list
    public void remove(int index)
    {
        if (checkIndex(index))
         products.remove(index);
    }

    //Return the number of elements (products) in the list.
    public int size()
    {
        return products.size();
    }

    //Validet the given index so it is not out of range.
    private boolean checkIndex(int index) {
        return (index >= 0) && (index < products.size());
    }
    //Preapare and rturn a string array where each element contains
    //information about the loanObject, calling the object's
    //toString metod.  The return array can then be used to update
    //the GUI on the related view.
    public  String[] getProductInfoStrings()
    {
        if( (products == null) || (products.size() <= 0) )
            return new String []{"Products available: ", " "};

        //Create an array with a size equal to the number of items in the list.
        //one extra lines for the title, size and a blank line.
        String [] infoStrings = new String[products.size()+3]; //See (x)

        infoStrings[0] =   String.format("Number of products availabale: %s", products.size()); //(1)
        infoStrings[1] = "";  //(2)
        int j= 2;

        //Fill the list with info on each element, using the object's toString
        //method.
        for (int i=0; i < products.size(); i++)
            infoStrings[j++] = products.get(i).toString();

        infoStrings[infoStrings.length-1] = "";  //(3)
        return infoStrings;
    }

    //Fill the list with some test objects.
    public void addTestProducts() {
        for (int i = 0; i < 15; i++) {
           addNewTestProduct();
          }
    }

    //Return true if the list of items is empty, an thus no product available for loaning.
    public boolean noProductsAvailable()
    {
        return (products == null) || (products.isEmpty());
    }

    //This method is called for adding a new tests object in the list.
    public Product addNewTestProduct()
    {
        Product product = new Product(); // Assume Product constructor and methods are defined elsewhere
        product.setId(Integer.toString( lastProductID));
        product.setName("Product" + (lastProductID++));
        products.add(product);
        return product;
    }

    public void remove(Product product) {
        products.remove(product);
    }


}
