package loanSysModel;


import loanSysModel.Member;
import loanSysModel.Product;

//Class that represents a loan item.  Each loan item has an object of the
//Product class and an object of the Member class.  The member is the
//object that loans the product. More attributes like "description,
//loan date, return date" can be added as optional features.
public class LoanItem {
    private Product product;
    private Member member;

    //Constructor
    public LoanItem(Product product, Member member) {
        this.product = product;
        this.member = member;
    }

    //Setter method for the product field
    public Product getProduct() {
        return product;
    }

    //Setter method for the member field
    public Member getMember() {
        return member;
    }

    //The toString method retuning a textual representation of the
    //object's values.
    @Override
    public String toString() {

        String memberStr = (member != null) ? "Loaned to  " + member.getName() : "";

        return String.format("%-15s %-12s", product.toString(), memberStr);
    }
}
