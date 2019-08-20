	
import java.awt.EventQueue;
import java.util.ArrayList;
public class PokazOkno 
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               Okno okno = new Okno();
               ModelLista model = new ModelLista();
               Kontroler kontroler = new Kontroler(okno, model);
               okno.setKontroler(kontroler);
               okno.danePacjenta.setKontroler(kontroler, kontroler);
               okno.lista.setKontroler(kontroler);
               okno.badanie.setKontroler(kontroler, kontroler);
            }
        });
    }
}