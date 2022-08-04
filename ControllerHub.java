/**
	* <p> ControllerHub holds the two controller, the view, and the model of the game. It creates the two controllers and passes on the view and the model.</p>
	*/
public class ControllerHub
{
	private GameController gameController;
	private MenuController menuController;
	private GameView gameView;
	private Game gameModel;

	/** creates the ControllerHub object. It also instantiate the two controllers of the game and passes the respective model and view. 
		*
		* @param view handles the graphics of the game
		* @param model handles the model of the game
		*/
	public ControllerHub (Game model, GameView view)
	{
		gameModel = model;
		gameView = view;

		gameController = new GameController (gameModel, gameView);
		menuController = new MenuController (gameModel, gameView);
	}
}