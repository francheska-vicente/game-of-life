import java.awt.event.*;
import javax.swing.event.*;

import java.util.*;

/** 
	* <p> GameController is the ActionListener for the View that is responsible for the Game. It manages the interaction between the View and the Model as it passes the needed prompt to the view and passes back the user's input to the Model. </p>
	*/
public class GameController implements ActionListener
{
	private Game gameModel;
	private GameView gameView;

	/** creates the instance of the GameController. 
		* @param gameModel is where the controller passes the inputs to.
		* @param gameView is the view that the controller manages.
		*/
	public GameController (Game gameModel, GameView gameView)
	{
		this.gameModel = gameModel;
		this.gameView = gameView;

    gameView.setGameListener (this);
	}

	/** updates the buttons and the player information shown in the view after every turn.
		*
		* @param p is the player that is currently in turn.
		*/
	public void updateView (Player p)
	{
		if (p.getHouseCard() == null)
			gameView.setHouseCardEnabled (false);
		else
			gameView.setHouseCardEnabled (true);

		if (p.getLoan() == 0)
			gameView.setPayLoanEnabled (false);
		else
			gameView.setPayLoanEnabled (true);

		if (p.getCareerCard () == null)
			gameView.setCareerCardEnabled (false);
		else
			gameView.setCareerCardEnabled (true);

		if (p.getSalaryCard () == null)
			gameView.setSalaryCardEnabled (false);
		else
			gameView.setSalaryCardEnabled (true);

		gameView.setGetLoanEnabled (true);

		gameView.setNameTxt(p.getName());
    gameView.setCashTxt("Cash: $" + Integer.toString(p.getCash()));
    gameView.setLoanTxt("Loan: $" + Integer.toString(p.getLoan()));
    gameView.setPathTxt(p.getPath().toString());
	}
	
	/** does all the needed passing of output and input between view and model so that the model would be able to do the needed action indicated in the MagentaSpace.
		* 
		* @param mSpace is the space that the player is currently in.
		* @param players is the ArrayList of players
		* @param turnCount is the player number of the player that is currently in turn
		* @param paths is the array of path of the game 
		* @param finalorder is the ArrayList of players who are done with the game
		*/
	private void magentaSpace (MagentaSpace mSpace, ArrayList<Player> players, int turnCount, Path[] paths, ArrayList<Player> finalOrder)
	{
		ArrayList<HouseCard> houses = new ArrayList<>(5);
		ArrayList<SalaryCard> salary = new ArrayList<>(2);
		ArrayList<CareerCard> career = new ArrayList<>(2);
		int choice = 0;

		switch (mSpace.getType ())
		{
			case "College Career Choice":
					career = mSpace.getCareerCards (players.get (turnCount - 1), 2);
					salary = mSpace.getSalaryCards (players.get (turnCount - 1), career, 2);

					// asks the player to choose until he choosen an option and doesn't close the prompt
					do
					{
						choice = gameView.chooseBetweenCards (salary, career, 1);
						
						if (choice == 0)
							gameView.outputError ("Please choose.");
					} while (choice == 0);

					mSpace.chooseBetweenCards (players.get (turnCount - 1), career, salary, choice);

					break;
			case "Job Search":
					career = mSpace.getCareerCards (players.get (turnCount - 1), 1);
					salary = mSpace.getSalaryCards (players.get (turnCount - 1), career, 1);

					// asks the player to choose until he choosen an option and doesn't close the prompt
					do
					{
						choice = gameView.chooseBetweenCards (salary, career, 2);
						
						if (choice == 0)
							gameView.outputError ("Please choose.");
					} while (choice == 0);

					mSpace.chooseBetweenCards (players.get (turnCount - 1), career, salary, choice);

					break;
			case "Buy a House":
					houses = gameModel.getHouseCards ();

					// asks the player to choose until he choosen an option and doesn't close the prompt
					do
					{
						choice = gameView.chooseHouse (houses);
						
						if (choice == -1)
							gameView.outputError ("Please choose.");
					} while (choice == -1);

					mSpace.buyHouse (players.get (turnCount - 1), houses, houses.get (choice));

					break;
			case "Which Path?":
					
					do
					{
						if (mSpace.getNumber () == 20)
							choice = gameView.askChangeCareerPath();
						else
							choice = gameView.askFamilyPath();
						
						if (choice == -1)
							gameView.outputError ("Please choose.");
					} while (choice == -1);
					
					mSpace.whichPath (players.get (turnCount - 1), paths, choice);

					break;
			default : 
			{
					int number = mSpace.determineAction (players, turnCount - 1, paths, finalOrder);

					switch (mSpace.getType ())
					{
						case "Graduation Space":
							gameView.graduatePrompt ();
							break;
						case "Get Married":
							gameView.getMarriedPrompt (number);
							break;
						case "Have Baby":
							gameView.haveKidsPrompt (number);
							break;
						case "Have Twins":
							gameView.haveKidsPrompt (number);
							break;
						case "END. Retirement." :
							gameView.retirePrompt (finalOrder, number);
					}
			}
		}
	}

