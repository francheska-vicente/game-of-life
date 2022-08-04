import java.util.*;

/** 
  * <p> Game class holds the game, and tells the game flow of That's Life.
  * It also allows the addition of player, and determination of 
	* the winner based on the cash after the payment of the loans </p>
  */
public class Game
{	
	private static final int MAX_NO_SPACE = 60;

  private ArrayList<Player> players;
	private ArrayList<Player> finalOrder;

	public static CardDeck cards;

  private int turnCount;
	private Path [] paths;

	/** creates the Game object. It also creates the needed objects in the game such as the path, spaces and the CardDeck.
	  */
  public Game ()
  {
		paths = new Path[4];
		paths[0] = new Path ("Career Path", 60);
		paths[1] = new Path ("College Path", 10);
		paths[2] = new Path ("C. Career Path", 10);
		paths[3] = new Path ("Family Path", 10);

		addSpaces ();
  }

	/** creates the ArrayList that holds the players, and the ranking of players based on their time of finishing the game.
		* @param playerCount is the number of players
		*/
	public void createPlayers (int playerCount)
	{
		turnCount = 0;
		players = new ArrayList<>(playerCount);
		finalOrder = new ArrayList<>(playerCount);
		cards = new CardDeck ();

		for (int i = 0; i < 4; i++)
			paths [i].setCurrentIndex (-1);
	}

	/** adds players that would be playing the game, given their name and their choice for path.
		*
		* @param name is the name of the player
		* @param index is the index assigned to the path the player wishes to take
		*/
  public void addPlayer (String name, int index)
  {
		boolean status = false;

		turnCount++;

    players.add (new Player (name, paths [index], turnCount));
		
    // if the player chose the career path, get career card that doesn't require a degree
		if (index == 0)
		{
			for (int i = 0; i < 7 && status == false; i++)
			{
				CareerCard career = cards.getCareerCard ();

				if (career.getDegreeReq () == true)
				{
					cards.addCareerCard (career);
				}
				else
				{
					players.get (players.size () - 1).setCareerCard (career);
					status = true;
				}
			}

			players.get (players.size () - 1).setSalaryCard (cards.getSalaryCard ());
			players.get (players.size () - 1).getSalaryCard ().setMaximumSalary (players.get (players.size () - 1).getCareerCard ());
		}
		else
		{
			// if the player chooses college path, he takes a 50,000 loan from the bank that would be used to pay his college
			players.get (players.size () - 1).addLoan (50000);
		}
  }

	/** creates the spaces, which would create the board for the game
		*/
	private void addSpaces ()
	{
		// creates the career path
		for (int i = 1; i <= 60; i++)
			if (i == 9 || i == 26 || i == 37 || i == 58)
				paths [0].addSpace ("Green", i, "Pay Raise");
			else if (i == 4 || i == 14 || i == 23 || i == 33 || i == 52)
				paths [0].addSpace ("Green", i, "Pay Day");
			else if (i == 20 || i == 40)
				paths [0].addSpace ("Magenta", i, "Which Path?");
			else if (i == 45)
				paths [0].addSpace ("Magenta", i, "Get Married");
			else if (i == 6 || i == 16 || i == 35 || i == 42 || i == 48 || i == 54)
				paths [0].addSpace ("Blue", i);
			else if (i == 60)
				paths [0].addSpace ("Magenta", i, "END. Retirement.");
			else
				paths [0].addSpace ("Orange", i);

		// creates college path
		for (int i = 1; i <= 10; i++)
			if (i == 8)
				paths [1].addSpace ("Magenta", i, "Graduation Space");
			else if (i == 9)
				paths [1].addSpace ("Magenta", i, "College Career Choice");
			else 
				paths [1].addSpace ("Orange", i);

		// creates the change career path
		for (int i = 21; i <= 30; i++)
			if (i == 29)
				paths [2].addSpace ("Blue", i);
			else if (i == 22)
				paths [2].addSpace ("Magenta", i, "Job Search");
			else if (i == 23)
				paths [2].addSpace ("Green", i, "Pay Day");
			else if (i == 26)
				paths [2].addSpace ("Green", i, "Pay Raise");
			else
				paths [2].addSpace ("Orange", i);

		// creates the start a family path
		for (int i = 41; i <= 50; i++)
			if (i == 41)
				paths [3].addSpace ("Magenta", i, "Get Married");
			else if (i == 42)
				paths [3].addSpace ("Blue", i);
			else if (i == 44)
				paths [3].addSpace ("Magenta", i, "Buy a House");
			else if (i == 47)
				paths [3].addSpace ("Magenta", i, "Have Baby");
			else if (i == 49)
				paths [3].addSpace ("Magenta", i, "Have Twins");
			else
				paths [3].addSpace ("Orange", i);	
	}

	/** generates a number from min to max
		*
		* @param min is the smallest number that the generator may generate
		* @param max is the largest number that the generator may generate
		* @return random number from min - max
		*/
  public static int randomNumberGenerator (int min, int max)
  {
    Random rand = new Random ();
    return (rand.nextInt (max + 1 - min) + min);
  }

	/** determines which player would be the next turn
		*
		* @return the turnCount that is considered as the next turn
		*/
  public int nextTurn ()
  {
    if (turnCount >= players.size())
      turnCount = 1;
    else
      turnCount++;

    return turnCount;
  }

	/** updates the index of the path of the player to the currentIndex of the player
		*/
	public void updateIndex ()
	{
		Path p = players.get (turnCount - 1).getPath ();
		int index = players.get (turnCount - 1).getCurrentIndex ();

		p.setCurrentIndex (index);
	}

