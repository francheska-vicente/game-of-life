import java.util.*;

/**
	* <p>Lawsuit handles the attributes and methods needed to do the action for a Lawsuit. It generates the random amt that would determine how much the player needs to pay or how much the player would receive.</p>
	*/
	
public class Lawsuit extends BlueCard
{
	protected int amt;

	/** creates the Lawsuit object. It also passes on to the superclass BlueCard the career match for this object.
		*/
	public Lawsuit ()
	{
		super ("Lawyer");
		amt = Game.randomNumberGenerator (1,10) * 10000;
	}

	/** returns the String that indicates that the player who got the card matches the career indicated in the card.
		* 
		* @return "You won the case! Get $15000."
		*/
	public String matchText ()
	{
		return "You won the case! Get $15000.";
	}

	/** returns the String that indicates that the player's career is not matched with the career indicated in the card.
		*
		* @return "You lost the case."
		*/
	public String notMatchText ()
	{
		return "You lost the case.";
	}

	/** returns a formatted String that indicates that the player paid  a certain amt of money
		*
		* @return "You paid $ amt"
		*/ 
  public String payText ()
	{
    return "You paid $" + Integer.toString(amt) + " ";
	}

	/** returns the amount that was generated
		*
		* @return amt that they player needs to pay
		*/
	public int getAmt ()
	{
		return amt;
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
			p.get (index).addCash (amt * -1);
      
			if (match != -1)
        p.get (match).addCash (amt);
		}

		return match;
	}

	@Override
	/** returns a String that indicates that someone filed a lawsuit against you.
		*
		* @return "Someone filed a lawsuit against you!"
		*/
	public String toString ()
	{
		return "Someone filed a lawsuit against you!";
	}
}