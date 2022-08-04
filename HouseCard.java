/**
	* <p> HouseCard holds the information of the houses that the player can buy. 
	* It includes the type of house, the selling price and the buying price.</p>
	*/
public class HouseCard
{
	private static final String[] HOUSES = {"Penthouse Suite", "Bungalow", "Condominium Unit", "Log Cabin", "Country Cottage", "Farm House", "Beach House", "Contemporary Mansion", "Townhouse", "Apartment"};

	private int sellingPrice;
	private int buyingPrice;
	private String type;

	/** creates a house card, and calls the method to generate a buying and selling price for the card
		*
		* @param type is the number assigned to the card to determine its type
		*/
	public HouseCard (int type)
	{
		this.type = HOUSES [type];
		sellingPrice = Game.randomNumberGenerator (38, 54) * 10000;
		buyingPrice = Game.randomNumberGenerator (19, 34) * 10000;
	}

	/** returns the type of house indicated in the house card
		*
		* @return the type of house
		*/
	public String getType ()
	{
		return type;
	}

	/** returns the selling price of the house
		*
		* @return selling price of the house
		*/
	public int getSellingPrice ()
	{
		return sellingPrice;
	}

	/** returns the buying price of the house
		*
		* @return buying price of the house
		*/
	public int getBuyingPrice ()
	{
		return buyingPrice;
	}

	@Override
	/** returns a string formatteed that holds the information in the card
		* @return a string in the format of : "House type: " + type + "\nBuying Price: " + sellingPrice + "\nSelling Price: " + buyingPrice
		*/
	public String toString ()
	{
		return "House type: " + type + "\nBuying Price: " + sellingPrice + "\nSelling Price: " + buyingPrice + "\n";
	}
}