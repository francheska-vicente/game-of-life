import java.util.*;

/**
	* <p>TipServer handles the attributes and methods needed to do the action for a WorldCup. It also holds the number that is randomly generated that they player needs to pay x1000.</p>
	*/
public class TipServer extends BlueCard 
{
    protected int number;

	/** creates the TipServer object. It also passes on to the superclass BlueCard the career match for this object.
		*/
  public TipServer () 
	{
    super ("Server");
  }

	/** returns the number generated 
		* 
		* @return randomly generated number
		*/
  public int getNumber() 
	{
    return number;
  }

	/** returns the String that indicates that the player who got the card matches the career indicated in the card.
		* 
		* @return "You got tipped! Get $15000."
		*/ 
  public String matchText() 
	{
    return "You got tipped! Get $15000.";
  }

	/** returns the String that indicates that the player's career is not matched with the career indicated in the card.
		*
		* @return "You tipped the server."
		*/
  public String notMatchText() 
	{
    return "You tipped the server.";
  }

	/** returns a formatted String that the player rolled a number and that he needs to pay x1000 of this number
		*
		* @return "You rolled the number " + number + ". Paid $ " + number * 1000
		*/ 
	public String payText() 
	{
    return "You rolled the number " + Integer.toString(number)
                + ". Paid $ " + Integer.toString(number * 1000) + " ";
  }

	/** determines if the player needs to pay the bank or a player, or if a player will receive cash from the bank.
		*
		* @param p is the ArrayList of players
		* @param index is the index of the player that is currently in turn
		* @return the index of the player that has the career match; -1 if there is no career match
		*/
  public int determineAction(ArrayList<Player> p, int index) 
	{
    int match = super.findCareerMatch(p);

    if (match == index)
      p.get(index).addCash(COLLECT);
    else 
		{
      number = Game.randomNumberGenerator(1, 10);
      int amt = number * 1000;

      p.get(index).addCash(amt * -1);

      if (match != -1)
        p.get(match).addCash(amt);
    }

		return match;
  }

  @Override
	/** returns a String that indicates that you are at a fancy restaurant
		*
		* @return "You are at a fancy restaurant."
		*/
  public String toString() 
	{
    return "You are at a fancy restaurant.";
  }
}
