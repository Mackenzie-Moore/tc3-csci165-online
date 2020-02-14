//Mackenzie Moore
//CSCI 165 Module 2 Assignment: Food Truck

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class FoodTruck
{
	public static void main(String [] args)
	{
    	double total = 0;
    	double subtotal = 0;

    	String[] foodtruckmenu = new String[3];
    	double[] foodtruckprices = new double[3];
    	int[] foodtruckamount = new int [3];
		double[] foodtrucktotal = new double [3];
		
		try
		{
			File menu = new File("menu.txt");
      		File prices = new File ("prices.txt");

      		Scanner scanMenu = new Scanner(menu);
      		Scanner scanPrices = new Scanner(prices);

      		byte index = 0;

      		while(scanMenu.hasNextLine())
      		{
      			String items = scanMenu.nextLine();
        		double price = scanPrices.nextDouble();
           		
           		foodtruckmenu[index] = items;
        		foodtruckprices[index] = price;
        		index++;
        	}
        }//End of try

        catch (IOException e) //This give out an error message if the program was unable to read the two files
        {
        	System.out.println("An error has occured trying to write to file");
      	}//End of Catch

  	System.out.println("Welcome to the Synapse Food Truck");
  	Scanner input = new Scanner(System.in);
  	System.out.print("\nPlease enter your name here: ");
  	String name = input.nextLine();
  	System.out.println("\nPlease enter how much food you want");
  	System.out.println("===============================");

  	for (int i = 0; i < 3; i++)
  	{
  		System.out.printf("\n%s - $%.2f: ", foodtruckmenu[i], foodtruckprices[i]);
  		foodtruckamount[i] = input.nextInt();
  	}//End of for loop

  	for (int i = 0; i < 3; i++) //To determine the total price of food
  	{
  		foodtrucktotal[i] = (foodtruckprices[i] * foodtruckamount[i]);
  	}//End of for loop
  
  	for (int i = 0; i < 3; i++) //To determine the subtotal of the bill 
  	{
    	subtotal += (foodtruckprices[i] * foodtruckamount[i]);
  	}//End of for loop

  	double salesTax = subtotal * 0.0625;
 	total = subtotal + salesTax;

  	String[] bill = bill(name);

  	String billLine = (bill[0]);
  	String number = (bill[1]);
  	String divider = "\n===============================================================\n";
  	String desc =  String.format("\n%-30s %10s %10s %10s", "Item", "Amount", "Price", "Total");
  	String itemOne =   String.format("\n%-30s %10d %10s %10s", foodtruckmenu[0], foodtruckamount[0], currency(foodtruckprices[0]), currency(foodtrucktotal[0]));
  	String itemTwo =   String.format("\n%-30s %10d %10s %10s", foodtruckmenu[1], foodtruckamount[1], currency(foodtruckprices[1]), currency(foodtrucktotal[1]));
  	String itemThree = String.format("\n%-30s %10d %10s %10s", foodtruckmenu[2], foodtruckamount[2], currency(foodtruckprices[2]), currency(foodtrucktotal[2]));
  	String sub =   String.format("\nSubtotal: %53s", currency(subtotal));
  	String taxL =   String.format("\nSales Tax: %53s", currency(salesTax));
  	String totalL = String.format("\nTotal: %56s %n", currency(total));


  	String output = (billLine + "\n" + desc + divider + itemOne + itemTwo + itemThree + divider + sub + taxL + totalL);
	try //Program will attempt to write the bill text file
  	{
  		FileWriter fileWriter = new FileWriter(bill[1] + ".txt");
    	fileWriter.write(output);
    	fileWriter.close();
  	}

  	catch (IOException e) //This will give out an error message if the program could not write the file
  	{
  		System.out.println("There was a problem writing to the file");
  	}

  	System.out.println(output);
}
  private static String[] bill(String name)//This will create the bill number, the date, and the time
  {
  	int space = name.indexOf(" ");
    String initialF = name.substring(0,2);
    String initialL = name.substring(space + 1, space + 3);
    String first = initialF.toUpperCase();
    String last = initialL.toUpperCase();
    char firstChar = name.charAt(0);
    char lastChar = name.charAt(space +1 );
    int intOne = (int) firstChar;
    int intTwo = (int) lastChar;
    int length = name.length();

    LocalDateTime current = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    String formattedDateTime = current.format(formatter);
    String date = formattedDateTime.substring(0,10);
    String time = formattedDateTime.substring(11,19);
    String mounth = formattedDateTime.substring(0,2);
    String day = formattedDateTime.substring(3,5);
    String hour = formattedDateTime.substring(11,13);
    String min = formattedDateTime.substring(14,16);
    String number =  Integer.toString((intOne + intTwo) * length);

    String billNumber = first + last + number + mounth + day + hour + min;
    String lineOne = String.format("\nBill Number: %46s", billNumber);
    String lineTwo = String.format("\nDate:        %46s", date);
    String lineThree = String.format("\nTime:      %46s", time);
    String theFinal = lineOne + lineTwo + lineThree;

    return new String[] {theFinal, billNumber};
  }

  private static String currency(double value)//This will format the prices to actual currency
  {
  	DecimalFormat df = new DecimalFormat("#,###,###.00");
    String cost = String.valueOf(df.format(value));
    String output = "$" + cost;

    return output;
  }

}
