import java.util.*;

/**
	* <p> MagentaSpace holds the methods that would implement the needed function of a magenta space. 
	* It allows a player to experience different milestones in his life </p>
	*/
public class MagentaSpace extends Space 
{
	private String type;

	/** creates a magenta space given the number and the type. It passes the number assigned to it to its superclass Space.
		*
		* @param number is the number assigned to the space (1-60)
		* @param type is the the type of magena space  		
		*/
	public MagentaSpace (int number, String type)
	{
		super (number);
		this.type = type;
	}

	/** determines what type of magenta space the player landed on
		*
		* @param p is the ArrayList of players
		* @param index is the index of the player that has the current turn
		* @param paths is the paths in the game
		* @param order is the players that is already done with the game
		* @return number that may specify if the player is alrady married or getting married, of how many children would be added to his number of children
		*/
 	public int determineAction (ArrayList<Player> p, int index, Path[] paths, ArrayList<Player> order)
  {
		int number = -1;
    
		switch(type)
    {
      case "Graduation Space":
				p.get (index).graduated ();
				break;
      case "Get Married":
        number = getMarried(p, index);
        break;
      case "Have Baby":
        number = haveBaby(p, index);
        break;
      case "Have Twins":
        number = haveTwins(p, index);
        break;
			case "END. Retirement." :
				number = retirement(p, index, order);
    }
		
		return number;
  }

	/** makes a player choose between two sets of career cards and salary cards.
		*
		* @param p is the player that has the current turn
		* @param c is the ArrayList of career cards
		* @param s is the ArrayList of salary cards
		* @param answer is the index of the set of salaryCard and careerCard that the player chose
		*/
  public void chooseBetweenCards (Player p, ArrayList<CareerCard> c, ArrayList<SalaryCard> s, int answer)
  { 
		p.setCareerCard (c.remove (answer - 1));
		p.setSalaryCard (s.remove (answer - 1));
		
		CardDeck.addCareerCard (c.get (0));
		CardDeck.addSalaryCard (s.get (0));
  }

	/** gets the careerCards that they player can choose from 
		* @param p is the player that would choose from the careerCards
		* @param num is the number of careerCards that would be coming from the deck
		* @return an ArrayList of careerCards that the player can choose from
		*/
	public ArrayList<CareerCard> getCareerCards (Player p, int num)
	{
		ArrayList<CareerCard> career = new ArrayList<>(2);
		boolean check;
		int i = 0;

		if (num == 1)
		{
			career.add (p.getCareerCard ());
			i++;
			num++;
		}
			
		// gets the specified number of career cards from the deck, with respect to the player's degree and the card's degree requirement
		for (; i < num; i++)
			do
			{
				career.add (CardDeck.getCareerCard ());

				check = career.get (i).getDegreeReq ();
				if (check && !p.isWithDegree ())
				{
					CardDeck.addCareerCard (career.get (i));
					CardDeck.addSalaryCard (CardDeck.getSalaryCard ());
					career.remove (i);
				}
			} while (check && !p.isWithDegree ());

		return career;
	}

	/** gets the salaryCards that they player can choose from 
		*
		* @param p is the player that would choose from the careerCards
		* @param career is the arrayList of careerCards 
		* @param num is the number of careerCards that would be coming from the deck
		* @return an ArrayList of careerCards that the player can choose from
		*/
	public ArrayList<SalaryCard> getSalaryCards (Player p, ArrayList<CareerCard> career, int num)
	{
		ArrayList<SalaryCard> salary = new ArrayList<>(2);
		int i = 0;

		if (num == 1)
		{
			salary.add (p.getSalaryCard ());
			i++;
			num++;
		}
			
		for (; i < num; i++)
		{
			salary.add (CardDeck.getSalaryCard ());
			salary.get (i).setMaximumSalary (career.get (i));
		}

		return salary;
	}

	/** allows a player to buy a house from the deck of house cards.
		* @param p is the player who will buy a house
		* @param houses is the arrayList of houseCards 
		* @param h is the houseCard that the player chose 
		*/
  public void buyHouse (Player p, ArrayList<HouseCard> houses, HouseCard h)
  {
		p.buyHouse (h);
		p.addCash (-1 * h.getBuyingPrice ());
		houses.remove (h);

		while (houses.size () > 0)
			CardDeck.addHouseCard (houses.remove (0));
  }

