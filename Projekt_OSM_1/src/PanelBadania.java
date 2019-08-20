import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;


public class PanelBadania extends JPanel 
{


	private static final long serialVersionUID = 1L;
	
	JLabel data;
	JDateChooser kalendarz;
	
	JLabel GGT;
	JTextField wpisaneGGT;
	
	JLabel A1AT;
	JTextField wpisaneA1AT;
	
	JLabel AspAT;
	JTextField wpisaneAspAT;
	
	JButton zapisz;
	JButton anuluj;
	
	JLabel nazwa;
	JLabel pusty;

	public void ustawBadanie(Badanie b)
	{
		kalendarz.setDate(b.getData());
		wpisaneGGT.setText( ((Double)b.getGGT() ).toString());
		wpisaneA1AT.setText( ((Integer)b.getA1AT() ).toString());
		wpisaneAspAT.setText( ((Integer)b.getAspAT() ).toString());	
		
	}
	
	public void wyczyscBadanie()
	{
		kalendarz.setDate(Date.valueOf(LocalDate.now()));
		wpisaneGGT.setText("Prosz� poda� st�enie jako liczb� rzeczywist�");
		wpisaneA1AT.setText("Prosz� poda� st�enie jako liczb� ca�kowit�");
		wpisaneAspAT.setText("Prosz� poda� st�enie jako liczb� ca�kowit�");	
		
	}

	
	public PanelBadania()
	{
		this.setBorder(BorderFactory.createTitledBorder("Badanie"));
		this.setVisible(true);
		
		//DATA
		data = new JLabel("Data:");
		kalendarz = new JDateChooser();
		kalendarz.setDate(Date.valueOf(LocalDate.now()));
		kalendarz.setMaximumSize(new Dimension(600, 20));
		
		//NAZWA BADANIA
		nazwa = new JLabel("Wyniki bada� st�enia w krwi:");
		pusty = new JLabel("");
		
		//BADANE PARAMETRY
		GGT = new JLabel("Gamma-glutamylotransferaza GGT");
		wpisaneGGT = new JTextField("Prosz� poda� st�enie jako liczb� rzeczywist�");
		
		A1AT = new JLabel("Aminotraferaza alaninowa A1AT");
		wpisaneA1AT = new JTextField("Prosz� poda� st�enie jako liczb� ca�kowit�");
		
		AspAT = new JLabel("Aminotraferaza asparganianowa");
		wpisaneAspAT = new JTextField("Prosz� poda� st�enie jako liczb� ca�kowit�");

		zapisz = new JButton("Zapisz");
		anuluj = new JButton("Anuluj");
		
		
		GroupLayout grupa = new GroupLayout(this);
		this.setLayout(grupa);
		
		grupa.setAutoCreateGaps(true);
		grupa.setAutoCreateContainerGaps(true);
		
		grupa.setHorizontalGroup(grupa.createSequentialGroup()
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(data)
						.addComponent(nazwa)
						.addComponent(GGT)
						.addComponent(A1AT)
						.addComponent(AspAT)
						.addGroup(grupa.createSequentialGroup()
								.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(zapisz))
								.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(anuluj))))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(kalendarz)
								.addComponent(pusty)
								.addComponent(wpisaneGGT)
								.addComponent(wpisaneA1AT)
								.addComponent(wpisaneAspAT))
				);
						
		grupa.setVerticalGroup(grupa.createSequentialGroup()
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(80)
						.addComponent(data)
						.addComponent(kalendarz))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(50)
						.addComponent(nazwa)
						.addComponent(pusty))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(80)
						.addComponent(GGT)
						.addComponent(wpisaneGGT))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(80)
						.addComponent(A1AT)
						.addComponent(wpisaneA1AT))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(80)
						.addComponent(AspAT)
						.addComponent(wpisaneAspAT))
				.addGroup(grupa.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(zapisz)
						.addComponent(anuluj))
						);
	}
	
	
	//METODY
	
	
	//ustawia kontroler - dodaje s�uchaczy	
	public void setKontroler( ActionListener kontroler, FocusListener l)
	{
		this.wpisaneGGT.addFocusListener(l);
		this.wpisaneA1AT.addFocusListener(l);
		this.wpisaneAspAT.addFocusListener(l);
		
		
		
		this.zapisz.addActionListener(kontroler);
		this.anuluj.addActionListener(kontroler);
	}

	//gettery i settery - do pobierania i ustawiania p�l prywatnych
	
	public String getWpisaneGGT() 
	{
		return this.wpisaneGGT.getText();
	}

	public void setWpisaneGGT(String wpisaneGGT) 
	{
		this.wpisaneGGT.setText(wpisaneGGT);
	}

	public String getWpisaneA1AT() 
	{
		return this.wpisaneA1AT.getText();
	}

	public void setWpisaneA1AT(String wpisaneA1AT) 
	{
		this.wpisaneA1AT.setText(wpisaneA1AT);
	}

	public String getWpisaneAspAT()
	{
		return this.wpisaneAspAT.getText();
	}

	public void setWpisaneAspAT(String wpisaneAspAT) 
	{
		this.wpisaneAspAT.setText(wpisaneAspAT);
	}
	
	
	public JLabel getData()
	{
		return data;
	}

	public void setData(JLabel data)
	{
		this.data = data;
	}


	//metody zmieniaj�ca t�o textfielda - ma zaznacza� b��d
	public void zmienKolorGGT(Color b)
	{
		wpisaneGGT.setForeground(b);
	}
	public void zmienKolorAspAT(Color b)
	{
		wpisaneAspAT.setForeground(b);
	}
	public void zmienKolorA1AT(Color b)
	{
		wpisaneA1AT.setForeground(b);
	}
}

