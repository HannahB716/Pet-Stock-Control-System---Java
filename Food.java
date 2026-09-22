
public class Food extends Product
{
    //declaring unique variables of the Food product
    private String type;
    private String flavour;
    private int size;
    
    public Food()
    {
       this.type = "";
       this.flavour = "";
       this.size = 0;
    }
    
     public Food(String id, double price, String brand, int stock, String type, String flav, int size)
    {
       super(id, price, brand, stock); // inherites product id, price, brand, stock amount
       this.type = type; // treat/wet or dry food
       this.flavour = flav; //chicken, beef, tuna 
       this.size = size; //100g, 250g, 500g, 1kg
    }

    //getter methods
    public String getType()
    {
        return this.type;
    }
    
     public String getFlavour()
    {
        return this.flavour;
    }
    
     public int getSize()
    {
        return this.size;
    }
    
    //setter
    public void setType(String t)
    {
        this.type = t;
    }
    
     public void setFlav(String f)
    {
        this.flavour = f;
    }
    
     public void setSize(int s)
    {
        this.size = s;
    }
    
    //Checks is the product is stocked
    public boolean isStocked()
    {
        boolean stocked;
        if(super.getStock() >= 0)
        {
            stocked = true;
            System.out.println("\nThere are " + super.getStock() + " in stock.");
        }else 
        {
            stocked = false;
        }
        return stocked;
    }
    
    //toString 
    public String toString()
    {
        return super.toString() +
        "Type of food: " + this.type + "\n" +
        "Flavour: " + this.flavour + "\n" + 
        "Size: " + this.size + "\n" ;
    }
}