	/** allows a person to get married, if he is not yet married. 
		* It also adds cash to the player who got married from the other player's cash.
		* @param p is the ArrayList of players 
		* @param index is the index of the player that is currently in turn
		*/
  private int getMarried (ArrayList<Player> p, int index)
  {
		int number = -1, i = index;
    if (!(p.get (index).isMarried ()))
    {
			number = Game.randomNumberGenerator (1, 10);
			p.get (index).getMarried ();
      int amt = 5000;
      if (number % 2 == 0)
        amt = 10000;
      
      p.get (index).addCash ((p.size () - 1) * amt);

      for (int j = 0; j < p.size() ; j++)
			{
				if (i >= p.size () - 1)
					i = 0;
				else
					i++;

				if (index != i && i != p.size ())
					p.get (i).addCash (amt * -1);
			}
    }

		return number;
  }

	/** allows a player to have twins, if they are married. It also adds 5000 from each players to the player that will have the twins. 
		*
		* @param p is the ArrayList of players
		* @param index is the index of the player that has the current turn
		*/
  private int haveBaby (ArrayList<Player> p, int index)
  {
		int number = -1, i = index;

    if (p.get (index).isMarried ())
    {
			number = 1;
			p.get (index).haveChildren (1);
			int amt = 5000;
      p.get (index).addCash ((p.size () - 1) * amt);

      for (int j = 0; j < p.size() ; j++)
			{
				if (i >= p.size () - 1)
					i = 0;
				else
					i++;

				if (index != i && i != p.size ())
					p.get (i).addCash (amt * -1);
			}
    }
		return number;
  }

	/** allows a player to have twins, if they are married. It also adds 10000 from each players to the player that will have the twins. 
		*
		* @param p  is the ArrayList of players
		* @param index is the index of the player that has the current turn
		*/
  private int haveTwins (ArrayList<Player> p, int index)
  {
		int number = -1, i = index;

    if (p.get (index).isMarried ())
    {
			number = 2;
			p.get (index).haveChildren (2);
			
			int amt = 10000;
      p.get (index).addCash ((p.size () - 1) * amt);
      
			for (int j = 0; j < p.size() ; j++)
			{
				if (i >= p.size () - 1)
					i = 0;
				else
					i++;

				if (index != i && i != p.size ())
					p.get (i).addCash (amt * -1);
			}
    }
		return number;
  }

	/** manages the junction of  the game. It allows the players to continue on their current path or to change path.
		*
		* @param p is the player that can change his path
		* @param paths is the paths of the game
		* @param choice is the player's choice of path
		*/
  public void whichPath (Player p, Path[] paths, int choice)
  {
		if (choice != 0)
			p.changePath (paths [choice]);
  }

	/** computes the final amount of cash that a player has by selling his house, the number of his children and the order of reaching the 60th space
		*
		* @param players is the ArrayList of pplayers
		* @param index is the index of the player that has the curent turn
		* @param order is the ArrayList of the players that are done with the game
		* @return number is the amount of loan of the player
		*/
	private int retirement (ArrayList<Player> players, int index, ArrayList<Player> order)
	{
			int num = 0;
			// removes player from the turn count
			Player p = players.remove (index);
			order.add (p);
			
			// collects retirement pay based on place
			int amt = 0;
			switch (order.size ())
			{
				case 1: amt = 100000; break;
				case 2: amt = 50000; break;
				case 3: amt = 20000;
			}
			p.addCash (amt);

			// collects money for each child
			if (p.getNoOfChildren () != 0)
			{
				amt = p.getNoOfChildren () * 10000;
				p.addCash (amt);
			}

			// sells house
			if (p.getHouseCard () != null)
			{
				HouseCard h = p.getHouseCard ();
				amt = h.getSellingPrice ();
				p.addCash (amt);
			}
			
			// pays loans
			if (p.getLoan () != 0)
			{
				num = p.getLoan ();
				p.addCash (-num);
			}

			return num;
	}

	/** returns the type of magenta space that the player landed on
		* @return the type of magenta space
		*/
	public String getType ()
	{
		return type;
	}

	@Override
	/** returns a formatted string that says that the player landed on a MagentaSpace
		* @retun "You have landed on a Magenta Space."
		*/
	public String toString ()
	{
		return "STOP! You have landed on a Magenta Space.";
	}
}