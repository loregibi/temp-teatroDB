import dao.*;
import model.Posto;
import model.Spettacolo;
import model.Utente;

import java.sql.SQLException;
import java.util.*;

public class Teatro {

    private PostoDaoSql postoDaoSql = new PostoDaoSql();
    private SalaDaoSql salaDaoSql = new SalaDaoSql();
    private SedeDaoSql sedeDaoSql = new SedeDaoSql();
    private SpettacoloDaoSql spettacoloDaoSql = new SpettacoloDaoSql();
    private TicketDaoSql ticketDaoSql = new TicketDaoSql();
    private UtenteDaoSql utenteDaoSql = new UtenteDaoSql();

    public void inserisciUtente() throws SQLException {
        System.out.println("** Inserisci utente **");
        Scanner input = new Scanner(System.in);
        System.out.print("nome: ");
        String nome = input.nextLine();
        System.out.print("cognome: ");
        String cognome = input.nextLine();
        System.out.print("mail: ");
        String mail = input.nextLine();
        System.out.print("telefono: ");
        String telefono = input.nextLine();
        System.out.print("indirizzo: ");
        String indirizzo = input.nextLine();

        Utente utente = new Utente(nome,cognome,mail,telefono,indirizzo);
        utenteDaoSql.insert(utente);
    }

    public boolean prenotaSpettacolo() throws SQLException {
        List<Spettacolo> spettacoli = spettacoloDaoSql.getAll();
        Map<Spettacolo, Set<Posto>> postiInSpettacolo = new HashMap<>();

        return false;
    }




}
