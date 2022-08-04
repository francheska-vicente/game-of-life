/**
	* <p> Green Space holds the methods that would implement the needed function of a green space. 
	* It allows a player to experience pay day or pay raise.</p>
	*/
public class GreenSpace extends Space 
{
	private String type;

	/** creates a green space given the number and the typ of green space. It passes the number assigned to it to its superclass Space.
		*
		* @param number is the number assigned to the space (1-60)
		* @param type is the type of green card
		*/
	public GreenSpace (int number, String type)
	{
		super (number);
		this.type = type;
	}

	/** determines if the space is a pay day space or a pay raise space
		*
		* @param p is the player that landed on the space
		*/
	public void determineAction (Player p)
  {
    if (type.equals ("Pay Day"))
      payDay (p);
    else
      payRaise (p);
  }

	/** allows a player to experience a pay day
		*
		* @param p is the player that landed on the space
		*/
  private void payDay (Player p)
  {
		SalaryCard s = p.getSalaryCard ();
    p.addCash (s.getSalary());
  }

	/** allows a player to have a pay raise if the maximum salary is not yet reached
		* @param p is the player that landed on the space
		*/
  private void payRaise (Player p)
  {
    SalaryCard s = p.getSalaryCard ();
		if (s.getSalary () != s.getMaxSalary ())
		{
			s.setSalary (s.getSalary () + s.getPayRaiseVal ());
			s.setTaxDue (s.getTaxDue () + 2000);
		}

    p.addCash (s.getSalary ());
  }

	/** returns what type a space is
		*
		* @return type of card
		*/
	public String getType ()
	{
		return type;
	}

	@Override
	/** returns a formatted string that says that the player landed on a GreenSpace
		* @return "You have landed on a Green Space."
		*/
	public String toString ()
	{
		return "You have landed on a Green Space.";
	}
}