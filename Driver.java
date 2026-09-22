import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/** Semester 2 - assignment 2 
 *  Creating a clothing brand 
 */
public class Driver
{
    ArrayList<Customer> clist;
    ArrayList<Product> plist;

    public Driver()
    {
        System.out.print("\f");
        clist = new ArrayList<Customer> ();
        plist = new ArrayList<Product> ();

        if (readFromFile()) //opening the file and updating product list

        {
            System.out.println("System is updated with product details");
        }
        else //if a error occurs and not updated
        {
            System.out.println("There are no product details in this system");
        }
        processMainMenu();
        saveToFile();
    }

    public void saveToFile() // goes through the product list and saves all the details to the binary file
    {
        ObjectOutputStream fileOut;
        try{
            fileOut = new ObjectOutputStream(new FileOutputStream("product.dat"));
            for (Product s : plist)
            {
                fileOut.writeObject(s);
            }
            fileOut.close();
            System.out.println("All product records have been saved");
        }
        catch (IOException e)
        {
            System.out.println("IO Error : " + e.getMessage());
        }
    }//end saveToFile

    public boolean readFromFile()//opens the binary file and updates the product list with the details
    {
        int index=0;
        ObjectInputStream fileIn;
        Product p;

        try{
            fileIn = new ObjectInputStream(new FileInputStream("product.dat"));
            System.out.println("Opened file successfully");
            p = (Product) fileIn.readObject();
            index = 1;
            while (p != null)
            {
                plist.add(p);
                p = (Product) fileIn.readObject();
                index++;
            }
            fileIn.close();
            return true;
        }
        catch (IOException e)
        {
            if (index > 0) 
            {
                return true; 
            }
            else 
            { System.out.println("Data file does not exist\n");
                return false;
            }
        }

        catch (ClassNotFoundException e)
        {
            System.out.println("Class Error : " + e.getMessage());
            return false;
        }
    }//end saveToFile

    //menus to display
    public void mainMenu() 
    {

        System.out.println("\n\n\tPetStop - Stock Control System" + "\n----------------------------------------------" +
            "\n1. Customer menu" + "\n2. Staff menu " + "\n3. Exit program" );

    }

    public void customerMenu()
    {
        System.out.println("\n\tCustomer Menu" + "\n-----------------------------"  + 
            "\n1. Display foods" + "\n2. Display accessories " + "\n3. Buy foods" + "\n4. Buy accessories" 
            + "\n5. Exit menu");

    }

    public void staffMenu()
    {

        System.out.println("\n\tStaff Menu" + "\n--------------------------"  + 
            "\n1. Add a new product" + "\n2. Update stock level " + "\n3. Display customer list and their last ordered product"
            + "\n4. Staff noticiations " + "\n5. Exit menu");

    }

    //controlling all menu and main menu options
    public void processMainMenu()
    {
        Scanner scan = new Scanner(System.in);
        int num;
        do {
            mainMenu();
            System.out.println("\nEnter option..");
            num = scan.nextInt();
            if( num == 1) 
            {
                processCustomerMenu();
                System.out.print("\f");
            }
            else if(num == 2)
            {
                if( passCheck() == true) //if staff password is correct proceed to staff menu
                {
                    processStaffMenu();
                }// returns to main menu if not correct
            }else if (num == 3)//exiting program
            {
                System.out.println("Goodbye..");
            }else 
            {
                System.out.println("Invalid option - try again");
            }
        }  while (num != 3); // do keep users trapped
    }

