

public class Customer
{
    // instance variables of customers
    private String email;
    private Product lastProduct;
    private int numProduct;
    private double balance;
    
    public Customer()
    {
         this.email = "";
         this.lastProduct = null;
         this.numProduct = 0;
    }
    
    public Customer(String e,Product p,int a)
    {
         this.email = e;
         this.lastProduct = p;
         this.numProduct = a;
    }
    
    //getter methods
    public String getEmail()
    {
        return this.email;
    }
    
    public Product getLastProd()
    {
        return this.lastProduct;
    }
    
    public int getNumProduct()
    {
        return this.numProduct;
    }
    
    public double getBalance()
    {
        return this.balance;
    }
    
    //setter methods
    public void setEmail(String e)
    {
        this.email = e;
    }
    
     public void setLastProd(Product p)
    {
        this.lastProduct = p;
    }
    
     public void setNumProd(int n)
    {
        this.numProduct = n;
    }
    
    public void setBalance(double b)
    {
        this.balance = b;
    }
    
    //Method to manipulate details of the product to display for the staff menu option 3
    public String prodDetails()
    {
        String sentence = "No previous purchasement.";
        if(this.lastProduct instanceof Food)
        {
            Food fProd = (Food) this.lastProduct;
            sentence = lastProduct.getBrand() + ", "  + fProd.getSize() + "g " + fProd.getType()
            + " " + fProd.getFlavour() + " pet food. "; 
        }else if (this.lastProduct instanceof Accessories)
        {
            Accessories aProd = (Accessories) this.lastProduct;
            sentence = lastProduct.getBrand() + ", "  + aProd.getSize() + ", " + aProd.getMateriel()
            + " " + aProd.getType() + " pet accessory. "; 
        }
        return sentence;
    }
    
    //toString method
    public String toString()
    {
        return  "\n\tCustomer details..." + "\n" +
        "Customer email: " + this.email + "\n" +
        "Last bought product: " + prodDetails() + "\n" +
        "Amount bought: " + this.numProduct;
    }
}