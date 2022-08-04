import java.util.*;

/**
	* <p> Player holds all the information of the player during the duration of the game.
  * This includes the name, amount of cash and loan, the player's career card, salary card and house card, if the player's married and how many children he has, and his current path and index. This also allows the manipulation of these attributes. </p>
	*/
public class Player
{	
  private String name;
  private int cash;
  private int loan;
	private int number;

  private SalaryCard salary;
  private HouseCard house;
  private CareerCard career;

  private Space currentSpace;
  private Path currentPath;
	private int currentIndex;

	private boolean withDegree;
  private boolean married;
  private int noOfChildren;

	/** creates the Player object. This also initializes the different attributes of the players.
		*
		* @param name is the name of the player
		* @param p is the chosen path of the player
		* @param number is the player number assigned to the player
		*/
  public Player (String name, Path p, int number)
  {
		if (name.length () > 10)
			name = name.substring (0,10);
		
		this.number  = number;
    this.name = name;
    cash = 200000;
		
		currentPath = p;
		p.setCurrentIndex(0);
		currentSpace = p.getSpace ();
		p.setCurrentIndex (-1);

		currentIndex = -1;

		withDegree = false;

		loan = 0;
		noOfChildren = 0;
		married = false;
  }

	/** returns the name of the player
		*
		* @return name of player
		*/
	public String getName ()
	{
		return name;
	}

	/** returns the player's amount of cash
		*
		* @return amount of cash
		*/
  public int getCash ()
	{
		return cash;
	}

	/** returns the player's amount of loan
		*
		* @return amount of loan
		*/
	public int getLoan ()
	{
		return loan;
	}

	public int getNumber ()
	{
		return number;
	}

	/** returns the current path of the player
		*
		* @return the player's current path
		*/
	public Path getPath ()
	{
		return  currentPath;
	}

	/** returns the current space of the player
		*
		* @return the player's current space
		*/
	public Space getCurrentSpace ()
	{
		return currentSpace;
	}

	/** returns the index of the current space of the player in its current path
		*
		* @return player's  current index
		*/
	public int getCurrentIndex ()
	{
		return currentIndex;
	}

	/** returns the player's career card
		*
		* @return the career card of the player
		*/
	public CareerCard getCareerCard ()
	{
		return career;
	}

	/** returns the player's salary card
		*
		* @return the salary card of the player
		*/
	public SalaryCard getSalaryCard ()
	{
		return salary;
	}

	/** returns the player's house card
		*
		* @return the house card of the player
		*/
	public HouseCard getHouseCard ()
	{
		return house;
	}

	/** returns if the player is married or not
		*
		* @return true if the player is married; false otherwise
		*/
	public boolean isMarried ()
	{
		return married;
	}

	/** returns the player's number of children 
		*
		* @return the number of children of the player
		*/
	public int getNoOfChildren ()
	{
		return noOfChildren;
	}

	/** returns if the player has a degree 
		*
		* @return true if the player has a degree; false otherwise
		*/
	public boolean isWithDegree ()
	{
		return withDegree;
	}

	/** changes the path of the player and makes the player's current index to -1
		*
		* @param p is the new currentPath of the player
		*/
	public void changePath (Path p)
	{
		currentPath = p;
		currentIndex = -1;
	}

	/** sets the current space of the player 
		*
		* @param space is the new currentSpace of the player
		*/
	public void setCurrentSpace (Space space)
	{
		currentSpace = space;
	}

	/** sets the currentIndex of the player to the given index. It also calls setCurrentSpace () to the currentIndex
		*
		* @param index is the new currentIndex of the player
		*/
	public void setCurrentIndex (int index)
	{
		currentIndex = index;
		currentPath.setCurrentIndex(currentIndex);
		setCurrentSpace (currentPath.getSpace ());
	}

	/** sets the careerCard of the player to the given careerCard
		*
		* @param c is the player's new careerCard
		*/
	public void setCareerCard (CareerCard c)
	{
		career = c;
	}

	/** sets the careerCard of the player to the given salaryCard
		*
		* @param s is the player's new salaryCard
		*/
	public void setSalaryCard (SalaryCard s)
	{
		salary = s;
	}

	/** adds/subtracts amount from the player's cash. If there is insufficient funds, it allows the player to borrow from the bank.
		*
		* @param amt is the amount that would be added
		*/
  public void addCash (int amt)
  {
    if (cash + amt >= 0 || currentIndex == 59)
      cash += amt;
		else
		{
			int minimum = -1 * (cash + amt);
			int times = ((int) Math.ceil (((double) minimum)/20000));
			cash = cash + times * 20000;
			cash = cash + amt;
			addLoan (times * 25000);
		}
  }

	/** adds the amount to the loan of the player 
		*
		* @param amt  is the amount to be added to the player's loan 
		*/
	public void addLoan (int amt)
	{
		if (loan + amt >= 0)
			loan += amt;
	}

	/** sets the houseCard of the player to the given houseCard
		*
		* @param h is the player's houseCard
		*/
  public void buyHouse (HouseCard h)
  {
    house = h;
  }

	/** adds the given num to the player's number of children 
		*
		* @param num is the number of children to be added to the player's number of children 
		*/
  public void haveChildren (int num)
  {
    noOfChildren += num;
  }

	public void graduated ()
	{
		withDegree = true;
	}

	/** sets the player's married attribute to true if called
		*/
	public void getMarried ()
  {
    married = true;
  }

	@Override
	/** returns the player's name in the format: "Player " + name + "\nCash: " + cash + "\nLoan: " + loan + "\nCurrent Path: " + currentPath.toString ()
		*
		* @return "Player " + name + "\nCash: " + cash + "\nLoan: " + loan + "\nCurrent Path: " + currentPath.toString ()
		*/
  public String toString ()
	{
		return "Player " + name + "\nCash: " + cash + "\nLoan: " + loan + "\nCurrent Path: " + currentPath.toString ();
  }
}