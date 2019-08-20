import java.util.Date;

public class Badanie
{

	double GGT;
	int A1AT;
	int AspAT;
	Date data;

	//KONTRUKTOR
	
	public Badanie(double g, int a1, int a2, Date d)
	{
		this.GGT = g;
		this.A1AT = a1;
		this.AspAT = a2;
		this.data = d;
	}

	//METODY
	
	//gettery i settery
	public Date getData()
	{
		return data;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public double getGGT()
	{
		return GGT;
	}

	public void setGGT(double gGT)
	{
		GGT = gGT;
	}

	public int getA1AT()
	{
		return A1AT;
	}

	public void setA1AT(int a1at)
	{
		A1AT = a1at;
	}

	public int getAspAT()
	{
		return AspAT;
	}

	public void setAspAT(int aspAT)
	{
		AspAT = aspAT;
	}

	//automatycznie wygenerowane metody hashcode i equals
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + A1AT;
		result = prime * result + AspAT;
		long temp;
		temp = Double.doubleToLongBits(GGT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Badanie other = (Badanie) obj;
		if (A1AT != other.A1AT)
			return false;
		if (AspAT != other.AspAT)
			return false;
		if (Double.doubleToLongBits(GGT) != Double.doubleToLongBits(other.GGT))
			return false;
		return true;
	}

}
