
public class Accessories extends Product
{
    // instance variables - replace the example below with your own
    private String type;
    private String materiel;
    private String size;

    public Accessories()
    {
        this.type = "";
        this.materiel = "";
        this.size = "";
    }

    public Accessories(String id, double p, String b, int s, String t, String m, String si)
    {
        super(id, p, b, s);  // inherites product id, price, brand, stock amount 
        this.type = t; //beds, collars, toys, leash
        this.materiel = m; //leather, rubber, nylon, cotton
        this.size = si; // xs, s, m, l, xl
    }

    //getter method
    public String getType()
    {
        return this.type;
    }

    public String getMateriel()
    {
        return this.materiel;
    }

    public String getSize()
    {
        return this.size;
    }

    //setter method
    public void setType(String t)
    {
        this.type = t;
    }

    public void setMateriel(String m)
    {
        this.materiel = m;
    }

    public void setSize(String s)
    {
        this.size = s;
    }

    //checks if the product is stocked 
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
        "Product type: " + this.type + "\n" +
        "Materiel: " + this.materiel + "\n" +
        "Size: " + this.size + "\n" ;
    }
}
