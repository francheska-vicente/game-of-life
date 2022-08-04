import java.util.*;

/**
	* <p> f1Race handles the attributes and methods needed to do the action for f1Race. </p>
	*/
public class F1Race extends BlueCard
{
	/** creates the F1Race object. It also passes on to the superclass BlueCard the career match for this object.
		*/
	public F1Race ()
	{
		super ("Racecar Driver");
	}

	/** returns a String that tells that the player's career matches the career assigned to the card.
		*
		* @return "You won the race! Get $15000."
		*/
	public String matchText ()
	{
		return "You won the race! Get $15000.";
	}

	/** returns the String that indicates that the player's career is not matched with the career indicated in the card.
		*
		* @return "You got tickets for a front row seat."
		*/
	public String notMatchText ()
	{
		return "You got tickets for a front row seat.";
	}

	/** returns the String that indicates that the player pays with 10% of his salary
		*
		* @return "You paid 10% of your salary "
		*/      
  public String payText ()
	{
    return "You paid 10% of your salary ";
	}
	
	/** determines if the player needs to pay the bank or a player, or if a player will receive cash from the bank.
		*
		* @param p is the ArrayList of players
		* @param index is the index of the player that has the current turn
		* @return index of the player that has the career match
		*/
	public int determineAction (ArrayList<Player> p, int index)
	{
		int match = super.findCareerMatch (p);
    
		if (match == index)
      p.get(index).addCash (COLLECT);
    else
    {
			SalaryCard s = p.get (index).getSalaryCard();
			double val = s.getSalary () * .10;
			int amt = (int) val;

			p.get (index).addCash (amt * -1);
      
			// if there is a racecar driver in the game, the cash gets add to him
			if (match != -1)
        p.get (match).addCash (amt);
		}

		return match;
	}

	@Override
	/** returns a String that indicates that there is an F1 Race ongoing.
		*
		* @return "The F1 Race is now ongoing!"
		*/
	public String toString ()
	{
		return "The F1 Race is now ongoing!";
	}
}