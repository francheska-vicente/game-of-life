import java.util.*;

/**
  * <p> ActionCard holds the information of an Action Card, along with its amt and description. It also has two abstract classes that its subclasses ca
  * </p>
  */
public abstract class ActionCard
{	
	protected int desc;
	protected int amt;

	/** does the indicated action in an action card
		* @param p is the ArrayList of 0players
		* @param index is the index of the current player
		* @param choice is the player number of the player who the cash would be added or subtracted from
		*/
	public abstract void determineAction (ArrayList<Player> p, int index, int choice);

	/** creates the ActionCard object
		*
		* @param desc index of the card's description
		*/
	public ActionCard (int desc)
	{
    this.desc = desc;
		amt = Game.randomNumberGenerator (1, 10) * 10000;
  }

	/** returns the amount that the user will need to pay/receive
		*
		* @return the amount generated for the ActionCard
		*/
	public int getAmt ()
	{
		return amt;
	}

	/** returns the number associated with the index of TYPES
		*
		* @return number that tells what is the specific type of card t is
		*/
	public int getDesc ()
	{
		return desc;
	}
}