	/** moves the player to the next space, which is the player's number's current space added to the random generated number
		* 
		* @param numberGenerated is the random number (1 - 10) that is generated 
		* @return the space that the player landed on 
		*/
	public Space moveToSpace (int numberGenerated)
	{	
		Path p = players.get (turnCount - 1).getPath ();
		int index = players.get (turnCount - 1).getCurrentIndex ();

		int start = 0;
		int move = 0, num = 0, i, check;
		boolean status = false;

		updateIndex ();

		// checks if the player is currently on a magenta space
		if (index != -1 && p.getMaxNoOfSpaces () > index + 1 && p.getSpace () instanceof MagentaSpace)
		{
			p.setCurrentIndex (index + 1);
			start = 1;
		}

		check = index;
	
		// checks if a player would go through a magenta space
		for (i = 1; i <= numberGenerated - start && p.getMaxNoOfSpaces () > index + i && status == false; i++)
			if (check != -1 && p.getSpace () instanceof MagentaSpace)
			{
				status = true;
				move = i;
			}
			else
			{
				p.setCurrentIndex(index + start +  i);
				check = 0;
			}

		p.setCurrentIndex (index);

		// finds the multiplier for the index of the career path if the player is already finished with his path
		if (p.getName().equalsIgnoreCase("College Path"))
			num = 1;
		else if (p.getName().equalsIgnoreCase("C. Career Path"))
			num = 3;
		else if (p.getName().equalsIgnoreCase("Family Path"))
			num = 5; 

		if (start == 1)
			start = 0;
		else if (start == 0)
			start = 1;

		if (players.get (turnCount - 1).getCurrentIndex () + numberGenerated >= 60)
		{
			players.get (turnCount - 1).getPath ().setCurrentIndex (59);
		}
		else if (status)
		{
			// if the player would go through a magenta space, stop at the magenta space
			players.get (turnCount - 1).getPath ().setCurrentIndex (index + move - start);
		}
		else if (p.getMaxNoOfSpaces () == 10 && index + numberGenerated >= 10)
		{
			// if the player is on a path that is not the career path and adding the number generated to the current space would result to a number higher than the maximum number of spaces, go back to career path
			changePath (1);
			players.get (turnCount - 1).getPath ().setCurrentIndex (num * 10 + (numberGenerated + index - 10));
		}
		else
		{
			players.get (turnCount - 1).getPath ().setCurrentIndex (index  + numberGenerated);
		}
		
		players.get (turnCount - 1).setCurrentIndex (players.get (turnCount - 1).getPath ().getCurrentIndex ());

		Space space = players.get (turnCount - 1).getPath ().getSpace ();

		return space;
	}

	/** changes the path of the player from his current path to the given choice
		* @param choice is the path's index+1
		*/
	private void changePath (int choice)
	{
		players.get (turnCount - 1).changePath (paths[choice - 1]);
		players.get (turnCount - 1).getPath ().setCurrentIndex (-1);
	} 

	/** allows the current player borrow money that is a multiple of 20,000 during his turn
		*
		* @param amt is the amount that the player wants to borrow from the bank
		*/
	public void borrowMoney (int amt) throws ArithmeticException
	{
		if (amt % 20000 == 0 && amt != 0)
		{
			int times = ((int) Math.ceil ((double) amt/20000));
			players.get (turnCount - 1).addCash (amt);
			players.get (turnCount - 1).addLoan (amt + times * 5000);
		}
		else if (amt <= 0)
			throw new ArithmeticException ("Amount is invalid.");
		else
			throw new ArithmeticException ("Amount is not a multiple of 20000.");
	}

	/** allows the player to pay his loans during his turn as long as it is divisible by 25000
		* @param amt is the amount that the player wants to pay 
		*/
	public void payLoan (int amt) throws ArithmeticException
	{
		if (players.get (turnCount - 1).getLoan() < amt)
		{
			throw new ArithmeticException ("Payment exceeds the loan.");
		}
		else if (amt <= 0)
			throw new ArithmeticException ("Invalid value.");
		else if (!(amt % 25000 == 0))
			throw new ArithmeticException ("Payment should be a multiple of $25,000.");
		else if (players.get (turnCount - 1).getCash () - amt < 0)
			throw new ArithmeticException ("Payment exceeds cash.");
		else
		{
			players.get (turnCount - 1).addLoan (amt * -1);
			players.get (turnCount - 1).addCash (amt * -1);
		}
	} 

	/** determines the winner of the game (the player that has the most amount of cash).
		*
		* @return the player that is considered as the winner of the game.
		*/
	public Player determineWinner ()
	{
    Player winner = finalOrder.get(0);

		for (int j = 1; j < finalOrder.size(); j++)
		{
			if (winner.getCash() < finalOrder.get(j).getCash())
				winner = finalOrder.get(j);
		}
		
		return winner;
	}

	/** returns the ArrayList of players that is currently playing the game 
		*
		* @return the ArrayList of active players in the game
		*/
	public ArrayList<Player> getPlayers ()
	{
		return players;
	}

	/** returns the value of the turnCount
		*
		* @return turnCount value
		*/
	public int getTurnCount ()
	{
		return turnCount;
	}

	/** returns the houseCards 
		*
		* @return the ArrayList of hosueCards
		*/
	public ArrayList<HouseCard> getHouseCards ()
	{
		ArrayList <HouseCard> houses = new ArrayList<>(5);

		for (int i = 0; i < 5 && cards.getHouseCardSize () != 0; i++)
			houses.add (cards.getHouseCards ());

		return houses;
	}

	/** returns the finalOrder of players, which is the players in the order that they finished the game
		*
		* @return the ArrayList of the players who are done with the game
		*/
	public ArrayList<Player> getFinalOrder ()
	{
		return finalOrder;
	}

	/** returns the paths in the game
		*
		* @return array of path in the game
		*/
	public Path[] getPaths ()
	{
		return paths;
	}
}