import java.util.*;

/**
	* <p> BlueSpace handles the calling of the blue card from the card deck, whenever a player lands on a space that was created as a BlueSpace. </p>
	*/
public class BlueSpace extends Space 
{
	/** creates a blue space. 
		* @param number is the number assigned to the space (1-60)
		*/
	public BlueSpace (int number)
	{
		super (number);
	}

	/** gets a blue card from the deck and exectues the function of the given card.
		*
		* @param p is the ArrayList of players
		* @param index is the index of the player that has the current turn
		* @return the BlueCard that the player got from the deck
		*/
	public BlueCard determineAction (ArrayList<Player> p, int index)
	{
		BlueCard card = CardDeck.getBlueCard ();
		
		return card;
	}

	@Override
	/** returns a formatted string that indicates the player landed on a BlueSpace.
		* @return "You have landed on a Blue Space."
		*/
	public String toString ()
	{
		return "You have landed on a Blue Space.";
	}
}