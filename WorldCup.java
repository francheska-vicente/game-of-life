import java.util.*;

/**
	* <p>WorldCup handles the attributes and methods needed to do the action for a WorldCup. </p>
	*/
public class WorldCup extends BlueCard
{
	/** creates the WorldCup object. It also passes on to the superclass BlueCard the career match for this object.
		*/
	public WorldCup ()
	{
		super ("Athlete");
	}

	/** returns the String that indicates that the player who got the card matches the career indicated in the card.
		* 
		* @return "You got first place! Get $15000."
		*/ 
	public String matchText ()
	{
		return "You got first place! Get $15000.";
	}

	/** returns the String that indicates that the player's career is not matched with the career indicated in the card.
		*
		* @return "You bought tickets to watch."
		*/
	public String notMatchText ()
	{
		return "You bought tickets to watch.";
	}
  
	/** returns a formatted String that indicates that the player paid  a certain amt of money
		*
		* @return "You paid $5000 for each player "
		*/ 
  public String payText ()
	{
    return "You paid $5000 for each player ";
	}

	/** determines if the player needs to pay the bank or a player, or if a player will receive cash from the bank.
		*
		* @param p is the ArrayList of players
		* @param index is the index of the player that is currently in turn
		* @return the index of the player that has the career match; -1 if there is no career match
		*/
	public int determineAction (ArrayList<Player> p, int index)
	{
		int match = super.findCareerMatch (p);
    
		if (match == index)
      p.get(index).addCash (COLLECT);
    else
    {
			int amt = p.size() * 5000;

			p.get (index).addCash (amt * -1);
      
			if (match != -1)
        p.get (match).addCash (amt);
		}

		return match;
	}

	@Override
	/** returns a String that indicates that the world cup is happening
		*
		* @return "The World Cup is now happening!"
		*/
	public String toString ()
	{
		return "The World Cup is now happening!";
	}
}