import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class PanelLista extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JTable mTable = null; // tworzy fizycznie tabel� wy�wietlan�
	private JButton mAddButton = null, mRemoveButton = null;
	private Kontroler mKontroler;

	// KLASA POMOCNICZA - MODEL TABLICY (do wsp�pracy z JTable)
	public class MyTableModel extends AbstractTableModel
	{
		private String[] nazwyKolumn = new String[] { "Imi� i Nazwisko", "P�e�", "PESEL", "Ubezpieczenie", "Badanie" };
		private static final long serialVersionUID = 1L;

		// METODY DO POMOCNICZEGO MODELU
		
		//ustawienie nazw kolumn
		@Override
		public String getColumnName(int col)
		{
			return nazwyKolumn[col];
		}

		//wy�wietlanie danych osobowych pacjent�w
		@Override
		public Object getValueAt(int rowI, int columnI)
		{
			Pacjent pacjent = listaPacjentow.get(rowI);
			switch (columnI)
			{
			case 0:
				return pacjent.getImie() + " " + pacjent.getNazwisko();
			case 1:
				if (pacjent.getPlec() == Pacjent.EnumPlec.KOBIETA)
				{
					return "K";
				}
				else
				{
					return "M";
				}
			case 2:
				return pacjent.getPesel();
			case 3:
				return pacjent.getUbezpieczenie();
			case 4:
				return pacjent.isBadanie();
			}

			return "";

		}

		//ustawienie typu ostatniej kolumny na boolean - do wy�wietlania check'a
		@Override
		public Class getColumnClass(int col)
		{
			if (col == 4)
			{
				return Boolean.class;
			}
			return String.class;
		}

		//pobiera liczb� pacjent�w w li�cie
		@Override
		public int getRowCount()
		{
			return listaPacjentow.size();
		}

		//ustawienie liczby kolumn
		@Override
		public int getColumnCount()
		{
			return 5;
		}

		//dodaje pacjenta do listy
		public void add(Pacjent pacjent)
		{
			int rozmiar = getSize().height;
			listaPacjentow.add(pacjent);
			fireTableRowsInserted(rozmiar, rozmiar); //wy�apuje dodane rz�dy
		}

		//usuwa pacjenta z listy
		public void remove(Pacjent pacjent)
		{
			if (listaPacjentow.contains(pacjent) == true)
			{
				int index = listaPacjentow.indexOf(pacjent);
				listaPacjentow.remove(pacjent);
				fireTableRowsDeleted(index, index);
			}
		}

		//UTWORZENIE LISTY ZAWIERAJ�CEJ PACJENT�W
		private List<Pacjent> listaPacjentow;

		// KONSTRUKTOR MODELU POMOCNICZEGO
		public MyTableModel(List<Pacjent> listaPacjentow)
		{
			this.listaPacjentow = listaPacjentow;
		}
	}

	
	// KONSTRUKTOR
	public PanelLista()
	{
		this.toGui();
	}

	
	
	// METODY

	//wybranie rz�du w mTable
	public void selectRow(int row)
	{
		mTable.setRowSelectionInterval(row, row);
	}

	//pobranie numeru wybranego rz�du z mTable
	public int selectedRow()
	{
		return mTable.getSelectedRow();
	}
	
	// metoda rejestruj�ca kontroler
	public void setKontroler(Kontroler c)
	{
		mKontroler = c;
		this.mAddButton.addActionListener(c); // niejawne rzutowanie klasy
												// Kontroler na ActionListener
		this.mRemoveButton.addActionListener(c);
		this.mTable.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (mTable.rowAtPoint(e.getPoint()) == -1) // sprawdza na jakim wierszu zosta�o klini�te
				{
					mTable.clearSelection(); // odklika zaznaczone wiersze
					mKontroler.zmienWidocznoscPacjenta(false);
					mKontroler.zmienWidocznoscBadania(false);
				}

			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}
		});

		mTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent event)
			{
				if (mTable.getSelectedRow() > -1)
				{
					// print first column value from selected row
					System.out.println(mTable.getValueAt(mTable.getSelectedRow(), 0).toString());
					mKontroler.wyswietlDane(mTable.getSelectedRow());

				}
			}
		});
	}
	
	//odznacza zaznaczenie z PanelLista
	public void odznacz()
	{
		mTable.clearSelection();
	}

	//podpina model listy do pomocniczego modelu z klasy PanelLista
	/**
	 * @param model model listy pomocniczej
	 */
	public void setModelLista(ModelLista model)
	{
		MyTableModel tableModel = new MyTableModel(model.getValues());
		mTable.setModel(tableModel);
	}
	
	/**
	 * od�wie�a widok
	 */
	public void odswiezJTable()
	{
		((AbstractTableModel) mTable.getModel()).fireTableDataChanged();
	}

	//gettery i settery
	
	
	public JButton getmAddButton()
	{
		return mAddButton;
	}

	public void setmAddButton(JButton mAddButton)
	{
		this.mAddButton = mAddButton;
	}

	public JButton getmRemoveButton()
	{
		return mRemoveButton;
	}

	public void setmRemoveButton(JButton mRemoveButton)
	{
		this.mRemoveButton = mRemoveButton;
	}

	// TWORZENIE WIDOKU W GUI
	private void toGui()
	{

		this.setBorder(BorderFactory.createTitledBorder("Lista Pacjent�w"));
		this.setVisible(true);

		GroupLayout panelGlowny = new GroupLayout(this);
		this.setLayout(panelGlowny);

		this.mTable = new JTable();
		JScrollPane scroll = new JScrollPane(this.mTable);

		mTable.getTableHeader().setReorderingAllowed(false); // nie pozwala przesuwa� kolumn
		mTable.setFillsViewportHeight(true);
		mTable.setRowSelectionAllowed(true);
		mTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		this.mAddButton = new JButton("Dodaj");

		this.mRemoveButton = new JButton("Usu�");

		panelGlowny.setAutoCreateGaps(true);
		panelGlowny.setAutoCreateContainerGaps(true);

		// ustawienie u�o�enia listy JTable oraz przycisk�w

		panelGlowny.setHorizontalGroup(panelGlowny.createSequentialGroup()
				.addGroup(panelGlowny.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(scroll)
						.addGroup(panelGlowny.createSequentialGroup()
								.addGroup(panelGlowny.createParallelGroup().addComponent(mAddButton)).addGroup(
										panelGlowny.createParallelGroup().addComponent(mRemoveButton)))));

		panelGlowny.setVerticalGroup(panelGlowny.createSequentialGroup().addComponent(scroll).addGap(80)
				.addGroup(panelGlowny.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mAddButton)
						.addComponent(mRemoveButton)));

	}
}