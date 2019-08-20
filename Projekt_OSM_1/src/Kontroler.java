import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.JOptionPane;

public class Kontroler implements ActionListener, FocusListener
{
	private Okno okno = null;
	private ModelLista model = null;

	// KONSTRUKTOR
	
	public Kontroler(Okno o, ModelLista m) // podpiêcie widoku i modelu
	{
		okno = o;
		model = m;
		okno.lista.setModelLista(model);
	}

	//METODY
		
	//dodaje pacjenta do modelu
	public boolean addPacjent(Pacjent p)
	{
		int row = okno.lista.selectedRow();
		boolean ret = model.addPacjent(p, row);
		okno.lista.odswiezJTable();
		if (ret) // zaznacza sie tylko kiedy pacjent sie dodal (ret == 1)
			okno.lista.selectRow(model.getValues().size() - 1);
		return ret;
	}

	//usuwa pacjenta z modelu
	public boolean removePacjent()
	{
		int row = okno.lista.selectedRow();
		boolean ret = model.removeValue(row);
		okno.lista.odswiezJTable();
		return ret;
	}

	//dodawanie badania do pacjenta do modelu
	public boolean addBadanie(Badanie b)
	{
		int row = okno.lista.selectedRow();
		if (row == -1)
			return false;
		boolean ret = model.przypiszBadanie(b, row);
		okno.lista.odswiezJTable();
		okno.lista.selectRow(model.getValues().size() - 1);
		return ret;
	}

	//wyœwietlanie danych pacjenta i jego badania z wybranego rzêdu z paneluListy
	public void wyswietlDane(int row)
	{
		Pacjent p = model.getPacjent(row);
		okno.danePacjenta.ustawPacjenta(p);
		zmienWidocznoscPacjenta(true);
		if (p.isBadanie())
		{
			Badanie b = p.getBadanie();
			okno.badanie.ustawBadanie(b);
		}
		else
		{
			okno.badanie.wyczyscBadanie();
		}
	}

	//zmiana aktywnoœci okna Pacjenta
	public void zmienWidocznoscPacjenta(boolean b)
	{
		okno.widocznoscPacjenta(b);
	}

	//zmiana aktywnoœci okna Badania
	public void zmienWidocznoscBadania(boolean b)
	{
		okno.widocznoscBadania(b);
	}
	
	//sprawdzanie, czy dany string sk³ada siê tylko z liter
	public boolean czySlowo(String zdanie)
	{
		int dlugosc = zdanie.length();
		boolean jest = true;
		int licznik = 0;
		while(jest == true && licznik < dlugosc)
		{
			jest = Character.isLetter(zdanie.charAt(licznik));
			licznik ++;
			
			if(jest == false)
				return false;
		}
		return true;
	}
	
	//sprawdzanie czy dany string sk³ada siê tylko z cyfr
	public boolean czyNumer(String numer)
	{
		int dlugosc = numer.length();
		boolean jest = true;
		int licznik = 0;
		while(jest == true && licznik < dlugosc)
		{
			jest = Character.isDigit(numer.charAt(licznik));
			licznik ++;
			
			if(jest == false)
				return false;
		}
		return true;
	}