    public void processCustomerMenu()
    {
        Scanner scan = new Scanner(System.in);
        int option;
        boolean found = false;
        Customer aCustomer = null;

        System.out.println("\n\nEnter your email: ");
        String email = scan.nextLine();
        for(Customer c: clist) //checks if customer exists by using their email 
        {
            if(c.getEmail().equalsIgnoreCase(email)) //if the customer is found customer ref variable is initialized 
            {
                aCustomer = c;
                System.out.print("\f");
                System.out.println("\n\t\tWelcome back " + aCustomer.getEmail());
                found = true;
            }
        }
        if(!found) //if customer is not found, a new account is created
        {
            System.out.print("\f");
            System.out.println("\n\t\tAccount created, Welcome to Petstop. ");
            aCustomer = new Customer(email, null, 0);
            clist.add(aCustomer); //add to array list 
        }
        do 
        {
            customerMenu();
            System.out.println("\nEnter option...");
            option = scan.nextInt();

            if(option == 1)
            {
                System.out.println("\n\t\tDisplaying food products..");
                displayFoodProduct();
            }else if(option == 2)
            {
                System.out.println("\n\t\tDisplaying accessory products..");
                displayAccessProduct();
            }else if(option == 3)
            {
                System.out.println("\n\t\tPurchasing a food product");
                buyF(aCustomer);
                System.out.println("\n\tTransaction successful, Food product purchased.");
            }else if(option == 4)
            {
                System.out.println("\n\t\tPurchasing an accessory product");
                buyA(aCustomer);
                System.out.println("\n\tTransaction successful, Accessory product purchased.");
            }else
            {
                System.out.println("Invalid option - try again");
            }
        }while(option != 5); //keeps user trapped until option is chosen
    }

    public void displayFoodProduct() //displaying all food products
    {
        for(Product s: plist)
        {
            if( s instanceof Food)
            {
                Product ref = s;
                System.out.println(ref.toString());
            }else if( s == null) //if it is empty, display error message
            {
                System.out.println("No items in stock");
            }
        }
    }

    public void displayAccessProduct() //displays all the accessory products
    {
        for(Product s: plist)
        {
            if( s instanceof Accessories)
            {
                Product ref = s;
                System.out.println(ref.toString());
            }else if(s == null)
            {
                System.out.println("No items in stock");
            }
        }
    }

    public void buyF(Customer aCustomer) 
    {
        Scanner scan = new Scanner(System.in);
        String id;
        int amount;
        double total;
        boolean valid = false;
        boolean enough = false;
        do
        {
            System.out.println("Enter the food product's id: "); 
            id = scan.nextLine(); 
            for(Product s: plist) //searching the product list 
            {
                if(s instanceof Food && s.getID().equalsIgnoreCase(id)) //if food id match and it is an Food product
                {
                    Food aProd = (Food)s; //assigning to the correct Food product 
                    if(aProd.isStocked() == true) // if the product is stocked proceed
                    {
                        do
                        {
                            System.out.println("Enter the amount you wish to buy: ");
                            amount = scan.nextInt();

                            if(amount <= aProd.getStock()) // if the amount is less or equal to the stock level 
                            {
                                aCustomer.setLastProd(aProd); //updating customers last bought product
                                aCustomer.setNumProd(amount); //updating customers last bought product's amount 
                                aProd.buyProduct(amount); //updating product stock level 
                                valid = true;
                                enough = true;
                                total = aProd.getPrice() * amount; // calculating the total amount the customer must pay 
                                System.out.println("Total price: " + total);

                            }else if(amount > aProd.getStock()) //if stock level is less then what they want to buy then error
                            {
                                System.out.println("\ninsufficient amount of product in stock.");
                            }
                        }while(!enough); // do this while it while a right amount is input 
                    }
                }
            }
            if(valid == false) //if product is not found by id or is an instance of another product error message 
            {
                System.out.println("\nError - please check if product id is correct and is a food product id" );
            }
        }while(!valid);
    }

