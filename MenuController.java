import java.awt.event.*;
import javax.swing.event.*;

/**
	* <p> MenuController is the ActionListener for the View that is responsible for the Main Menu. It manages the interaction between the View and the Model as it passes the needed prompt to the view and passes back the user's input to the Model. </p>
	*/
public class MenuController implements ActionListener
{
	private Game gameModel;
	private GameView gameView;

	/** creates the instance of the MenuController . 
		* @param gameModel is where the controller passes the inputs to.
		* @param gameView is the view that the controller manages.
		*/
	public MenuController (Game gameModel, GameView gameView)
	{
		this.gameModel = gameModel;
		this.gameView = gameView;
        
    gameView.setMenuListener (this);
	}

	@Override
	/** determines what to do when a player clicks a button in the view.
		* @param e is the ActionEvent that happened in the view. 
		*/
	public void actionPerformed (ActionEvent e)
	{
		if (e.getActionCommand ().equals ("START GAME"))
		{
			try
			{
				gameView.playButtonSound ();
			}
			catch (Exception startGameSoundExce)
			{
			} 
			
			int num = gameView.askPlayerNum ();
      if (num == 0)
        gameView.changeCard("MAIN");
			else 
			{
					gameModel.createPlayers(num);

					String name = null;
					boolean checker = true, check = true;

          for (int j = 1; j <= num && checker && check; j++)
          {
						try
						{
							do
							{
								name = gameView.askPlayerName(j);

								if (name.equals (""))
									gameView.showErrorName ();
							} while (name.equals (""));
						}
            catch (NullPointerException exc)
						{
							gameView.changeCard("MAIN");
							checker = false;
						}
						
						if (checker)
						{
							int path = gameView.askPlayerPath();

							if (path == -1)
							{
								gameView.changeCard("MAIN");
								check = false;
							}
							else
							{
								gameModel.addPlayer (name, path);

								if (path == 1)
								{
									gameView.showCollegePath ();
								}	
								else
								{
									Player p = gameModel.getPlayers ().get (gameModel.getTurnCount () - 1);
									gameView.showCareerCard (p.getCareerCard ());
									gameView.showSalaryCard (p.getSalaryCard ());
								}
							}
						}
					}
					
					if (checker && check)
					{
						gameModel.nextTurn ();
						Player p = gameModel.getPlayers ().get (gameModel.getTurnCount () - 1);
												
						if (gameModel.getPlayers().size() == 2)
							gameView.setPeg3Enabled(false);
						else
            	gameView.setPeg3Enabled(true);

						gameView.setNameTxt(p.getName());
						gameView.setCashTxt("Cash: $" + Integer.toString(p.getCash()));
						gameView.setLoanTxt("Loan: $" + Integer.toString(p.getLoan()));
						gameView.setPathTxt(p.getPath().toString());

						if (p.getCareerCard () == null)
						{
							gameView.setCareerCardEnabled (false);
							gameView.setSalaryCardEnabled (false);
						}
						else
						{
							gameView.setPayLoanEnabled (false);
						}

						gameView.setHouseCardEnabled (false);
					
						gameView.changeCard("GAME");
					}					
			}
		}
		else if (e.getActionCommand ().equals ("INSTRUCTIONS"))
		{
			try
			{
				gameView.playButtonSound ();
			}
			catch (Exception showInstrucSoundExce)
			{
			} 

			gameView.showInstructions ();
		}
		else if (e.getActionCommand ().equals ("EXIT GAME"))
		{
			try
			{
				gameView.playButtonSound ();
			}
			catch (Exception exitSoundExce)
			{
			} 

			System.exit (0);
		}
	}
}