	// g³ówna funkcja, zbieraj¹ca dane z pól tekstowych, sprawdzaj¹ca poprawnoœæ
	// wpisanych danych i dodaj¹ca pacjenta
	private boolean panelPacjentZapisz()
	{
		String imie = okno.danePacjenta.getWpisaneImie();
		String nazwisko = okno.danePacjenta.getWpisaneNazwisko();
		String pesel = okno.danePacjenta.getWpisanyPESEL();
		boolean ret = true;
		
		//sprawdzenie poprawnoœci wprowadzonych danych
		if(czySlowo(imie) == false)
		{
			JOptionPane.showMessageDialog(okno, "Wprowadzono niepoprawne imiê");
			okno.lista.odznacz();
			ret = false;
		}
		if(czySlowo(nazwisko) == false)
		{
			JOptionPane.showMessageDialog(okno, "Wprowadzono niepoprawne nazwisko");
			okno.lista.odznacz();
			ret = false;
		}
		
		if (pesel.length() != 11)
		{
			JOptionPane.showMessageDialog(okno, "Wprowadzono niepoprawn¹ d³ugoœæ nr PESEL");
			okno.lista.odznacz();
			ret = false;
		}
		
		if(czyNumer(pesel) == false)
		{
			JOptionPane.showMessageDialog(okno, "Wprowadzono niepoprawn¹ formê nr PESEL");
			okno.lista.odznacz();
			ret = false;
		}

		
		if (ret == false) //jeœli któreœ z wprowadzonych danych oka¿¹ siê niepoprawne
			return false;
		

		Pacjent.EnumPlec plec;
		if (okno.danePacjenta.kobieta.isSelected())
			plec = Pacjent.EnumPlec.KOBIETA;
		else
			plec = Pacjent.EnumPlec.MEZCZYZNA;

		Pacjent.EnumUbezpieczenie ubezpieczenie = Pacjent.EnumUbezpieczenie
				.values()[okno.danePacjenta.listaUbezpieczenie.getSelectedIndex()];

		Pacjent p = new Pacjent(imie, nazwisko, plec, pesel, ubezpieczenie, null);

		boolean b = addPacjent(p);
		if (b == false)
		{
			JOptionPane.showMessageDialog(okno, "Pacjent o danym numerze PESEL istnieje ju¿ w bazie");
			okno.lista.odznacz();
		}
		return ret;
	}

	//dodawanie badania
	private boolean panelBadanieZapisz()
	{
		Double ggt = 0.0;
		Integer a1at = 0;
		Integer aspat = 0;
		Date d = null;
		
		Color kol1= Color.BLACK; //odpowiada za ggt
		Color kol2 = Color.BLACK; //odpowiada za a1at
		Color kol3 = Color.BLACK; //odpowiada za aspat
		boolean ret = true;
		
		zmienKolor(kol1, kol2, kol3);

		//obs³uga wyj¹tków
		try {ggt = Double.parseDouble(okno.badanie.getWpisaneGGT());} // konwersja pobranego stringa do double}
		catch (NumberFormatException e)
		{
			kol1 = Color.RED;
			ret = false;
		}
		
		try {a1at = Integer.parseInt(okno.badanie.getWpisaneA1AT());}
		catch (NumberFormatException e)
		{
			kol2 = Color.RED;
			ret = false;
		}
		
		try {aspat = Integer.parseInt(okno.badanie.getWpisaneAspAT());}
		catch (NumberFormatException e)
		{
			kol3 = Color.RED;
			ret = false;
		}
		
		if (ret == false) //jeœli z³apa³ siê któryœ z wyj¹tków dot. formatu
		{
			zmienKolor(kol1, kol2, kol3);
			JOptionPane.showMessageDialog(okno, "Podano z³y format jednego lub kilku stê¿eñ");
		}
		
		
		try {d = okno.badanie.kalendarz.getCalendar().getTime();}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(okno, "Podano z³¹ datê");
			ret = false;
		}

		if ((ggt < 0 || ggt > 100) || (a1at < 0 || a1at > 100) || (aspat < 0 || aspat > 100))
		{
			
			if((ggt < 0 || ggt > 100))
				kol1 = Color.RED;
			if((a1at < 0 || a1at > 100))
				kol2 = Color.RED;
			if((aspat < 0 || aspat > 100))
				kol3 = Color.RED;
			zmienKolor(kol1, kol2, kol3);
			ret = false;
			
			JOptionPane.showMessageDialog(okno,
					"Podane wartoœci stê¿enia A1AT, AspAT lub GGT wykraczaj¹ poza zakres normy");
		}
		
