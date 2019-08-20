import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Okno extends JFrame
{
	private static final long serialVersionUID = 1L;
	PanelPacjenta danePacjenta;
	PanelBadania badanie;
	PanelLista lista;
	JPanel tlo;
	JPanel lewy;
	JPanel prawy;
	JMenuBar pasek;
	JMenu aplikacja;
	JMenuItem zamknij;

	public Okno() // po co tutaj publiczny skoro potem s¹ gettery i settery?
	{
		// USTAWIAMY NAZWÊ OKNA I JEGO ROZMAR
		super("Rejestracja wyników badañ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 1000);
		setLocation(20, 20);
		setVisible(true);
		setLayout(new BorderLayout());

		/* USTAWIAMY MENU I DODAJEMY DO RAMKI */
		pasek = new JMenuBar();
		setJMenuBar(pasek);
		pasek.setVisible(true);
		aplikacja = new JMenu("Aplikacja");
		pasek.add(aplikacja);
		aplikacja.setSize(100, 20);
		aplikacja.setVisible(true);
		aplikacja.setMnemonic(KeyEvent.VK_A);

		/* DODAJEMY OPCJÊ ZAMKNIJ DO PASKA */
		zamknij = new JMenuItem("Zamknij Alt + F4");
		zamknij.setMnemonic(KeyEvent.VK_Z);
		aplikacja.add(zamknij);

		/* T£O */
		danePacjenta = new PanelPacjenta(this);
		badanie = new PanelBadania();
		lista = new PanelLista();
		tlo = new JPanel();
		lewy = new JPanel();
		prawy = new JPanel();
		lewy.setVisible(true);
		prawy.setVisible(true);
		lewy.setBackground(Color.RED);
		prawy.setBackground(Color.BLUE);

		add(tlo);
		tlo.setVisible(true);
		tlo.setLayout(new GridLayout(1, 2));
		tlo.setBackground(Color.white);
		tlo.add(lewy);
		tlo.add(prawy);
		lewy.setLayout(new GridLayout(2, 1));
		lewy.add(danePacjenta);
		danePacjenta.setEnabled(false);
		lewy.add(badanie);
		prawy.setLayout(new GridLayout(1, 1));
		prawy.add(lista);

		widocznoscBadania(false);
		widocznoscPacjenta(false);
	}

	public void widocznoscPacjenta(boolean b)
	{
		for (Component c : danePacjenta.getComponents())
		{
			c.setEnabled(b);
		}
	}

	public void widocznoscBadania(boolean b)
	{
		for (Component c : badanie.getComponents())
		{
			c.setEnabled(b);
		}
	}

	public PanelPacjenta getPacjent()
	{
		return danePacjenta;
	}

	public PanelBadania getBadanie()
	{
		return badanie;
	}

	public PanelLista getLista()
	{
		return lista;
	}

	public void setKontroler(ActionListener kontroler)
	{
		this.zamknij.addActionListener(kontroler);
	}

}