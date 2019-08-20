import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PanelPacjenta extends JPanel
{
	JLabel imie;
	JTextField wpisaneImie;

	JLabel nazwisko;
	JTextField wpisaneNazwisko;

	JLabel PESEL;
	JTextField wpisanyPESEL;

	JLabel plec;
	ButtonGroup plcie;
	JRadioButton mezczyzna;
	JRadioButton kobieta;

	JLabel ubezpieczenie;
	JComboBox listaUbezpieczenie;

	JButton zapisz;
	JButton anuluj;
	Okno biezaceokno;

	public PanelPacjenta(Okno okno)
	{

		this.biezaceokno = okno;

		this.setBorder(BorderFactory.createTitledBorder("Dane Pacjenta"));
		this.setVisible(true);

		// POLE IMIÊ
		imie = new JLabel("Imiê");
		wpisaneImie = new JTextField("Podaj imiê", 50);

		// NAZWISKO
		nazwisko = new JLabel("Nazwisko");
		wpisaneNazwisko = new JTextField("Podaj nazwisko", 50);

		// PESEL
		PESEL = new JLabel("PESEL");
		wpisanyPESEL = new JTextField("Podaj numer PESEL", 11);

		// P£EÆ
		plec = new JLabel("P³eæ");
		plcie = new ButtonGroup();
		mezczyzna = new JRadioButton("Mê¿czyzna", false);
		kobieta = new JRadioButton("Kobieta", false);
		plcie.add(kobieta);
		plcie.add(mezczyzna);

		// UBEZPIECZENIE
		ubezpieczenie = new JLabel("Ubezpieczenie");
		listaUbezpieczenie = new JComboBox();
		listaUbezpieczenie.addItem("NFZ");
		listaUbezpieczenie.addItem("Prywatne");
		listaUbezpieczenie.addItem("Brak");

		// PRZYCISKI
		zapisz = new JButton("Zapisz");
		anuluj = new JButton("Anuluj");

		// USTAWIAMY U£O¯ENIE
		GroupLayout grupa = new GroupLayout(this);
		this.setLayout(grupa);
		grupa.setAutoCreateGaps(true);
		grupa.setAutoCreateContainerGaps(true);

		
		
		// USTAWIENIE W PIONIE

		grupa.setHorizontalGroup(grupa.createSequentialGroup()
				
				// PIERWSZA KOLUMNA
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(imie)
						.addComponent(nazwisko)
						.addComponent(PESEL)
						.addComponent(plec)
						.addComponent(ubezpieczenie)
						.addGroup(grupa.createSequentialGroup()
								.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(zapisz))
								.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(anuluj))))

				// DRUGA KOLUMNA
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(wpisaneImie)
						.addComponent(wpisaneNazwisko)
						.addComponent(wpisanyPESEL)
						.addGroup(grupa.createSequentialGroup()
								.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(kobieta))
								.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(mezczyzna))
								.addComponent(listaUbezpieczenie))));

		//USTAWIENIE W POZIOMIE

		grupa.setVerticalGroup(grupa.createSequentialGroup()
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(80)
						.addComponent(imie)
						.addComponent(wpisaneImie))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(80)
						.addComponent(nazwisko)
						.addComponent(wpisaneNazwisko))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(80)
						.addComponent(PESEL)
						.addComponent(wpisanyPESEL))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(80)
						.addComponent(plec)
						.addComponent(kobieta)
						.addComponent(mezczyzna))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(80)
						.addComponent(ubezpieczenie)
						.addComponent(listaUbezpieczenie))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(zapisz)
						.addComponent(anuluj)));

	}

	
	//METODY
	
	//Ustawienie kontorlera - dodanie s³uchaczy
	public void setKontroler(ActionListener kontroler, FocusListener l)
	{
		this.wpisaneImie.addFocusListener(l);
		this.wpisaneNazwisko.addFocusListener(l);
		this.wpisanyPESEL.addFocusListener(l);

		this.zapisz.addActionListener(kontroler);
		this.anuluj.addActionListener(kontroler);
	}

	
	//gettery i settery do pobierania i ustawiania pól prywatnych
	
	public String getWpisaneImie()
	{
		return this.wpisaneImie.getText();
	}

	public void setWpisaneImie(String wpisaneImie)
	{
		this.wpisaneImie.setText(wpisaneImie);
	}

	public String getWpisaneNazwisko()
	{
		return this.wpisaneNazwisko.getText();
	}

	public void setWpisaneNazwisko(String wpisaneNazwisko)
	{
		this.wpisaneNazwisko.setText(wpisaneNazwisko);
	}

	public String getWpisanyPESEL()
	{
		return this.wpisanyPESEL.getText();
	}

	public void setWpisanyPESEL(String wpisanyPESEL)
	{
		this.wpisanyPESEL.setText(wpisanyPESEL);
	}
	
	public JButton getZapisz()
	{
		return zapisz;
	}

	public void setZapisz(JButton zapisz)
	{
		this.zapisz = zapisz;
	}

	public JButton getAnuluj()
	{
		return anuluj;
	}

	public void setAnuluj(JButton anuluj)
	{
		this.anuluj = anuluj;
	}



	//wyœwietla dane pacjenta wybranego (w zamyœle przy klikniêciu pacjenta z listy na paneluPacjenta)
	public void ustawPacjenta(Pacjent p)
	{
		wpisaneImie.setText(p.getImie());
		wpisaneNazwisko.setText(p.getNazwisko());
		wpisanyPESEL.setText(p.getPesel());
		if (p.getPlec() == Pacjent.EnumPlec.MEZCZYZNA)
		{
			mezczyzna.setSelected(true);
			kobieta.setSelected(false);
		} else
		{
			mezczyzna.setSelected(false);
			kobieta.setSelected(true);
		}

		int s = p.getUbezpieczenie().ordinal();
		listaUbezpieczenie.setSelectedIndex(s);
	}


	//usuwa dane pacjenta z paneluPacjenta (w zamyœle po odklikniêciu go z listy)
	public void wyczyscPacjenta()
	{
		wpisaneImie.setText("Podaj imiê");
		wpisaneNazwisko.setText("Podaj nazwisko");
		wpisanyPESEL.setText("Podaj numer PESEL");
		kobieta.setSelected(true);
		listaUbezpieczenie.setSelectedIndex(1);
	}
	
}