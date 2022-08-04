import java.util.*;

/**
	* <p> CollectFromBank handles all actionCards that makes a player collect cash from the bank. This includes Tax Refunds, Sell an Item, Bonus Payday, Setup School, Win a competition, and Write a Book. </p>
	*/
public class CollectFromBank extends ActionCard
{
	public static final int COUNT = 20;
	public static final String[] TYPES = {"Tax Refund", "Sell an Item", "Bonus Payday", "Setup School", "Win a competition", "Write a Book"};

	/** creates the CollectFromBank object. The desc is passed to the constructor of ActionCard to be stored in ActionCard
		*
		* @param desc number associated with the index of the TYPES 
		*/
	public CollectFromBank (int desc)
	{
		super (desc);
	}
	
	/** returns the possible maximum number of this type of cards in the deck
		*
		* @return the maximum number of PayPlayer cards in the deck
		*/
	public int getCount ()
	{
		return COUNT; 
	}

	/** adds cash to the player
		* @param p is the ArrayList of players
		* @param index is the index of the current player in the ArrayList of players
		* @param choice is the index that the current player chose to pay/to be paid by; if not needed, it is initialized to 0.
		*/
  public void determineAction (ArrayList<Player> p, int index, int choice)
  {
    p.get (index).addCash (amt);  
  }

	@Override
	/** returns the action card's type and description in the format: type + ": " + desc
		*
		* @return type + ": " + desc
		*/
	public String toString ()
	{
		return TYPES [desc];
  }
}