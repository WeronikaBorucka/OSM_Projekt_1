import java.util.ArrayList;

public class ModelLista
{
	// utworzenie listy przechowuj¹cej pacjentów - MODEL
	private ArrayList<Pacjent> lista = null;

	
	// KONSTRUKTOR
	
	public ModelLista()
	{
		this.lista = new ArrayList<Pacjent>();
	}
	

	//METODY
	
	// pobieranie prywatnej listy wartoœci (pacjentów)
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

	//dodanie wartoœci do listy w modelu
	public boolean addPacjent(Pacjent p, int r)
	{
		//1 przypadek - jeœli ¿aden rz¹d nie jest wybrany
		if (r == -1)
		{
			for (Pacjent pacjent : lista)
			{
				//sprawdzenie czy istnieje osoba o identycznym nr PESEL
				if (p.getPesel().equals(pacjent.getPesel())) //equals - bo stringów nie mo¿na porównaæ ==
				{
					return false;
				}
			}
			//sprawdzenie czy istnieje identyczny pacjent na liœcie
			if (!this.lista.contains(p))
				return (this.lista.add(p));
			return (false);
		}
		//2 przypadek - jeœli chcê zmieniæ dane pacjenta z wybranego rzêdu + pobranie jego badania i przypisanie go
		Pacjent p2 = lista.get(r);
		p.setBadanie(p2.getBadanie()); //przypisanie badania starego pacjenta
		lista.remove(r);
		lista.add(r, p);
		return true;
	}

	//usuwanie wartoœci (pacjenta z rzêdu r) z listy w modelu
	public boolean removeValue(int r)
	{

		if (r == -1)
		{
			return false;
		}
		this.lista.remove(r);
		return true;

	}

	//przypisanie badania do ju¿ istniej¹cego pacjenta
	public boolean przypiszBadanie(Badanie b, int r)
	{
		lista.get(r).setBadanie(b);
		return true;
	}
}
