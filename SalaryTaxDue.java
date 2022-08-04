import java.util.*;

/**
	* <p>SalaryTaxDue handles the attributes and methods needed to do the action for a SalaryTaxDue. </p>
	*/
public class SalaryTaxDue extends BlueCard
{
	/** creates the SalaryTaxDue object. It also passes on to the superclass BlueCard the career match for this object.
		*/
	public SalaryTaxDue ()
	{
		super ("Accountant");
	}

	/** returns the String that indicates that the player who got the card matches the career indicated in the card.
		* 
		* @return "You were hired to fix someone's taxes! Got $15000."
		*/ 
	public String matchText ()
	{
		return "You were hired to fix someone's taxes! Got $15000.";
	}

	/** returns the String that indicates that the player's career is not matched with the career indicated in the card.
		*
		* @return "You paid your taxes."
		*/
	public String notMatchText ()
	{
		return "You paid your taxes.";
	}

	/** returns a formatted String that indicates that the player paid  a certain amt of money
		*
		* @return "You paid the tax due on your current salary "
		*/      
  public String payText ()
	{
    return "You paid the tax due on your current salary ";
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
			SalaryCard s = p.get (index).getSalaryCard();
			int amt = s.getTaxDue ();

			p.get (index).addCash (amt * -1);
      
			if (match != -1)
        p.get (match).addCash (amt);
		}

		return match;
	}

	@Override
	/** returns a String that indicates that the filing of taxes is near.
		*
		* @return "The deadline for taxes is near."
		*/
	public String toString ()
	{
		return "The deadline for taxes is near.";
	}
}