    public void buyA(Customer aCustomer)
    {
        Scanner scan = new Scanner(System.in);
        String id;
        int amount;
        double total;
        boolean valid = false;
        boolean enough = false;
        do
        {
            System.out.println("Enter the accessory product's id: ");
            id = scan.nextLine(); 
            for(Product s: plist)
            {
                if(s instanceof Accessories && s.getID().equalsIgnoreCase(id)) //searching accessory products with the same product id
                {
                    Accessories aProd = (Accessories)s;  //assigning reference variable of product to the reference variable of accessory 
                    if(aProd.isStocked() == true) //checking if it is stock 
                    { 
                        do
                        {
                            System.out.println("Enter the amount you wish to buy: "); 
                            amount = scan.nextInt();

                            if(amount <= aProd.getStock()) // if the amount is less or equal to the stock level 
                            {
                                aCustomer.setLastProd(aProd);//updating customers last bought product
                                aCustomer.setNumProd(amount); //updating customers last bought product's amount 
                                aProd.buyProduct(amount);  //updating product stock level
                                valid = true;
                                enough = true;
                                total = aProd.getPrice() * amount;  // calculating the total amount the customer must pay 
                                System.out.println("Total price: " + total);
                            }else if(amount > aProd.getStock())
                            {
                                System.out.println("\ninsufficient amount of product in stock.");
                            }
                        }while(!enough); //if stock level is less then what they want to buy then error
                    }
                }
            }
            if(valid == false)  //if product is not found by id or is an instance of another product error message
            {
                System.out.println("\nError - please check if product id is correct and is a accessories product id" );
            }
        }while(!valid);
    }

    public boolean passCheck() // checks if password is correct 
    {
        Scanner scan = new Scanner(System.in);
        boolean passed = false;
        int  count=0;
        String staffPassword = "Staffpass@345";  // hardwarided password

        do
        {
            System.out.println("Enter staff password: "); 
            String password = scan.nextLine();

            if(password.equals(staffPassword)) 
            {
                passed = true;
                System.out.print("\f");
                System.out.println("Login successful");
            }else
            {
                System.out.println("\nIncorrect password - try again");
                count ++; // every incorrect password count is added by one
            }
        }while(!passed && count < 3); //user trapped for 3 attempts 
        if(!passed) //if incorrect and 3 attempts are used up returns back to main menu 
        {
            System.out.println("3/3 attempts, returning to main menu ");
        }
        return passed;
    }

    public void processStaffMenu()
    {
        Scanner scan = new Scanner(System.in);
        int option, num;

        do
        {
            staffMenu();
            System.out.println("\nEnter option...");
            option = scan.nextInt();
            if(option == 1)//option 1 adding a product 
            {
                do
                {
                    System.out.println("\n1. Add a food product" + "\n2. Add accessory product" + 
                        "\n3. Return to staff menu");
                    num = scan.nextInt();
                    if(num < 1 || num > 3)
                    {
                        System.out.println("Error-invalid option, try again");
                    }else 
                    {
                        addProduct(num);
                    }
                }while(num != 3);
            }else if(option == 2) // updating the stock of existing products
            {
                do
                {
                    System.out.println("\n1. Update food stock level" + "\n2. Update accessory level" 
                        + "\n3. Return to staff menu");
                    num = scan.nextInt();
                    if(num < 1 || num > 3)
                    {
                        System.out.println("Error-invalid option, try again");
                    }else 
                    {
                        updateStock(num);
                    }
                }while ( num !=3);
            }else if(option == 3) // displaying customer list and their last bought product 
            {
                System.out.println("\n\tDisplaying customer list and last bought products");
                for(Customer c: clist)
                {
                    //Customer found = c;
                    System.out.println(c.toString());
                }
                System.out.print("\n\n");

            }else if(option == 4)
            {
                staffNotify(); // my own method
            }
            else
            {
                System.out.println("Invalid option - try again"); //error message 
            }
        }while(option != 5);
    }

