import java.util.*;

/**
	* <p> BlueCard handles the methods needed the finding of the career math of the BlueCard from the players. It also holds the card's careerType match. </p>
	*/
public abstract class BlueCard
{	
	protected final int COLLECT = 15000;
	protected String careerType;
	
	/** returns the String that indicates that the player who got the card matches the career indicated in the card.
		* @return formatted String that would indicate that the career is a match
		*/
	public abstract String matchText ();
	/** returns the String that indicates that the player's career is not matched with the career indicated in the card.
		* @return formatted String that would indicate that the career is not a match
		*/
  public abstract String notMatchText ();
	/** returns the String that indicates theh amount that the player needs to pay to a player or to the bank
		* @return formatted String that would indicate how much a player needs to pay
		*/
  public abstract String payText ();
	/** returns the index of the player with a career match
		* @param p is the ArrayList of 0players
		* @param index is the index of the current player
		* @return the index of the player that has the career match; -1 if there is no career match
		*/
	public abstract int determineAction (ArrayList<Player> p, int index);

	/** creates the BlueCard given the career that is matched with the card.
		*
		* @param type is the career matched with the BlueCard.
		*/
	public BlueCard (String type)
	{
		careerType = type;
	}

	/** return the career matched with the BlueCard
		*
		* @return the careerType of the BlueCard
		*/
	public String getCareerType ()
	{
		return careerType;
	}

	/** checks the players' CareerCard to find if there is a career match with the BlueCard.
		*
		* @param p is the ArrayList of players
		* @return index of the player that matches the careerType of the BlueCard
		*/
  public int findCareerMatch (ArrayList<Player> p)
  {
    for (int i = 0; i < p.size(); i++)
		{
			CareerCard career = p.get(i).getCareerCard();

			if (career != null && career.getCareer ().equalsIgnoreCase (careerType))
				return i;
		}
    return -1;
  }
}