	/** does all the needed passing of output and input between view and model so that the model would be able to do the needed action indicated in the ActionCard that the user got.
		*
		* @param oSpace is the space that the player is currently in.
		* @param players is the ArrayList of players
		* @param turnCount is the player number of the player that is currently in turn
		*/
	private void orangeSpace (OrangeSpace oSpace, ArrayList<Player> players, int turnCount)
	{
		ActionCard card = oSpace.determineAction (players, turnCount - 1);
		boolean checker;
		int choice = 0;

		gameView.showActionCard (card);

		if (((card instanceof PayPlayer && card.toString ().equalsIgnoreCase ("Lawsuit")) || (card instanceof CollectFromPlayer && card.toString ().equalsIgnoreCase ("File Lawsuit"))) && players.size () == 3)
		{
			if (players.size () == 3)
			{
				checker = false;
				int [] index = new int [2];
				gameModel.nextTurn ();
				index [0] = gameModel.getTurnCount () - 1;
				gameModel.nextTurn ();
				index [1] = gameModel.getTurnCount () - 1;
				
				do
				{
					choice = gameView.askWhichPlayer (index);
						
					if (choice == -1)
						gameView.outputError ("Please choose.");
				} while (choice == -1);
			
				choice++;
				gameModel.nextTurn ();
			}
		}
		else if (players.size () == 2)
		{
				gameModel.nextTurn ();
				choice = gameModel.getTurnCount ();
				gameModel.nextTurn ();
		}
		
		card.determineAction (players, turnCount - 1, choice);	
		gameView.showActionPrompt (card);
	}

	/** determines what would happen during a turn of a player. It passes on to the view the needed information for the prompts, and passes to the model the user input.
		*
		* @return space that the player is currently in
		*/
  private Space currentTurn ()
  {
		Space space = null;
		ArrayList<Player> players = gameModel.getPlayers ();
		ArrayList<Player> finalOrder = gameModel.getFinalOrder ();
		int turnCount = gameModel.getTurnCount ();
		Path [] paths = gameModel.getPaths ();

		int number = gameModel.randomNumberGenerator (1,10);

		if (players.size () > 0)
		{
			space = gameModel.moveToSpace (number);
			int checkNum = space.getNumber ();

			gameView.setPegs (players.get(turnCount-1));
			gameView.showDiceRoll (number, space);
                        gameView.setPegs (players.get(turnCount-1));
			if (checkNum <= 60)
			{
				if (space instanceof MagentaSpace)
				{
					MagentaSpace mSpace = (MagentaSpace) space;
					magentaSpace (mSpace, players, turnCount, paths, finalOrder);
				}
				else if (space instanceof GreenSpace)
				{
					GreenSpace gSpace = (GreenSpace) space;
					gameView.showGreenPrompt (players.get (turnCount - 1).getSalaryCard (), gSpace);
					gSpace.determineAction (players.get (turnCount - 1));
				} 
				else if (space instanceof OrangeSpace)
				{
					OrangeSpace oSpace = (OrangeSpace) space;
					orangeSpace (oSpace, players, turnCount);
				}
				else
				{
					BlueSpace bSpace = (BlueSpace) space;
					BlueCard card = bSpace.determineAction (players, turnCount - 1);
					int match = card.determineAction (players, turnCount - 1);
					
					gameView.showBlueCard (card);
					gameView.showBluePrompt (card, match, turnCount - 1);
				}
			}
		}

		return space;
  }

