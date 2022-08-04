import java.util.*;
/** 
	* <p>CardDeck holds all the cards needed for the game. It also 
	* allows the shuffling and generation of the Action Cards, Blue 
	* Cards, Salary Cards, Career Cards and the House Cards. </p>
	*/
public class CardDeck 
{
	private static ArrayList<ActionCard> actionCards;
  private static ArrayList<ActionCard> discardPile;
	private static ArrayList<SalaryCard> salaryCards;
	private static ArrayList<BlueCard> blueCards;
	private static ArrayList<BlueCard> discardBlue;
	private static ArrayList<HouseCard> houseCards;
	private static ArrayList<CareerCard> careerCards;

	/** creates the different cards needed, and calls the method needed for generation of the cards.
		*/
	public CardDeck ()
	{
		actionCards = new ArrayList<>(50);
    blueCards = new ArrayList<>(7);
    discardPile = new ArrayList<>(50);
		careerCards = new ArrayList<>(7);
		salaryCards = new ArrayList<>(10);
		houseCards = new ArrayList<>(10);
		discardBlue = new ArrayList<>(7);

		generateCards();
	}

	/** generates all the cards for the game, and shuffles them after generation.
		*/
  private void generateCards ()
  {
    int desc, ctr;

		// generates the Action Cards
		for (ctr = 0, desc = 0; ctr < CollectFromBank.COUNT; desc++, ctr++)
		{
			if (desc >= 6)
				desc = 0;
			actionCards.add(new CollectFromBank (desc));
		}

		for (ctr = 0, desc = 0; ctr < PayBank.COUNT; desc++, ctr++)
		{
			if (desc >= 5)
				desc = 0;
			actionCards.add (new PayBank (desc));
		}
		
		for (ctr = 0, desc = 0; ctr < PayPlayer.COUNT; desc++, ctr++)
		{
			if (desc >= 2)
				desc = 0;
			actionCards.add (new PayPlayer (desc));
			actionCards.add (new CollectFromPlayer (desc));
		}

    shuffleActionCards();

    // generates the Blue Cards
		blueCards.add (new Lawsuit ());
		blueCards.add (new CompRepair ());
		blueCards.add (new F1Race ());
		blueCards.add (new SalaryTaxDue ());
		blueCards.add (new SkiAccident ());
		blueCards.add (new TipServer ());
		blueCards.add (new WorldCup ());
    shuffleBlueCards();

    // generates the Career Cards
    careerCards.add (new CareerCard (0, true, 5, 8));
    careerCards.add (new CareerCard (1, true, 4, 7));
    careerCards.add (new CareerCard (2, true, 3, 7));
    careerCards.add (new CareerCard (3, true, 5, 8));
    careerCards.add (new CareerCard (4, false, 1, 4));
    careerCards.add (new CareerCard (5, false, 2, 8));
    careerCards.add( new CareerCard( 6, false, 1, 6));
		shuffleCareerCards();

    // generates the Salary Cards
    for (int j = 0; j < 10; j++)
			salaryCards.add (new SalaryCard ());

		// generates the House Cards
		for (int j = 0; j < 10; j++)
			houseCards.add (new HouseCard (j));
		
		Collections.shuffle(houseCards);
  }

	/** moves the action card that a player picks from the action card deck to the discard pile. If the deck of actionCards is empty, it transfers the cards from the discardPile to the actionCards and shuffles it.
		*
		* @return the ActionCard that was on top of the deck of actionCards
		*/
  private static ActionCard moveCards()
  {
		ActionCard card = actionCards.remove(0);
    discardPile.add (card);
    
		// moves back the discardPile to the actionCard deck if actionCard deck is already empty
		if (actionCards.size () == 0)
		{
			actionCards = discardPile;
			Collections.shuffle (actionCards);
			discardPile = new ArrayList <ActionCard>(50);
		}

		return card;
  }

	/** moves the blue cards that a player picks from the blue card deck to the discard pile. If the deck of blueCards is empty, it transfers the cards from the discardBlue to the blueCards and shuffles it.
		*
		* @return the BlueCard that was on top of the deck of blueCards
		*/
	private static BlueCard moveBlue ()
	{
		BlueCard card = blueCards.remove (0);
		discardBlue.add (card);

		// moves back the discardBlue to the blueCard deck if blueCard deck is already empty
		if (blueCards.size () == 0)
		{
			blueCards = discardBlue;
			Collections.shuffle (blueCards);
			discardBlue = new ArrayList <BlueCard>(7);
		}

		return card;
	}

	/** adds the career card to the ArrayList that holds the career cards
		* @param c is the CareerCard that would be added
		*/
	public static void addCareerCard (CareerCard c)
	{
		careerCards.add (c);
	}

	/** adds the salary card to the ArrayList that holds the salary cards
		* @param s is the SalaryCard that would be added
		*/
	public static void addSalaryCard (SalaryCard s)
	{
		salaryCards.add (s);
	}

	/** removes the house card to the ArrayList that holds the house cards
		* @param h is the HouseCard that would be removed
		*/
	public static void addHouseCard (HouseCard h)
	{
		houseCards.add (h);
	}

	/** shuffles the Action Cards created for the game.
		*/
  private void shuffleActionCards ()
  {
    Collections.shuffle (actionCards);
  }

	/** shuffles the Blue Cards created for the game.
		*/
  private void shuffleBlueCards ()
  {
    Collections.shuffle (blueCards);
  }

	/** shuffles the Career Cards created for the game.
		*/
	private void shuffleCareerCards ()
	{
		Collections.shuffle (careerCards);
	}

	/** calls the method moveCards, and returns the card at index 0 in the ArrayList of action cards
		*
		* @return the topmost card in the deck of actionCards
		*/
  public static ActionCard getActionCard ()
	{
		return moveCards ();
	}

	/** calls the method moveBlue, and returns the card at index 0 in the ArrayList of blueCards
		*
		* @return the topmost card in the deck of blueCards
		*/
  public static BlueCard getBlueCard ()
	{
		return moveBlue ();
	}

	/** returns the card at index 0 in the ArrayList of career cards, which removes the careerCard from the deck of careerCards
		* 
		* @return careerCard at index 0
		*/
	public static CareerCard getCareerCard ()
	{
		return careerCards.remove (0);
	}

	/** returns the ArrayList of houseCards without removing it from the deck
		*
		* @return ArrayList of houseCards
		*/
  public HouseCard getHouseCards ()
	{
		return houseCards.remove (0);
	}

	/** returns the card at index 0 in the ArrayList of salary cards, which removes it from the deck of salaryCards
		* 
		* @return salaryCard at index 0
		*/
	public static SalaryCard getSalaryCard ()
	{
		return salaryCards.remove (0);
	}

	/** returns the number of cards in the ArrayList of salary cards
		*
		* @return number of salaryCards in the deck
		*/
	public int getSalaryCardSize ()
	{
		return salaryCards.size ();
	}

	/** returns the number of cards in the ArrayList of house cards
		*
		* @return number of houseCards in the deck
		*/
	public int getHouseCardSize ()
	{
		return houseCards.size ();
	}

	/** returns the number of cards in the ArrayList of career cards
		*
		* @return number of careerCards in the deck
		*/
	public int getCareerCardSize ()
	{
		return careerCards.size ();
	}	
}