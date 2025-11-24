package loanSysModel;

//Stores and handles data about a product (equipment) that
//can be loaned to members of the system
public class Product {
    private String name;
    private String id;
    private String description;

    // Default constructor
    public Product() {
    }
    //constructor
    public Product(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //The toString method retuning a textual representation of the
    //object's values.
    @Override
    public String toString() {
        return String.format("%-13s ID: %4s", name, id);
    }
}
