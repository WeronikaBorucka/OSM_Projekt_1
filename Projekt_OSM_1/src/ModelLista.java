import java.util.ArrayList;

public class ModelLista
{
	// utworzenie listy przechowuj�cej pacjent�w - MODEL
	private ArrayList<Pacjent> lista = null;

	
	// KONSTRUKTOR
	
	public ModelLista()
	{
		this.lista = new ArrayList<Pacjent>();
	}
	

	//METODY
	
	// pobieranie prywatnej listy warto�ci (pacjent�w)
	public ArrayList<Pacjent> getValues()
	{
		return (this.lista);
	}

	//pobranie pojedynczego pacjenta z listy
	public Pacjent getPacjent(int index)
	{
		Pacjent p = this.lista.get(index);
		return p;
	}

	//dodanie warto�ci do listy w modelu
	public boolean addPacjent(Pacjent p, int r)
	{
		//1 przypadek - je�li �aden rz�d nie jest wybrany
		if (r == -1)
		{
			for (Pacjent pacjent : lista)
			{
				//sprawdzenie czy istnieje osoba o identycznym nr PESEL
				if (p.getPesel().equals(pacjent.getPesel())) //equals - bo string�w nie mo�na por�wna� ==
				{
					return false;
				}
			}
			//sprawdzenie czy istnieje identyczny pacjent na li�cie
			if (!this.lista.contains(p))
				return (this.lista.add(p));
			return (false);
		}
		//2 przypadek - je�li chc� zmieni� dane pacjenta z wybranego rz�du + pobranie jego badania i przypisanie go
		Pacjent p2 = lista.get(r);
		p.setBadanie(p2.getBadanie()); //przypisanie badania starego pacjenta
		lista.remove(r);
		lista.add(r, p);
		return true;
	}

	//usuwanie warto�ci (pacjenta z rz�du r) z listy w modelu
	public boolean removeValue(int r)
	{

		if (r == -1)
		{
			return false;
		}
		this.lista.remove(r);
		return true;

	}

	//przypisanie badania do ju� istniej�cego pacjenta
	public boolean przypiszBadanie(Badanie b, int r)
	{
		lista.get(r).setBadanie(b);
		return true;
	}
}
