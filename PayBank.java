import java.util.*;

/**
	* <p> PayBank handles all actionCards that makes a player pay the bank. This includes Buy an Item, Visit a Place, Hiking, Watch a Show, Traffic Violation </p>
	*/
public class PayBank extends ActionCard
{
	public static final int COUNT = 20;
	public static final String[] TYPES = {"Buy an Item", "Visit a Place", "Hiking", "Watch a Show", "Traffic Violation"};

	/** creates the PayBank object. The desc is passed to the constructor of ActionCard to be stored in ActionCard
		*
		* @param type number associated with the index of the TYPES 
		*/
	public PayBank (int type)
	{
		super (type);
	}

	/** returns the possible maximum number of this type of cards in the deck
		* @return the maximum number of PayBank cards in the deck
		*/
	public int getCount ()
	{
		return COUNT; 
	}

	/** deducts cash from the player
		* @param p is the ArrayList of players 
		* @param index is the index of the player that is currenty in turn
		* @param choice is the index that the current player chose to pay/to be paid by; if not needed, it is initialized to 0.
		*/
  public void determineAction (ArrayList<Player> p, int index, int choice)
  {
		p.get (index).addCash (-amt);
  }

	@Override
	/** returns the action card's type and description in the format: type
		*
		* @return type
		*/
	public String toString ()
	{
		return TYPES [desc];
  }
}