	@Override
	/** determines what to do when a player clicks a button in the view.
		* @param e is the ActionEvent that happened in the view. 
		*/
	public void actionPerformed (ActionEvent e)
	{
		if (e.getActionCommand ().equals ("Roll Dice"))
		{
			gameView.setRollDiceEnabled (false);
			try
			{
				gameView.playButtonSound ();
			} 
			catch (Exception rollDiceSound)
			{

			}
			
			Space space = currentTurn ();
			Player winner;

			if (!(space instanceof MagentaSpace) || space.getNumber () == 60)
				gameModel.nextTurn ();

			if (gameModel.getPlayers ().size () != 0)
			{
				Player p = gameModel.getPlayers ().get (gameModel.getTurnCount () - 1);
				gameModel.updateIndex ();
				updateView (p);

				// the only button the player can click if he lands on magenta space is the roll dice button
				if (space instanceof MagentaSpace && space.getNumber () != 60)
				{
					gameView.setPayLoanEnabled (false);
					gameView.setGetLoanEnabled (false);
				}
			}
			else
			{
				winner = gameModel.determineWinner ();
				gameView.winPrompt (winner);
        gameView.changeCard ("MAIN");
				gameView.initGame ();
			}
			gameView.setRollDiceEnabled (true);
		}
		else if (e.getActionCommand ().equals ("Pay Loan"))
		{
			try
			{
				gameView.playButtonSound ();
			} 
			catch (Exception payLoanSound)
			{

			}

			boolean check, checkNull;
			int amt = -1;
			String val = null;

			do
			{
				checkNull = false;
				check = false;
				try
				{
					val = gameView.payLoanPrompt ();
					amt = Integer.parseInt (val);
					gameModel.payLoan (amt);
				}
				catch (ArithmeticException exc)
				{
					// when a player inputs a invalid amount
					String [] error = exc.toString ().split (":");
					gameView.outputError (error [1]);
					check = true;
				}
				catch (NumberFormatException excep)
				{
					// when a player inputs a String
					if (!(val == null || val.equals ("")))
					{
						gameView.outputError ("Please input a valid number without a comma.");
						check = true;
					}
					else
					{
						// when a player closes the prompt or clicks the cancel button
						checkNull = true;
					}
				}
			} while (check);

			if (!check && !checkNull)
				gameView.outputError ("Payment successful.");

			Player p = gameModel.getPlayers ().get (gameModel.getTurnCount () - 1);
			updateView (p);
		}
		else if (e.getActionCommand ().equals ("Get Loan"))
		{
			try
			{
				gameView.playButtonSound ();
			} 
			catch (Exception getLoanSound)
			{

			}

			boolean check, checkNull;
			int amt = -1;
			String val = null;
			do
			{
				check = false;
				checkNull = false;
				try
				{
					val = gameView.getLoanPrompt ();
					amt = Integer.parseInt (val);
					gameModel.borrowMoney (amt);
				}
				catch (ArithmeticException exc)
				{
					// when a player inputs a invalid amount
					String [] error = exc.toString ().split (":");
					gameView.outputError (error [1]);
					check = true;
				}
				catch (NumberFormatException excep)
				{
					// when a player inputs a String
					if (!(val == null || val.equals ("")))
					{
						gameView.outputError ("Please input a valid number without a comma.");
						check = true;
					}
					else
					{
						// when a player closes the prompt or clicks the cancel button
						checkNull = true;
					}
				}
			} while (check);

			if (!check && !checkNull)
				gameView.outputError ("Loan successful.");

			Player p = gameModel.getPlayers ().get (gameModel.getTurnCount () - 1);
			updateView (p);
		}
		else if (e.getActionCommand ().equals ("Career Card"))
		{
			try
			{
				gameView.playButtonSound ();
			} 
			catch (Exception careerSound)
			{

			}
			
			Player p = gameModel.getPlayers ().get (gameModel.getTurnCount () - 1);
			gameView.showCareerCard (p.getCareerCard ());
		}
		else if (e.getActionCommand ().equals ("Salary Card"))
		{
			try
			{
				gameView.playButtonSound ();
			} 
			catch (Exception salarySound)
			{

			}

			Player p = gameModel.getPlayers ().get (gameModel.getTurnCount () - 1);
			gameView.showSalaryCard (p.getSalaryCard ());
		}
		else if (e.getActionCommand ().equals ("House Card"))
		{
			try
			{
				gameView.playButtonSound ();
			} 
			catch (Exception houseSound)
			{

			}

			Player p = gameModel.getPlayers ().get (gameModel.getTurnCount () - 1);
			gameView.showHouseCard (p.getHouseCard ());
		}
		else if (e.getActionCommand ().equals ("Milestones"))
		{
			try
			{
				gameView.playButtonSound ();
			} 
			catch (Exception mileSound)
			{

			}
		
			Player p = gameModel.getPlayers ().get (gameModel.getTurnCount () - 1);
			gameView.showMilestone (p);
		}
	}
}