
public class Pacjent
{
	private String imie;
	private String nazwisko;
	private String pesel;
	private Badanie badanie;

	public enum EnumPlec
	{
		KOBIETA, MEZCZYZNA;
	}

	private EnumPlec plec;

	public enum EnumUbezpieczenie
	{
		NFZ, PRYWATNE, BRAK;
	}

	private EnumUbezpieczenie ubezpieczenie;

	// KONSTRUKTOR
	
	public Pacjent(String i, String n, EnumPlec p1, String p2, EnumUbezpieczenie u, Badanie b)
	{
		this.imie = i;
		this.nazwisko = n;
		this.plec = p1;
		this.pesel = p2;
		this.ubezpieczenie = u;
		this.badanie = b;
	}

	
	//METODY
	
	//gettery i settery
	public String getImie()
	{
		return imie;
	}

	public void setImie(String imie)
	{
		this.imie = imie;
	}

	public String getNazwisko()
	{
		return nazwisko;
	}

	public void setNazwisko(String nazwisko)
	{
		this.nazwisko = nazwisko;
	}

	public String getPesel()
	{
		return pesel;
	}

	public void setPesel(String pesel)
	{
		this.pesel = pesel;
	}

	public void setBadanie(Badanie badanie)
	{
		this.badanie = badanie;
	}

	public Badanie getBadanie()
	{
		return badanie;
	}

	public EnumPlec getPlec()
	{
		return plec;
	}

	public void setPlec(EnumPlec plec)
	{
		this.plec = plec;
	}

	public EnumUbezpieczenie getUbezpieczenie()
	{
		return ubezpieczenie;
	}

	public void setUbezpieczenie(EnumUbezpieczenie ubezpieczenie)
	{
		this.ubezpieczenie = ubezpieczenie;
	}
	
	
	//sprawdza czy badanie istnieje
	public boolean isBadanie()
	{
		if (badanie == null)
			return false;
		return true;
	}
	
	//automatycznie wygenerowane metody hashcode i equals
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((badanie == null) ? 0 : badanie.hashCode());
		result = prime * result + ((imie == null) ? 0 : imie.hashCode());
		result = prime * result + ((nazwisko == null) ? 0 : nazwisko.hashCode());
		result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
		result = prime * result + ((plec == null) ? 0 : plec.hashCode());
		result = prime * result + ((ubezpieczenie == null) ? 0 : ubezpieczenie.hashCode());
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
		Pacjent other = (Pacjent) obj;
		if (badanie == null)
		{
			if (other.badanie != null)
				return false;
		} else if (!badanie.equals(other.badanie))
			return false;
		if (imie == null)
		{
			if (other.imie != null)
				return false;
		} else if (!imie.equals(other.imie))
			return false;
		if (nazwisko == null)
		{
			if (other.nazwisko != null)
				return false;
		} else if (!nazwisko.equals(other.nazwisko))
			return false;
		if (pesel == null)
		{
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		if (plec != other.plec)
			return false;
		if (ubezpieczenie != other.ubezpieczenie)
			return false;
		return true;
	}

}