		if(ret == false) //jeœli któreœ z wprowadzonych danych oka¿¹ siê niepoprawne
			return false;
		
		Badanie b = new Badanie(ggt, a1at, aspat, d);
		zmienKolor(Color.BLACK, Color.BLACK, Color.BLACK);
		addBadanie(b);
		return true;
	}
	
	//funkcja zmieniaj¹ca kolor 3 JTextFieldów z paneluBadania
	private void zmienKolor(Color ggt, Color a1at, Color aspat)
	{
		okno.badanie.zmienKolorGGT(ggt);
		okno.badanie.zmienKolorA1AT(a1at);
		okno.badanie.zmienKolorAspAT(aspat);
	}

	
	// PRZYCISKI - OBS£UGA
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// PANEL PACJENTA
		if (e.getSource() == okno.zamknij)
		{
			System.exit(0);
		}
		if (e.getSource() == okno.danePacjenta.getZapisz())
		{
			if (panelPacjentZapisz() == false)
			{
				zmienWidocznoscPacjenta(true);
				zmienWidocznoscBadania(false);
			} else
			{
				zmienWidocznoscPacjenta(false);
				zmienWidocznoscBadania(true);
			}
		}
		if (e.getSource() == okno.danePacjenta.getAnuluj())
		{
			okno.danePacjenta.setWpisaneImie("");
			okno.danePacjenta.setWpisaneNazwisko("");
			okno.danePacjenta.setWpisanyPESEL("");
			JOptionPane.showMessageDialog(okno, "ANULUJ");
		}
		// PANEL BADANIA
		if (e.getSource() == okno.badanie.zapisz)
		{
			if (panelBadanieZapisz() == true)
			{
				zmienWidocznoscPacjenta(false);
				zmienWidocznoscBadania(false);
			}
		}
		if (e.getSource() == okno.badanie.anuluj)
		{
			zmienKolor(Color.BLACK, Color.BLACK, Color.BLACK);
			zmienWidocznoscBadania(false);
			JOptionPane.showMessageDialog(okno, "ANULUJ");
		}

		// PANEL LISTA
		if (e.getSource() == okno.lista.getmAddButton())
		{
			okno.badanie.wyczyscBadanie();
			okno.danePacjenta.wyczyscPacjenta();
			zmienWidocznoscPacjenta(true);
			zmienWidocznoscBadania(false);
			okno.lista.odznacz();
		}
		if (e.getSource() == okno.lista.getmRemoveButton())
		{
			zmienWidocznoscPacjenta(false);
			zmienWidocznoscBadania(false);
			model.removeValue(okno.lista.selectedRow());
			okno.lista.odswiezJTable();
			okno.lista.odznacz();
		}

	}

	// POLA TEKSTOWE - OBS£UGA
	@Override
	public void focusGained(FocusEvent e)
	{
		// PANEL PACJENTA
		if (e.getSource() == okno.danePacjenta.wpisaneImie)
		{
			okno.danePacjenta.setWpisaneImie("");
		}
		if (e.getSource() == okno.danePacjenta.wpisaneNazwisko)
		{
			okno.danePacjenta.setWpisaneNazwisko("");
		}
		if (e.getSource() == okno.danePacjenta.wpisanyPESEL)
		{
			okno.danePacjenta.setWpisanyPESEL("");
		}

		// PANEL BADANIA
		if (e.getSource() == okno.badanie.wpisaneGGT)
		{
			okno.badanie.setWpisaneGGT("");
		}
		if (e.getSource() == okno.badanie.wpisaneA1AT)
		{
			okno.badanie.setWpisaneA1AT("");
		}
		if (e.getSource() == okno.badanie.wpisaneAspAT)
		{
			okno.badanie.setWpisaneAspAT("");
		}

	}

	@Override
	public void focusLost(FocusEvent e)
	{
		// TODO Auto-generated method stub

	}
}
