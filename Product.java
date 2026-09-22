import java.io.Serializable;

public abstract class Product implements Serializable
{
    //Declare the common instance variables (Id, Price, Brand, Stock level) 
    private String id;
    private double price;
    private String brand;
    private int stock;

    public Product()
    {
        this.id = "";
        this.price = 0;
        this.brand = "";
        this.stock = 0; 
    }

    public Product(String id, double p, String b, int s)
    {
        this.id = id;
        this.price = p;
        this.brand = b;
        this.stock = s; 
    }

    //getter methods
    public String getID()
    {
        return this.id;
    }

    public double getPrice()
    {
        return this.price;
    }

    public String getBrand()
    {
        return this.brand;
    }

    public int getStock()
    {
        return this.stock;
    }

    //setter methods
    public void setID(String id)
    {
        this.id = id;
    }

    public void setPrice(double p)
    {
        this.price = p;
    }

    public void setBrand(String b)
    {
        this.brand = b;
    }

    public void setStock(int s)
    {
        this.stock = s;
    }
    
    //New delivery and updating stock
    public void updateStock(int amount)
    {
        this.stock = this.stock + amount;
    }

    //Updating stock level after a product is bought
    public void buyProduct(int amount)
    {
        this.stock = this.stock - amount;
    }

    //polymorphic method
    public abstract boolean isStocked();
    
    //toString method
    public String toString()
    {
        return "\t\nProduct details..." + "\n" +
        "Product id: " + this.id + "\n" +
        "Price: " + this.price + "\n" + 
        "Brand: " + this.brand + "\n" + 
        "Stock amount: " + this.stock + "\n";
    }
}
