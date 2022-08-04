import java.util.*;

/**
	* <p> PayPlayer handles all actionCards that makes a player pay a player. This includes Lawsuit, and Christmas Bonus. </p>
	*/
public class PayPlayer extends ActionCard
{
	public static final int COUNT = 5;
	public static final String[] TYPES = {"Lawsuit", "Christmas Bonus"};

	/** creates the PayPlayer object. The desc is passed to the constructor of ActionCard to be stored in ActionCard
		*
		* @param desc number associated with the index of the TYPES 
		*/
	public PayPlayer (int desc)
	{
		super (desc);
	}

	/** returns the possible maximum number of this type of cards in the deck
		* @return the maximum number of PayPlayer cards in the deck
		*/
	public int getCount ()
	{
		return COUNT; 
	}

	/** deducts cash from the player and may either add cash to a chosen player, or add cash to all players 
		* @param p is the ArrayList of players 
		* @param index is the index of the player that is currenty in turn
		* @param choice is the index that the current player chose to pay/to be paid by; if not needed, it is initialized to 0.
		*/
  public void determineAction (ArrayList<Player> p, int index, int choice)
  {
    if (desc == 1 && p.size () > 2)
		{
			p.get(index).addCash(amt * -2);
			int i = index;

			for (int j = 0; j < p.size() ; j++)
			{
				if (i >= p.size () - 1)
					i = 0;
				else
					i++;

				if (index != i && i != p.size ())
					p.get (i).addCash (amt);
			}
		}
    else if (p.size () != 1 && choice != 0)
    {
      p.get (index).addCash (-amt);
      p.get (choice - 1).addCash (amt);
    }
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