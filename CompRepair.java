import java.util.*;

/**
	* <p>CompRepair handles the attributes and methods needed to do the action for computer repair. It generates the random number that would determine how much the player needs to pay or how much the player would receive.</p>
	*/
public class CompRepair extends BlueCard
{
	protected int number;

	/** creates the CompRepair object. It also passes on to the superclass BlueCard the career match for this object.
		*/
	public CompRepair ()
	{
		super ("Computer Consultant");
	}

	/** returns the number generated when the gets this card.
		*
		* @return random generated number 
		*/
	public int getNumber ()
	{
		return number;
	}

	/** returns the String that indicates that the player who got the card matches the career indicated in the card.
		* 
		* @return "You manage to fix the computer! Get $15000."
		*/
	public String matchText ()
	{
		return "You manage to fix the computer! Get $15000.";
	}

	/** returns the String that indicates that the player's career is not matched with the career indicated in the card.
		*
		* @return "You paid someone else to fix the computer."
		*/
	public String notMatchText ()
	{
		return "You paid someone else to fix the computer.";
	}
  
	/** returns a formatted String that indicates if the player rolled an even or an odd number, what number the played rolled, and how much the player will pay/receive.
		*
		* @return "You rolled an even/odd number (number). Paid amount"
		*/
  public String payText ()
	{
    if (number % 2 == 1)
      return "You rolled an odd number (" + Integer.toString(number) + "). Paid $10000 ";
    else
      return "You rolled an even number (" + Integer.toString(number) + "). Paid $5000 ";
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
			number = Game.randomNumberGenerator(1, 10);
			int amt = 10000;
			
			if (number % 2 == 0)
      	amt = 5000;

			p.get (index).addCash (amt * -1);
      
			// if there is a computer consultant in the game, the cash gets add to him
			if (match != -1)
        p.get (match).addCash (amt);
		}

		return match;
	}

	@Override
	/** returns a String that indicates that the computer broke down
		*
		* @return "Oh no, your computer broke down!"
		*/
	public String toString ()
	{
		return "Oh no, your computer broke down!";
	}
}