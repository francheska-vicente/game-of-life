import java.util.*;

/**
	* <p> OrangeSpace handles the calling of the orange card from the card deck, whenever a player lands on a space that was created as a OrangeSpace.</p>
	*/
public class OrangeSpace extends Space 
{
	/** creates an orange space given the number. It passes the number assigned to it to its superclass Space.
		*
		* @param number is the number assigned to the space (1-60)
		*/
	public OrangeSpace (int number)
	{
		super (number);
	}

	/** gets a orange card from the deck
		*
		* @param p is the ArrayList of players
		* @param index is the index of the player that has the current turn
		* @return the ActionCard that was from the deck
		*/
	public ActionCard determineAction (ArrayList<Player> p, int index)
	{
		ActionCard card = CardDeck.getActionCard ();

		return card;
	}

	@Override
	/** returns a formatted string that says that the player landed on an OrangeSpace
		* @return "You have landed on an Orange Space."
		*/
	public String toString ()
	{
		return "You have landed on an Orange Space.";
	}
}