    public void addProduct(int num)
    {
        Scanner scan = new Scanner(System.in);
        if(num == 1) //adding food product
        {
            String id, brand, type, flav;
            int stock, weight;
            double price;

            System.out.println("\nEnter the product id: ");
            id = scan.nextLine();
            System.out.println("Enter the product price: ");
            price = scan.nextDouble();
            System.out.println("Enter the product brand: ");
            brand = scan.nextLine();
            scan.nextLine();
            System.out.println("Enter the product stock: ");
            stock = scan.nextInt();
            System.out.println("Enter the product type(dry/wet/treat): ");
            type = scan.nextLine();
            scan.nextLine();
            System.out.println("Enter the product flavour(chicken/beef/tuna): ");
            flav = scan.nextLine();
            System.out.println("Enter the product weight(g): ");
            weight = scan.nextInt();

            Food f = new Food(id, price, brand, stock, type, flav, weight);
            plist.add(f);
            System.out.println("\nProduct has been added");
        }else if( num == 2) //adding accessory product
        {
            String id, brand, type, mat, size;
            int stock;
            double price;

            System.out.println("Enter the product id: ");
            id = scan.nextLine();
            System.out.println("Enter the product price: ");
            price = scan.nextDouble();
            System.out.println("Enter the product brand: ");
            brand = scan.nextLine();
            scan.nextLine();
            System.out.println("Enter the product stock: ");
            stock = scan.nextInt();
            System.out.println("Enter the product type(clothes/collar/beds): ");
            type = scan.nextLine();
            scan.nextLine();
            System.out.println("Enter the product materiel(leather/nylon/foam): ");
            mat = scan.nextLine();
            System.out.println("Enter the product size: ");
            size = scan.nextLine();

            Accessories a = new Accessories(id, price, brand, stock, type, mat, size);
            plist.add(a);
            System.out.println("\nProduct has been added");
        }
    }

    public void updateStock(int num) //updating stock 
    {
        Scanner scan = new Scanner(System.in);
        boolean valid = false;

        if(num == 1) //updating food product
        {
            do
            {
                System.out.println("\nEnter the product id: ");
                String id = scan.nextLine(); 
                for(Product s: plist)
                {
                    if(s instanceof Food && s.getID().equalsIgnoreCase(id))
                    {
                        valid = true;
                        Food fProd = (Food) s;
                        System.out.println("Enter the amount of delivery: ");
                        int amount = scan.nextInt();

                        fProd.updateStock(amount);
                        System.out.println("New stock level: " + fProd.getStock()); //displaying new updated amount 
                    }
                }
                if(valid == false)
                {
                    System.out.println("\nError - please check if product id is correct and is a food product id" );
                }
            }while(!valid);
        }else if(num == 2) //updating accessory product
        {
            do
            {
                System.out.println("\nEnter the product id: ");
                String id = scan.nextLine(); 
                for(Product s: plist)
                {
                    if(s instanceof Accessories && s.getID().equalsIgnoreCase(id))  //searches for accessory products with the same id
                    {
                        valid = true;
                        Accessories aProd = (Accessories)s;
                        System.out.println("Enter the amount of delivery: ");
                        int amount = scan.nextInt();

                        aProd.updateStock(amount);  
                        System.out.println("New stock level: " + aProd.getStock()); //displaying the new updated amount 
                    }
                }
                if(valid == false)
                {
                    System.out.println("\nError - please check if product id is correct and is a food product id" );
                }
            }while(!valid);
        }
    }

    public boolean isStocked() // polymorphic method to check if product is stocked
    {
        boolean stocked = false;
        for(Product s: plist)
        {
            if(s.isStocked() == true)
            {
                stocked = true;
            }
        }
        return stocked;
    }

    public void staffNotify() // my own method 
    {
        System.out.println("\nLow stock products ... \n");

        for(Product s: plist) //searches the product list
        {
            if(s.getStock() < 5) //if stock level is less then 5 
            {
                if(s instanceof Food)  // if it is a food product display food product (id), brand and stock level
                {
                    Food aFood = (Food) s;
                    System.out.println("Food product " + aFood.getID() + ": " + aFood.getStock() + " left.");
                }else if(s instanceof Accessories) // if it is a accessory product display accessory product (id), brand and stock level
                { 
                    Accessories aAccess = (Accessories) s;
                    System.out.println("Accessory product "+ s.getID() + ": " + s.getStock() + " left.");
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new Driver();
    }
}
