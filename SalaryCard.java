/**
	* <p> SalaryCard holds the information of the player's salary. This includes the salary, tax due, pay raise value and the maximum salary of the player. </p>
	*/
public class SalaryCard
{
	private int salary;
	private int taxDue;
  private int payRaiseVal;
	private int maximumSalary;	

	/** creates the salary. This method also generates the amount for the salary and the tax due. 
		* It also computes for the pay raise value of the indicated salary.
		*/
  public SalaryCard()
  {
      salary = Game.randomNumberGenerator(1, 8) * 10000;
      taxDue = (int) (salary * 0.10);
			double val = salary * 0.2;
			payRaiseVal = (int) Math.round(val);
  }

	/** returns the salary indicated in the card
		* @return salary amount
		*/
	public int getSalary ()
	{
		return salary;
	}

	/** returns the tax due indicateed in the card
		* @return tax due amount
		*/
	public int getTaxDue ()
	{
		return taxDue;
	}

	/** retus the maximum salary computed
		* @return the maximum salary amount
		*/
	public int getMaxSalary ()
	{
		return maximumSalary;
	}

	/** returns the value of the pay raise
		* @return pay raise value
		*/
	public int getPayRaiseVal ()
	{
		return payRaiseVal;
	}

	/** sets the salary to the given amount
		* @param amt is the new value of the salary
		*/
	public void setSalary (int amt)
	{
		salary = amt;
	}

	/** sets the tax due to the given amount
		* @param amt is the new value of the salary
		*/
	public void setTaxDue (int amt)
	{
		taxDue = amt;
	}

	/** sets the maximum salary of the player using the pay raise limit in the career card, pay raise value and the salary.
		* @param c is the career card of the player
		*/
	public void setMaximumSalary (CareerCard c)
	{
		maximumSalary = c.getPayRaiseLimit () * payRaiseVal + salary;
	}

	@Override
	/** returns a formatted string that holds the information in the salary card
		* @return a string in the format: "Salary: " + salary + "\nTax Due: " + taxDue + "\nMaximum Salary: " + maximumSalary
		*/
	public String toString ()
	{
		return "Salary: " + salary + "\nTax Due: " + taxDue + 
					 "\nMaximum Salary: " + maximumSalary + "\n"; 
	}
}