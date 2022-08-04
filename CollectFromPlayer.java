import java.util.*;

/**
	* <p> CollectFromPlayer handles all actionCards that makes a player collect cash from the bank. This includes Tax Refunds, File Lawsuit, and It's your Birthday! </p>
	*/
public class CollectFromPlayer extends ActionCard
{
	public static final int COUNT = 5;
	public static final String [] TYPES = {"File Lawsuit", "It's your Birthday!"};

	/** creates the CollectFromPlayer object. The desc is passed to the constructor of ActionCard to be stored in ActionCard
		*
		* @param type number associated with the index of the TYPES 
		*/
	public CollectFromPlayer (int type)
	{
		super (type);
	}

	/** returns the possible maximum number of this type of cards in the deck
		* @return the maximum number of CollectFromPlayer cards in the deck
		*/
	public int getCount ()
	{
		return COUNT; 
	}

	 /** adds cash from the player and may either deduct cash from a chosen player, or deduct cash from all players 
	 	* 
		* @param p is the ArrayList of players 
		* @param index is the index of the current player
		* @param choice is the index that the current player chose to pay/to be paid by; if not needed, it is initialized to 0.
		*/
  public void determineAction (ArrayList<Player> p, int index, int choice)
  {
		// player collects cash from every player.
    if (desc == 1 && p.size () > 2)
			{
				p.get (index).addCash (amt * 2);
				int i = index;

				for (int j = 0; j < p.size() ; j++)
				{
					if (i >= p.size () - 1)
						i = 0;
					else
						i++;

					if (index != i && i != p.size ())
						p.get (i).addCash (amt * -1);
				}
		} 
		else if (p.size () != 1 && choice != 0)
    {
			// player collects cash from only one player
      p.get (index).addCash (amt);
			p.get (choice - 1).addCash (-amt);
    } 
  }

	@Override
	/** returns the action card's type 
		*
		* @return type 
		*/
	public String toString ()
	{
		return TYPES [desc];
  }
}