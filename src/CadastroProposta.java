import java.util.Calendar;

public class CadastroProposta {
    private int id;
    private Jogador ofereceProp;
    private Jogador recebeProp;
    private Item oferece;
    private Item recebe;
    private String diaHora;
    private boolean aceita;
    private boolean declinada;
    private boolean respondida;

    public void Proposta(Jogador ofereceProp, Jogador recebeProp, Item oferece, Item recebe) {
        Calendar calendar = Calendar.getInstance();
        int mes = calendar.get(Calendar.MONTH) + 1;

        this.id = Item.randCode();
        this.diaHora = calendar.get(Calendar.DAY_OF_MONTH) + "/" + mes + "/" + calendar.get(Calendar.YEAR)
                + " - " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        this.ofereceProp = ofereceProp;
        this.recebeProp = recebeProp;
        this.oferece = oferece;
        this.recebe = recebe;
        this.aceita = false;
        this.declinada = false;
        this.respondida = false;
    }

}